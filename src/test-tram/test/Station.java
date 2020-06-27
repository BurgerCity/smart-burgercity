package test;

/**
 * Marine Bucaille
 */
import java.util.ArrayList;



import java.io.IOException;
import java.lang.Math;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;

public class Station {
//	private Client_socket client;
	int height;
	int width;
	int budget;
	 int price;
		float nb_station ; /*number of tram stations in the city*/
		  double r; /*distance between two stations*/
		  ArrayList<Double> xs ;     /*tab of x coordinates*/
		  ArrayList<Double>ys ;     /*tab of y coordiantes*/
		  ArrayList <Double> lines ;
		  ArrayList <Integer> beginning ;
		  ArrayList <Integer> end ;
		  ArrayList <Integer> final_beg ;
		  ArrayList <Integer> final_end ;
		  ArrayList <Integer> visited ;


	 
	City burger;
	Station() throws IOException, SQLException{
		  burger = new City();
		  height = burger.getA();
		  width = burger.getB();
		  budget = burger.getBudget();
		  price = burger.getStation_price();
		  nb_station = (burger.getBudget()/burger.getStation_price()) ; /*number of tram stations in the city*/
		  r= (Math.sqrt(burger.getSurface()/(Math.PI*nb_station))); /*distance between two stations*/
		  xs = new ArrayList<>();     /*tab of x coordinates*/
		  ys = new ArrayList<>();     /*tab of y coordiantes*/
		  lines = new ArrayList<>();
		  beginning = new ArrayList<>();
	      end = new ArrayList<>();
		  final_beg = new ArrayList<>();
		  final_end = new ArrayList<>();
		  visited = new ArrayList<>();
		//  create_network();
		

	}
	

	 
	  
	  
	  
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
	      System.out.println(xs.size());
		     
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
 			
 			}
		    		 
		    		 for (int l = 0 ; l< xs.size()-1; l++) {
		    			 double min_lenght= Double.MAX_VALUE;
		    				for (int m =0;  m< visited.size() ; m++) {
		    					for (int n=0; n< lines.size();n++) {
		    						id_station_start = (int) beginning.get(n);
		    						id_station_end = (int) end.get(n);
		    						if (id_station_end != m ) {
		    							if (lines.get(n)< min_lenght) {
		    								min_lenght = lines.get(n);
		    								tempo_start = id_station_start -1;
		    								tempo_end = id_station_end;
		    								System.out.println(id_station_end);
		    							}	
		    						}
		    					}
		    					
		    				}
		    			final_beg.add(tempo_start);
		    			final_end.add(tempo_end);
		    			
		    			for(int w =0 ; w< lines.size(); w++) {
		    				if (end.get(w) == tempo_end) {
		    					beginning.remove(w);
		    					end.remove(w);
		    					lines.remove(w);
		    					
		    			
		    				}
		    			}
		    			
		    	 }	
		    		 for(int r = 0; r < final_end.size(); r++) {
		    			 System.out.println("final end " + final_end.get(r));
		    		 }
		    		 
		    		 
		    		 
		     }

	 
	public ArrayList<Integer> getFinal_beg() {
		return final_beg;
	}


	public ArrayList<Integer> getFinal_end() {
		return final_end;
	}


	public ArrayList getXs() {
		return xs;
	}

	public ArrayList getYs() {
		return ys;
	}
	
	 public static void main(String[] args) throws IOException, SQLException {
		 Station salad = new Station();
		 //testing creation of the network
		 if (salad.getXs().size() > 0 && salad.getFinal_beg().size()<0) {
			 fail("lines not created");	 
		 }
		 
		 //testing line created
		 if (salad.getFinal_beg().size()!=0) {
		 if (salad.getFinal_beg().get(0)==salad.getFinal_end().get(0) ) {
			 fail("lines making loops");
		 }
		 }
		 
	 }
	 }

