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

		try {
			InventoryDao inventoryDao = DaoFactory.createInventoryDao();

			List<Inventory> inventoryDistinctList1 = inventoryDao.findDistinct1();
			List<Inventory> inventoryDistinctList2 = inventoryDao.findDistinct2();
			request.setAttribute("inventoryDistinctList1", inventoryDistinctList1);
			request.setAttribute("inventoryDistinctList2", inventoryDistinctList2);
		    request.getRequestDispatcher("/WEB-INF/view/addInventory.jsp").forward(request, response);
	}
		catch (Exception e) {
			throw new ServletException(e);
		}
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
		Integer supplierId = Integer.parseInt(request.getParameter("supplierId"));
		Integer itemId = Integer.parseInt(request.getParameter("itemId"));

		Integer price = null;
		String strPrice = request.getParameter("price");
		request.setAttribute("price", strPrice);
		if(strPrice != null && !strPrice.isEmpty()) {
			try {
				price = Integer.parseInt(strPrice);
			} catch (NumberFormatException e) {
				request.setAttribute("priceError", "価格が未入力です");
				isError = true;
			}
		}

		Integer quantity = null;
		String strQuantity = request.getParameter("quantity");
		request.setAttribute("quantity", strQuantity);
		if(strQuantity != null && !strQuantity.isEmpty()) {
			try {
				quantity = Integer.parseInt(strQuantity);
			} catch (NumberFormatException e) {
				request.setAttribute("quantityError", "個数が未入力です");
				isError = true;
			}
		}
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