package model.rec;

public class PayVO {
	private String payDate;
	private int workerCode, sheetCode, actualPay, recontIncen, workerTax;
	private int pay;
	public PayVO( ) {

	}
	
	public PayVO(String payDate, int workerCode,  int sheetCode,  int actualPay,  int recontIncen,  int workerTax) {

		this.payDate = payDate;
		this.workerCode = workerCode; 
		this.sheetCode= sheetCode; 
		this.actualPay = actualPay;
		this.recontIncen = recontIncen;
		this.workerTax = workerTax;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public int getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(int workerCode) {
		this.workerCode = workerCode;
	}

	public int getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(int sheetCode) {
		this.sheetCode = sheetCode;
	}

	public int getActualPay() {
		return actualPay;
	}

	public void setActualPay(int actualPay) {
		this.actualPay = actualPay;
	}

	public int getRecontIncen() {
		return recontIncen;
	}

	public void setRecontIncen(int recontIncen) {
		this.recontIncen = recontIncen;
	}

	public int getWorkerTax() {
		return workerTax;
	}

	public void setWorkerTax(int workerTax) {
		this.workerTax = workerTax;
	}
	

}
