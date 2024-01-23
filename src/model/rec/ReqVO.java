package model.rec;

public class ReqVO {
	int reqCode, custCode, sectorCode, workerNum;
	String expecSdate, expecEdate;
	int langCode, cityCode;
	String local,reqLangLevel,localLangLevel,etcReq,sex;
	String ageRange, quali;
	int totalCost;
	String state;
	String langLevel;
	String cityName;
	String sectorName,custName;

	int perCost;
	String reqDate;

	
	
	public ReqVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ReqVO(	int reqCode, int custCode, int sectorCode, int workerNum,
			String expecSdate, String expecEdate,
			int langCode, int cityCode,
			String local, String reqLangLevel, String localLangLevel, String etcReq,String sex,
			String ageRange, String quali, int totalCost, String state, String langLevel) {
		
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
		this.state = state;
		this.langLevel = langLevel;
		
	}
	
	
	// 유승민
	public ReqVO( int reqCode, String custName, String sectorName, int workerNum,
			String expecSdate, String expecEdate, String langLevel, String cityName,
			String local, String reqLangLevel, String localLangLevel, String etcReq,String sex,
			String ageRange, String quali, int totalCost) {
		
		this.reqCode = reqCode;
		this.custName = custName;
		this.sectorName = sectorName;
		this.workerNum = workerNum;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.langLevel = langLevel;
		this.cityName = cityName;
		this.local = local;
		this.reqLangLevel = reqLangLevel;
		this.localLangLevel = localLangLevel;
		this.etcReq = etcReq;
		this.sex = sex;
		this.ageRange = ageRange;
		this.quali = quali;
		this.totalCost = totalCost;
//		this.state = state;
		
	}

	
	// 이수정
	public ReqVO(int reqCode) {
		this.reqCode = reqCode;
	}
	
	// 이수정
	public ReqVO (int sectorCode, int perCost) {
		this.sectorCode = sectorCode;
		this.perCost= perCost;
	}
	
	// 이수정
	public ReqVO(int reqCode, String etcReq) {
		this.reqCode = reqCode;
		this.etcReq = etcReq;
	}
	
	// 이수정
	public ReqVO(String reqDate, int custCode, int sectorCode, 
			String expecSdate, String expecEdate,
			int langCode, String reqLangLevel, String localLangLevel,
			int cityCode, String local, String sex, int workerNum, String ageRange,
			String quali, int totalCost, String etcReq) {
		
		this.reqDate = reqDate;
		this.custCode = custCode;
		this.sectorCode = sectorCode;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.langCode = langCode;
		this.reqLangLevel = reqLangLevel;
		this.localLangLevel = localLangLevel;
		this.cityCode = cityCode;
		this.local = local;
		this.sex = sex;
		this.workerNum = workerNum;
		this.ageRange = ageRange;
		this.quali = quali;	
		this.totalCost = totalCost;
		this.etcReq = etcReq;
	}
	
	// 이수정
	public ReqVO(int reqCode, String reqDate, int custCode, int sectorCode, 
			String expecSdate, String expecEdate,
			int langCode, String reqLangLevel, String localLangLevel,
			int cityCode, String local, String sex, int workerNum, String ageRange,
			String quali, int totalCost, String etcReq) {
		
		this.reqCode = reqCode;
		this.reqDate = reqDate;
		this.custCode = custCode;
		this.sectorCode = sectorCode;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.langCode = langCode;
		this.reqLangLevel = reqLangLevel;
		this.localLangLevel = localLangLevel;
		this.cityCode = cityCode;
		this.local = local;
		this.sex = sex;
		this.workerNum = workerNum;
		this.ageRange = ageRange;
		this.quali = quali;	
		this.totalCost = totalCost;
		this.etcReq = etcReq;
	}
	
	// 이수정
	public ReqVO(int custCode, int sectorCode, int workerNum,
			String expecSdate, String expecEdate,
			int langCode, int cityCode, int totalCost,
			String local, String reqLangLevel, String sex,
			String ageRange, String quali) {
		
		this.custCode = custCode;
		this.sectorCode = sectorCode;
		this.workerNum = workerNum;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.langCode = langCode;
		this.cityCode = cityCode;
		this.totalCost = totalCost;
		this.local = local;
		this.reqLangLevel = reqLangLevel;
		this.sex = sex;
		this.ageRange = ageRange;
		this.quali = quali;	
	}
	
	// 이수정
	public ReqVO(int custCode, int sectorCode, int workerNum,
			String expecSdate, String expecEdate,
			int langCode, int cityCode, int totalCost,
			String local, String reqLangLevel, String sex,
			String ageRange, String quali, String localLangLevel) {
		
		this.custCode = custCode;
		this.sectorCode = sectorCode;
		this.workerNum = workerNum;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.langCode = langCode;
		this.cityCode = cityCode;
		this.totalCost = totalCost;
		this.local = local;
		this.reqLangLevel = reqLangLevel;
		this.sex = sex;
		this.ageRange = ageRange;
		this.quali = quali;	
		this.localLangLevel = localLangLevel;
	}
	
	// 이수정
	public ReqVO(int reqCode,
			String expecSdate, String expecEdate, String reqLangLevel, String localLangLevel,
			String cityName, String local, String langLevel, 
			int workerNum, String sex, String ageRange, String quali, int totalCost) {
		
		this.reqCode = reqCode;
		this.expecSdate = expecSdate;
		this.expecEdate = expecEdate;
		this.workerNum = workerNum;
		this.reqLangLevel = reqLangLevel;
		this.localLangLevel = localLangLevel;
		this.cityName = cityName;
		this.local = local;
		this.langLevel = langLevel;
		this.workerNum = workerNum;
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


	public int getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getLangLevel() {
		return langLevel;
	}


	public void setLangLevel(String langLevel) {
		this.langLevel = langLevel;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getSectorName() {
		return sectorName;
	}


	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public int getPerCost() {
		return perCost;
	}


	public void setPerCost(int perCost) {
		this.perCost = perCost;
	}


	public String getReqDate() {
		return reqDate;
	}


	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}


	
	
}
