package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;
import sun.security.action.GetIntegerAction;

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
	public ThreadSensor(int i, Socket s, DataSource data, Crud crud) {
		this.i = i;
		this.socket = s;
		this.data = data;
		this.crud = crud;
	}
	
	public void run() {
		//ajouter la methode qu iva exec le thread
		Connection c = data.takeConnection();
		try {	
			Statement s = c.createStatement();
			crud = new Crud();
			this.connectionSensor(socket);
			server = new Server();
			r = new Request();
			msg = new Message();
			r = server.deserialize(msg.readMessage(in));
			System.out.println(r.getOperation_type());
			//while(true) {
					
					ResultSet rs = s.executeQuery("SELECT * FROM sensor WHERE id_sensor = " + i + ";");
					ResultSetMetaData rmd = rs.getMetaData();
					Response rp = new Response();
					while(rs.next()) {
						for(int j = 1; j <= rmd.getColumnCount(); j++) {
							if(rmd.getColumnTypeName(j) == "varchar") {
								rp.getA().add(rs.getString(j));
							} else {
								rp.getA().add(Integer.toString(rs.getInt(j)));
							}
							rp.setTypeOperation(Integer.toString(i));
						}
					}
						System.out.println(rp.getA());
						this.sendResponse(rp);
						while(true) {
							r = new Request();
							System.out.println("je suis al");
							r = server.deserialize(msg.readMessage(in));
							System.out.println(r.getA());
							r.getA().add("now()");
							r.setTable("statements");
							r.getA().add("1");
							System.out.println(r.getTable());
							crud.insert(r, data);
							//Thread.sleep(rs.getInt(4) * 1000);
							//System.out.println("i sleep" + rs.getInt(4) * 1000);
							
							this.testAlert();
							//Remplir la table n fois  			n est un multiple du statement
							//si n est dépassé alors on supprime une ligne avant d'inserer la suivante que l'on va tester pour savoir s'il y a une alert
						}
					//}
			//}
		} catch (SQLException | /*InterruptedException |*/ IOException e) {}
	}
	
	public Boolean testAlert() throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		ResultSet rst = stmt.executeQuery("SELECT * FROM sensor s, statements st WHERE s.id_sensor = st.id_sensor and s.id_sensor = " + i + ";");
	
		ArrayList<Boolean> a = new ArrayList<Boolean>();
		while(rst.next()) {
			double average = (rst.getInt(5) * 0.3 + rst.getDouble(7) * 0.1 + rst.getDouble(9) * 0.3 + rst.getInt(11) * 0.3) / (rst.getInt(5) + rst.getDouble(7) + rst.getDouble(9) + rst.getInt(11));
			double test = (rst.getInt(15) * 0.3 + rst.getDouble(16) * 0.1 + rst.getDouble(17) * 0.3 + rst.getInt(18) * 0.3) / (rst.getInt(15) + rst.getDouble	(16) + rst.getDouble(17) + rst.getInt(18));
			System.out.println(rst.getInt(15) + " " + rst.getDouble(16)+ " " + rst.getDouble(17)+ " " + rst.getInt(18));
			System.out.println(rst.getInt(5) + " " + rst.getDouble(7)+ " " + rst.getDouble(9)+ " " + rst.getInt(11));
			int k = 9;	
			int cpt = 0;
			if(test >= average) {
				a.add(true);
				System.out.println(test + "   " + average);
			}
			else {
				for(int j = 6; j < 10; j++) {
					if(rst.getDouble(j + cpt) <= rst.getDouble(j + k)) {
						a.add(true);			
						System.out.println(rst.getDouble(j + cpt) + "    " + rst.getDouble(j + k));
						break;
					}
					cpt = cpt + 1;
				}
				
			}
		}
		
	/*	ResultSet rs = this.selectSensor();
		rs.next();
		while(rs.getInt(3) > a.size())	{
			System.out.println(rs.getInt(3) + "  "+ a.size());
			a.add(false);
		}*/
		
		a = this.testTimeBeforeAlert(a);
		System.out.println(this.alert(a));
		data.returnConnection(c);
		return true;
	}
	
	public void connectionSensor(Socket socket) throws IOException {
		out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	}
	
	public Boolean alert(ArrayList<Boolean> a) {
		Iterator<Boolean> it = a.iterator();
		while(it.hasNext()) {
			Boolean b = it.next();
			if(b == false) return false;
		}
		return true;
	}
	
	public ArrayList<Boolean> testTimeBeforeAlert(ArrayList<Boolean> a) throws SQLException {
		ResultSet rs = this.selectSensor();
		rs.next();
		while(rs.getInt(3) > a.size())	{
			a.add(false);
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
	
	/*public Connection createConnection() {
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
	}*/
}
