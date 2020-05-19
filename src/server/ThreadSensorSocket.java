package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ThreadSensorSocket extends Thread {
	private Socket socket;
	private DataSource data;
	private Crud crud;
	private Server s;
	private ServerSocket ss2018;
	public ThreadSensorSocket(Socket socket, DataSource data, Crud crud, Server s, ServerSocket ss2018) {
		this.socket = socket;
		this.data = data;
		this.crud = crud;
		this.s = s;
		this.ss2018 = ss2018;
	}
	
	public void run() {
		StatementSensor ss = new StatementSensor(socket);
		try {
				ss.statement(data, crud, s, ss2018);
			} catch (ClassNotFoundException | SQLException | IOException e) {}
	}
}
