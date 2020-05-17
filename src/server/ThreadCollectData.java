package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ThreadCollectData implements Runnable {

	ServerSocket serverSocket2;
	public ThreadCollectData(ServerSocket serverSocket2) {
		this.serverSocket2 = serverSocket2;
	}
	
	public void run() {
		Server s = new Server();
		try {
			s.ThreadStatement(serverSocket2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
