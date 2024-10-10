<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="model.DatabaseManager" %>
<%@ page import="codice.Colore" %>
<%@ page import="model.*" %>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp"></jsp:include>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Prodotto</title>
    <link rel="stylesheet" href="dettaglio.css">
</head>
	<%
		String ids = (String) request.getParameter("id");
		int id = Integer.parseInt(ids);
		
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		
		Prodotto p = prodottoDao.getProdottoByIdColor(id);
		
		if(p == null) {
			p = prodottoDao.getProdottoById(id);
		}
		
		ArrayList<ProdottoColore> colori = null;
		colori = prodottoDao.getProdottoColoreById(id);
		
	
	%>
    <div class="product-details">
        <h1 class="product-name"><%=p.getNome() %></h1>
        <img src="./getPicture?id=<%=p.getId() %>" onerror="this.src='./images/nophoto.png'" title="id <%=p.getId() %>" style="width:150px">
        <p class="product-description"><%=p.getDescrizione() %></p>
        <p class="product-description">1.00 Kg</p>
        <p class="product-price">Prezzo: <strong><%=String.format("%.2f", p.getPrezzo())%> €</strong></p>
        <div class="quantity-section">
        <form method="post" action="AddToCartServlet">
        	<input type="number" value="<%=id %>" name="id" hidden="true">
        		<%
		for(ProdottoColore pc : colori) {
		%>
				<input type="radio" name="colore" value="<%=pc.getHex() %>" required><input type="color" value="<%=pc.getHex() %>" disabled  style="margin-right: 10px;"></input>
		<%
			}
		%>
        	<label for="quantity">Quantità:</label>
        	<input type="number" id="quantity" name="qta" min="1" max="5" value="1">
            <input type="submit" value="Aggiungi al carrello" class="add-to-cart-btn">
        </form>
            
        </div>
    </div>
	
<jsp:include page="footer.jsp"></jsp:include>