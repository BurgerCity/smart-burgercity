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

public class Frame4 extends Container {
	Response rp;
	JLabel[] jl;
	JRadioButton[] jr;
	JPanel jp;
	JPanel jp1;
	JPanel jp2;
	JButton b;
	Frame4(Response rp) {
		this.rp = rp;
		b = new JButton("Validate");
		jp = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		jp1 = new JPanel(new GridLayout(rp.getA().size(), 2));
		jp2 = new JPanel(new GridLayout(1,1));
		jl = new JLabel[rp.getA().size()];
		jr = new JRadioButton[rp.getA().size()];
		for(int i = 0; i < rp.getA().size(); i++) {
			jl[i] = new JLabel(rp.getA().get(i));
			jr[i] = new JRadioButton();
			jp1.add(jl[i]);
			jp1.add(jr[i]); //ajouter les jp1 et jp2 au jp de base et voila
		}
		jp2.add(b);
		g.gridy = 0;
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		jp.add(jp1, g);
		g.fill = GridBagConstraints.NONE;
		g.gridy = 1;
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
	
}
