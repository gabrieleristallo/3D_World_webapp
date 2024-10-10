<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<head>
<style>
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
		
		#form-registration {
			padding-right: 20px;
		}
		
		#togglePassword1, #togglePassword2 {
			margin-top: 7px;
			height: 20px;
			width: 21%;
		}
</style>

<script src="javascript/script2.js"></script>
<script src="javascript/ajax3.js"></script>
<script>
	function showPassword(pw) {
		if(pw == 1) {
			var passwordField = document.getElementById("pw");
			var textContent = document.getElementById("togglePassword1");
		} else {
			var passwordField = document.getElementById("pw1");
			var textContent = document.getElementById("togglePassword2");
		}
		
		
	    const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
	    passwordField.setAttribute('type', type);
	    textContent.innerHTML = type === 'password' ? 'Mostra' : 'Nascondi';
	}
</script>
</head>
<div class="form" style="margin-top: 60px">
	<h3>Registrati qui</h3>
	<div class="alert, nascosto" id="alert">
		<ul>
		</ul>
	</div>
	<form method="post" action="RegisterServlet" onsubmit="event.preventDefault(); validateRegister(this)" class="1" id="form-registration">
		<label for="nome">Nome</label>
		<input type="text" id="nome" name="nome" onkeyup="checkNome(this)">
		<label for="cognome">Cognome</label>
		<input type="text" id="cognome" name="cognome" onkeyup="checkNome(this)">
		<label for="indirizzo">Indirizzo</label>
		<div id="indirizzo-content">
			<select name="classificazione" id="classificazione">
				<option value="Via">Via</option>
				<option value="Piazza">Piazza</option>
				<option value="Viale">Viale</option>
			</select>
			<input type="text" id="indirizzo" name="indirizzo">
			<input type="number" name="civico" id="civico">
		</div>
		<label for="email">E-Mail</label> 
		<input type="email" id="email" name="email" onkeyup="checkEmail(this)" autocomplete="off">
		<label for="pw">Password</label>
		<div style="display: flex;">
			<input type="password" id="pw" name="pw"> <button type="button" id="togglePassword1" class="toggle-password1" onclick="showPassword(1)">Mostra</button>
		</div>
		<div style="display: flex;">
			<input type="password" id="pw1" name="pw1" placeholder="Ripeti la password"> <button type="button" id="togglePassword2" class="toggle-password" onclick="showPassword(2)">Mostra</button>
		</div>
		
		<input type="submit">
	</form>
</div>