package connectionPool;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;



public class JDBCConnectionPool {
	
	private ArrayList<Connection> a = new ArrayList<Connection>();
	JDBCConnectionPool() {
		PropertyLoader prop = new PropertyLoader();
		Connection cn=null;
		try {
			prop.loaded();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("error properties");
		}
		try {
			Class.forName(prop.getProperty("driver")); // loaded the driver (use properties)
			for(int i = 1 ; i <= 10 ; i++) {
				cn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
				a.add(cn);} //connection
			
		} catch (Exception e){
			System.out.println("erreur for");
		}
	}
	
	public ArrayList<Connection> getA() {
		return a;
	}

	public void setA(ArrayList<Connection> a) {
		this.a = a;
	}

	public Connection take()  { // To take a object of the attribute
		Connection cp=null;
		if(!a.isEmpty()) {
			cp= a.get(0);
			a.remove(0);
			System.out.println(cp);
			return cp;
			}
		else {
			return cp;
	} 
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
