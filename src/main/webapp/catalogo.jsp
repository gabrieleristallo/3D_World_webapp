<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<jsp:include page="header.jsp"></jsp:include>

<head>
	<style>
		div.first {
			flex-grow: 1;
			padding-bottom: 30px;
		}
		
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
		
		body {
			display: flex;
		}
		
		.filter-form {
			display: flex;
			flex-wrap: wrap;
			justify-content: space-between;
			margin-bottom: 20px;
			padding: 10px;
			border: 1px solid #ccc;
			border-radius: 5px;
			background-color: #f9f9f9;
		}
		
		.filter-form select {
			margin-right: 10px;
			padding: 5px;
			border: 1px solid #ccc;
			border-radius: 5px;
			background-color: #fff;
		}
		
		.filter-form input[type="range"] {
			margin-right: 10px;
		}
		
		.filter-form input[type="submit"] {
			padding: 8px 20px;
			border: none;
			border-radius: 5px;
			background-color: #007bff;
			color: #fff;
			cursor: pointer;
			transition: background-color 0.3s;
		}
		
		.filter-form input[type="submit"]:hover {
			background-color: #0056b3;
		}
		
		#button-hide {
            margin-top: 5px;
		}
		
		#button-hide:hover {
			border: none;
			border-radius: 3px;
		}

	</style>
</head>

<div class="first">
	<h1 class="cenetered-element">Catalogo</h1>
	<form method="get" action="FiltroServlet" style="padding-bottom: 10px;">
		Filtra: <br>
		<%
		String m = (String) request.getSession().getAttribute("materiale");
		String ps = (String) request.getSession().getAttribute("prezzo");
		float p = 0;
		if(ps != null) {
			p = Float.parseFloat(ps);
		}
		
		%>
		Materiale: 
		<select name="materiale" style="margin-right: 10px;">
			<option value="TUTTI" <%= (m != null && m.equals("TUTTI")) ? "selected" : "" %>>Tutti</option>
		    <option value="PLA" <%= (m != null && m.equals("PLA")) ? "selected" : "" %>>PLA</option>
		    <option value="ABS" <%= (m != null && m.equals("ABS")) ? "selected" : "" %>>ABS</option>
		    <option value="PETG" <%= (m != null && m.equals("PETG")) ? "selected" : "" %>>PETG</option>
		    <option value="TPU" <%= (m != null && m.equals("TPU")) ? "selected" : "" %>>TPU</option>
		    <option value="NYLON" <%= (m != null && m.equals("NYLON")) ? "selected" : "" %>>NYLON</option>
		</select>
		Prezzo: 
		  <input name="prezzo" type="range" min="0" max="150" value="<%=ps != null ? p : 150 %>" class="slider" id="myRange" style="vertical-align: bottom;">
		  <span class="slider-value" id="sliderValue">50</span><span> €</span>
		<input type="submit" value="Applica">
	</form>
	
	
	<div class="container">
		<%
		
			ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
			ArrayList<Prodotto> prodotti = null;
			if(m != null && ps != null && !m.equals("TUTTI")) {
				prodotti = prodottoDao.getProdottiFiltred(m, p);
			} else if(m != null && m.equals("TUTTI") && ps != null){
				prodotti = prodottoDao.getProdottiFiltredByPrezzo(p);
			} else {
				prodotti = prodottoDao.getAllProdotti();
			}
			
			/*ResultSet rs = null;
			if(m != null && ps != null && !m.equals("TUTTI")) {
				rs = DatabaseManager.getProdottiFiltred(m, p);
			} else if(m != null && m.equals("TUTTI") && ps != null){
				rs = DatabaseManager.getProdottiFiltredByPrezzo(p);
			} else {
				rs = DatabaseManager.getProdotti();
			}*/
			
			for(Prodotto prodotto : prodotti) {
				if(prodotto.getVisibile() == 0) {
					continue;
				}
				int id = prodotto.getId();
				String nome = prodotto.getNome();
				String descrizione = prodotto.getDescrizione();
				float prezzo = prodotto.getPrezzo();
				ArrayList<ProdottoColore> colori = null;
				colori = prodottoDao.getProdottoColoreById(id);
		%>
			<div class="product-box">
				<h3><%=nome %></h3>
				<img src="./getPicture?id=<%=id %>" onerror="this.src='./images/nophoto.png'"  title="id <%=id %>" style="width:100%">
				<form method="post" action="AddToCartServlet">
				<a href="dettagliProdotto.jsp?id=<%=id %>"><strong>Prodotto <%= id %></strong></a><br>
				<%
					for(ProdottoColore pc : colori) {
				%>
					<div style="display: inline-block; margin-right: 10px;">
						<input type="radio" name="colore" value="<%=pc.getHex() %>" required>
						<input type="color" value="<%=pc.getHex() %>" disabled></input>
					</div>
				<%
					}
				%>
				<p><%=descrizione %></p>
				<p><b>Prezzo: <%=String.format("%.2f", prezzo) %> €</b></p>
				
				<input type="number" value="<%=id %>" name="id" hidden="true">
				<label for="quantity">Quantità:</label>
				<input type="number" id="quantity" name="qta" min="1" max="5" value="1">
				<input type="submit" value="Aggiungi al carrello" class="add-to-cart-btn">
				</form>
			</div>

		<%
				
			}
		%>
    </div>

    
    
	<script>
		var slider = document.getElementById("myRange");
		var output = document.getElementById("sliderValue");
		output.innerHTML = slider.value;
		
		slider.oninput = function() {
		  output.innerHTML = this.value;
		}
		
		function hide() {
			console.log("hide clicked");
			var right = document.getElementById("right");
			right.hidden = true;
		}
	</script>
</div>
		<%
    		ProdottoCarrello just = (ProdottoCarrello) request.getSession().getAttribute("justAdded");
			if(just != null || request.getParameter("just") != null) {
    	%>
			    <div class="right" id="right" style="margin-top: 40px;" <%=just == null || request.getParameter("just") == null ? "hidden='true'" : "" %>>
			    	<div style="align-content: center; border: 1px; border-color: black;">
			    		Aggiunto:
			    		<img src="./getPicture?id=<%=prodottoDao.getIdProdottoByIdColore(just.getId())  %>" onerror="this.src='./images/nophoto.png'"  title="id <%=just.getId() %>" style="width:80px">
			    		<input type="color" value="<%=just.getColore() %>" disabled></input> x <%=just.getQta() %>
			    		<%=just.getNome() %>
			    	</div>
			    	 <button id="button-hide" onclick="hide()">Nascondi</button>
			    	
			    </div>
    	<%
		   }
   		%>



<jsp:include page="footer.jsp"></jsp:include>