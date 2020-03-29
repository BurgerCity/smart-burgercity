package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Request;
import common.Response;

public class Server {
	private ServerSocket s;
	private Socket clientSocket;
	//private PrintWriter out;
	private OutputStreamWriter out;
	private BufferedReader in;
	private String select;
	private Request r;
	private Response rp;
	private ObjectMapper objectMapper;
	private String rpAsString;
	private String json;
	private boolean b = true;
	
	public void start(int port, Crud crud) throws IOException, SQLException, ClassNotFoundException {
		s = new ServerSocket(port);
		clientSocket = s.accept();
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		b = true;
		while(b == true) {
			System.out.println("serveur en attente");
			json = in.readLine();
			
			r = this.deserialize(json);
			this.launchCrud(r, crud);
			
			out.write(this.serializeServeur(r.getOperation_type()) + "\n");
			out.flush();
			if(r.getOperation_type().equals("STOP")) {
				clientSocket.close();
			}
		}
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server s = new Server();
		Crud crud = new Crud();
		try {
			s.start(2013, crud);
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
		if(select.equals("L'identifiant n'existe pas")) rp.setSuccessfulOperation(false);
		rp.setSelect(select);
		rpAsString = objectMapper.writeValueAsString(rp);
		return rpAsString;
	}
}
