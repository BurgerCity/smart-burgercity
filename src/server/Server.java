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
	private Message msg;
	private Crud crud;
	private static DataSource data;
	

	
	public void start(Socket clientSocket) throws IOException, SQLException, ClassNotFoundException {

		while(!clientSocket.isClosed()) {
			try {
				out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
				msg = new Message();
				crud = new Crud();
				r = new Request();
					while(true) {
						r = this.deserialize(msg.readMessage(in));
						System.out.println(r.getOperation_type());
						rp = this.launchCrud(r, crud, data);
						System.out.println("aprescrud");
						msg.sendMessage(out, this.serializeServeur(rp));
						System.out.println("reponselancee");
						if(r.getOperation_type().equals("STOP")) {
							this.closeClient();
						}
					}
				
			} catch(Exception e) {}
		}
	}
		
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		Server s = new Server();
		ServerSocket serverSocket = s.startServer(2013);
		new Thread(new ThreadCollectData(serverSocket, s)).start();
		ServerSocket serverSocket2 = s.startServer(2015);
			try {
				while(true) {	
					Socket clientSocket = serverSocket2.accept();
					new Thread(new ThreadClient(clientSocket)).start();
				}
			} catch (Exception e) {
				serverSocket2.close();
			}
	}
	
	public ServerSocket startServer(int port) throws IOException, ClassNotFoundException {
		s = new ServerSocket(port);
		data = new DataSource();
		return s;
	}
	
	public void ThreadClientSocket(ServerSocket serverSocket2) throws IOException {
		try {
			while(true) {	
				Socket clientSocket = serverSocket2.accept();
				new Thread(new ThreadClient(clientSocket)).start();
			}
		} catch (Exception e) {
			serverSocket2.close();
		}
	}
	
	public void ThreadStatement(ServerSocket serverSocket2, Server s) throws IOException {
		try {
			while(true) {	
				Socket clientSocket = serverSocket2.accept();
				new Thread(new ThreadSensorSocket(clientSocket, data, crud, s)).start();
			}
		} catch (Exception e) {
			serverSocket2.close();
		}
	}
		
	public Socket openSocket(ServerSocket s) throws IOException {
		clientSocket = s.accept();
		return clientSocket;
	}
	public Response launchCrud(Request r, Crud crud, DataSource data) throws SQLException, JsonGenerationException, JsonMappingException, IOException, ClassNotFoundException {
		rp = new Response();

		if(r.getOperation_type().equals("SELECT")) {
			rp = crud.select(r, data);
		}
		else if(r.getOperation_type().equals("INSERT")) {
			crud.insert(r, data);
		}
		else if(r.getOperation_type().equals("UPDATE")) {
			crud.update(r, data);
		} else if(r.getOperation_type().equals("DELETE")) {
			crud.delete(r, data);
		}
		else if(r.getOperation_type().equals("COUNT_CAR")){
			rp=crud.countcar(data);
		}
		else if(r.getOperation_type().equals("CARMAX")){
			
			System.out.println("if");
			rp=crud.carmax(data);
		}
		else if(r.getOperation_type().equals("BORNES")){
			rp=crud.nbborne(data);
		}
		else if(r.getOperation_type().equals("CAPTORS")){
			rp=crud.getnbcap(data);
		}
		else if(r.getOperation_type().equals("TRAMS")){
			rp=crud.nbtram(data);
		}
	/*	else if(r.getOperation_type().equals("INTHETOWN")){
			rp=crud.carnow(data,r.getDate());
		}
		else if(r.getOperation_type().equals("POLL")){
			rp=crud.tpa(data,r.getDate());
		}
		else if(r.getOperation_type().equals("THERE")){
			rp=crud.tpb(data);
		}
		else if(r.getOperation_type().equals("EMPC")){
			rp=crud.empca(data,r.getDate());
		}
		else if(r.getOperation_type().equals("POLLPERI")){
			rp=crud.tpap(data,r.getDate(),r.getPos());
		}
		else if(r.getOperation_type().equals("CARINPERI")){
			rp=crud.carinperi(data,r.getDate(),r.getDate2());
		}
		else if(r.getOperation_type().equals("EMPDATE")){
			rp=crud.empperi(data,r.getDate(),r.getDate2());
		}
		else if(r.getOperation_type().equals("TPDATE")){
			rp=crud.tpdatee(data,r.getDate(),r.getDate2());
		}
		else if(r.getOperation_type().equals("TAB")){
			rp=crud.tabb(data,r.getDate(),r.getDate2());
		}*/
		return rp;
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

	public static DataSource getData() {
		return data;
	}
	
	
	public String serializeServeur(Response rp) throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		rp.setSuccessfulOperation(true);
		rp.setTypeOperation(r.getOperation_type());
		rpAsString = objectMapper.writeValueAsString(rp);
		System.out.println(rpAsString);
		return rpAsString;
	}
}
