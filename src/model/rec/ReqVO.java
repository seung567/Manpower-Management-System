package model.rec;

public class ReqVO {
	int reqCode, custCode, sectorCode, workerNum;
	String expecSdate, expecEdate;
	int langCode, cityCode;
	String local,reqLangLevel,localLangLevel,etcReq,sex;
	String ageRange, quali, totalCost;

	public ReqVO(	int reqCode, int custCode, int sectorCode, int workerNum,
			String expecSdate, String expecEdate,
			int langCode, int cityCode,
			String local, String reqLangLevel, String localLangLevel, String etcReq,String sex,
			String ageRange, String quali, String totalCost) {
		
		this.reqCode = reqCode;
		this.custCode = custCode;
		this.sectorCode = sectorCode;
		this.workerNum = workerNum;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.langCode = langCode;
		this.cityCode = cityCode;
		this.local = local;
		this.reqLangLevel = reqLangLevel;
		this.localLangLevel = localLangLevel;
		this.etcReq = etcReq;
		this.sex = sex;
		this.ageRange = ageRange;
		this.quali = quali;
		this.totalCost = totalCost;
		
	}

	public int getReqCode() {
		return reqCode;
	}

	public void setReqCode(int reqCode) {
		this.reqCode = reqCode;
	}

	public int getCustCode() {
		return custCode;
	}

	public void setCustCode(int custCode) {
		this.custCode = custCode;
	}

	public int getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(int sectorCode) {
		this.sectorCode = sectorCode;
	}

	public int getWorkerNum() {
		return workerNum;
	}

	public void setWorkerNum(int workerNum) {
		this.workerNum = workerNum;
	}

	public String getExpecSdate() {
		return expecSdate;
	}

	public void setExpecSdate(String expecSdate) {
		this.expecSdate = expecSdate;
	}

	public String getExpecEdate() {
		return expecEdate;
	}

	public void setExpecEdate(String expecEdate) {
		this.expecEdate = expecEdate;
	}

	public int getLangCode() {
		return langCode;
	}

	public void setLangCode(int langCode) {
		this.langCode = langCode;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getReqLangLevel() {
		return reqLangLevel;
	}

	public void setReqLangLevel(String reqLangLevel) {
		this.reqLangLevel = reqLangLevel;
	}

	public String getLocalLangLevel() {
		return localLangLevel;
	}

	public void setLocalLangLevel(String localLangLevel) {
		this.localLangLevel = localLangLevel;
	}

	public String getEtcReq() {
		return etcReq;
	}

	public void setEtcReq(String etcReq) {
		this.etcReq = etcReq;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getQuali() {
		return quali;
	}

	public void setQuali(String quali) {
		this.quali = quali;
	}

	public String gettotalCost() {
		return totalCost;
	}

	public void settotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
