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
      //  JTextField field = new JTextField();
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
       // JTextField field1 = new JTextField();
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
                String getValue1 = field1.getText().toString(); 

                Date date = null;
                Date date1 = null;
				try {
					date = dateFormat.parse(getValue);
					date1 = dateFormat.parse(getValue1);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
					
                try {
                	AideIhm f = new AideIhm(getValue,getValue1);
					boolean o = f.getOk(getValue);

					if ( (f.okdate(getValue,getValue1) == true) ){ 
                    	
                    	Fenetrep2 nw = new Fenetrep2(getValue,getValue1);
                    
                    } 

					if ( !dateFormat.format(date).equals(getValue) && !dateFormat.format(date1).equals(getValue1)) { 
		                 	
		 					a.setText("Le format de la date n'est pas correcte, VEUILLEZ ENTRER UNE DATE DE NOUVEAU avec le bon format (aaaa-mm-dd) :");
		 					a1.setText("Le format de la date n'est pas correcte, VEUILLEZ ENTRER UNE DATE DE NOUVEAU avec le bon format (aaaa-mm-dd) :");

		            } 
						
					else {
                    	
						a.setText("La date choisit n'a pas de données enregistrées, VEUILLEZ ENTRER UNE DATE DE DEBUT DE NOUVEAU avec le bon format (aaaa-mm-dd) :");
						a1.setText("La date choisit n'a pas de données enregistrées, VEUILLEZ ENTRER UNE DATE DE FIN DE NOUVEAU avec le bon format (aaaa-mm-dd) :");

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
    