package codice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import model.*;
import codice.*;

@WebServlet("/AggiornaProdottoColoreServlet")
public class AggiornaProdottoColoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id_prodottos = request.getParameter("id_prodotto");
    	int id_prodotto = Integer.parseInt(id_prodottos);
    	String ids = request.getParameter("id_prodotto_colore");
    	String qtas = request.getParameter("qta");
    	int qta = Integer.parseInt(qtas);
    	int id = Integer.parseInt(ids);
    	ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
    	prodottoDao.updateProdottoColore(id, qta);
    	response.sendRedirect(request.getContextPath() + "/protected/aggiornaProdotto.jsp?id=" + id_prodotto + "&aggiornato=1");
    }
}

