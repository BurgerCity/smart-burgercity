package client;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;



public class CarbonJPanel extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color lightgrey = new Color(215,215,215);
		Color duckblue = new Color(0,154,166);
		g.setColor(duckblue);
		g.fillOval(550,400,270,270); // cercle remplissage
		g.drawOval(550,400,270,270); // cercle bord
		Graphics g2 = (Graphics) g;
		g2.setColor(duckblue);
		g2.fillRect(0,0,1600,80); // bande remplissage
		g2.drawRect(0,0,1600,80); // bande bord
		Graphics g3 = (Graphics) g;
		g3.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		//Info ville
		g2.drawRect(60, 110, 300, 500);
		g3.drawString("Info de la ville",156,140);
		//Proportion
		g2.drawRect(480, 110, 400, 260);
		g3.drawString("Entrez la proportion d'utilisation des",535,140);
		g3.drawString("différents moyens de transports",555,160);
		//Carbone
		g2.drawRect(1000, 110, 300, 350);
		g3.drawString("Empreinte carbone des différents ",1020,140);
		g3.drawString("moyens de transports ",1070,160);
		g3.drawString("Retour au menu ",1093,555);
		//Menu
		g2.drawRect(1000, 500, 300, 100);
		ImageIcon icon = new ImageIcon("burger.png");
		Graphics g4 = (Graphics) g;
		Toolkit t=Toolkit.getDefaultToolkit();  
		Image i=t.getImage("burger.png");  
		g4.drawImage(i, 500,500,this);  
	}
}
