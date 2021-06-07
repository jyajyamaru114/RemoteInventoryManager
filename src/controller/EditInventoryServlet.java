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
 * Servlet implementation class EditInventoryServlet
 */
@WebServlet("/editInventory")
public class EditInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditInventoryServlet() {
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
			InventoryDao inventoryDao = DaoFactory.createInventoryDao();
			//List<Inventory> inventoryList = inventoryDao.findAll();
			//request.setAttribute("inventoryList", inventoryList);
			//この機能でも同様の処理に使用できる、フォームの入力時表示。

			List<Inventory> inventoryDistinctList1 = inventoryDao.findDistinct1();
			List<Inventory> inventoryDistinctList2 = inventoryDao.findDistinct2();

			request.setAttribute("inventoryDistinctList1", inventoryDistinctList1);
			request.setAttribute("inventoryDistinctList2", inventoryDistinctList2);

			Inventory inventory = inventoryDao.findById(id);
			request.setAttribute("supplierId", inventory.getSupplierId());
			request.setAttribute("itemId", inventory.getItemId());
			request.setAttribute("price", inventory.getPrice());
			request.setAttribute("quantity", inventory.getQuantity());
			request.getRequestDispatcher("/WEB-INF/view/editInventory.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isError = false;

		Integer supplierId = Integer.parseInt(request.getParameter("supplierId"));
		request.setAttribute("supplierId", supplierId);

		Integer itemId = Integer.parseInt(request.getParameter("itemId"));
		request.setAttribute("itemId", itemId);

		Integer price = null;
		String strPrice = request.getParameter("price");
		request.setAttribute("price", strPrice);
		if(strPrice != null && !strPrice.isEmpty()) {
			try {
				price = Integer.parseInt(strPrice);
		} catch (NumberFormatException e) {
			request.setAttribute("priceError", "価格が未入力です。");
			isError = true;
		}
	}


		Integer quantity = null;
		String strQuantity = request.getParameter("quantity");
		request.setAttribute("quantity", strQuantity);
		if(strQuantity != null && !strQuantity.isEmpty()) {
			try {
				quantity = Integer.parseInt(strQuantity);
		} catch (NumberFormatException e){
			request.setAttribute("quantityError", "個数が未入力です。");
			isError = true;
		}
	}

		String memo = request.getParameter("memo");
		request.setAttribute("memo", memo);


		if(!isError) {

			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				InventoryDao inventoryDao = DaoFactory.createInventoryDao();
				Inventory inventory = inventoryDao.findById(id);
				inventory.setSupplierId(supplierId);
				inventory.setItemId(itemId);
				inventory.setPrice(price);
				inventory.setQuantity(quantity);
				inventory.setMemo(memo);
				inventoryDao.update(inventory);
				request.getRequestDispatcher("/WEB-INF/view/editInventoryDone.jsp").forward(request, response);

			}  catch (Exception e) {
				throw new ServletException(e);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/view/editMember.jsp").forward(request, response);
		}
	}

}
