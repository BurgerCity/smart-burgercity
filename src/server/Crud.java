package server;

import java.sql.*;

public class Crud {
	Crud() {
	}
	
	public String insert(String firstname, String lastname, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		stmt.executeUpdate("INSERT INTO test1(lastname, firstname) values('" + lastname + "','" + firstname + "');");
		stmt.close();
		data.returnConnection(c);
		return "Successful operation";
	}
	
	public String select(String table, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
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
		data.returnConnection(c);
		return s;
	}
	
	public String update(String lastname, String firstname, int id, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		if(testId(id, c) == 0) {
			data.returnConnection(c);
			return "The identifier doesn't exist";
		} else {
			stmt.executeUpdate("UPDATE test1 SET lastname = '" + lastname + "', firstname = '" + firstname + "' WHERE id = " + id + " ;");
			data.returnConnection(c);
			return "Successful operation";
		}

	}
	
	public String delete(int id, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement st = c.createStatement();
		if(testId(id, c) == 0) {
			return "The identifier doesn't exist";
		} else {
			st.executeUpdate("DELETE FROM test1 WHERE id =" + id + ";");
		}
		st.close();
		data.returnConnection(c);
		return "Successful operation";
	}
	
	public int testId(int str, Connection c) throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT id FROM test1 WHERE id = " + str + ";" );
		if(rs.next()) {
			return rs.getInt(1);
		}
		else {
			return 0;
		}
	}
}