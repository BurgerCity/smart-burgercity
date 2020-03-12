package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class Crud {
	
	private DataSource data = new DataSource();
	Statement stmt = a.get(0).createStatement (); 
	data.takeConnection();

	public void Insert(String nom, String prenom) {
		int n = stmt.executeUpdate("INSERT INTO test1 values('nom','prenom')");
	}
	
	public void Select() {
		ResultsSet result = stmt.executeQuery("Select * FROM test1;");
	}
	
	public void Update() {
		
		}
	
	public void Delete() {
		
	}
	
}