package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ThreadCollectData implements Runnable {
	Server s;
	ServerSocket serverSocket2;
	ServerSocket ss2018;
	public ThreadCollectData(ServerSocket serverSocket2, Server s, ServerSocket ss2018) {
		this.serverSocket2 = serverSocket2;
		this.s = s;
		this.ss2018 = ss2018;
	}
	
	public void run() {
		Server s = new Server();
		try {
			s.ThreadStatement(serverSocket2, s, ss2018);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
