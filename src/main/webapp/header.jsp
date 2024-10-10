<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>3D World</title>
    <link rel="icon" href="/progetto10/images/icona.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="stile.css">
    <script src="javascript/ajax3.js"></script>
    <script>
    	function toPage(destinazione) {
    		window.location.href = destinazione;
    	}
    	
    	function hamburger() {
    		var hamburger = document.getElementById("hamburger-content");
    		if(hamburger.classList.contains('nascosto')) {
    			console.log("era nascosto");
    			hamburger.classList.remove('nascosto');
    		} else {
    			hamburger.classList.add('nascosto');
    			console.log("non era nascosto");
    		}
    	}
    	
    	
    </script>
    <style>
        
        @media screen and (min-width: 650px) {
        	#header2 {
        		display: none;
        	}
        	input[type="color"]:disabled {
			    opacity: 1; /* Rimuove l'effetto sbiadito */
			    pointer-events: none; /* Evita che l'utente possa interagire con l'input */
			}
        	header {
	            background-color: #f2f2f2;
	            padding: 0px 5px 0px 5px;
	            display: flex;
	            justify-content: space-between;
	            align-items: center;
	            width: 99%;
	            left: 0;
	            right: 0;
	            z-index: 999999;
	        }
	
	        header h3 {
	            color: black;
	            font-size: 18px;
	        }
	
	        /* Stile per la navbar */
	        header nav ul {
	            list-style-type: none;
	            margin: 0;
	            padding: 0;
	            font-size: 12px;
	        }
	
	        /* Stile per ciascun elemento della navbar */
	        header nav ul li {
	            display: inline;
	            margin-right: 10px;
	        }
	
	        /* Stile per i link nella navbar */
	        header nav ul li a {
	            text-decoration: none;
	            color: #333;
	        }
	
	        /* Stile per il pulsante di ricerca nella navbar */
	        header .search-bar {
	            display: flex;
	        }
	
	        header .search-bar form {
	            display: flex;
	        }
	
	        header .search-bar input[type="text"] {
	            padding: 5px;
	            border: 1px solid #ccc;
	            border-radius: 4px;
	            margin-right: 5px;
	        }
	
	        /* Stile per il pulsante "Cerca" nella navbar */
	        header .search-bar  {
	        	margin-top: 8px;
	            padding: 5px 5px;
	            color: white;
	            border: none;
	            border-radius: 4px;
	            cursor: pointer;
	            font-size: 14px;
	        }
	                /* Stile per il pulsante "Esci/Accedi" */
	        header .login-button {
	            margin-left: 10px;
	        }
	        
	        #logo {
	        	height: 4em;
	        }
	        
	        #logolink, header nav ul li a {
			    /* Stile base del link */
			    text-decoration: none;
			    color: black;
				    position: relative;
			}
			
			#logolink::before, header nav ul li a::before {
			    /* Pseudo-elemento per il bordo inferiore */
			    content: '';
			    position: absolute;
			    left: 0;
			    bottom: 0;
			    width: 0;
			    height: 2px;
			    background-color: grey;
			    transition: width 0.25s ease-in-out; /* Transizione per la larghezza */
			}
			
			#logolink:hover::before, header nav ul li a:hover::before {
			    /* Animazione del bordo quando il mouse è sopra */
			    width: 100%; /* Cresce fino a coprire l'intera larghezza */
			}
			
	       
		    
        }
        
        @media screen and (max-width: 650px) {
	        #header1 {
	        	display: none;
	        }
	        
	        header {
	            background-color: #f2f2f2;
	            padding: 0px 5px 0px 5px;
	            display: flex;
	            justify-content: space-between;
	            align-items: center;
	            width: 99%;
	            left: 0;
	            right: 0;
	        }
	        
	        #logo {
	        	width: 80px;
	        }
	       
	        
	        .hamburger {
			  display: none;
			  flex-direction: column;
			  cursor: pointer;
			}
			
			.hamburger span {
			  height: 3px;
			  width: 25px;
			  background: white;
			  margin: 4px;
			  transition: all 0.3s ease;
			}
			
			div.search-bar {
				display: flex;
				width: 60%;
				maring-left: 0px;
			}
			
			#hamburger {
				width: 30px;
				margin-right: 8px;
				padding-left: 2px;
		    	padding-right: 2px;
			}
	        
	        input[name="cerca"] {
	        	width: 50%;
	        	height: 5px;
	        	margin-top: 10px;
	        }
	        
	        form.cerca {
	        	float: right;
	        }
	        
	        #hamburger-content {
	        	width: 120px;
	        	position: absolute;
	        	z-index: 9999;
	        	color: black;
	        	background-color: white;
	        	right: 0;
	        	top: 40px;
	        	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
			    padding: 0px 0;
			    transition: all 0.3s ease;
	        }
	        
	        #hamburger-content ul {
		      list-style-type: none;
		      margin: 0;
		      padding: 0;
		    }
		    
	        #hamburger-content li {
		      padding: 10px 20px;
		      cursor: pointer;
		      transition: background-color 0.3s ease;
		    }
		
		    #hamburger-content li:hover {
		      background-color: #f0f0f0;
		    }
		
		    #hamburger-content li:not(:last-child) {
		      border-bottom: 1px solid #e0e0e0;
		    }
		    
		    #hamburger-content li:last-child {
		    	margin-bottom: 0;
		    }
		    
		    #hamburger-content li:first-child {
		    	margin-top: 0;
		    }
		    
		    #hamburger:hover {
		    	border-radius: 5px;
		    	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
		    }
		    
		    #hamburger:active {
		    	border-radius: 5px;
		    	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.5);
		    }
		    
		    input[type="color"]:disabled {
			    opacity: 1; /* Rimuove l'effetto sbiadito */
			    pointer-events: none; /* Evita che l'utente possa interagire con l'input */
			}
        }
        
        
        
        input[type="submit"] {
        	font-size: 10px;
        	margin-top: 2px;
        	padding: 2px;
        	height: 20px;
        	self-align: bottom;
        }
        
        input[type="submit"]:hover {
        	background-color: gray;
        }
        
        .nascosto {
        	display: none;
        }

 		#suggerimenti-content, #suggerimenti-content1 {
	        	width: auto;
	        	position: absolute;
	        	z-index: 9999;
	        	color: black;
	        	background-color: white;
	        	
	        	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
			    padding: 0px 0;
			    transition: all 0.3s ease;
			    border-radius: 3px;
			    font-size: 10px;
	        }
	        
	        #suggerimenti-content1 {
	        	top: 35px;
	        }
	        
	        #suggerimenti-content {
	        	left: 51.5%;
	        	top: 32px;
	        }
	        
	        #suggerimenti-content ul, #suggerimenti-content1 ul {
		      list-style-type: none;
		      margin: 0;
		      padding: 0;
		    }
		    
	        #suggerimenti-content li, #suggerimenti-content1 li {
		      padding: 10px 20px;
		      cursor: pointer;
		      transition: background-color 0.3s ease;
		    }
		
		    #suggerimenti-content li:hover, #suggerimenti-content1 li:hover {
		      background-color: #f0f0f0;
		    }
		
		    #suggerimenti-content li:not(:last-child), #suggerimenti-content1 li:not(:last-child) {
		      border-bottom: 1px solid #e0e0e0;
		    }
		    
		    #suggerimenti-content li:last-child, #suggerimenti-content1 li:last:child {
		    	margin-bottom: 0;
		    }
		    
		    #suggerimenti-content li:first-child, suggerimenti-content1li:firt-child {
		    	margin-top: 0;
		    }

    </style>
</head>
<body style="font-size: 70%">
	<%
		HttpSession s1 = request.getSession();
    	Boolean logged1 = (Boolean) s1.getAttribute("loggedIn");
    	HttpSession s = request.getSession();
        Boolean logged = (Boolean) s.getAttribute("loggedIn");
        Boolean admin = (Boolean) s.getAttribute("admin");
	%>
	<header id="header1"> <!-- header per computer (min-width: 1024px) -->
	    <a href="index.jsp" id="logolink">
	    	<img src="/progetto10/images/logo2.png" id="logo">
	    </a>
	    <nav>
	        <ul>
	            <li><a href="index.jsp">Home</a></li>
	            <li><a href="catalogo.jsp">Catalogo</a></li>
	            <li><a href="carrello.jsp">Carrello</a></li>
			     <% 
			        
			        if (logged1 != null && logged1) {
			    %>
			        <li><a href="profilo.jsp">Profilo</a></li>
			        <li><a href="ordini.jsp">Ordini</a></li>
			    <% 
			        } else {
			    %>

			    <% 
			        }
			    %>
	        </ul>
	    </nav>
	    <div class="search-bar">
	    	<form method="get" action="CercaServlet">
	    		<input type="text" placeholder="Cerca..." name="cerca" id="searchBar" autocomplete="off" onkeyup="cercaSuggerimenti(0)">
	    		<input type="submit" class="cerca-button" value="Cerca">
	    	</form>
	    	<div id="suggerimenti-content">
	    	<ul id="suggerimenti">
	    	</ul>
	    </div>
	    </div>
	    <div class="space-between"></div>
	    <% 
	        
	        if(admin != null && admin) {
	        	%>
	        	<button class="login-button" onclick="toPage('./protected/indexAdmin.jsp')">Amministrazione</button>
	        	<%
	        			
	        }
	        	
	        if ((logged != null && logged) || ( admin != null && admin)) {
	    %>
	    	
	        <button class="login-button" onclick="toPage('LogoutServlet')">Esci</button>
	    <% 
	        } else {
	    %>
	        <button class="login-button" onclick="toPage('login.jsp')">Accedi</button>
	    <% 
	        }
	    %>
	</header>
	<header id="header2"> <!-- header per smartphone max-witdh: 600px -->
	    <img src="/progetto10/images/logo2.png" id="logo" onclick="toPage('index.jsp')">
		<div class="search-bar">
	    	<form method="get" action="CercaServlet" class="cerca">
	    		<input type="text" placeholder="Cerca..." name="cerca" id="searchBar1" onkeyup="cercaSuggerimenti(1)" autocomplete="off">
	    		<input type="submit" class="cerca-button" value="Cerca">
	    	</form>
	    	<div id="suggerimenti-content1">
	    	<ul id="suggerimenti1">

	    	</ul>
	    </div>
	    </div>
	    <img src="images/hamburger.png" id="hamburger" onclick="hamburger()">
	    <div id="hamburger-content" class="nascosto">
	    	<ul>
	    		<li onclick="toPage('catalogo.jsp')">Catalogo</li>
	    		<li onclick="toPage('carrello.jsp')">Carrello</li>
	    		<%
		    		if(admin != null && admin) {
			        	%>
			        	<li onclick="toPage('./protected/indexAdmin.jsp')">Amministrazione</li>
			        	<%
			        			
			        }
	    		
	    			if(logged != null && logged) {
	    		%>
	    				<li onclick="toPage('profilo.jsp')">Profilo</li>
	    				<li onclick="toPage('ordini.jsp')">Ordini</li>
	    		<%
	    			}
	    			if((logged != null && logged) || (admin != null && admin)) {
	    		%>
	    				<li onclick="toPage('LogoutServlet')">Esci</li>
	    		<%
	    			} else {
	    		%>
	    				<li onclick="toPage('login.jsp')">Accedi</li>
	    		<%
	    			}
	    		%>
	    		
	    	</ul>
	    </div>
	</header>


