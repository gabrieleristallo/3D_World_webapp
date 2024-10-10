package model;


public class DettaglioOrdine {
	int id_dettaglio_ordine;
	int id_prodotto_colore;
	int qta;
	float prezzo;
	
	public DettaglioOrdine(int id_dettaglio_ordine, int id_prodotto_colore, int qta, float prezzo) {
		super();
		this.id_dettaglio_ordine = id_dettaglio_ordine;
		this.id_prodotto_colore = id_prodotto_colore;
		this.qta = qta;
		this.prezzo = prezzo;
	}

	public int getId_dettaglio_ordine() {
		return id_dettaglio_ordine;
	}

	public int getId_prodotto_colore() {
		return id_prodotto_colore;
	}

	public int getQta() {
		return qta;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setId_dettaglio_ordine(int id_dettaglio_ordine) {
		this.id_dettaglio_ordine = id_dettaglio_ordine;
	}

	public void setId_prodotto_colore(int id_prodotto_colore) {
		this.id_prodotto_colore = id_prodotto_colore;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
}