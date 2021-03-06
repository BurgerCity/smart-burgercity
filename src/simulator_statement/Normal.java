package simulator_statement;

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
/**
 * 
 * @author Mathias
 *	This class is the simulator
 */
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
	
	public Normal() {
	}
	Normal(Response rp, int x) {
		this.rp = rp;
		this.x = x;
	}
	
	//this method is used to launch the simulator, 
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
			msg.sendMessage(out, json.serialize(r));						//the simulator sends to the server
			new Normal(json.deserialize(msg.readMessage(in)), x).start();	//when the server answers, a thread is created
		}
	}
	
	public void run() {						
		try {
			socket = new Socket("172.31.249.164", 2018);											//a socket is created for each sensor
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
			
			this.statement();			//this method do the statement with an appropriate random 
			
			System.out.println(rp.getA());
			

			
			try {
			Thread.sleep(Long.parseLong(rp.getA().get(3)) * 1000); 		//this line allows to do a statement every x minutes
			
			r.setOperation_type("GIVE");
			
			System.out.println(" ");
			System.out.println("********************************************");
			System.out.println(" ");
			
			System.out.println(json.serialize(r));
			

			
			msg.sendMessage(out, json.serialize(r));		//this method allows to send statements to the server
			

			
			rp = new Response();
			
			rp = json.deserialize(msg.readMessage(in));		//the simulator recovers data of the sensor
			
			counter = counter + 1;						//this variable is used for the test
			System.out.println("Type of the alert " + rp.getA().get(rp.getA().size() - 1) + " for the sensor " + rp.getA().get(0));
			
			this.switchFrame(rp);				//this method enable to switch JPanel depending of the level of alert, if it's 0, 1 or 2

			} catch (NumberFormatException | InterruptedException | IOException | SQLException e1) {}
		}
	}
	
	public void random(int i) {			//this method do a random
		int k;
		if(x == 6) {										//
			k = Integer.parseInt(rp.getA().get(i + 4));		//	conditions are used for the test
		} else {				
			k = Integer.parseInt(rp.getA().get(i + x));
		}
		int rm = rdm.nextInt(k);
		r.getA().add(Integer.toString(rm));
	}
	
	public void statement() {
		for(int i = 0;  i < 8; i += 2) {			//this loop add in the arraylist each random statement depending of the data of the sensor
			if(i == 2) {
				
				double y = Double.parseDouble(rp.getA().get(6));		//it's double because there is a threshold between 0 and 0.5
				y = y * 100;
				int z = 100 - (100 - (int)y);
				int xx = rdm.nextInt(z);
				y = Double.valueOf(xx) / 100;
				r.getA().add(Double.toString(y));
			
			} else {
				
				if(x == 4 || x == 6) {										//this condition is for the test
					
					if(Integer.parseInt(rp.getA().get(0)) == 1) {
						
						if(counter >= 2 && counter < 6) {
							
							if(i == 0) {
								
								int k = 0;
								if(x == 6) {
									k = Integer.parseInt(rp.getA().get(i + 5));
								} else if(x == 4){
									k = Integer.parseInt(rp.getA().get(i + x));
								}	
								int rm = rdm.nextInt(k) + k;
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
					this.random(i);				//this method do a random 
				}
			}
		}
	}

	public void switchFrame(Response r) {				//this method create a frame when the alert is either 1 or 2
		if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 1 || Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 2) {
			if(frame == true) {
				f = new FrameAlert();
				frame = false;
			}
			if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 1) {		//when the alert is 1 then the frame is displayed
				if(bb == false) {		//there is a boolean, it's for not add "<br>for the sensor" everytime
					this.getF().getP1().getL().setText("<html><center><h1> " + this.getF().getP1().getL().getText() + " <br>for the sensor " + rp.getA().get(0) + "</h1></center></html>");
					this.getF().getCl().show(f.getP(), "p1");
					bb = true;
				} else {
					this.getF().getCl().show(f.getP(), "p1");
				}
			} else if(Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 2) {		//when the alert is 2 then the frame is displayed
				if(b == false) {			//there is a boolean, it's for not add "<br>for the sensor" everytime
					this.getF().getP2().getL().setText("<html><center><h1> " + this.getF().getP2().getL().getText() + " <br>for the sensor " + rp.getA().get(0) + "</h1></center></html>");
					this.getF().getCl().show(f.getP(), "p2");
					b = true;
				} else {
					this.getF().getCl().show(f.getP(), "p2");
				}		
			}
			boo = false;
		}
		else if (Integer.parseInt(rp.getA().get(rp.getA().size() - 1)) == 0) {		//when the alert is 0 then the frame is displayed
			if(boo == false) {
				if(bool == true) {		//there is a boolean, it's for not add "<br>for the sensor" everytime
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
