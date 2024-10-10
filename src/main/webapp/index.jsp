<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="model.DatabaseManager" %>
<%@ page import="codice.Colore" %>
<%@ page import="codice.*" %>
<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<jsp:include page="header.jsp"/>
<head>
	<style>
		.slider {
            width: 100%;
            background-color: #000;
            position: relative;
            overflow: hidden;
            margin: auto;
        }

        .slides, .slides2 {
            width: 300%;
            /*height: 350px;*/
            display: flex;
            animation: scroll 20s infinite;
        }

        .slides > div, .slides2 > div {
            width: 33.33%;
            position: relative;
        }

        .slides > div > span {
            font-family: inherit;
            font-size: 20px;
            position: absolute;
            top: 45%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: #000;
            z-index: 1;
        }

        .slides > div > img, .slides2 > div > img {
            width: 100%;
            /*height: 350px;*/
        }

        @keyframes scroll {
            0% { transform: translateX(0); }
            20% { transform: translateX(0); }
            40% { transform: translateX(-33.33%); }
            60% { transform: translateX(-33.33%); }
            80% { transform: translateX(-66.66%); }
            100% { transform: translateX(-66.66%); }
        }
        
        div.wellcome {
        	font-size: 18px;
        	position: absolute;
        	z-index: 9000;
        	color: white;
        	margin: 10px;
        }
        
        @media screen and (max-width: 650px) {
        	#titolo1 {
        		width: 70%;
        		margin-right: 30px;
        	}
        	
        	#dettaglio {
        		position: absolute;
        		margin-left: 130px;
        	}
        	
        	.slides {
        		display: none;
        	}
        }
        
        @media screen and (min-width: 650px) {
        	.slides2 {
        		display: none;
        	}
        }
        
        #product-container-box {
        	padding-left: 10%;
        	padding-right: 10%;
        }
		
	</style>
	<script>
	
	</script>
</head>

<div class="first"> <!-- Annuncio centrale -->
<%
	String codices = request.getParameter("new");
	String utente = request.getParameter("nome");
	int codice = 0;
	if(codices != null) {
		codice = Integer.parseInt(codices);
	}
	if(codice == 1) {
	%>
		<div class="wellcome">Benvenuto <%=utente != null ? utente : ""%></div>
	<%
	}
%>
	<div class="slider">
		<div class="slides">
	    	<div><span style="color: white;">STAMPE DI QUALITA</span><img src="./images/sfondo1.png" height="350px" width="100%"></div>
	       	<div><span style="color: white;">PRECISIONE DI STAMPA</span><img src="./images/sfondo2.png" height="350px" width="100%"></div>
	        <div><span style="color: white;">BOBINE PROFESSIONALI</span><img src="./images/sfondo3.png" height="350px" width="100%"></div>
	    </div>
	    <div class="slides2">
	    	<div><img src="./images/sfondo1.png" width="100%"></div>
	       	<div><img src="./images/sfondo2.png" width="100%"></div>
	        <div><img src="./images/sfondo3.png" width="100%"></div>
	    </div>
	</div>
</div>
<div>
</div>
<div id="product-container-box"> <!-- Catalogo -->
	<h1 class="cenetered-element">Esplora</h1>
	
	<form method="get" action="OrdinamentoServlet" style="padding-bottom: 10px">
		Ordina per 
		<%
		String o = (String) request.getSession().getAttribute("ordine");
		String v = (String) request.getSession().getAttribute("valore");
		%>
		<select name="ordine">
		    <option value="id_prodotto" <%= (o != null && o.equals("id_prodotto")) ? "selected" : "" %>>id</option>
		    <option value="prezzo" <%= (o != null && o.equals("prezzo")) ? "selected" : "" %>>prezzo</option>
		    <option value="nome" <%= (o != null && o.equals("nome")) ? "selected" : "" %>>nome</option>
		</select>
		<select name="valore">
		    <option value="crescente" <%= (v != null && v.equals("crescente")) ? "selected" : "" %>>crescente</option>
		    <option value="decrescente" <%= (v != null && v.equals("decrescente")) ? "selected" : "" %>>decrescente</option>
		</select>
		<input type="submit" value="Applica">
	</form>
	<%
		ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
		ArrayList<Prodotto> prodotti = null;
		
		if(o != null && v != null) {
			prodotti = prodottoDao.getProdottiOrder(o, v);
		} else {
			prodotti = prodottoDao.getAllProdotti();
		}
		
		for(Prodotto p : prodotti) {
			if(p.getVisibile() == 0) {
				continue;
			}
			int id = p.getId();
			String nome = p.getNome();
			String descrizione = p.getDescrizione();
			float prezzo = p.getPrezzo();
			ArrayList<ProdottoColore> colori = null;
			colori = prodottoDao.getProdottoColoreById(id);
			
	%>
			<div class="product-details" style="display: flex;">
				<div style="width: 40%">
					<h2 id="titolo1"><%= nome %></h2>
			    	<img src="./getPicture?id=<%=id %>" onerror="this.src='./images/nophoto.png'" title="id <%=id %>" style="width:100px">
				</div>
			    

			    
			    <div class="description" style="margin-top: 50px;" id="dettaglio">
			        <p>
			            <a href="dettagliProdotto.jsp?id=<%=id %>"><strong>Prodotto <%= id %></strong></a><br>
			            
	<%
							for(ProdottoColore pc : colori) {
	%>
								<input type="color" value="<%=pc.getHex() %>" disabled  style="margin-right: 10px;">
	<%
							}
	%>

			        </p>
			        <div class="info" style="padding-top: 10px;">
			        <p>
			            Prezzo: <%= String.format("%.2f", prezzo) %> €
			        </p>
			    </div>
			    </div>
			    
			</div>
	<%
		}
	%>
</div>
<jsp:include page="footer.jsp"/>