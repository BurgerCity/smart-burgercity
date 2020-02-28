package connectionPool;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCConnectionPool {


	private ArrayList<Connection> a;

	public void fill() throws SQLException { 		// remplir l'attribut
		try {
			Class.forName("127.0.0.1"); // driver chargé 
			for(int i = 1 ; i <= 10 ; i++) {
				a.add(DriverManager.getConnection("127.0.0.1", "user" + i, "password" + i)); // connexion
			}
		} catch (Exception e){}
	}
	
	public Connection take(int i) { 		// prend un objet de l'attribut
	
		Connection cp = a.get(i);
		a.remove(i);
		return cp;
	} 

	void restore(Connection cp) {		// rendre la connexion
		a.add(cp);
	} 

	void closeConnection() throws SQLException {
		for(Connection y : a) {
			y.close();
		}
	} // ferme les connexion de l'attribut
}
