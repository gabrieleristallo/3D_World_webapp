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
	<script src="javascript/script2.js"></script>
	<script>
	function formatExpiryDate(input) {
        const value = input.value.replace(/\D/g, '');
        const formattedValue = value.substring(0, 2) + (value.length > 2 ? '/' : '') + value.substring(2, 4);
        input.value = formattedValue;
    }
	
	function formatCardNumber(input) {
		const value = input.value.replace(/\D/g, ''); // Rimuove tutti i caratteri non numerici
		const formattedValue = value.replace(/(.{4})/g, '$1 ').trim(); // Aggiunge uno spazio ogni 4 caratteri
		input.value = formattedValue;
	}
	</script>
</head>
<div class="form" style="margin-top: 60px">
	<h3>Inserisci i dati della carta di credito</h3>
	<div class="alert, nascosto" id="alert">
		<ul>
		</ul>
	</div>
	<%
		UtenteDaoImpl utenteDao = new UtenteDaoImpl();
		int id_utente;
		Utente u;
		if(request.getParameter("provenienza") != null && request.getParameter("provenienza").equals("1")) {
			id_utente = Integer.parseInt(request.getParameter("id_utente"));
			u = utenteDao.getUtenteById(id_utente);
		} else {
			u = (Utente) request.getSession().getAttribute("modifica");
			System.out.println("utente da sessione in jsp" + u.getId());
		}
		
	%>
	<form method="post" action="AggiungiMetodoDiPagamentoServlet?id=<%=u.getId() %>&provenienza=<%=request.getParameter("provenienza") %>" class="1" id="form-aggiorna" onsubmit="event.preventDefault(); validatePagamento(this)">
        <div class="form-control">
            <label for="numeroCarta">Numero di Carta:</label>
            <input type="text" id="numeroCarta" name="numeroCarta" maxlength="19" required onkeyup="formatCardNumber(this)">
        </div>
        <div class="form-control">
            <label for="scadenza">Data di Scadenza (MM/YY):</label>
            <input type="text" id="scadenza" name="scadenza" maxlength="5" required onkeyup="formatExpiryDate(this)">
        </div>
        <div class="form-control">
            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" name="cvv" maxlength="3" required>
        </div>
        <input type="text" name="id" value="<%=u.getId()%>" hidden="true">
        <input type="submit" class="formsubmit" id="submit-aggiorna" value="Aggiungi Metodo di Pagamento">
    </form>
	<a href="profilo.jsp" id="torna-button">Torna indietro</a>
</div>