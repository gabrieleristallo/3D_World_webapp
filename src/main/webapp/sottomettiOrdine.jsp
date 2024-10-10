<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<div class="first">
<h2 style="margin-top: 1px;">Riepilogo ordine:</h2>
<%
	Carrello cart = null;
	cart = (Carrello) request.getSession().getAttribute("cart");
	Boolean admin = (Boolean) request.getSession().getAttribute("admin");
	int id_utente = (Integer) request.getSession().getAttribute("id_utente");
	
	if(cart == null || cart.size() == 0) {
%>
		<p class="first" style="text-align: center; font-size: 14px;">Nessun prodotto nel carrello.<br>Visita il nostro <a href="catalogo.jsp">catalogo</a></p>
<%
	} else {
		ArrayList<ProdottoCarrello> prodotti = cart.getCart();
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		for(ProdottoCarrello p : prodotti) {
			String nome = p.getNome();
			int id = p.getId();
			float prezzo = p.getPrezzo();
			int qta = p.getQta();
			String hex = p.getColore();
			System.out.println("carrello.jsp " + hex);
			int id_prodotto = prodottoDao.getIdProdottoByIdColore(id);
%>
			<div class="product-details" style="display: flex; height: 5%">
    <img src="./getPicture?id=<%=id_prodotto %>" onerror="this.src='./images/nophoto.png'" title="id <%=id_prodotto %>" style="height:100px; margin-right: 20px;">
    
    <div>
        <h2><%= nome %></h2>
        
        <div class="description">
            <p>
                <a href="dettagliProdotto.jsp?id=<%=id %>"><strong>Prodotto <%= id %></strong></a><br>
                Colore: <input type="color" value="<%= hex%>" disabled>
            </p>
        </div>
        <div class="info">
            <p>
                Prezzo: <%= String.format("%.2f", prezzo*qta) %> €
            </p>
        </div>
        
    </div>
</div>

<%
		}
%>
		<p style="font-size: 15px">Totale: <%= String.format("%.2f", cart.getTotalPrice()) %> €</p>
		
		<h2 style="margin-top: 1px;">Seleziona un metodo di pagamento: </h2>
		
		
		<%
			UtenteDaoImpl utenteDao = new UtenteDaoImpl();
			ArrayList<Pagamento> lista = utenteDao.getMetodiPagamentoByUtente(id_utente);
			if(lista != null && lista.size() != 0) {
	%>
				<select>
	<%
				for(Pagamento p : lista) {
				    String numeroCarta = p.getNumero_carta();
				    // Ottieni solo le ultime 4 cifre del numero della carta
				    String ultimeQuattroCifre = numeroCarta.substring(numeroCarta.length() - 4);
				    // Sostituisci i primi caratteri con asterischi
				    String numeroCartaNascosto = "**** **** **** " + ultimeQuattroCifre;
	%>
				    <option value="<%=p.getNumero_carta()%>"><%= numeroCartaNascosto %></li>
	<%
				}
	%>
				</select>
	<%
			} else {
	%>
				<p>Non hai ancora inserito un metodo di pagamento</p>
	<%			
			}
	%>
			<form method="post" action="aggiungiMetodoDiPagamento.jsp?provenienza=1&id_utente=<%=id_utente%>">
				<input type="text" name="id" value="<%=id_utente %>" hidden="true">
				<input type="submit" value="Aggiungi una carta di credito">
			</form>
		
		<form method="post" action="AcquistaServlet">
			<input type="submit" value="Acquista" <%= (admin != null && admin) ? "disabled" : "" %>>
		</form>
<%
	}
%>

</div>
<jsp:include page="footer.jsp"/>