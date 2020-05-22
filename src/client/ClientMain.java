package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;

public class ClientMain extends JFrame implements ActionListener {
	private JPanel panel;
	private JButton tram;
	private JButton sensor;
	private JButton bound;
	private JButton carbon;
	private JButton analyzes;
	private JLabel title;
	private static Client_socket c;
	
	ClientMain() {
		super("Smart Burger City");
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		tram = new JButton("Tram network");
		sensor = new JButton("Configure sensor(s)");
		bound = new JButton("Configure bound(s) and cars");
		carbon = new JButton("Carbon footprint");
		analyzes = new JButton("Analyzes");
		
		tram.addActionListener(this);
		sensor.addActionListener(this);
		bound.addActionListener(this);
		carbon.addActionListener(this);
		analyzes.addActionListener(this);

		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		g.fill = GridBagConstraints.BOTH;
		g.gridx = 0;
		g.gridy = 0;
		panel.add(tram, g);
		
		g.gridx = 1;
		panel.add(sensor, g);
		
		g.gridx = 2;
		panel.add(bound, g);
		
		g.gridx = 0;
		g.gridy = 1;
		panel.add(carbon, g);
		
		g.gridx = 1;
		panel.add(analyzes, g);
		
		this.getContentPane().add(panel);
		this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tram) {
			
		}
		if(e.getSource() == sensor) {
			ClientFrame cf = new ClientFrame();
			SensorListener s = new SensorListener(cf, c);
		}
		if(e.getSource() == bound) {
			BoundCarListner bcl= new BoundCarListner(c);
		}
		if(e.getSource() == carbon) {
			try {
				CarbonIhm f1 = new CarbonIhm();
			} catch (ClassNotFoundException | IOException | SQLException | InterruptedException e1) {}
		}
		if(e.getSource() == analyzes) {
			try {
				Fenetre fenetre = new Fenetre();
			} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {}
		      Indicator f = new Indicator(c);
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		c = new Client_socket();
		c.startConnection("172.31.249.164", 2015);
		ClientMain client = new ClientMain();
	}
	
	
}
