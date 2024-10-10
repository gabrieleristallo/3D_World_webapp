<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amministrazione 3D World</title>
    <style>
        body {
            font-family: Helvetica, Arial, sans-serif;
            margin: 0; /* Rimuovi margini predefiniti */
            padding: 0; /* Rimuovi spaziature predefinite */
        }

        header {
            background-color: #333;
            padding: 10px 0;
        }

        nav {
            display: flex; /* Allinea gli elementi orizzontalmente */
            justify-content: space-between; /* Spazio tra i due elementi */
            align-items: center; /* Centra verticalmente */
            padding: 0 20px; /* Aggiungi spazio ai lati */
            color: white; /* Colore del testo */
        }

        .navbar-title {
            font-size: 18px;
            font-weight: bold;
        }

        .logout-link {
            text-decoration: none;
            color: black;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
            transition: color 0.2s ease; /* Transizione colore al passaggio del mouse */
        }

        .logout-link:hover {
            color: #ddd; /* Colore più chiaro al passaggio del mouse */
        }
        
        #indietro {
        	position: absolute; 
        	margin-top: 10px; 
        	margin-left: 10px; 
        	text-decoration: none; 
        	font-size: 10px;
        }
    </style>
</head>
<body>
    <header>
        <nav>
            <div class="navbar-title">Amministrazione</div>
            <form method="post" action="../index.jsp">
                <input type="submit" value="Visualizzazione utente" class="logout-link">
            </form>
            <form method="post" action="../LogoutServlet">
                <input type="submit" value="Esci" class="logout-link">
            </form>
        </nav>
    </header>
    <a href="indexAdmin.jsp" id="indietro">Indietro</a>

