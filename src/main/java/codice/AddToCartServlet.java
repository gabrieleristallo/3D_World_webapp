package codice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdottoDaoImpl;
import model.Carrello;
import codice.*;
import dao.*;
import model.*;
import model.Prodotto;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String qtas = (String) request.getParameter("qta");
    	int qta = Integer.parseInt(qtas);
    	String ids = (String) request.getParameter("id");
    	int id = Integer.parseInt(ids);
    	String ups = (String) request.getParameter("update");
    	int up = 0;
    	if(ups != null) {
    		up = Integer.parseInt(ups);
    	}
    	
    	String colore = (String) request.getParameter("colore");
    	System.out.println("addToCartServlet " + colore);
    	
    	Carrello cart = (Carrello) request.getSession().getAttribute("cart");
    	
    	if(cart == null) {
    		cart = new Carrello();
    	}
    	
    	Prodotto p;
    	ProdottoColore pc;
    	int idpc;
    	
    	ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
    	if(up != 0) {
    		//l'id che arriva non è quello del prodotto ma quello del colore_prodotto
    		int id_prodotto = prodottoDao.getIdProdottoByIdColore(id);
    		p = prodottoDao.getProdottoById(id_prodotto);
        	pc = prodottoDao.getProdottoColoreByIdAndColor(id_prodotto, colore);
        	idpc = pc.getId_prodotto_colore();
    	} else {
    		//l'id che arriva è quello del prodotto
    		p = prodottoDao.getProdottoById(id);
        	pc = prodottoDao.getProdottoColoreByIdAndColor(id, colore);
        	idpc = pc.getId_prodotto_colore();
    	}
    	
    	
    	try {
			if(p != null) {
				request.getSession().setAttribute("cart", cart);
				if(up != 0) {
					cart.updateProdotto(idpc, qta);
					response.sendRedirect(request.getContextPath() + "/carrello.jsp");
				} else {
					cart.addProdotto(idpc, p.getNome(), p.getDescrizione(), qta, p.getPrezzo(), p.getMateriale(), colore);
					request.getSession().setAttribute("justAdded", cart.getProdotto(idpc));
					response.sendRedirect(request.getContextPath() + "/catalogo.jsp?just=true");
				}
				
			} else {
				response.sendRedirect(request.getContextPath() + "/resultAddToCart.jsp?problem=true");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   
    
}

