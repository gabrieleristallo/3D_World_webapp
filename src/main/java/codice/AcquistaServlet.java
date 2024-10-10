package codice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import model.*;
import codice.*;

@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Carrello cart = (Carrello) request.getSession().getAttribute("cart");
    	Boolean loggedin = (Boolean) request.getSession().getAttribute("loggedIn");
    	OrdineDaoImpl ordineDao = new OrdineDaoImpl();
    	String dataOggi = FormatoData.getDataAttuale();
    	if(loggedin == null) { //prima volta che si apre il sito, non esiste ancora il paramento loggedin
    		response.sendRedirect(request.getContextPath() + "/login.jsp?provenienza=1");
    		return;
    	} else if(!loggedin) { //loggedin esiste ma è falso
    		response.sendRedirect(request.getContextPath() + "/login.jsp?provenienza=1");
    		return;
    	} else if(cart == null) { //l'utente ha acceduto ma non esistono prodotti nel carrello
    		response.sendRedirect(request.getContextPath() + "/carrello.jsp");
    		return;
    	}
    	
    	int id = (Integer) request.getSession().getAttribute("id_utente");
    	
    	UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    	
    	ArrayList<Pagamento> pagamento = utenteDao.getMetodiPagamentoByUtente(id);
    	if(pagamento == null || pagamento.size() == 0) { //L'utenter ha acceduto, ha prodotti nel carrello ma non ha metodi di pagamento
    		
    	}
    	
    	int id_ordine = ordineDao.saveOrdine(id, dataOggi, cart.getTotalPrice());
    	
    	ArrayList<ProdottoCarrello> prodotti = cart.getCart();
    	for(ProdottoCarrello prodotto : prodotti) {
    		try {
				ordineDao.saveDettaglioOrdine(id_ordine, prodotto.getId(), prodotto.getQta(), prodotto.getPrezzo()*prodotto.getQta());
			} catch (Exception e) {
				String codice = e.getMessage().split(" ")[3];
				response.sendRedirect(request.getContextPath() + "/qtaError.jsp?id=" + codice);
				return;
			}
    	}
    	//Da qui in poi l'acquisto è andato bene
    	
    	
    	
    	request.getSession().setAttribute("cart", null);
    	response.sendRedirect(request.getContextPath() + "/confermaOrdine.jsp?id_ordine=" + id_ordine);
    	
    }
}