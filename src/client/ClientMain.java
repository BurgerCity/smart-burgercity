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

public class ClientMain {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		Client_socket c = new Client_socket();
		c.startConnection("127.0.0.1", 2017);
		//ClientFrame cf = new ClientFrame();
	//	SensorListner s = new SensorListner(cf, c);
        Indicator f = new Indicator(c);
      //  f.car();
        //f.borne();
    //    f.captor();
        //    f.tram();
        //f.emp("2013-06-01");
        //    f.carinthetown("2013-06-01");
        //   f.td("2013-06-01");
        // f.tp("2013-06-01");
        //   f.tdn("2013-06-01","sud");
        //  f.tpn("2013-06-01","sud");
        //   f.carinthetowndate("2013-06-01","2013-06-15");
        //    f.empdate("2013-06-01","2013-06-15");
        //    f.tpdate("2013-06-01","2013-06-15");
        //    f.tddate("2013-06-01","2013-06-15");
       // f.tab("2013-06-01","2013-06-15");
	}
}
