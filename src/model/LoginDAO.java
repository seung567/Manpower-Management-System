package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.MgrVO;

public class LoginDAO extends Connect{

	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	
	// 유승민
	public LoginDAO() throws Exception {
		
		super();
		conn = super.connectValue("LoginDAO");

	}

	
	// 유승민
	public boolean managerLoginCheck(String id, String pw) throws Exception {

		String managerSql = "select mgr_id from mgr where mgr_id = '" + id + "'" + " and MGR_PW = '" + pw + "'";
		System.out.println("관리자 로그인 테스트정보 - sql");
		System.out.println(managerSql);
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(managerSql);

		
		return res.next();
	}
	
	
	// 유승민
	public boolean custLoginCheck(String id, String pw) throws Exception {

		String officeSql = "select cust_id from cust" + " where cust_id = '" + id + "'" + " and CUST_PW = '" + pw + "'";
		System.out.println("파견업체 로그인 테스트정보 - sql");
		System.out.println(officeSql);
		
		stmt = conn.createStatement();
		ResultSet resoffice = stmt.executeQuery(officeSql);

		
		stmt.close();
		
		return resoffice.next();
	}

	
	// 유승민
	public boolean workerLoginCheck(String id, String pw) throws Exception {

		String workerSql = "select worker_id from worker" + " where worker_id = '" + id + "'" + " and WORKER_PW = '" + pw + "'";

		stmt = conn.createStatement();
		ResultSet resWorker = stmt.executeQuery(workerSql);
		

		return resWorker.next();
	}
	
	public MgrVO mgrInfoGet(String mgrID) throws Exception {

		String sql = "select mgr_code, mgr_name from mgr where mgr_id = '" + mgrID + "'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		MgrVO vo = new MgrVO();

		if (res.next()) {

			vo.setMgrCode(res.getInt("mgr_code"));
			vo.setMgrName(res.getString("mgr_name"));

		}
		
		res.close();
		stmt.close();
		return vo;
	}

	public int managerCode(String id) throws Exception {

		String sql = "select mgr_code from mgr where mgr_id = '" + id + "'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		int code = 0;

		if (res.next()) {
			code = res.getInt("mgr_code");
		}
		
		res.close();
		stmt.close();
		return code;

	}
}
