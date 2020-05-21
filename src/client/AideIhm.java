package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;

public class AideIhm {
	
	private Indicator f; String s; String s1;
	
	public AideIhm(String s,String s1) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
	Client_socket c = new Client_socket();
	//c.startConnection("172.31.249.164", 2015);
	c.startConnection("127.0.0.1", 2015);

    this.f = new Indicator(c);  
    this.s=s;
    this.s1=s1;

	}
	
   
    public int getBorne() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return f.borne();
    }
    public int getCaptor() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return f.captor();
    }
    public int getTram() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.tram();
    }
   public int getEmp() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	   int v = f.emp(s);
	   return v;
   }
    public int getCarinthetown() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.carinthetown(s);
    }
    public Double getTd() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.td(s);
    }
    public Double getTp() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.tp(s);
    }
    public Double getTdn(String s,String s1) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return f.tdn(s,s1);

    }
    public Double getTpn(String s,String s1) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return f.tpn(s,s1);

    } 
    public int getCarinthetowndate() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return   f.carinthetowndate(s,s1);
    }
    public int getEmpdate() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.empdate(s,s1);
    }
    public Double getTddate() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return   f.tpdate(s,s1);
    }
    public Double getTpdate() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.tddate(s,s1);
    }
    public ArrayList<String> getTab() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.tab(s,s1);
    }
    
    public boolean getOk(String s) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        return  f.ok(s);
    }

	public boolean okdate(String s1, String s2) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		return f.okdate(s1,s2);
	}

	public ArrayList<String> geTab(String s1, String s2) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		return f.tab(s1, s2);
	}
         
          
           
          
            
        
	}
