package client;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;


import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Fenetrep4 extends Ihmindic {

public Fenetrep4 (String s,String s1,Boolean a,Boolean b,Boolean c,Boolean d,Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean d1,Boolean d2,Boolean d3,Boolean d4,Boolean d5) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
	
	

	 this.setTitle("DEMANDE DE RENSEIGNEMENT");
	 JPanel container = new JPanel();
	 container.setLayout(new GridBagLayout());
	 GridBagConstraints co = new GridBagConstraints();
	 
	 co.gridx = 0;
    co.gridy = 2;
    JButton bouton = new JButton("Valider");
    container.add(bouton, co);

	    
   
    		
            
       	 co.gridx = 0;
       	 co.gridy = 0;
       	 JLabel dd = new JLabel("Un de vos choix a été le taux de depassement de pollution, veuillez renseigner les secteurs voulues (si aucun choix n'est fait l'accès ne saura pas autorisé) : ");
       	 container.add(dd, co);
       	 
       	 co.gridx = 1;
       	 co.gridy = 0;
       	 JCheckBox dd1 = new JCheckBox("Nord");
       	 container.add(dd1, co);
       	 
       	 co.gridx = 2;
       	 co.gridy = 0;
       	 JCheckBox dd2 = new JCheckBox("Sud");
       	 container.add(dd2, co)
       	 ;
       	 co.gridx = 3;
       	 co.gridy = 0;
       	 JCheckBox dd3 = new JCheckBox("Ouest");
       	 container.add(dd3, co);
       	 
       	 co.gridx = 4;
       	 co.gridy = 0;
       	 JCheckBox dd4 = new JCheckBox("Est");
       	 container.add(dd4, co);
       	 
       	 co.gridx = 5;
       	 co.gridy = 0;
       	 JCheckBox dd5 = new JCheckBox("Tous");
       	 container.add(dd5, co);
       	 
       	

            
            ItemListener itemListener = new ItemListener() {
            	boolean aa = a;
        		boolean bb = b;
        		boolean cc = c;
        		boolean dd = d;
        		boolean b1 = false;
        		boolean b2 = false;
        		boolean b3 = false;
        		boolean b4 = false;
        		boolean b5 = false;
        		boolean d1 = false;
        		boolean d2 = false;
        		boolean d3 = false;
        		boolean d4 = false;
        		boolean d5 = false;

        		

                 public void itemStateChanged(ItemEvent itemEvent) {
                	 
                	 if(itemEvent.getSource()==dd1)  { d1 = true;}
                	 if(itemEvent.getSource()==dd2)  { d2 = true;  }
                	 if(itemEvent.getSource()==dd3)  { d3 = true;}
                	 if(itemEvent.getSource()==dd4)  { d4 = true;}
                	 if(itemEvent.getSource()==dd5)  { d5 = true;}

                	 bouton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                    
							try {
								Fenetre5p f= new Fenetre5p(s,s1,aa,bb,cc,dd,b1,b2,b3,b4,b5,d1,d2,d3,d4,d5);
							} catch (SQLException | ParseException e1) {
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
               	 

               }
       };
       dd1.addItemListener(itemListener);
       dd2.addItemListener(itemListener);
       dd3.addItemListener(itemListener);
       dd4.addItemListener(itemListener);
       bouton.addItemListener(itemListener);

       
       
      



       

    

        
  	 this.setContentPane(container);

	
}
}