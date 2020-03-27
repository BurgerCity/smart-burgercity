package client;

import java.util.Scanner;

public class RequestJSON {
	Scanner sc;
	public String selectJSON() {
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une table :");
		String table = sc.nextLine();
		String s = "request : {\r\n" + 
				"    operation_type  : 'SELECT';\r\n" + 
				"    target          : '" + table + "';\r\n" + 
				"};";
		return s;
	}
	
	public String insertJSON() {
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nom :");
		String firstname = sc.nextLine();
		System.out.println("Vous avez saisi : " + firstname);
		System.out.println("Veuillez saisir un prenom :");
		String lastname = sc.nextLine();
		System.out.println("Vous avez saisi : " + lastname);
		
		String s = "request : {\r\n" + 
				"    operation_type  : 'INSERT';\r\n" + 
				"    firstname       : '" + firstname + "';\r\n" + 
				"    lastname        : '" + lastname + "';\r\n" +
				"};";
		return s;
	}
	
	public String updateJSON() {
		System.out.println("Veuillez saisir le nom modifié :");
		String lastname = sc.nextLine();
		System.out.println("Vous avez saisi : " + lastname);
		System.out.println("Veuillez saisir le prenom modifié :");
		String firstname = sc.nextLine();
		System.out.println("Vous avez saisi : " + firstname);
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		int id = sc.nextInt();
		
		String s = "request : {\r\n" + 
				"    operation_type  : 'UPDATE';\r\n" + 
				"    firstname       : '" + firstname + "';\r\n" + 
				"    lastname        : '" + lastname + "';\r\n" +
				"    id              : '" + id + "';\r\n" +
				"};";
		return s;
	}
	
	public String deleteJSON() {
		System.out.println("Choisissez l'id de la personne a supprimer :");
		int id = sc.nextInt();
		String s = "request : {\r\n" + 
				"    operation_type  : 'DELETE';\r\n" + 
				"    id              : '" + id + "';\r\n" + 
				"};";
		return s;
	}
}
