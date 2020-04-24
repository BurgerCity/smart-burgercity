package usecaseCarbon;
class CarbonFootprintVehicle{

	// Pour 1 km
	private int cf_car = 206; 
	private int cf_velib = 0;
	private double cf_tram = 2.1;
	private int cf_foot = 0;

	public int getCf_car(){
		return cf_car;
	}

	public int getCf_velib(){
		return cf_velib;
	}

	public double getCf_tram(){
		return cf_tram;
	}

	public int getCf_foot(){
		return cf_foot;
	}




	public String toString(){
		String r = "Carbon footprint of city vehicles :" + '\n' + '\n' + 
		"Car : " + cf_car + " gCO2/km"+'\n'+ 
		"Velib : " + cf_velib + "gCO2/km"+'\n'+
		"Tram : " + cf_tram + "gCO2/km"+'\n' +
		"Foot : " + cf_foot + "gCO2/km"+'\n' +'\n';
		return r;
	}
	public static void main(String[] args){
		CarbonFootprintVehicle c = new CarbonFootprintVehicle();
		System.out.println(c.toString());
	}
}