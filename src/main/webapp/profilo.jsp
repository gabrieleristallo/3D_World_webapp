<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<jsp:include page="header.jsp"></jsp:include>
<head>
	<style>


.first {
    width: 80%;
    margin: 0 auto;
    padding-top: 50px;
    padding: 20px;
    background-color: #fff;
    //box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
    padding-top: 50px;
}

.first h1 {
    color: #333;
    text-align: left;
}

.first div {
    margin-bottom: 20px;
}

.first p {
    color: #666;
    line-height: 1.6;
}

form {
    text-align: left;
}

	</style>
</head>

<div class="first">
		<h1>Profilo</h1>
	<%
		UtenteDaoImpl utenteDao = new UtenteDaoImpl();
		Integer ids = (Integer) request.getSession().getAttribute("id_utente");
		System.out.println(ids);
		int id = 0;
		if(ids != null) {
			id = ids;
		}
		Utente u;
		if(id != 0) {
			u = utenteDao.getUtenteById(id);
			
			ArrayList<Pagamento> lista = utenteDao.getMetodiPagamentoByUtente(id);
			
			request.getSession().setAttribute("modifica", u);
	%>
		<div>
			<strong><p style="font-size: 14px;"><%=u.getNome() %> <%=u.getCognome() %></p></strong>
			<p><b>Indirizzo:</b> <%=u.getIndirizzo() %></p>
			<p><b>Email:</b> <%=u.getEmail() %></p>
		</div>
		<div>
			I tuoi metodi di pagamento:
	<%
			if(lista != null && lista.size() != 0) {
	%>
				<ul>
	<%
				for(Pagamento p : lista) {
				    String numeroCarta = p.getNumero_carta();
				    // Ottieni solo le ultime 4 cifre del numero della carta
				    String ultimeQuattroCifre = numeroCarta.substring(numeroCarta.length() - 4);
				    // Sostituisci i primi caratteri con asterischi
				    String numeroCartaNascosto = "**** **** **** " + ultimeQuattroCifre;
	%>
				    <li>Carta di credito: <%= numeroCartaNascosto %></li>
	<%
				}
	%>
				</ul>
	<%
			} else {
	%>
				<p>Non hai ancora inserito un metodo di pagamento</p>
	<%			
			}
	%>
			<form method="post" action="aggiungiMetodoDiPagamento.jsp">
				<input type="text" name="id" value="<%=id %>" hidden="true">
				<input type="submit" value="Aggiungi una carta di credito">
			</form>
		</div>
		<form method="post" action="updateUtente.jsp">
			<input type="text" name="id" value="<%=id %>" hidden="true">
			<input type="submit" value="Modifica informazioni">
		</form>
	<%
		}
	%>

</div>

<jsp:include page="footer.jsp"></jsp:include>