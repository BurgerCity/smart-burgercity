package client;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class Ihmindic extends JFrame {

    private static Object Ihmindic;

    Ihmindic() throws IOException  {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int)tailleEcran.getHeight();
        int largeur = (int)tailleEcran.getWidth();
        setSize(largeur,hauteur);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

    
    }

}

