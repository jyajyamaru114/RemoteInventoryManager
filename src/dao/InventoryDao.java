package dao;

import java.util.List;

import domain.Inventory;

public interface InventoryDao {

	List<Inventory> findAll() throws Exception;

	List<Inventory> findCount() throws Exception;

	List<Inventory> findDistinct1() throws Exception;

	List<Inventory> findDistinct2() throws Exception;

	Inventory findById(Integer id) throws Exception;


	void insert(Inventory inventory) throws Exception;

	void insertSplierName(Inventory inventory) throws Exception;

	void insertItemName(Inventory inventory) throws Exception;

	void update(Inventory inventory) throws Exception;

	void delete(Inventory inventory) throws Exception;

}
