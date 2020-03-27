package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket s;
	Socket c;
	PrintWriter out;
	BufferedReader in;
	public void start(int port) throws IOException {
		s = new ServerSocket(port);
		c = s.accept();
		out = new PrintWriter(c.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		String msg = in.readLine();
			
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
			int c = 1099;
			s.start(c);
		} catch (Exception e) {
			s.close();
		}
	}
}
