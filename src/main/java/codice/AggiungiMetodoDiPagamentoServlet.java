package codice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import model.*;

@WebServlet("/AggiungiMetodoDiPagamentoServlet")
public class AggiungiMetodoDiPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String provenienza = request.getParameter("provenienza");
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		String numeroCarta = request.getParameter("numeroCarta");
		String scadenza = request.getParameter("scadenza");
		String codice = request.getParameter("cvv");
		
		
		UtenteDaoImpl utenteDao = new UtenteDaoImpl();
		utenteDao.saveMetodoPagamento(id, numeroCarta, scadenza, codice);
		String redirect;
		if(provenienza.equals("1")) {
			redirect = "/sottomettiOrdine.jsp";
		} else {
			redirect = "/profilo.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirect);
	}

}
