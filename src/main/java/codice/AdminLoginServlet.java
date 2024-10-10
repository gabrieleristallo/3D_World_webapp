package codice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDaoImpl;
import model.Admin;
import model.DatabaseManager;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String hash = toHash(password);
    	String redirect = null;
    	
    	try {
			checkLogin(username, hash);
    		request.getSession().setAttribute("admin", true);
    		redirect = "/protected/indexAdmin.jsp"; //!!!!!!
    	} catch (Exception e) {
    		request.getSession().setAttribute("admin", false);
    		redirect = "/error.jsp"; //!!!!!
    	}
    	response.sendRedirect(request.getContextPath() + redirect);
    	
    }
    
    private void checkLogin(String username, String password) throws Exception {
    	AdminDaoImpl adminDao = new AdminDaoImpl();
    	ArrayList<Admin> lista = adminDao.getCredenziali();
    	for(Admin a : lista) {
    		if(a.getPw().equals(password) && a.getUtente().equals(username)) {
    			return;
    		}
    	}
    	throw new Exception("Invalid login");
    }
    
    private String toHash(String password) {
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
    }

}
