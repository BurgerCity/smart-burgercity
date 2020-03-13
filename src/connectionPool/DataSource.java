package connectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

	private static JDBCConnectionPool pool;
	DataSource() {
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
