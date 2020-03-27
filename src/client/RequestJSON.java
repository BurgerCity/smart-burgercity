package client;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
		select.setTable(table);
		this.writeJSONselect(select);
	}
	
	public void insertJSON() throws IOException {
		Insert insert = new Insert();
		
		sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un nom :");
		String firstname = sc.nextLine();
		insert.setFirstname(firstname);
		System.out.println("Vous avez saisi : " + firstname);
		System.out.println("Veuillez saisir un prenom :");
		String lastname = sc.nextLine();
		insert.setLastname(lastname);
		System.out.println("Vous avez saisi : " + lastname);
		this.writeJSONinsert(insert);
	}
	
	

	public void updateJSON() throws IOException {
		Update update = new Update();
		
		System.out.println("Veuillez saisir le nom modifié :");
		String lastname = sc.nextLine();
		System.out.println("Vous avez saisi : " + lastname);
		update.setLastname(lastname);
		System.out.println("Veuillez saisir le prenom modifié :");
		String firstname = sc.nextLine();
		System.out.println("Vous avez saisi : " + firstname);
		update.setFirstname(firstname);
		System.out.println("Veuillez saisir l'identifiant de la personne :");
		int id = sc.nextInt();
		update.setId(id);
		this.writeJSONupdate(update);
	}
	
	public void deleteJSON() throws IOException {
		Delete delete = new Delete();
		
		System.out.println("Choisissez l'id de la personne a supprimer :");
		int id = sc.nextInt();
		delete.setId(id);
		this.writeJSONdelete(delete);
	}
	
	public void closeConnectionJSON() throws IOException {
		String s = "request : {\r\n" + 
				"    operation_type  : 'CLOSE CONNECTION';\r\n" + 
				"};";
		client.Communiquer(s);
	}
	
	
	
	private void writeJSONinsert(Insert insert) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		mapper.writeValue(new File("insert.json"),insert);
}
	private void writeJSONselect(Select select) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		mapper.writeValue(new File("select.json"),select);
}
	private void writeJSONdelete(Delete delete) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		mapper.writeValue(new File("delete.json"),delete);
}
	private void writeJSONupdate(Update update) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		mapper.writeValue(new File("update.json"),update);
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
