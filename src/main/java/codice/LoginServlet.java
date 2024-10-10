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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("pw");
    	String provenienza = request.getParameter("provenienza");
    	String redirect = null;
    	
    	try {
			int id = checkLogin(email, password);
			System.out.println("Login servlet -> id utente loggato: " + id);
    		request.getSession().setAttribute("loggedIn", true);
    		request.getSession().setAttribute("id_utente", id);
    		System.out.println(request.getSession().getAttribute("id_utente"));
    		String nome = getNome(id, email, password);
    		if(provenienza != null) {
    			redirect="/carrello.jsp";
    		} else {
    			redirect = "/index.jsp?new=1&nome=" + nome;
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		request.getSession().setAttribute("loggedIn", false);
    		redirect = "/resultRegister.jsp?codice=3";
    	}
    	response.sendRedirect(request.getContextPath() + redirect);
    	
    }
    
    private int checkLogin(String email, String password) throws Exception {
    	UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    	//System.out.println("email e pw: " + email + " " + password);
    	Utente u = utenteDao.getUtenteBycredentials(email, password);
    	//System.out.println("utente in Login Servlet " + u);
    	if(u == null) {
			throw new Exception("Invalid login");
		} else {
			return u.getId();
		}
    }
    
    private String getNome(int id, String email, String password) {
    	UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    	Utente u = utenteDao.getUtenteBycredentials(email, password);
    	return u.getNome();
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
