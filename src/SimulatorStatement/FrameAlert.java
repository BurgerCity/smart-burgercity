package SimulatorStatement;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Mathias
 *	This is the frame when an alert occurs
 */
public class FrameAlert extends JFrame {
	private Panel p0 = new Panel("There is no more alert", Color.GREEN);
	private Panel p1 = new Panel("Statements are too high", Color.ORANGE);
	private Panel p2 = new Panel("ALERT", Color.RED);
	private CardLayout cl = new CardLayout();
	private JPanel p = new JPanel();
	
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

	public Panel getP0() {
		return p0;
	}

	public Panel getP1() {
		return p1;
	}

	public Panel getP2() {
		return p2;
	}
}
