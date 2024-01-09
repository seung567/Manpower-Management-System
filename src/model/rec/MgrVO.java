package model.rec;

public class MgrVO {

	int mgrCode, deptCode;
	String mgrId, mgrPw, mgrName, mgrTel;

	public MgrVO() {

	}

	public MgrVO(int mgrCode, int deptCode, String mgrId, String mgrPw, String mgrName, String mgrTel) {

		this.mgrCode = mgrCode;
		this.deptCode  = deptCode;
		this.mgrId = mgrId;
		this.mgrPw = mgrPw;
		this.mgrName = mgrName;
		this.mgrTel = mgrTel;
		
	}

	public int getMgrCode() {
		return mgrCode;
	}

	public void setMgrCode(int mgrCode) {
		this.mgrCode = mgrCode;
	}

	public int getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getMgrPw() {
		return mgrPw;
	}

	public void setMgrPw(String mgrPw) {
		this.mgrPw = mgrPw;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public String getMgrTel() {
		return mgrTel;
	}

	public void setMgrTel(String mgrTel) {
		this.mgrTel = mgrTel;
	}

   // �ʿ�� �����ؼ� ���!
	
	
	
	
	
	
	
	
	
	
	
	

}
