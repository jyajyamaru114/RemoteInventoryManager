package dao;

import java.util.List;

import domain.Admin;

public interface AdminDao {

	List<Admin> findAll() throws Exception;

	Admin findById(Integer id) throws Exception;

	void insert(Admin admin) throws Exception;
	//戻り値の型 メソッド名（引数） 例外を投げる

	void update(Admin admin) throws Exception;

	void dalete(Admin admin) throws Exception;

	Admin findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception;

}
