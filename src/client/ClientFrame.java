package client;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This is the frame of the sensor configuration
 * @author Mathias
 *
 */
public class ClientFrame extends JFrame {
	
	private CardLayout cl = new CardLayout();
	private JPanel p = new JPanel();
	private Frame1 f1 = new Frame1();
	private Frame2 f2 = new Frame2("insert");
	private Frame3 f3 = new Frame3();
	Frame4 f4;
	private Frame2 f5 = new Frame2("update");
	ClientFrame() {
		super("Configuration");
		this.setSize(400, 200);
		this.p.setLayout(cl);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.p.add("f1", f1.getJ());
		this.p.add("f2", f2.getJ());
		this.p.add("f3", f3.getJ());
		this.p.add("f5", f5.getJ());
		this.getContentPane().add(p);
		
		this.setVisible(true);
	}
	public CardLayout getCl() {
		return cl;
	}
	public JPanel getP() {
		return p;
	}
	public Frame1 getF1() {
		return f1;
	}
	public Frame2 getF2() {
		return f2;
	}
	public Frame3 getF3() {
		return f3;
	}
	public Frame4 getF4() {
		return f4;
	}
	public Frame2 getF5() {
		return f5;
	}

}
