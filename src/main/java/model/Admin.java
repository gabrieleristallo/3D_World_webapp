package model;

public class Admin {
	String pw;
	String utente;
	
	public Admin(String pw, String utente) {
		super();
		this.pw = pw;
		this.utente = utente;
	}

	public String getPw() {
		return pw;
	}

	public String getUtente() {
		return utente;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}
}