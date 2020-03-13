package connectionPool;

import java.util.Scanner;
import java.sql.*;

public class Crud {
	private JDBCConnectionPool jdbc;
	private DataSource data;
	private Connection c;
	private Scanner sc = new Scanner(System.in);
	Crud() {
		try {
			jdbc = new JDBCConnectionPool();
			data = new DataSource();
			c = data.takeConnection();
		} catch(Exception e) {}
	}
	
	public void insert() throws SQLException {
		PreparedStatement stmt = c.prepareStatement("INSERT INTO test1(lastname, firstname) values('?','?')");
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nom :");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		System.out.println("Veuillez saisir un prenom :");
		String pren = sc.nextLine();
		System.out.println("Vous avez saisi : " + pren);
		stmt.setString(1, str);
		stmt.setString(2, pren);	
	}
	
	public void select() throws SQLException {
		PreparedStatement stmt = c.prepareStatement("Select ? FROM test1");
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir 1 pour nom, 2 pour prenom ou 0 pour *");
		int n = sc.nextInt();
		//System.out.println("Vous avez saisi : " + str);
		if(n==1) {
			stmt.setString(1,"lastname");
			ResultSet rsp= stmt.executeQuery();
			System.out.println(rsp.next());
			while(rsp.next()) {
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
		}
	}
	
	public void update() throws SQLException {
		PreparedStatement stmt = c.prepareStatement("UPDATE test1 SET lastname = '?', firstname = '?' WHERE id = ?");
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
		PreparedStatement stmt = c.prepareStatement("DELETE FROM test1 WHERE id = ?");
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		String id = sc.nextLine();
		stmt.setString(1, id);
	}
	
	public void choice() throws SQLException {
		boolean b = true;
		System.out.println("Tapez 1 pour Insert, 2 pour Select, 3 pour Update, 4 pour Delete, 5 pour arreter : ");

		int str = sc.nextInt();
		while(b == true)
			
			if(str==1) {
					System.out.println("hi");
					this.insert();}
				
			else if(str==2){this.select();}
				
			else if(str==3){this.update();}
				
			else if(str==4){this.delete();}
				
			else		{b = false;}			
		
		
	}
}