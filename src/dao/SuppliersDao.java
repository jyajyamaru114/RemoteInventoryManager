package dao;

import java.util.List;

import domain.Suppliers;

public interface SuppliersDao {

	List<Suppliers> findAll() throws Exception;

	Suppliers findById(Integer id) throws Exception;

	void insert(Suppliers suppliers) throws Exception;

	void update(Suppliers suppliers) throws Exception;

	void delete(Suppliers suppliers) throws Exception;

}
