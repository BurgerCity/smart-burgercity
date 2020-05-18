package client;
import java.util.Random;
class UserResponse{ //Trouver le bon nombre de v�hicule a avoir dans la ville


	// Va dependre de la proportion d'utilisation donn� par l'utilisateur
	private Random rnd;
	private CarbonFootprintVehicle carbon;
	private CityInfo info;
	private int pourcentageCar; 
	private int pourcentageVelib;
	private int pourcentageTram;
	private int politicIntensity; // Va jouer sur les distance moyenne parcouru (Existe, car la population peut etre receptif � la politique ou non)
	private float[] cf;
	String UserResponseString;
	float ec;
	float ecHab;

	UserResponse(){ 
		rnd = new Random();
		politicIntensity = 30 + rnd.nextInt(50); // Marche au moins � 30%
		//politicIntensity = 50;
		carbon = new CarbonFootprintVehicle();
		info = new CityInfo(100); // bdd marine A regrouper dans fichier properties
		cf = new float[4];
		
		init();
		ConvertToUsed(pourcentageCar, pourcentageVelib, pourcentageTram);
		CalculAvgDist(pourcentageCar, pourcentageVelib, pourcentageTram);
		ConvertToCF();
		UserResponseString = toString();
		ec = cf[0] + cf[1] + cf[2] + cf[3];
		ecHab = ec/info.getPopulationSize();
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
		info.init(100); // BDD MARINE
		ConvertToUsed(pourcentageCar, pourcentageVelib, pourcentageTram);
		CalculAvgDist(pourcentageCar, pourcentageVelib, pourcentageTram);
		ConvertToCF();
		UserResponseString = toString();
		ec = cf[0] + cf[1] + cf[2] + cf[3];
	}
	
	public float[] getCf() {
		return cf;
	}

	public void setCf(float[] cf) {
		this.cf = cf;
	}

	/*
	Les proportion entr� par l'utilisateur vont etre convertie en nb de v�hicule,
	puis ces nb de v�hicule vont => UserResponse(nbCar,nbVelib,nbTram,nbFoot)
	Exemple (maxCar = 250): 50% d'utilisation des voiture => nbCar = 125
	Puis ce UserResponse va pass� par la classe/m�thode qui va calculer l'EC li� a ce nombre
	*/

	public void ConvertToUsed( int c, int v, int t){ // c,f,t => pourcentage
		// (nbmax /100) * %
		info.setUsedCar((int)(info.getCarAvaible() / 100)* c); // voiture environ 1,1 pers
		info.setUsedVelib((int)(info.getVelibAvaible() / 100)* v); // velib environ 1 pers
		info.setUsedTramStation((int)((double)((double)info.getNbTramStation() / 100)* t)); // 1 station rajoute 50 pers en + (hypothese)
		info.setWalking((int)(info.getWalking() - (1.1 * info.getUsedCar()) - (1 * info.getUsedVelib()) - (50 * info.getUsedTramStation()))); // NbOccupant * VehiculeConcern�
		if(info.getWalking() < 0) {
			info.setWalking(0);
		}
		// depend de la taille de la ville et du nb de station de tram (1 station de tram = 1 km en moins pour les voiture) et de la politique (pourcentage)
		// et des %
	}
	public void CalculAvgDist(int c, int v, int t) {
		
		info.setAvgDistTravCar((int)((double)(info.getAvgDistTravCar() +
				(info.getSurface()/100 * info.getAvgDistTravCar() - 0.2 * info.getNbTramStation())))*c/50); 
		
		info.setAvgDistTravVelib((int)((double)(info.getAvgDistTravVelib() +
				(info.getSurface()/100 * info.getAvgDistTravVelib()) - (int)(0.2 * info.getNbTramStation())))*v/50);
		
		info.setAvgDistTravTram(info.getAvgDistTravTram() * info.getNbTramStation() * t/50);
		
		info.setAvgDistTravWalking((info.getAvgDistTravWalker() +
				(info.getSurface()/100 * info.getAvgDistTravWalker()) - (int)(0.3 * info.getNbTramStation())));
		
		
		// FOR DEBUG
		/*
		System.out.println("Avg Car :" + info.getAvgDistTravCar());
		System.out.println("Avg Velib :" + info.getAvgDistTravVelib());
		System.out.println(info.getAvgDistTravTram());
		System.out.println(info.getAvgDistTravWalker());
		System.out.println("nbtramstation : " + info.getNbTramStation());
		*/
	}
	
	public String toString() {
		String r = "";
		//r = r + info.toString() + '\n';
		r = r + "Used Car : " + info.getUsedCar() + '\n' +
		"Used Velib : " + info.getUsedVelib() + '\n' +
		"Used TramStation : " + info.getUsedTramStation() + '\n' +
		"Walking : " + info.getWalking() + '\n' + '\n' +
		"AvgDistTrav Car : " + info.getAvgDistTravCar() + " km" + '\n' +
		"AvgDistTrav Velib : " + info.getAvgDistTravVelib() +  " km" + '\n' +
		"AvgDistTrav Tram : " + info.getAvgDistTravTram() + " km" + '\n' +
		"AvgDistTrav Walking : " + info.getAvgDistTravWalker() + " km"+ '\n' +
		"CF Array : ";
		
		for(int i = 0 ; i < 4; i++) {
			r = r + cf[i] + " | ";
		}
	//	r = r + '\n' + "Value of carbon footprint : " + (cf[0] + cf[1] + cf[2] + cf[3]) / info.getPopulationSize();

		return r;
	}



	public void ConvertToCF(){
	
		cf[0] = (long)info.getUsedCar() * (long)(info.getAvgDistTravCar() * (long)carbon.getCf_car());
		cf[1] = info.getUsedVelib() * (info.getAvgDistTravVelib() * carbon.getCf_velib());
		cf[2] = (float)(info.getAvgDistTravTram() * carbon.getCf_tram());
		cf[3] = info.getAvgDistTravWalker() * carbon.getCf_walker();
/*
		// On aura besoin de la distance parcouru dans notre calcul... 
		 * Va dependre de la taille de la ville  / nb habitant / du nb de station de tram / politique mise en place
		 * 
		 * int DistanceMoyenneParcouruCar = ValeurInternet * ValeurCalculTailleVille * ValeurCalculPolitique - ValeurCalculNbTram 
		 * int DistanceMoyenneParcouruVelib = ValeurInternet * ValeurCalculTailleVille * ValeurCalculPolitique - ValeurCalculNbTram 
		 * int DistanceMoyenneParcouruTram = ValeurInternet * ValeurCalculNbTram
		 * 
		 * Voiture : nbCar * DistanceMoyenneParcouruCar
		 * Velib : nbVelib * DistanceMoyenneParcouruVelib
		 * Marche : nbTram * DistanceMoyenneParcouruTram
		 * 
		//(Question au prof, Cette distance doit d�pendre de la taille de la ville ? Doit on prendre une donn�e moyenne pour chaque v�hicule ?)
		 * OUI, va d�pendre de la taille de la ville, Nb station de tram etc..
*/
	}

	public static void main(String[] args){
		UserResponse u = new UserResponse(); // Voir Scanner plus tard
		/*
		u.init();
		u.ConvertToUsed(u.pourcentageCar, u.pourcentageVelib, u.pourcentageTram);
		u.CalculAvgDist(u.pourcentageCar, u.pourcentageVelib, u.pourcentageTram);
		u.ConvertToCF();
		*/
		//System.out.println(u.info.toString());
		//System.out.println(u.toString());
		//System.out.println();
		//System.out.println("Value of carbon footprint : " + (u.cf[0] + u.cf[1] + u.cf[2] + u.cf[3]) / u.info.getPopulationSize());
		//System.out.println(u.ec);
		u.response(50,50,50);
		System.out.println(u.toString());
		System.out.println(u.ec);
		//System.out.println(u.ec/u.info.getPopulationSize());
		System.out.println();
		System.out.println("info :");
		System.out.println(u.info.toString());
		System.out.println();
		System.out.println();
		u.response(50,50,50);
		System.out.println(u.toString());
		System.out.println(u.ec);
		//System.out.println(u.ec/u.info.getPopulationSize());
		System.out.println();
		System.out.println("info :");
		System.out.println(u.info.toString());
		System.out.println();
		System.out.println();
		u.response(50,50,50);
		System.out.println(u.toString());
		System.out.println(u.ec);
		//System.out.println(u.ec/u.info.getPopulationSize());
		System.out.println();
		System.out.println("info :");
		System.out.println(u.info.toString());
		System.out.println();
		System.out.println();
		u.response(50,50,50);
		System.out.println(u.toString());
		System.out.println(u.ec);
		//System.out.println(u.ec/u.info.getPopulationSize());
		System.out.println();
		System.out.println("info :");
		System.out.println(u.info.toString());
		System.out.println();
		System.out.println();
		u.response(50,50,50);
		System.out.println(u.toString());
		System.out.println(u.ec);
		//System.out.println(u.ec/u.info.getPopulationSize());
		System.out.println();
		System.out.println("info :");
		System.out.println(u.info.toString());
		System.out.println();
		System.out.println();

		
	} 
}