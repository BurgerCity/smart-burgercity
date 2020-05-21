package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BoundCarFrame1 extends Container {
	JPanel jp;
	JLabel car;
	JButton auto;
	JButton manu;
	JTextField maxcar;
	JButton refresh;
	BoundCarFrame1(){
		jp=new JPanel(new GridBagLayout());
		refresh=new JButton("Actualiser compteur car");
		
		GridBagConstraints gdbc=new GridBagConstraints();
		auto=new JButton("mode automatique");
		manu=new JButton("mode manuelle");
		car=new JLabel("curr car");
		maxcar=new JTextField("ENTREZ ICI NBR MAX DE VOITURES");
		
		
		gdbc.gridx=3;
		gdbc.gridy=3;
		jp.add(auto,gdbc);

		gdbc.gridx=4;
		gdbc.gridy=4;
		
		jp.add(manu,gdbc);
		
		
		gdbc.gridx=0;
		gdbc.gridy=0;
		gdbc.gridheight= 1;
		gdbc.gridwidth = 3;
		jp.add(maxcar,gdbc);
		
		
		gdbc.gridx=0;
		gdbc.gridy=2;
		gdbc.gridheight= 1;
		gdbc.gridwidth = 3;
		jp.add(car,gdbc);

		gdbc.gridx=0;
		gdbc.gridy=4;
		
		jp.add(refresh,gdbc);

		
	}
	public JPanel getJp() {
		return jp;
	}
	public JLabel getCar() {
		return car;
	}
	public JButton getAuto() {
		return auto;
	}
	public JButton getManu() {
		return manu;
	}
}
