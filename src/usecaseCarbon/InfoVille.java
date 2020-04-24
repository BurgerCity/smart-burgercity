package usecaseCarbon;

class InfoVille{

	//Info de la ville
	private int citySize =100; // Va dépendre de l'utilisateur => Voir marineBDD
	private int populationSize = 1000000; // Va dépendre de la citySize
	private int maxCar = 500; // Va dépendre de la taille => Voir Idriss OU plan de secours
	private int maxVelib = 700; // Va dépendre de la taille
	private int nbTramStation = 8; // Marine bdd // new
	private int maxFoot = 700000; // Va dépendre de tout
	
	private CarbonFootprintVehicle infoVehicle;

	//Au cas ou, prévoir base de donnée local pour ces données


	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getCitySize() {
		return citySize;
	}

	public void setCitySize(int citySize) {
		this.citySize = citySize;
	}

	public int getMaxCar() {
		return maxCar;
	}

	public void setMaxCar(int maxCar) {
		this.maxCar = maxCar;
	}

	public int getMaxVelib() {
		return maxVelib;
	}

	public void setMaxVelib(int maxVelib) {
		this.maxVelib = maxVelib;
	}

	public int getNbTramStation() {
		return nbTramStation;
	}

	public void setNbTramStation(int nbTramStation) {
		this.nbTramStation = nbTramStation;
	}

	public int getMaxFoot() {
		return maxFoot;
	}

	public void setMaxFoot(int maxFoot) {
		this.maxFoot = maxFoot;
	}

	public CarbonFootprintVehicle getInfoVehicle() {
		return infoVehicle;
	}

	InfoVille(){ // pr test
		infoVehicle = new CarbonFootprintVehicle();
	}

	InfoVille(int pS, int cS, int c, int v, int t, int n){ // futur utilisation (But : Aller chercher ses donnÃ©e dans la BDD reliÃ© a l'IHM de marine)

		infoVehicle = new CarbonFootprintVehicle();
		populationSize = pS;
		citySize = cS;
		maxCar = c;
		maxVelib = v;
		nbTramStation = n; // new
	}

	public String toString(){
		String r = "Info ville : " + '\n' + '\n' +
		"populationSize : " + populationSize + '\n' +
		"citySize : " + citySize + '\n' +
		"maxCar : " + maxCar + '\n' +
		"maxVelib : " + maxVelib + '\n' +
		"nbTramStation  : " + nbTramStation + '\n' + '\n' //new
		//+ infoVehicle.toString()
		;
		return r;
	}

	public static void main(String[] args){
		InfoVille i = new InfoVille();
		System.out.println(i.toString());
	}
}