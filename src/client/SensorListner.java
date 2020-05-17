package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

public class SensorListner implements ActionListener {
	ClientFrame f;
	Client_socket client;
	Response rp;
	ArrayList<String> listOfId = new ArrayList<String>();
	boolean test = false;
	public SensorListner(ClientFrame f, Client_socket client) {
		this.f = f;
		this.client = client;
		this.f.getF1().getB1().addActionListener(this);
		this.f.getF1().getB2().addActionListener(this);
		this.f.getF2().getB().addActionListener(this);
		this.f.getF3().getB().addActionListener(this);
		this.f.getF5().getB().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.f.getF1().getB1()) {
			f.setSize(550, 600);
			(f.getCl()).show(f.getP(), "f2");
		}
		if(e.getSource() == this.f.getF2().getB()) {
			if(this.sensor("INSERT", f.getF2()) == false) {
				(f.getCl()).show(f.getP(), "f2");
			} else {
				f.setSize(400, 200);
				(f.getCl()).show(f.getP(), "f1");
			}
		}
		if(e.getSource() == this.f.getF1().getB2()) {
			f.setSize(370, 150);
			(f.getCl()).show(f.getP(), "f3");
		}
		if(e.getSource() == this.f.getF3().getB()) {
			try {
				//rp = this.selectSensor();
				this.f.f4 = new Frame4(this.selectSensor());
				this.f.getF4().getB().addActionListener(this);
				this.f.getP().add("f4", this.f.f4.getJp());
				f.setSize(400, 300);
				(f.getCl()).show(f.getP(), "f4");
				test = true;
			} catch (IOException e1) {}
		}
		if(test == true) {
			if(e.getSource() == this.f.f4.getB()) {
				for(int i = 0; i < this.f.f4.getJr().length; i++) {
					if(this.f.f4.getJr()[i].isSelected()) {
						listOfId.add(this.f.f4.getJl()[i].getText());
					}
				}
				System.out.println(listOfId);
				test = false;
				f.setSize(550, 600);
				(f.getCl()).show(f.getP(), "f5");
			}
		}
		if(e.getSource() == this.f.getF5().getB()) {
			if(this.sensor("UPDATE", f.getF5()) == false) {
				//this.f.getF5() = new Frame2("update");
				(f.getCl()).show(f.getP(), "f5");
				
			}
			else {
				f.setSize(300, 100);
				(f.getCl()).show(f.getP(), "f1");
			}
		}
	}
	public Response selectSensor() throws IOException {
		Request r = new Request();
		r.setOperation_type("SELECT");
		r.setTable("sensor");
		r.getA().add("1");
		r.getA().add("id_sensor");
		int i = 0;
		while(i < 4) {
			if(f.getF3().getJr()[i].isSelected()) {
				r.getA().add("localization");
				r.getA().add(f.getF3().getJr()[i].getText());
			}
			i++;
		}
		this.sendRequest(r);
		Message m = new Message();
		Json js = new Json();
		rp = new Response();
		String st = m.readMessage(client.getIn());
		System.out.println(st);
		rp = js.deserialize(st);
		System.out.println(rp.getA());
		return rp;
	}
	public boolean sensor(String st, Frame2 f) {
		Request r = new Request();
		r.setOperation_type(st);
		r.setTable("sensor");
		//String v = f.getF2().getLocalisation().getSelectedItem().toString();
		/*for(int i = 1; i < f.getTf().length; i++) {
			System.out.println(f.getTf()[i].getText().toString());
			System.out.println(f.getTf()[i].getText().length());
			if(f.getTf()[i].getText().toString().length() == 0) {
				System.out.println(f.getTf()[i].getText().length());
				f.getJ12().setText("Please fill in all fields");
				return false;
			}
		}*/
		if(this.testIhm(f, r, f.getTf().length) == false) return false;
		r.getA().add(f.getLocalisation().getSelectedItem().toString());
		r.getA().add(f.getTf()[9].getText());
		r.getA().add(f.getTf()[10].getText());
		for(int i = 1; i < 9; i++) {
			r.getA().add(f.getTf()[i].getText());
		}
		r.getA().add("0");
		if(st.equals("INSERT"))	{
			r.getA().add(f.getTf()[0].getText());
		} else {
			Iterator<String> it = listOfId.iterator();
			while(it.hasNext()) {
				String s = it.next();
				r.getA().add(s);
				System.out.println(r.getA());
			}
		}
		this.sendRequest(r);
		return true;

	}
	
	public void sendRequest(Request r) {
		Message msg = new Message();
		Json json = new Json();
		String s = "";
		try {
			s = json.serialize(r);
			System.out.println(s);
			msg.sendMessage(client.getOut(), s);
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean testIhm(Frame2 f, Request r, int n) {
		int i = 0;
		if(r.getOperation_type().equals("UPDATE")) i++;
		while(i < n) {
			if(f.getTf()[i].getText().toString().length() == 0) {
				System.out.println(f.getTf()[i].getText().length());
				f.getJ12().setText("Please fill in all fields");
				return false;
			}
			if(Double.parseDouble(f.getTf()[i].getText()) <= 0 ) {
				f.getJ12().setText("Please don't put a negative number");
				return false;
			}
			i++;
		}
		for(int j = 1; j < 9; j += 2) {
			if(Double.parseDouble(f.getTf()[j].getText()) > Double.parseDouble(f.getTf()[j + 1].getText())) {
				f.getJ12().setText("<html><center>Info threshold can't be bigger <br> thant alert threshold</center></html>");
				return false;
			}
		}
		if(Integer.parseInt(f.getTf()[1].getText()) > 400 || Integer.parseInt(f.getTf()[2].getText()) > 400 || Double.parseDouble(f.getTf()[3].getText()) > 0.5
				|| Double.parseDouble(f.getTf()[4].getText()) > 0.5 || Integer.parseInt(f.getTf()[5].getText()) > 80 || Integer.parseInt(f.getTf()[6].getText()) > 80
				|| Integer.parseInt(f.getTf()[7].getText()) > 10000 || Integer.parseInt(f.getTf()[8].getText()) > 10000) {
			f.getJ12().setText("Threshold can't be bigger than the standard");
			return false;
		}
		return true;		
	}
}
