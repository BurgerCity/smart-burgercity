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

public class Normal extends Thread {
	private Socket socket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Message msg;
	private Json json;
	private Response rp;
	private Request r;
	private Random rdm;
	
	Normal() {}
	Normal(Response rp) {
		this.rp = rp;
	}
	
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
		r.setOperation_type("GIVE");
		
		while(true) {
			msg.sendMessage(out, json.serialize(r));
			new Normal(json.deserialize(msg.readMessage(in))).start();
		}
	}
	
	public void run() {
		try {
		socket = new Socket("127.0.0.1", 2018);
		out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {}
		msg = new Message();
		json = new Json();
		r = new Request();
		rdm = new Random();
		while(true) {
			System.out.println(rp.getA());
			r = new Request();
			r.getA().add(rp.getA().get(0));
			for(int i = 0;  i < 8; i += 2) {
				if(i == 2) {
					double y = Double.parseDouble(rp.getA().get(6));
					y = y * 100;
					int z = 100 - (100 - (int)y);
					int xx = rdm.nextInt(z);
					y = Double.valueOf(xx) / 100;
					//double x = rdm.nextDouble() / 4;
					r.getA().add(Double.toString(y));
				} else {
					int k = Integer.parseInt(rp.getA().get(i + 4));
					int rm = rdm.nextInt(k);
					//double random = rdm.nextDouble();
					r.getA().add(Integer.toString(rm));
				}
			}
			System.out.println(r.getA());
			try {
			Thread.sleep(Long.parseLong(rp.getA().get(3)) * 1000);
			r.setOperation_type("GIVE");

				System.out.println(json.serialize(r));
				msg.sendMessage(out, json.serialize(r));
			} catch (NumberFormatException | InterruptedException | IOException | SQLException e1) {}
		}
	}
	
	
	public static void main(String[] args) throws IOException, SQLException, NumberFormatException, InterruptedException {
		 Normal c = new Normal();
		 c.GiveStatement("127.0.0.1", 2013);
	}

	public Response getRp() {
		return rp;
	}
}
