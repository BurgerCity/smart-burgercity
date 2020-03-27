package client;

import java.net.*;
import java.io.*;

public class Client_socket {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
	}
	
	public String Communiquer(String msg) throws IOException {
		out.println(msg);
		String resp = in.readLine();
		return resp;
	}

	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client_socket c = new Client_socket();
		c.startConnection("172.31.249.164", 1099);
		String str = c.Communiquer("Bonjour server");
		if(str.equals("Enchanter cher client")) {
			System.out.println("Enchanter cher client");
		} else {
			System.out.println("Client inconnu");
		}
		c.close();
	}

}