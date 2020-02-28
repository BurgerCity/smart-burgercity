package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {

	private static JDBCConnectionPool pool = new JDBCConnectionPool();
	
	public static Connection takeConnection() {
		return pool.take();

	private static JDBCConnectionPool connection;

	public static Connection returnConnection() {
		return connection.take();
	}
	 public static void addConnection(Connection c) {
		 pool.restore(c);
	 }
	 public static void closeC() throws SQLException{
		 pool.closeConnection();
	 }
}
