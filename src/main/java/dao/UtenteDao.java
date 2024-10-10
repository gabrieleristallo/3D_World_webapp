package dao;

import java.util.ArrayList;
import model.*;

public interface UtenteDao {
	
	//SELECT
	Utente getUtenteById(int id);
	Utente getUtenteBycredentials(String email, String password);
	Utente getUtenteByEmail(String email);
	ArrayList<Utente> getAllUtenti();
	
	ArrayList<Pagamento> getMetodiPagamentoByUtente(int id_utente);
	
	//INSERT
	void saveUtente(Utente u);
	void saveUtente(String nome, String cognome, String email, String password, String indirizzo);
	
	void saveMetodoPagamento(int id_utente, String numero_carta, String scadenza, String codice);
	
	//UPDATE
	void updateUtente(Utente u);
	void updateUtente(int id, String nome, String cognome, String email, String password, String indirizzo);
	
	//DELETE
	void deleteUtente(int id);
	
	void deleteMetodoPagamento(String numero_carta);
}