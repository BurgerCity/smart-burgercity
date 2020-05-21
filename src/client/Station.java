package client;
/**
 * Marine Bucaille
 */
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

import java.io.IOException;
import java.lang.Math;

class Station {
	private Client_socket client;
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
	  ArrayList <Double> lines = new ArrayList<>();
	     ArrayList <Integer> beginning = new ArrayList<>();
	     ArrayList <Integer> end = new ArrayList<>();
	     ArrayList <Integer> final_beg = new ArrayList<>();
	     ArrayList <Integer> final_end = new ArrayList<>();
	     ArrayList <Integer> visited = new ArrayList<>();

	  
	  
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
	
	 public void inserting_stations()throws JsonMappingException, JsonProcessingException, IOException {
		 for (int h=0; h< xs.size();h++) {
	  Json j = new Json();
	  Request r1 = new Request();
	r1.setOperation_type("INSERT");
	r1.setTable("stations");
	r1.getA().add(Double.toString(xs.get(h)));
	r1.getA().add(Double.toString(ys.get(h)));
	r1.getA().add("1");
	j.sendRequest(r1);
	Message m = new Message();
	Json js = new Json(client);
	Response res = new Response();
	res = j.deserialize(m.readMessage(client.getIn()));
}
	 }
	 
	 public void inserting_network() throws JsonMappingException, JsonProcessingException, IOException{
		 for( int z = 0 ; z < final_beg.size();z++) {
			 Json j = new Json();
			  Request r2 = new Request();
			  r2.setOperation_type("INSERT");
				r2.setTable("network");
				r2.getA().add(Integer.toString(final_beg.get(z)));
				r2.getA().add(Integer.toString(final_end.get(z)));
				r2.getA().add("1");
				j.sendRequest(r2);
				Message m = new Message();
	
				Response res = new Response();
				res = j.deserialize(m.readMessage(client.getIn()));
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
	




}