package codice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrdinamentoServlet")
public class OrdinamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String o = request.getParameter("ordine");
    	String v = request.getParameter("valore");
    	request.getSession().setAttribute("ordine", o);
    	request.getSession().setAttribute("valore", v);
    	response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    

    
}