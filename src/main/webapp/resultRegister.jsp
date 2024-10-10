<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
	.button {
	    display: inline-block;
	    padding: 10px 20px;
	    font-size: 10px;
	    text-align: center;
	    text-decoration: none;
	    background-color: #DDDDDD;
	    color: black;
	    border-radius: 5px;
	    margin: 5px;
	}
	.button:hover {
		background-color: #BBBBBB;
	}
	.firstdiv {
		margin-top: 60px;
		margin-left: 30px;
	}
</style>
<div class="first, firstdiv">
	<%
		int codice = Integer.parseInt(request.getParameter("codice"));
	%>
	<%
		switch(codice) {
		case 0:
			%>
				<div>
				    <p>Risulta già un utente registrato con questa mail. Prova ad accedere:</p>
				    <a href="login.jsp" class="button">Accedi</a>
				    <a href="index.jsp" class="button">Torna alla home</a>
				</div>	
			<%
			break;
		case 1:
			%>
				<div>
				    <p>La registrazione è avvenuta con successo!</p>
				    <a href="login.jsp" class="button">Accedi</a>
				    <a href="index.jsp" class="button">Vai alla home</a>
				</div>	
			<%
			break;
		case 2:
			%>
				<div>
				    <p>Sembra che sia avvenuto un errore durante la registrazione.</p>
				    <a href="register.jsp" class="button">Riprova</a>
				    <a href="index.jsp" class="button">Torna alla home</a>
				</div>	
			<%
			break;
		case 3:
			%>
				<div>
				    <p>Sembra che tu non sia ancora registrato!</p>
				    <a href="register.jsp" class="button">Puoi farlo qui</a>
				    <a href="index.jsp" class="button">Torna alla home</a>
				</div>	
			<%
			break;
		}
	%>
</div>