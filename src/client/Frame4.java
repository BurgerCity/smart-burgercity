package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import common.Response;
/**
 * 
 * @author Mathias
 *This is the fourth panel
 */
public class Frame4 extends Container {
	private Response rp;
	private JLabel[] jl;
	private JRadioButton[] jr;
	private JPanel jp;
	private JPanel jp1;
	private JPanel jp2;
	private JButton b;
	private JButton b2;
	private JLabel label;
	Frame4(Response rp) {
		this.rp = rp;
		b = new JButton("Validate");
		b2 = new JButton("Return");
		jp = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		jp1 = new JPanel(new GridLayout(rp.getA().size(), 2));
		jp2 = new JPanel(new GridLayout(1,3));
		jl = new JLabel[rp.getA().size()];
		jr = new JRadioButton[rp.getA().size()];
		label = new JLabel();
		for(int i = 0; i < rp.getA().size(); i++) {
			jl[i] = new JLabel("<html><center> " + rp.getA().get(i) + "</center></html>");
			jr[i] = new JRadioButton();
			jl[i].setHorizontalAlignment(JLabel.CENTER);
			jp1.add(jl[i]);
			jp1.add(jr[i]);
		}
		jp2.add(b2);
		jp2.add(b);
		g.gridy = 0;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		jp.add(label);
		g.gridy = 1;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		jp.add(jp1, g);
		g.fill = GridBagConstraints.NONE;
		g.gridy = 2;
		g.weightx = 0.5;
		jp.add(jp2, g);	
	}
	public JPanel getJp() {
		return jp;
	}
	public JButton getB() {
		return b;
	}
	public JRadioButton[] getJr() {
		return jr;
	}
	public JLabel[] getJl() {
		return jl;
	}
	public Response getRp() {
		return rp;
	}
	public JButton getB2() {
		return b2;
	}
	public JLabel getLabel() {
		return label;
	}
	
}
