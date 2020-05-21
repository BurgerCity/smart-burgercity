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
/**
 * 
 * @author Mathias
 *
 *This is the third panel
 */
public class Frame3 extends Container {
	private JButton b2;
	private JRadioButton[] jr;
	private JPanel j;
	private JPanel j1;
	private JLabel jl;
	private JButton b;
	private JPanel jp1;
	private JLabel jl1;
	
	Frame3() {
		j = new JPanel(new GridBagLayout());
		j1 = new JPanel(new GridLayout(1,3));
		GridBagConstraints g = new GridBagConstraints();
		jl = new JLabel("Choose the location of the sensor(s)");
		jl1 = new JLabel("");
		b = new JButton("Validate");
		b2 = new JButton("Return");
		jr = new JRadioButton[4];
		jp1 = new JPanel(new GridLayout(2,2));
		jr[0] = new JRadioButton("north");
		jr[1] = new JRadioButton("south");
		jr[2] = new JRadioButton("east");
		jr[3] = new JRadioButton("west");
		j1.add(b2);
		j1.add(jl1);
		j1.add(b);
		//g.weightx = 0.5;
		g.weighty = 0.8;
		j.add(jl, g);
		
		g.gridy = 1;
		for(int i = 0; i < jr.length; i++) {
			jp1.add(jr[i]);
		}
		j.add(jp1);
		g.gridy = 2;
		//g.gridx = 1;
	//	g.weightx = 0.3;
		g.weighty = 0.8;
		j.add(j1, g);
	}

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

	public JButton getB2() {
		return b2;
	}

	public JLabel getJl1() {
		return jl1;
	}
}
