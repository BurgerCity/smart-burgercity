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
	private int surface; // Va d�pendre de l'utilisateur => Voir marine BDD
	private int populationSize; 
	
	private int carAvaible;
	private int usedCar;
	private int AvgDistTravCar = 6; //  6 km en moyenne (ou 31 km en france)

	private int velibAvaible;
	private int usedVelib;  
	private int AvgDistTravVelib = 3; //  3 km en moyenne
	
	private int nbTramStation; // BDD MARINE VOIR init()
	private int usedTramStation;
	private double AvgDistTravTram = 0.7; // 700 m en moyenne
	
	private int walking;
	private int AvgDistTravWalker = 3; // 3 km en moyenne
	String infoString;
	
	//private CarbonFootprintVehicle infoVehicle;

	//Au cas ou, pr�voir base de donn�e local pour ces donn�es

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
/*
	public CarbonFootprintVehicle getInfoVehicle() {
		return infoVehicle;
	}

	public void setInfoVehicle(CarbonFootprintVehicle infoVehicle) {
		this.infoVehicle = infoVehicle;
	}
*/
	
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
		//this.client = c;
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
		
		
		//AvgDistTravCar = AvgDistTravCar + (s/100*AvgDistTravCar) - nbTramStation; //+ politique; // depend de la taille de la ville et du nb de station de tram
		//AvgDistTravVelib = AvgDistTravVelib + (s/100*AvgDistTravVelib) - (int)(0.2 * nbTramStation); //+politique;
		

		
	}

	CityInfo() throws ClassNotFoundException, IOException, SQLException, InterruptedException{ // futur utilisation (But : Aller chercher ses donnée dans la BDD relié a l'IHM de marine) //2eme argument nb St tram !!
		
		clb = new CarbonLinkBdd();
		surface = clb.valueRequest[0];
		nbTramStation = clb.valueRequest[1];
		init();
//		surface = s;
		infoString = toString();
	}
	
	

	public String toString(){
		String r =
		"population Size : " + populationSize + " " +  '\n' +
		"surface : " + surface + " " +  '\n' +
		"car Avaible : " + carAvaible + " " +  '\n' +
		"velib Avaible : " + velibAvaible + " " +  '\n' +
		"nb TramStation Avaible  : " + nbTramStation + " " +  '\n' ;
		//"usedTramStation : " + usedTramStation + " " + '\n';
		//+ infoVehicle.toString()
		return r;
	}

	//TEST
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, InterruptedException{
		CityInfo i = new CityInfo(); // 100 pour test, valeur a aller chercher dans BDD marine
		//System.out.println(i.getSurface());
		System.out.println(i.clb.valueRequest[1]);
		System.out.println(i.toString());
		//System.out.println("TravCar :" + i.AvgDistTravCar);
		//System.out.println("TravVelib :" + i.AvgDistTravVelib);
	}
	
}