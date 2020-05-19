package streetcar;

import javax.swing.JOptionPane;

class City {
	 // String a_dialogue = JOptionPane.showInputDialog("Hauteur de la ville");

	  private int a = Integer.parseInt(JOptionPane.showInputDialog("Hauteur de la ville"));  /*width*/
	  private int b = Integer.parseInt(JOptionPane.showInputDialog("Largeur de la ville"));   /*Height*/
	  private int budget =Integer.parseInt(JOptionPane.showInputDialog("Budget de la ville"));  /*city's budget in €*/
	  private int station_price = Integer.parseInt(JOptionPane.showInputDialog("Prix d'une station"));  /*price to build a station*/
	  private double surface = (Math.PI*a*b);  /*surface of the city*/

	  public int getA(){return a;}                                  /**/
	  public int getB(){return b;}                                  /**/
	  public int getBudget(){return budget;}                        /*definition of get methods*/
	  public int getStation_price(){return station_price;}          /**/
	  public double getSurface(){return surface;}                     /**/
	  
	

}
