package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Idriss Zerai
 *
 */
public class BoundCarFrame2 extends Container{
	JPanel boundpanel1;
	JPanel boundpanel2;
	JPanel boundpanel3;
	JPanel boundpanel4;
	JPanel boundpanel5;
	JPanel boundpanel6;
	JPanel boundpanel7;
	JPanel boundpanel8;	
	JLabel borne1;
	JLabel borne2;
	JLabel borne3;
	JLabel borne4;
	JLabel borne5;
	JLabel borne6;
	JLabel borne7;
	JLabel borne8;
	JLabel text1;
	JLabel text2;
	JLabel text4;
	JLabel text5;
	JLabel text6;
	JLabel text7;
	JLabel text8;
	JLabel text3;
	JPanel jp;
	JButton back;
	JButton refresh;
	GridBagConstraints gdbc=new GridBagConstraints();

	BoundCarFrame2(){
		jp=new JPanel();
		jp.setLayout(new GridBagLayout());
		
		boundpanel1=new JPanel();
		boundpanel1.setLayout(new GridLayout(1,2));
		
		boundpanel2=new JPanel();
		boundpanel2.setLayout(new GridLayout(1,2));
		
		boundpanel3=new JPanel();
		boundpanel3.setLayout(new GridLayout(1,2));
		
		boundpanel4=new JPanel();
		boundpanel4.setLayout(new GridLayout(1,2));
		
		boundpanel5=new JPanel();
		boundpanel5.setLayout(new GridLayout(1,2));
		
		boundpanel6=new JPanel();
		boundpanel6.setLayout(new GridLayout(1,2));
		
		boundpanel7=new JPanel();
		boundpanel7.setLayout(new GridLayout(1,2));
		
		boundpanel8=new JPanel();
		boundpanel8.setLayout(new GridLayout(1,2));
		
		borne1=new JLabel("borne 1 nord");
		gdbc.gridx=0;
		gdbc.gridy=1;
		boundpanel1.add(borne1);
		jp.add(boundpanel1, gdbc);
		
		borne2=new JLabel("borne 2 nord");
		gdbc.gridx=0;
		gdbc.gridy=2;
		boundpanel2.add(borne2);
		jp.add(boundpanel2, gdbc);
		
		borne3=new JLabel("borne 3 south");
		gdbc.gridx=0;
		gdbc.gridy=3;
		boundpanel3.add(borne3);
		jp.add(boundpanel3, gdbc);
		
		borne4=new JLabel("borne 4 south");
		gdbc.gridx=0;
		gdbc.gridy=4;
		boundpanel4.add(borne4);
		jp.add(boundpanel4, gdbc);
		
		borne5=new JLabel("borne 5 east");
		gdbc.gridx=0;
		gdbc.gridy=5;
		boundpanel5.add(borne5);
		jp.add(boundpanel5, gdbc);
		
		borne6=new JLabel("borne 6 east");
		gdbc.gridx=0;
		gdbc.gridy=6;
		boundpanel6.add(borne6);
		jp.add(boundpanel6, gdbc);
		
		borne7=new JLabel("borne 7 west");
		gdbc.gridx=0;
		gdbc.gridy=7;
		boundpanel7.add(borne7);
		jp.add(boundpanel7, gdbc);
		
		borne8=new JLabel("borne 8 west");
		gdbc.gridx=0;
		gdbc.gridy=8;
		boundpanel8.add(borne8);
		jp.add(boundpanel8, gdbc);
		
		back=new JButton("RETOUR");
		gdbc.gridx=2;
		gdbc.gridy=10;
		jp.add(back,gdbc);
		
		refresh=new JButton("ACTUALISER");
		gdbc.gridx=2;
		gdbc.gridy=11;
		jp.add(refresh,gdbc);
		
		borne1.setText("fermé");
		borne2.setText("fermé");
		borne3.setText("fermé");
		borne4.setText("fermé");
		borne5.setText("fermé");
		borne6.setText("fermé");
		borne7.setText("fermé");
		borne8.setText("fermé");
		
		text1=new JLabel();
		text2=new JLabel();
		text3=new JLabel();
		text4=new JLabel();
		text5=new JLabel();
		text6=new JLabel();
		text7=new JLabel();
		text8=new JLabel();
		
		text1.setText("borne 1 NORD");
		text2.setText("borne 2 NORD");
		text3.setText("borne 1 SUD ");
		text4.setText("borne 2 SUD ");
		text5.setText("borne 1 EST ");
		text6.setText("borne 2 EST ");
		text7.setText("borne 1 WEST");
		text8.setText("borne 2 WEST");
		
		boundpanel1.add(text1);
		boundpanel2.add(text2);
		boundpanel3.add(text3);
		boundpanel4.add(text4);
		boundpanel5.add(text5);
		boundpanel6.add(text6);
		boundpanel7.add(text7);
		boundpanel8.add(text8);
		
	}
	public JPanel getBoundpanel1() {
		return boundpanel1;
	}
	public JPanel getBoundpanel2() {
		return boundpanel2;
	}
	public JPanel getBoundpanel3() {
		return boundpanel3;
	}
	public JPanel getBoundpanel4() {
		return boundpanel4;
	}
	public JPanel getBoundpanel5() {
		return boundpanel5;
	}
	public JPanel getBoundpanel6() {
		return boundpanel6;
	}
	public JPanel getBoundpanel7() {
		return boundpanel7;
	}
	public JPanel getBoundpanel8() {
		return boundpanel8;
	}
	public JLabel getText1() {
		return text1;
	}
	public JLabel getText2() {
		return text2;
	}
	public JLabel getText4() {
		return text4;
	}
	public JLabel getText5() {
		return text5;
	}
	public JLabel getText6() {
		return text6;
	}
	public JLabel getText7() {
		return text7;
	}
	public JLabel getText8() {
		return text8;
	}
	public JLabel getText3() {
		return text3;
	}
	public JPanel getJp() {
		return jp;
	}
	public JButton getBack() {
		return back;
	}
	public GridBagConstraints getGdbc() {
		return gdbc;
	}
	public JLabel getBorne1() {
		return borne1;
	}
	public JLabel getBorne2() {
		return borne2;
	}
	public JLabel getBorne3() {
		return borne3;
	}
	public JLabel getBorne4() {
		return borne4;
	}
	public JLabel getBorne5() {
		return borne5;
	}
	public JLabel getBorne6() {
		return borne6;
	}
	public JLabel getBorne7() {
		return borne7;
	}
	public JLabel getBorne8() {
		return borne8;
	}
	
}
