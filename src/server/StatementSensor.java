package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementSensor extends Thread {
	public Connection createConnection() {
		PropertyLoader prop = new PropertyLoader();
		try {
			prop.loaded();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("error properties");
		}
		try {
				Class.forName(prop.getProperty("driver"));
				Connection c = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
				prop.close();
				return c;
		} catch(Exception e) {}
		return null;
	}
	
	public void statement() throws SQLException {
		Connection c = this.createConnection();
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT id_sensor FROM sensor;");
		while(r.next()) {
			new Thread(new ThreadSensor(r.getInt(1))).start();
		}
	}
}
