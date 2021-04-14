package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.InventoryDao;
import domain.Inventory;

/**
 * Servlet implementation class ItemCountServlet
 */
@WebServlet("/ItemCountServlet")
public class ItemCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			InventoryDao inventoryDao = DaoFactory.createInventoryDao();
			//Daoと繋ぐため（記述したデータベースとの接続や機能を使えるように）
			List<Inventory> inventoryCountList = inventoryDao.findCount();
			//Listに情報を入れる。DaoImplのfindAll()の機能を使うため
			request.setAttribute("inventoryCountList", inventoryCountList);
			request.getRequestDispatcher("/WEB-INF/view/itemCount.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
