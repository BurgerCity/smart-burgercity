package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * 
 * @author Mathias
 *
 */
public class StatementSensor extends Thread {
	private Socket ss;
	public StatementSensor() {}
	public StatementSensor(Socket s) {
		this.ss = s;
	}	
		public void statement(DataSource data, Crud crud, Server server, ServerSocket ss2018) throws SQLException, ClassNotFoundException, IOException {
			//Connection c = data.takeConnection();
			//Statement s = c.createStatement();
			ArrayList<Integer> a1 = new ArrayList<Integer>();
			while(true)	{
				ArrayList<Integer> a2 = new ArrayList<Integer>();
				Connection c = data.takeConnection();
				Statement s = c.createStatement();
				ResultSet r = s.executeQuery("SELECT id_sensor FROM sensor;");
				while(r.next()) {
					a2.add(r.getInt(1));
				}
				ResultSet rs = s.executeQuery("SELECT id_sensor FROM sensor;");
				while(rs.next()) {
					for(int i = 0; i < a2.size(); i++) {
						//System.out.println(!a1.contains(a2.get(i)));
						if(!a1.contains(a2.get(i))) {
							int x = a2.get(i);
							a1.add(x);
							new Thread(new ThreadSensor(x, ss, data, crud, ss2018)).start();
							System.out.println("j'ajoute a2 = " + a2 + "   a1 = " + a1);
						}
					}
				}
				data.returnConnection(c);
			}
		}
}
