package SimulatorStatement;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends Container {
	private JPanel p;
	private JLabel l;
	Panel(String s, Color r) {
		l = new JLabel(s);
		p = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		p.setBackground(r);
		g.fill = GridBagConstraints.BOTH;
		p.add(l, g);
	}
	public JPanel getP() {
		return p;
	}
	public void setL(JLabel l) {
		this.l = l;
	}
	public JLabel getL() {
		return l;
	}
}
