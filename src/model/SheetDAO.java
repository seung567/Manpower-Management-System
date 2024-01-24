package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.PayVO;
import model.rec.SheetVO;

public class SheetDAO extends Connect {

	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	
	
	// 유승민 =========================================================
	public SheetDAO() {
		// TODO Auto-generated constructor stub
		super();
		conn = super.connectValue("SheetDAO");
	}
	
	public ArrayList sheetArrayList() throws Exception {
		
		String sql = "select "
				+ "	wc.worker_cont_code,"
				+ "	rc.req_cont_code,"
				+ "	to_char(rc.req_cont_sdate,'yyyy-mm-dd') req_cont_sdate,"
				+ "	to_char(r.total_cost,'999,999,999') total_cost,"
				+ "	decode(sh.sheet_code,null,'미발행',sh.sheet_code) sheet_code "
				+ "from worker_cont wc, apply ap, req_cont rc, req r, sheet sh "
				+ "where "
				+ "	wc.apply_code = ap.apply_code and "
				+ "	ap.req_cont_code = rc.req_cont_code and "
				+ "	rc.req_code = r.req_code and "
				+ "	wc.worker_cont_code = sh.worker_cont_code(+) and "
				+ "	wc.worker_cont_ck = '계약체결' and "
				+ " wc.cont_state = '계약체결'";
		
		stmt = conn.createStatement();

		ResultSet res = stmt.executeQuery(sql);

		ArrayList list = new ArrayList();

		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("worker_cont_code"));
			temp.add(res.getInt("req_cont_code"));
			temp.add(res.getString("req_cont_sdate"));
			temp.add(res.getString("sheet_code"));

			list.add(temp);

		}
		System.out.println("정산내역 작성완료");
		return list;

	}
	
	
	public SheetVO sheetReceipt(int code) throws Exception {
				
		String sql = "with "
				+ "req_cont_code as ("
				+ "	select "
				+ "		ap.req_cont_code as ap_reqContCode,"
				+ "		wc.worker_cont_code as wc_workerContCOde "
				+ "	from "
				+ "		apply ap, "
				+ "		req_cont rc, "
				+ "		worker_cont wc "
				+ "	where "
				+ "		ap.req_cont_code = rc.req_cont_code and "
				+ "		ap.apply_code = wc.apply_code and "
				+ "		wc.worker_cont_code = ?"
				+ "),"
				+ "per_cost as ("
				+ "	select "
				+ "		cs.per_cost as cs_perCost "
				+ "	from "
				+ "		apply ap, "
				+ "		req_cont rc, "
				+ "		req r, "
				+ "		sector se, "
				+ "		cost_standard cs, "
				+ "		worker_cont wc, "
				+ "		req_cont_code vRc "
				+ "	where "
				+ "		ap.req_cont_code = rc.req_cont_code and "
				+ "		rc.req_code = r.req_code and "
				+ "		r.sector_code = se.sector_code and "
				+ "		se.sector_code = cs.sector_code and "
				+ "		ap.apply_code = wc.apply_code and "
				+ "		wc.worker_cont_code = vRc.wc_workerContCOde"
				+ ") "
				+ "select "
				+ "	rc.ap_reqContCode , "
				+ "	ps.cs_perCost as cost,"
				+ "	ps.cs_perCost*0.3 as cost_Fee, "
				+ "	ps.cs_perCost*0.033 as cost_Tax, "
				+ "	ps.cs_perCost+(ps.cs_perCost*0.3)+(ps.cs_perCost*0.033) cost_Total "
				+ "	from req_cont_code rc, per_cost ps";
		
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, code);
		
		SheetVO vo = null;
		
		ResultSet res = ps.executeQuery();
		
		if(res.next()) {
			int vCode = res.getInt("ap_reqContCode");
			int cost = res.getInt("cost");
			int charge = res.getInt("cost_Fee");
			int settlecost = res.getInt("cost_Total");
			int custTex = res.getInt("cost_Tax");
			
			vo = new SheetVO(vCode,cost,charge,settlecost,custTex);
		}
		
		return vo;
	}
	
	public int sheetInput(int worker_code) throws Exception  {
		
		String sql = "insert into sheet("
			    + "sheet_code,"
			    + "worker_cont_code,"
			    + "worker_cost,"
			    + "charge,"
			    + "cust_tax,"
			    + "settle_cost) "
			    + "with "
			    + "req_cont_code as ("
			    + "    select "
			    + "    ap.req_cont_code as ap_reqContCode,"
			    + "    wc.worker_cont_code as wc_workerContCode "
			    + "    from apply ap, req_cont rc, worker_cont wc "
			    + "    where "
			    + "    wc.apply_code = ap.apply_code and "
			    + "    ap.req_cont_code = rc.req_cont_code and "
			    + "    wc.worker_cont_code = ? "
			    + "),"
			    + "per_cost as ("
			    + "    select cs.per_cost as cs_perCost "
			    + "    from apply ap, req_cont rc, req r, sector se, cost_standard cs, worker_cont wc, req_cont_code wReq_cont "
			    + "    where "
			    + "    wc.apply_code = ap.apply_code and "
			    + "    ap.req_cont_code = rc.req_cont_code and "
			    + "    rc.req_code = r.req_code and "
			    + "    r.sector_code = se.sector_code and "
			    + "    se.sector_code = cs.sector_code and "
			    + "    wc.worker_cont_code = wReq_cont.wc_workerContCode"
			    + ") "
			    + "select "
			    + "sheet_sq.nextval,"
			    + "rc.wc_workerContCode , "
			    + "ps.cs_perCost,"
			    + "ps.cs_perCost*0.3, "
			    + "ps.cs_perCost*0.033, "
			    + "ps.cs_perCost-(ps.cs_perCost*0.3)-(ps.cs_perCost*0.033) "
			    + "from req_cont_code rc, per_cost ps";
		
		ps = conn.prepareStatement(sql);

		ps.setInt(1, worker_code);

		int state = ps.executeUpdate();

		return state;
	}
	
	
	// 파견인력 수당 목록 메소드
	public ArrayList workerPayArrayList() throws Exception   {
		
		String sql = "select "
				+ "wc.worker_cont_code,"
				+ "wc.apply_code,"
				+ "to_char(wc.cont_date,'yyyy-mm-dd') as cont_date,"
				+ "sh.sheet_code,"
				+ "to_char(sh.sheet_date,'yyyy-mm-dd') as sheet_date,"
				+ "decode(p.pay_code,null,'미지급',p.pay_code) pay_code "
				+ "from worker_cont wc, sheet sh, pay p "
				+ "where "
				+ "wc.worker_cont_code = sh.worker_cont_code and "
				+ "sh.sheet_code = p.sheet_code(+)";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		
		ArrayList list = new ArrayList();
		
		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("worker_cont_code"));
			temp.add(res.getInt("apply_code"));
			temp.add(res.getString("cont_date"));
			temp.add(res.getInt("sheet_code"));
			temp.add(res.getString("sheet_date"));
			temp.add(res.getString("pay_code"));

			list.add(temp);

		}
		
		return list;
	}
	
	// 파견인력 수당 상세정보 메소드
	public PayVO workerPayOutput(int workerContCode)  throws Exception  {
		System.out.println("실행1");
		String sql = "with pay_result as ("
			    + "select "
			    + "ap.worker_code as v_worker_code,"
			    + "sh.worker_cont_code as v_worker_cont_code,"
			    + "sh.sheet_code as v_sheet_code,"
			    + "sh.worker_cost as v_worker_cost,"
			    + "wc.recont_num as v_recont_num "
			    + "from "
			    + "worker_cont wc,"
			    + "apply ap,"
			    + "req_cont rc,"
			    + "sheet sh "
			    + "where "
			    + "wc.apply_code = ap.apply_code and "
			    + "ap.req_cont_code = rc.req_cont_code and "
			    + "wc.worker_cont_code = sh.worker_cont_code and "
			    + "sh.worker_cont_code = " + workerContCode + "),"
			    + "incen_result as ("
			    + "select "
			    + "i.per_incen as v_per_incen "
			    + "from "
			    + "incen i, pay_result p "
			    + "where "
			    + "i.row_recont_num <= p.v_recont_num and i.hi_recont_num >= p.v_recont_num) "
			    + "select "
			    + "p.v_worker_cost,"
			    + "p.v_worker_code, "
			    + "p.v_sheet_code,"
			    + "sysdate, "
			    + "p.v_worker_cost * i.v_per_incen incen, "
			    + "(p.v_worker_cost * i.v_per_incen) * 0.033 tax,"
			    + "(p.v_worker_cost + p.v_worker_cost * i.v_per_incen) - ((p.v_worker_cost * i.v_per_incen) * 0.033) total "
			    + "from "
			    + "pay_result p, "
			    + "incen_result i,"
			    + "worker_cont wc "
			    + "where "
			    + "wc.worker_cont_code = p.v_worker_cont_code";

		/*
		 * 파견지원자번호
		 * 재계약 추가 수당
		 * 세금
		 * 실수당정산금액
		 */
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		PayVO vo = new PayVO();

		if (res.next()) {

			vo.setWorkerCode(res.getInt("v_worker_code"));
			vo.setRecontIncen(res.getInt("incen"));
			vo.setWorkerTax(res.getInt("tax"));
			vo.setActualPay(res.getInt("total"));
			vo.setPay(res.getInt("v_worker_cost"));

		}
		
		System.out.println("실행2");
		return vo;

	}
	
	public int workerPayInsert(int workerContCode)  throws Exception  {
		
		String sql = "insert into pay("
				+ "	pay_code,"
				+ "	worker_code,"
				+ "	sheet_code,"
				+ "	pay_date,"
				+ "	recont_incen,"
				+ "	worker_tax,"
				+ "	actual_pay) "
				+ "with "
				+ "pay_result as ("
				+ "	select \r\n"
				+ "		ap.worker_code as v_worker_code,"
				+ "		sh.sheet_code as v_sheet_code,"
				+ "		sh.worker_cost as v_worker_cost,"
				+ "		wc.recont_num as v_recont_num "
				+ "	from "
				+ "		worker_cont wc,"
				+ "		apply ap,"
				+ "		req_cont rc,"
				+ "		sheet sh "
				+ "	where "
				+ "		wc.apply_code = ap.apply_code and "
				+ "		ap.req_cont_code = rc.req_cont_code and "
				+ "		wc.worker_cont_code = sh.worker_cont_code and "
				+ "		wc.worker_cont_code = "+ workerContCode +"),"
				+ "incen_result as ("
				+ "	select "
				+ "		i.per_incen as v_per_incen"
				+ "	from "
				+ "		incen i, pay_result p "
				+ "	where "
				+ "		i.row_recont_num <= p.v_recont_num and i.hi_recont_num >= p.v_recont_num)"
				+ "select "
				+ "	pay_sq.nextval,"
				+ "	p.v_worker_code, "
				+ "	p.v_sheet_code,"
				+ "	sysdate, "
				+ "	p.v_worker_cost * i.v_per_incen incen, "
				+ "	(p.v_worker_cost * i.v_per_incen) *0.033 tax,"
				+ "	(p.v_worker_cost + p.v_worker_cost * i.v_per_incen) - ((p.v_worker_cost * i.v_per_incen) *0.033) total "
				+ "from "
				+ "	pay_result p, "
				+ "	incen_result i";
		/*
		 * 파견지원자번호
		 * 재계약 추가 수당
		 * 세금
		 * 실수당정산금액
		 */
		
		stmt = conn.createStatement();
		
		int state = stmt.executeUpdate(sql);		
		
		return state;
	}
	// 유승민 =========================================================
	
	// 이수정 ========================================================= 
	// 월별정산목록을 조회하는 메소드
	public ArrayList searchSheetInfo(int custCode) throws Exception {

		String sql = "select "
				+ "sh.sheet_code,"
				+ "wc.worker_cont_code,"
				+ "to_char(sh.sheet_date, 'yyyy-mm-dd') sheet_date,"
				+ "to_char(sh.settle_cost, '999,999,999') settle_cost,"
				+ "decode(sh.sheet_date,null,'미송금','송금완료') as state "
				+ "from sheet sh, worker_cont wc, apply ap, req_cont rc, req r, cust c "
				+ "where "
				+ "sh.worker_cont_code = wc.worker_cont_code and "
				+ "wc.apply_code = ap.apply_code and "
				+ "ap.req_cont_code = rc.req_cont_code and "
				+ "rc.req_code = r.req_code and "
				+ "r.cust_code = c.cust_code and "
				+ "c.cust_code = " + custCode
				+ "order by 1";
		
		System.out.println(sql);
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList sheetList = new ArrayList();

		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("sheet_code"));
			temp.add(res.getInt("worker_cont_code"));
			temp.add(res.getString("sheet_date"));
			temp.add(res.getString("settle_cost"));
			temp.add(res.getString("state"));
			
			sheetList.add(temp);
		}

		res.close();
		stmt.close();

		//		System.out.println(sql);
		System.out.println("월별정산목록 조회완료");

		return sheetList;
	}

	// 월별정산 상세정보를 조회하는 메소드
	public SheetVO sheetInfoSearch(String vNum) throws Exception {
		SheetVO vo = new SheetVO();

		Statement stmt = conn.createStatement();

		String sql = "select " 
				+ "worker_cont_code, " // 파견계약번호
				+ "sheet_date, " // 대금지급일
				+ "worker_cost, " // 파견인력수당
				+ "charge, " // 파견요청수수료
				+ "cust_tax, " // 세금
				+ "settle_cost " // 실정산금액
				+ "from sheet " 
				+ "where sheet_code = " + vNum;

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setReqContCode(rs.getInt("worker_cont_code"));
			vo.setSheetDate(rs.getString("sheet_date"));
			vo.setWorkerCost(rs.getInt("worker_cost"));
			vo.setCharge(rs.getInt("charge"));
			vo.setCustTax(rs.getInt("cust_tax"));
			vo.setSettleCost(rs.getInt("settle_cost"));
		}

		rs.close();
		stmt.close();

		System.out.println("월별정산상세정보 출력완료");
		return vo;
	}
	
	
	// 송금하면 대금지급일이 null -> 입력값으로 수정되는 메소드
		public int updateSheetDate(SheetVO vo) throws Exception {
			String sql = "update sheet set "
					+ "sheet_date = ? "
					+ "where sheet_code = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getSheetDate()); 
			ps.setInt(2, vo.getSheetCode()); 
					
			ps.executeUpdate();
			System.out.println("송금 완료");
			
			ps.close(); // 닫기 추가
			return 0;

		}
	// 이수정 ========================================================= 
		
}
















