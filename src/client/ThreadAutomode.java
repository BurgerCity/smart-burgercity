package client;

import java.io.IOException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

public class ThreadAutomode extends Thread {
	BoundMainPanel bmp;
	Client_socket client;
	ThreadAutomode(Client_socket client){
		this.client=client;
	}
	public void run() {
		while(true) 
		try{
		if(borne(1)) {
			bmp.getB2().borne1.setText("ouvert");
		}else bmp.getB2().borne1.setText("fermé");
		if(borne(2)) {
			bmp.getB2().borne2.setText("ouvert");
		}else bmp.getB2().borne2.setText("fermé");
		if(borne(3)) {
			bmp.getB2().borne3.setText("ouvert");
		}else bmp.getB2().borne3.setText("fermé");
		if(borne(4)) {
			bmp.getB2().borne4.setText("ouvert");
		}else bmp.getB2().borne4.setText("fermé");
		if(borne(5)) {
			bmp.getB2().borne5.setText("ouvert");
		}else bmp.getB2().borne5.setText("fermé");
		if(borne(6)) {
			bmp.getB2().borne6.setText("ouvert");
		}else bmp.getB2().borne6.setText("fermé");
		if(borne(7)) {
			bmp.getB2().borne7.setText("ouvert");
		}else bmp.getB2().borne7.setText("fermé");
		if(borne(8)) {
			bmp.getB2().borne8.setText("ouvert");
		}else bmp.getB2().borne8.setText("fermé");
		Thread.sleep((long) 1000);
		}
		
		catch(Exception e) {}
		
	}

	public boolean borne(int id) throws IOException {
		boolean b=true;
		Request r=new Request();
		Json j=new Json();
		r.setOperation_type("SELECT_BOUND_STATUS");
		r.setTable("bound");
		r.getA().add(""+id);
		j.sendRequest(r);
		Response rp=new Response();
		Message m=new Message();
		rp=j.deserialize(m.readMessage(client.getIn()));
		b=Boolean.parseBoolean(rp.getA().get(0));
		return b;
	}

}
