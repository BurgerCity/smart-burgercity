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

public class InsertData2 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Message msg = new Message();
		Request r = new Request();
		ObjectMapper ob = new ObjectMapper();
		Client_socket c = new Client_socket();
		OutputStreamWriter out = c.startConnection("127.0.0.1", 2015);
	
	String chain = "";
	//lecture du fichier texte
    try{
       InputStream ip = new FileInputStream("src/indicator_jsoninsert/Insertstations.json");
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
    msg.sendMessage(out, chain);


	String chai = "";
	//lecture du fichier texte
    try{
       InputStream ipt = new FileInputStream("src/indicator_jsoninsert/Insertcar.json");
       InputStreamReader is = new InputStreamReader(ipt);
       BufferedReader bs = new BufferedReader(is);
       String lig;
       while ((lig = bs.readLine()) != null){
          chai+=lig;
       }
       System.out.println(chai);
       bs.close();
    }    
    catch (Exception e){
       System.out.println(e.toString());
    }
    msg.sendMessage(out, chai);
    
  
          
}


}