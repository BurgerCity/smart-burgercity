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
	//c.startConnection("172.31.249.164", 2015);
	c.startConnection("127.0.0.1", 2015);
     Fenetre fenetre = new Fenetre();
      Indicator f = new Indicator(c);
     /* System.out.println("OKKK" + f.ok("2020-05-21"));
      System.out.println("OKKK" +  f.emp("2020-05-21") ) ;
      System.out.println("OKKK" +  f.carinthetown("2020-05-21")) ;
      System.out.println("OKKK" + 	 f.tp("2020-05-21") ) ;
  	 f.td("2020-05-21") ;*/

      
 /* 	String s  = "2020-05-21";
    String s1 = "2020-05-20";
    
     Indicator f = new Indicator(c);
      System.out.println("BORNE "+f.borne());
      System.out.println("CAPTERU "+f.captor());
      System.out.println("Carinthetown " + f.carinthetown(s));
      System.out.println("CarinthetownDate " +f.carinthetowndate(s, s1));
      System.out.println("EMPCARBO " +f.emp(s));
      System.out.println("EMPCARBO DATE " +f.empdate(s, s1));
      System.out.println("OK " +f.ok(s));
      System.out.println("OKDATE " +f.okdate(s, s1));
      System.out.println("TAB " +f.tab(s, s1));
      System.out.println("TD " +f.td(s));
      System.out.println("TDDATE " +f.tddate(s, s1));
      System.out.println("TDN " +f.tdn(s, "north"));
      System.out.println("TP " +f.tp(s));
      System.out.println("TPAC " +f.tpac(s));
      System.out.println("tpacp " +f.tpacp(s, s1));
      System.out.println("tpadate " +f.tpadate(s, s1));
      System.out.println("tpbc " +f.tpbc());
      System.out.println("tpdate " +f.tpdate(s, s1));
      System.out.println("tpn " +f.tpn(s, "north"));*/
	}
}