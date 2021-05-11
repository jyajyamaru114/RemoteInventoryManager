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
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addItem.jsp").forward(request, response);
	 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//addItem.jspで入力した追加在庫品名をデータベースに反映させる？
		//POST送信されたパラメータを変数名itemNameに格納
		String itemName = request.getParameter("itemName");


		//domainのitem.javaのitemクラスをインスタンス化
		//一つ上でjspで入力したパラメータを変数itemNameに格納しているので
		//これをsetItemNameメソッドの引数にしてメソッドを呼ぶ
		//これによりデータベースに在庫品名を追加？（↓のinsertを使って反映）
		Item item = new Item();
		item.setItemName(itemName);


		try {
			ItemDao itemDao = DaoFactory.createItemDao();
			itemDao.insert(item);
			request.getRequestDispatcher("/WEB-INF/view/addItemDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
