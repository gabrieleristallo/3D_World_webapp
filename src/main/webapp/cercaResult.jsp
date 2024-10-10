<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ page import="java.sql.*" %>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<head>
		<style>
		.container {
		  display: flex;
		  flex-wrap: wrap;
		  justify-content: space-between;
		}
		
		div.container:has(> div.product-box:nth-child(2)) {
			display: flex;
		  	flex-wrap: wrap;
		  	justify-content: flex-start;
		}
		
		.product-box {
		  width: calc(33.33% - 20px); 
		  margin-bottom: 20px; 
		  padding: 20px;
		  border: 1px solid #ccc;
		  box-sizing: border-box;
		  border-radius: 5px;
		  background-color: rgb(239, 239, 239);
		}
		
		.product-box:not(:last-child) {
			  margin-right: 20px;
		}
		
		.product-box:last-child {
			margin-right: 20px;
		}
		
		@media screen and (max-width: 800px) {
		  .product-box {
		    width: calc(50% - 20px); 
		  }
		}
		
		@media screen and (max-width: 500px) {
		  .product-box {
		    width: calc(100% - 20px); /* Larghezza del box, con margine */
		  }
		}
		
		
	</style>
</head>
<div class="first"></div>
<h3>Risultati</h3>
<div class="container">
<%
    String parametro = request.getParameter("cerca");
    ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
    ArrayList<Prodotto> prodotti = prodottoDao.getProdottiByParameter(parametro);
    if(prodotti != null) {
        for(Prodotto prodotto : prodotti) {
            int id = prodotto.getId();
            String nome = prodotto.getNome();
            String descrizione = prodotto.getDescrizione();
            float prezzo = prodotto.getPrezzo();
            ArrayList<ProdottoColore> colori = prodottoDao.getProdottoColoreById(id);
%>
    <div class="product-box">
        <h3><%=nome %></h3>
        <form method="post" action="AddToCartServlet">
        <a href="dettagliProdotto.jsp?id=<%=id %>"><strong>Prodotto <%= id %></strong></a><br>
        <img src="./getPicture?id=<%=id %>" onerror="this.src='./images/nophoto.png'" title="id <%=id %>" style="width: 99%; display: block; margin-bottom: 5px; margin-top: 5px;">
        <%
            for(ProdottoColore pc : colori) {
        %>
            <div style="display: inline-block; margin-right: 10px;">
                <input type="radio" name="colore" value="<%=pc.getHex() %>">
                <input type="color" value="<%=pc.getHex() %>" disabled></input>
            </div>
        <%
            }
        %>
        <p><%=descrizione %></p>
        <p><b>Prezzo: <%=prezzo %></b></p>
        
        <input type="number" value="<%=id %>" name="id" hidden="true">
        <label for="quantity">Quantità:</label>
        <input type="number" id="quantity" name="qta" min="1" max="5" value="1">
        <input type="submit" value="Aggiungi al carrello" class="add-to-cart-btn">
        </form>
    </div>
<%
        }
    } else {
%>
    <p>La tua ricerca non ha prodotto risultati. Riprova!</p>
<%
    }
%>
</div>


<jsp:include page="footer.jsp"></jsp:include>