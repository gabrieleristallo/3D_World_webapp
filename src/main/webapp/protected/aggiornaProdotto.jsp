<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="codice.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<head>
	<style>
		table, h2, h4 {
			margin-left: 20px;
			margin-bottom: 5px;
		} table {
			width: 90%;
			margin-bottom: 2px;
		}
		.note {
			margin-left: 20px;
	        font-size: 7px;
	        padding-top: 10px;
	        margin-bottom: 100px;
	        padding-bottom: 100px;
	        text-align: center;
	    }
	    
	   /* div.popup-content {
		    position: absolute;
		    display: flex;
		    top: 80px;
		    left: 90px;
		    color: #008000;
		    z-index: 99999;
		}*/
		
		#popup1 {
			position: absolute;
			top: 80px;
			left: 50%;
			font-size: 10px;
			color: rgb(10,54,34);
			background-color: rgb(209,231,221);
			padding: 3px;
			border: 1px solid rgb(163,207,187); 
			border-radius: 4px;
		}
		
		
		.appari {
			
		}
		
		.scompari {
			visibility: hidden;
		}
		
	</style>

</head>
<jsp:include page="headerAdmin.jsp"></jsp:include>
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
<%
	String ids = request.getParameter("id");
	String as = request.getParameter("aggiornato");
	
	int id = Integer.parseInt(ids);
	
	ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
	
	Prodotto prodotto = prodottoDao.getProdottoById(id);
	ArrayList<ProdottoColore> lista = prodottoDao.getProdottoColoreById(id);
%>
<div>
<h2>Prodotto <%=id %></h2>
	<div class="popup-content, scompari" id="popup1">
		Aggiornato con successo
	</div>
	<%
	if(as != null) {
		%>
				<script>
					    document.getElementById("popup1").classList.remove("scompari");
					    document.getElementById("popup1").classList.add("compari");
				
					    setTimeout(function() {
					    	document.getElementById("popup1").classList.remove("compari");
					    	document.getElementById("popup1").classList.add("scompari");
						    
					    }, 5000);
				</script>
		<%
		}
	%>
	<table border="1" cellpadding="4" cellspacing="0" style="font-size: 11px;">
		<tr><th>Id</th><th>Nome</th><th>Descrizione</th><th>Materiale</th><th>Prezzo</th><th>Visibilità</th><th></th></tr>
		<form action="../AggiornaProdottoServlet?id=<%=id %>" method="post">
			<tr>
				<td>
					<input type="number" name="id_prodotto" value="<%=id %>" disabled>
				</td>
				<td>
					<input type="text" name="nome" value="<%=prodotto.getNome() %>" required>
				</td>
				<td>
					<textarea name="descrizione" required><%= prodotto.getDescrizione() %></textarea>
				</td>
				<td>
					<select name="materiale">
					    <option value="PLA" <%= prodotto.getMateriale().equals("PLA") ? "selected" : "" %>>PLA</option>
					    <option value="ABS" <%= prodotto.getMateriale().equals("ABS") ? "selected" : "" %>>ABS</option>
					    <option value="TPU" <%= prodotto.getMateriale().equals("TPU") ? "selected" : "" %>>TPU</option>
					    <option value="PETG" <%= prodotto.getMateriale().equals("PETG") ? "selected" : "" %>>PETG</option>
					    <option value="NYLON" <%= prodotto.getMateriale().equals("NYLON") ? "selected" : "" %>>NYLON</option>
					</select>
				</td>
				<td>
					<input type="number" step="any" name="prezzo" value="<%=prodotto.getPrezzo()%>" required>
				</td>
				<td>
					<select name="visibilita">
						<option value="0" <%=prodotto.getVisibile()==0 ? "selected" : "" %>>Nascosto</option>
						<option value="1" <%=prodotto.getVisibile()==1 ? "selected" : "" %>>Visibile</option>
					</select>
				</td>
				<td><input type="submit" value="Modifica"></td>
			</tr>
		</form>
	</table>
	<h4>Colori disponibli</h4>
	<table border="1" cellpadding="4" cellspacing="0" style="font-size: 11px;">
		<tr><th>Id colore</th><th>Id prodotto</th><th>Colore</th><th>Nome Colore</th><th>Quantità</th><th>Azione</th><th>Elimina</th></tr>
	<%
		for(ProdottoColore pc : lista) {
	%>
			<tr>
			<form action="../AggiornaProdottoColoreServlet" method="post">
				
					<td><%=pc.getId_prodotto_colore() %></td>
					<td><%=pc.getId_prodotto() %></td>
					<td><input type="color" value="<%=pc.getHex() %>" disabled></td>
					<td><%=prodottoDao.getColoreByIdProdottoColore(pc.getId_prodotto_colore()) %></td>
					<td>
						<input type="number" name="id_prodotto_colore" value="<%=pc.getId_prodotto_colore() %>" hidden="true">
						<input type="number" name="id_prodotto" value="<%=pc.getId_prodotto() %>" hidden="true">
						<input type="number" name="qta" value="<%=pc.getQta() %>">
					</td>
					<td>
						<input type="submit" value="Aggiorna">
					</td>
					
			</form>
			<td>
						<form action="../DeleteProdottoColoreServlet" methon="get">
							<input type="number" name="id_prodotto_colore" value="<%=pc.getId_prodotto_colore() %>" hidden="true">
							<input type="number" name="id_prodotto" value="<%=pc.getId_prodotto() %>" hidden="true">
							<input type="submit" value="X">
						</form>
					</td>
				</tr>
	<%
		}
	%>
		<form action="../AggiungiProdottoColoreServlet?id_prodotto=<%=id %>" method="post">
			<tr>
				<td>Nuovo colore: </td>
				<td><input type="number" name="id_prodotto" value="<%=id %>" disabled></td>
				<td><input type="color" name="colore" value="#FFFFFF" required></td>
				<td><input type="text" name="nome_colore" placeholder="Inserisci il nome del colore">*</td>
				<td><input type="number" name="qta" required></td>
				<td><input type="submit" value="Inserisci"></td>
				<td></td>
			</tr>
		</form>
	</table>
	<font class="note">*NB: Il nome del colore verrà sovrascritto con il nome del colore presente nel databse se il colore selezionato è già presente</font>
</div>