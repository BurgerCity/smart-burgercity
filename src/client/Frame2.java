package client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame2 extends Container {
	String[] items = {"north", "south", "west", "east"};
	JComboBox localisation;
	JPanel j;
	JPanel jp1;
	JPanel jp2;

	JPanel jp3;
	JPanel jp4;

	JTextField[] tf = new JTextField[11];
	JButton b;
	//GridLayout gl = new GridLayout(12,2);
	JLabel j1;
	JLabel j2;	
	JLabel j3;	
	JLabel j4;	
	JLabel j5;	
	JLabel j6;	
	JLabel j7;	
	JLabel j8;	
	JLabel j9;
	JLabel j10;	
	JLabel j11;
	JLabel j12;
	String st;
	JLabel[] jlb;

	Frame2(String st) {
		this.st = st;
		j = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		if(st.equals("insert")) {
			jp1 = new JPanel(new GridLayout(1,1));
		}
		jp2 = new JPanel(new GridLayout(1,1));
		jp3 = new JPanel(new GridLayout(10,2));
		jp4 = new JPanel(new GridLayout(0,2));
		
		
		localisation = new JComboBox(items);
		for(int i = 0; i < 11; i++) {
			tf[i] = new JTextField();
			//tf[i].setPreferredSize(new Dimension(50, 10));
		}
		jlb = new JLabel[12];
		b = new JButton("Validate");
		j1 =new JLabel("Number of sensors to configure : "); 			jlb[0] = j1;
		j2 =new JLabel("Nitrogen Dioxide information threshold : ");	jlb[1] = j2;
		j3 =new JLabel("<html>Nitrogen Dioxide alert threshold <br> <center>(less than 400 µm^3) : </center></html>");			jlb[2] = j3;
		j4 =new JLabel("Lead information threshold : ");				jlb[3] = j4;
		j5 =new JLabel("<html>Lead alert threshold <br> <center>(less than 0,5 µm^3) : </center></html>");						jlb[4] = j5;
		j6 =new JLabel("Fine Particle information threshold : ");		jlb[5] = j6;
		j7 =new JLabel("<html>Fine Particle alert threshold <br> <center> (less than 80 µm^3) : </center></html>");				jlb[6] = j7;
		j8 =new JLabel("Carbon Monoxide information threshold : ");		jlb[7] = j8;
		j9 =new JLabel("<html>Carbon Monoxide alert threshold <br> <center>(less than 10000 µm^3) : </center></html>");			jlb[8] = j9;
		j10 =new JLabel("Number of statements before alert : ");		jlb[9] = j10;
		j11 =new JLabel("Number of minutes between each statement : ");	jlb[10] = j11;
		j12 = new JLabel(""); jlb[11] = j12;
		for(int i = 0; i < 12; i++) {
			jlb[i].setHorizontalAlignment(JLabel.CENTER);
		}
		if(st.equals("insert")) {
			jp1.add(j1);
			jp1.add(tf[0]);
			g.gridy = 0;
			g.gridx = 0;
			g.weightx = 0.5;
			g.weighty = 0.1;
			g.fill = GridBagConstraints.BOTH;
			j.add(jp1, g);
			g.fill = GridBagConstraints.NONE;
		}
		
		jp2.add(localisation);
		g.gridx = 0;
		g.gridy = 1;
		j.add(jp2, g);

		
		
		/*j.add(j1);
		j.add(tf[0]);
		j.add(localisation);*/
		jp3.add(j2);
		jp3.add(tf[1]);
		jp3.add(j3);
		jp3.add(tf[2]);
		jp3.add(j4);
		jp3.add(tf[3]);
		jp3.add(j5);
		jp3.add(tf[4]);
		jp3.add(j6);
		jp3.add(tf[5]);
		jp3.add(j7);
		jp3.add(tf[6]);
		jp3.add(j8);
		jp3.add(tf[7]);
		jp3.add(j9);
		jp3.add(tf[8]);
		jp3.add(j10);
		jp3.add(tf[9]);
		jp3.add(j11);
		jp3.add(tf[10]);
		g.gridx = 0;
		g.gridy = 2;
		g.weighty = 0.7;
		g.weightx = 0.5;
		g.fill = GridBagConstraints.BOTH;
		j.add(jp3, g);
		
		jp4.add(b);
		jp4.add(j12);
		g.gridx = 0;
		g.gridy = 3;
		g.weightx = 0.25;
		g.weighty = 0.05;
		j.add(jp4, g);

	}

	public JPanel getJ() {
		return j;
	}

	public JButton getB() {
		return b;
	}

	public String[] getItems() {
		return items;
	}

	public JComboBox getLocalisation() {
		return localisation;
	}

	public JTextField[] getTf() {
		return tf;
	}

/*	public GridLayout getGl() {
		return gl;
	}*/

	public JLabel getJ1() {
		return j1;
	}

	public JLabel getJ2() {
		return j2;
	}

	public JLabel getJ3() {
		return j3;
	}

	public JLabel getJ4() {
		return j4;
	}

	public JLabel getJ5() {
		return j5;
	}

	public JLabel getJ6() {
		return j6;
	}

	public JLabel getJ7() {
		return j7;
	}

	public JLabel getJ8() {
		return j8;
	}

	public JLabel getJ9() {
		return j9;
	}

	public JLabel getJ10() {
		return j10;
	}

	public JLabel getJ11() {
		return j11;
	}

	public void setJ12(JLabel j12) {
		this.j12 = j12;
	}

	public JLabel getJ12() {
		return j12;
	}
}
