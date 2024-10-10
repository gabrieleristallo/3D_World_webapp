package dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.DatabaseManager;

public class PhotoDaoImpl implements PhotoDao {
	
	private Connection conn;
    
    public PhotoDaoImpl() {
        conn = DatabaseManager.databaseManager();
    }

	@Override
	public byte[] getPhoto(int id) {
		byte[] bt = null;
		try {
			String sql = "SELECT image from prodotti WHERE id_prodotto = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				bt = rs.getBytes("image");
			}
			return bt;
		} catch (SQLException e) {
			System.out.println("Photo dao " + e.getMessage());
			return bt;
		}
	}

	@Override
	public void loadPhoto(int id, String photo) {
		try {
			String sql = "UPDATE prodotti SET image = ? WHERE id_prodotto = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			File file = new File(photo);
			try {
				FileInputStream fis = new FileInputStream(file);
				statement.setBinaryStream(1, fis, fis.available());
				statement.setInt(2, id);
				
				statement.executeUpdate();
				conn.commit();
			} catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch(IOException i) {
				System.out.println(i.getMessage());
			} catch (Exception r) {
				System.out.println(r.getMessage());
			}
		} catch (SQLException g) {
			System.out.println(g.getMessage());
		} catch (Exception y) {
			System.out.println(y.getMessage());
		}
	}
	
}