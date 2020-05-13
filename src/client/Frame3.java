package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame3 extends Container {
	String[] items = {"north", "south", "west", "east"};
	JComboBox localisation;
	JPanel j;
	JLabel jl;
	JButton b;
	Frame3() {
		localisation = new JComboBox(items);
		j = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		jl = new JLabel("Choose the location of the sensor(s)");
		b = new JButton("Validate");
		
		g.weightx = 0.5;
		g.weighty = 0.8;
		j.add(jl, g);
		g.weightx = 0.5;
		j.add(localisation, g);
		g.gridy = 1;
		g.gridx = 1;
		g.weightx = 0.3;
		g.weighty = 0.8;
		j.add(b, g);
	}
	public JComboBox getLocalisation() {
		return localisation;
	}
	public JPanel getJ() {
		return j;
	}
	public JButton getB() {
		return b;
	}
}
