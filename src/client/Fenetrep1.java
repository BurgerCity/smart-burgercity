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
 *This page ask a begin date and a end date and if the format is not correct or is not in base its ask again to enter it by a new window
 */
public class Fenetrep1  extends Ihmindic {
   
private String date= "";

    public Fenetrep1() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
    	
        this.setTitle("CHOIX DE LA DATE");
        
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy = 0;
        JLabel a = new JLabel("Veuillez renseigner une date de DEBUT (aaaa-mm-jj), si le format n'est pas respecté l'accès ne sera pas autorisé, ainsi que la cohérence des mois et jours: ");
        container.add(a, c);
        
        c.gridx = 1;
        c.gridy = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	JFormattedTextField field = new JFormattedTextField(dateFormat);
        //JTextField field = new JTextField();
        field.setPreferredSize( new Dimension( 200, 24 ) );
      //  field.setFocusLostBehavior(JFormattedTextField.REVERT);
        container.add(field,c);
        
        c.gridx = 0;
        c.gridy = 1;
        JLabel a1 = new JLabel("Veuillez renseigner une date de FIN (aaaa-mm-jj), si le format n'est pas respecté l'accès ne sera pas autorisé, ainsi que la cohérence des mois et jours: ");
        container.add(a1, c);
        
        c.gridx = 1;
        c.gridy = 1;
        
    	JFormattedTextField field1 = new JFormattedTextField(dateFormat);
        //JTextField field = new JTextField();
        field1.setPreferredSize( new Dimension( 200, 24 ) );
      //  field.setFocusLostBehavior(JFormattedTextField.REVERT);
        container.add(field1,c);
        
        c.gridx = 0;
        c.gridy = 2;
        JButton d = new JButton("Ok");
        container.add(d, c);

        //System.out.println(getValue);

        d.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String getValue = field.getText().toString(); 
                String getValue2 = field1.getText().toString(); 

                Date date = null;
				try {
					date = dateFormat.parse(getValue);
					date = dateFormat.parse(getValue2);

				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
		
                 if ( !dateFormat.format(date).equals(getValue) || !dateFormat.format(date).equals(getValue2)) { 
                 	
                 	try {
    					Fenetrep1bis a = new Fenetrep1bis(getValue,getValue2);
					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                 }

                try {
                	String s2 = null;
					AideIhm f = new AideIhm(getValue,getValue2);
                    boolean o = f.okdate(getValue,getValue2);
                    if ( f.okdate(getValue,getValue2) == true) { 
                    	System.out.println(f.okdate(getValue,getValue2));
                    	Fenetrep2 nw = new Fenetrep2(getValue,getValue2);
                    
                    } else {
    					Fenetrep1bis a = new Fenetrep1bis(getValue,getValue2);
					}
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
        });

        this.setContentPane(container);

    }
    
  

}
