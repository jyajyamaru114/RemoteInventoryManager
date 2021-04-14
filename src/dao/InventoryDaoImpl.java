package dao;

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
			String sql = "SELECT item_id,sum(quantity) FROM inventory GROUP BY item_id" ;
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
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, inventory.getSupplierId(), Types.INTEGER);
			stmt.setObject(2, inventory.getItemId(), Types.INTEGER);
			stmt.setObject(3, inventory.getPrice(), Types.INTEGER);
			stmt.setObject(4, inventory.getQuantity(), Types.INTEGER);
			stmt.setString(5, inventory.getMemo());
			stmt.setInt(5, inventory.getId());
			stmt.executeUpdate();
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
	}

	private Inventory mapToCount(ResultSet rs) throws Exception{
		Inventory inventory = new Inventory();
		inventory.setItemId((Integer)rs.getObject("item_id"));
		inventory.setSum(quantity)((Integer)rs.getObject("sum"));

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
