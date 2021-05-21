package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Inventory;

public class InventoryDaoImpl implements InventoryDao {

	private DataSource ds;

	public InventoryDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Inventory> findAll() throws Exception {
		List<Inventory> inventoryList = new ArrayList<>();
		//Inventoryクラス型のデータを格納するList
		try(Connection con = ds.getConnection()){
			String sql = "SELECT DISTINCT * FROM inventory JOIN suppliers ON inventory.supplier_id = suppliers.id JOIN item ON inventory.item_id = item.id ORDER BY inventory.id ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				inventoryList.add(mapToInventory(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return inventoryList;
	}


	@Override
	public List<Inventory> findCount() throws Exception {
		List<Inventory> inventoryCountList = new ArrayList<>();
		try(Connection con = ds.getConnection()){
			String sql = "SELECT item_name,sum(quantity), sum(quantity) AS countQuantity FROM inventory JOIN suppliers ON inventory.supplier_id = suppliers.id JOIN item ON inventory.item_id = item.id GROUP BY item_id;" ;
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				inventoryCountList.add(mapToCount(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return inventoryCountList;
	}

	@Override
	public List<Inventory> findDistinct1() throws Exception {
		List<Inventory> inventoryDistinctList1 = new ArrayList<>();
		try(Connection con = ds.getConnection()){
			String sql = "SELECT DISTINCT id, supplier_name, id AS supplier_id  FROM suppliers";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				inventoryDistinctList1.add(mapToDistinct1(rs));
			}
		}   catch (Exception e) {
			throw e;
		}
//在庫の追加機能フォームで、仕入先名と在庫品名の入力を簡略化する
//仕入先名を表示させるためにsql文でidとnameの重複表示を禁止し、
//idのカラム名をテーブル名suppliersではなくinventory（追加して表示するテーブル）のsupplier_nameに変更
//idとnameの2つを指定する訳はフォームの選択肢で、idを基にnameを表示したいため


	 return inventoryDistinctList1;
	}

	@Override
	public List<Inventory> findDistinct2() throws Exception {
		List<Inventory> inventoryDistinctList2 = new ArrayList<>();
		try(Connection con = ds.getConnection()){
			String sql = "SELECT DISTINCT id, item_name, id AS item_id FROM item";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				inventoryDistinctList2.add(mapToDistinct2(rs));
			}
		}   catch (Exception e) {
			throw e;
		}

	 return inventoryDistinctList2;
	}

	@Override
	public Inventory findById(Integer id) throws Exception {
		Inventory inventory = null;
		try(Connection con = ds.getConnection()){
			String sql = "SELECT * FROM inventory JOIN suppliers ON inventory.supplier_id = suppliers.id JOIN item ON inventory.item_id = item.id WHERE inventory.id = ?";
			//指定したidを持つデータを参照
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				inventory = mapToInventory(rs);
			}
		}	catch(Exception e) {
				throw e;
			}
		return inventory;
	}



	@Override
	public void insert(Inventory inventory) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "INSERT INTO inventory(supplier_id, item_id, price, quantity, memo, created)VALUES(?, ?, ?, ?, ?, NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, inventory.getSupplierId(),Types.INTEGER);
			stmt.setObject(2, inventory.getItemId(),Types.INTEGER);
			stmt.setObject(3, inventory.getPrice(),Types.INTEGER);
			stmt.setObject(4, inventory.getQuantity(),Types.INTEGER);
			stmt.setString(5, inventory.getMemo());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public void update(Inventory inventory) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "UPDATE inventory SET supplier_id=?, item_id=?, price=?, quantity=?, memo=? WHERE id=?";
			//指定したidを持つレコードの選択したカラムを更新するための記述
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, inventory.getSupplierId(), Types.INTEGER);
			stmt.setObject(2, inventory.getItemId(), Types.INTEGER);
			stmt.setObject(3, inventory.getPrice(), Types.INTEGER);
			stmt.setObject(4, inventory.getQuantity(), Types.INTEGER);
			stmt.setString(5, inventory.getMemo());
			stmt.setInt(6, inventory.getId());
			//sql文で指定したデータの中に合致するidがあればそのデータを取得
			//変更したい部分を変えてデータベースの更新を行う。
			stmt.executeUpdate();//sql文の実行
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Inventory inventory) throws Exception {
		int id = inventory.getId();
		try(Connection con = ds.getConnection()){
			String sql = "DELETE FROM inventory WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}  catch (Exception e) {
			throw e;
		}

	}



	private Inventory mapToInventory(ResultSet rs) throws Exception{
		Inventory inventory = new Inventory();
		//Inventory型変数inventoryを定義 この変数にInventoryクラスのインスタンスを格納
		inventory.setId((Integer)rs.getObject("id"));
		inventory.setSupplierId((Integer)rs.getObject("supplier_id"));
		inventory.setItemId((Integer)rs.getObject("item_id"));
		inventory.setSupplierName(rs.getString("supplier_name"));
		inventory.setItemName(rs.getString("item_name"));
		inventory.setPrice((Integer)rs.getObject("price"));
		inventory.setQuantity((Integer)rs.getObject("quantity"));
		inventory.setMemo(rs.getString("memo"));
		inventory.setCreated(rs.getTimestamp("created"));
		inventory.setUpdate(rs.getTimestamp("update"));
		return inventory;
	}//sql文で取得したデータを、処理で使いたい形に作り替えている

	private Inventory mapToCount(ResultSet rs) throws Exception{
		Inventory inventory = new Inventory();
		inventory.setItemName(rs.getString("item_name"));
		inventory.setCountQuantity((BigDecimal)rs.getObject("CountQuantity"));

		return inventory;
	}

	private Inventory mapToDistinct1(ResultSet rs) throws Exception{
		Inventory inventory = new Inventory();
		inventory.setSupplierId((Integer)rs.getObject("supplier_id"));
		inventory.setSupplierName(rs.getString("supplier_name"));
		return inventory;
	}


	private Inventory mapToDistinct2(ResultSet rs) throws Exception{
		Inventory inventory = new Inventory();
		inventory.setItemId((Integer)rs.getObject("item_id"));
		inventory.setItemName(rs.getString("item_name"));
		return inventory;
	}









}
