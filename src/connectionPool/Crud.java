package connectionPool;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Crud {
	private JDBCConnectionPool jdbc;
	private DataSource data;
	private Connection c;
	private Scanner sc;
	Crud() {
		try {
			jdbc = new JDBCConnectionPool();
			data = new DataSource();
			c = data.takeConnection();
		} catch(Exception e) {}
	}
	
	public void insert() throws SQLException {
		PreparedStatement stmt = c.prepareStatement("INSERT INTO test1(id, lastname, firstname) values('?','?');");
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nom :");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		System.out.println("Veuillez saisir un prenom :");
		String pren = sc.nextLine();
		System.out.println("Vous avez saisi : " + pren);
		stmt.setString(2, str);
		stmt.setString(3, pren);
		ResultSet rs = stmt.executeQuery();
		rs.close();
		
	}
	
	public void select() throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT id,lastname,firstname FROM test1;");
		ResultSetMetaData rd = rs.getMetaData();
		/*String s = "";
		int n = rd.getColumnCount();
		for(int i = 1; i <= n; i++) {
			s = rd.getColumnName(i);
			s = s + " ";
		}
		System.out.println(s);*/
		while(rs.next()) {
			int id = rs.getInt("id");
			String last = rs.getString("lastname");
			String first = rs.getString("firstname");
			System.out.println(id + " " + last + " " + first);
		}
		rs.close();
		st.close();
		
	/*	PreparedStatement stmt = c.prepareStatement("Select ? FROM test1");
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir 1 pour nom, 2 pour prenom ou 0 pour *");
		int n = sc.nextInt();
		//System.out.println("Vous avez saisi : " + str);
		if(n==1) {
			stmt.setString(1,"lastname");
			ResultSet rsp= stmt.executeQuery();
			while(rsp.next()) {
				ResultSetMetaData rsmd=rsp.getMetaData();
				//rsp.getObjet(i);
				String noms=rsp.getString(1);
				System.out.println(noms);
			}
			rsp.close();
			stmt.close();
		}
		else if(n==2) {
			stmt.setString(1, "firstname");
			ResultSet rsp=stmt.executeQuery();
			while(rsp.next()) {
				String prenoms=rsp.getString("firstname");
				System.out.println(prenoms);
			}
			rsp.close();
			stmt.close();
		}
		else if(n==0) {
			stmt.setString(1, "*");
			ResultSet rsp=stmt.executeQuery();
			while(rsp.next()) {
				String noms=rsp.getString("lastname");
				String prenoms=rsp.getString("firstname");
				int ids=rsp.getInt("id");
				System.out.println(ids +" "+prenoms+" "+noms);
			}
			rsp.close();
			stmt.close();
		}
		else {
			System.out.println("error typo");
			stmt.close();
		}*/
	}
	
	public void update() throws SQLException {
		PreparedStatement stmt = c.prepareStatement("UPDATE test1 SET lastname = '?', firstname = '?' WHERE id = ?;");
		System.out.println("Veuillez saisir le nom modifié :");
		String str = sc.nextLine();
		System.out.println("Veuillez saisir le prenom modifié :");
		String pren = sc.nextLine();
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		String id = sc.nextLine();
		stmt.setString(1, str);
		stmt.setString(2, pren);
		stmt.setString(3, id);
	}
	
	public void delete() throws SQLException {
		PreparedStatement stmt = c.prepareStatement("DELETE FROM test1 WHERE id = ?;");
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		int id = sc.nextInt();
		stmt.setInt(1, id);
	}
	
	public void choice() throws SQLException {
		boolean b = true;
		sc = new Scanner(System.in);
		int str = 0;
		while(b == true)
		    if(str == 0 ) {
				System.out.println("Tapez 1 pour Insert, 2 pour Select, 3 pour Update, 4 pour Delete, 5 pour arreter : ");
				str = sc.nextInt();
			}
			else if(str==1) {
					System.out.println("hi");
					this.insert();
					str = 0;		
			}
				
			else if(str==2){
				this.select();
				str = 0;
			}
				
			else if(str==3){this.update();
			str = 0;
			}
				
			else if(str==4){this.delete();
			str = 0;}
				
			else		{b = false;}
		data.returnConnection(c);
		data.closeC();
	}
}