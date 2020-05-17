package SimulatorStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Random;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

public class CollectData {
	private Socket socket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Message msg;
	private Json json;
	private Response rp;
	private Request r;
	private Random rdm;
	
	public void GiveStatement(String ip, int port) throws IOException, SQLException, NumberFormatException, InterruptedException {
		socket = new Socket(ip, port);
		out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		msg = new Message();
		json = new Json();
		rp = new Response();
		r = new Request();
		rdm = new Random();
		int j = 1;
		while(true) {
			r.setOperation_type("GIVE");
			msg.sendMessage(out, json.serialize(r));
			if(j == 1) {
				rp = json.deserialize(msg.readMessage(in));
				j = 0;
			}
			System.out.println(rp.getA());
			r = new Request();
			r.getA().add(rp.getA().get(0));
			for(int i = 0;  i < 8; i += 2) {
				if(i == 2) {
					double x = rdm.nextDouble() / 4;
					r.getA().add(Double.toString(x));
				} else {
					int k = Integer.parseInt(rp.getA().get(i + 4));
					int rm = rdm.nextInt(k);
					//double random = rdm.nextDouble();
					r.getA().add(Integer.toString(rm));
				}
			}
			System.out.println(rp.getA().get(3));
			Thread.sleep(Long.parseLong(rp.getA().get(3)) * 1000);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException, SQLException, NumberFormatException, InterruptedException {
		 CollectData c = new CollectData();
		 c.GiveStatement("127.0.0.1", 2013);
	}
}
