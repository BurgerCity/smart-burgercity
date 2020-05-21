package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.Request;

public class Thread_bounds extends Thread {
	ArrayList<Integer> alerts;
	ArrayList<Integer> alertcar;
	int i=0;

	public void setAlerts(ArrayList<Integer> alerts) {
		this.alerts = alerts;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	public void setData(DataSource data) {
		this.data = data;
	}
	public void setCrud(Crud crud) {
		this.crud = crud;
	}
	public void setT(boolean t) {
		this.t = t;
	}
	Socket s; 
	DataSource data; 
	Crud crud;
	boolean t=true;
	Thread_bounds(){
		alerts=new ArrayList<Integer>();
		alertcar=new ArrayList<Integer>();
	}
	public void run(){
		while(true)
		while(t) {			
			try {
				this.boundRegulator(this.getalert(data),this.getalertcar(data),data); 
				alerts.clear();
				alertcar.clear();
			} catch (SQLException e) {
				e.printStackTrace();
			
			}
		}
	}
	public ArrayList getalert(DataSource data) throws SQLException {
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("Select alert from sensor ;");
		while(rslt.next()) {
			alerts.add(rslt.getInt(1));
		}
		stmt.close();	
		rslt.close();
		data.returnConnection(c);
		return alerts;
		}
	public ArrayList<Integer> getalertcar(DataSource data) throws SQLException {
		Integer i;
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rsl=stmt.executeQuery("Select alert from alertcar;");
		while(rsl.next()) {
			i=rsl.getInt(1);
			alertcar.add(i);
		}
		stmt.close();	
		rsl.close();
		data.returnConnection(c);
		return alertcar;
		}
	
	public void boundRegulator(ArrayList<Integer> alerts,ArrayList<Integer> alertcar,DataSource data) throws SQLException {
		Crud crud=new Crud();
		Connection c = data.takeConnection();
		Connection c2 = data.takeConnection();

		Statement stmt = c.createStatement();
		Statement st=c2.createStatement();
		if(alerts.contains(2) || alertcar.contains(2)) {
			stmt.executeUpdate("UPDATE bound SET status=false ;");
			System.out.println(alertcar.get(0));
			i=2;

		}
		else if(alerts.contains(1) || alertcar.contains(1)){
			stmt.executeUpdate("UPDATE bound SET status=false where var=1 ;");
			st.executeUpdate("UPDATE bound SET status=true where var=2 ;");

			i=1;
		}
		else {stmt.executeUpdate("UPDATE bound SET status=true ;");
		i=0;
		}
		System.out.println(i);
		stmt.close();	
		st.close();
		data.returnConnection(c2);
		data.returnConnection(c);
	}
}
