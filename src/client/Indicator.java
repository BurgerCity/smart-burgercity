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
import sun.security.action.GetIntegerAction;

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
		r.setTable("setting");
		r.getA().add("0");
		r.setDate("");

	//	System.out.println("dfgh");
		j.sendRequest(r);
	//	System.out.println("finis");

		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		System.out.println("dfgh");
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	/*public int idclient() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("IDCLIENT");
		r.setTable("Client");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}*/
	
	public int captor() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("CAPTORS");
		r.setTable("Sensor");
		r.setDate("");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public int borne() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("BORNES");
		r.setTable("gatecontrol");
		r.setDate("");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public int tram() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TRAMS");
		r.setTable("tram");
		r.setDate("");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public Float emp(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Float n;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("EMPC");
		//r.getA().add("0");
		r.setTable("Empreintecarborne");
		r.setDate(s);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Float.parseFloat(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public int carinthetown(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("INTHETOWN");
		r.setTable("Car");
		r.setDate(s);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public float tpac(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		float n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("POLL");
		r.setDate(s);
		//r.setTable("car");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Float.parseFloat(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public float tpbc() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		float n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("THERE");
		r.setTable("setting");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Float.parseFloat(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public float tp(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		float n = a.tpac(s) / a.tpbc();
		System.out.println("taux de poll"+n);

		return n;
	}
	
	public float td(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		float b =a.tp(s) -1;
		float n = b*100;
		System.out.println("depassement"+n);

		return n;
	}
	
	
	
	public float tpacp(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		float n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("POLLPERI");
		r.setDate(s);
		r.setPos(s1);
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Float.parseFloat(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	
	public float tpn(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		float n = a.tpacp(s,s1) / a.tpbc();
		System.out.println("pollution secteur"+n);
		return n;
	}
	
	public float tdn(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		float b =a.tpn(s,s1) -1;
		float n = b*100;
		System.out.println("depasse secteur"+n);
		return n;
	}
	
	public int carinthetowndate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("CARINPERI");
		r.setDate(s);
		r.setDate2(s1);
		r.setTable("car");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println("car in the town between a periode"+n);
		return n;
	}
	
	public float empdate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		float n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("EMPDATE");
		r.setDate(s);
		r.setDate2(s1);
		r.setTable("Empreintecarbone");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Float.parseFloat(rp.getA().get(0));
		System.out.println("emp carb between a periode"+n);
		return n;
	}
	
	public float tpadate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		float n=0;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TPDATE");
		r.setDate(s);
		r.setDate2(s1);
		//r.setTable("Empreintecarbone");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Float.parseFloat(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	public float tpdate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		float n = a.tpadate(s,s1) / a.tpbc();
		System.out.println("pollution date"+n);
		return n;
	}
	
	public float tddate(String s,String s1) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Indicator a = new Indicator(client);
		float b =a.tpdate(s,s1) -1;
		float n = b*100;
		System.out.println("depasse date"+n);
		return n;
	}
	
	 public ArrayList<String>  tab(String s,String s1)throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		 ArrayList<String> n;
		Request r = new Request();
		Json j=new Json(client);
		r.setOperation_type("TAB");
		r.setDate(s);
		r.setDate2(s1);
		//r.setTable("Empreintecarbone");
		j.sendRequest(r);
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=rp.getA();
		for(String elem: n)
        {
        	System.out.println (elem + "date boucle" );

        }		
		return n;
	}
	

}