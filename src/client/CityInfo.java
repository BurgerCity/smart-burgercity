package client;
public class CityInfo{

	private int surface; // Va dépendre de l'utilisateur => Voir marine BDD
	private int populationSize; 
	
	private int carAvaible;
	private int usedCar;
	private int AvgDistTravCar = 6; //  6 km en moyenne (ou 31 km en france)

	private int velibAvaible;
	private int usedVelib;  
	private int AvgDistTravVelib = 3; //  3 km en moyenne
	
	private int nbTramStation = 8; // BDD MARINE VOIR init()
	private int usedTramStation;
	private double AvgDistTravTram = 0.7; // 700 m en moyenne
	
	private int walking;
	private int AvgDistTravWalker = 3; // 3 km en moyenne
	String infoString;
	
	//private CarbonFootprintVehicle infoVehicle;

	//Au cas ou, prévoir base de donnée local pour ces données


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

	public void init(int s) {
		populationSize = s * 10000;
		walking = populationSize;
		carAvaible = (int)(populationSize * 0.5);
		velibAvaible = (int)(populationSize * 0.3);
		
		AvgDistTravCar = 6;
		nbTramStation = 8; // BDD MARINE
		AvgDistTravVelib = 3;
		AvgDistTravTram = 0.7;
		AvgDistTravWalker = 3;
		
		
		//AvgDistTravCar = AvgDistTravCar + (s/100*AvgDistTravCar) - nbTramStation; //+ politique; // depend de la taille de la ville et du nb de station de tram
		//AvgDistTravVelib = AvgDistTravVelib + (s/100*AvgDistTravVelib) - (int)(0.2 * nbTramStation); //+politique;
		

		
	}

	CityInfo(int s){ // futur utilisation (But : Aller chercher ses donnÃ©e dans la BDD reliÃ© a l'IHM de marine) //2eme argument nb St tram !!
		
		//2eme argument nb St tram !!
		
		init(s);
		//infoVehicle = new CarbonFootprintVehicle();
		surface = s;
		infoString = toString();
	}
	
	

	public String toString(){
		String r =
		"population Size : " + populationSize + " " +  '\n' +
		"surface : " + surface + " " +  '\n' +
		"car Avaible : " + carAvaible + " " +  '\n' +
		"velib Avaible : " + velibAvaible + " " +  '\n' +
		"nb TramStation  : " + nbTramStation + " " +  '\n';
		//+ infoVehicle.toString()
		return r;
	}

	//TEST
	
	
	
	
	public static void main(String[] args){
		CityInfo i = new CityInfo(100); // 100 pour test, valeur a aller chercher dans BDD marine
		//System.out.println(i.getSurface());
		System.out.println(i.toString());
		//System.out.println("TravCar :" + i.AvgDistTravCar);
		//System.out.println("TravVelib :" + i.AvgDistTravVelib);
	}
	
}