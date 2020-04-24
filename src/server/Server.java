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
import common.Sensor;
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
	private Message msg;
	private Crud crud;
	private static DataSource data;
	private Sensor sens;
	
	public void start(Socket clientSocket) throws IOException, SQLException, ClassNotFoundException {

		while(!clientSocket.isClosed()) {
			try {
				out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
				msg = new Message();
				crud = new Crud();
				r = new Request();
				sens = new Sensor();
					while(true) {
						r = this.deserialize(msg.readMessage(in));
						this.launchCrud(r, crud, data);
						
						msg.sendMessage(out, this.serializeServeur(r.getOperation_type()));
						if(r.getOperation_type().equals("STOP")) {
							this.closeClient();
						}
					}
				
			} catch(Exception e) {}
		}
	}
		
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server s = new Server();
		ServerSocket serverSocket = s.startServer(2013);
		try {
			while(true) {	
				Socket clientSocket = serverSocket.accept();
				new Thread(new ThreadClient(clientSocket)).start();
			}
		} catch (Exception e) {
			serverSocket.close();
		}
	}
	
	public ServerSocket startServer(int port) throws IOException, ClassNotFoundException {
		s = new ServerSocket(port);
		data = new DataSource();
		return s;
	}
		
	public Socket openSocket(ServerSocket s) throws IOException {
		clientSocket = s.accept();
		return clientSocket;
	}
	public void launchCrud(Request r, Crud crud, DataSource data) throws SQLException, JsonGenerationException, JsonMappingException, IOException, ClassNotFoundException {
		
		if(r.getOperation_type().equals("SELECT")) {
			select = crud.select(r.getTable(), data);
		}
		else if(r.getOperation_type().equals("INSERTSENSOR")) {
			select = crud.insertSensor(r, data);
		}
		else if(r.getOperation_type().equals("UPDATE")) {
			select = crud.update(r.getLastname(), r.getFirstname(), r.getId(), data);
		}else if(r.getOperation_type().equals("INSERT")) {
			select = crud.insert(r.getFirstname(), r.getLastname(), data);
		}
		else if(r.getOperation_type().equals("DELETE")) {
			select = crud.delete(r.getId(), data);
		}
	}

	
	public void closeClient() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
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
