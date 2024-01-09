package model.rec;

public class WorkerContVO {
	
	private int workerContCode,workerCode,accNum;
	private String accBank,accName;
	private String workeContSdate, workerContEdate;
	private int recontNum;
	private String contPeriod,contDate;
	private int mgrCode;
	
	
	public WorkerContVO(int workerContCode, int workerCode,int accNum, 
			String accBank, String accName, String workeContSdate, String workerContEdate,
			int recontNum, String contPeriod, String contDate, int mgrCode) {
		
		this.workerContCode = workerContCode;
		this.workerCode = workerCode;
		this.accNum = accNum;
		this.accBank = accBank;
		this.accName = accName;
		this.workeContSdate = workeContSdate;
		this.workerContEdate = workerContEdate;
		this.recontNum = recontNum;
		this.contPeriod = contPeriod;
		this.contDate = contDate;
		this.mgrCode = mgrCode;
		
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
	
	public int getAccNum() {
		return accNum;
	}
	
	public void setAccNum(int accNum) {
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
	
	public String getWorkeContSdate() {
		return workeContSdate;
	}
	
	public void setWorkeContSdate(String workeContSdate) {
		this.workeContSdate = workeContSdate;
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
	
}
