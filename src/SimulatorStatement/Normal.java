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
	private FrameAlert f;
	private int x;
	private boolean b = false;
	private boolean bb = false;
	private boolean frame = true;
	private boolean boo = true;
	private boolean bool = true;
	private int counter = 0;
	
	Normal() {
	}
	Normal(Response rp, int x) {
		this.rp = rp;
		this.x = x;
	}
	
	public void GiveStatement(String ip, int port, int x) throws IOException, SQLException, NumberFormatException, InterruptedException {
		socket = new Socket(ip, port);
		out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		msg = new Message();
		json = new Json();
		rp = new Response();
		r = new Request();
		rdm = new Random();
		r.setOperation_type("GIVE");
		while(true) {
			msg.sendMessage(out, json.serialize(r));
			new Normal(json.deserialize(msg.readMessage(in)), x).start();
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
			
			r = new Request();
			r.getA().add(rp.getA().get(0));
			
			this.statement();
			System.out.println(rp.getA());
			try {
			Thread.sleep(Long.parseLong(rp.getA().get(3)) * 1000);
			r.setOperation_type("GIVE");
			System.out.println(json.serialize(r));
			msg.sendMessage(out, json.serialize(r));
			rp = new Response();
			rp = json.deserialize(msg.readMessage(in));
			counter = counter + 1;
			System.out.println("ALERTE EST DE " + rp.getA().get(rp.getA().size() - 1));
			this.switchFrame(rp);

			} catch (NumberFormatException | InterruptedException | IOException | SQLException e1) {}
		}
	}
	
	public void random(int i) {
		int k;
		if(x == 6) {
			k = Integer.parseInt(rp.getA().get(i + 4));
		} else {
			k = Integer.parseInt(rp.getA().get(i + x));
		}
		int rm = rdm.nextInt(k/* - (k/4)*/);
		r.getA().add(Integer.toString(rm));
	}
	
	public void statement() {
		for(int i = 0;  i < 8; i += 2) {
			if(i == 2) {
				double y = Double.parseDouble(rp.getA().get(6));
				y = y * 100;
				int z = 100 - (100 - (int)y);
				int xx = rdm.nextInt(z);
				y = Double.valueOf(xx) / 100;
				r.getA().add(Double.toString(y));
			} else {
				if(x == 4 || x == 6) {
					if(Integer.parseInt(rp.getA().get(0)) == 14967) {
						if(counter >= 2 && counter < 8) {
							if(i == 0) {
								int k = 0;
								if(x == 6) {
									System.out.println("je suis la fdp");
									k = Integer.parseInt(rp.getA().get(i + 5));
								} else if(x == 4){
									k = Integer.parseInt(rp.getA().get(i + x));
								}	
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
				} else {
					this.random(i);
				}
			}
		}
	}

	public void switchFrame(Response r) {
		if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 1 || Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 2) {
			if(frame == true) {
				f = new FrameAlert();
				frame = false;
			}
			if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 1) {
				if(bb == false) {
					this.getF().getP1().getL().setText("<html><center><h1> " + this.getF().getP1().getL().getText() + " <br>for the sensor " + rp.getA().get(0) + "</h1></center></html>");
					this.getF().getCl().show(f.getP(), "p1");
					bb = true;
				} else {
					this.getF().getCl().show(f.getP(), "p1");
				}
			} else if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 2) {
				if(b == false) {
					this.getF().getP2().getL().setText("<html><center><h1> " + this.getF().getP2().getL().getText() + " <br>for the sensor " + rp.getA().get(0) + "</h1></center></html>");
					this.getF().getCl().show(f.getP(), "p2");
					b = true;
				} else {
					this.getF().getCl().show(f.getP(), "p2");
				}		
			}
			boo = false;
		}
		else if (Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 0) {
			if(boo == false) {
				if(bool == true) {
					this.getF().getP0().getL().setText("<html><center><h1> " + this.getF().getP0().getL().getText() + " <br>for the sensor " + rp.getA().get(0) + "</h1></center></html>");
					this.getF().getCl().show(f.getP(), "p0");
					bool = false;
				} else {
					this.getF().getCl().show(f.getP(), "p0");
				}
			}
		}
	}
	
	public Response getRp() {
		return rp;
	}
	public FrameAlert getF() {
		return f;
	}
}
