package client;

import javax.swing.*;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;

/**
 * 
 * @author tarshiniparameswaran
 *This page ask the date and if the format is not correct or is not in base its ask again to enter it by a new window
 */
public class Fenetre3  extends Ihmindic {
   
private String date= "";

    public Fenetre3() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
    	
        this.setTitle("CHOIX DE LA DATE");
        
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy = 0;
        JLabel a = new JLabel("Veuillez renseigner une date (aaaa-mm-jj), si le format n'est pas respecté l'accès ne sera pas autorisé : ");
        container.add(a, c);
        
        c.gridx = 1;
        c.gridy = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	JFormattedTextField field = new JFormattedTextField(dateFormat);
        //JTextField field = new JTextField();
        field.setPreferredSize( new Dimension( 200, 24 ) );
      //  field.setFocusLostBehavior(JFormattedTextField.REVERT);
        container.add(field,c);
        
        c.gridx = 2;
        c.gridy = 0;
        JButton d = new JButton("Ok");
        container.add(d, c);

        //System.out.println(getValue);

        d.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String getValue = field.getText().toString(); 
                
                Date date = null;
				try {
					date = dateFormat.parse(getValue);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
		
                 if ( !dateFormat.format(date).equals(getValue)) { 
                 	
 					try {
						Fenetre3bis a = new Fenetre3bis(getValue);
					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
                 else
                 {

                try {
                	String s2 = null;
					AideIhm f = new AideIhm(getValue,s2);
                    boolean o = f.getOk(getValue);
                    if ( f.getOk(getValue) == true) { 
                    	
                    	Fenetre4 nw = new Fenetre4(getValue);
                    
                    } else {
					Fenetre3bis a = new Fenetre3bis(getValue);}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            
                 }
            }
        });

        this.setContentPane(container);

    }
    
  

}
    

    