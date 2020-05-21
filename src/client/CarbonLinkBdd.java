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
		this.client.startConnection("172.31.249.164", 2015);
		RequestResult();
}
	

	public void RequestResult() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n = 0;
		Request r = new Request();
		Json j = new Json(client);
		r.setOperation_type("CarbonSelect");
		r.setTable("city");
		r.getA().add("surface");
		r.getA().add("nbstationtram");
		j.sendRequest(r);
		System.out.println("Envoi de la Requête : ");
		Message m = new Message();
		Response rp = new Response();
		System.out.println("Lecture du Client.getIn : ");
		String st = m.readMessage(client.getIn());
		rp = j.deserialize(st);
		int size = rp.getA().size();
		valueRequest[0] = Integer.parseInt(rp.getA().get(size - 2)); 
		valueRequest[1] = Integer.parseInt(rp.getA().get(size - 1));
	}
	public void RequestInsert(String ec) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		Message msg = new Message();
		Request r = new Request();
		Json j = new Json();
		r.setOperation_type("INSERT");
		r.setTable("carbonfootprint");
		r.getA().add(ec);
		r.getA().add("now()");
		r.getA().add("1");
		msg.sendMessage(client.getOut(), j.serialize(r));
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		CarbonLinkBdd clb = new CarbonLinkBdd();
	}
	
}
