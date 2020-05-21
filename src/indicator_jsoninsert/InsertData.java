package indicator_jsoninsert;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.Client_socket;
import client_common.Json;
import common.Message;
import common.Request;

public class InsertData {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Message msg = new Message();
		Request r = new Request();
		ObjectMapper ob = new ObjectMapper();
		Client_socket c = new Client_socket();
		OutputStreamWriter out = c.startConnection("127.0.0.1", 2015);
		
		String chaine="";
	       
	      //lecture du fichier texte
	      try{
	         InputStream ips = new FileInputStream("src/indicator_jsoninsert/sensor.json");
	         InputStreamReader in = new InputStreamReader(ips);
	         BufferedReader br = new BufferedReader(in);
	         String ligne;
	         while ((ligne = br.readLine()) != null){
	            chaine+=ligne;
	         }
	         System.out.println(chaine);
	         br.close();
	      }    
	      catch (Exception e){
	         System.out.println(e.toString());
	      }
	      msg.sendMessage(out, chaine);
	
	
    String chignon = "";
   	//lecture du fichier texte
       try{
          InputStream neo = new FileInputStream("src/indicator_jsoninsert/Insertstatements.json");
          InputStreamReader zac = new InputStreamReader(neo);
          BufferedReader fred = new BufferedReader(zac);
          String lilo;
          while ((lilo = fred.readLine()) != null){
             chignon+=lilo;
          }
          System.out.println(chignon);
          fred.close();
       }    
       catch (Exception e){
          System.out.println(e.toString());
       }
       msg.sendMessage(out, chignon);
    
}


}

