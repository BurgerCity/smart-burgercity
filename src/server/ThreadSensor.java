package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import sun.security.action.GetIntegerAction;

public class ThreadSensor extends Thread{
	private int i;
	public ThreadSensor(int i) {
		this.i = i;
	}
	
	public void run() {
		//ajouter la methode qu iva exec le thread
		StatementSensor ss = new StatementSensor();
		Connection c = ss.createConnection();
		try {	
			Statement s = c.createStatement();
			while(true) {
					ResultSet rs = s.executeQuery("SELECT * FROM sensor WHERE id_sensor = " + i + ";");
				while(rs.next()) {
					Thread.sleep(rs.getInt(4) * 1000);
					
					this.testAlert();
					//Remplir la table n fois  			n est un multiple du statement
					//si n est dépassé alors on supprime une ligne avant d'inserer la suivante que l'on va tester pour savoir s'il y a une alert
				}
			}
		} catch (SQLException | InterruptedException e) {
		}
	}
	
	public Boolean testAlert() throws SQLException {
		StatementSensor ssr = new StatementSensor();
		Connection con = ssr.createConnection();
		Statement stmt = con.createStatement();
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
		return true;
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
		StatementSensor ss = new StatementSensor();
		Connection c = ss.createConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM sensor WHERE id_sensor = " + i + ";");
		return rs;
		
	}
	
	public ResultSet selectStatements() throws SQLException {
		StatementSensor ssr = new StatementSensor();
		Connection con = ssr.createConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = stmt.executeQuery("SELECT * FROM sensor s, statements st WHERE s.id_sensor = st.id_sensor and s.id_sensor = " + i + ";");
		return rst;
	}
}
