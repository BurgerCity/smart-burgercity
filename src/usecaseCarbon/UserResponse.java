package usecaseCarbon;
class UserResponse{ //Trouver le bon nombre de véhicule a avoir dans la ville
	CarbonFootprintVehicle carbon;
	InfoVille info;

	// Va dependre de la proportion d'utilisation donné par l'utilisateur
	
	private int nbCar; 
	private int nbVelib;
	private int nbStationTram; //new
	private int DistanceTotalParcouruTram; // new
	private int nbFoot;
	private int[] ec;

	UserResponse(int c, int v, int t, int f){ 

		carbon = new CarbonFootprintVehicle();
		info = new InfoVille();

		nbCar = c;
		nbVelib = v;
		nbStationTram = t;
		nbFoot = f;
	}

	/*
	Les proportion entré par l'utilisateur vont etre convertie en nb de véhicule,
	puis ces nb de véhicule vont => UserResponse(nbCar,nbVelib,nbTram,nbFoot)
	Exemple (maxCar = 250): 50% d'utilisation des voiture => nbCar = 125
	Puis ce UserResponse va passé par la classe/méthode qui va calculer l'EC lié a ce nombre
	*/

	public void ConvertToNb( int c, int v, int t, int f){ // c,f,t,f => pourcentage
		// (nbmax /100) * %
		nbCar = (info.getMaxCar() / 100)* c; // voiture environ 1 pers
		nbVelib = (info.getMaxVelib() / 100)* v; // velib environ 1 pers
		nbStationTram = (info.getNbTramStation() / 100)* t; // 1 station rajoute 50 pers en + (hypothese)
		nbFoot = info.getMaxFoot() - (1 * nbCar) - (1 * nbVelib) - (50 * nbStationTram); // NbOccupant * VehiculeConcerné

		// nbFoot doit etre trouver par un calcule prenant en compte les autre proportion(Ce qu'il restera de la pop),
	

	}

/* A FAIRE

	public int[] ConvertToEC( int nbCar, int nbVelib, int nbTram, int nbFoot){

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
		//(Question au prof, Cette distance doit dépendre de la taille de la ville ? Doit on prendre une donnée moyenne pour chaque véhicule ?)
		 * OUI, va dépendre de la taille de la ville, Nb station de tram etc..

	}

*/

/* A FAIRE

	public static void main(String[] args){

	}
	*/
}
