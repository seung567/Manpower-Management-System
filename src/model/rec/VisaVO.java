package model.rec;

public class VisaVO {
	int visaCode, workerCode, countryCode;
	String visack, visaSdate, visaEsdate, visaMinPeriod, visaExpPeriod;
	String visaType, visaIssuer;
	
	
	// 유승민
	public VisaVO(int visaCode,int workerCode,int countryCode,
			String visack, String visaSdate, String visaEsdate, String visaMinPeriod, String visaExpPeriod,
			String visaType, String visaIssuer) {
		
		this.visaCode = visaCode;
		this.workerCode = workerCode;
		this.countryCode = countryCode;
		this.visack = visack;
		this.visaSdate = visaSdate;
		this.visaEsdate = visaEsdate;
		this.visaMinPeriod = visaMinPeriod;
		this.visaExpPeriod = visaExpPeriod;
		this.visaType = visaType;
		this.visaIssuer = visaIssuer;
		
	}

	public int getVisaCode() {
		return visaCode;
	}

	public void setVisaCode(int visaCode) {
		this.visaCode = visaCode;
	}

	public int getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getVisack() {
		return visack;
	}

	public void setVisack(String visack) {
		this.visack = visack;
	}

	public String getVisaSdate() {
		return visaSdate;
	}

	public void setVisaSdate(String visaSdate) {
		this.visaSdate = visaSdate;
	}

	public String getVisaEsdate() {
		return visaEsdate;
	}

	public void setVisaEsdate(String visaEsdate) {
		this.visaEsdate = visaEsdate;
	}

	public String getVisaMinPeriod() {
		return visaMinPeriod;
	}

	public void setVisaMinPeriod(String visaMinPeriod) {
		this.visaMinPeriod = visaMinPeriod;
	}

	public String getVisaExpPeriod() {
		return visaExpPeriod;
	}

	public void setVisaExpPeriod(String visaExpPeriod) {
		this.visaExpPeriod = visaExpPeriod;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaIssuer() {
		return visaIssuer;
	}

	public void setVisaIssuer(String visaIssuer) {
		this.visaIssuer = visaIssuer;
	}
	
	
}
