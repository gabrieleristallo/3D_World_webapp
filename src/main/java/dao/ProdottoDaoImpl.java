package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

public class ProdottoDaoImpl implements ProdottoDao {
	
	private Connection conn;
    
    public ProdottoDaoImpl() {
        conn = DatabaseManager.databaseManager();
    }

	@Override
	public Prodotto getProdottoById(int id) {
		try {
			String sql = "SELECT * FROM prodotti WHERE id_prodotto =?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next()) {
	        	return new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"), rs.getInt("visibile"));
	        }
	        
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Prodotto> getAllProdotti() {
			try {
				String sql = "SELECT * FROM prodotti";
		        
		        PreparedStatement statement = conn.prepareStatement(sql);
		        
		        ResultSet rs = statement.executeQuery();
		        ArrayList<Prodotto> lista = new ArrayList<>();
		        
		        while(rs.next()) {
		        	lista.add(new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"), rs.getInt("visibile")));
		        }
		        return lista;
			} catch(SQLException e) {
				System.out.println("getAllProdotti" + e.getMessage());
				return null;
			}
	}

	@Override
	public ArrayList<Prodotto> getProdottiFiltredByPrezzo(float prezzo) {
		try {
			String sql = "SELECT * FROM prodotti WHERE prezzo < ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setFloat(1, prezzo);
	        ResultSet rs = statement.executeQuery();
	        ArrayList<Prodotto> lista = new ArrayList<>();
	        
	        while(rs.next()) {
	        	lista.add(new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"), rs.getInt("visibile")));
	        }
	        return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Prodotto> getProdottiFiltred(String materiale, float prezzo) {
		try {
			String sql = "SELECT * FROM prodotti WHERE prezzo < ? AND materiale LIKE ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setFloat(1, prezzo);
	        statement.setString(2, materiale);
	        ResultSet rs = statement.executeQuery();
	        ArrayList<Prodotto> lista = new ArrayList<>();
	        
	        while(rs.next()) {
	        	lista.add(new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"), rs.getInt("visibile")));
	        }
	        return lista;
		} catch(SQLException e) {
			return null;
		}
	}

	@Override
	public ArrayList<Prodotto> getProdottiByParameter(String parametro) {
		try {
			String sql = "SELECT * FROM prodotti WHERE nome LIKE ? OR descrizione LIKE ? OR materiale LIKE ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, "%" + parametro + "%");
	        statement.setString(2,  "%" + parametro + "%");
	        statement.setString(3,  "%" + parametro + "%");
	        ResultSet rs = statement.executeQuery();
	        ArrayList<Prodotto> lista = new ArrayList<>();
	        
	        while(rs.next()) {
	        	lista.add(new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"),  rs.getInt("visibile")));
	        }
	        return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void saveProdotto(Prodotto p) {
		try {
			String sql = "INSERT INTO prodotto(nome, descrizione, materiale, prezzo) VALUES(?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, p.getNome());
	        statement.setString(2, p.getDescrizione());
	        statement.setString(3, p.getMateriale());
	        statement.setFloat(4, p.getPrezzo());
	        statement.executeQuery();

		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	@Override
	public int saveProdotto(String nome, String descrizione, float prezzo, String materiale) {
		try {
			String sql = "INSERT INTO prodotti(nome, descrizione, materiale, prezzo) VALUES(?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        statement.setString(1, nome);
	        statement.setString(2, descrizione);
	        statement.setString(3, materiale);
	        statement.setFloat(4, prezzo);
	        //statement.executeQuery();
	        
	        int nuovoId = 0;
	        
	        int righeInserite = statement.executeUpdate();
	        
	        if (righeInserite == 1) {
	            ResultSet rs = statement.getGeneratedKeys();
	            if (rs.next()) {
	                nuovoId = rs.getInt(1); // Ottieni l'ID generato
	            }
	        }
	        
	        conn.commit();
	        
	        return nuovoId;

		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public void updateProdotto(Prodotto p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProdotto(int id, String nome, String descrizione, float prezzo, String materiale, int visibile) {
		try {
			String sql = "UPDATE prodotti SET nome = ?, descrizione = ?, prezzo = ?, materiale = ?, visibile = ? WHERE id_prodotto = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setString(2, descrizione);
			statement.setFloat(3, prezzo);
			statement.setString(4, materiale);
			statement.setInt(5, visibile);
			statement.setInt(6, id);
			
			statement.executeUpdate();
			conn.commit();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void deleteProdotto(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ProdottoColore> getProdottoColoreById(int id_prodotto) {
		try {
			String sql = "SELECT * FROM prodotti_colori NATURAL JOIN colori WHERE id_prodotto = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id_prodotto);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        ArrayList<ProdottoColore> lista = new ArrayList<>();
	        
	        while(rs.next()) {
	        	lista.add(new ProdottoColore(rs.getInt("id_prodotto_colore"), rs.getInt("id_prodotto"), rs.getString("hex"), rs.getString("colore"), rs.getInt("qta")));
	        }
	        return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void saveProdottoColore(int id_prodotto, String nomeColore, String colore, int qta) {
		
		if(!isHexPresent(colore)) {
			try {
				String sql = "INSERT INTO colori (hex, colore) VALUES (?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, colore);
				statement.setString(2, nomeColore);
				System.out.println(statement);
				statement.executeUpdate();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		try {
			String sql = "INSERT INTO prodotti_colori (id_prodotto, hex, qta) VALUES(?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id_prodotto);
	        statement.setString(2, colore);
	        statement.setInt(3, qta);
	        
	        statement.executeUpdate();
	        conn.commit();

		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	@Override
	public void updateProdottoColore(int id_prodotto_colore, int qta) {
		try {
			String sql = "UPDATE prodotti_colori SET qta = ? WHERE id_prodotto_colore = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, qta);
			statement.setInt(2, id_prodotto_colore);
			
			statement.executeUpdate();
			conn.commit();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList<Prodotto> getProdottiOrder(String ordinamento, String valore) {
		String sql;
    	try {
    		if(valore.equals("crescente")) {
    			sql = "SELECT * FROM prodotti ORDER BY " + ordinamento + " ASC";
    		} else {
    			sql = "SELECT * FROM prodotti ORDER BY " + ordinamento + " DESC";
    		}
            
            
            Statement statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);
            
            ArrayList<Prodotto> lista = new ArrayList<>();
	        
	        while(rs.next()) {
	        	lista.add(new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"), rs.getInt("visibile")));
	        }
	        return lista;
            
        } catch (SQLException e) {
        	System.out.println("getProdottiOrder" + e.getMessage());
            return null;
        } 
	}

	@Override
	public ProdottoColore getProdottoColoreByIdAndColor(int id_prodotto, String colore) {
		try {
			String sql = "SELECT * FROM prodotti_colori NATURAL JOIN colori WHERE id_prodotto = ? AND hex = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id_prodotto);
	        statement.setString(2, colore);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next()) {
	        	return new ProdottoColore(rs.getInt("id_prodotto_colore"), rs.getInt("id_prodotto"), rs.getString("hex"), rs.getString("colore"), rs.getInt("qta"));
	        }
	        
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public int getIdProdottoByIdColore(int id_prodotto_colore) {
		try {
			String sql = "SELECT id_prodotto FROM prodotti_colori WHERE id_prodotto_colore = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id_prodotto_colore);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next()) {
	        	return rs.getInt("id_prodotto");
	        }
	        
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
		return 0;
	}

	@Override
	public Prodotto getProdottoByIdColor(int id_prodotto_colore) {
		try {
			String sql = "SELECT * FROM prodotti WHERE id_prodotto IN (SELECT id_prodotto FROM prodotti_colori WHERE id_prodotto_colore = ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id_prodotto_colore);
	        
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	        	return new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"),  rs.getInt("visibile"));
	        }
	        return null;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String getHexByIdPrdottoColore(int id_prodotto_colore) {
		try {
			String sql = "\n"
					+ "SELECT hex FROM prodotti_colori WHERE id_prodotto_colore = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id_prodotto_colore);
	        
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	        	return rs.getString("hex");
	        }
	        return null;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String getColoreByIdProdottoColore(int id_prodotto_colore) {
		try {
			String sql = "SELECT colore FROM prodotti_colori NATURAL JOIN colori WHERE id_prodotto_colore = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_prodotto_colore);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				return rs.getString("colore");
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	private boolean isHexPresent(String hex) {
		try {
			String sql = "SELECT * FROM colori WHERE hex = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, hex);
			
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return true;
			} 
			return false;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public ArrayList<Prodotto> getProdottiByParameterSug(String parametro) {
		
		try {
			String sql = "SELECT DISTINCT * FROM prodotti WHERE nome LIKE ? LIMIT 6";
	        
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, "%" + parametro + "%");
	        ResultSet rs = statement.executeQuery();
	        ArrayList<Prodotto> lista = new ArrayList<>();
	        if(parametro == "") {
				return lista;
			}
	        while(rs.next()) {
	        	lista.add(new Prodotto(rs.getInt("id_prodotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getFloat("prezzo"), rs.getString("materiale"),  rs.getInt("visibile")));
	        }
	        return lista;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteProdottoColore(int id_prodotto_colore) {
		if(id_prodotto_colore == 0) {
			return;
		}
		try {
			String sql = "DELETE FROM prodotti_colori WHERE id_prodotto_colore = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_prodotto_colore);
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}