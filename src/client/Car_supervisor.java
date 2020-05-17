package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.databind.ObjectMapper;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;
import server.*;

public class Car_supervisor {
	Client_socket client;
	
	Car_supervisor(Client_socket c){
		this.client=c;
	}
	public int currcar() throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		//calculates the number of cars in the town by using the data base
		Request r=new Request();
		r.setOperation_type("COUNT_CAR");
		r.setTable("car");
		r.getA().add("0");
		Message m=new Message();
		this.sendRequest(r);
		Json j=new Json();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		rp=j.deserialize(stt);
		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
	public void sendRequest(Request r) {
		Message msg = new Message();
		Json json = new Json();
		String s = "";
		try {
			s = json.serialize(r);
			System.out.println(s);
			msg.sendMessage(client.getOut(), s);
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}

	
