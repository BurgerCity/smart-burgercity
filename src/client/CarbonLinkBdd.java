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
		this.client.startConnection("127.0.0.1", 2015);
		RequestResult();
		
		
	
	}
	

	public void RequestResult() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		//int[] ValueRequest = new ValueRequest[2];
		int n = 0;
		Request r = new Request();
		Json j = new Json(client);
		//System.out.println("Creation de la Request");
		r.setOperation_type("CarbonRequest");
		r.setTable("client");
		r.getA().add("surface");
		r.getA().add("nbstationtram");
	    //System.out.println("Envoi de la Request");
		j.sendRequest(r);
		//System.out.println("Request envoyer");
		Message m = new Message();
		Response rp = new Response();
		//System.out.println(rp);
		//System.out.println("Lecture du Client.getIn : ");
		String st = m.readMessage(client.getIn());
		//System.out.println("st : " +st);
		rp = j.deserialize(st);
		//System.out.println("st apres deserialize " + rp.getA().get(0));
		//System.out.println("st apres deserialize " + rp.getA().get(1));
		//n = Integer.parseInt(rp.getA().get(1));
		//System.out.println(n);
		//System.out.println("Lecture effectué");
		this.valueRequest[0] = Integer.parseInt(rp.getA().get(0)); 
		valueRequest[1] = Integer.parseInt(rp.getA().get(1));
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
		System.out.println(clb.valueRequest[0] + "," + clb.valueRequest[1]);
	}
	
}
