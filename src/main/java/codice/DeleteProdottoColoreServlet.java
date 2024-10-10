package codice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;

@WebServlet("/DeleteProdottoColoreServlet")
public class DeleteProdottoColoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("id_prodotto_colore");
		String id_prodottos = request.getParameter("id_prodotto");
		int id = 0;
		int id_prodotto = 0;
		if(ids != null) {
			id = Integer.parseInt(ids);
		}
		if(id_prodottos != null) {
			id_prodotto = Integer.parseInt(id_prodottos);
		}
		
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		prodottoDao.deleteProdottoColore(id);
		
		response.sendRedirect(request.getContextPath() + "/protected/aggiornaProdotto.jsp?id="+ id_prodotto + "&aggiornato=1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
