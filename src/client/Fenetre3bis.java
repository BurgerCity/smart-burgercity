package client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fenetre3bis extends Ihmindic {
	

public Fenetre3bis(String s) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
	

    	
    	 this.setTitle("CHOIX DE LA DATE");
         
         JPanel container = new JPanel();
         container.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();


         c.gridx = 0;
         c.gridy = 0;
         JLabel a = new JLabel("Veuillez renseigner une date (aaaa-mm-jj) de nouveau la date choisit n'a pas de données enregistrées, attention si le format de la date n'est pas respecté l'accès n'est pas autorisé : ");
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

                 String s2 ="";
                 Date date = null;
 				try {
 					date = dateFormat.parse(getValue);
 				} catch (ParseException e2) {
 					// TODO Auto-generated catch block
 					e2.printStackTrace();
 				}
                 if ( !dateFormat.format(date).equals(getValue)) { 
                 	
 					try {
 						Fenetre3bis a = new Fenetre3bis(getValue);
 					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                 
                 } 
                 try {
                 	AideIhm f = new AideIhm(getValue,s2);
 					boolean o = f.getOk(getValue);
                     if ( f.getOk(getValue) == true) { 
                     	
                     	Fenetre4 nw = new Fenetre4(getValue);
                     
                     } else {
 					Fenetre3bis a = new Fenetre3bis(getValue);
 					
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