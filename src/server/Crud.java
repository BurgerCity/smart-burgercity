package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import common.Request;
import common.Response;

public class Crud {
	
	Crud() {}

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
		String st = "(";
		for(i = 2; i <= n; i++) {		
			if(i != n) st = st + rmd.getColumnName(i) + ",";		//this loop allows to create the list 
			else st = st + rmd.getColumnName(i) + ")";
		}
		String s = "(";
		i = 1;
		String k;
		int cpt = 0;
		int cp = (r.getA().size()) / (n - 1); 		// nb de string dans l'arraylist par rapport au nombre d'insertion
		while(i <= cp) {
			for(int j = 0; j < n - 1; j++) {
				k = rmd.getColumnTypeName(cpt + 2);
				if(k.equals("varchar")) {			//if it's a varchar then it's between quote
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
		for(int j = 0; j < Integer.parseInt(r.getA().get(r.getA().size() - 1)); j++) {
			System.out.println("INSERT INTO " + r.getTable() + st + " values" + s);
			stmt.executeUpdate("INSERT INTO " + r.getTable() + st + " values" + s);
		}
		stmt.close();
		data.returnConnection(c);
		return "Successful operation";
	}
	
	public Response CarbonSelect(Request r, DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		String rr = r.getA().get(0);
		String rr2 = r.getA().get(1);
		String tt = r.getTable();
		ResultSet rslt=stmt.executeQuery("SELECT " + rr + "," + rr2 + " FROM " + tt + ";");
		Response rp=new Response();
		while(rslt.next()) {

			String rep = Integer.toString(rslt.getInt(1));
			String rep2 = Integer.toString(rslt.getInt(2));
			rp.getA().add(rep);
			rp.getA().add(rep2);
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	public Response select(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement st = c.createStatement();
		String s = "";
		Response rp = new Response();
		int i = 1;
		if(r.getA().size() == 0) {
			s = "*";
			ResultSet rs = st.executeQuery("SELECT " + s + " FROM " + r.getTable() + " ;");
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
				rp.getA().add(Integer.toString(rs.getInt(1)));
			}
			rs.close();
		}
		st.close();
		data.returnConnection(c);
		return rp;
	}
	public Response countcar(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("SELECT count(*) from car where inthetown=true;");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		
 		return rp;
	}
	
	public Response getAlert(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("SELECT alert from sensor where ...;");
		return null;} 


	public Response carmax(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select avg(maxcar) from alertcar;");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}

	
	public Response getnbcap(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		Crud ss = new Crud();
		ResultSet rslt=stmt.executeQuery("select count (*)  from sensor ; ");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response nbborne(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		Crud ss = new Crud();
		ResultSet rslt=stmt.executeQuery("select count (*)  from bound ; ");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response nbtram(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		Crud ss = new Crud();
		ResultSet rslt=stmt.executeQuery("select count (*)  from stations ; ");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response carnow(DataSource data,String s) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select count (*)  from car where  date = ' "+s+" ' and inthetown = true;" );
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response tpa(DataSource data,String s) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select avg(plomb)+avg(carbonmonoxide)+avg(fineparticle)+avg(nitrogendioxide) from statements where to_char(date_heure, 'YYYY-MM-DD')= '"+s+"';");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Float.toString(rslt.getFloat(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response tpb(DataSource data) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		Crud ss = new Crud();
		ResultSet rslt=stmt.executeQuery("select avg(leadalert) + avg(carbonmonoxidealert) + avg(fineparticlesalert) + avg(nitrogendioxidealert) from sensor ;");		
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Float.toString(rslt.getFloat(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response empca(DataSource data,String s) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select avg(result) from carbonfootprint where date = ' "+s+" ';");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Long.toString(rslt.getLong(1)));
			System.out.println(Long.toString(rslt.getLong(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response tpap(DataSource data,String s,String pos) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		Crud ss = new Crud();
		ResultSet rslt=stmt.executeQuery("SELECT avg(plomb)+avg(carbonmonoxide)+avg(fineparticle)+avg(nitrogendioxide) FROM statements INNER JOIN sensor ON (statements.id_sensor = sensor.id_sensor) and to_char(date_heure,'YYYY-MM-DD') = '"+s+"' and localization = '"+pos+"';");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Float.toString(rslt.getFloat(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	
	public Response carinperi(DataSource data,String s,String s1) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select count (*)  from car where inthetown = true and date BETWEEN '"+s+"' and '"+s1+"';");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Integer.toString(rslt.getInt(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	public Response empperi(DataSource data,String s,String s1) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select avg(result) from carbonfootprint where  date BETWEEN '"+s+"' and '"+s1+"';");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Long.toString(rslt.getLong(1)));
			System.out.println(Long.toString(rslt.getLong(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	public Response tpdatee(DataSource data,String s,String s1) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("SELECT avg(plomb)+avg(carbonmonoxide)+avg(fineparticle)+avg(nitrogendioxide) from statements where  to_char(date_heure,'YYYY-MM-DD') BETWEEN '"+s+"' and '"+s1+"';");	
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(Float.toString(rslt.getFloat(1)));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}
	public Response tabb(DataSource data,String s,String s1) throws SQLException{
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("SELECT distinct(to_char(date_heure,'YYYY-MM-DD')) FROM statements  where to_char(date_heure,'YYYY-MM-DD') BETWEEN '"+s+"' and '"+s1+"';");
		Response rp=new Response();
		while(rslt.next()) {
			rp.getA().add(rslt.getString(1));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
 		return rp;
	}

	public String update(Request r, DataSource data) throws SQLException {
		Connection c = data.takeConnection();
		Statement stmt = c.createStatement();
		ResultSetMetaData rmd = this.getTable(r.getTable(), stmt);
		int n = rmd.getColumnCount();
		if(testId(c, r, n, rmd) == 0) {
			data.returnConnection(c);
			return "The identifier doesn't exist";
		} else {
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
	
	public Response boundstatus(Request r,DataSource data) throws SQLException {
		Response rp=new Response();
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		ResultSet rslt=stmt.executeQuery("select status from bound where id="+Integer.parseInt(r.getA().get(0))+";");
		while(rslt.next()) {		
			rp.getA().add(Boolean.toString(rslt.getBoolean(1)));
			System.out.println(rslt.getBoolean(1));
		}
		stmt.close();
 		rslt.close();
 		data.returnConnection(c);
		return rp;
	}

	public void manual(Request r,DataSource data) throws SQLException {
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		stmt.executeUpdate("UPDATE bound set status="+r.getA().get(0)+";");
		stmt.close();
		data.returnConnection(c);
	}

	public void fillmaxcar(Request r,DataSource data) throws SQLException {
		Connection c=data.takeConnection();
		Statement stmt=c.createStatement();
		stmt.executeUpdate("UPDATE alertcar set maxcar="+r.getA().get(0)+";");
		stmt.close();
		data.returnConnection(c);		
	}
}