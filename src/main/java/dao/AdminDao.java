package dao;

import java.util.ArrayList;

import model.Admin;

public interface AdminDao {
	//SELECT
	ArrayList<Admin> getCredenziali();
	
	//INSERT
	void saveCredenziali(String utente, String pw);
}