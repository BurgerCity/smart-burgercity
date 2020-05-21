package client;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Idriss Zerai
 *
 */
public class BoundMainPanel extends JFrame{
	CardLayout c=new CardLayout();
	JPanel jp=new JPanel();
	JButton back=new JButton();
	BoundCarFrame1 b1=new BoundCarFrame1();
	BoundCarFrame2 b2=new BoundCarFrame2();
	BoundCarFrame3 b3=new BoundCarFrame3();
	public JPanel getJp() {
		return jp;
	}
	public JButton getBack() {
		return back;
	}
	BoundMainPanel(){
		super("bornes et véhicules");
		this.setSize(500, 500);
		this.jp.setLayout(c);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		

		this.jp.add("f1",b1.getJp());
	
		this.jp.add("f2",b2.getJp());
	
		this.jp.add("f3",b3.getJp());

		this.getContentPane().add(jp);
		this.setVisible(true);
	}
	public CardLayout getC() {
		return c;
	}
	
	public BoundCarFrame1 getB1() {
		return b1;
	}
	public BoundCarFrame2 getB2() {
		return b2;
	}
	public BoundCarFrame3 getB3() {
		return b3;
	}
}
