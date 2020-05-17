package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ThreadSensorSocket extends Thread {
	private Socket socket;
	private DataSource data;
	private Crud crud;
	public ThreadSensorSocket(Socket socket, DataSource data, Crud crud) {
		this.socket = socket;
		this.data = data;
		this.crud = crud;
	}
	
	public void run() {
		StatementSensor ss = new StatementSensor(socket);
		try {
				ss.statement(data, crud);
			} catch (ClassNotFoundException | SQLException e) {
		}
	}
}
