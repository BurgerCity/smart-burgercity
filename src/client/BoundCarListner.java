package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

public class BoundCarListner implements ActionListener {
	BoundMainPanel bmp=new BoundMainPanel();
	Client_socket client=new Client_socket();
	ThreadAutomode thread;
	boolean tt=true;
	boolean tc=true;
	private int maxcar;
	BoundCarListner(Client_socket client){
		bmp.getB1().getAuto().addActionListener(this);
		bmp.getB1().getManu().addActionListener(this);
		bmp.getB2().getBack().addActionListener(this);
		bmp.getB2().refresh.addActionListener(this);
		bmp.getB1().maxcar.addActionListener(this);
		bmp.getB1().refresh.addActionListener(this);

		bmp.b3.openall.addActionListener(this);
		bmp.b3.closeall.addActionListener(this);
		bmp.b3.back.addActionListener(this);
		
		this.client=client;
		

	}
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()== this.bmp.getB1().getAuto()) {
				try {
					autobounds(client);
					if(tt) {
						textfill(client);
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				(bmp.getC()).show(bmp.getJp(), "f2");

			}
			if(e.getSource()==bmp.getB1().getManu()) {
				manumode();
				(bmp.getC()).show(bmp.getJp(), "f3");
			
			}
			if(e.getSource()==bmp.b3.openall) {
				this.openAll(client);
			}
			if(e.getSource()==bmp.b3.closeall) {
				this.closeAll(client);
			}
			if(e.getSource()==bmp.b2.back) {
				(bmp.getC()).show(bmp.getJp(), "f1");
			}
			if(e.getSource()==bmp.b3.back) {
				(bmp.getC()).show(bmp.getJp(), "f1");
			}
			if(e.getSource()==bmp.b2.refresh) {
				try {
					autobounds(client);
					if(tt) {
						textfill(client);
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource()==bmp.getB1().maxcar) {
				
				maxcar=Integer.parseInt(bmp.getB1().maxcar.getText());
				try {
						setmaxcar(client,maxcar);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				
				try {				
					bmp.getB1().car.setText(""+currcar(client));
					System.out.println("*****************");				

				} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
					e1.printStackTrace();
				}			
			}
			if(e.getSource()==bmp.getB1().refresh) {
				try {
					bmp.getB1().car.setText(""+currcar(client));
				} catch (IOException | ClassNotFoundException | SQLException | InterruptedException e1) {
					e1.printStackTrace();
				}
				}
			
	}
	
	public int getMaxcar() {
		return maxcar;
	}
	public void setMaxcar(int maxcar) {
		this.maxcar = maxcar;
	}
	public void manumode() {
		Request rq=new Request();
		rq.setOperation_type("MANUAL_BOUNDS");
		rq.setTable("bound");
		rq.getA().add("0");
		Json json=new Json(client);
		json.sendRequest(rq);
		}
	public void openAll(Client_socket client) {
		Request rq=new Request();
		rq.setOperation_type("OPEN_BOUNDS");
		rq.setTable("bound");
		rq.getA().add("true");
		Json json=new Json(client);
		json.sendRequest(rq);
	}
	public void closeAll(Client_socket client) {
		Request rq=new Request();
		rq.setOperation_type("CLOSE_BOUNDS");
		rq.setTable("bound");
		rq.getA().add("false");
		Json json=new Json(client);
		json.sendRequest(rq);
	}
	public void textfill(Client_socket client) throws IOException {
		bmp.b2.borne1.setText(""+borne(1,client));
		bmp.b2.borne2.setText(""+borne(2,client));
		bmp.b2.borne3.setText(""+borne(3,client));
		bmp.b2.borne4.setText(""+borne(4,client));
		bmp.b2.borne5.setText(""+borne(5,client));
		bmp.b2.borne6.setText(""+borne(6,client));
		bmp.b2.borne7.setText(""+borne(7,client));
		bmp.b2.borne8.setText(""+borne(8,client));
		
	}
	public boolean borne(int id,Client_socket client) throws IOException {
		boolean b=true;
		String s;
		Request r=new Request();
		r.setOperation_type("SELECT_BOUND_STATUS");
		r.setTable("bound");
		r.getA().add(""+id);
		Json j=new Json(client);
		j.sendRequest(r);
		Response rp=new Response();
		Message m=new Message();
		s=m.readMessage(client.getIn());
		rp=j.deserialize(s);
		b=Boolean.parseBoolean(rp.getA().get(0));
		return b;
	}
	public void autobounds(Client_socket client) throws JsonMappingException, JsonProcessingException, IOException {
		Request rq=new Request();
		rq.setOperation_type("AUTO_BOUNDS");
		rq.setTable("bound");
		rq.getA().add("0");
		Json json=new Json(client);
		json.sendRequest(rq);
		Response rp=new Response();
		Message m=new Message();
		rp=json.deserialize(m.readMessage(client.getIn()));
		tt=Boolean.parseBoolean(rp.getA().get(0));
	}
	public void setmaxcar(Client_socket client,int i) throws JsonMappingException, JsonProcessingException, IOException {
		Request rq=new Request();
		rq.setOperation_type("SET_MAXCAR");
		rq.getA().add(Integer.toString(i));
		Json json=new Json(client);
		json.sendRequest(rq);
		Response rp=new Response();
		Message m=new Message();
		rp=json.deserialize(m.readMessage(client.getIn()));
	}
	
	public int currcar(Client_socket client) throws SQLException, ClassNotFoundException, IOException, InterruptedException{
		int n=0;
		//calculates the number of cars in the town 
		Request r=new Request();
		Json j=new Json(client);
		r.setOperation_type("COUNT_CAR");
		r.setTable("car");
		r.getA().add("0");
		j.sendRequest(r);
		//Request sent out 
		Message m=new Message();
		Response rp = new Response();
		String stt=m.readMessage(client.getIn());
		//Response received

		rp=j.deserialize(stt);

		n=Integer.parseInt(rp.getA().get(0));
		System.out.println(n);
		return n;
	}
}
