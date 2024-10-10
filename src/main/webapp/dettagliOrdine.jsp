<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<style>
	div.first {
		padding-left: 10%;
        	padding-right: 10%;
	}
	
	div.product-details {
		display: flex;
	}
</style>
<div class="first">
	<%
		String ids = request.getParameter("id");
		int id_ordine = Integer.parseInt(ids);
		String data = request.getParameter("data");
	%>
	<h1 class="cenetered-element">Dettaglio Ordine <%=id_ordine %></h1>
	<p>Ordine effettuato <%=FormatoData.is1_8_11(data) ? "l'" : "il"  %> 
		<strong><%=FormatoData.formattaDataString(data) %></strong><br>
		<p style="font-size: 15px;">Contenente</p>
	</p>
	<%
		OrdineDaoImpl ordineDao = new OrdineDaoImpl();
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		ArrayList<DettaglioOrdine> dettagli = ordineDao.getDettaglioOrdineById(id_ordine);
		float totale = ordineDao.getOrdineById(id_ordine).getTotale();
		
		for(DettaglioOrdine dettaglio : dettagli) {
			Prodotto p = prodottoDao.getProdottoByIdColor(dettaglio.getId_prodotto_colore());
			String colore = prodottoDao.getHexByIdPrdottoColore(dettaglio.getId_prodotto_colore());
			
	%>
			
			<div class="product-details">
			    <div>
			    	<h2 style="width: 200px"><%= p.getNome() %></h2>
					<img src="./getPicture?id=<%=p.getId() %>" onerror="this.src='./images/nophoto.png'" title="id <%=p.getId() %>" style="width:100px">
			    </div>
			    <div style="padding-top: 5%; padding-right: 10px;">
			    	<div class="description">
				        <p>
				            <a href="dettagliProdotto.jsp?id=<%=p.getId() %>"><strong>Prodotto <%= p.getId() %></strong></a><br>
									<input type="color" value="<%=colore %>" disabled  style="margin-right: 10px;">
				        </p>
				    </div>
				    <div class="info">
				    	<p>
				            Quantità: <%= dettaglio.getQta() %>
				        </p>
				        <p>
				            <%= String.format("%.2f", dettaglio.getPrezzo()) %> €
				        </p>
				    </div>
			    </div>
			    
			</div>
	<%
			
		}
	%>
	<strong><p style="font-size: 15px;">Totale: <%=String.format("%.2f", totale) %> €</p></strong>
</div>


<jsp:include page="footer.jsp"></jsp:include>