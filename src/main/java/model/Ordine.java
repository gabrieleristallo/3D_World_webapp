package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ordine {
	int id_ordine;
	int id_utente;
	String data;
	float totale;
	
	public Ordine(int id_ordine, int id_utente, String data, float totale) {
		this.id_ordine = id_ordine;
		this.id_utente = id_utente;
		this.data = data;
		this.totale = totale;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public int getId_utente() {
		return id_utente;
	}

	public String getData() {
		return data;
	}

	public float getTotale() {
		return totale;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}
}