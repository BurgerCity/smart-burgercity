package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ThreadCollectData implements Runnable {
	Server s;
	ServerSocket serverSocket2;
	public ThreadCollectData(ServerSocket serverSocket2, Server s) {
		this.serverSocket2 = serverSocket2;
		this.s = s;
	}
	
	public void run() {
		Server s = new Server();
		try {
			s.ThreadStatement(serverSocket2, s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
