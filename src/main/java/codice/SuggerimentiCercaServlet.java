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

@WebServlet("/SuggerimentiCercaServlet")
public class SuggerimentiCercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
		
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		
		String parametro = request.getParameter("parametro");
		ArrayList<Prodotto> lista = prodottoDao.getProdottiByParameterSug(parametro);
		
		 ArrayList<String> nomiProdotti = new ArrayList<>();
	     for (Prodotto prodotto : lista) {
	    	 nomiProdotti.add(prodotto.getNome());
	     }
	     
	     ordinamento(nomiProdotti);
	     
	     JSONArray jsonArray = new JSONArray(nomiProdotti);

	     JSONObject json = new JSONObject();
	     json.put("functionName", "aggiornaDatiProdottiJSON");
	     json.put("result", jsonArray);
	        
	     out.print(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void ordinamento(ArrayList<String> list) {
		//based on bubble sort
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).length() < list.get(j + 1).length()) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }


}
