package client;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 
 * @author tarshiniparameswaran
 *This is the final page with all the choice and show us what you want
 */
public class Fenetre5 extends Ihmindic {
	

	public Fenetre5(String s,Boolean a,Boolean b,Boolean c,Boolean d,Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean d1,Boolean d2,Boolean d3,Boolean d4,Boolean d5) throws SQLException, ClassNotFoundException, IOException, InterruptedException { 
		
		 
		      String targetDay = s;
String s1 ="";
	      AideIhm f = new AideIhm(s,s1);
		 JPanel container = new JPanel();
		 container.setLayout(new GridBagLayout());
		 GridBagConstraints co = new GridBagConstraints();
		 int n = 1;
		 
		  //System.out.println(list.get(i));
         System.out.println(targetDay);
		 
         String nord = "north";
			 String sud = "south";
			 String ouest = "west";
			 String est = "east";

			 long emp = f.getEmp();
			 int car = f.getCarinthetown();
			 Double tp= f.getTp();
			 Double td=f.getTd();
			 
			 Double tp1 = 0.0;
			 Double tp2= 0.0;
			 Double tp3= 0.0;
			 Double tp4= 0.0;
			 
			 Double td1=0.0;
			 Double td2=0.0;
			 Double td3=0.0;
			 Double td4=0.0;

			 this.setTitle("AFFICHAGE DE LA DATE");
			 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel titre = new JLabel();
		     titre.setText("<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +
		             "<h1 style=\"text-align: center;\"><span style=\"text-decoration: underline;\">" +
		             "<b>STATISTIQUE POUR LA DATE DU " +targetDay+ "</b></span></h1>\n" +
		             "<p style=\"text-align: center;\"><b><i></i></b></p>\n");
			 container.add(titre, co);
		     System.out.println(n);
			 n++;
			 
			 if (((b1 == true)&&(b2 == true)&&(b3 == true)&&(b4 == true))  && ((d1 == true)&&(d2 == true)&&(d3 == true)&&(d4 == true)) )  {
				 System.out.println("OKKKKKK");
				 emp = f.getEmp();
				 car = f.getCarinthetown();
				 tp1 = f.getTpn(targetDay, "north");
				 tp2 = f.getTpn(targetDay, "south");
				 tp3 = f.getTpn(targetDay, "west");
				 tp4 = f.getTpn(targetDay, "east");
				 td1 = f.getTdn(targetDay, "north");
				 td2 = f.getTdn(targetDay, "south");
				 td3 = f.getTdn(targetDay, "west");
				 td4 = f.getTdn(targetDay, "east");
				 
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l = new JLabel();
				 if(emp == 0) {
					 l.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour l'empreinte carbone.");
				 } else {
					 l.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "L'empreinte carbone de la ville : "+ emp +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l, co);
			     System.out.println(n);
				 n++;
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l1 = new JLabel();
				 if(car == 0) {
					 l1.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour la présence de voiture en ville.");
				 } else {
					 l1.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le mobre de voiture en ville : "+ car +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l1, co);
			     System.out.println(n);
				 n++;
				 
				 Double tp0= f.getTp();

					co.gridx = 0;
					 co.gridy = n;
				     JLabel l41 = new JLabel();
					 if(tp0 == 0) {
						 l41.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution de la ville.");
					 } else {
						 l41.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
					                "Le taux de pollution  de la ville : "+ tp0 +"&nbsp;</span></p>\n" +
					                "<p style=\"text-align: center;\"></p>\n" );
					 }
					 container.add(l41, co);
				     System.out.println(n);
					 n++;
				 
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l4 = new JLabel();
				 if(tp1 == 0) {
					 l4.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur nord de la ville.");
				 } else {
					 l4.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution pour le secteur nord de la ville : "+ tp1 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l4, co);
			     System.out.println(n);
				 n++;
				 
				
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l5 = new JLabel();
				 if(tp2 == 0) {
					 l5.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur sud de la ville.");
				 } else {
					 l5.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution pour le secteur sud de la ville : "+ tp2 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l5, co);
			     System.out.println(n);
				 n++;
				 
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l6 = new JLabel();
				 if(tp3 == 0) {
					 l6.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur ouest de la ville.");
				 } else {
					 l6.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution pour le secteur ouest de la ville : "+ tp3 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l6, co); 
			     System.out.println(n);
				 n++;
				 
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l7= new JLabel();
				 if(tp4 == 0) {
					 l7.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur est de la ville.");
				 } else {
					 l7.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution pour le secteur est de la ville : "+ tp4 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l7, co);
			     System.out.println(n);
				 n++;
				 
					 
					 Double td0= f.getTd();

					 co.gridx = 0;
					 co.gridy = n;
				     JLabel l121 = new JLabel();
					 if(td0 == 0) {
						 l121.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution de la ville.");
					 } else {
						 l121.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
					                "Le taux de pollution de depassement de la ville : "+ td0 +"&nbsp;</span></p>\n" +
					                "<p style=\"text-align: center;\"></p>\n" );
					 }
					 container.add(l121, co);
				     System.out.println(n);
				     n++;
					 
					 
					 
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l8 = new JLabel();
				 if(td1 == 0) {
					 l8.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur nord de la ville.");
				 } else {
					 l8.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution de depassement pour le secteur nord de la ville : "+ td1 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l8, co);
				 co.gridx = 0;
				 co.gridy = n;
				 n++;

				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l9 = new JLabel();
				 if(td2 == 0) {
					 l9.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur sud de la ville.");
				 } else {
					 l9.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution de depassement pour le secteur sud de la ville : "+ td2 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l9, co);
			     System.out.println(n);
				 n++;
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l10 = new JLabel();
				 if(td3 == 0) {
					 l10.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur ouest de la ville.");
				 } else {
					 l10.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution de depassement pour le secteur ouest de la ville : "+ td3 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l10, co);
			     System.out.println(n);
				 n++;
				 
				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l11 = new JLabel();
				 if(td4 == 0) {
					 l11.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur est de la ville.");
				 } else {
					 l11.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution de depassement pour le secteur est de la ville : "+ td4 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l11, co);
			     System.out.println(n);
			     n++;
			 
			 }
			 else {
	//////////////////////////		 
			 
			 if (a == true) {
				 
					emp = f.getEmp();

			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l = new JLabel();
			 if(emp == 0) {
				 l.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour l'empreinte carbone.");
			 } else {
				 l.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "L'empreinte carbone de la ville : "+ emp +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l, co);
		     System.out.println(n);
			 n++;
			 
			 
			 }
			 
			
			 

			 if (c == true) {car = f.getCarinthetown();
			 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l1 = new JLabel();
			 if(car == 0) {
				 l1.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour la présence de voiture en ville.");
			 } else {
				 l1.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le mobre de voiture en ville : "+ car +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l1, co);
		     System.out.println(n);
			 n++;
			 
			 }
			 
	
			 
			 
			
			 
			 if (b == true  ||(b5==true)  ) {
				 
				 Double tp0= f.getTp();

				co.gridx = 0;
				 co.gridy = n;
			     JLabel l41 = new JLabel();
				 if(tp0 == 0) {
					 l41.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur nord de la ville.");
				 } else {
					 l41.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution  de la ville : "+ tp0 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l41, co);
			     System.out.println(n);
				 n++;
				 
				 
				 }

			 if (b1 == true ) {tp1 = f.getTpn(targetDay, "north");
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l4 = new JLabel();
			 if(tp1 == 0) {
				 l4.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur nord de la ville.");
			 } else {
				 l4.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution pour le secteur nord de la ville : "+ tp1 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l4, co);
		     System.out.println(n);
			 n++;

			 }
			 
			 if (b2 == true) {
				 
			tp2 = f.getTpn(targetDay, "south");
			 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l5 = new JLabel();
			 if(tp2 == 0) {
				 l5.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur sud de la ville.");
			 } else {
				 l5.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution pour le secteur sud de la ville : "+ tp2 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l5, co);
		     System.out.println(n);
			 n++;
			 }
			 
			 if (b3 == true) {tp3 = f.getTpn(targetDay, "west");
			 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l6 = new JLabel();
			 if(tp3 == 0) {
				 l6.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur ouest de la ville.");
			 } else {
				 l6.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution pour le secteur ouest de la ville : "+ tp3 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l6, co); 
		     System.out.println(n);
			 n++;

			 }
			 
			 if (b4 == true) {tp4 = f.getTpn(targetDay, "east");
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l7= new JLabel();
			 if(tp4 == 0) {
				 l7.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de pollution pour le secteur est de la ville.");
			 } else {
				 l7.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution pour le secteur est de la ville : "+ tp4 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l7, co);
		     System.out.println(n);
			 n++;

			 
			 }
			 
		 if (d == true ||(d5==true) ) {
				 
				 Double td0= f.getTd();

				 co.gridx = 0;
				 co.gridy = n;
			     JLabel l121 = new JLabel();
				 if(td0 == 0) {
					 l121.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution de la ville.");
				 } else {
					 l121.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
				                "Le taux de pollution de depassement de la ville : "+ td0 +"&nbsp;</span></p>\n" +
				                "<p style=\"text-align: center;\"></p>\n" );
				 }
				 container.add(l121, co);
			     System.out.println(n);
			     n++;
				 
				 
				 }
			 
			 if (d1 == true){td1 = f.getTdn(targetDay, "north");
			 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l8 = new JLabel();
			 if(td1 == 0) {
				 l8.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur nord de la ville.");
			 } else {
				 l8.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution de depassement pour le secteur nord de la ville : "+ td1 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l8, co);
		     System.out.println(n);
			 n++;}
			 
			 if (d2 == true) {td2 = f.getTdn(targetDay, "south");
			 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l9 = new JLabel();
			 if(td2 == 0) {
				 l9.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur sud de la ville.");
			 } else {
				 l9.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution de depassement pour le secteur sud de la ville : "+ td2 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l9, co);
		     System.out.println(n);
			 n++;

			 }
			 
			 if (d3 == true) {
				 td3 = f.getTdn(targetDay, "west");	 
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l10 = new JLabel();
			 if(td3 == 0) {
				 l10.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur ouest de la ville.");
			 } else {
				 l10.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution de depassement pour le secteur ouest de la ville : "+ td3 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l10, co);
		     System.out.println(n);
			 n++;}
			 
			 if (d4 == true) {td4 = f.getTdn(targetDay, "east");
			 co.gridx = 0;
			 co.gridy = n;
		     JLabel l11 = new JLabel();
			 if(td4 == 0) {
				 l11.setText("Pour cette date, nous n'avons pas de donnée enregistrée pour le taux de depassement de pollution pour le secteur est de la ville.");
			 } else {
				 l11.setText( "<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +"<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
			                "Le taux de pollution de depassement pour le secteur est de la ville : "+ td4 +"&nbsp;</span></p>\n" +
			                "<p style=\"text-align: center;\"></p>\n" );
			 }
			 container.add(l11, co);
		     System.out.println(n);
		     n++;}
		 
			 System.out.println(a);
			 System.out.println(b);
			 System.out.println(c);
			 System.out.println(d);
			 
			 System.out.println(b1);
			 System.out.println(b2);
			 System.out.println(b3);
			 System.out.println(b4);
			 
			 System.out.println(d1);
			 System.out.println(d2);
			 System.out.println(d3);
			 System.out.println(d4);
			 
		
			 }
			 
	 
	 this.setContentPane(container);

}

    

   }