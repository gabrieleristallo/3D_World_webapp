<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style>
		.button {
		    display: inline-block;
		    padding: 10px 20px;
		    text-align: center;
		    text-decoration: none;
		    color: black;
		    background-color: #EEEEEE;
		    border-radius: 6px;
		    outline: none;
		    width: 20%;
		}
		
		.button:hover {
		    background-color: #DDDDDD;
		}
		
		input[type="submit"] {
		    width: 20%;
		    height: 30px;
		    text-align: center;
		    padding: 10px;
		    border-radius: 5px;
		    border: none;
		    color: #000;
		    background-color: #eee;
		    cursor: pointer;
		}
		
		input[type="submit"]:hover {
		    background-color: #ddd;
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
		
		.nascosto {
			display: none;
		}
		
		#login-form {
			padding-right: 20px;
		}
		
		#togglePassword1, #togglePassword2 {
			margin-top: 7px;
			height: 20px;
			width: 21%;
		}
		
		@media screen and (max-width: 650px) {
			#button-admin {
				display: none;
			}
		}
		
		
					
	</style>
	<script src="javascript/script2.js"></script>
	<script>
	function showPassword(pw) {
		if(pw == 1) {
			var passwordField = document.getElementById("pw");
		} else {
			var passwordField = document.getElementById("pw1");
		}
		
		var textContent = document.getElementById("togglePassword1");
		
	    const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
	    passwordField.setAttribute('type', type);
	    textContent.innerHTML = type === 'password' ? 'Mostra' : 'Nascondi';
	}
	</script>
</head>
<div class="form" style="margin-top: 60px; width: 90%;">
	<h3>Accedi qui</h3>
	<div class="alert, nascosto" id="alert">
		<%
			String provenienza = request.getParameter("provenienza");
		%>
	</div>
	<form method="post" action="LoginServlet<%=provenienza != null ? "?provenienza=1" : "" %>" onsubmit="event.preventDefault(); validateLogin(this)" class="1" id="login-form">
		<label for="email">E-Mail</label> 
		<input type="email" id="email" name="email" value=<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""  %>>
		<label for="pw">Password</label>
		<div style="display: flex;">
			<input type="password" id="pw" name="pw"> <button type="button" id="togglePassword1" class="toggle-password1" onclick="showPassword(1)">Mostra</button>
		</div>
		<input type="submit">
	</form>
</div>
<div style="text-align: center; margin-top: 20px;">
	<p>oppure</p>
	<a href="register.jsp" class="button">Registrati qui</a>
	<a href="adminLogin.jsp" class="button" id="button-admin">Accedi come amministratore</a>
</div>