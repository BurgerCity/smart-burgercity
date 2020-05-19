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
	public StatementSensor() {}
	public StatementSensor(Socket s) {
		this.ss = s;
	}	
		public void statement(DataSource data, Crud crud, Server server, ServerSocket ss2018) throws SQLException, ClassNotFoundException, IOException {
			System.out.println("ICI ???????????");
			Connection c = data.takeConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT id_sensor FROM sensor;");
			System.out.println("JE SUIS LA OU PAS");
			while(r.next()) {
				new Thread(new ThreadSensor(r.getInt(1), ss, data, crud, ss2018)).start();
			}
			data.returnConnection(c);
		}
}
