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
		     ArrayList <Double> lines = new ArrayList<>();
		     ArrayList <Integer> beginning = new ArrayList<>();
		     ArrayList <Integer> end = new ArrayList<>();
		     ArrayList <Integer> final_beg = new ArrayList<>();
		     ArrayList <Integer> final_end = new ArrayList<>();
		     ArrayList <Integer> visited = new ArrayList<>();
		     visited.add(0);
		     
		     int id_station_start;
		     int id_station_end;
		    
		     int tempo_start=0;
		     int tempo_end=0;
		     
		     for (int j=0;j<xs.size();j++) {
		    	 for (int g=0;g<ys.size();g++) {
		    		 beginning.add(j);                             //create two arrays with num of beginning and end stations
		    		 end.add(g);
		    		 lines.add(Math.sqrt((xs.get(g)-xs.get(j))*(xs.get(g)-xs.get(j))+(ys.get(g)-ys.get(j))*(ys.get(g)-ys.get(j))));}
		    		
		     }
		     
		     for(int w =0 ; w< lines.size(); w++) {
 				if (end.get(w) == 0 || lines.get(w)== (Double) 0.0) {
 					beginning.remove(w);
 					end.remove(w);
 					lines.remove(w);
 					
 			
 				}
 			//	System.out.println("end" + end.get(w));
 			//	System.out.println("long"+ min_lenght);
 				
 				
 			//	System.out.println("lines size"+ lines.size());
 			}
		    		 
		//     System.out.println("line size "+lines.size());
		    		 for (int l = 0 ; l< xs.size()-1; l++) {
		    			 double min_lenght= Double.MAX_VALUE;
		    				for (int m =0;  m< visited.size() ; m++) {
		    					for (int n=0; n< lines.size();n++) {
		    						id_station_start = (int) beginning.get(n);
		    						id_station_end = (int) end.get(n);
		    						if (id_station_end != m ) {
		    							if (lines.get(n)< min_lenght) {
		    								min_lenght = lines.get(n);
		    								tempo_start = id_station_start;
		    								tempo_end = id_station_end;
		    								System.out.println(id_station_end);
		    							}	
		    						}
		    					}
		    					
		    				}
		    			final_beg.add(tempo_start);
		    			final_end.add(tempo_end);
		    	//	System.out.print(tempo_end+ " / ");
		    			
		    			for(int w =0 ; w< lines.size(); w++) {
		    				if (end.get(w) == tempo_end) {
		    					beginning.remove(w);
		    					end.remove(w);
		    					lines.remove(w);
		    					
		    			
		    				}
		    			//	System.out.println("end" + end.get(w));
		    			//	System.out.println("long"+ min_lenght);
		    				
		    				
		    			//	System.out.println("lines size"+ lines.size());
		    			}
		    			
		    	 }	
		    		 for(int r = 0; r < final_end.size(); r++) {
		    			 System.out.println("final end " + final_end.get(r));
		    		 }
		     }
		     
	 
	
	public ArrayList getXs() {
		return xs;
	}

	public ArrayList getYs() {
		return ys;
	}
	




}
