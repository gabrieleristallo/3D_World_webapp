<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="first">
	<%
		String p = (String) request.getParameter("problema");
		if(p != null && p.equals("true")) {
	%>
		<p>Si è verificato un problema</p>
		Continua lo <a href="index.jsp">shopping</a>!
	<%
		} else {
	%>
		<p>Prodotto aggiunto al carrello!</p>
		Continua lo <a href="index.jsp">shopping</a>!
	<%
		}
	%>
</div>
<jsp:include page="footer.jsp"></jsp:include>