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

public class Tram extends JPanel {

	  public static void main(String[] args) {
	    Station station = new Station();
	    station.create_network();
	    JFrame frame = new JFrame();
	    frame.setTitle("Your tram network");
	    frame.setSize(1600, 800);
	    frame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent e) {
	          System.exit(0);
	       }
	       
	    });
	    Container contentPane = frame.getContentPane();
	    contentPane.add(new wind());
	    frame.setVisible(true);
	 }


	}

