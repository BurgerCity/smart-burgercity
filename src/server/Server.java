package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
	ServerSocket s;
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	Crud crud;
	String select;
	Request r;
	Response rp;
	ObjectMapper objectMapper;
	String rpAsString;
	String json;
	int k = 0;
	boolean b = true;
	public void start(int port, Crud crud) throws IOException, SQLException, ClassNotFoundException {
		s = new ServerSocket(port);
		clientSocket = s.accept();
		System.out.println("serveur en attente");
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("serveur avant de lire");
		b = true;
		while(b == true) {
			clientSocket.setSoTimeout(100000);
			json = in.readLine();
			System.out.println(json);
			
			r = this.deserialize(json);
			this.launchCrud(r, crud);
			if(r.getOperation_type().equals("STOP")) {
				b = false;
				out.println(this.serializeServeur(r.getOperation_type()));
				out.flush();
				clientSocket.close();
				
			} else {
				out.println(this.serializeServeur(r.getOperation_type()));
				out.flush();
			}

		}
		
	}
	
	public void startServer(int port) throws IOException {

	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server s = new Server();
		Crud crud = new Crud();
		try {
			s.start(2013, crud);
		} catch (Exception e) {
			//s.close();
		}
	}
	
	public void launchCrud(Request r, Crud crud) throws SQLException, JsonGenerationException, JsonMappingException, IOException, ClassNotFoundException {
		
		System.out.println(r.getOperation_type());
		if(r.getOperation_type().equals("SELECT")) {
			k = 1;
			select = crud.select(r.getTable());
			//this.serializeServeur("SELECT");
		}
		else if(r.getOperation_type().equals("INSERT")) {
			crud.insert(r.getFirstname(), r.getLastname());
			//this.serializeServeur("INSERT");
		}
		else if(r.getOperation_type().equals("UPDATE")) {
			k = 1;
			select = crud.update(r.getLastname(), r.getFirstname(), r.getId());
			//this.serializeServeur("UPDATE");
		}
		else if(r.getOperation_type().equals("DELETE")) {
			crud.delete(r.getId());
			//this.serializeServeur("DELETE");
		}
		else if(r.getOperation_type().equals("STOP")) {
			crud.closeConnection();
			//this.serializeServeur("STOP");
			//data.returnConnection(c);
			//data.closeC();
		} else {
			System.out.println("Crud ne se fait pas");
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
		//objectMapper = new ObjectMapper();
		if(select.equals("L'identifiant n'existe pas")) rp.setSuccessfulOperation(false);
		if(k == 1) {
			rp.setSelect(select);
		}
		//objectMapper.writeValue(new File("response.json"), rp);
		rpAsString = objectMapper.writeValueAsString(rp);
		System.out.println(rpAsString);
		return rpAsString;
	}
}
