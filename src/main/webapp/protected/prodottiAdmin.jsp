<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<jsp:include page="headerAdmin.jsp"></jsp:include>
<head>
	<style>
		table, h2 {
			margin-left: 20px;
		} table {
			width: 90%;
			margin-bottom: 100px;
		}
	</style>
</head>
<div >
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
	<h2>Prodotti</h2>
	<table border="1" cellpadding="4" cellspacing="0" style="font-size: 11px;">
		<tr><th>Id</th><th>Nome</th><th>Descrizione</th><th>Materiale</th><th>Prezzo</th><th>Immagine</th><th>Aggiungi immagine</th><th>Visibile</th><th>Modifica</th></tr>
	<%
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		ArrayList<Prodotto> prodotti = prodottoDao.getAllProdotti();
		for(Prodotto prodotto : prodotti) {
			int id = prodotto.getId();
			String nome = prodotto.getNome();
			String descrizione = prodotto.getDescrizione();
			String materiale = prodotto.getMateriale();
			float prezzo = prodotto.getPrezzo();
	%>
		<tr><td><%=id %></td><td><%=nome %></td><td><%=descrizione %></td><td><%=materiale %></td><td><%=String.format("%.2f€", prezzo) %></td><td><img src="../getPicture?id=<%=id %>" onerror="this.src='../images/nophoto.png'" title="id <%=id %>" style="width:50px"></td>
			<td>
				<form action="../UploadPhotoServlet?id=<%=id %>" enctype="multipart/form-data" method="post">
					<input type="text" name="id_prodotto" value="<%=id %>" hidden="true">
					<input class="file" type="file" name="talkPhoto" value="" maxlength="255">	
					<input type="submit" value="Aggiungi">
				</form>
			</td>
			<td><%=prodotto.getVisibile() == 0 ? "No" : "Si" %></td>
			<td>
				<form action="aggiornaProdotto.jsp?id=<%=id %>" method="post">
					<input type="text" name="id_prodotto" value="<%=id %>" hidden="true">	
					<input type="submit" value="Modifica">
				</form>
			</td>
		</tr>
	<%
		}
	%>
		<tr>
			<form action="../AggiungiProdottoServlet" enctype="multipart/form-data" method="post">
				<td></td>
				<td><input type="text" name="nome" required placeholder="Inserisci il nome"></td>
				<td><input type="text" name="descrizione" required placeholder="Inserisci la descrizione"></td>
				<td>
					<select name="materiale">
						<option value="PLA">PLA</option>
						<option value="ABS">ABS</option>
						<option value="TPU">TPU</option>
						<option value="PETG">PETG</option>
						<option value="NYLON">NYLON</option>
					</select>
				</td>
				<td><input name="prezzo" type="number" step="any" required style="width: 90%;"></td>
				<td></td>
				<td><input class="file" type="file" name="talkPhoto" value="" maxlength="255"></td>
				<td><input type="submit" value="Aggiungi"></td>
			</form>
		</tr>
	</table>
</div>