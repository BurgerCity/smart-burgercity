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

public class InsertData3 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Message msg = new Message();
		Request r = new Request();
		ObjectMapper ob = new ObjectMapper();
		Client_socket c = new Client_socket();
		OutputStreamWriter out = c.startConnection("127.0.0.1", 2015);
	

    String chalet = "";
	//lecture du fichier texte
    try{
       InputStream les = new FileInputStream("src/indicator_jsoninsert/Insertbound.json");
       InputStreamReader va = new InputStreamReader(les);
       BufferedReader ca = new BufferedReader(va);
       String nces;
       while ((nces = ca.readLine()) != null){
          chalet+=nces;
       }
       System.out.println(chalet);
       ca.close();
    }    
    catch (Exception e){
       System.out.println(e.toString());
    }
    msg.sendMessage(out, chalet);

  
       String ore = "";
      	//lecture du fichier texte
          try{
             InputStream lie = new FileInputStream("src/indicator_jsoninsert/insertcarbon.json");
             InputStreamReader chi = new InputStreamReader(lie);
             BufferedReader ne = new BufferedReader(chi);
             String ja;
             while ((ja = ne.readLine()) != null){
                ore+=ja;
             }
             System.out.println(ore);
             ne.close();
          }    
          catch (Exception e){
             System.out.println(e.toString());
          }
          msg.sendMessage(out, ore);
          
          
}


}