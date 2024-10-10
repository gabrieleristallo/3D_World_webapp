package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Admin;
import model.DatabaseManager;

public class AdminDaoImpl implements AdminDao {
	
private Connection conn;
    
    public AdminDaoImpl() {
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
	public ArrayList<Admin> getCredenziali() {
		try {
			String sql = "SELECT * FROM admin_pw";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			ArrayList<Admin> lista = new ArrayList<>();
			while(rs.next()) {
				String pw = rs.getString("pw");
			    String utente = rs.getString("utente");
			    Admin a = new Admin(pw, utente);
				lista.add(a);
			}
			return lista;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void saveCredenziali(String utente, String pw) {
		try {
			String sql = "INSERT INTO admin_pw (utente, pw) VALUES (?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			utente = "admin";
			statement.setString(1, utente);
			statement.setString(2, pw);
			statement.executeUpdate();
			conn.commit();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}