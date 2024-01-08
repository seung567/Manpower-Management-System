package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public void loginCustomCheck(String id) {
		
		String manager = null;
		String office = null;
		String worker = null;
		
		String managerSql = "select mgr_id from mgr"
				+ "where mgr_id = '" + id + "'";
		
		String officeSql = "select mgr_id from mgr"
				+ "where mgr_id = '" + id + "'";
		
		String workerSql = "select mgr_id from mgr"
				+ "where mgr_id = '" + id + "'";
		
//		stmt = conn.createStatement();
	}
}
