<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<div class="first">
<h2 style="margin-top: 1px;">Carrello</h2>
<%
	Carrello cart = null;
	cart = (Carrello) request.getSession().getAttribute("cart");
	Boolean admin = (Boolean) request.getSession().getAttribute("admin");
	Integer ids = (Integer) request.getSession().getAttribute("id_utente");
	int id_utente = 0;
	if(ids != null) {
		id_utente = ids;
	}
	
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
			<div class="product-details" style="display: flex;">
    <img src="./getPicture?id=<%=id_prodotto %>" onerror="this.src='./images/nophoto.png'" title="id <%=id_prodotto %>" style="height:160px; margin-right: 20px;">
    
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
        <form method="post" action="AddToCartServlet">
            <input type="number" value="<%=id %>" name="id" hidden="true">
            <input type="number" value="1" name="update" hidden="true">
            <input type="color" name="colore" value="<%= hex%>" hidden="true">
            <%System.out.println("Sto in mezzo al codice che crea il form per aggiornare" + hex + " id: " + id); %>
            <label for="quantity">Quantità:</label>
            <input type="number" id="quantity" name="qta" min="1" max="5" value="<%=qta%>">
            <input type="submit" value="Aggiorna">
        </form>
        <form method="post" action="DeleteFromCartServlet" style="display: flex; justify-content: space-between; margin-right: 10px;">
            <input type="number" value="<%=id %>" name="id" hidden="true">
            <input type="submit" value="Elimina">
        </form>
    </div>
</div>

<%
		}
%>
		<p style="font-size: 15px">Totale: <%= String.format("%.2f", cart.getTotalPrice()) %> €</p>
		
		<form method="post" action="<%= ids == null ? "login.jsp" : "sottomettiOrdine.jsp?id_utente=" + id_utente%>">
			<input type="submit" value="Acquista" <%= (admin != null && admin) ? "disabled" : "" %>>
		</form>
<%
	}
%>

</div>
<jsp:include page="footer.jsp"/>