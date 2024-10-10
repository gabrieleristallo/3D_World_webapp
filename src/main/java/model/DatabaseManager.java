package model;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {
	
    private static final String URL = "jdbc:mysql://localhost:3306/progetto_v5";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    
    public static Connection databaseManager() {
    	try {
			return getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    public static Connection getConnection() throws SQLException {
    	Connection conn = null;
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	conn.setAutoCommit(false);
    	return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unused")
	private static void addProdotto(String nome, String descrizione, float prezzo) {
    	try (Connection conn = getConnection()) {
            String sql = "INSERT INTO prodotti (id, nome, descrizione, prezzo) VALUES ("+ nome +", "+ descrizione +", "+ prezzo +")";
            /*PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, descrizione);
            statement.setFloat(3, prezzo);
            int righeInserite = statement.executeUpdate(sql);*/
            
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            
            //closeConnection(conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int addUtente(String nome, String cognome, String email, String password, String indirizzo) {
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "INSERT INTO utenti(nome, cognome, email, password, indirizzo) VALUES(?, ?, ?, ?, ?)";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, nome);
    		ps.setString(2, cognome);
    		ps.setString(3, email);
    		ps.setString(4, password);
    		ps.setString(5, indirizzo);
    		
    		int righe = ps.executeUpdate();
    		conn.commit();
    		
    		return righe;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return 0;
    	}
    }
    
    public static ResultSet getUtenti() {
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "SELECT * FROM utenti";
    		Statement s = conn.createStatement();
    		ResultSet rs = s.executeQuery(sql);
    		return rs;
    	} catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static ResultSet getProdotti() {
    	Connection conn = null;
    	try {
    		conn = getConnection();
            String sql = "SELECT * FROM prodotti";
            
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            return resultSet;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
        	//closeConnection(conn);
        }
    }
    
    public static ResultSet getProdottoById(int id) {
    	Connection conn = null;
    	try {
    		conn = getConnection();
            String sql = "SELECT * FROM prodotti WHERE id=?";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            return resultSet;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
        	//closeConnection(conn);
        }
    }
    
    public static ResultSet getProdottiOrder(String ordinamento, String valore) {
    	Connection conn = null;
    	String sql;
    	try {
    		conn = getConnection();
    		if(valore.equals("crescente")) {
    			sql = "SELECT * FROM prodotti ORDER BY " + ordinamento + " ASC";
    		} else {
    			sql = "SELECT * FROM prodotti ORDER BY " + ordinamento + " DESC";
    		}
            
            
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            return resultSet;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
        	//closeConnection(conn);
        }
    }
    
    public static ResultSet getUtente(String email, String password) {
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "SELECT * FROM utenti WHERE email=? AND password=?";
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, email);
    		ps.setString(2, password);
    		
    		ResultSet rs = ps.executeQuery();

    		return rs;
    	} catch (SQLException e) {
    		return null;
    	}
    }
    
    public static ResultSet getProdottiFiltred(String materiale, float prezzo) {
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "SELECT * FROM prodotti WHERE materiale LIKE ? AND prezzo < ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, materiale);
    		ps.setFloat(2,prezzo);
    		
    		ResultSet rs = ps.executeQuery();
    		return rs;
    	} catch (SQLException e) {
    		return null;
    	}
    }
    
    public static ResultSet getProdottiFiltredByPrezzo(float prezzo) {
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "SELECT * FROM prodotti WHERE prezzo < ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setFloat(1,prezzo);
    		
    		ResultSet rs = ps.executeQuery();
    		return rs;
    	} catch (SQLException e) {
    		return null;
    	}
    }
    
    public static ResultSet getProdottiByParameter(String parametro) {
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "SELECT * FROM prodotti WHERE nome LIKE ? UNION SELECT * FROM prodotti WHERE materiale LIKE ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1,parametro);
    		ps.setString(2,parametro);
    		
    		ResultSet rs = ps.executeQuery();
    		return rs;
    	} catch(SQLException e) {
    		return null;
    	}
    }
    
    public static ArrayList<String> getAdminPw() {
    	ArrayList<String> elenco = new ArrayList<>();
    	Connection conn = null;
    	try {
    		conn = getConnection();
    		String sql = "SELECT * FROM admin_pw";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			String s = rs.getString("pw");
    			elenco.add(s);
    		}
    		return elenco;
    	} catch(SQLException e) {
    		return null;
    	}
    }
}