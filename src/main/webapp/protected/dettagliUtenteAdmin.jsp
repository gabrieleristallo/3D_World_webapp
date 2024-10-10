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
<div style="padding-left: 20px; padding-top: 40px">
	<%
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		UtenteDaoImpl utenteDao = new UtenteDaoImpl();
		OrdineDaoImpl ordineDao = new OrdineDaoImpl();
		
		Utente u = utenteDao.getUtenteById(id);
		ArrayList<Ordine> ordini = ordineDao.getOrdineByUtente(id);
	%>
	<strong>Utente <%=u.getId() %> - <%=u.getNome() %> <%=u.getCognome() %></strong>
	<p>Email: <b><%=u.getEmail() %></b><br>Indirizzo: <b><%=u.getIndirizzo() %></b></p>
	<p><b>Ordini effettuati</b></p>
	<%
		if(ordini != null && ordini.size() != 0) {
			%>
			<table border="1" cellpadding="5" cellspacing="0">
				<tr><th>Id</th><th>Data</th><th>Totale</th><th>Azioni</th></tr>
			<%
				for(Ordine ordine : ordini) {
			%>
				<tr><td><%=ordine.getId_ordine() %></td><td><%=ordine.getData() %></td><td><%=ordine.getTotale() %> €</td><td><a href="dettagliOrdineAdmin.jsp?id=<%=ordine.getId_ordine()%>">Dettagli</a></td></tr>
			<%
				}
			%>
			</table>
			<%
		} else {
			%>
				<p style="font-size: 12px">Questo utente non ha ancora effettuato ordini</p>
			<%
		}
	%>
</div>