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
 * Servlet implementation class AddSuppliersServlet
 */
@WebServlet("/addSuppliers")
public class AddSuppliersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSuppliersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addSuppliers.jsp").forward(request, response);
	 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String supplierName = request.getParameter("supplierName");
		
		
		Suppliers suppliers = new Suppliers();
		suppliers.setSupplierName(supplierName);
		
		try {
			SuppliersDao suppliersDao = DaoFactory.createSuppliersDao();
			suppliersDao.insert(suppliers);
			request.getRequestDispatcher("/WEB-INF/view/addSuppliersDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
