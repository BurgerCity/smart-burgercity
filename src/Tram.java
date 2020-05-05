/*Marine Bucaille
2020 April
Tram network
*/


/* Libraries*/
import java.util.*;
import java.lang.Math;
//import java.lang.reflect.Array.get();
import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;



/*Parameter classes*/
/*** Class defining lenght - Width - surface ***/
class City {
  private float a = 10;  /*width*/
  private float b = 5;   /*Height*/
  private float budget = 1000000 ;   /*city's budget in €*/
  private float station_price = 10000;   /*price to build a station*/
  private double surface = (Math.PI*a*b);  /*surface of the city*/

  public float getA(){return a;}                                  /**/
  public float getB(){return b;}                                  /**/
  public float getBudget(){return budget;}                        /*definition of get methods*/
  public float getStation_price(){return station_price;}          /**/
  public double getSurface(){return surface;}                     /**/
}

class Station {
  City burger = new City();
  float nb_station = (burger.getBudget()/burger.getStation_price()) ; /*number of tram stations in the city*/
  double r= (Math.sqrt(burger.getSurface()/(Math.PI*nb_station))); /*distance between two stations*/
  float xs[]=new float[(int)nb_station];     /*tab of x coordinates*/
  float ys[]=new float[(int)nb_station];     /*tab of y coordiantes*/

  public void create_network(){
      float x = 0;
      float y = - burger.getB();
      for (int i=0 ; i< nb_station; i++){
        if (x<= burger.getA()*Math.sqrt(1-((y*y)/(burger.getB()*burger.getB()))))   {
                xs[i]=x;
                ys[i]=y;
        }
        else {
          x = -1*(float)(burger.getA()*Math.sqrt(1-((y*y)/(burger.getB()*burger.getB()))));
          y=y+(float)r*(float)1.5;
        }
        x=x+(float)r*(float)1.5;

        System.out.println(x);
      }

  }
//  public float[] getXs(){return xs;}
//  public float[] getYs(){return ys;}
}


/*Plot classes*/
class wind extends JPanel{
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Polygon p = new Polygon();
     for (int i = 0; i < 1; i++) { p.addPoint((int)
      (100 + 50 * Math.cos(i * 2 * Math.PI / 5)),
      (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)));
      g.drawPolygon(p);}
   }

}



/*Main*/

public class Tram extends JPanel {

  public static void main(String[] args) {
    Station station = new Station();
    station.create_network();
    JFrame frame = new JFrame();
    frame.setTitle("Your tram network");
    frame.setSize(450, 350);
    frame.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent e) {
          System.exit(0);
       }
    });
    Container contentPane = frame.getContentPane();
    contentPane.add(new wind());
    frame.setVisible(true);
 }


}
