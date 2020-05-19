package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;

public class ClientIndic {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
	Client_socket c = new Client_socket();
	c.startConnection("127.0.0.1", 2015);
     Indicator f = new Indicator(c);      
      Fenetre fenetre = new Fenetre();
      f.tpn("2013-06-01", "nord");
 
	}
}