package client;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
class UserResponse{

	private Random rnd;
	private CarbonFootprintVehicle carbon;
	private CityInfo info;
	private int pourcentageCar; 
	private int pourcentageVelib;
	private int pourcentageTram;
	//private int politicIntensity; // Va jouer sur les distance moyenne parcouru (Existe, car la population peut etre receptif à la politique ou non)
	private float[] cf;
	String UserResponseString;
	float ec;
	float ecHab;

	UserResponse() throws ClassNotFoundException, IOException, SQLException, InterruptedException{ 
		rnd = new Random();
		//politicIntensity = 30 + rnd.nextInt(50); // Marche au moins à 30%
		carbon = new CarbonFootprintVehicle();
		info = new CityInfo();
		cf = new float[4];
		
		init();
		ConvertToUsed(pourcentageCar, pourcentageVelib, pourcentageTram);
		CalculAvgDist(pourcentageCar, pourcentageVelib, pourcentageTram);
		ConvertToCF();
		ec = cf[0] + cf[1] + cf[2] + cf[3];
		ecHab = ec/info.getPopulationSize();
		UserResponseString = toString();
	}
	public void init() {
		pourcentageCar = 50;
		pourcentageVelib = 50;
		pourcentageTram = 50;
	}
	public void response(int c, int v, int t) {
		pourcentageCar = c;
		pourcentageVelib = v;
		pourcentageTram = t;
		info.init();
		ConvertToUsed(pourcentageCar, pourcentageVelib, pourcentageTram);
		CalculAvgDist(pourcentageCar, pourcentageVelib, pourcentageTram);
		ConvertToCF();
		ec = cf[0] + cf[1] + cf[2] + cf[3];
		UserResponseString = toString();
	}
	 
	public float[] getCf() {
		return cf;
	}

	public void setCf(float[] cf) {
		this.cf = cf;
	}

	/*
	The proportions entered by the user will be converted to the number of vehicles,
	then these nb of vehicle go => UserResponse (nbCar, nbVelib, nbTram, nbFoot)
	Example (maxCar = 250): 50% of car use => nbCar = 125
	Then this UserResponse will go through the class / method which will calculate the EC linked to this number
	*/

	public void ConvertToUsed( int c, int v, int t){ // c,f,t => percentage
		// (nbmax /100) * %
		info.setUsedCar((int)(info.getCarAvaible() / 100)* c); // car approximately 1,1 pers
		info.setUsedVelib((int)(info.getVelibAvaible() / 100)* v); // velib approximately 1 pers
		info.setUsedTramStation((int)((double)((double)info.getNbTramStation() / 100)* t)); //1 station adds 50 more people (hypothesis)
		info.setWalking((int)(info.getWalking() - (1.1 * info.getUsedCar()) - (1 * info.getUsedVelib()) - (50 * info.getUsedTramStation()))); // NbOccupant * VehiculeConcerné
		if(info.getWalking() < 0) {
			info.setWalking(0);
		}
	}
	public void CalculAvgDist(int c, int v, int t) {
		
		info.setAvgDistTravCar((int)((double)(info.getAvgDistTravCar() +
				(info.getSurface()/100 * info.getAvgDistTravCar() - 0.2 * info.getNbTramStation())))*c/50); 
		
		info.setAvgDistTravVelib((int)((double)(info.getAvgDistTravVelib() +
				(info.getSurface()/100 * info.getAvgDistTravVelib()) - (int)(0.2 * info.getNbTramStation())))*v/50);
		
		info.setAvgDistTravTram(info.getAvgDistTravTram() * info.getNbTramStation() * t/50);
		
		info.setAvgDistTravWalking((info.getAvgDistTravWalker() +
				(info.getSurface()/100 * info.getAvgDistTravWalker()) - (int)(0.3 * info.getNbTramStation())));
	}
	
	public String toString() {
		String r = "";
		r = r + "Used Car : " + info.getUsedCar() + '\n' +
		"Used Velib : " + info.getUsedVelib() + '\n' +
		"Used TramStation : " + info.getUsedTramStation() + '\n' +
		"Walking : " + info.getWalking() + '\n' + '\n' +
		"AvgDistTrav Car : " + info.getAvgDistTravCar() + " km" + '\n' +
		"AvgDistTrav Velib : " + info.getAvgDistTravVelib() +  " km" + '\n' +
		"AvgDistTrav Tram : " + info.getAvgDistTravTram() + " km" + '\n' +
		//"AvgDistTrav Walking : " + info.getAvgDistTravWalker() + " km"+ '\n' +
		"CF Array : ";
		
		for(int i = 0 ; i < 4; i++) {
			r = r + cf[i] + " | ";
		}
		return r;
	}



	public void ConvertToCF(){
	
		cf[0] = (long)info.getUsedCar() * (long)(info.getAvgDistTravCar() * (long)carbon.getCf_car());
		cf[1] = info.getUsedVelib() * (info.getAvgDistTravVelib() * carbon.getCf_velib());
		cf[2] = (float)(info.getAvgDistTravTram() * carbon.getCf_tram());
		cf[3] = info.getAvgDistTravWalker() * carbon.getCf_walker();
/*
		 * Will depend about the size of the city / nb hab / nb tramStation / policy in place
		 * int DistanceAvgCar = ValueInternet * ValeurCalculTailleVille * ValeurCalculPolitique - ValeurCalculNbTram 
		 * int DistanceAvgVelib = ValueInternet * ValeurCalculTailleVille * ValeurCalculPolitique - ValeurCalculNbTram 
		 * int DistanceAvgTram = ValueInternet * ValeurCalculNbTram
		 * Car : nbCar * DistanceAvgCar
		 * Velib : nbVelib * DistanceAvgVelib
		 * MWlking : nbTram * DistanceAvgTram
*/
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, InterruptedException{
		UserResponse u = new UserResponse();
		u.response(50,50,70);
	}
	public CityInfo getInfo() {
		return info;
	}
	public void setInfo(CityInfo info) {
		this.info = info;
	} 
}
