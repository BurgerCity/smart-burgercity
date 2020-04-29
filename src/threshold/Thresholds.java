package threshold;

public class Thresholds {
	private int thDioAz;
	private int thOxAz;
	private int	thPlmb;
	private int thParFin;
	private int ThMonoCarb;
	private int ThBenz;
	private int MaxCars;
	public int getThDioAz() {
		return thDioAz;
	}
	public void setThDioAz(int thDioAz) {
		this.thDioAz = thDioAz;
	}
	public int getThOxAz() {
		return thOxAz;
	}
	public void setThOxAz(int thOxAz) {
		this.thOxAz = thOxAz;
	}
	public int getThPlmb() {
		return thPlmb;
	}
	public void setThPlmb(int thPlmb) {
		this.thPlmb = thPlmb;
	}
	public int getThParFin() {
		return thParFin;
	}
	public void setThParFin(int thParFin) {
		this.thParFin = thParFin;
	}
	public int getThMonoCarb() {
		return ThMonoCarb;
	}
	public void setThMonoCarb(int thMonoCarb) {
		ThMonoCarb = thMonoCarb;
	}
	public int getThBenz() {
		return ThBenz;
	}
	public void setThBenz(int thBenz) {
		ThBenz = thBenz;
	}
	public int getMaxCars() {
		return MaxCars;
	}
	public void setMaxCars(int maxCars) {
		MaxCars = maxCars;
	}
	Thresholds(){}
	public int currpollution(int pindicatior) {
		int currp=0;
		//retrieves current pollution value
		return currp;
	}
	public void pollutionSuperviser() {
		//analises pollution indicators and compares them to the set thresholds
	}
	public int currcar(){
		int n=0;
		//calculates the number of cars in the town by using the data base
		return n;
	}
}
