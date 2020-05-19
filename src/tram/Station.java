package streetcar;

import java.util.ArrayList;
import java.lang.Math;

class Station {
	
	City burger = new City();
	
	  int height = burger.getA();
	  int width = burger.getB();
	  int budget = burger.getBudget();
	  int price = burger.getStation_price();
	  
	  
	  
	  public int getHeight() {
		return height;
	}


	public int getWidth() {
		return width;
	}


	public int getBudget() {
		return budget;
	}


	public int getPrice() {
		return price;
	}


	float nb_station = (burger.getBudget()/burger.getStation_price()) ; /*number of tram stations in the city*/
	  double r= (Math.sqrt(burger.getSurface()/(Math.PI*nb_station))); /*distance between two stations*/
	  ArrayList<Double> xs = new ArrayList<>();     /*tab of x coordinates*/
	  ArrayList<Double>ys = new ArrayList<>();     /*tab of y coordiantes*/

	  
	  
	public float getNb_station() {
		return nb_station;          // number of stations getter
	}

	public void create_network(){  // creation of the stations and lines 
	      double x = 0;
	      double y = - burger.getB()/2;
	      
	      
	      int i = 0;
	      while ( i < (int) nb_station) {
	    	  if (x<= burger.getA()*Math.sqrt(1-((y*y)/(burger.getB()*burger.getB()))))   {
	                xs.add(x);
	                ys.add(y);
	        }
	        else {
	          x = -1*(float)(burger.getA()*Math.sqrt(1-((y*y)/(burger.getB()*burger.getB()))));
	          y=y+(float)r*(float)1.5;
	        }
	        x=x+(float)r*(float)1.5;
	        i=i+1;
	    	  
	      }
		     ArrayList  lines = new ArrayList<>();
		     ArrayList beginning = new ArrayList<>();
		     ArrayList end = new ArrayList<>();
		     ArrayList final_beg = new ArrayList<>();
		     ArrayList final_end = new ArrayList<>();
		     
		     
		     for (int j=0;j<xs.size();j++) {
		    	 for (int g=0;g<ys.size();g++) {
		    		 beginning.add(j);                             //create two arrays with num of beginning and end stations
		    		 end.add(g);
		    		 lines.add(Math.sqrt((xs.get(g)-xs.get(j))*(xs.get(g)-xs.get(j))+(ys.get(g)-ys.get(j))*(ys.get(g)-ys.get(j))));
		    	 }	 
		     }
		     
	  }
	
	
	public ArrayList getXs() {
		return xs;
	}

	public ArrayList getYs() {
		return ys;
	}
	




}
