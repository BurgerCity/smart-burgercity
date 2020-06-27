package test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Marine Bucaille 
 */

import java.io.IOException;

import javax.swing.JOptionPane;


class City {
	  private int a ;  /*width*/
	  private int b ;   /*Height*/
	  
	  private int budget ;  /*city's budget in €*/
	  private int station_price ;  /*price to build a station*/
	  private double surface ;  /*surface of the city*/
	  public int getA(){return a;}                                  /**/
	  public int getB(){return b;}                                  /**/
	  public int getBudget(){return budget;}                        /*definition of get methods*/
	  public int getStation_price(){return station_price;}          /**/
	  public double getSurface(){return surface;}                     /**/
	  public int theorical_num_stat;
	 
	  public City() throws IOException{  
      a = Integer.parseInt(JOptionPane.showInputDialog("Hauteur de la ville"));  /*width*/
	  b = Integer.parseInt(JOptionPane.showInputDialog("Largeur de la ville"));   /*Height*/
	  
	  budget =Integer.parseInt(JOptionPane.showInputDialog("Budget de la ville"));  /*city's budget in €*/
	  station_price =Integer.parseInt(JOptionPane.showInputDialog("Prix d'une station"));  /*price to build a station*/

		  
		  surface = (Math.PI*a*b);  /*surface of the city*/

		  
	  }
	
	
	  public static void main(String[] args) throws IOException {
			City burger = new City();
			//a
			if (burger.getA() < 0) {
				fail("a negative");
			}
			if (burger.getA()==0) {
				fail("a=0");
			}
			if (burger.getA()>2147483647) {
				fail("a too long");
			}
			
			//b
			if (burger.getB() < 0) {
				fail("b negative");
			}
			if (burger.getB()==0) {
				fail("b=0");
			}
			if (burger.getB()>2147483647) {
				fail("b too long");
			}
			
			//budget
			if (burger.getBudget() < 0) {
				fail("budget negative");
			}
			if (burger.getBudget()==0) {
				fail("budget=0");
			}
			if (burger.getBudget()>2147483647) {
				fail("budget too long");
			}
			
			//station_price
			if (burger.getStation_price() < 0) {
				fail("station price negative");
			}
			if (burger.getStation_price()==0) {
				fail("station price =0");
			}
			if (burger.getStation_price()>2147483647) {
				fail("station price too long");
			}
			
			//surface
			if (burger.getSurface() < 0) {
				fail("surface negative");
			}
		
	  }

}
