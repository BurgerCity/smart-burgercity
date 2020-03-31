package server;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ThreadClient implements Runnable {
	private Socket clientSocket;
	
	public ThreadClient(Socket c) {
		clientSocket = c;
	}
	
	public void run() {
		Server s = new Server();
		try {
			s.start(clientSocket);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
}
