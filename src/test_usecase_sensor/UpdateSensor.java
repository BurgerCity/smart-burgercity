package test_usecase_sensor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.Client_socket;
import common.Message;
import common.Request;

public class UpdateSensor {
	public static void main(String[] agrs ) throws IOException {
		Message msg = new Message();
		Request r = new Request();
		ObjectMapper ob = new ObjectMapper();
		Client_socket c = new Client_socket();
		OutputStreamWriter out = c.startConnection("127.0.0.1", 2013);
		
		String chaine="";
	       
	      //lecture du fichier texte
	      try{
	         InputStream ips = new FileInputStream("src/test_usecase_sensor/updateSensor.json");
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
	}
}
