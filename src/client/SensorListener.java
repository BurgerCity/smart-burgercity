package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JRadioButton;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;
/**
 * 
 * @author Mathias
 *This is the listener of the frame
 */
public class SensorListener implements ActionListener {
	private ClientFrame f;
	private Client_socket client;
	private Response rp;
	private ArrayList<String> listOfId = new ArrayList<String>();
	private boolean test = false;
	
	public SensorListener(ClientFrame f, Client_socket client) {
		this.f = f;
		this.client = client;
		this.f.getF1().getB1().addActionListener(this);
		this.f.getF1().getB2().addActionListener(this);
		this.f.getF2().getB().addActionListener(this);
		this.f.getF3().getB().addActionListener(this);
		this.f.getF5().getB().addActionListener(this);
		this.f.getF2().getB2().addActionListener(this);
		this.f.getF3().getB2().addActionListener(this);
		this.f.getF5().getB2().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.f.getF1().getB1()) {
			f.setSize(550, 600);
			f.getF2().getJ12().setText("");
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
			f.getF3().getJl1().setText("");
			(f.getCl()).show(f.getP(), "f3");
		}
		if(e.getSource() == this.f.getF3().getB()) {
			try {
				if(this.testJRadioBttonF3(this.f.getF3().getJr()) == false) {
					f.getF3().getJl1().setText("Please check a button");
					f.setSize(800, 300);									//if no button is selected then that refresh the frame with a text
					(f.getCl()).show(f.getP(), "f3");
				}
				else {	this.f.f4 = new Frame4(this.selectSensor());
					if(f.f4.getRp().getA().size() == 0) {
						f.getF3().getJl1().setText("There is no sensor in this localization");
						f.setSize(800, 300);
						(f.getCl()).show(f.getP(), "f3");
					} 		
					else {
						this.f.getF4().getB().addActionListener(this);
						this.f.getF4().getB2().addActionListener(this);
						this.f.getP().add("f4", this.f.f4.getJp());
						f.setSize(400, 300);
						(f.getCl()).show(f.getP(), "f4");
						test = true;
					}
				}
			} catch (IOException e1) {}
		}
		if(test == true) {
			if(e.getSource() == this.f.f4.getB()) {
				int counter = 0;
				for(int i = 0; i < this.f.f4.getJr().length; i++) {
					if(this.f.f4.getJr()[i].isSelected()) {
						listOfId.add(this.f.f4.getJl()[i].getText());
					} else {
						counter += 1;
					}
				}
				if(counter == this.f.f4.getJr().length) {
					f.getF4().getLabel().setText("Please check a button");
					(f.getCl()).show(f.getP(), "f4");
				} else {
					f.setSize(550, 600);
					f.getF5().getJ12().setText("");
					(f.getCl()).show(f.getP(), "f5");
				}
			}
			if(e.getSource() == this.f.getF4().getB2()) {
				f.setSize(800, 300);
				f.getF3().getJl1().setText("");
				(f.getCl()).show(f.getP(), "f3");
			}
		}
		if(e.getSource() == this.f.getF5().getB()) {
			if(this.sensor("UPDATE", f.getF5()) == false) {
				(f.getCl()).show(f.getP(), "f5");
			}
			else {
				f.setSize(400, 200);
				(f.getCl()).show(f.getP(), "f1");
			}
		}
		if(e.getSource() == this.f.getF2().getB2()) {
			f.setSize(400, 200);
			(f.getCl()).show(f.getP(), "f1");
		}
		if(e.getSource() == this.f.getF3().getB2()) {
			f.setSize(400, 200);
			(f.getCl()).show(f.getP(), "f1");
		}
		if(e.getSource() == this.f.getF5().getB2()) {
			f.setSize(400, 300);
			f.getF4().getLabel().setText("");
			(f.getCl()).show(f.getP(), "f4");
		}
	}
	
	public boolean testRadioButton() { //this method test if radio button are selected
		int i = 0;
		int counter = 0;
		while(i < 4) {
			if(!f.getF3().getJr()[i].isSelected()) {
				counter += 1;
			}
			i++;
		}
		if(counter == 4) return false;
		return true;
	}
	public Response selectSensor() throws IOException { //this method select the id sensor depending of which localizations selected
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
		return rp;
	}
	public boolean sensor(String st, Frame2 f) { //this method send the configuration to the database
		Request r = new Request();
		r.setOperation_type(st);
		r.setTable("sensor");
		
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
	
	public boolean testJRadioBttonF3(JRadioButton[] jr) {
		int x = 0;
		for(int i = 0; i < jr.length; i++) {
			if(!jr[i].isSelected()) {
				x += 1;
			}
		}
		if(x == jr.length) return false;
		return true;
	}
	
	public void sendRequest(Request r) {
		Message msg = new Message();
		Json json = new Json();
		try {
			msg.sendMessage(client.getOut(), json.serialize(r));
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean testIhm(Frame2 f, Request r, int n) { //this method test if the form is filled in the good way
		int i = 0;
		try {	
			if(r.getOperation_type().equals("UPDATE")) i++;
			while(i < n) {
				if(f.getTf()[i].getText().toString().length() == 0) {			//this if, test if all TextField are fill up
					f.getJ12().setText("Please fill in all fields");
					return false;
				}
				if(Double.parseDouble(f.getTf()[i].getText()) <= 0 ) {			//this if, test if there is no negative number in all Text Field
					f.getJ12().setText("<html><center>Please don't put a <br> negative number</center></html>");
					return false;
				}
				i++;
			}
			for(int j = 1; j < 9; j += 2) {		//this loop test if threshold information are not bigger than threshold alert
				if(Double.parseDouble(f.getTf()[j].getText()) > Double.parseDouble(f.getTf()[j + 1].getText())) {			
					f.getJ12().setText("<html><center>Info threshold can't be bigger <br> than alert threshold</center></html>");
					return false;
				}
			}
			//this if test if all threshold are not bigger than the standard 
			if(Integer.parseInt(f.getTf()[1].getText()) > 400 || Integer.parseInt(f.getTf()[2].getText()) > 400 || Double.parseDouble(f.getTf()[3].getText()) > 0.5
					|| Double.parseDouble(f.getTf()[4].getText()) > 0.5 || Integer.parseInt(f.getTf()[5].getText()) > 80 || Integer.parseInt(f.getTf()[6].getText()) > 80
					|| Integer.parseInt(f.getTf()[7].getText()) > 10000 || Integer.parseInt(f.getTf()[8].getText()) > 10000) {
				f.getJ12().setText("<html><center>Threshold can't be bigger <br> than the standard</center></html>");
				return false;
			}
			return true;	
		} catch (NumberFormatException e) {
			//this exception is used if the user enter a string in Text Field
			f.getJ12().setText("Please enter a number");
			return false;
		}
	}
}
