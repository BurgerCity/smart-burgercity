package client;

import java.io.IOException;

import java.sql.SQLException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;
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
/**
 * 
 * @author tarshiniparameswaran
 * 
 * This class give us the results of the request ask to the database
 *
 */
public class Indicator {
	Client_socket client;
	
	Indicator(Client_socket c){
		this.client=c;
	}
	//its the number of the maximum cars allowed in town 
	public int car() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("CARMAX");
		r.setTable("car");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		//System.out.println("dfgh");
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		//System.out.println("RÉSULTAT CAR : "+n);
		return n;
	}
	// count the captor numbers
	public int captor() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("CAPTORS");
		r.setTable("Ssensor");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println("RÉSULTAT CAPTOR : "+n);
		return n;
	}
	//count the bounds
	public int borne() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("BORNES");
		r.setTable("bound");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println("RÉSULTAT BORNES : "+n);
		return n;
	}
	//count the tram stations
	public int tram() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TRAMS");
		r.setTable("network");
	//	r.setDate("");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println("RÉSULTAT TRAM : "+n);
		return n;
	}
	// calculate an average of carbonfootprint for a date
	public int emp(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n = 0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("EMPC");
		r.setTable("carbonfootprint");
		r.getA().add(s);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		System.out.println(rp);
		if ( rp.getA().size() != 0) {
			 n = Integer.parseInt(rp.getA().get(0));

		} else {
			n = 0;
		}
		
		return n;
	}
	// calculate how many cars are in the town
	public int carinthetown(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("INTHETOWN");
		r.setTable("car");
	//	r.setDate(s);
		r.getA().add(s);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		if ( rp.getA().size() != 0) {
			 n = Integer.parseInt(rp.getA().get(0));

		} else {
			n = 0;
		}
		
		return n;
	}
	// its an average of the polluant result of the town 
	public Float tpac(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		float n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("POLL");
		r.setTable("statements");
	//	r.setDate(s);
		r.getA().add(s);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		if ( rp.getA().size() != 0) {
			 n = Float.parseFloat(rp.getA().get(0));

		} else {
			n = 0;
		}
		
		return n;
	}
	//its the theresold alert
	public Double tpbc() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Double n=0.0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("THERE");
		r.setTable("sensor");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Double.parseDouble(rp.getA().get(0));
		//System.out.println("RÉSULTAT TPBC : "+n);
		return n;
	}
	// it calcule the pollution rate for one day
	public Double tp(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n;
		if (a.tpac(s)==0.0) { n=0.0;}else {
		 n = a.tpac(s) / a.tpbc();
		}
		return n;
	}
	// it calcule the exceeding pollution rate for day
	public Double td(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n;
		if (a.tp(s)==0.0) { n=0.0;}else {
		Double b =a.tp(s) -1;
		 n = b*100;
		}
	
		return n;
	}
	
	
	// it calculate the pollution rate between a periode
	public Double tpacp(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Double n=0.0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("POLLPERI");
		r.getA().add(s);
		r.getA().add(s1);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		if ( rp.getA().size() != 0) {
			 n = Double.parseDouble(rp.getA().get(0));
		} else {
			n = 0.0;
		}
		return n;
	}
	// it calculate the pollution rate in a localization
	public Double tpn(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n = a.tpacp(s,s1) / a.tpbc();
		return n;
	}
	//it calculate the exceeding pollution rate in a localization
	public Double tdn(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n;
		if (a.tpn(s,s1)==0.0) { n=0.0;}else {
		Double b = (a.tpn(s,s1) - 1);
		 n = b*100;
		}
		return n;
	}
	
	
	// it calculate the number of the car in the town in a date
	public int carinthetowndate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("CARINPERI");
		r.getA().add(s);
		r.getA().add(s1);
		r.setTable("car");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		if ( rp.getA().size() != 0) {
			 n = Integer.parseInt(rp.getA().get(0));

		} else {
			n = 0;
		}
		return n;
	}
	//it calculate the carbonfootprint between a periode 
	public int empdate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("EMPDATE");
		r.setTable("carbonfootprint");
		r.getA().add(s);
		r.getA().add(s1);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		if ( rp.getA().size() != 0) {
			 n = Integer.parseInt(rp.getA().get(0));

		} else {
			n = 0;
		}
		return n;
	}
	// its calculate polluant average in a periode
	public Double tpadate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Double n=0.0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TPDATE");
		r.setTable("statements");
		r.getA().add(s);
		r.getA().add(s1);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		if ( rp.getA().size() != 0) {
			 n = Double.parseDouble(rp.getA().get(0));
		} else {
			n = 0.0;
		}
		//System.out.println(n);
		return n;
	}
	//its the  pollution rate in a periode
	public Double tpdate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n ;
		if (a.tpn(s,s1)==0.0) { n=0.0;}else {
		 n = a.tpadate(s,s1) / a.tpbc();
		System.out.println("RÉSULTAT TAUX DE POLLUTION  DATE : "+n);}
		return n;
	}
	//its the exceeding pollution rate in a periode
	public Double tddate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n ;
		if (a.tpdate(s,s1)==0.0) { n=0.0;}else {
		Double b =a.tpdate(s,s1) -1;
		 n = b*100;}
		return n;
	}
	//its an array of the date which are in the db
	 public ArrayList<String>  tab(String s,String s1)throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		 ArrayList<String> n;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TAB");
		r.setTable("statements");
		r.getA().add(s);
		r.getA().add(s1);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=rp.getA();
		for(String elem: n)
        {
        	//System.out.println (elem + "date boucle" );

        }		
		return n;
	}
	
	 public boolean ok(String s)  throws SQLException, ClassNotFoundException, IOException, InterruptedException {
	    	boolean x = false;
	    	if( this.emp(s) != 0 || this.carinthetown(s) != 0 || this.tp(s)  != 0.0 || this.td(s)  != 0.0 ) {
	    	x = true;
	    	}
	    	//System.out.println(x);
			return x;
	  }

	  public boolean okdate(String s,String s1)  throws SQLException, ClassNotFoundException, IOException, InterruptedException {
	    	boolean x = false;
	    	if( this.empdate(s,s1) != 0 || this.carinthetowndate(s,s1) != 0 || this.tpdate(s,s1)  != 0 || this.tddate(s,s1)  != 0) {
	    		x = true;
	    	}
	    	//System.out.println(x);
			return x;
	    }
	  
	  

}