package model.rec;

import java.sql.Date;

public class WorkerContVO {
	
	private int workerContCode,workerCode;
	private String accBank,accName,accNum;
	private String workerContSdate, workerContEdate;
	private int recontNum;
	private String contPeriod,contDate;
	private int mgrCode;
	private String workerName,mgrID;
	private String contState, workerContCK;
	private int applyCode;
	private int reqContCode;
	


	private String actualSdate, actualEdate;
	private Date actualSdateD, actualEdateD;
	
	
	public WorkerContVO() {
		// TODO Auto-generated constructor stub
	}
	
	// 유승민
	public WorkerContVO(int workerContCode, int workerCode,String accNum, 
			String accBank, String accName, String workerContSdate, String workerContEdate,
			int recontNum, String contPeriod, String contDate, int mgrCode,String contState) {
		
		this.workerContCode = workerContCode;
		this.workerCode = workerCode;
		this.accNum = accNum;
		this.accBank = accBank;
		this.accName = accName;
		this.workerContSdate = workerContSdate;
		this.workerContEdate = workerContEdate;
		this.recontNum = recontNum;
		this.contPeriod = contPeriod;
		this.contDate = contDate;
		this.mgrCode = mgrCode;
		this.contState = contState;
	}
	
	
	// 유승민
	public WorkerContVO(int workerContCode,String accNum,String accBank,String accName) {
		
		this.workerContCode = workerContCode;
		this.accNum = accNum;
		this.accBank = accBank;
		this.accName = accName;
		
	}
	
	
	// 유승민
	public WorkerContVO(
			String workerContSdate,
			String workerContEdate,
			String contPeriod,
			String contDate,
			int applyCode,
			String workerContCK,
			String contState,
			String mgrID) {
		
		this.workerContSdate = workerContSdate;
		this.workerContEdate = workerContEdate;
		this.recontNum = recontNum;
		this.contPeriod = contPeriod;
		this.contDate = contDate;
		this.mgrCode = mgrCode;
		this.applyCode = applyCode;
		this.workerContCK = workerContCK;
		this.contState = contState;
		this.mgrID = mgrID;
		
	}
	
	// 유승민
	public WorkerContVO(String workerContSdate, String workerContEdate, int recontNum, String contPeriod,String contDate) {
		
		this.workerContSdate = workerContSdate;
		this.workerContEdate = workerContEdate;
		this.recontNum = recontNum;
		this.contPeriod = contPeriod;
		this.contDate = contDate;
		
	}
	
	//@오버라이딩 - 장경희
	public WorkerContVO(int workerContCode, String accNum, 
			String accBank, String workerContSdate, String workerContEdate,
			int recontNum, String contPeriod, String contDate, String actualSdate, String actualEdate) {
		
		this.workerContCode = workerContCode;
	//	this.workerCode = workerCode;
		this.accNum = accNum;
		this.accBank = accBank;
	//	this.accName = accName;
		this.workerContSdate = workerContSdate;
		this.workerContEdate = workerContEdate;
		this.recontNum = recontNum;
		this.contPeriod = contPeriod;
		this.contDate = contDate;
	//	this.mgrCode = mgrCode;
		this.actualSdate = actualSdate;
		this.actualEdate = actualEdate;
	//	this.workerContCk = workerContCk;
		
	}
	
	//@오버라이딩 - 장경희
	public WorkerContVO(String accName, String accBank, String accNum) {
		
		this.accNum = accNum;
		this.accBank = accBank;
		this.accName = accName;
	
	}

//	public WorkerContVO(int workerContCode, String accName, String accBank, String accNum) {
//		s
//		this.workerContCode = workerContCode;
//		this.accNum = accNum;
//		this.accBank = accBank;
//		this.accName = accName;
//		
//	}
	

	//@오버라이딩 - 장경희(고용계약 상세정보 값 반환)
	public WorkerContVO(int workerContCode, String workerContSdate, String workerContEdate, int recontNum, String contPeriod, String actualSdate, String actualEdate) {
		
		this.workerContCode = workerContCode;
		this.workerContSdate = workerContSdate;
		this.workerContEdate = workerContEdate;
		this.recontNum = recontNum;
		this.contPeriod = contPeriod;
		this.actualSdate = actualSdate;
		this.actualEdate = actualEdate;
		
	}

	//@오버라이딩 - 장경희(고용계약 리스트 값 반환)
	public WorkerContVO(int workerContCode, String workerContSdate, String workerContEdate, String contDate, String workerContCk) {
		
		this.workerContCode = workerContCode;
		this.contDate = contDate;
		this.workerContSdate = workerContSdate;
		this.workerContEdate = workerContEdate;
		this.workerContCK = workerContCk;
	
	}


	public int getWorkerContCode() {
		return workerContCode;
	}


	public void setWorkerContCode(int workerContCode) {
		this.workerContCode = workerContCode;
	}


	public int getWorkerCode() {
		return workerCode;
	}


	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}


	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getAccBank() {
		return accBank;
	}


	public void setAccBank(String accBank) {
		this.accBank = accBank;
	}


	public String getAccName() {
		return accName;
	}


	public void setAccName(String accName) {
		this.accName = accName;
	}


	public String getWorkerContSdate() {
		return workerContSdate;
	}


	public void setWorkerContSdate(String workerContSdate) {
		this.workerContSdate = workerContSdate;
	}


	public String getWorkerContEdate() {
		return workerContEdate;
	}


	public void setWorkerContEdate(String workerContEdate) {
		this.workerContEdate = workerContEdate;
	}


	public int getRecontNum() {
		return recontNum;
	}


	public void setRecontNum(int recontNum) {
		this.recontNum = recontNum;
	}


	public String getContPeriod() {
		return contPeriod;
	}


	public void setContPeriod(String contPeriod) {
		this.contPeriod = contPeriod;
	}


	public String getContDate() {
		return contDate;
	}


	public void setContDate(String contDate) {
		this.contDate = contDate;
	}


	public int getMgrCode() {
		return mgrCode;
	}


	public void setMgrCode(int mgrCode) {
		this.mgrCode = mgrCode;
	}


	public String getWorkerName() {
		return workerName;
	}


	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}


	public String getMgrID() {
		return mgrID;
	}


	public void setMgrID(String mgrID) {
		this.mgrID = mgrID;
	}


	public String getContState() {
		return contState;
	}


	public void setContState(String contState) {
		this.contState = contState;
	}


	public String getWorkerContCK() {
		return workerContCK;
	}


	public void setWorkerContCK(String workerContCK) {
		this.workerContCK = workerContCK;
	}


	public int getApplyCode() {
		return applyCode;
	}


	public void setApplyCode(int applyCode) {
		this.applyCode = applyCode;
	}


	public int getReqContCode() {
		return reqContCode;
	}


	public void setReqContCode(int reqContCode) {
		this.reqContCode = reqContCode;
	}


	public String getActualSdate() {
		return actualSdate;
	}


	public void setActualSdate(String actualSdate) {
		this.actualSdate = actualSdate;
	}


	public String getActualEdate() {
		return actualEdate;
	}


	public void setActualEdate(String actualEdate) {
		this.actualEdate = actualEdate;
	}


	public Date getActualSdateD() {
		return actualSdateD;
	}


	public void setActualSdateD(Date actualSdateD) {
		this.actualSdateD = actualSdateD;
	}


	public Date getActualEdateD() {
		return actualEdateD;
	}


	public void setActualEdateD(Date actualEdateD) {
		this.actualEdateD = actualEdateD;
	}



	
}
