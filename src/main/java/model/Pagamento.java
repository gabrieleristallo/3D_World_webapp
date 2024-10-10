package model;

public class Pagamento {
	int id_utente;
	String numero_carta;
	String scadenza;
	String codice;
	
	public Pagamento(int id_utente, String numero_carta, String scadenza, String codice) {
		this.id_utente = id_utente;
		this.numero_carta = numero_carta;
		this.scadenza = scadenza;
		this.codice = codice;
	}
	
	public int getId_utente() {
		return id_utente;
	}
	public String getNumero_carta() {
		return numero_carta;
	}
	public String getScadenza() {
		return scadenza;
	}
	public String getCodice() {
		return codice;
	}
	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}
	public void setNumero_carta(String numero_carta) {
		this.numero_carta = numero_carta;
	}
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	
}