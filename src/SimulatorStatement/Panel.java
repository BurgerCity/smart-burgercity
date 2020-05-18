package SimulatorStatement;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends Container {
	JPanel p;
	JLabel l;
	Panel(String s, Color r) {
		l = new JLabel(s);
		p = new JPanel();
		p.setBackground(r);
		
		p.add(l);
	}
	public JPanel getP() {
		return p;
	}
}
