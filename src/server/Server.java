package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Request;
import common.Response;

public class Server {
	ServerSocket s;
	Socket c;
	PrintWriter out;
	BufferedReader in;
	ObjectMapper objectMapper;
	Crud crud;
	String select;
	Request rq;
	int k = 0;
	public void start(int port) throws IOException, SQLException {
		s = new ServerSocket(port);
		c = s.accept();
		out = new PrintWriter(c.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		objectMapper = new ObjectMapper();
		crud = new Crud();
		String json = in.readLine();
		rq =  objectMapper.readValue(json, Request.class);
		if(rq.getOperation_type().equals("SELECT")) {
			k = 1;
			String select = crud.select(rq.getTable());
			this.serializeServeur("SELECT");
		}
		else if(rq.getOperation_type().equals("INSERT")) {
			crud.insert(rq.getFirstname(), rq.getLastname());
			this.serializeServeur("INSERT");
		}
		else if(rq.getOperation_type().equals("UPDATE")) {
			crud.update(rq.getLastname(), rq.getFirstname(), rq.getId());
			this.serializeServeur("UPDATE");
		}
		else if(rq.getOperation_type().equals("DELETE")) {
			crud.delete(rq.getId());
			this.serializeServeur("DELETE");
		}
		else if(rq.getOperation_type().equals("STOP")) {
			crud.closeConnection();
			this.serializeServeur("STOP");
		}
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		c.close();
		s.close();
	}
	
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		try {
			int c = 2011;
			s.start(c);
		} catch (Exception e) {
			s.close();
		}
	}
	
	public void serializeServeur(String type) throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		Response rp = new Response(true, type);
		if(k == 1) {
			rp.setSelect(select);
		}

		//objectMapper.writeValue(new File("response.json"), rp);
		String rpAsString = objectMapper.writeValueAsString(rp);
		out.write(rpAsString + "\n");
	}
}
