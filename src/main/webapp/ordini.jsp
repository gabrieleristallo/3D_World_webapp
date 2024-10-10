<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<head>
	<style>
		#elemento {
			width: 60%;
		}
	</style>
</head>

<div style="margin-left: 20%">
<h1 class="cenetered-element" style="margin-top: 60px">Ordini</h1>
	<%
		int id_utente = (Integer) request.getSession().getAttribute("id_utente");
		
		OrdineDaoImpl ordineDao = new OrdineDaoImpl();
		ArrayList<Ordine> ordini = ordineDao.getOrdineByUtente(id_utente);
		if(ordini.size() == 0) {
	%>
		<p>Non hai ancora effettuato ordini</p>
	<%
		}
		for(Ordine o : ordini) {
	%>
		<div class="product-details" id="elemento">
			    <h2>Ordine <%=o.getId_ordine() %> - <%=FormatoData.formattaData(o.getData()) %></h2>

			    
			    <div class="description">
			        <p>
			            <a href="dettagliOrdine.jsp?id=<%=o.getId_ordine() %>&data=<%=FormatoData.formattaData(o.getData())%>"><strong>Dettaglio</strong></a><br>
			            
	

			        </p>
			    </div>
			    <div class="info" style="padding-bottom: 5px">
			        <p style="float: right; margin-bottom: 5px;">
			            Totale: <%=String.format("%.2f", o.getTotale()) %> €
			        </p>
			    </div>
			</div>
	<%
		}
		
	%>
</div>

<jsp:include page="footer.jsp"></jsp:include>