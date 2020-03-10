package connectionPool;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;



public class JDBCConnectionPool {
	
	private ArrayList<Connection> a = new ArrayList<Connection>();
	JDBCConnectionPool() {
		PropertyLoader prop = new PropertyLoader();
		try {
			prop.loaded();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName(prop.getProperty("driver")); // loaded the driver (use properties)
			for(int i = 1 ; i <= 10 ; i++) {
				a.add(DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"))); //connection
			}
		} catch (Exception e){}
	}
	
	public Connection take() { 		// To take a object of the attribute
		Connection cp = a.get(0);
		a.remove(0);
		return cp;
	} 

	void restore(Connection cp) {		// return the connection
		a.add(cp);
	}
	void closeConnection() throws SQLException { // Close connection of the attribute
		for(Connection y : a) {
			y.close();
		}
	}
}
