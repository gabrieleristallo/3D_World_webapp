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
<div>
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
	<h2>Utenti</h2>
	<table border="1" cellpadding="5" cellspacing="0">
	<tr><th>Id</th><th>Nome</th><th>Cognome</th><th>Email</th><th>Indirizzo</th><th>Azione</th></tr>
	<%
		UtenteDaoImpl utenteDao = new UtenteDaoImpl();
		ArrayList<Utente> utenti = utenteDao.getAllUtenti();
		for(Utente utente : utenti) {
	%>
		<tr><td><%=utente.getId() %></td><td><%=utente.getNome() %></td><td><%=utente.getCognome() %></td><td><%=utente.getEmail() %></td><td><%=utente.getIndirizzo() %></td><td><a href="dettagliUtenteAdmin.jsp?id=<%=utente.getId()%>">Dettagli</a></td></tr>
	<%
		}
	%>
	</table>
</div>