package client;

import java.awt.GridBagConstraints;import javax.swing.JList;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

public class Fenetre4 extends Ihmindic {
	

public Fenetre4(String s)throws SQLException, ClassNotFoundException, IOException, InterruptedException { 
	

   
     this.setTitle("CHOIX DES VARIABLES (si aucun choix n'est fait l'accès ne saura pas autorisé)");

	 JPanel container = new JPanel();
	 container.setLayout(new GridBagLayout());
	 GridBagConstraints c = new GridBagConstraints();
	 
	 c.gridx = 0;
	 c.gridy = 0;
	 JCheckBox a = new JCheckBox("Empreinte Carbone");
	 container.add(a, c);
	 
	 c.gridx = 0;
	 c.gridy = 1;
	 JCheckBox b = new JCheckBox("Le taux de pollution pour : ");
	 container.add(b, c);

	 c.gridx = 0;
	 c.gridy = 2;
	 JCheckBox cd= new JCheckBox("Le nombre de voiture en ville");
	 container.add(cd, c);
	 
	 c.gridx = 0;
	 c.gridy = 3;
	 JCheckBox d = new JCheckBox("Le taux de dépassement de pollution ");
	 container.add(d, c);
	 
	 c.gridx = 0;
     c.gridy = 4;
     JButton bouton = new JButton("Valider");
     container.add(bouton, c);
     
     ItemListener itemListener = new ItemListener() {
    	boolean aa = false;
		boolean bb = false;
		boolean cc = false;
		boolean dd = false;
		
		boolean bb1 = false;
		boolean bb2 = false;
		boolean bb3 = false;
		boolean bb4 = false;
		boolean bb5 = false;

		
		boolean dd1 = false;
		boolean dd2 = false;
		boolean dd3 = false;
		boolean dd4 = false;
		boolean dd5 = false;

		

         public void itemStateChanged(ItemEvent itemEvent) {
        	 
        	 if(itemEvent.getSource()==a)  { aa = true;}
        	 if(itemEvent.getSource()==b)  { bb = true; }
        	 if(itemEvent.getSource()==cd)  { cc = true;}
        	 if(itemEvent.getSource()==d)  { dd = true;}
        	 
        	 bouton.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     if((aa== true) ||(bb==false)) { try {
						Fenetre5 f= new Fenetre5(s,aa,bb,cc,dd,bb1,bb2,bb3,bb4,bb5,dd1,dd2,dd3,dd4,dd5);
					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}

                 if((bb== true) &&(dd==false)) { try {
					Fenetre4bis f3= new Fenetre4bis(s,aa,bb,cc,dd,bb1,bb2,bb3,bb4,bb5,dd1,dd2,dd3,dd4,dd5);
				} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
                	 if((bb== false) &&(dd==true)) { try {
						Fenetre4bis2 f1= new Fenetre4bis2(s,aa,bb,cc,dd,bb1,bb2,bb3,bb4,bb5,dd1,dd2,dd3,dd4,dd5);
					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
                	 if((bb== true) &&(dd==true)){ try {
						Fenetre4bis3 f2= new Fenetre4bis3(s,aa,bb,cc,dd,bb1,bb2,bb3,bb4,bb5,dd1,dd2,dd3,dd4,dd5);
					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}


                	 else { }

                 }
             });
        	 
        	 
           
           }

       };
       
       a.addItemListener(itemListener);
       b.addItemListener(itemListener);
       cd.addItemListener(itemListener);
       d.addItemListener(itemListener);
       bouton.addItemListener(itemListener);

      
	 this.setContentPane(container);
	 
	 
	

}




    

   }