package codice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FiltroServlet")
public class FiltroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String m = request.getParameter("materiale");
    	String p = request.getParameter("prezzo");
    	request.getSession().setAttribute("materiale", m);
    	request.getSession().setAttribute("prezzo", p);
    	response.sendRedirect(request.getContextPath() + "/catalogo.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    

    
}