package server;

import java.util.ArrayList;

import common.Response;

import java.sql.*;
import java.io.*;



public class JDBCConnectionPool {
	Response rsp;
	private static ArrayList<Connection> a = new ArrayList<Connection>();
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
			for(int i = 1 ; i <= 1 ; i++) {
				Class.forName(prop.getProperty("driver")); // loaded the driver (use properties)
				cn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
				a.add(cn); //connection
			}
			prop.close();
		} catch (Exception e){
			System.out.println("Error Connection");
		}
	}
	
	public synchronized Connection take()  { // To take an object of the attribute
		Connection cp=null;
		while(a.isEmpty()) {
			try {
				System.out.println("Server is full");
				wait();
				rsp.setConnection_Status("WAITING");
			} catch (Exception e) {}
		}
		cp = a.get(0);
		a.remove(0);
		rsp.setConnection_Status("OK");
		return cp;
	}

	public synchronized void restore(Connection cp) {		// return the connection
		a.add(cp);
		notifyAll();
	}

	public synchronized void closeConnection() throws SQLException { // Close connection of the attribute
		for(Connection y : a) {
			y.close();
		}
	}
	
}
