package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String url = "jdbc:oracle:thin:@192.168.0.2:1521:bridb";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;

	public LoginDAO() throws Exception {
		// TODO Auto-generated constructor stub
		Class.forName(driver);
		System.out.println("로딩 성공!");
		conn = DriverManager.getConnection(url, user, pw);
		System.out.println("DB 연결 성공!");

	}

	public boolean managerLoginCheck(String id, String pw) throws Exception {

		String managerSql = "select mgr_id from mgr where mgr_id = '" + id + "'" + " and MGR_PW = '" + pw + "'";
		System.out.println("관리자 로그인 테스트정보 - sql");
		System.out.println(managerSql);
		
		Statement stmtManager = conn.createStatement();
		ResultSet resManager = stmtManager.executeQuery(managerSql);

		return resManager.next();
	}
	
	public boolean custLoginCheck(String id, String pw) throws Exception {

		String officeSql = "select cust_id from cust" + " where cust_id = '" + id + "'" + " and CUST_PW = '" + pw + "'";
		System.out.println("파견업체 로그인 테스트정보 - sql");
		System.out.println(officeSql);
		
		Statement stmtOffice = conn.createStatement();
		ResultSet resoffice = stmtOffice.executeQuery(officeSql);

		return resoffice.next();
	}

	public boolean workerLoginCheck(String id, String pw) throws Exception {

		String workerSql = "select worker_id from worker" + " where worker_id = '" + id + "'" + " and WORKER_PW = '" + pw + "'";

		Statement stmtWorker = conn.createStatement();
		ResultSet resWorker = stmtWorker.executeQuery(workerSql);

		return resWorker.next();
	}
	
	
	
}
