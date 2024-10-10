package model;

public class ProdottoColore {
	int id_prodotto_colore;
	int id_prodotto;
	String hex;
	String colore;
	int qta;
	
	public ProdottoColore(int id_prodotto_colore, int id_prodotto, String hex, String colore, int qta) {
		this.id_prodotto_colore = id_prodotto_colore;
		this.id_prodotto = id_prodotto;
		this.hex = hex;
		this.colore = colore;
		this.qta = qta;
	}

	public int getId_prodotto_colore() {
		return id_prodotto_colore;
	}

	public int getId_prodotto() {
		return id_prodotto;
	}

	public String getHex() {
		return hex;
	}

	public String getColore() {
		return colore;
	}

	public int getQta() {
		return qta;
	}

	public void setId_prodotto_colore(int id_prodotto_colore) {
		this.id_prodotto_colore = id_prodotto_colore;
	}

	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}
}