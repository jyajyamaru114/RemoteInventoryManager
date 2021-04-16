package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ItemDao;
import domain.Item;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/editItem")
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 String strId = request.getParameter("id");
			 Integer id = Integer.parseInt(strId);
			 ItemDao itemDao = DaoFactory.createItemDao();
			 Item item = itemDao.findById(id);
			 request.setAttribute("itemName", item.getItemName());
			 request.getRequestDispatcher("/WEB-INF/view/editItem.jsp").forward(request, response);
			 } catch (Exception e) {
			 throw new ServletException(e);
			 }
			 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemName = request.getParameter("itemName");

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			ItemDao itemDao = DaoFactory.createItemDao();
			Item item = itemDao.findById(id);
			item.setItemName(itemName);
			itemDao.update(item);
			request.getRequestDispatcher("/WEB-INF/view/editItemDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
