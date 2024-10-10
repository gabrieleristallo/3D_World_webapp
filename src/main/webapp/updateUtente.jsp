<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="codice.*" %>
<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<jsp:include page="header.jsp"/>
<head>
	<style>
		#submit-aggiorna {
			height: 30px;
			padding: 5px;
			width: 100%;
			margin: 10px;
		}
		
		#submit-aggiorna:hover {
			background-color: #DDDDDD;
		}
		
		#form-aggiorna {
			padding-right: 20px;
		}
		
		#torna-button {
			float: right;
			text-decoration: none;
			color: #DD3333;
		}
		
		#torna-button:hover {
			text-decoration: none;
			color: #AA1111;
		}
		
				#indirizzo-content {
			display: flex;
			align: center;
		}
		
		#indirizzo {
			width: 100%;
		}
		
		#classificazione {
			margin: 0px 10px 10px 0px;
			width: 30%;
		}
		
		#civico {
			height: 25px;
			margin-righ: 0px;
			width: 20%;
		}
		
		.nascosto {
			display: none;
		}
		
			div#alert {
			border: solid 1px;
			border-color: rgb(241, 179, 187);
			background-color: rgb(246, 204, 209);
			color: rgb(69, 14, 22);
			border-radius: 10px;
			width: 100%;
			padding: 5px;
			self-align: center;
			margin-bottom: 10px;
		}
		
		input.error {
			border: 1px solid red;
		}
		
	</style>
	<script>
		function password() {
			var pw_container = document.getElementById("pw-container");
			var button = document.getElementById("pw-button");
			pw_container.classList.remove("nascosto");
			button.classList.add("nascosto");
		}
		
		function hidepassword() {
			var pw_container = document.getElementById("pw-container");
			var button = document.getElementById("pw-button");
			pw_container.classList.add("nascosto");
			button.classList.remove("nascosto");
		}
	</script>
	<script src="javascript/script2.js"></script>
</head>
<div class="form" style="margin-top: 60px">
	<h3>Modifica qui i campi che vuoi modificare</h3>
	<div class="alert, nascosto" id="alert">
		<ul>
		</ul>
	</div>
	<%
		Utente u = (Utente) request.getSession().getAttribute("modifica");
		String indirizzo = u.getIndirizzo();
		String[] indirizzos = indirizzo.split(" ");

		String classificazione = indirizzos[0];
		String via = "";
		for(int i = 1; i < indirizzos.length-1; i++) {
			via += indirizzos[i] + " ";
		}
		int civico = Integer.parseInt(indirizzos[indirizzos.length-1]);
		
	%>
	<form method="post" action="AggiornaUtenteServlet"  class="1" id="form-aggiorna" onsubmit="event.preventDefault(); validateUpdate(this)">
		<input type="text" name="id" value="<%=u.getId()%>" hidden="true">
		<label for="nome">Nome</label>
		<input type="text" id="nome" name="nome" value="<%=u.getNome()%>">
		<label for="cognome">Cognome</label>
		<input type="text" id="cognome" name="cognome" value="<%=u.getCognome()%>">
		<label for="indirizzo">Indirizzo</label>
		<div id="indirizzo-content">
			<select name="classificazione" id="classificazione">
				<option value="Via" <%=classificazione.equals("Via") ? "selected" : "" %>>Via</option>
				<option value="Piazza" <%=classificazione.equals("Piazza") ? "selected" : "" %>>Piazza</option>
				<option value="Viale" <%=classificazione.equals("Viale") ? "selected" : "" %>>Viale</option>
			</select>
			<input type="text" id="indirizzo" name="indirizzo" value="<%=via%>">
			<input type="number" name="civico" id="civico" value="<%=civico%>">
		</div>
		<label for="email">E-Mail</label> 
		<input type="email" id="email" name="email" value="<%=u.getEmail()%>">
		<button type="button" onclick="password()" id="pw-button">Modifica password</button>
		<div id="pw-container" class="nascosto">
			<div style="display: flex;"><label for="pw" id="pw-label">Password</label> <button style="margin-left: 10px; margin-bottom: 5px;" type="button" onclick="hidepassword()">x</button></div>
			<input type="password" id="vpw" name="vpw" placeholder="Password precedente">
			<input type="password" id="pw" name="pw" placeholder="Nuova password">
			<input type="password" id="pw1" name="pw1" placeholder="Ripeti la password">
		</div>
		<input type="submit" class="formsubmit" id="submit-aggiorna">
	</form>
	<a href="profilo.jsp" id="torna-button">Torna indietro</a>
</div>