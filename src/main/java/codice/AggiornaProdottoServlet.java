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

@WebServlet("/AggiornaProdottoServlet")
public class AggiornaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id");
    	String nome = request.getParameter("nome");
    	System.out.println(ids + " " + nome);
    	String descrizione = request.getParameter("descrizione");
    	String materiale = request.getParameter("materiale");
    	String prezzos = request.getParameter("prezzo");
    	String visibiles = request.getParameter("visibilita");
    	int visibile = Integer.parseInt(visibiles);
    	int id = Integer.parseInt(ids);
    	float prezzo = Float.parseFloat(prezzos);

    	ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
    	
    	prodottoDao.updateProdotto(id, nome, descrizione, prezzo, materiale, visibile);
    	response.sendRedirect(request.getContextPath() + "/protected/aggiornaProdotto.jsp?id=" + id + "&aggiornato=1");
    }
}
