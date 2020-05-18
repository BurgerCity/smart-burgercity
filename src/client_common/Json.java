package client_common;

import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.Client_socket;
import common.Message;
import common.Request;
import common.Response;

public class Json {
	Response rp;
	Request rq;
	ObjectMapper objectMapper;
	String rqAsString;
	Client_socket client;
	public Json(Client_socket client){
		this.client=client;
	}
	public Response deserialize(String respJson) throws JsonMappingException, JsonProcessingException {
		rp = new Response();
		objectMapper = new ObjectMapper();
		rp =  objectMapper.readValue(respJson, Response.class);
		System.out.println(rp.getTypeOperation());
		return rp;
	}
	
	public String serialize(Request rq) throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		objectMapper = new ObjectMapper();
		rqAsString = objectMapper.writeValueAsString(rq);
		return rqAsString;
	}
	public void sendRequest(Request r) {
		Message msg = new Message();
		String s = "";
		try {
			s = this.serialize(r);
			System.out.println(s);
			msg.sendMessage(client.getOut(), s);
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}
