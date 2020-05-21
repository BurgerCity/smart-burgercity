package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;


import common.Message;
import common.Request;
import common.Response;
/**
 * 
 * @author Mathias
 * This class enable to do the simulation of one sensor
 */

public class ThreadSensor extends Thread{
	
	private int i;
	private Socket socket;
	private Message msg;
	private BufferedReader in;
	private OutputStreamWriter out;
	private Request r;
	private Server server;
	private DataSource data;
	private Crud crud;
	private ServerSocket ss;
	private Response rp;
	
	public ThreadSensor(int i, Socket s, DataSource data, Crud crud, ServerSocket ss) {
		this.i = i;
		this.socket = s;
		this.data = data;
		this.crud = crud;
		this.ss = ss;
	}
	
	public void run() {
			Connection c = data.takeConnection();
			try {	
				Statement s = c.createStatement();
				crud = new Crud();
				this.connectionSensor(socket);					
				server = new Server();
				r = new Request();
				msg = new Message();
				
				r = server.deserialize(msg.readMessage(in));	
	
				rp = this.sendRow(s);
				data.returnConnection(c);
				this.sendResponse(rp);							//the server send in the socket the values of the sensor
				Socket client = ss.accept();					//a new socket is created for each sensor between the simulator and the thread of the sensor
				out = new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8);
				in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
				while(true) {
					r = new Request();

					r = server.deserialize(msg.readMessage(in));		//the server receives the statement of the simulation
					
					if(r == null) {										//if the simulation is finished, it comes out the while(true)
						break;
					}
					
					System.out.println(r.getA());
					r.getA().add("now()");								//the order in the arraylist is important because in the class Crud
					r.setTable("statements");
					r.getA().add("1");
					//this if test if the number of line for the sensor in the table statements is equal to the column timebeforealert in the table sensor
					if(this.NumberLine() == Integer.parseInt(rp.getA().get(2))) { 
						this.deleteRow(); //if it's the case, the oldest row is deleted
					}
					crud.insert(r, data);			//this method insert the statement of the simulator in the table statements
				
					int alert = this.testAlert();	//this method get the type of alert
					if(alert == 1) {
						this.updateAlert(alert);
					} else if(alert == 2) {
						this.updateAlert(alert);
					} else {
						this.updateAlert(alert);
					}
					System.out.println("  ");
					System.out.println("*************************************");
					System.out.println("  ");
					rp = this.sendRow(s);
					msg.sendMessage(out, server.serializeServeur(rp));		//the server send the row of the sensor to the simulator, 
																			//for that the simulator knows the type of alert
				}
			} catch (SQLException | IOException | NumberFormatException e) {}
	}
	
	public Response sendRow(Statement s) throws SQLException {				//this method recover the data of the sensor and its threshold
		ResultSet rs = s.executeQuery("SELECT * FROM sensor WHERE id_sensor = " + i + ";");
		ResultSetMetaData rmd = rs.getMetaData();
		rp = new Response();
		while(rs.next()) {
			for(int j = 1; j <= rmd.getColumnCount(); j++) {
				if(rmd.getColumnTypeName(j) == "varchar") {
					rp.getA().add(rs.getString(j));
				} else {
					if(j == 7) {
						rp.getA().add(Double.toString(rs.getDouble(j)));
					} else {
						rp.getA().add(Integer.toString(rs.getInt(j)));
					}
				}
				rp.setTypeOperation(Integer.toString(i));
			}
		}
		return rp;
	}
	
	public void updateAlert(int k) throws SQLException {			//this method update the alert
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE sensor SET alert = " + k + " WHERE id_sensor = " + i + ";");
		stmt.close();
		data.returnConnection(c);
	}

	public Integer testAlert() throws SQLException {		//this method test if the statement give an alert depending threshold of the sensor
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		ResultSet rst = stmt.executeQuery("SELECT * FROM sensor s, statements st WHERE s.id_sensor = st.id_sensor and s.id_sensor = " + i + ";");
	
		ArrayList<Integer> a = new ArrayList<Integer>();
		while(rst.next()) {
			//these double are weighted averages
			double alert = (rst.getInt(5) * 0.3 + rst.getDouble(7) * 0.1 + rst.getDouble(9) * 0.3 + rst.getInt(11) * 0.3) / (0.3 + 0.1 + 0.3 + 0.3);
			double info = (rst.getInt(6) * 0.3 + rst.getDouble(8) * 0.1 + rst.getDouble(10) * 0.3 + rst.getInt(12) * 0.3) / (0.3 + 0.1 + 0.3 + 0.3);
			double test = (rst.getInt(16) * 0.3 + rst.getDouble(17) * 0.1 + rst.getDouble(18) * 0.3 + rst.getInt(19) * 0.3) / (0.3 + 0.1 + 0.3 + 0.3);
			int k = 11;	
			int cpt = 0;
			if(test > alert) {				//if the weighted average of the test is superior than the weighted average of the alert, 2 is added to the arraylist
				a.add(2);
				System.out.println(test + " > " + alert + " ALERT");
			}
			else if (test > info) {		//if the weighted average of the test is superior than the weighted average of the information, 1 is added to the arraylist
				a.add(1);
				System.out.println(test + " > " +  info + "INFO");
			}
			else {						//if a statement of a pollutant is bigger than a threshold, 1 or 2 is added to the arraylist
				for(int j = 5; j < 9; j++) {
					if(rst.getDouble(j + cpt + 1) <= rst.getDouble(j + k)) {
						a.add(2);			
						System.out.println(rst.getDouble(j + cpt + 1) + "  <=  " + rst.getDouble(j + k) + "ALERTE");
						break;
					} else if(rst.getDouble(j + cpt) <= rst.getDouble(j + k)) {
						a.add(1);			
						System.out.println(rst.getDouble(j + cpt) + "  <=  " + rst.getDouble(j + k) + " INFO ");
						break;
					}
					cpt = cpt + 1;
				}
				
			}
		}
		System.out.println(" ");
		a = this.testTimeBeforeAlert(a);
		System.out.println(a + " apres this.alerttime");
		//System.out.println(this.alert(a));
		data.returnConnection(c);
		return this.alert(a);
	}
	
	public BufferedReader connectionSensor(Socket socket) throws IOException {
		out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		return in;
	}
	
	public Integer alert(ArrayList<Integer> a) {
		Iterator<Integer> it = a.iterator();
		int alert = 0;
		while(it.hasNext()) {
			Integer b = it.next();
			if(b == 0) return 0;
			else if(b == 2) {
				alert = alert + 1;
			}
		}
		if(alert == a.size()) {
			return 2;
		}
		return 1;
	}
	
	//if the arraylist isn't equal to the number of timeberforealert (that means, the number of statements in alert before launch the alert)
	//then 0 is added to the arraylist 
	public ArrayList<Integer> testTimeBeforeAlert(ArrayList<Integer> a) throws SQLException {
		ResultSet rs = this.selectSensor();
		rs.next();
		while(rs.getInt(3) > a.size())	{
			a.add(0);
		}
		return a;
	}
	
	public ResultSet selectSensor() throws SQLException {
		Connection c = data.takeConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM sensor WHERE id_sensor = " + i + ";");
		data.returnConnection(c);
		return rs;
	}
	
	public ResultSet selectStatements() throws SQLException {
		Connection con = data.takeConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = stmt.executeQuery("SELECT * FROM sensor s, statements st WHERE s.id_sensor = st.id_sensor and s.id_sensor = " + i + ";");
		data.returnConnection(con);
		return rst;
	}
	
	public void sendResponse(Response rp) throws IOException, SQLException {
		msg = new Message();
		msg.sendMessage(out, server.serializeServeur(rp));
	}
	
	public int NumberLine() throws SQLException {
		Connection con = data.takeConnection();
		Statement stmt = con.createStatement();
		int nb = 0;
		ResultSet rst = stmt.executeQuery("SELECT count(*) FROM statements WHERE id_sensor =" + i + ";");
		while(rst.next()) {
			nb = rst.getInt(1);
		}
		data.returnConnection(con);
		return nb;	
	}
	
	//this method recovers the oldest line and delete it
	public void deleteRow() throws SQLException {
		Connection con = data.takeConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = stmt.executeQuery("SELECT MIN(date_heure) FROM statements WHERE id_sensor = " + i + ";");
		rst.next();
		Timestamp ts = rst.getTimestamp(1);
		stmt.executeUpdate("DELETE FROM statements WHERE id_sensor = " + i + " AND date_heure = '" + ts +"';");
		stmt.close();
		data.returnConnection(con);
	}
}
