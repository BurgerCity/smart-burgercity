package server;

import java.util.ArrayList;
import java.sql.*;
import java.io.*;



public class JDBCConnectionPool {
	
	private ArrayList<Connection> a = new ArrayList<Connection>();
	JDBCConnectionPool() throws ClassNotFoundException {
		PropertyLoader prop = new PropertyLoader();
		Connection cn;
		try {
			prop.loaded();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("error properties");
		}
		try {
			for(int i = 1 ; i <= 10 ; i++) {
				Class.forName(prop.getProperty("driver")); // loaded the driver (use properties)
				cn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
				a.add(cn); //connection
			}
			prop.close();
		} catch (Exception e){
			System.out.println("Error Connection");
		}
	}
	
	public Connection take()  { // To take an object of the attribute
		Connection cp=null;
		if(!a.isEmpty()) {
			cp= a.get(0);
			a.remove(0);
		}
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
