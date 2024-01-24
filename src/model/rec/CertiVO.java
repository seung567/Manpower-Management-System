package model.rec;

import java.sql.Date;

public class CertiVO {
	
	int certiCode, workerCode, certiNum;
	String certiName, certiDate, certiExpPeriod;
	Date certiDateD, certiExpPeriodD;
	
	public CertiVO() {
	
	}
	
	// 유승민
	public CertiVO(int certiCode, int workerCode, int certiNum, String certiName, String certiDate, String certiExpPeriod) {
		
		this.certiCode = certiCode;
		this.workerCode = workerCode;
		this.certiNum = certiNum;
		this.certiName = certiName; 
		this.certiDate = certiDate;
		this.certiExpPeriod = certiExpPeriod;
		
	}
	
	public CertiVO(int certiCode, int certiNum, String certiName, String certiDate, String certiExpPeriod) {
		
		this.certiCode = certiCode;
		this.certiNum = certiNum;
		this.certiName = certiName; 
		this.certiDate = certiDate;
		this.certiExpPeriod = certiExpPeriod;
		
	}
	

	public CertiVO(int certiCode, int certiNum, String certiName, Date certiDateD, Date certiExpPeriodD) {
		
		this.certiCode = certiCode;
		this.certiName = certiName; 
		this.certiNum = certiNum;
		this.certiDateD = certiDateD;
		this.certiExpPeriodD = certiExpPeriodD;
		
	}
	
	
	public CertiVO(String certiName, int certiNum, Date certiDateD, Date certiExpPeriodD) {
		
		this.certiName = certiName; 
		this.certiNum = certiNum;
		this.certiDateD = certiDateD;
		this.certiExpPeriodD = certiExpPeriodD;
		
	}

	public CertiVO(String certiName, int certiNum, String certiDate, String certiExpPeriod) {
		
		this.certiName = certiName; 
		this.certiNum = certiNum;
		this.certiDate = certiDate;
		this.certiExpPeriod = certiExpPeriod;
		
	}
	public int getCertiCode() {
		return certiCode;
	}

	public void setCertiCode(int certiCode) {
		this.certiCode = certiCode;
	}

	public int getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}

	public int getCertiNum() {
		return certiNum;
	}

	public void setCertiNum(int certiNum) {
		this.certiNum = certiNum;
	}

	public String getCertiName() {
		return certiName;
	}

	public void setCertiName(String certiName) {
		this.certiName = certiName;
	}

	public String getCertiDate() {
		return certiDate;
	}

	public void setCertiDate(String certiDate) {
		this.certiDate = certiDate;
	}

	public String getCertiExpPeriod() {
		return certiExpPeriod;
	}

	public void setCertiExpPeriod(String certiExpPeriod) {
		this.certiExpPeriod = certiExpPeriod;
	}
	


}
