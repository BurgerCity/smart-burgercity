package server;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

	private static JDBCConnectionPool pool;
	DataSource() throws ClassNotFoundException {
		pool = new JDBCConnectionPool();
	}

	public static Connection takeConnection() {
		return pool.take();
	}
	
	public static void returnConnection(Connection c) {
		 pool.restore(c);
	}
	public static void closeC() throws SQLException{
		 pool.closeConnection();
	}
	
}
