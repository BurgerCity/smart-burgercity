package client;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;
import common.Request;
import common.Response;

import java.io.*;

public class Client_socket {

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Scanner sc;
	private ObjectMapper objectMapper;
	private Request rq;
	private Response rp;
	private Boolean b = true;
	private Message msg;
	private String rqAsString;
	private String firstname;
	private String lastname;
	private String table;
	private int id;
	private int str = 0;
	private String string;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));	
		msg = new Message();
		objectMapper = new ObjectMapper();
	}
	
	public void Communiquer() throws IOException, SQLException {
		/*msg.sendMessage(out, this.requestConnection());
		String s = msg.readMessage(in);
		System.out.println(s);
		rp = this.deserialize(msg.readMessage(in));
		System.out.println("bj");
		rp = this.deserialize(s);
		if(rp.getTypeOperation().equals("CONNECTION OK")) {*/
			System.out.println("Connection completed");
			while(b == true) {
				msg.sendMessage(out, this.serialize());
				string = msg.readMessage(in);
				if(string.equals("WAIT")) {
					this.deserialize(msg.readMessage(in));
					this.display();
				} else {
					this.display();
				}
			}
		/*}else if(rp.getTypeOperation().equals("SERVER IS FULL")) {
			System.out.println("Server is full");
		}*/
	}
	
	public void display() {
		if(rp.getTypeOperation().equals("STOP")) {
			b = false;
		} 
		else if(rp.getTypeOperation().equals("SELECT") || rp.getSuccessfulOperation().equals(false)) {
			System.out.println("RESPONSE \nType of operation : " + rp.getTypeOperation() 
			+ "\n" + "Successful operation : " + rp.getSuccessfulOperation() + "\n" +
			rp.getSelect());
		}
		else {
		System.out.println("RESPONSE \nType of operation : " + rp.getTypeOperation() 
			+ "\n" + "Successful operation : " + rp.getSuccessfulOperation());
		}
	}
	
	public String requestConnection() throws JsonProcessingException {
		rq = new Request();
		rq.setOperation_type("CONNECTION");
		rqAsString = objectMapper.writeValueAsString(rq);
		return rqAsString;
	}

	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException {
		Client_socket c = new Client_socket();
		c.startConnection("172.31.249.164", 2013);
		c.Communiquer();
		System.out.println("Fermeture de la socket");
		c.close();
	}
	
	public String serialize() throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		rq = this.choice();
		rqAsString = objectMapper.writeValueAsString(rq);
		return rqAsString;
	}
	
	public Response deserialize(String respJson) throws JsonMappingException, JsonProcessingException {
		rp = new Response();
		rp =  objectMapper.readValue(respJson, Response.class);
		System.out.println(rp.getTypeOperation());
		return rp;
	}
	public Request choice() throws SQLException {
		boolean b = true;
		sc = new Scanner(System.in);
		while(b == true) {
			if(str == 0) {
				while(str > 5 || str < 1) {
					System.out.println("Type 1 to Insert, 2 to Select, 3 to Update, 4 to Delete, 5 to stop : ");
					str = sc.nextInt();
					if(str < 5 || str > 1) {
						break;
					}
					System.out.println("Please enter a proposed number");
				}
			}
			else if(str == 1) {
				sc = new Scanner(System.in);
				System.out.println("Enter a firstname :");
				firstname = sc.nextLine();
				System.out.println("You entered : " + firstname);
				System.out.println("Enter a lastname:");
				lastname = sc.nextLine();
				System.out.println("You entered : " + lastname);
				
				rq = new Request();
				rq.setOperation_type("INSERT");
				rq.setFirstname(firstname);
				rq.setLastname(lastname);
					str = 0;	
					return rq;
			}
			else if(str == 2) {
				sc = new Scanner(System.in);
				System.out.println("Enter a table :");
				table = sc.nextLine();
				
				rq = new Request();
				rq.setOperation_type("SELECT");
				rq.setTable(table);
				str = 0;
				return rq;
			}
			else if(str == 3) {
		   		sc = new Scanner(System.in);
				System.out.println("Enter the modified lastname :");
				lastname = sc.nextLine();
				System.out.println("You entered : " + lastname);
				System.out.println("Enter the modified firstname :");
				firstname = sc.nextLine();
				System.out.println("You entered : " + firstname);
				System.out.println("Enter the person's identifier :");
				id = sc.nextInt();
				
				rq = new Request();
				rq.setOperation_type("UPDATE");
				rq.setFirstname(firstname);
				rq.setLastname(lastname);
				rq.setId(id);
				str = 0;
				return rq;
			}
			else if(str == 4) {
				System.out.println("Enter the person's identifier to delete :");
				id = sc.nextInt();
				rq = new Request();
				rq.setOperation_type("DELETE");
				rq.setId(id);
				str = 0;
				return rq;
			}
			else if(str == 5) {
				b = false;
			}
		}
		rq = new Request();
		rq.setOperation_type("STOP");
		return rq;
	}
}