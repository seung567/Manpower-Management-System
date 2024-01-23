package model.rec;

public class ReqContVO {
	private String reqContCk, reqContSdate, reqContEdate, reqContEwhy, actualSdate, actualEdate;
	private int reqContCode, reqCode, workerCode;
	
	public ReqContVO( ) {

	}
	
	public ReqContVO(String reqContCk, String reqContSdate, String reqContEdate, String reqContEwhy, 
			String actualSdate, String actualEdate, int reqContCode, int reqCode, int workerCode) {

		this.reqContCk = reqContCk;
		this.reqContSdate = reqContSdate; 
		this.reqContEdate= reqContEdate; 
		this.reqContEwhy = reqContEwhy;
		this.actualSdate = actualSdate;
		this.actualEdate = actualEdate;
		this.reqContCode = reqContCode; 
		this.reqCode = reqCode; 
		this.workerCode = workerCode;		
	}

	public ReqContVO(String reqContCk, int reqContCode, int reqCode, 
			String reqContSdate, String reqContEdate,
			String actualSdate, String actualEdate, String reqContEwhy) {

		this.reqContCk = reqContCk;
		this.reqContCode = reqContCode; 
		this.reqCode = reqCode; 
		this.reqContSdate = reqContSdate; 
		this.reqContEdate= reqContEdate; 
		this.actualSdate = actualSdate;
		this.actualEdate = actualEdate;
		this.reqContEwhy = reqContEwhy;
	}
	
	public ReqContVO(
			String reqContCk, String reqContSdate, String  reqContEdate,
			String actualSdate, String actualEdate, String reqContEwhy) {

		this.reqContCk = reqContCk;
		this.reqContSdate = reqContSdate;
		this.reqContEdate = reqContEdate;
		this.actualSdate = actualSdate; 
		this.actualEdate = actualEdate; 
		this.reqContEwhy = reqContEwhy;		
	}

	public String getReqContCk() {
		return reqContCk;
	}

	public void setReqContCk(String reqContCk) {
		this.reqContCk = reqContCk;
	}

	public String getReqContSdate() {
		return reqContSdate;
	}

	public void setReqContSdate(String reqContSdate) {
		this.reqContSdate = reqContSdate;
	}

	public String getReqContEdate() {
		return reqContEdate;
	}

	public void setReqContEdate(String reqContEdate) {
		this.reqContEdate = reqContEdate;
	}

	public String getReqContEwhy() {
		return reqContEwhy;
	}

	public void setReqContEwhy(String reqContEwhy) {
		this.reqContEwhy = reqContEwhy;
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

	public int getReqContCode() {
		return reqContCode;
	}

	public void setReqContCode(int reqContCode) {
		this.reqContCode = reqContCode;
	}

	public int getReqCode() {
		return reqCode;
	}

	public void setReqCode(int reqCode) {
		this.reqCode = reqCode;
	}

	public int getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}
	

}



