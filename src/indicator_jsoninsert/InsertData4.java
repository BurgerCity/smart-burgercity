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

public class InsertData4 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Message msg = new Message();
		Request r = new Request();
		ObjectMapper ob = new ObjectMapper();
		Client_socket c = new Client_socket();
		OutputStreamWriter out = 	c.startConnection("172.31.249.164", 2015);

		
		 String rr = "";
			//lecture du fichier texte
		    try{
		       InputStream ss = new FileInputStream("src/indicator_jsoninsert/updatecarmax.json");
		       InputStreamReader isg = new InputStreamReader(ss);
		       BufferedReader tt = new BufferedReader(isg);
		       String ligp;
		       while ((ligp = tt.readLine()) != null){
		          rr+=ligp;
		       }
		       System.out.println(rr);
		       tt.close();
		    }    
		    catch (Exception e){
		       System.out.println(e.toString());
		    }
		    msg.sendMessage(out, rr);
		    
		    
		   
		          
		}


		}
	