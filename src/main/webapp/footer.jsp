<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<style>
	.site-footer {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    padding-top: 5px;
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 10px 0;
    z-index: 0;
    display: none; /* Inizialmente nascosto */
}
	
	/* Stile per il body */
	/*body {
	    margin-bottom: 60px; /* Aggiunge uno spazio in fondo al corpo per evitare che il footer copra il contenuto */
	}
	
	/* Aggiusta il footer solo se c'è una scrollbar verticale */
	@media screen and (min-height: 100vh) {
	    body {
	        margin-bottom: calc(60px + 1rem); /* Aggiunge spazio in fondo solo se c'è una scrollbar verticale */
	    }
	}*/
	</style>
</head>
<div style="margin-bottom: 50px;">

</div>
<footer class="site-footer">
    3D World<br>
    © Copyright 2024 3D World. All Rights Reserved. Designed by Gabriele Ristallo
</footer>
<script>
document.addEventListener("DOMContentLoaded", function() {
    var footer = document.querySelector(".site-footer");

    // Controlla la posizione della scrollbar
    window.addEventListener("scroll", function() {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            // La scrollbar è in fondo alla pagina
            footer.style.display = "block";
        } else {
            // La scrollbar non è in fondo alla pagina
            footer.style.display = "none";
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    var footer = document.querySelector(".site-footer");
    var body = document.body;
    var html = document.documentElement;

    // Controlla se la pagina ha abbastanza contenuto da richiedere lo scroll
    var hasScrollbar = body.scrollHeight > window.innerHeight || html.scrollHeight > window.innerHeight;

    // Aggiungi spazio sopra il footer se non c'è scrollbar
    if (!hasScrollbar) {
        body.style.paddingBottom = footer.offsetHeight + "px";
    }
});

</script>
</body>
</html>