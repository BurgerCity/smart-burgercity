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
		public void statement(DataSource data, Crud crud, Server server) throws SQLException, ClassNotFoundException, IOException {;
			Connection c = data.takeConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT id_sensor FROM sensor;");
			//Server server = new Server();
			ServerSocket serverSocket = server.startServer(2018);
			while(r.next()) {
				new Thread(new ThreadSensor(r.getInt(1), ss, data, crud, serverSocket)).start();
			}
			data.returnConnection(c);
		}
}
