package dao;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DatabaseManager;
import model.Pagamento;
import model.Prodotto;
import model.Utente;

public class UtenteDaoImpl implements UtenteDao {
	
	private Connection conn;
    
    public UtenteDaoImpl() {
        conn = DatabaseManager.databaseManager();
    }
    
    public void commitConnection() {
    	try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }

	@Override
	public Utente getUtenteById(int id) {
		try {
			String sql = "SELECT * FROM utenti WHERE id_utente=?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next()) {
	        	return new Utente(rs.getInt("id_utente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("pw"), rs.getString("indirizzo"));
	        }
	        
		} catch(SQLException e) {
			return null;
		}
		return null;
	}

	@Override
	public Utente getUtenteBycredentials(String email, String password) {
		try {
			String sql = "SELECT * FROM utenti WHERE email = ? AND pw = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, email);
	        statement.setString(2, toHash(password));
	        //System.out.println("Utente dao " + email + " " + toHash(password));
	        //System.out.println(statement);
	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next()) {
	        	//System.out.println(rs.getInt("id_utente")+ " " + rs.getString("nome")+ " " + rs.getString("cognome")+ " " + rs.getString("email")+ " " + rs.getString("pw")+ " " + rs.getString("indirizzo"));
	        	Utente u = new Utente(rs.getInt("id_utente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("pw"), rs.getString("indirizzo"));
	        	return u;
	        } else {
	        	System.out.println("rs.next() = false");
	        	return null;
	        }
	        
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Utente> getAllUtenti() {
		try {
			String sql = "SELECT * FROM utenti";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        ArrayList<Utente> lista = new ArrayList<>();
	        
	        while(rs.next()) {
	        	lista.add(new Utente(rs.getInt("id_utente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("pw"), rs.getString("indirizzo")));
	        }
	        return lista;
		} catch(SQLException e) {
			return null;
		}
	}

	@Override
	public void saveUtente(Utente u) {
		try {
			String sql = "INSERT INTO utenti VALUES(?, ?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, u.getNome());
	        statement.setString(2, u.getCognome());
	        statement.setString(3, u.getEmail());
	        statement.setString(4, toHash(u.getPassword()));
	        statement.setString(5, u.getIndirizzo());
	        
	        ResultSet rs = statement.executeQuery();
		} catch(SQLException e) {
			return;
		}
	}

	@Override
	public void saveUtente(String nome, String cognome, String email, String password, String indirizzo) {
		try {
			String sql = "INSERT INTO utenti (nome, cognome, email, pw, indirizzo) VALUES(?, ?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, nome);
	        statement.setString(2, cognome);
	        statement.setString(3, email);
	        statement.setString(4, toHash(password));
	        statement.setString(5, indirizzo);
	        
	        statement.executeUpdate();
	        conn.commit();
		} catch(SQLException e) {
			return;
		}
	}

	@Override
	public void updateUtente(Utente u) {
		try {
			String sql = "UPDATE utenti SET nome = ?, cognome = ?, email = ?, pw = ?, indirizzo = ? WHERE id_utente = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, u.getNome());
	        statement.setString(2, u.getCognome());
	        statement.setString(3, u.getEmail());
	        statement.setString(4, toHash(u.getPassword()));
	        statement.setString(5, u.getIndirizzo());
	        statement.setInt(6, u.getId());
	        
	        statement.executeUpdate();
	        conn.commit();
		} catch (SQLException e) {
			System.out.println("updateUtente " + e.getMessage());
		}
		
	}

	@Override
	public void updateUtente(int id, String nome, String cognome, String email, String password, String indirizzo) {
		try {
			String sql = "UPDATE utenti SET nome = ?, cognome = ?, email = ?, pw = ?, indirizzo = ? WHERE id_utente = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, nome);
	        statement.setString(2, cognome);
	        statement.setString(3, email);
	        statement.setString(4, toHash(password));
	        statement.setString(5, indirizzo);
	        statement.setInt(6, id);
	        
	        
	        statement.executeUpdate();
	        conn.commit();
		} catch (SQLException e) {
			System.out.println("updateUtente " + e.getMessage());
		}
		
	}

	@Override
	public void deleteUtente(int id) {
		// TODO Auto-generated method stub
		
	}
	
    private String toHash(String password) {
    	String hashString = null;
    	try {
    		java.security.MessageDigest digest = java.security.MessageDigest.getInstance ("SHA-512");
    		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    		hashString = "";
    		for (int i = 0; i < hash.length; i++) {
    			hashString += Integer. toHexString((hash[i] &0xFF) | 0x100). toLowerCase() .substring(1,3);
    		}
    	} catch(java. security.NoSuchAlgorithmException e) {
    		System.out.println(e);
    	}
    	return hashString;
    }

	@Override
	public Utente getUtenteByEmail(String email) {
		try {
			String sql = "SELECT * FROM utenti WHERE email = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, email);
	        //System.out.println("Utente dao " + email + " " + toHash(password));
	        //System.out.println(statement);
	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next()) {
	        	//System.out.println(rs.getInt("id_utente")+ " " + rs.getString("nome")+ " " + rs.getString("cognome")+ " " + rs.getString("email")+ " " + rs.getString("pw")+ " " + rs.getString("indirizzo"));
	        	Utente u = new Utente(rs.getInt("id_utente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("pw"), rs.getString("indirizzo"));
	        	return u;
	        } else {
	        	System.out.println("rs.next() = false");
	        	return null;
	        }
	        
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Pagamento> getMetodiPagamentoByUtente(int id_utente) {
		try {
			String sql = "SELECT * FROM pagamento WHERE id_utente = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_utente);
			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<Pagamento> lista = new ArrayList<>();
			
			while(rs.next()) {
				lista.add(new Pagamento(rs.getInt("id_utente"), rs.getString("numero_carta"), rs.getString("scadenza"), rs.getString("codice")));
			}
			return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void saveMetodoPagamento(int id_utente, String numero_carta, String scadenza, String codice) {
		try {
			String sql = "INSERT INTO pagamento VALUES(?, ?, ? ,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_utente);
			statement.setString(2, numero_carta);
			statement.setString(3, scadenza);
			statement.setString(4, codice);
			
			statement.executeUpdate();
			conn.commit();
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteMetodoPagamento(String numero_carta) {
		try {
			String sql = "DELETE FROM pagamento WHERE numero_carta = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, numero_carta);
			
			statement.executeUpdate();
			conn.commit();
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
    
	
}