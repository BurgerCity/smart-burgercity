package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {
	private static JDBCConnectionPool connection;
	
	public static Connection returnConnection() {
		return connection.take();
	}
	 public static void addConnection(Connection c) {
		 connection.restore(c);
	 }
	 public static void closeC() throws SQLException{
		 connection.closeConnection();
	 }
}
