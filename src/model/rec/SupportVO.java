package model.rec;

import java.sql.Date;

public class SupportVO {

   private int supCode, workerCode, reqCode, workerCareerCode, certiCode, workerAge;
   private String supState;
   private String supSdate; // 문자열 타입으로 받아야 할떄 사용하는 변수 
   private Date supSDATE, careerSdate, careerEdate; // Date 타입으로 받아야 할 때 사용하는 변수
   private String workerName, workerTel, workerEmail, careerDetail, certiName;
   private String careerSdateS,careerEdateS;


   public SupportVO() {
      // TODO Auto-generated constructor stub
   }

   public SupportVO(int supCode, int workerCode, int reqCode, String supState, String supSdate) {
      // TODO Auto-generated constructor stub

      this.supCode  = supCode;
      this.workerCode = workerCode;
      this.reqCode = reqCode;
      this.supState = supState;
      this.supSdate = supSdate;  
   }

   public SupportVO(int workerCode, int workerCareerCode, int certiCode,
         String workerName, int workerAge, String workerTel, String workerEmail,
         Date careerSdate, Date careerEdate, String careerDetail, String certiName) {

      this.workerCode = workerCode;
      this.workerCareerCode = workerCareerCode;
      this.certiCode = certiCode;
      this.workerName = workerName;
      this.workerAge = workerAge;
      this.workerTel = workerTel;
      this.workerEmail = workerEmail;
      this.careerSdate = careerSdate;
      this.careerEdate = careerEdate;
      this.careerDetail = careerDetail;
      this.certiName = certiName;            
   }

   public String getCareerSdateS() {
	return careerSdateS;
}

public void setCareerSdateS(String careerSdateS) {
	this.careerSdateS = careerSdateS;
}

public String getCareerEdateS() {
	return careerEdateS;
}

public void setCareerEdateS(String careerEdateS) {
	this.careerEdateS = careerEdateS;
}

public int getSupCode() {
      return supCode;
   }

   public void setSupCode(int supCode) {
      this.supCode = supCode;
   }

   public int getWorkerCode() {
      return workerCode;
   }

   public void setWorkerCode(int workerCode) {
      this.workerCode = workerCode;
   }

   public int getReqCode() {
      return reqCode;
   }

   public void setReqCode(int reqCode) {
      this.reqCode = reqCode;
   }

   public int getWorkerCareerCode() {
      return workerCareerCode;
   }

   public void setWorkerCareerCode(int workerCareerCode) {
      this.workerCareerCode = workerCareerCode;
   }

   public int getCertiCode() {
      return certiCode;
   }

   public void setCertiCode(int certiCode) {
      this.certiCode = certiCode;
   }

   public int getWorkerAge() {
      return workerAge;
   }

   public void setWorkerAge(int workerAge) {
      this.workerAge = workerAge;
   }

   public String getSupState() {
      return supState;
   }

   public void setSupState(String supState) {
      this.supState = supState;
   }

   public String getSupSdate() {
      return supSdate;
   }

   public void setSupSdate(String supSdate) {
      this.supSdate = supSdate;
   }

   public Date getSupSDATE() {
      return supSDATE;
   }

   public void setSupSDATE(Date supSDATE) {
      this.supSDATE = supSDATE;
   }

   public Date getCareerSdate() {
      return careerSdate;
   }

   public void setCareerSdate(Date careerSdate) {
      this.careerSdate = careerSdate;
   }

   public Date getCareerEdate() {
      return careerEdate;
   }

   public void setCareerEdate(Date careerEdate) {
      this.careerEdate = careerEdate;
   }

   public String getWorkerName() {
      return workerName;
   }

   public void setWorkerName(String workerName) {
      this.workerName = workerName;
   }

   public String getWorkerTel() {
      return workerTel;
   }

   public void setWorkerTel(String workerTel) {
      this.workerTel = workerTel;
   }

   public String getWorkerEmail() {
      return workerEmail;
   }

   public void setWorkerEmail(String workerEmail) {
      this.workerEmail = workerEmail;
   }

   public String getCareerDetail() {
      return careerDetail;
   }

   public void setCareerDetail(String careerDetail) {
      this.careerDetail = careerDetail;
   }

   public String getCertiName() {
      return certiName;
   }

   public void setCertiName(String certiName) {
      this.certiName = certiName;
   }




}