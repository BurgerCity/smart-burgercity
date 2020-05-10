package streetcar;
import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import javax.swing.JComponent;



public class wind  extends JPanel{

	  public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      Color lightgrey = new Color(215,215,215);
	      g.setColor(lightgrey);
	      g.setColor(lightgrey);
	      g.fillOval(600,300,800,400);
	      g.drawOval(600,300,800,400);
	      Graphics g2 = (Graphics) g;
	      Color duckblue = new Color(0,154,166);
	      g2.setColor(duckblue);
	      g2.setColor(duckblue);
	      g2.fillRect(0,0,1600,80);
	      g2.drawRect(0,0,1600,80);
	      g2.drawRect(550, 110, 870, 650);
	      Graphics g3 = (Graphics) g;
	      g3.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	      g3.drawString("Votre réseau",600,170);
	      ImageIcon icon = new ImageIcon("burger.png");
	      Graphics g4 = (Graphics) g;
	      Toolkit t=Toolkit.getDefaultToolkit();  
	        Image i=t.getImage("burger.png");  
	        g4.drawImage(i, 120,100,this);  
	  }
	  

	   

	}

