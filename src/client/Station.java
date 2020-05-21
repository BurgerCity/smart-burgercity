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
import java.sql.SQLException;

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
		  Request r1 ;
		  Request r2 ;
		  Json j ;
		  Message m;
		  Response res;
		  Client_socket client;

	 
	City burger;
	Station() throws JsonMappingException, JsonProcessingException, IOException, SQLException{
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
		  client = burger.getClient();
		  j = new Json(client);
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
	
	 public void inserting_stations()throws JsonMappingException, JsonProcessingException, IOException {
		 System.out.println("stat");
		 System.out.println(xs.size());
	//	 j = new Json(burger.getClient());
		 for (int h=0; h< xs.size();h++) {

	r1 = new Request();
	r1.setOperation_type("INSERT");
	r1.setTable("stations");
	r1.getA().add(Double.toString(xs.get(h)));
	r1.getA().add(Double.toString(ys.get(h)));
	r1.getA().add("1");
	System.out.println(r1.getA());
	j.sendRequest(r1);
	m = new Message();
	res = new Response();
	res = j.deserialize(m.readMessage(burger.getClient().getIn()));
}
	 }
	 
	 public void inserting_network() throws JsonMappingException, JsonProcessingException, IOException, SQLException{
		 System.out.println("neti");
	//	 j = new Json(client);
		 System.out.println(final_beg.size());
		 for( int z = 0 ; z < final_beg.size();z++) {
			 	
			    r2 = new Request();
			    r2.setOperation_type("INSERT");
				r2.setTable("network");
				r2.getA().add(Integer.toString(final_beg.get(z)));
				r2.getA().add(Integer.toString(final_end.get(z)));
				r2.getA().add("1");
				System.out.println(r2.getA());
				j.sendRequest(r2);
				m = new Message();
				res = new Response();
				res = j.deserialize(m.readMessage(burger.getClient().getIn()));
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