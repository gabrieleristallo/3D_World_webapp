<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .container {
        margin-top: 60px;
            display: grid;
            grid-template-columns: repeat(2, auto);
            justify-content: center;
            align-items: center;
            gap: 5px; /* Ridotta distanza verticale */
            height: 50vh;
        }

        .box {
            width: 150px;
            height: 120px;
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }

        .box:hover {
            background-color: #e0e0e0;
        }

        .box h2 {
            font-size: 16px;
            margin-bottom: 5px;
        }

        .box p {
            font-size: 12px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
    <div class="container">
        <div class="box" onclick="openOrders()">
            <h2>Visualizza ordini</h2>
            <p>Visualizza tutti gli ordini effettuati dagli utenti</p>
        </div>
        <div class="box" onclick="addProduct()">
            <h2>Gestisci prodotti</h2>
            <p>Modifica i prodotti esistenti o aggiungine di nuovi</p>
        </div>
        <div class="box" onclick="viewUsers()">
            <h2>Visualizza utenti</h2>
            <p>Visualizza tutti gli utenti registrati al sito</p>
        </div>
        <div class="box" onclick="manageAdminPasswords()">
            <h2>Gestisci password amministratore</h2>
            <p>Gestisci tutte le password per accedere come amministratore</p>
        </div>
    </div>

    <!-- JavaScript functions to handle box clicks -->
    <script>
        function openOrders() {
            // Implement your logic here
            window.location.href = "ordiniAdmin.jsp"
        }

        function addProduct() {
            // Implement your logic here
        	window.location.href = "prodottiAdmin.jsp"
        }

        function viewUsers() {
            // Implement your logic here
        	window.location.href = "utentiAdmin.jsp"
        }

        function manageAdminPasswords() {
            // Implement your logic here
        	window.location.href = "passwordAdmin.jsp"
        }
    </script>


