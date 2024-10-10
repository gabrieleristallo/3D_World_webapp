package dao;

import java.util.ArrayList;
import model.*;

public interface ProdottoDao {
	
	//SELECT
	Prodotto getProdottoById(int id);
	Prodotto getProdottoByIdColor(int id_prodotto_colore);
	ArrayList<Prodotto> getAllProdotti();
	ArrayList<Prodotto> getProdottiFiltredByPrezzo(float prezzo);
	ArrayList<Prodotto> getProdottiFiltred(String materiale, float prezzo);
	ArrayList<Prodotto> getProdottiByParameter(String parametro);
	ArrayList<Prodotto> getProdottiByParameterSug(String parametro);
	ArrayList<Prodotto> getProdottiOrder(String ordinamento, String valore);
	ArrayList<ProdottoColore> getProdottoColoreById(int id_prodotto);
	ProdottoColore getProdottoColoreByIdAndColor(int id_prodotto, String colore);
	String getHexByIdPrdottoColore(int id_prodotto_colore);
	int getIdProdottoByIdColore(int id_prodotto_colore);
	String getColoreByIdProdottoColore(int id_prodotto_colore);
	
	//INSERT
	void saveProdotto(Prodotto p);
	int saveProdotto(String nome, String descrizione, float prezzo, String materiale);
	void saveProdottoColore(int id_prodotto, String nomeColore, String colore, int qta);
	
	//UPDATE
	void updateProdotto(Prodotto p);
	void updateProdotto(int id, String nome, String descrizione, float prezzo, String materiale, int visibile);
	void updateProdottoColore(int id_prodotto_colore, int qta);
	
	//DELETE
	void deleteProdotto(int id);
	void deleteProdottoColore(int id_prodotto_colore);
}