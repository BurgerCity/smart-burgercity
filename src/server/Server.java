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
	private Request r;
	private Response rp;
	private ObjectMapper objectMapper;
	private String rpAsString;
	private Message msg;
	private static Crud crud;
	private static DataSource data;
	private static Thread_bounds tb;
	private static Thread_car tc;
	
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
						rp = this.launchCrud(r, crud, data);
						msg.sendMessage(out, this.serializeServeur(rp));
						if(r.getOperation_type().equals("STOP")) {
							this.closeClient();
						}
					}
				
			} catch(Exception e) {}
		}
	}
		
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, Exception {
		Server s = new Server();
		ServerSocket serverSocket = s.startServer(2013);
		ServerSocket serverSocket2 = s.startServer(2015);
		ServerSocket serverSocket2018 = s.startServer(2018);	
		new Thread(new ThreadClientSocket(serverSocket2)).start();
		new Thread(new ThreadCollectData(serverSocket, s, serverSocket2018)).start();
		tc=new Thread_car(crud,data);
		tc.start();
		tb=new Thread_bounds(crud,data);
		tb.start();		
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
	
	public void ThreadStatement(ServerSocket serverSocket2, Server s, ServerSocket ss2018) throws IOException {
		try {
			while(true) {	
				Socket socket = serverSocket2.accept();
				new Thread(new ThreadSensorSocket(socket, data, crud, s, ss2018)).start();
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
		else if(r.getOperation_type().equals("CarbonSelect")){
			rp=crud.CarbonSelect(r,data);
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
		else if(r.getOperation_type().equals("THERE")){
			rp=crud.tpb(data);
		}
		else if(r.getOperation_type().equals("EMPC")){
			rp=crud.empca(data,r.getA().get(0));
		}
		else if(r.getOperation_type().equals("POLLPERI")){
			rp=crud.tpap(data,r.getA().get(0),r.getA().get(1));
		}
		else if(r.getOperation_type().equals("CARINPERI")){
			rp=crud.carinperi(data,r.getA().get(0),r.getA().get(1));
		}
		else if(r.getOperation_type().equals("EMPDATE")){
			rp=crud.empperi(data,r.getA().get(0),r.getA().get(1));
		}
		else if(r.getOperation_type().equals("TPDATE")){
			rp=crud.tpdatee(data,r.getA().get(0),r.getA().get(1));
		}
		else if(r.getOperation_type().equals("TAB")){
			rp=crud.tabb(data,r.getA().get(0),r.getA().get(1));
		}
		else if(r.getOperation_type().equals("SELECT_BOUND_STATUS")) {
			rp=crud.boundstatus(r,data);
		}
		else if(r.getOperation_type().equals("OPEN_BOUNDS")) {
			tb.setT(false);
			tc.setT(false);
			crud.manual(r,data);
		}
		else if(r.getOperation_type().equals("CLOSE_BOUNDS")) {
			tb.setT(false);
			tc.setT(false);
			crud.manual(r,data);
		}
		else if(r.getOperation_type().equals("MANUAL_BOUNDS")) {
			tb.setT(false);
			tc.setT(false);
		}
		else if(r.getOperation_type().equals("AUTO_BOUNDS")) {
			tb.setT(true);
			tc.setT(true);
			rp.getA().add("true");
		}
		else if(r.getOperation_type().equals("SET_MAXCAR")) {
			tc.setMaxCar(Integer.parseInt(r.getA().get(0)));
		}
		else if(r.getOperation_type().equals("GET_MAXCAR")) {
			rp.getA().add(Integer.toString(tc.getMaxCar()));
		}
		else if(r.getOperation_type().equals("INTHETOWN")){
			rp=crud.carnow(data,r.getA().get(0));
		}
		else if(r.getOperation_type().equals("POLL")){
			rp=crud.tpa(data,r.getA().get(0));
		}
		else if(r.getOperation_type().equals("FILLMAXCAR")){			
			crud.fillmaxcar(r,data);}
		else if(r.getOperation_type().equals("CARMAX")){
						rp=crud.carmax(data);
		}

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
