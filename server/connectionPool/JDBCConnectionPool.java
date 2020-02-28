package connectionPool;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.Properties;



public class JDBCConnectionPool {
	
	private ArrayList<Connection> a = new ArrayList<Connection>();
	
	JDBCConnectionPool(){
		try {
			Class.forName("com.postgre.jdbc.driver"); // loaded the driver (use properties)
			for(int i = 1 ; i <= 10 ; i++) {
				a.add(DriverManager.getConnection(/*OBJET_PROPERTIES.getProperties(url)*/ "url", "user", "password")); // connexion
			}
		} catch (Exception e){}
	}
	
//public load(InputStream inStream) 
	

	public Connection take() { 		// To take a object of the attribut
		Connection cp = a.get(0);
		a.remove(0);
		return cp;
	} 

	void restore(Connection cp) {		// return the connection
		a.add(cp);
	}

	void closeConnection() throws SQLException { // Close connection of the attribut
		for(Connection y : a) {
			y.close();
		}
	} 
}
