package client;

import client.Indicator;

import client.Client_socket;



import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

class Fenetre extends Ihmindic   {
		
    public Fenetre(  )throws SQLException, ClassNotFoundException, IOException, InterruptedException {
    	
    	long millis=System.currentTimeMillis();  
    	java.sql.Date date=new java.sql.Date(millis);  
    	String d = ""+date +"";
  
    	String s =d; 
    	String s1="";
    	
    	AideIhm aide = new AideIhm(s,s1);

		int nbcap = aide.getCaptor();
        int nbbo = aide.getBorne();
        int nbtram = aide.getTram();
        Double tt = aide.getTp();
        Double tdd = aide.getTd();
        int cit = aide.getCarinthetown();
        int eemp = aide.getEmp();
     

        this.setTitle("Analyser des indicateurs relatifs \u00e0 l'activit\u00e9 de la ville");


        getContentPane().setLayout(new GridLayout(1,1));

        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        l1.setHorizontalAlignment(JLabel.CENTER);

        l1.setText("<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +
                "<h1 style=\"text-align: center;\"> <span style=\"text-decoration: underline;\">" +
                "<b>INFORMATIONS</b></span></h1>\n"
                +"<p style=\"text-align: center;\"><b><i></i></b></p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">Le nombre de capteurs install&eacute;s : "+
                nbcap +"</p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">Le nombre de bornes install&eacute;s : "+
                nbbo +"</p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">Le nombre de stations de tram : "+
                nbtram + "</p> </body> </html>");
        //p1.setBackground(Color.YELLOW);

        JPanel p2 = new JPanel();
        //p2.setBackground(Color.YELLOW);
        p2.setLayout(new BorderLayout());
        l2.setHorizontalAlignment(JLabel.CENTER);
        if(tt!=0 && cit!=0 && tdd !=0 && eemp !=0) {
        l2.setText("<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +
                "<h1 style=\"text-align: center;\"><span style=\"text-decoration: underline;\">" +
                "<b>STATISTIQUE</b></span></h1>\n" +
                "<p style=\"text-align: center;\"><b><i></i></b></p>\n" +
                "<p style=\"text-align: center;\"><b><i>Actuellement on est le : "+d+".</i></b></p>\n" +
                "<p style=\"text-align: center;\"></p>\n" +
                "<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
                "Le taux de pollution dans la ville : "+ tt +"&nbsp;</span></p>\n" +
                "<p style=\"text-align: center;\"></p>\n" +
                "<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
                "Le taux de d&eacute;passement des seuils de pollution : "+
                tdd +"</span><i><span style=\"font-weight: 400;\">%</span></i></p>\n" +
                "<p style=\"text-align: center;\"></p>\n" +
                "<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
                "Le nombre de voiture dans la ville :"+ cit +"</span></p>\n" +
                "<p style=\"text-align: center;\"></p>\n" +
                "<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" +
                "L&rsquo;empreinte carbone globale g&eacute;n&eacute;r&eacute;e par les d&eacute;placements :"+ eemp+" &nbsp;</span></p>\n" +
                "<p style=\"text-align: center;\"><br /><br /></p></body> </html>");
        } else {l2.setText("<html> <head> <style> p{color:#404040;} h1{color:  #993333}</style> </head> <body> " +
                "<h1 style=\"text-align: center;\"><span style=\"text-decoration: underline;\">" +
                "<b>STATISTIQUE</b></span></h1>\n" +
                "<p style=\"text-align: center;\"><b><i></i></b></p>\n" +
                "<p style=\"text-align: center;\"><b><i>Actuellement on est le : "+d+" . Il n'y a pas de données enregistrées pour ce jour.</i></b></p>\n" +
                "<p style=\"text-align: center;\"></p>\n" +
                "<p style=\"text-align: center;\"><span style=\"font-weight: 400;\">" );}

        JButton b1 = new JButton("COMPARAISON");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             try {
				Fenetre2 a = new Fenetre2();
			} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            }
        });

        p1.add(l1,"l1");
        p2.add(l2,BorderLayout.CENTER);
        p2.add(b1,BorderLayout.SOUTH);

        // if you want have border to the jpanel
        Border blackline = BorderFactory.createLineBorder(Color.white);
         p1.setBorder(blackline);
         p2.setBorder(blackline);


        getContentPane().add(p1);
        getContentPane().add(p2);

    }



}
