<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<jsp:include page="headerAdmin.jsp"/>
<head>
	<style>
		table, h2 {
			margin-left: 20px;
		} table {
			width: 90%;
		}
	</style>
</head>
<div style="padding-left: 20px; padding-top: 40px" >
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
	<%
		String id_ordines = request.getParameter("id");
		int id_ordine = Integer.parseInt(id_ordines);
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		OrdineDaoImpl ordineDao = new OrdineDaoImpl();
		ArrayList<DettaglioOrdine> dettagliOrdine = ordineDao.getDettaglioOrdineById(id_ordine);
	%>
	<strong>Ordine <%=id_ordine %></strong>
	<table border="1" cellpadding="5" cellspacing="0">
		<tr><th>Id</th><th>Id prodotto</th><th>Nome</th><th>Quantità</th><th>Prezzo</th></tr>
	<%
		float cont = 0;
		for(DettaglioOrdine dettaglio : dettagliOrdine) {
			int id_dettaglio_ordine = dettaglio.getId_dettaglio_ordine();
			int qta = dettaglio.getQta();
			float prezzo = dettaglio.getPrezzo();
			
			int id_prodotto_colore = dettaglio.getId_prodotto_colore();
			
			Prodotto p = prodottoDao.getProdottoByIdColor(id_prodotto_colore);
			int id_prodotto = p.getId();
			String nome = p.getNome();
			cont += prezzo;
	%>
		<tr>
			<td><%=id_dettaglio_ordine %></td>
			<td><%=id_prodotto %></td>
			<td><%=nome %></td>
			<td><%=qta %></td>
			<td><%=String.format("%.2f", prezzo)%> €</td>
		</tr>
	<%
		}
	%>
	</table>
	<p><b>Totale: <%=String.format("%.2f", cont) %> €</b></p>
</div>