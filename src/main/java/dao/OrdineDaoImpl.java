package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.DatabaseManager;
import model.DettaglioOrdine;
import model.Ordine;
import model.Prodotto;
import model.ProdottoCarrello;

public class OrdineDaoImpl implements OrdineDao {
	
	private Connection conn;
    
    public OrdineDaoImpl() {
        conn = DatabaseManager.databaseManager();
    }

	@Override
	public Ordine getOrdineById(int id) {
		try {
			String sql = "SELECT * FROM ordini WHERE id_ordine = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				return new Ordine(rs.getInt("id_ordine"), rs.getInt("id_utente"), rs.getString("data"), rs.getFloat("totale"));
			}
			return null;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Ordine> getOrdiniByDate(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ordine> getOrdineByUtente(int id_utente) {
		try {
			String sql = "SELECT * FROM ordini WHERE id_utente = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_utente);
			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<Ordine> lista = new ArrayList<>();
			
			while(rs.next()) {
				Ordine o = new Ordine(rs.getInt("id_ordine"), rs.getInt("id_utente"), rs.getString("data"), rs.getFloat("totale"));
				lista.add(o);
			}
			return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<DettaglioOrdine> getDettaglioOrdineById(int id_ordine) {
		try {
			String sql = "SELECT * FROM dettaglio_ordini WHERE id_ordine = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_ordine);
			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<DettaglioOrdine> lista = new ArrayList<>();
			
			while(rs.next()) {
				DettaglioOrdine o = new DettaglioOrdine(rs.getInt("id_dettaglio_ordini"), rs.getInt("id_prodotto_colore"), rs.getInt("qta"), rs.getFloat("prezzo"));
				lista.add(o);
			}
			return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void saveOrdine(Ordine o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int saveOrdine(int id_utente, String data, float totale) {
	    try {
	        String sql = "INSERT INTO ordini (id_utente, data, totale) VALUES (?,?,?)";
	        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        statement.setInt(1, id_utente);
	        statement.setString(2, data);
	        statement.setFloat(3, totale);

	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected == 0) {
	            conn.rollback();
	            return 0;
	        }

	        ResultSet generatedKeys = statement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            int id_ordine = generatedKeys.getInt(1);
	            conn.commit();
	            return id_ordine;
	        } else {
	            conn.rollback();
	            return 0;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return 0;
	    }
	}


	@Override
	public void saveDettaglioOrdine(DettaglioOrdine od) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDettaglioOrdine(int id_ordine, int id_prodotto_colore, int qta, float prezzo) throws Exception {
		if(!checkQta(id_prodotto_colore, qta)) {
			String sql = "DELETE FROM ordini WHERE id_ordine=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_ordine);
			statement.executeUpdate();
			conn.commit();
			throw new Exception("Quantità non sufficiente " + id_prodotto_colore);
		} else {
			String sqlSelect = "SELECT qta FROM prodotti_colori WHERE id_prodotto_colore=?";
			PreparedStatement ss = conn.prepareStatement(sqlSelect);
			ss.setInt(1, id_prodotto_colore);
			ResultSet rss = ss.executeQuery();
			int qtaDisponibile = 0;
			if(rss.next()) {
				qtaDisponibile = rss.getInt("qta");
			}
			
			int qtaAggiornata = qtaDisponibile - qta;
			
			String sqlUpdate = "UPDATE prodotti_colori SET qta = ? WHERE id_prodotto_colore = ?";
			PreparedStatement su = conn.prepareStatement(sqlUpdate);
			su.setInt(1, qtaAggiornata);
			su.setInt(2, id_prodotto_colore);
			
			su.executeUpdate();
			conn.commit();
		}
		try {
			String sql = "INSERT INTO dettaglio_ordini (id_ordine, id_prodotto_colore, qta, prezzo) VALUES (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_ordine);
			statement.setInt(2, id_prodotto_colore);
			statement.setInt(3, qta);
			statement.setFloat(4, prezzo);
			
			statement.executeUpdate();
			conn.commit();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void deleteOrdine(int id) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkQta(int id_prodotto_colore, int qta) {
		try {
			String sql = "SELECT * FROM prodotti_colori WHERE id_prodotto_colore = ? AND qta >= ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_prodotto_colore);
			statement.setInt(2, qta);
			
			ResultSet rs = statement.executeQuery();
			if(!rs.next()) {
				return false;
			}
			return true;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public ArrayList<Ordine> getAllOrdini() {
		try {
			String sql = "SELECT * FROM ordini";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<Ordine> lista = new ArrayList<>();
			
			while(rs.next()) {
				Ordine o = new Ordine(rs.getInt("id_ordine"), rs.getInt("id_utente"), rs.getString("data"), rs.getFloat("totale"));
				lista.add(o);
			}
			return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<ProdottoCarrello> getProdottiDaOrdine(int id_ordine) {
		try {
			String sql = "SELECT prodotti.id_prodotto, prodotti.nome, prodotti.descrizione, dettaglio_ordini.qta, colore, prodotti.prezzo, prodotti.materiale  FROM dettaglio_ordini JOIN prodotti_colori ON dettaglio_ordini.id_prodotto_colore = prodotti_colori.id_prodotto_colore JOIN prodotti ON prodotti_colori.id_prodotto = prodotti.id_prodotto JOIN colori ON prodotti_colori.hex = colori.hex WHERE id_ordine = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_ordine);
			ResultSet rs = statement.executeQuery();
			
			ArrayList<ProdottoCarrello> lista = new ArrayList<>();
			
			while(rs.next()) {
				ProdottoCarrello p = new ProdottoCarrello(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getInt("qta"), rs.getFloat("prezzo"), rs.getString("materiale"), rs.getString("colore"));
				lista.add(p);
			}
			return lista;
		}  catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}