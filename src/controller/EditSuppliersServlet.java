package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.SuppliersDao;
import domain.Suppliers;

/**
 * Servlet implementation class EditSuppliersServlet
 */
@WebServlet("/editSuppliers")
public class EditSuppliersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSuppliersServlet() {
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
			 SuppliersDao suppliersDao = DaoFactory.createSuppliersDao();
			 Suppliers suppliers = suppliersDao.findById(id);
			 request.setAttribute("supplierName", suppliers.getSupplierName());
			 request.getRequestDispatcher("/WEB-INF/view/editSuppliers.jsp")
			 .forward(request, response);
			 } catch (Exception e) {
			 throw new ServletException(e);
			 }
			 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String supplierName = request.getParameter("supplierName");

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			SuppliersDao suppliersDao = DaoFactory.createSuppliersDao();
			Suppliers suppliers = suppliersDao.findById(id);
			suppliers.setSupplierName(supplierName);
			suppliersDao.update(suppliers);
			request.getRequestDispatcher("/WEB-INF/view/editSuppliersDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
