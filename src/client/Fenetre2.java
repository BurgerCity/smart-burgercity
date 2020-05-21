package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 * @author tarshiniparameswaran
 *This window propose to choose between a date or a periode
 */

public class Fenetre2  extends Ihmindic {


    public Fenetre2()throws SQLException, ClassNotFoundException, IOException, InterruptedException {


        this.setTitle("COMPARAISON");


        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        JButton a = new JButton("DATE");
        container.add(a, c);
        c.gridx = 1;
        c.gridy = 0;
        JLabel b = new JLabel("                   ");
        container.add(b,c);
        c.gridx = 2;
        c.gridy = 0;
        JButton d = new JButton("P\u00c9RIODE");
        container.add(d, c);

        a.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
					Fenetre3 a = new Fenetre3();
				} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        d.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
					Fenetrep1 b = new Fenetrep1();
				} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        this.setContentPane(container);



    }

}
