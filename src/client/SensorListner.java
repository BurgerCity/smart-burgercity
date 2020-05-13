package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import client_common.Json;
import common.Message;
import common.Request;

public class SensorListner implements ActionListener {
	ClientFrame f;
	Client_socket client;
	public SensorListner(ClientFrame f, Client_socket client) {
		this.f = f;
		this.client = client;
		this.f.getF1().getB1().addActionListener(this);
		this.f.getF1().getB2().addActionListener(this);
		this.f.getF2().getB().addActionListener(this);
		this.f.getF3().getB().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.f.getF1().getB1()) {
			f.setSize(550, 800);
			(f.getCl()).show(f.getP(), "f2");
		}
		if(e.getSource() == this.f.getF2().getB()) {
			this.insertSensor();
			f.setSize(300, 100);
			(f.getCl()).show(f.getP(), "f1");
		}
		if(e.getSource() == this.f.getF1().getB2()) {
			f.setSize(370, 150);
			(f.getCl()).show(f.getP(), "f3");
		}
		if(e.getSource() == this.f.getF3().getB()) {
			this.selectSensor();
		}
	}
	public void selectSensor() {
		Request r = new Request();
		r.setOperation_type("SELECT");
		r.setTable("sensor");
		r.getA().add("id_sensor");
		r.getA().add("localization");
		r.getA().add(f.getF3().getLocalisation().getSelectedItem().toString());
		this.sendRequest(r);
		
	}
	public void insertSensor() {
		Request r = new Request();
		r.setOperation_type("INSERT");
		r.setTable("sensor");
		//String v = f.getF2().getLocalisation().getSelectedItem().toString();
		r.getA().add(f.getF2().getLocalisation().getSelectedItem().toString());
		r.getA().add(f.getF2().getTf()[9].getText());
		r.getA().add(f.getF2().getTf()[10].getText());
		for(int i = 1; i < 9; i++) {
			r.getA().add(f.getF2().getTf()[i].getText());
		}
		r.getA().add(f.getF2().getTf()[0].getText());
		this.sendRequest(r);

	}
	
	public void sendRequest(Request r) {
		Message msg = new Message();
		Json json = new Json();
		String s = "";
		try {
			System.out.println(json.serialize(r));
			s = json.serialize(r);
			System.out.println(client.getOut());
			msg.sendMessage(client.getOut(), s);
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}
