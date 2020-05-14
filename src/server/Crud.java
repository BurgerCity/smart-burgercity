package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import common.Request;
import common.Response;

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
	
	public Response select(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement st = c.createStatement();
		String s = "";
		Response rp = new Response();
		int i = 1;
		if(r.getA().size() == 0) {
			s = "*";
			ResultSet rs = st.executeQuery("SELECT " + s + " FROM " + r.getTable() + ";");
			while(rs.next()) {
				rp.getA().add(Integer.toString(rs.getInt(0)));
			}
			rs.close();
		} else {
			while(i < Integer.parseInt(r.getA().get(0))) {
				s = s + r.getA().get(i) + " ,";
				i++;
			}
			s = s + r.getA().get(i);
			System.out.println(s);
			i = Integer.parseInt(r.getA().get(0)) + 1;
			String t = "";
			while(i < r.getA().size() - 2) {
				t = t + r.getA().get(i) + " = '" + r.getA().get(i + 1) + "' OR ";
				i += 2;
			}
			t = t + r.getA().get(i) + " = '" + r.getA().get(i + 1) + "'";
			System.out.println("SELECT " + s + " FROM " + r.getTable() + " WHERE " + t + ";");
			ResultSet rs = st.executeQuery("SELECT " + s + " FROM " + r.getTable() + " WHERE " + t + ";");
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				rp.getA().add(Integer.toString(rs.getInt(1)));
			}
			rs.close();
		}
		System.out.println("je suis");
		System.out.println(rp.getA());
		st.close();
		data.returnConnection(c);
		return rp;
	}
	
	public String update(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		ResultSetMetaData rmd = this.getTable(r.getTable(), stmt);
		int n = rmd.getColumnCount();
		System.out.println("bjr");
		if(testId(c, r, n, rmd) == 0) {
			System.out.println("wsh");
			data.returnConnection(c);
			return "The identifier doesn't exist";
		} else {
			System.out.println("aps bn");
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
				System.out.println("UPDATE " + r.getTable() + " SET " + s + "WHERE " + rmd.getColumnLabel(1) + " = " + r.getA().get(j) + ";");
				stmt.executeUpdate("UPDATE " + r.getTable() + " SET " + s + "WHERE " + rmd.getColumnLabel(1) + " = " + r.getA().get(j) + ";");
			}
			stmt.close();	
			data.returnConnection(c);
			return "Successful operation";
		}

	}
	
	public String delete(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement st = c.createStatement();
		ResultSetMetaData rmd = this.getTable(r.getTable(), st);
		int n = rmd.getColumnCount();
		if(testId(c, r, n, rmd) == 0) {
			return "The identifier doesn't exist";
		} else {
			st.executeUpdate("DELETE FROM " + r.getTable() + " WHERE id =" + r.getA().get(0) + ";");
		}
		st.close();
		data.returnConnection(c);
		return "Successful operation";
	}
	
	public int testId(Connection c, Request r, int n, ResultSetMetaData rdm) throws SQLException {
		Statement st = c.createStatement();
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = n - 1; i <= r.getA().size() - 1; i++) {	
			ResultSet rs = st.executeQuery("SELECT " + rdm.getColumnName(1)+ " FROM " + r.getTable()+ " WHERE " + rdm.getColumnName(1) + " = " + r.getA().get(i) + ";" );
			if(rs.next()) {
				al.add(rs.getInt(1));
			}
			else {
				al.add(0);
			}
		}
		Iterator<Integer> it = al.iterator();	
		while(it.hasNext()) {
			int k = it.next();
			System.out.println(k);
			if(k == 0) return 0;
		}
		return 1;
	}
}