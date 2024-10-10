package dao;

public interface PhotoDao {
	byte[] getPhoto(int id);
	void loadPhoto(int id, String photo);
}