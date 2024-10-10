package codice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import dao.*;
import model.*;

@WebServlet("/CercaEmailServlet")
public class CercaEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
		
        
        UtenteDaoImpl utenteDao = new UtenteDaoImpl();
		
		String parametro = request.getParameter("parametro");
		Utente utente = utenteDao.getUtenteByEmail(parametro);
		
		String email = "";
		
		if(utente == null) {
			email = "";
		} else {
			email = utente.getEmail();
		}

	     JSONObject json = new JSONObject();
	     json.put("functionName", "aggiornaDatiProdottiJSON");
	     System.out.println(email);
	     json.put("result", email);
	        
	     out.print(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

