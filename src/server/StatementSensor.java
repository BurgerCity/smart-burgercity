package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementSensor extends Thread {
	Socket ss;
	public StatementSensor(Socket s) {
		this.ss = s;
	}	
		public void statement(DataSource data, Crud crud) throws SQLException, ClassNotFoundException {;
			Connection c = data.takeConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT id_sensor FROM sensor;");
			while(r.next()) {
				new Thread(new ThreadSensor(r.getInt(1), ss, data, crud)).start();
			}
			data.returnConnection(c);
		}
}
