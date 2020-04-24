package server;

import java.sql.*;

import common.Request;

public class Crud {
	Crud() {}
	
	public String insert(String firstname, String lastname, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		stmt.executeUpdate("INSERT INTO test1(lastname, firstname) values('" + lastname + "','" + firstname + "');");
		stmt.close();
		data.returnConnection(c);
		return "Successful operation";
	}
	public ResultSetMetaData getTable(String table, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + ";");
		ResultSetMetaData rd = rs.getMetaData();
		return rd;
	}
	
	
	public String insertSensor(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		int i = 0;
		ResultSet rs = stmt.executeQuery("SELECT * FROM sensor;");
		ResultSetMetaData rmd = rs.getMetaData();
		int n = rmd.getColumnCount();
		String st = "(";
		for(i = 1; i <= n; i++) {
			
			while(i != n) st = st + rmd.getColumnLabel(i) + ",";
			st = st + rmd.getColumnLabel(i) + ")";
		}
		System.out.println(st);
		String s = "";
		while(i <= r.getPoll().getNbSensors()) {
				s = s + "('" + r.getPoll().getLocation() + "','" + r.getPoll().getTimeBeforeAlert() 
						+ "','" + r.getPoll().getStatement() + "','" + r.getPoll().getNitrogenDioxideInfo() + "','" + r.getPoll().getNitrogenDioxideAlert() +
						"','" + r.getPoll().getLeadInfo() + "','" + r.getPoll().getLeadAlert() + "','" + r.getPoll().getFineParticlesInfo() + 
						"','" + r.getPoll().getFineParticlesAlert() + "','" + r.getPoll().getCarbonMonoxideInfo() + "','" + r.getPoll().getCarbonMonoxideAlert() + "')";
				
				if(i != r.getPoll().getNbSensors()) s = s + ",";
				else s = s + ";";
			i++;
		}
		stmt.executeUpdate("INSERT INTO " + r.getTable() + st + " values" + s);
		i++;
		stmt.close();
		data.returnConnection(c);
		return "Successful operation";
	}
	
	public String select(String table, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + table + ";");
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