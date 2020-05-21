package client;
/**
 * Marine Bucaille 
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;


public class Tram extends JPanel {
	  public static void main(String[] args) {
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

