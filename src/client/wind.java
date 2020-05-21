package client;
import javax.swing.JPanel;


import java.awt.*;
import java.awt.Graphics;
import java.io.IOException;
import java.sql.SQLException;



public class wind  extends JPanel{
	 Station station;
	// City burger = new City();	 
	  public void paintComponent(Graphics g) {
		 	
			try {
				station = new Station();
				station.create_network();
				  station.inserting_stations();
				  station.inserting_network();
	      super.paintComponent(g);
	      Color lightgrey = new Color(215,215,215);
	      g.setColor(lightgrey);
	      //g.fillOval(560,130,800,620);
	     // g.drawOval(560,130,800,620);
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
	      g6.drawString("Hauteur (en km)",25 ,230);
	      g6.drawString("Largeur (en km)", 25, 310);
	      g6.drawString("Votre budget (k€)", 25, 390);
	      g6.drawString("Coupe de construction / station (k€)", 25, 470);
	      g7.setColor(carbon);
	      g7.drawString(Integer.toString(station.getHeight()),50, 270);
	      g7.drawString(Integer.toString(station.getWidth()), 50, 350);
	      g7.drawString(Integer.toString(station.getBudget()), 50, 430);
	      g7.drawString(Integer.toString(station.getPrice()), 50, 510);
	     
	      Graphics g4 = (Graphics) g;
	      Graphics g8 = (Graphics) g;
	      g8.setColor(purple);

	 	 int si = station.getXs().size();
	 	 System.out.println("size"+si);
	     for (int i = 0; i< si ;i++) {
	    	 int x = (int) Math.round((double)station.getXs().get(i)*10);
	    	 int y = (int) Math.round((double)station.getYs().get(i)*10);
	    	 g4.fillRect((int)x*5 + 995,(int)y*5+400 , 8, 8);
	    	 }
	     
	     for (int i= 0 ; i< si-1 ; i++ ) {
	    	 int x1 = (int) Math.round((Double)station.getXs().get(station.getFinal_beg().get(i))*10);
	    	 int y1 = (int) Math.round( (Double) station.getYs().get(station.getFinal_beg().get(i))*10);
	    	 int x2 = (int) Math.round((Double) station.getXs().get(station.getFinal_end().get(i))*10);
	    	 int y2 = (int) Math.round( (Double) station.getYs().get(station.getFinal_end().get(i))*10);
	    	// System.out.println("x1  "+x1);
	    	 g8.drawLine((int) x1 *5 + 995  ,(int) y1*5+400 ,(int) x2*5+995 ,(int) y2*5+400 );
	    	
	     }
	     
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 	
	  
	  }
}

