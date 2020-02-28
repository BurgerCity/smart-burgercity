package connectionPool;

import java.util.ArrayList;
import java.sql.*;

public class JDBCConnectionPool {


	private ArrayList<Connection> a;

	public void fill() throws SQLException { 		// remplir l'attribut
		//try {Class.forName("url");} // driver chargé 
		for(int i = 1 ; i <= 10 ; i++) {
			a.add(DriverManager.getConnection("url", "user" + i, "password" + i)); // connexion
			Statement s = a.get(i).createStatement (); // statement créé
		} 
	}
	
	public Connection take(int i) { 		// prend un objet de l'attribut
	
		Connection cp = a.get(i);
		a.remove(i);
		return cp;
	} 

	void restore(Connection cp) {
		a.add(cp);
	} // rendre la connexion

	void closeConnection() throws SQLException {
		for(Connection y : a) {
			y.close();
		}
	} // ferme les connexion de l'attribut
}
