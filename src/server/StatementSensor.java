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
 *For each connection on the port 2013
 */
public class StatementSensor extends Thread {
	private Socket ss;
	public StatementSensor() {}
	public StatementSensor(Socket s) {
		this.ss = s;
	}	
	
	/*this method allows to launch for each sensor the simulation
	 * There is a loop while(true) because for the simulation if the user add a sensor, this sensor must have his simulation too
	 * 
	 * In this method there are 2 arraylist, the second take all id_sensor in the database, the first compare if hit has the same id_sensor in it,
	 * if the first hasn't the id_sensor then a new thread is created and the simulation is launched
	 */
	public void statement(DataSource data, Crud crud, Server server, ServerSocket ss2018) throws SQLException, ClassNotFoundException, IOException {
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
					if(!a1.contains(a2.get(i))) {
						int x = a2.get(i);
						a1.add(x);
						new Thread(new ThreadSensor(x, ss, data, crud, ss2018)).start();
					}
				}
			}
			data.returnConnection(c);
		}
	}
}
