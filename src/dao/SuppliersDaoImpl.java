package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Suppliers;

public class SuppliersDaoImpl implements SuppliersDao {

	private DataSource ds;

	public SuppliersDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Suppliers> findAll() throws Exception {
		List<Suppliers> suppliersList = new ArrayList<>();
		try(Connection con = ds.getConnection()){
			String sql = "SELECT DISTINCT * FROM suppliers ORDER BY suppliers.id ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				suppliersList.add(mapToSuppliers(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return suppliersList;
	}


	@Override
	public Suppliers findById(Integer id) throws Exception {
		Suppliers suppliers = null;
		try(Connection con = ds.getConnection()){
			String sql = "SELECT * FROM suppliers WHERE suppliers.id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				suppliers = mapToSuppliers(rs);
			}
		}	catch(Exception e) {
				throw e;
			}
		return suppliers;
	}


	@Override
	public void insert(Suppliers suppliers) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "INSERT INTO suppliers(supplier_name)VALUES(?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, suppliers.getSupplierName());

			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(Suppliers suppliers) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "UPDATE suppliers SET supplier_name=? WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, suppliers.getSupplierName());
			stmt.setInt(2, suppliers.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Suppliers suppliers) throws Exception {
			int id = suppliers.getId();
			try(Connection con = ds.getConnection()){
				String sql = "DELETE FROM suppliers WHERE id=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}  catch (Exception e) {
				throw e;
			}

		}


	private Suppliers mapToSuppliers(ResultSet rs) throws Exception{
		Suppliers suppliers = new Suppliers();
		suppliers.setId((Integer)rs.getObject("id"));
		suppliers.setSupplierName(rs.getString("supplier_name"));
		return suppliers;
	}
}
