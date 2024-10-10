package codice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;

@WebServlet("/AggiornaUtenteServlet")
public class AggiornaUtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id");
    	int id = Integer.parseInt(ids);
    	String nome = request.getParameter("nome");
    	String cognome = request.getParameter("cognome");
    	String email = request.getParameter("email");
    	String password = request.getParameter("pw");
    	String classificazione = request.getParameter("classificazione");
    	String indirizzo = request.getParameter("indirizzo");
    	String civico = request.getParameter("civico");
    	String indirizzo_completo = classificazione + " " + indirizzo + " " + civico;
    	UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    	utenteDao.updateUtente(id, nome, cognome, email, password, indirizzo_completo);
    	response.sendRedirect(request.getContextPath() + "/profilo.jsp");
    }
}