package model.rec;

public class CustVO {

	int custCode, mgrCode;
	String custId, custPw, custName, custRepre, custAddr, custEmail, custTel, custBnum, custSector;

	public CustVO() {

	}

	public CustVO(int custCode, int mgrCode, String custId, String custPw, String custName, String custRepre, String custAddr, String custEmail, String custTel, String custBnum, String custSector) {

		this.custCode = custCode;
		this.mgrCode = mgrCode;
		this.custId = custId;
		this.custPw= custPw;
		this.custName = custName;
		this.custRepre = custRepre;
		this.custAddr = custAddr;
		this.custEmail = custEmail;
		this.custTel = custTel;
		this.custBnum = custBnum;
		this.custSector = custSector;

	}
	
	public CustVO(int custCode, String custName) {
		
		this.custCode = custCode;
		this.custName = custName;
		
	}

	public int getCustCode() {
		return custCode;
	}

	public void setCustCode(int custCode) {
		this.custCode = custCode;
	}

	public int getMgrCode() {
		return mgrCode;
	}

	public void setMgrCode(int mgrCode) {
		this.mgrCode = mgrCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustPw() {
		return custPw;
	}

	public void setCustPw(String custPw) {
		this.custPw = custPw;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustRepre() {
		return custRepre;
	}

	public void setCustRepre(String custRepre) {
		this.custRepre = custRepre;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public String getCustBnum() {
		return custBnum;
	}

	public void setCustBnum(String custBnum) {
		this.custBnum = custBnum;
	}

	public String getCustSector() {
		return custSector;
	}

	public void setCustSector(String custSector) {
		this.custSector = custSector;
	}

	

}
