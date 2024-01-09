package model.rec;

public class WorkerVO {
	int workerCode;
	String workerID, workerPW, workerName, workerAddr;
	String workerTel, workerAge, workerRnum;
	String careerDetail, workerEmail, careerPeriod;
	int skillCode;
	
	
	public WorkerVO() {
		// TODO Auto-generated constructor stub
		
	}
	
	public WorkerVO(int workerCode, 
			String workerID, String workerPW, String workerName, String workerAddr,
			String workerTel, String workerAge, String workerRnum,
			String careerDetail, String workerEmail, String careerPeriod,
			int skillCode) {
		
		this.workerCode = workerCode;
		this.workerID = workerID;
		this.workerPW = workerPW;
		this.workerName = workerName;
		this.workerAddr = workerAddr;
		this.workerTel = workerTel;
		this.workerAge = workerAge;
		this.workerRnum = workerRnum;
		this.careerDetail = careerDetail;
		this.workerEmail = workerEmail;
		this.careerPeriod = careerPeriod;
		this.skillCode = skillCode;
		
	}

	public int getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}

	public String getWorkerID() {
		return workerID;
	}

	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}

	public String getWorkerPW() {
		return workerPW;
	}

	public void setWorkerPW(String workerPW) {
		this.workerPW = workerPW;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerAddr() {
		return workerAddr;
	}

	public void setWorkerAddr(String workerAddr) {
		this.workerAddr = workerAddr;
	}

	public String getWorkerTel() {
		return workerTel;
	}

	public void setWorkerTel(String workerTel) {
		this.workerTel = workerTel;
	}

	public String getWorkerAge() {
		return workerAge;
	}

	public void setWorkerAge(String workerAge) {
		this.workerAge = workerAge;
	}

	public String getWorkerRnum() {
		return workerRnum;
	}

	public void setWorkerRnum(String workerRnum) {
		this.workerRnum = workerRnum;
	}

	public String getCareerDetail() {
		return careerDetail;
	}

	public void setCareerDetail(String careerDetail) {
		this.careerDetail = careerDetail;
	}

	public String getWorkerEmail() {
		return workerEmail;
	}

	public void setWorkerEmail(String workerEmail) {
		this.workerEmail = workerEmail;
	}

	public String getCareerPeriod() {
		return careerPeriod;
	}

	public void setCareerPeriod(String careerPeriod) {
		this.careerPeriod = careerPeriod;
	}

	public int getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(int skillCode) {
		this.skillCode = skillCode;
	}
	
	
	
}
