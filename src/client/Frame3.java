package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Frame3 extends Container {
	/*String[] items = {"north", "south", "west", "east"};
	JComboBox localisation;*/
	JRadioButton[] jr;
	JPanel j;
	JPanel j1;
	JLabel jl;
	JButton b;
	JPanel jp1;
	JLabel jl1;
	Frame3() {
		//localisation = new JComboBox(items);
		j = new JPanel(new GridBagLayout());
		j1 = new JPanel(new GridLayout(1,2));
		GridBagConstraints g = new GridBagConstraints();
		jl = new JLabel("Choose the location of the sensor(s)");
		jl1 = new JLabel("");
		b = new JButton("Validate");
		jr = new JRadioButton[4];
		jp1 = new JPanel(new GridLayout(2,2));
		jr[0] = new JRadioButton("north");
		jr[1] = new JRadioButton("south");
		jr[2] = new JRadioButton("east");
		jr[3] = new JRadioButton("west");
		j1.add(jl1);
		j1.add(b);
		g.weightx = 0.5;
		g.weighty = 0.8;
		j.add(jl, g);
		
		g.gridy = 1;
		for(int i = 0; i < jr.length; i++) {
			jp1.add(jr[i]);
		}
		j.add(jp1);
		//j.add(localisation, g);
		g.gridy = 2;
		g.gridx = 1;
		g.weightx = 0.3;
		g.weighty = 0.8;
		j.add(j1, g);
	}
	/*public JComboBox getLocalisation() {
		return localisation;
	}*/
	public JPanel getJ() {
		return j;
	}
	public JButton getB() {
		return b;
	}
	public JRadioButton[] getJr() {
		return jr;
	}
	public void setJl1(JLabel jl1) {
		this.jl1 = jl1;
	}
}
