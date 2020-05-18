package SimulatorStatement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Alert extends Thread implements ActionListener {
	private Socket socket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Message msg;
	private Json json;
	private Response rp;
	private Request r;
	private Random rdm;
	private FrameAlert f;
	
	public Alert() {}
	Alert(Response rp) {
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
			new Alert(json.deserialize(msg.readMessage(in))).start();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
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
		int counter = 0;
	//	f = new FrameAlert();
		while(true) {
			//try {
				//rp = json.deserialize(msg.readMessage(in));
				System.out.println(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)));
			/*	if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 0) {
					this.f.getCl().show(f.getP(), "p0");
					System.out.println("je suis la");
				} else if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 1) {
					this.f.getCl().show(f.getP(), "p1");
				}else if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 2) {
					this.f.getCl().show(f.getP(), "p2");
				}*/
				System.out.println("est ce que");
			//} catch (IOException e) {}
			System.out.println(rp.getA());
			r = new Request();
			r.getA().add(rp.getA().get(0));
			this.statement(counter);
		System.out.println(r.getA());
			try {
				Thread.sleep(Long.parseLong(rp.getA().get(3)) * 1000);
				r.setOperation_type("GIVE");
				System.out.println(json.serialize(r));
				msg.sendMessage(out, json.serialize(r));
				counter = counter + 1;
			} catch (NumberFormatException | InterruptedException | IOException | SQLException e1) {}
		}
	}
	
	public void random(int i) {
		int k = Integer.parseInt(rp.getA().get(i + 4));
		int rm = rdm.nextInt(k);
		r.getA().add(Integer.toString(rm));
	}
	
	public void statement(int counter) {
		for(int i = 0;  i < 8; i += 2) {
			if(i == 2) {
				double y = Double.parseDouble(rp.getA().get(6));
				y = y * 100;
				int z = 100 - (100 - (int)y);
				int xx = rdm.nextInt(z);
				y = Double.valueOf(xx) / 100;
				r.getA().add(Double.toString(y));
			} else {
				if(Integer.parseInt(rp.getA().get(0)) == 14967) {
					if(counter >= 2 && counter < 6) {
						if(i == 0) {
							int k = Integer.parseInt(rp.getA().get(i + 5));
							System.out.println(k);
							int rm = rdm.nextInt(k) + k;
							System.out.println(rm);
							r.getA().add(Integer.toString(rm));
						} else {
							this.random(i);
						}
					} else {
						this.random(i);
					}

				} else {	
					this.random(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException, NumberFormatException, InterruptedException {
		Alert i = new Alert();
		//FrameAlert f = new FrameAlert();
		i.GiveStatement("127.0.0.1", 2013);
	}
}
