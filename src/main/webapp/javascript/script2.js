var problemi = "";

function validateLogin(obj) {
	var valid = true;
	var email = document.getElementsByName("email")[0];
	var alert = document.getElementById("alert");
	if(!checkEmail(email)) {
		valid = false;
		alert.innerHTML = problemi;
		alert.classList.remove("nascosto");
	} else {
		alert.classList.add("nascosto");
	}
	if(valid) {
		obj.submit();
	}
}

function validateRegister(obj) {
	var valid = true;
	var alert = document.getElementById("alert");
	problemi = "";
	var nome = document.getElementsByName("nome")[0];
	console.log(nome.classList);
	var cognome = document.getElementsByName("cognome")[0];
	var indirizzo = document.getElementsByName("indirizzo")[0];
	var email = document.getElementsByName("email")[0];
	var pw = document.getElementsByName("pw")[0];
	var pw1 = document.getElementsByName("pw1")[0];
	
	var checked = true;
	checked &= checkNome(nome);
	checked &= checkNome(cognome);
	checked &= checkIndirizzo(indirizzo);
	checked &= checkEmail(email);
	checked &= checkPw(pw, pw1);
	//checked &= await checkEmail2();

	if(!checked) {
		valid = false;
		alert.innerHTML = problemi;
		alert.classList.remove("nascosto");
	} else {
		alert.classList.add("nascosto");
	}
	if(valid) {
		obj.submit();
	}
}

function validateUpdate(obj) {
	var valid = true;
	var alert = document.getElementById("alert");
	var nome = document.getElementsByName("nome")[0];
	var cognome = document.getElementsByName("cognome")[0];
	var email = document.getElementsByName("email")[0];
	var pw = document.getElementsByName("pw")[0];
	var pw1 = document.getElementsByName("pw1")[0];
	
	var checked = true;
	checked &= checkNome(nome);
	checked &= checkNome(cognome);
	checked &= checkEmail(email);
	if(pw.value.length != 0) {
		checked &= checkPw(pw, pw1);
	}
	
	
	if(!checked) {
		valid = false;
		alert.innerHTML = problemi;
		alert.classList.remove("nascosto");
	} else {
		alert.classList.add("nascosto");
	}
	if(valid) {
		obj.submit();
	}
}

function validatePagamento(obj) {
	var valid = true;
	var numeroCarta = document.getElementById("numeroCarta");
	var scadenza = document.getElementById("scadenza");
	var codice = document.getElementById("cvv");
	var checked = true;
	checked &= checkNumero(numeroCarta);
	checked &= checkScadenza(scadenza);
	checked &= checkCVV(codice);
	
	if(!checked) {
		valid = false;
	}
	if(valid) {
		obj.submit();
	}
}

function checkNumero(elemento) {
	var regex = /^\d{4}\s\d{4}\s\d{4}\s\d{4}$/;
	if(elemento && elemento.value && elemento.value.match(regex)) {
		elemento.classList.remove("error");
		return true;
	} else {
		elemento.classList.add("error");
		return false;
	}
}

function checkScadenza(elemento) {
    var value = elemento.value;
    var [month, year] = value.split('/').map(Number);

    var currentYear = new Date().getFullYear();
    var shortCurrentYear = currentYear % 100;

    var isValidMonth = month >= 1 && month <= 12;

    var isValidYear = year >= (shortCurrentYear - 1);

    if (!isValidMonth || !isValidYear) {
        elemento.classList.add("error");
        return false;
    } else {
       	elemento.classList.remove("error");
		return true;
    }
}

function checkCVV(elemento) {
	var regex = /^\d{3}$/;
	if(elemento && elemento.value && elemento.value.match(regex)) {
		elemento.classList.remove("error");
		return true;
	} else {
		elemento.classList.add("error");
		return false;
	}
}

function checkNome(elemento) {
	var regex = /\b[A-Za-z]{3,}\b/;
	if(elemento && elemento.value && elemento.value.match(regex)) {
		elemento.classList.remove("error");
		console.log(elemento.value, " true");
		return true;
	} else {
		if(problemi.length == 0) {
			problemi += "<li>Il nome o il cognome non rispettano la sintassi";
		}
		elemento.classList.add("error");
	}
	console.log(elemento.value, " false");
	return false;
}

function checkIndirizzo(elementoi) {
	return true;
	/*var regex = /^[A-Za-z ]*$/;
	console.log(elementoi.value);
	console.log(elementoi.value.match(regex));
	if(elementoi.value.match(regex)) {
		elementoi.classList.remove("error");
		console.log(elementoi.value, " true");
		return true;
	} else {
		if(problemi.length == 0) {
			problemi += "<li>L'indirizzo inserito non è valido";
		}
		elementoi.classList.add("error");
	}
	console.log(elementoi.value, " false");
	return false;*/
}

	function showPassword(pw) {
		if(pw == 1) {
			var passwordField = document.getElementById("pw");
		} else {
			var passwordField = document.getElementById("pw1");
		}
		
		var textContent = document.getElementById("togglePassword1");
		
	    const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
	    passwordField.setAttribute('type', type);
	    textContent.innerHTML = type === 'password' ? 'Mostra' : 'Nascondi';
	}

function checkPw(pw, pw1) {
	var regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
	var valid = true;

	if(!pw.value.match(regex)) {
		problemi +=  "<li>La password dovrebbe essere lunga almeno 8 caratteri e contenere caratteri e cifre";
		valid = false;
	}

	if(pw.value != pw1.value) {
		problemi += "<li>Le due password non corrispondono";
		valid = false;
	}

	if(valid) {
		pw.classList.remove("error");
		pw1.classList.remove("error");
	} else {
		pw.classList.add("error");
		pw1.classList.add("error");
	}

	return valid;
}

function checkEmail(email) {
	var regex = /^\w+([\.-]?w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.value.match(regex)){
		email.classList.remove("error");
		console.log(email.value, " true");
		//cercaEmail();
		return true;
	} else {
		problemi += "<li>L'email inserita non è valida";
		email.classList.add("error");
	}
	console.log(email.value, " false");
	return false;
}

/*function checkEmail2() {
	if(cercaEmail()) {
		email.classList.remove("error");
		console.log(email.value, " true");
		return true;
	} else {
		problemi += "<li>L'email inserita risulta già associata ad un account";
		email.classList.add("error");
	}
	return false;
}
*/

function cercaEmail() {
	var inputId = 'email';
	var input = document.getElementById(inputId).value;
	var params = 'parametro=' + input;
	loadAjaxDoc('CercaEmailServlet', "GET", params, handleEmailResult);
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

function handleEmailResult(request) {
	var response = JSON.parse(request.responseText);
	var email = document.getElementById("email");
	var emailv = response.result;
	console.log(emailv + " email da ajax");
	if(emailv == "") {
		email.classList.remove("error");
		console.log(email.value, " true");
		return true;
	} else {
		problemi += "<li>L'email inserita risulta già associata ad un account";
		email.classList.add("error");
	}
	return false;
}
