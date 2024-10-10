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

@WebServlet("/AggiungiProdottoColoreServlet")
public class AggiungiProdottoColoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id_prodotto");
    	String colore = request.getParameter("colore");
    	String nomeColore = request.getParameter("nome_colore");
    	String qtas = request.getParameter("qta");
    	int id = Integer.parseInt(ids);
    	int qta = Integer.parseInt(qtas);
    	
    	System.out.println(ids + " " + colore);
    	
    	ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
    	prodottoDao.saveProdottoColore(id, nomeColore, colore, qta);
    	response.sendRedirect(request.getContextPath() + "/protected/aggiornaProdotto.jsp?id=" + id);
    }
}
