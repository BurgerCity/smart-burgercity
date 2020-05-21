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

public class Indicator {
	Client_socket client;
	
	Indicator(Client_socket c){
		this.client=c;
	}
	
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
	
	public Double tp(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n;
		if (a.tpac(s)==0.0) { n=0.0;}else {
		 n = a.tpac(s) / a.tpbc();
		System.out.println("RÉSULTAT TAUX DE POLLUTION : "+n);
		}
		return n;
	}
	
	public Double td(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n;
		if (a.tp(s)==0.0) { n=0.0;}else {
		Double b =a.tp(s) -1;
		 n = b*100;
		System.out.println("RÉSULTAT TAUX DE DEPASSEMENT DE POLLUTION : "+n);

		}
	
		return n;
	}
	
	
	
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
	
	public Double tpn(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n = a.tpacp(s,s1) / a.tpbc();
		System.out.println("RÉSULTAT TAUX DE POLLUTION SECTEUR 1: "+ a.tpacp(s,s1));
		System.out.println("RÉSULTAT TAUX DE POLLUTION SECTEUR 2: "+ a.tpbc());
		System.out.println("RÉSULTAT TAUX DE POLLUTION SECTEUR: "+n);
		return n;
	}
	
	public Double tdn(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n;
		if (a.tpn(s,s1)==0.0) { n=0.0;}else {
		Double b = (a.tpn(s,s1) - 1);
		 n = b*100;
		System.out.println("RÉSULTAT TAUX DE DEPASSEMENT POLLUTION SECTEUR : "+n);}
		return n;
	}
	
	
	
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
	
	public Double tpadate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Double n=0.0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TPDATE");
		r.setTable("statements");
	//	r.setDate(s);
	//	r.setDate2(s1);
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
	public Double tpdate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n ;
		if (a.tpn(s,s1)==0.0) { n=0.0;}else {
		 n = a.tpadate(s,s1) / a.tpbc();
		System.out.println("RÉSULTAT TAUX DE POLLUTION  DATE : "+n);}
		return n;
	}
	
	public Double tddate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		Double n ;
		if (a.tpdate(s,s1)==0.0) { n=0.0;}else {
		Double b =a.tpdate(s,s1) -1;
		 n = b*100;}
		//System.out.println("RÉSULTAT TAUX DE DEPASSEMENT POLLUTION DATE: "+n);
		return n;
	}
	
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