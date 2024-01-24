package model.rec;

import java.sql.Date;


public class ApplyVO {

	int applyCode, reqContCode, workerCode;
	Date applyDateD;
	String applyDate;


	public ApplyVO() {

	}

	public ApplyVO(int applyCode, int reqContCode, int workerCode, String applyDate) {

		this.applyCode = applyCode;
		this.	reqContCode =reqContCode;
		this.workerCode = workerCode;
		this.applyDate = applyDate;

	}

	public ApplyVO(int applyCode, int reqContCode, int workerCode, Date applyDateD) {

		this.applyCode = applyCode;
		this.	reqContCode =reqContCode;
		this.workerCode = workerCode;
		this.applyDateD = applyDateD;

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

	public int getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}

	public Date getApplyDateD() {
		return applyDateD;
	}

	public void setApplyDateD(Date applyDateD) {
		this.applyDateD = applyDateD;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	
	
	

}
