package SimulatorStatement;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameAlert extends JFrame {
	JLabel j0 = new JLabel("There is no alert");
	JLabel j1 = new JLabel("Statements are too high");
	JLabel j2 = new JLabel("ALERT");
	Panel p0 = new Panel("There is no alert", Color.GREEN);
	Panel p1 = new Panel("Statements are too high", Color.ORANGE);
	Panel p2 = new Panel("\"ALERT", Color.RED);
	CardLayout cl = new CardLayout();

	JPanel p = new JPanel();
	
	public FrameAlert() {
		super("Configuration");
		this.setSize(400, 300);
		this.p.setLayout(cl);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.p.add("p0", p0.getP());
		this.p.add("p1", p1.getP());
		this.p.add("p2", p2.getP());
		this.getContentPane().add(p);

		
		this.setVisible(true);
	}

	public CardLayout getCl() {
		return cl;
	}

	public JPanel getP() {
		return p;
	}
}
