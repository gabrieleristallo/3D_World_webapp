<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<script>
function downloadFile(id) {
    var win = window.open('DownloadServlet?id_ordine=' + id, '_blank');
    win.focus();
}
</script>
<style>
    div.first {
        padding-top: 40px;
        text-align: center;
    }

    #testo {
        font-size: 15px;
    }

    .button {
        padding: 10px 20px;
        margin-top: 20px;
        margin-bottom: 15px;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        cursor: pointer;
        font-size: 14px;
        background-color: #DDDDDD;
        color: black;
        transition: background-color 0.3s;
    }

    .button:hover {
        background-color: #CCCCCC;
    }

    .button:active {
        background-color: #BBBBBB;
    }
    
    
</style>
<div class="first">
    <%
        String id_ordines = request.getParameter("id_ordine");
        int id_ordine = 0;
        if(id_ordines != null) {
            id_ordine = Integer.parseInt(id_ordines);
        }
    %>
    <h1>Grazie per il tuo acquisto!</h1>
    <p id="testo">Il tuo acquisto è stato completato con successo.</p>
    <button class="button" onclick="downloadFile(<%=id_ordine%>)">Scarica la fattura</button><br>
    <a style="font-size: 10px" class="button" href="index.jsp">Torna alla home</a>
</div>

