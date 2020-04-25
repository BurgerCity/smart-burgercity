package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import common.Request;

public class Crud {
	Crud() {}
	
	/*public String insert(String firstname, String lastname, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		stmt.executeUpdate("INSERT INTO test1(lastname, firstname) values('" + lastname + "','" + firstname + "');");
		stmt.close();
		data.returnConnection(c);
		return "Successful operation";
	}*/
	public ResultSetMetaData getTable(String table, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + ";");
		ResultSetMetaData rd = rs.getMetaData();
		return rd;
	}
	
	
	public String insert(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		int i = 2;
		ResultSetMetaData rmd = this.getTable(r.getTable(), stmt);
		int n = rmd.getColumnCount();
		System.out.println(n);
		String st = "(";
		for(i = 2; i <= n; i++) {
			
			if(i != n) st = st + rmd.getColumnName(i) + ",";
			else st = st + rmd.getColumnName(i) + ")";
		}
		String s = "(";
		i = 1;
		String k;
		int cpt = 0;
		int cp = (r.getA().size()) / (n - 1); // nb de string dans l'arraylist par rapport au nombre d'insertion
		System.out.println(cp);
		while(i <= cp) {
			for(int j = 0; j < n - 1; j++) {
				k = rmd.getColumnTypeName(cpt + 2);
				if(k.equals("varchar")) {
					if (j < n - 2) {
						s = s + "'"+  r.getA().get(cpt) + "'" + ","; 
					} else if(j == n - 2){ 
						if(i < cp) s = s + "'"+  r.getA().get(cpt) + "'" + "),";
						else if(i == cp) s = s + "'"+  r.getA().get(cpt) + "'" + ");";
					}
				}
				else {
					if (j < n - 2) {
						s = s + r.getA().get(cpt) + ","; 
					} else if(j == n - 2){ 
						if(i < cp) s = s + r.getA().get(cpt) + "),";
						else if(i == cp) s = s + r.getA().get(cpt) + ");";
					}
				}
				cpt++;
			}
			i++;
		}
		System.out.println(s);
		for(int j = 0; j < Integer.parseInt(r.getA().get(r.getA().size() - 1)); j++) {
			stmt.executeUpdate("INSERT INTO " + r.getTable() + st + " values" + s);
		}
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
	
	public String update(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		ResultSetMetaData rmd = this.getTable(r.getTable(), stmt);
		int n = rmd.getColumnCount();
		/*if(testId(c, r, n) == 0) {
			data.returnConnection(c);
			return "The identifier doesn't exist";
		} else {*/
			String s = "";
			String k;
			int i = 1;
			while(i <= n - 1) {
				k = rmd.getColumnTypeName(i + 1);
				if(k.equals("varchar")) {
					if(i < n - 1) s = s + rmd.getColumnName(i + 1) + " = '" + r.getA().get(i - 1) + "', ";
					else  s = s + rmd.getColumnName(i + 1) + " = '" + r.getA().get(i - 1) + "' ";
				} else {
					if(i < n - 1) s = s + rmd.getColumnName(i + 1) + " = " + r.getA().get(i - 1) + ", ";
					else  s = s + rmd.getColumnName(i + 1) + " = " + r.getA().get(i - 1) + " ";
				}
				i++;
			}
			for(int j = n - 1; j <= (r.getA().size() - 1); j++) {
				System.out.println("UPDATE " + r.getTable() + " SET " + s + "WHERE id = " + r.getA().get(j) + ";");
				stmt.executeUpdate("UPDATE " + r.getTable() + " SET " + s + "WHERE id = " + r.getA().get(j) + ";");
			}
			stmt.close();	
			data.returnConnection(c);
			return "Successful operation";
		}

	//}
	
	public String delete(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement st = c.createStatement();
		/*if(testId(id, c) == 0) {
			return "The identifier doesn't exist";
		} else {
			st.executeUpdate("DELETE FROM test1 WHERE id =" + id + ";");
		}
		st.close();*/
		data.returnConnection(c);
		return "Successful operation";
	}
	
	public int testId(Connection c, Request r, int n) throws SQLException {
		Statement st = c.createStatement();
		ArrayList<Integer> al = new ArrayList<Integer>();
		Iterator<String> it = r.getA().iterator();	
			ResultSet rs = st.executeQuery("SELECT id FROM " + r.getTable()+ " WHERE id = " + r.getA().get(n) + ";" );
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
	}
}