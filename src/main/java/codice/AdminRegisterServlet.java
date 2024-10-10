package codice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String pw = request.getParameter("pw");
		String hash = toHash(pw);
		
		AdminDaoImpl adminDao = new AdminDaoImpl();
		
		adminDao.saveCredenziali(nome, hash);
		response.sendRedirect(request.getContextPath() + "/protected/passwordAdmin.jsp");
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
