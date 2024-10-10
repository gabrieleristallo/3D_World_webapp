package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import model.DettaglioOrdine;
import model.Ordine;
import model.Prodotto;
import model.ProdottoCarrello;
import model.Utente;

public interface OrdineDao {
	
	//SELCT
	Ordine getOrdineById(int id);
	ArrayList<Ordine> getAllOrdini();
	ArrayList<Ordine> getOrdiniByDate(String data);
	ArrayList<Ordine> getOrdineByUtente(int id_utente);
	ArrayList<DettaglioOrdine> getDettaglioOrdineById(int id_ordine);
	ArrayList<ProdottoCarrello> getProdottiDaOrdine(int id_ordine);
	
	//INSERT
	void saveOrdine(Ordine o);
	int saveOrdine(int id_utente, String data, float totale);
	void saveDettaglioOrdine(DettaglioOrdine od);
	void saveDettaglioOrdine(int id_ordine, int id_prodotto_colore, int qta, float prezzo) throws Exception;
	
	//DELETE
	void deleteOrdine(int id);
}