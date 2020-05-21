package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.Response;

/**
 * 
 * @author Idriss Zerai
 *
 */

public class Thread_car extends Thread {
	private Crud crud;
	private DataSource data;
	private int maxCar=0;
	private boolean t=true;
	public Thread_car(Crud crud,DataSource data) throws ClassNotFoundException{
		this.crud=crud;
		this.data=data;
	}
	
	public void run() {
		while(true) {
			while(t) {
			try {				
				launchalert(data);				
			} catch (SQLException e) {
				System.out.println("error count car");
			}}
		}
			
	}
	public int carsupervisor(DataSource data) throws SQLException {
		Crud crud=new Crud();
		maxCar=maxcargetter(data);
		int n=0;
		int a=0;
			Response rp=crud.countcar(data);
			a=Integer.parseInt(rp.getA().get(0));
		
		if(a>=maxCar*0.7 && a<=maxCar*0.9) {
			n=1;
		}
		else if(a>=maxCar*0.9) {
			n=2;
		}
		return n;
	}
	
	public void launchalert(DataSource data) throws SQLException {
		Crud crud=new Crud();
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		for(int i=0;i<3;i++) {
		if(carsupervisor(data)==i) {
			stmt.executeUpdate("UPDATE alertcar SET alert="+i+";");
			}
		}
		stmt.close();
		data.returnConnection(c);
	}
	public int maxcargetter(DataSource data) throws SQLException{
		int n=0;
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("SELECT maxcar from alertcar;");
		while(rslt.next()) {
			n=(rslt.getInt(1));
		}
		
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return n;
	}
	public boolean isT() {
		return t;
	}
	public void setT(boolean t) {
		this.t = t;
	}

	public int getMaxCar() {
		return maxCar;
	}

	public void setMaxCar(int maxCar) {
		this.maxCar = maxCar;
	}

}
