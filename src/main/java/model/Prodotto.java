package model;

import java.util.ArrayList;

public class Prodotto {
	int id;
	String nome;
	String descrizione;
	float prezzo;
	String materiale;
	int visibile;
	
	public Prodotto(int id, String nome, String descrizione, float prezzo, String materiale, int visibile) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.materiale = materiale;
		this.visibile = visibile;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getVisibile() {
		return this.visibile;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}
	
	public float getPrezzo() {
		return this.prezzo;
	}
	
	public String getMateriale() {
		return this.materiale;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}
	
	public void setVisibile(int visibile) {
		this.visibile = visibile;
	}
	
	@Override
	public String toString() {
	    return "Prodotto{" +
	            "id=" + id +
	            ", nome='" + nome + '\'' +
	            ", descrizione='" + descrizione + '\'' +
	            ", prezzo=" + prezzo +
	            ", materiale='" + materiale + '\'' +
	            '}';
	}

	
	
}