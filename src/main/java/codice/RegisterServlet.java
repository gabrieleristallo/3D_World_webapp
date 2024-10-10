package codice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UtenteDaoImpl;
import model.DatabaseManager;
import model.Utente;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nome = request.getParameter("nome");
    	String cognome = request.getParameter("cognome");
    	String classificazione = request.getParameter("classificazione");
    	String indirizzo = request.getParameter("indirizzo");
    	String civico = request.getParameter("civico");
    	String email = request.getParameter("email");
    	String password = request.getParameter("pw");
    	
    	String indirizzo_completo = classificazione + " " + indirizzo + " " + civico;
    	
    	UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    	Utente u = utenteDao.getUtenteByEmail(email);
    	
    	try {
			if(u != null) {
				response.sendRedirect(request.getContextPath() + "/resultRegister.jsp?codice=0"); //RegisterResultJSP ("risulta già un utente") COD: 0
			} else {
				utenteDao.saveUtente(nome, cognome, email, password, indirizzo_completo);
				response.sendRedirect(request.getContextPath() + "/resultRegister.jsp?codice=1"); //RegisterResultJSP ("registrazione succeded") COD: 1
			}
		} catch (Exception e) {
	    	response.sendRedirect(request.getContextPath() + "/resultRegister.jsp?codice=2"); //RegisterResultJSP ("errore") COD: 2
			System.out.println(e.getMessage());
		}

    }
    
    /*private String toHash(String password) {
    	String hashString = null;
    	try {
    		java.security.MessageDigest digest = java.security.MessageDigest.getInstance ("SHA-512");
    		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    		hashString = "";
    		for (int i = 0; i < hash.length; i++) {
    			hashString += Integer. toHexString((hash[i] &0xFF) | 0x100). toLowerCase() .substring(1,3);
    		}
    	} catch(java. security.NoSuchAlgorithmException e) {
    		System.out.println(e);
    	}
    	return hashString;
    }*/

    
}
