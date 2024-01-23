package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.PayVO;

public class WorkerPayDAO extends Connect  {
	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	
	
	public WorkerPayDAO() {
		super();
		conn = super.connectValue("SheetDAO");
	}
	
	// 파견인력 수당 목록 메소드
	public ArrayList workerPayArrayList(String workerCode) throws Exception   {
		
		String sql = "select "
			    + "w.worker_code,"
			    + "wc.worker_cont_code,"
			    + "to_char(wc.cont_date,'yyyy-mm-dd') cont_date,"
			    + "to_char(sh.worker_cost,'999,999,999') worker_cost,"
			    + "decode(p.pay_date,null,'미지급',p.pay_date) pay_date "
			    + "from worker w, worker_cont wc, sheet sh, pay p, apply ap "
			    + "where "
			    + "wc.worker_cont_code = sh.worker_cont_code and "
			    + "sh.sheet_code = p.sheet_code and "
			    + "wc.apply_code = ap.apply_code and "
			    + "ap.worker_code = w.worker_code and "
			    + "w.worker_code = " + workerCode;
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		
		ArrayList list = new ArrayList();
		
		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("worker_cont_code"));
			temp.add(res.getString("cont_date"));
			temp.add(res.getString("worker_cost"));
			temp.add(res.getString("pay_date"));

			list.add(temp);

		}
		
		
		return list;
	}
	
	// 파견인력 수당 상세정보 메소드
	public PayVO workerPayOutput(int workerContCode)  throws Exception  {
		
		String sql = "with "
				+ "pay_result as ("
				+ "	select "
				+ "		ap.worker_code as v_worker_code,"
				+ "		sh.sheet_code as v_sheet_code,"
				+ "		sh.worker_cost as v_worker_cost,"
				+ "		wc.recont_num as v_recont_num "
				+ "	from "
				+ "		worker_cont wc,"
				+ "		apply ap,"
				+ "		req_cont rc,"
				+ "		sheet sh "
				+ "	where"
				+ "		wc.apply_code = ap.apply_code and "
				+ "		ap.req_cont_code = rc.req_cont_code and "
				+ "		wc.worker_cont_code = sh.worker_cont_code and "
				+ "		wc.worker_cont_code = " + workerContCode +"),"
				+ "incen_result as ("
				+ "	select "
				+ "		i.per_incen as v_per_incen"
				+ "	from "
				+ "		incen i, pay_result p "
				+ "	where "
				+ "		i.row_recont_num <= p.v_recont_num and i.hi_recont_num >= p.v_recont_num)"
				+ "select "
				+ "	p.v_worker_code, "
				+ "	p.v_sheet_code,"
				+ "	p.v_worker_cost, "
				+ "	sysdate, "
				+ "	p.v_worker_cost * i.v_per_incen incen, "
				+ "	(p.v_worker_cost * i.v_per_incen) *0.033 tax,"
				+ "	(p.v_worker_cost + (p.v_worker_cost * i.v_per_incen)) - ((p.v_worker_cost * i.v_per_incen) *0.033) total "
				+ "from "
				+ "	pay_result p, "
				+ "	incen_result i ";
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

		return vo;

	}
}
