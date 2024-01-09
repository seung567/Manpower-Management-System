package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {
	
	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;
	
	public LoginDAO() throws Exception{
		// TODO Auto-generated constructor stub
		Class.forName(driver);
		System.out.println("로딩 성공!");
		conn = DriverManager.getConnection(url, user, pw);
		System.out.println("DB 연결 성공!");
		
	}
	
	public String loginCustomCheck(String id) throws Exception{
		
		String manager = null;
		String office = null;
		String worker = null;
		
		String managerSql = "select mgr_id from mgr"
				+ " where mgr_id = '" + id + "'";
	
		String officeSql = "select cust_id from cust"
				+ " where cust_id = '" + id + "'";
		
		String workerSql = "select worker_id from worker"
				+ " where worker_id = '" + id + "'";
	
		
		Statement stmtManager = conn.createStatement();
		Statement stmtOffice = conn.createStatement();
		Statement stmtWorker = conn.createStatement();
		
		ResultSet resManager = stmtManager.executeQuery(managerSql);
		ResultSet resoffice = stmtOffice.executeQuery(officeSql);
		ResultSet resWorker = stmtWorker.executeQuery(workerSql);
		

		if(resManager.next()) {
			return "관리자";
			
		}else if(resoffice.next()) {
			return "사용업체";
			
		}else if(resWorker.next()) {
			return "파견인력";
			
		}else {
			return "검색불가";
		}
	}
}










