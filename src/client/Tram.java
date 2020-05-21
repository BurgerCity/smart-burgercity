package client;
/**
 * Marine Bucaille 
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class Tram extends JPanel {
	  public static void main(String[] args) throws JsonMappingException, JsonProcessingException, IOException {
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
	   // Station s = new Station();
	  //  System.out.println(s.height);
	    
	    frame.setVisible(true);

	    
	 }

 
	}

