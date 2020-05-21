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
	  private int a = Integer.parseInt(JOptionPane.showInputDialog("Hauteur de la ville"));  /*width*/
	  private int b = Integer.parseInt(JOptionPane.showInputDialog("Largeur de la ville"));   /*Height*/
	  
	  private int budget =Integer.parseInt(JOptionPane.showInputDialog("Budget de la ville"));  /*city's budget in €*/
	  private int station_price =Integer.parseInt(JOptionPane.showInputDialog("Prix d'une station"));  /*price to build a station*/
	  private double surface = (Math.PI*a*b);  /*surface of the city*/
	  public int getA(){return a;}                                  /**/
	  public int getB(){return b;}                                  /**/
	  public int getBudget(){return budget;}                        /*definition of get methods*/
	  public int getStation_price(){return station_price;}          /**/
	  public double getSurface(){return surface;}                     /**/
	  public int theorical_num_stat = budget / station_price;

	  
	  public void inserting_datas(Client_socket client) throws JsonMappingException, JsonProcessingException, IOException{
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

}
