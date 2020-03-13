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
		Statement stmt = c.createStatement();
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nom :");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		System.out.println("Veuillez saisir un prenom :");
		String pren = sc.nextLine();
		System.out.println("Vous avez saisi : " + pren);
		stmt.executeUpdate("INSERT INTO test1(lastname, firstname) values('" + str + "','" + pren + "');");
		stmt.close();
		
	}
	
	public void select() throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT id,lastname,firstname FROM test1;");
		sc = new Scanner(System.in);
		while(rs.next()) {
			int id = rs.getInt("id");
			String last = rs.getString("lastname");
			String first = rs.getString("firstname");
			System.out.println(id + " " + last + " " + first);
		}
		rs.close();
		st.close();
	}
	
	public void update() throws SQLException {
		Statement stmt = c.createStatement();
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le nom modifié :");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		System.out.println("Veuillez saisir le prenom modifié :");
		String pren = sc.nextLine();
		System.out.println("Vous avez saisi : " + pren);
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		String id = sc.nextLine();
		stmt.executeUpdate("UPDATE test1 SET lastname = '" + str + "', firstname = '" + pren + "' WHERE id = " + id + " ;");
	}
	
	public void delete() throws SQLException {
		Statement st = c.createStatement();
		sc = new Scanner(System.in);
		System.out.println("Choisissez l'id de la personne a supprimer :");
		int str = sc.nextInt();
		st.executeUpdate("DELETE FROM test1 WHERE id =" + str + ";");
		st.close();
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