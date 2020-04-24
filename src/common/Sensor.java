package common;

public class Sensor {
	private int nitrogenDioxideInfo;
	private int nitrogenDioxideAlert;
	private int leadInfo;
	private int leadAlert;
	private int fineParticlesInfo;
	private int fineParticlesAlert;
	private int carbonMonoxideInfo;
	private int carbonMonoxideAlert;
	private String localization;
	private int timeBeforeAlert;
	private int timeStatement;
	private int nbSensors;
	private String operation_type;
	
	
	public int getNbSensors() {
		return nbSensors;
	}
	public void setNbSensors(int nbSensors) {
		this.nbSensors = nbSensors;
	}
	public String getLocation() {
		return localization;
	}
	public void setLocation(String location) {
		this.localization = location;
	}
	public int getTimeBeforeAlert() {
		return timeBeforeAlert;
	}
	public void setTimeBeforeAlert(int timeBeforeAlert) {
		this.timeBeforeAlert = timeBeforeAlert;
	}
	public int getStatement() {
		return timeStatement;
	}
	public void setStatement(int statement) {
		this.timeStatement = statement;
	}
	public int getNitrogenDioxideInfo() {
		return nitrogenDioxideInfo;
	}
	public void setNitrogenDioxideInfo(int nitrogenDioxideInfo) {
		this.nitrogenDioxideInfo = nitrogenDioxideInfo;
	}
	public int getNitrogenDioxideAlert() {
		return nitrogenDioxideAlert;
	}
	public void setNitrogenDioxideAlert(int nitrogenDioxideAlert) {
		this.nitrogenDioxideAlert = nitrogenDioxideAlert;
	}
	public int getLeadInfo() {
		return leadInfo;
	}
	public void setLeadInfo(int leadInfo) {
		this.leadInfo = leadInfo;
	}
	public int getLeadAlert() {
		return leadAlert;
	}
	public void setLeadAlert(int leadAlert) {
		this.leadAlert = leadAlert;
	}
	public int getFineParticlesInfo() {
		return fineParticlesInfo;
	}
	public void setFineParticlesInfo(int fineParticlesInfo) {
		this.fineParticlesInfo = fineParticlesInfo;
	}
	public int getFineParticlesAlert() {
		return fineParticlesAlert;
	}
	public void setFineParticlesAlert(int fineParticlesAlert) {
		this.fineParticlesAlert = fineParticlesAlert;
	}
	public int getCarbonMonoxideInfo() {
		return carbonMonoxideInfo;
	}
	public void setCarbonMonoxideInfo(int carbonMonoxideInfo) {
		this.carbonMonoxideInfo = carbonMonoxideInfo;
	}
	public int getCarbonMonoxideAlert() {
		return carbonMonoxideAlert;
	}
	public void setCarbonMonoxideAlert(int carbonMonoxideAlert) {
		this.carbonMonoxideAlert = carbonMonoxideAlert;
	}
}
