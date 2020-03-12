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
	public void afficherM() {
		Connection cn=pool.take();
		//System.out.println(cn);
		try{
			PreparedStatement statement= cn.prepareStatement("select * from test1;");
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String lastname = rs.getString(3);
		
		System.out.println("display done");
			}
	} catch (SQLException ex) {
		System.out.println("erreur " + ex.getMessage());
		}
	}
}
