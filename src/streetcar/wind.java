package streetcar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class wind  extends JPanel{
	
	// City burger = new City();	 
	  public void paintComponent(Graphics g) {
		 	 Station station = new Station();
		 	 station.create_network();
	      super.paintComponent(g);
	      Color lightgrey = new Color(215,215,215);
	      g.setColor(lightgrey);
	      g.fillOval(600,300,800,400);
	      g.drawOval(600,300,800,400);
	      Graphics g2 = (Graphics) g;
	      Color duckblue = new Color(0,154,166);
	      Color purple = new Color(110,30,120);
	      Color carbon = new Color(60,55,50);
	      g2.setColor(duckblue);
	      g2.fillRect(0,0,1600,80);
	      g2.drawRect(0,0,1600,80);
	      g2.drawRect(550, 110, 870, 650);
	      g2.drawRect(10, 110,500,650);
	      Graphics g3 = (Graphics) g;
	      Graphics g6 = (Graphics) g;
	      Graphics g7 = (Graphics) g;
	      g3.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	      g3.drawString("Votre réseau",600,170);
	      g3.drawString("Vos données", 20, 170);
	      g6.setColor(purple);
	      g6.drawString("Hauteur",25 ,210);
	      g7.setColor(carbon);
	      g7.drawString(Integer.toString(station.getHeight()),30, 250);
	    
	      Graphics g4 = (Graphics) g;

	 	 int si = station.getXs().size();
	 	 System.out.println("size"+si);
	     for (int i = 0; i< si ;i++) {
	    	 int x = (int) Math.round((double)station.getXs().get(i)*10);
	    	 int y = (int) Math.round((double)station.getYs().get(i)*10);
	    	 g4.fillRect((int)x*3 + 995,(int)y*5+470 , 2, 2);
	    	 }
	     
	     
	  }
	  
	  }

