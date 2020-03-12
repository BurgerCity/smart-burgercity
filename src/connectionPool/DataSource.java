package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
	public static JDBCConnectionPool pool;
	
	//public JDBCConnection getPool() {
		//return pool;
//	}
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
