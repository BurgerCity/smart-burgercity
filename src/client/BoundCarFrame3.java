package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoundCarFrame3 extends Container{
	JButton openall;
	JButton closeall;
	JButton back;
	JPanel jp;
	GridBagConstraints gdbc=new GridBagConstraints();

	public JButton getOpenall() {
		return openall;
	}

	public JButton getCloseall() {
		return closeall;
	}

	public JButton getBack() {
		return back;
	}

	public JPanel getJp() {
		return jp;
	}

	public GridBagConstraints getGdbc() {
		return gdbc;
	}

	BoundCarFrame3(){
		jp=new JPanel();
		jp.setLayout(new GridBagLayout());
		
		openall =new JButton("ouvrir les bornes");
		gdbc.gridx=0;
		gdbc.gridy=0;
		jp.add(openall,gdbc);
		
		closeall =new JButton("fermer les bornes");
		gdbc.gridx=1;
		gdbc.gridy=1;
		jp.add(closeall,gdbc);
		
		back=new JButton("RETOUR");
		gdbc.gridx=7;
		gdbc.gridy=10;
		jp.add(back,gdbc);
		
		
	}
	
}
