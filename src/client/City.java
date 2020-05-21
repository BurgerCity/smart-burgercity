package client;
/**
 * Marine Bucaille 
 */

import java.io.IOException;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

class City {
	  private Client_socket client;
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
	 
	  public City() throws JsonMappingException, JsonProcessingException, IOException{
		  System.out.println("eee");
		  this.client = new Client_socket();
		  this.client.startConnection("127.0.0.1", 2015);
		  a = Integer.parseInt(JOptionPane.showInputDialog("Hauteur de la ville"));  /*width*/
		  b = Integer.parseInt(JOptionPane.showInputDialog("Largeur de la ville"));   /*Height*/
		  
		  budget =Integer.parseInt(JOptionPane.showInputDialog("Budget de la ville"));  /*city's budget in €*/
		  station_price =Integer.parseInt(JOptionPane.showInputDialog("Prix d'une station"));  /*price to build a station*/
		  surface = (Math.PI*a*b);  /*surface of the city*/
		  theorical_num_stat = budget / station_price;

		  inserting_datas(this.client);
		  
	  }
	  public void inserting_datas(Client_socket client) throws JsonMappingException, JsonProcessingException, IOException{
			System.out.println("fff");
		  Json j = new Json(client);
		  Request r = new Request();
		r.setOperation_type("INSERT");
		r.setTable("city");
		r.getA().add(Integer.toString(a));
		r.getA().add(Integer.toString(b));
		r.getA().add(Integer.toString((int) Math.round((Double) surface)));
		r.getA().add(Integer.toString(budget));
		r.getA().add(Integer.toString(theorical_num_stat));
		r.getA().add("1");
		j.sendRequest(r);
	
		Message m = new Message();
		Response res = new Response();
		res = j.deserialize(m.readMessage(client.getIn()));
	  }
	public Client_socket getClient() {
		return client;
	}
	public void setClient(Client_socket client) {
		this.client = client;
	}
	
	
	

}
