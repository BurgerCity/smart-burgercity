package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;
import common.Request;
import common.Response;

public class Server {
	private ServerSocket s;
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private String select;
	private Request r;
	private Response rp;
	private ObjectMapper objectMapper;
	private String rpAsString;
	private boolean b = true;
	private Message msg;
	private Crud crud; 
	
	public void start(int port) throws IOException, SQLException, ClassNotFoundException {
		s = new ServerSocket(port);
		clientSocket = s.accept();
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		msg = new Message();
		b = true;
		crud = new Crud();
		while(b == true) {
			System.out.println("server is waiting");
			r = this.deserialize(msg.readMessage(in));
			this.launchCrud(r, crud);
			
			msg.sendMessage(out, this.serializeServeur(r.getOperation_type()));
			if(r.getOperation_type().equals("STOP")) {
				clientSocket.close();
			}
		}
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server s = new Server();
		try {
			while(true) s.start(2013);
		} catch (Exception e) {
			s.close();
		}
	}
	
	public void launchCrud(Request r, Crud crud) throws SQLException, JsonGenerationException, JsonMappingException, IOException, ClassNotFoundException {
		
		System.out.println(r.getOperation_type());
		if(r.getOperation_type().equals("SELECT")) {
			select = crud.select(r.getTable());
		}
		else if(r.getOperation_type().equals("INSERT")) {
			select = crud.insert(r.getFirstname(), r.getLastname());

		}
		else if(r.getOperation_type().equals("UPDATE")) {
			select = crud.update(r.getLastname(), r.getFirstname(), r.getId());
		}
		else if(r.getOperation_type().equals("DELETE")) {
			select = crud.delete(r.getId());
		}
		else if(r.getOperation_type().equals("STOP")) {
			crud.closeConnection();
			select = "operation ends";
		}
	}

	
	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		s.close();
	}
	
	public Request deserialize(String json) throws JsonMappingException, JsonProcessingException {
		objectMapper = new ObjectMapper();
		r = objectMapper.readValue(json, Request.class);
		return r;
	}
	
	
	public String serializeServeur(String type) throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		rp = new Response();
		rp.setSuccessfulOperation(true);
		rp.setTypeOperation(type);
		if(select.equals("The identifier doesn't exist")) rp.setSuccessfulOperation(false);
		rp.setSelect(select);
		rpAsString = objectMapper.writeValueAsString(rp);
		return rpAsString;
	}
}
