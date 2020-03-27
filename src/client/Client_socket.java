package client;

import java.net.*;
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
	private PrintWriter out;
	private BufferedReader in;
	private Scanner sc;
	private ObjectMapper mapper;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
	}
	
	public void Communiquer(Object msg) throws IOException {
		out.println(msg);
		String respJson = in.readLine();
		Response rp =  mapper.readValue(respJson, Response.class);
		if(rp.getTypeOperation().equals("SELECT")) {
			System.out.println(rp.getSuccessfulOperation());
			System.out.println(rp.getSelect());
		} else {
			System.out.println(rp.getTypeOperation());
			System.out.println(rp.getSuccessfulOperation());
		}
	}

	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException {
		Client_socket c = new Client_socket();
		c.startConnection("127.0.0.1", 2004);
		c.Communiquer(c.serialize());
		c.close();
	}
	
	public String serialize() throws JsonGenerationException, JsonMappingException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		//Request rq = this.choice();
		
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une table :");
		String table = sc.nextLine();
		
		Request rq = new Request("SELECT");
		rq.setTable(table);
		
		objectMapper.writeValue(new File("request.json"), rq);
		String rqAsString = objectMapper.writeValueAsString(rq);
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
				System.out.println("Veuillez saisir un nom :");
				String firstname = sc.nextLine();
				System.out.println("Vous avez saisi : " + firstname);
				System.out.println("Veuillez saisir un prenom :");
				String lastname = sc.nextLine();
				System.out.println("Vous avez saisi : " + lastname);
				
				Request rq = new Request("INSERT");
				rq.setFirstname(firstname);
				rq.setLastname(lastname);
					str = 0;	
					return rq;
			}
				
			else if(str==2)	{
				sc = new Scanner(System.in);
				System.out.println("Veuillez saisir une table :");
				String table = sc.nextLine();
				
				Request rq = new Request("SELECT");
				rq.setTable(table);
				str = 0;
				return rq;
			}
				
			else if(str==3)	{
				sc = new Scanner(System.in);
				System.out.println("Veuillez saisir le nom modifié :");
				String lastname = sc.nextLine();
				System.out.println("Vous avez saisi : " + lastname);
				System.out.println("Veuillez saisir le prenom modifié :");
				String firstname = sc.nextLine();
				System.out.println("Vous avez saisi : " + firstname);
				System.out.println("Veuillez saisir l'identifiant de la personne :");
				int id = sc.nextInt();
				
				Request rq = new Request("UPDATE");
				rq.setFirstname(firstname);
				rq.setLastname(lastname);
				rq.setId(id);
				str = 0;
				return rq;
			}
				
			else if(str==4)	{
				System.out.println("Choisissez l'id de la personne a supprimer :");
				int id = sc.nextInt();
				Request rq = new Request("DELETE");
				rq.setId(id);
				str = 0;
				return rq;
			}
			else if(str >= 5 )	{
				b = false;
			}
		}
		Request rq = new Request("STOP");
		return rq;
	}
}