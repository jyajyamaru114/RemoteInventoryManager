package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.InventoryDao;
import domain.Inventory;

/**
 * Servlet implementation class AddInventoryServlet
 */
@WebServlet("/addInventory")
public class AddInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInventoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		    request.getRequestDispatcher("/WEB-INF/view/addInventory.jsp").forward(request, response);


}
	//listInventory.jspからdoGet
	//doGetからaddInventory.jsp
	//addminInventory.jspで値を入力してdoPostへ
	//doPostからaddInventoryDone.jspへ

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isError = false;

		//値の取得



		Integer supplierId = null;
		String strSupplierId = request.getParameter("supplierId");
		request.setAttribute("supplierId", strSupplierId);
		if(strSupplierId != null && !strSupplierId.isEmpty()) {
			try {
				supplierId = Integer.parseInt(strSupplierId);
			} catch (NumberFormatException e) {
				request.setAttribute("supplierIdError", "価格が未入力です");
				isError = true;
			}
		}

		Integer itemId = null;
		String strItemId = request.getParameter("itemId");
		request.setAttribute("itemId", strItemId);
		if(strItemId != null && !strItemId.isEmpty()) {
			try {
				itemId = Integer.parseInt(strItemId);
			} catch (NumberFormatException e) {
				request.setAttribute("itemIdError", "価格が未入力です");
				isError = true;
			}
		}

		Integer price = Integer.parseInt(request.getParameter("price"));

		Integer quantity = Integer.parseInt(request.getParameter("quantity"));


		String memo = request.getParameter("memo");
		request.setAttribute("memo", memo);



		if(!isError) {
		//取得したものをセット
		Inventory inventory = new Inventory();
		inventory.setSupplierId(supplierId);
		inventory.setItemId(itemId);
		inventory.setPrice(price);
		inventory.setQuantity(quantity);
		inventory.setMemo(memo);

		// debug
		System.out.println(inventory);

		try {
			InventoryDao inventoryDao = DaoFactory.createInventoryDao();
			inventoryDao.insert(inventory);
			request.getRequestDispatcher("/WEB-INF/view/addInventoryDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	} else {
		 // フォームの再表示
		 request.getRequestDispatcher("/WEB-INF/view/addInventory.jsp").forward(request, response);
		 }
   }
}