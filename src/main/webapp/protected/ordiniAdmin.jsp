<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="codice.*" %>
<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<jsp:include page="headerAdmin.jsp"/>
<head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		table, h2 {
			margin-left: 20px;
		} table {
			width: 90%;
		}
		th {
		  cursor: pointer;
		}
	</style>
	<script>
	function sortTable(n) {
		  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		  table = document.getElementById("myTable");
		  switching = true;
		  dir = "asc"; 
		  while (switching) {
		    switching = false;
		    rows = table.rows;
		    for (i = 1; i < (rows.length - 1); i++) {
		      shouldSwitch = false;
		      x = rows[i].getElementsByTagName("TD")[n];
		      y = rows[i + 1].getElementsByTagName("TD")[n];
		      if (dir == "asc") {
		        if (n == 2) { // Se la colonna è la data
		          if (new Date(x.innerHTML.toLowerCase()) > new Date(y.innerHTML.toLowerCase())) {
		            shouldSwitch= true;
		            break;
		          }
		        } else if (n == 3) { // Se la colonna è il totale (float)
		          if (parseFloat(x.innerHTML.toLowerCase()) > parseFloat(y.innerHTML.toLowerCase())) {
		            shouldSwitch= true;
		            break;
		          }
		        } else { // Per le altre colonne (interi e stringhe)
		          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
		            shouldSwitch= true;
		            break;
		          }
		        }
		      } else if (dir == "desc") {
		        if (n == 2) { // Se la colonna è la data
		          if (new Date(x.innerHTML.toLowerCase()) < new Date(y.innerHTML.toLowerCase())) {
		            shouldSwitch= true;
		            break;
		          }
		        } else if (n == 3) { // Se la colonna è il totale (float)
		          if (parseFloat(x.innerHTML.toLowerCase()) < parseFloat(y.innerHTML.toLowerCase())) {
		            shouldSwitch= true;
		            break;
		          }
		        } else { // Per le altre colonne (interi e stringhe)
		          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
		            shouldSwitch= true;
		            break;
		          }
		        }
		      }
		    }
		    if (shouldSwitch) {
		      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		      switching = true;
		      switchcount ++;      
		    } else {
		      if (switchcount == 0 && dir == "asc") {
		        dir = "desc";
		        switching = true;
		      }
		    }
		  }
		}
	</script>
</head>

<div>
	<%
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		if(admin == null || !admin.booleanValue()) {
			response.sendRedirect("../index.jsp");
			return;
		}
	%>
	<h2>Ordini</h2>
	<table border="1" cellpadding="5" cellspacing="0" id="myTable">
		<tr>
		    <th onclick="sortTable(0)">Id <i class="fa fa-sort"></i></th>
		    <th onclick="sortTable(1)">Utente <i class="fa fa-sort"></i></th>
		    <th onclick="sortTable(2)">Data <i class="fa fa-sort"></i></th>
		    <th onclick="sortTable(3)">Totale <i class="fa fa-sort"></i></th>
		    <th>Azioni</th>
		  </tr>
		<%
			UtenteDaoImpl utenteDao = new UtenteDaoImpl();
			OrdineDaoImpl ordineDao = new OrdineDaoImpl();
			ArrayList<Ordine> ordini = ordineDao.getAllOrdini();
			for(Ordine ordine : ordini) {
				Utente u = utenteDao.getUtenteById(ordine.getId_utente());
		%>
			<tr>
				<td><%=ordine.getId_ordine()%></td>
				<%
					if(u == null) {
				%>
						<td>Utente rimosso</td>
				<%
					} else {
				%>
						<td><a href="dettagliUtenteAdmin.jsp?id=<%=u.getId()%>"><%=u.getEmail() %></a></td>
				<%
					}
				%>
				<td><%=ordine.getData() %></td>
				<td><%=String.format("%.2f", ordine.getTotale())%> €</td>
				<td><a href="dettagliOrdineAdmin.jsp?id=<%=ordine.getId_ordine()%>">Dettagli</a></td>
			</tr>
		<%
			}
		%>
	</table>
</div>