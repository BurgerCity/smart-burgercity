package client;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestJSON {
	Client_socket client;
	Scanner sc;
	ObjectMapper mapper = new ObjectMapper();
	public void selectJSON() throws IOException {
		Select select = new Select();
		
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une table :");
		String table = sc.nextLine();
		String jsonString = "{\"table\":\"" + table + "\"}";
		jsonString = mapper.writeValueAsString(select);
	}
	
	public void insertJSON() throws IOException {
		Insert insert = new Insert();
		
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nom :");
		String firstname = sc.nextLine();
		System.out.println("Vous avez saisi : " + firstname);
		System.out.println("Veuillez saisir un prenom :");
		String lastname = sc.nextLine();
		System.out.println("Vous avez saisi : " + lastname);
		String jsonString = "{\"firstname\":\"" + firstname + "\", \"lastname\":" + lastname + "}";
		jsonString = mapper.writeValueAsString(insert);
	}
	
	public void updateJSON() throws IOException {
		Update update = new Update();
		
		System.out.println("Veuillez saisir le nom modifié :");
		String lastname = sc.nextLine();
		System.out.println("Vous avez saisi : " + lastname);
		System.out.println("Veuillez saisir le prenom modifié :");
		String firstname = sc.nextLine();
		System.out.println("Vous avez saisi : " + firstname);
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		int id = sc.nextInt();
		
		String jsonString = "{\"firstname\":\"" + firstname + "\", \"lastname\":" + lastname + "\", \"id\":" + id + "\"}";
		jsonString = mapper.writeValueAsString(update);
	}
	
	public void deleteJSON() throws IOException {
		Delete delete = new Delete();
		
		System.out.println("Choisissez l'id de la personne a supprimer :");
		int id = sc.nextInt();
		
		String jsonString = "{\"id\":\"" + id + "\"}";
		jsonString = mapper.writeValueAsString(delete);
	}
	
	public void closeConnectionJSON() throws IOException {
		String s = "request : {\r\n" + 
				"    operation_type  : 'CLOSE CONNECTION';\r\n" + 
				"};";
		client.Communiquer(s);
	}
	
	public void choice() throws IOException {
		boolean b = true;
		sc = new Scanner(System.in);
		int str = 0;
		while(b == true)
		    if(str == 0 ) {
				System.out.println("Tapez 1 pour Insert, 2 pour Select, 3 pour Update, 4 pour Delete, 5 pour arreter : ");
				str = sc.nextInt();
			}
			else if(str==1) {
				this.insertJSON();
					str = 0;		
			}
				
			else if(str==2)	{
				this.selectJSON();
				str = 0;
			}
				
			else if(str==3)	{
				this.updateJSON();
				str = 0;
			}
				
			else if(str==4)	{
				this.deleteJSON();
				str = 0;
			}
			else	{
				b = false;
			}
		//this.closeConnectionJSON();
	}
}
