<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="codice.*"%>
<%@ page import="dao.*"%>
<%@ page import="model.*"%>
<jsp:include page="header.jsp"></jsp:include>

<style>
    .error-page {
       	
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 50vh;
        text-align: center;
        background-color: #EEEEEE;
        color: #721c24;
        border: 1px solid #f5c6cb;
        border-radius: 10px;
        padding: 20px;
        margin: 20px;
        margin-top: 100px;
    }
    .error-page h2 {
        font-size: 2.5em;
        margin-bottom: 20px;
    }
    .error-page p {
        font-size: 1.2em;
        margin: 10px 0;
    }
    .error-page a {
        color: #721c24;
        text-decoration: underline;
        font-weight: bold;
    }
    .error-page a:hover {
        text-decoration: none;
    }
    .error-icon {
        font-size: 3em;
        margin-bottom: 20px;
    }
</style>

<div class="error-page">
    <div class="error-icon">⚠️</div>
    <h2>Errore Quantità</h2>
    <p>Sembra che alcuni prodotti nel carrello non siano disponibili nelle quantità desiderate.<br>Non è stato possibilie concludere l'acquisto.</p>
    <%
        String ids = (String) request.getParameter("id");
        int id = Integer.parseInt(ids);
        ProdottoDaoImpl prodottoDao = new ProdottoDaoImpl();
        Prodotto p = prodottoDao.getProdottoByIdColor(id);
    %>
    <p>Prodotto <%=p.getId() %> - <%=p.getNome() %></p>
    <p>Continua lo <a href="catalogo.jsp">shopping</a>!</p>
</div>

<jsp:include page="footer.jsp"></jsp:include>
