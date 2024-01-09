package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.WorkerVO;

public class workerDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;

	public WorkerVO workerInfoSerch(String code) throws Exception{
		workervo = new WorkerVO();

		String sql = "select"
				+ " worker_name, worker_age, worker_tel," // 이름 , 나이 , 연락처
				+ " worker_email, career_priod, career_detail" // 이메일 , 경력기간 , 경력내용
				+ " from worekr"
				+ " where worker_code = " + code;

		stmt = conn.createStatement();		
		ResultSet res = stmt.executeQuery(sql);

		if(res.next()) {
			workervo.setWorkerName(res.getString("worker_name")); // 이름
			workervo.setWorkerAge(res.getString("worker_age")); // 나이
			workervo.setWorkerTel(res.getString("worker_tel")); // 연락처
			workervo.setWorkerEmail(res.getString("worker_email")); // 이메일
			workervo.setCareerPeriod(res.getString("career_priod")); // 경력기간
			workervo.setCareerDetail(res.getString("career_detail")); // 경력내용
		} // end if

		return workervo;
		
	} // WorkerVO 메소드 끝

}
