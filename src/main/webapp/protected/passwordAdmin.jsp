<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<jsp:include page="headerAdmin.jsp"/>
<head>
	<style>
		table, h2, h4 {
			margin-left: 20px;
			margin-bottom: 5px;
		} table {
			width: 50%;
			margin-bottom: 2px;
		}
	</style>
</head>
<div>
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
	<h2>Credenziali amministratore</h2>
	<table border="1" cellpadding="4" cellspacing="0" style="font-size: 11px;">
		<tr><th>Nome admin</th><th>Password</th><th></th></tr>
		<form action="../AdminRegisterServlet" method="post">
			<tr><td><input type="text" name="nome" value="admin" disabled></td><td><input type="password" name="pw"></td><td><input type="submit" value="salva"></td></tr>
		</form>
	</table>
</div>