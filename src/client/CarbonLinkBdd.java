package client;

import java.io.IOException;
import java.sql.SQLException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;


public class CarbonLinkBdd {
	int[] valueRequest;
	
	private Client_socket client;
	
	CarbonLinkBdd() throws IOException, ClassNotFoundException, SQLException, InterruptedException{
		this.valueRequest = new int[2];
		this.client = new Client_socket();
		//this.client.startConnection("172.31.249.164", 2015);
		this.client.startConnection("172.31.249.164", 2015);
		RequestResult();
		
		
	
	}
	

	public void RequestResult() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		//int[] ValueRequest = new ValueRequest[2];
		int n = 0;
		Request r = new Request();
		Json j = new Json(client);
		//System.out.println("Creation de la Request");
		r.setOperation_type("CarbonSelect");
		r.setTable("city");
		r.getA().add("surface");
		r.getA().add("nbstationtram");
	    //System.out.println("Envoi de la Request");
		j.sendRequest(r);
		//System.out.println("Request envoyer");
		Message m = new Message();
		Response rp = new Response();
		//System.out.println(rp);
		System.out.println("Lecture du Client.getIn : ");
		String st = m.readMessage(client.getIn());
		System.out.println("st : " +st);
		rp = j.deserialize(st);
		//System.out.println("st apres deserialize " + rp.getA().get(0));
		//System.out.println("st apres deserialize " + rp.getA().get(1));
		//n = Integer.parseInt(rp.getA().get(1));
		//System.out.println(n);
		//System.out.println("Lecture effectué");
		//System.out.println(rp.getA().size());
		int size = rp.getA().size();
		System.out.println(size);
		valueRequest[0] = Integer.parseInt(rp.getA().get(size - 2)); 
		valueRequest[1] = Integer.parseInt(rp.getA().get(size - 1));
		System.out.println("v1 : " + valueRequest[0]);
		System.out.println("v2 : " + valueRequest[1]);
	}
	public void RequestInsert(String ec) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		//int[] ValueRequest = new ValueRequest[2];
		//String st = Integer.toString((int)f);
		Message msg = new Message();
		Request r = new Request();
		Json j = new Json();
		//System.out.println("Creation de la Request");
		r.setOperation_type("INSERT");
		r.setTable("carbonfootprint");
		//long millis = System.currentTimeMillis();
		//java.sql.Date date = new java.sql.Date(millis);
		//date = now();
		//String d = ""+date+"";
		r.getA().add(ec);
		r.getA().add("now()");
		r.getA().add("1");
	    //System.out.println("Envoi de la Request");
		//j.sendRequest(r);
		msg.sendMessage(client.getOut(), j.serialize(r));
		//System.out.println(client.getOut());
		//System.out.println(j.serialize(r));
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		/*
		Client_socket c = new Client_socket();
		c.startConnection("127.0.0.1", 2015);
		CarbonLinkBdd clb = new CarbonLinkBdd(c);
		clb.RequestResult();
		System.out.println(clb.valueRequest[0] + " , " + clb.valueRequest[1]);
		//System.out.println("sisi");
		*/
		CarbonLinkBdd clb = new CarbonLinkBdd();
		//clb.RequestResult();
		//System.out.println(clb.valueRequest[0] + "," + clb.valueRequest[1]);
		//float t = 200.01;
		//clb.RequestInsert(2777.099);
	}
	
}
