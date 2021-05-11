package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Item;

public class ItemDaoImpl implements ItemDao {

	private DataSource ds;

	public ItemDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override//findAll()が呼ばれたら、配列List<item>〇〇に指定したデータベースからデータが入る
	public List<Item> findAll() throws Exception {
		List<Item> itemList = new ArrayList<>();
		try(Connection con = ds.getConnection()){
			String sql = "SELECT DISTINCT * FROM item ORDER BY item.id ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				itemList.add(mapToItem(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return itemList;
	}


	@Override
	public Item findById(Integer id) throws Exception {
		Item item = null;
		try(Connection con = ds.getConnection()){
			String sql = "SELECT * FROM item WHERE item.id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				item = mapToItem(rs);
			}
		}	catch(Exception e) {
				throw e;
			}
		return item;
	}


	@Override
	public void insert(Item item) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "INSERT INTO item(item_name)VALUES(?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, item.getItemName());

			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(Item item) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "UPDATE item SET item_name=? WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, item.getItemName());
			stmt.setInt(2, item.getId());

			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Item item) throws Exception {
			int id = item.getId();
			try(Connection con = ds.getConnection()){
				String sql = "DELETE FROM item WHERE id=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}  catch (Exception e) {
				throw e;
			}

		}


	private Item mapToItem(ResultSet rs) throws Exception{
		Item item = new Item();
		item.setId((Integer)rs.getObject("id"));
		item.setItemName(rs.getString("item_name"));
		return item;
	}
}
