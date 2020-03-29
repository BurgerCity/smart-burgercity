package client;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Request;
import common.Response;

import java.io.*;

public class Client_socket {

	private Socket clientSocket;
	//private PrintWriter out;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Scanner sc;
	private ObjectMapper objectMapper;
	private Request rq;
	private Response rp;
	private Boolean b = true;
	private String msg;
	private String rqAsString;
	private String firstname;
	private String lastname;
	private String table;
	private String respJson;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));	
	}
	
	public void Communiquer() throws IOException, SQLException {
		while(b == true) {
			msg = this.serialize();

			out.write(msg + "\n");
			out.flush();
			respJson = in.readLine();
			objectMapper = new ObjectMapper();
			rp =  objectMapper.readValue(respJson, Response.class);
			if(rp.getTypeOperation().equals("STOP")) {
				b = false;
				rp.getSelect();
			} else {
			System.out.println("RESPONSE \nVoici le type d'opération : " + rp.getTypeOperation() 
				+ "\n" + "Statut de l'operation : " + rp.getSuccessfulOperation() 
				+ "\n" + rp.getSelect());
			}
		}
	}

	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException {
		Client_socket c = new Client_socket();
		c.startConnection("127.0.0.1", 2013);
		c.Communiquer();
		System.out.println("Fermeture de la socket");
		c.close();
	}
	
	public String serialize() throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		objectMapper = new ObjectMapper();
		rq = this.choice();
		rqAsString = objectMapper.writeValueAsString(rq);
		return rqAsString;
	}
	public Request choice() throws SQLException {
		boolean b = true;
		sc = new Scanner(System.in);
		int str = 0;
		while(b == true) {
		   if(str == 0 ) {
				System.out.println("Tapez 1 pour Insert, 2 pour Select, 3 pour Update, 4 pour Delete, 5 pour arreter : ");
				str = sc.nextInt();
		   }
			else if(str==1) {
				sc = new Scanner(System.in);
				System.out.println("Veuillez saisir un prenom :");
				firstname = sc.nextLine();
				System.out.println("Vous avez saisi : " + firstname);
				System.out.println("Veuillez saisir un nom :");
				lastname = sc.nextLine();
				System.out.println("Vous avez saisi : " + lastname);
				
				rq = new Request();
				rq.setOperation_type("INSERT");
				rq.setFirstname(firstname);
				rq.setLastname(lastname);
					str = 0;	
					return rq;
			}
				
			else if(str==2)	{
				sc = new Scanner(System.in);
				System.out.println("Veuillez saisir une table :");
				table = sc.nextLine();
				
				rq = new Request();
				rq.setOperation_type("SELECT");
				rq.setTable(table);
				str = 0;
				return rq;
			}
				
			else if(str==3)	{
				sc = new Scanner(System.in);
				System.out.println("Veuillez saisir le nom modifié :");
				lastname = sc.nextLine();
				System.out.println("Vous avez saisi : " + lastname);
				System.out.println("Veuillez saisir le prenom modifié :");
				firstname = sc.nextLine();
				System.out.println("Vous avez saisi : " + firstname);
				System.out.println("Veuillez saisir l'identifiant de la personne :");
				int id = sc.nextInt();
				
				rq = new Request();
				rq.setOperation_type("UPDATE");
				rq.setFirstname(firstname);
				rq.setLastname(lastname);
				rq.setId(id);
				str = 0;
				return rq;
			}
				
			else if(str==4)	{
				System.out.println("Choisissez l'id de la personne a supprimer :");
				int id = sc.nextInt();
				rq = new Request();
				rq.setOperation_type("DELETE");
				rq.setId(id);
				str = 0;
				return rq;
			}
			else if(str >= 5 )	{
				b = false;
			}
		}
		rq = new Request();
		rq.setOperation_type("STOP");
		return rq;
	}
}