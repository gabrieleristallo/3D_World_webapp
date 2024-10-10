package model;

public class ProdottoCarrello {
	int id;
	String nome;
	String descrizione;
	int qta;
	float prezzo;
	String materiale;
	String colore;
	
	public ProdottoCarrello(int id, String nome, String descrizione, int qta, float prezzo, String materiale, String colore) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.qta = qta;
		this.prezzo = prezzo;
		this.materiale = materiale;
		this.colore = colore;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getQta() {
		return qta;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public String getMateriale() {
		return materiale;
	}

	public String getColore() {
		return colore;
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

	public void setQta(int qta) {
		this.qta = qta;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
}