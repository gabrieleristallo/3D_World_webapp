function cercaSuggerimenti(valore) {
    var inputId = (valore === 0) ? 'searchBar' : 'searchBar1';
    var input = document.getElementById(inputId).value;
    var params = 'parametro=' + input;
    loadAjaxDoc('SuggerimentiCercaServlet', "GET", params, handleResult, valore);
}


function createXMLHttpRequest() {
    var request;
    try {
        // Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
        request = new XMLHttpRequest();
    } catch (e) {
        // Versioni precedenti di Internet Explorer
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Il browser non supporta AJAX");
                return null;
            }
        }
    }
    return request;
}

function loadAjaxDoc(url, method, params, cFuction, valore) {
    var request = createXMLHttpRequest();
    if (request) {
        request.onreadystatechange = function() {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    cFuction(this, valore);
                } else {
                    if (this.status == 0) { // Quando si annulla la richiesta
                        alert("Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
                    } else { // Qualsiasi altra situazione
                        alert("Problemi nell'esecuzione della richiesta:\n" + this.status);
                    }
                    return null;
                }
            }
        };

        setTimeout(function() { // per annullare dopo 15 secondi
            if (request.readyState < 4) {
                request.abort();
            }
        }, 15000);

        if (method.toLowerCase() == "get") {
            if (params) {
                request.open("GET", url + "?" + params, true);
            } else {
                request.open("GET", url, true);
            }
            request.send();
        } else {
            if (params) {
                request.open("POST", url, true);
                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(params);
            } else {
                console.log("Utilizza GET se non ci sono parametri!");
                return null;
            }
        }
    }
}


function handleResult(request, valore) {
    var response = JSON.parse(request.responseText);
    var suggerimenti = response.result;
    var suggerimentiDiv = (valore === 0) ? document.getElementById('suggerimenti') : document.getElementById('suggerimenti1');
    suggerimentiDiv.classList.remove("nascosto");
    suggerimentiDiv.innerHTML = "";
    
    var input = document.getElementById((valore === 0) ? 'searchBar' : 'searchBar1').value;
    
    suggerimenti.forEach(function(suggerimento) {
        var suggerimentoElement = document.createElement('li');
        
        var suggerimentoParts = suggerimento.split(new RegExp('(' + input + ')', 'gi')); 
        
        suggerimentoParts.forEach(function(part) {
            var span = document.createElement('span');
            if (part.toLowerCase() === input.toLowerCase()) {
                span.innerHTML = '<strong>' + part + '</strong>';
            } else {
                span.textContent = part;
            }
            suggerimentoElement.appendChild(span); 
        });
        
        suggerimentoElement.onclick = function() {
            var searchBarId = (valore === 0) ? 'searchBar' : 'searchBar1';
            document.getElementById(searchBarId).value = suggerimento;
            suggerimentiDiv.classList.add("nascosto");
        };
        
        suggerimentiDiv.appendChild(suggerimentoElement);
    });
}



/*function handleResult(request, valore) {
	if(valore == 0) {
		var response = JSON.parse(request.responseText);
	    var suggerimenti = response.result;
	    var suggerimentiDiv = document.getElementById('suggerimenti');
	    suggerimentiDiv.classList.remove("nascosto");
	    suggerimentiDiv.innerHTML = "";
	    
	    var input = document.getElementById('searchBar').value; // Ottieni il valore inserito dall'utente
	    
	    suggerimenti.forEach(function(suggerimento) {
	        var suggerimentoElement = document.createElement('li');
	        suggerimentoElement.onclick = function() {
			    document.getElementById('searchBar').value = suggerimento;
			    suggerimentiDiv.classList.add("nascosto");
			};
			var suggerimentoFormattato = suggerimento.replace(new RegExp(input, 'gi'), function(match) {
		    	return '<strong>' + match + '</strong>';
			});
	        suggerimentoElement.innerHTML = suggerimentoFormattato; // Usa innerHTML invece di textContent per mantenere il markup HTML
	        suggerimentiDiv.appendChild(suggerimentoElement);
	    });
	} else {
		var response = JSON.parse(request.responseText);
	    var suggerimenti = response.result;
	    var suggerimentiDiv = document.getElementById('suggerimenti1');
	    suggerimentiDiv.classList.remove("nascosto");
	    suggerimentiDiv.innerHTML = "";
	    
	    var input = document.getElementById('searchBar').value; // Ottieni il valore inserito dall'utente
	    
	    suggerimenti.forEach(function(suggerimento) {
	        var suggerimentoElement = document.createElement('li');
	        suggerimentoElement.onclick = function() {
			    document.getElementById('searchBar1').value = suggerimento;
			    suggerimentiDiv.classList.add("nascosto");
			};
			var suggerimentoFormattato = suggerimento.replace(new RegExp(input, 'gi'), function(match) {
		    	return '<strong>' + match + '</strong>';
			});
	        suggerimentoElement.innerHTML = suggerimentoFormattato; // Usa innerHTML invece di textContent per mantenere il markup HTML
	        suggerimentiDiv.appendChild(suggerimentoElement);
	    });
	}

}*/

