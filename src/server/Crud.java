package server;

import java.sql.*;

public class Crud {
	private DataSource data;
	private Connection c;
	Crud() {
		try {
			data = new DataSource();
			c = data.takeConnection();
		} catch(Exception e) {}
	}
	
	public String insert(String firstname, String lastname) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate("INSERT INTO test1(lastname, firstname) values('" + lastname + "','" + firstname + "');");
		stmt.close();
		return "Successful operation";
	}
	
	public String select(String table) throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT id,lastname,firstname FROM " + table + ";");
		String s = "";
		while(rs.next()) {
			int id = rs.getInt("id");
			String last = rs.getString("lastname");
			String first = rs.getString("firstname");
			s = s + id + " " + last + " " + first +"\n";
		}
		rs.close();
		st.close();
		return s;
	}
	
	public String update(String lastname, String firstname, int id) throws SQLException {
		Statement stmt = c.createStatement();
		if(testId(id) == 0) {
			return "The identifier doesn't exist";
		} else {
			stmt.executeUpdate("UPDATE test1 SET lastname = '" + lastname + "', firstname = '" + firstname + "' WHERE id = " + id + " ;");
			return "Successful operation";
		}
	}
	
	public String delete(int id) throws SQLException {
		Statement st = c.createStatement();
		if(testId(id) == 0) {
			return "The identifier doesn't exist";
		} else {
			st.executeUpdate("DELETE FROM test1 WHERE id =" + id + ";");
		}
		st.close();
		return "Successful operation";
	}
	
	public int testId(int str) throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT id FROM test1 WHERE id = " + str + ";" );
		if(rs.next()) {
			return rs.getInt(1);
		}
		else {
			return 0;
		}
	}
	
	public void closeConnection() throws SQLException {
		data.returnConnection(c);
	}
}