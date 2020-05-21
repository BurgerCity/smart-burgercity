package client;

import java.io.IOException;
import java.sql.SQLException;

import client_common.Json;
import common.Message;
import common.Request;
import common.Response;

public class CityInfo{
	private Client_socket client;
	
	private CarbonLinkBdd clb;
	private int surface;
	private int populationSize; 
	
	private int carAvaible;
	private int usedCar;
	private int AvgDistTravCar = 6; //  6 km avg

	private int velibAvaible;
	private int usedVelib;  
	private int AvgDistTravVelib = 3; //  3 km avg
	
	private int nbTramStation;
	private int usedTramStation;
	private double AvgDistTravTram = 0.7; // 700 m avg
	
	private int walking;
	private int AvgDistTravWalker = 3; // 3 km avg
	String infoString;

	public Client_socket getClient() {
		return client;
	}

	public void setClient(Client_socket client) {
		this.client = client;
	}

	public CarbonLinkBdd getClb() {
		return clb;
	}

	public void setClb(CarbonLinkBdd clb) {
		this.clb = clb;
	}

	public String getInfoString() {
		return infoString;
	}

	public void setInfoString(String infoString) {
		this.infoString = infoString;
	}
	
	public int getAvgDistTravWalker() {
		return AvgDistTravWalker;
	}

	public void setAvgDistTravWalking(int avgDistTravWalking) {
		AvgDistTravWalker = avgDistTravWalking;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getCarAvaible() {
		return carAvaible;
	}

	public void setCarAvaible(int carAvaible) {
		this.carAvaible = carAvaible;
	}

	public int getUsedCar() {
		return usedCar;
	}

	public void setUsedCar(int usedCar) {
		this.usedCar = usedCar;
	}

	public int getVelibAvaible() {
		return velibAvaible;
	}

	public void setVelibAvaible(int velibAvaible) {
		this.velibAvaible = velibAvaible;
	}

	public int getUsedVelib() {
		return usedVelib;
	}

	public void setUsedVelib(int usedVelib) {
		this.usedVelib = usedVelib;
	}

	public int getNbTramStation() {
		return nbTramStation;
	}

	public void setNbTramStation(int nbTramStation) {
		this.nbTramStation = nbTramStation;
	}

	public int getUsedTramStation() {
		return usedTramStation;
	}

	public void setUsedTramStation(int usedTramStation) {
		this.usedTramStation = usedTramStation;
	}

	public int getWalking() {
		return walking;
	}

	public void setWalking(int walking) {
		this.walking = walking;
	}
	
	public int getAvgDistTravCar() {
		return AvgDistTravCar;
	}

	public void setAvgDistTravCar(int avgDistTravCar) {
		AvgDistTravCar = avgDistTravCar;
	}

	public int getAvgDistTravVelib() {
		return AvgDistTravVelib;
	}

	public void setAvgDistTravVelib(int avgDistTravVelib) {
		AvgDistTravVelib = avgDistTravVelib;
	}

	public double getAvgDistTravTram() {
		return AvgDistTravTram;
	}

	public void setAvgDistTravTram(double avgDistTravTram) {
		AvgDistTravTram = avgDistTravTram;
	}

	public void setAvgDistTravWalker(int avgDistTravWalker) {
		AvgDistTravWalker = avgDistTravWalker;
	}

	public void setAvgDistTravTram(int avgDistTravTram) {
		AvgDistTravTram = avgDistTravTram;
	}
	
	

	public void init() {
		surface = clb.valueRequest[0];
		nbTramStation = clb.valueRequest[1];
		usedTramStation = nbTramStation;
		populationSize = surface * 10000;
		walking = populationSize;
		carAvaible = (int)(populationSize * 0.5);
		velibAvaible = (int)(populationSize * 0.3);
		
		AvgDistTravCar = 6;
		AvgDistTravVelib = 3;
		AvgDistTravTram = 0.7;
		AvgDistTravWalker = 3;
	}

	CityInfo() throws ClassNotFoundException, IOException, SQLException, InterruptedException{ 
		clb = new CarbonLinkBdd();
		surface = clb.valueRequest[0];
		nbTramStation = clb.valueRequest[1];
		init();
		infoString = toString();
	}
	public String toString(){
		String r =
		"population Size : " + populationSize + " " +  '\n' +
		"surface : " + surface + " " +  '\n' +
		"car Avaible : " + carAvaible + " " +  '\n' +
		"velib Avaible : " + velibAvaible + " " +  '\n' +
		"nb TramStation Avaible  : " + nbTramStation + " " +  '\n' ;
		return r;
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, InterruptedException{
		CityInfo i = new CityInfo();
	}
	
}