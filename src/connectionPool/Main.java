package connectionPool;

import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		/*Crud c = new Crud();
		c.choice();*/
		JDBCConnectionPool j = new JDBCConnectionPool();
		DataSource d = new DataSource();
		d.afficherM();
	}
	
}