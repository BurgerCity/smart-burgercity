package client;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author Mathias
 *This is the first panel of the sensor configuration
 */
public class Frame1 extends Container {
	private JButton b1;
	private JButton b2;
	private JPanel j;
	Frame1() {
		j = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		b1 = new JButton("Configure new sensors");
		b2 = new JButton("Reconfigure sensors");
		g.gridx = 0;
		g.gridy = 0;
		g.weightx = 0.5;
		g.weighty = 0.5;

		j.add(b1, g);
		g.gridx = 1;
		j.add(b2, g);
	}
	public JPanel getJ() {
		return j;
	}
	public JButton getB1() {
		return b1;
	}
	public JButton getB2() {
		return b2;
	}
}
