package model.rec;

public class SheetVO {
	private String sheetDate;
	private int sheetCode, reqContCode, workerCost, charge, settleCost, custTax;
	
	public SheetVO( ) {

	}
	
	public SheetVO(String sheetDate, int sheetCode, int reqContCode, int workerCost, int charge, int settleCost, int custTax) {

		this.sheetDate = sheetDate;
		this.sheetCode = sheetCode; 
		this.reqContCode= reqContCode; 
		this.workerCost = workerCost;
		this.charge = charge;
		this.settleCost = settleCost;
		this.custTax = custTax; 
	}

	public String getSheetDate() {
		return sheetDate;
	}

	public void setSheetDate(String sheetDate) {
		this.sheetDate = sheetDate;
	}

	public int getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(int sheetCode) {
		this.sheetCode = sheetCode;
	}

	public int getreqContCode() {
		return reqContCode;
	}

	public void setreqContCode(int reqContCode) {
		this.reqContCode = reqContCode;
	}

	public int getWorkerCost() {
		return workerCost;
	}

	public void setWorkerCost(int workerCost) {
		this.workerCost = workerCost;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public int getSettleCost() {
		return settleCost;
	}

	public void setSettleCost(int settleCost) {
		this.settleCost = settleCost;
	}

	public int getCustTax() {
		return custTax;
	}

	public void setCustTax(int custTax) {
		this.custTax = custTax;
	}
	

}


	
