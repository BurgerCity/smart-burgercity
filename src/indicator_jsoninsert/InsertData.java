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

/**
 * 
 * @author tarshiniparameswaran
 *
 */

public class InsertData {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Message msg = new Message();
		Request r = new Request();
		ObjectMapper ob = new ObjectMapper();
		Client_socket c = new Client_socket();
		OutputStreamWriter out = 	c.startConnection("172.31.249.164", 2015);
		//OutputStreamWriter out =c.startConnection("127.0.0.1", 2015);

/*		DONT USE FOR OUR SPECIFITIES BUT FONCTIONAL
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
	*/
		String chaine="";
	      try{
	         InputStream ips = new FileInputStream("statements2.json");
	       //  InputStream ips = new FileInputStream("src/indicator_jsoninsert/statements2.json");
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
       try{
          InputStream neo = new FileInputStream("Insertstatements.json");
	     //    InputStream ips = new FileInputStream("src/indicator_jsoninsert/statements2.json");
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
       
   	Message msg1 = new Message();
	Request r1 = new Request();
	ObjectMapper ob1 = new ObjectMapper();
	OutputStreamWriter out1 = 	c.startConnection("172.31.249.164", 2015);
	//OutputStreamWriter out1 =c.startConnection("127.0.0.1", 2015);

       
   	String chain = "";
       try{
          //InputStream ip = new FileInputStream("src/indicator_jsoninsert/statement3.json");
          InputStream ip = new FileInputStream("statement3.json");
          InputStreamReader i = new InputStreamReader(ip);
          BufferedReader b = new BufferedReader(i);
          String lign;
          while ((lign = b.readLine()) != null){
             chain+=lign;
          }
          System.out.println(chain);
          b.close();
       }    
       catch (Exception e){
          System.out.println(e.toString());
       }
       msg.sendMessage(out1, chain);
}


}

