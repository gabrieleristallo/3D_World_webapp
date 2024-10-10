package codice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;

@WebServlet("/DeleteFromCartServlet")
public class DeleteFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id");
    	int id = Integer.parseInt(ids);
    	Carrello cart = (Carrello) request.getSession().getAttribute("cart");
    	cart.deleteProdotto(id);
    	request.getSession().setAttribute("cart", cart);
    	response.sendRedirect(request.getContextPath() + "/carrello.jsp");
    }
   
    
}
