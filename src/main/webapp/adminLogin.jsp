<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	padding: 30px;
    display: block;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    background-color: #fff;
    font-family: Arial, sans-serif;
}

form {
    background-color: #fff;
    padding: 20px;
    width: 50%;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}

label {
    display: block;
    margin-bottom: 10px;
}

input[type="text"], input[type="password"] {
    width: 90%;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

input[type="submit"] {
    width: 20%;
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
</style>
</head>
<body>
	<h3>Accedi come amministratore</h3>
	<form method="post" action="AdminLoginServlet">
		<label for="username">Username</label>
		<input type="text" id="username" name="username">
		<label for="password">Password</label>
		<input type="password" id="password" name="password"><br>
		<input type="submit" value="Invio">
	</form>
</body>
</html>