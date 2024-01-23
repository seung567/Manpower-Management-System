package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.WorkerContVO;
import model.rec.WorkerVO;

public class ProfileInfoDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;

	public ProfileInfoDAO() throws Exception {
		// TODO Auto-generated constructor stub
		Class.forName(driver);
		System.out.println("프로필인포DAO 로딩 성공!");
		conn = DriverManager.getConnection(url, user, pw);
		System.out.println("profileinfodao DB 연결 성공!");

	}

	//
	public WorkerVO workerPfInfoSelect(String id) throws Exception {
		WorkerVO vo = new WorkerVO();
		String sql = "select WORKER_PW, " + "WORKER_NAME, " + "WORKER_RNUM, " + "WORKER_AGE, " + "WORKER_TEL, "
				+ "WORKER_EMAIL, " + "WORKER_ADDR, " + "SKILL_CODE " 
				+ "from worker where worker_id =?";

		ps = conn.prepareStatement(sql);

		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			vo.setWorkerPW(rs.getString("WORKER_PW"));
			vo.setWorkerName(rs.getString("WORKER_NAME"));
			vo.setWorkerAge(rs.getString("WORKER_AGE"));
			vo.setWorkerRnum(rs.getString("WORKER_RNUM"));
			vo.setWorkerTel(rs.getString("WORKER_TEL"));
			vo.setWorkerEmail(rs.getString("WORKER_EMAIL"));
			vo.setWorkerAddr(rs.getString("WORKER_ADDR"));
			vo.setSkillCode(rs.getInt("SKILL_CODE"));

			//		vo.setSkillCode(0);

		}
		// System.out.println(sql);
		// System.out.println("id 보내기 성공");
		rs.close();
		ps.close();
		return vo;
	}



	// 수정/등록 화면
	public int workerPfInfoupdate(WorkerVO vo) throws Exception {
//		String sql = "update worker set "
//				+ "WORKER_NAME = ? ,  " 
//				+ "WORKER_PW = ? , WORKER_TEL = ? , WORKER_EMAIL= ? , "
//				+ "WORKER_ADDR = ?, " + "CAREER_PERIOD = ?, " + "CAREER_DETAIL = ?," + "SKILL_CODE=? "
//				+ "where WORKER_ID=? ";
		String sql = "update worker set "
				+ "WORKER_NAME = ? ,  " 
				+ "WORKER_PW = ? , "
				+ "WORKER_TEL = ? , "
				+ "WORKER_RNUM = ?, "
				+ "WORKER_EMAIL= ? , "
				+ "WORKER_AGE = ?, "
				+ "WORKER_ADDR = ? " 
					+ "where WORKER_ID=? ";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getWorkerName());
		ps.setString(2, vo.getWorkerPW());
		ps.setString(3, vo.getWorkerTel());
		ps.setString(4, vo.getWorkerRnum());
		ps.setString(5, vo.getWorkerEmail());
		ps.setString(6, vo.getWorkerAge());
		ps.setString(7, vo.getWorkerAddr());
		ps.setString(8, vo.getWorkerID());

		int state = ps.executeUpdate();
		System.out.println("파견인력 프로필 변경완료");
		ps.close();
		
		return state;
	}

	

//파견 인력 계좌정보 등록
	
//	public String workerAccNameSearch(String ID) throws Exception {
//		
//		String sql = "select worker_name from worker where worker_id =  '" + ID + "'";
//		stmt = conn.createStatement();
//		ResultSet res = stmt.executeQuery(sql);	
//	
//		String code = null;
//		
//		if (res.next()) {
//			code = res.getString("worker_name");
//		}
//		
//		return code;
//		
//	}	
//	
//	
//	public void  workerAccInsert(WorkerContVO vo) throws Exception {
//
//		String sql = "insert into worker_cont " 
//		+ "(WORKER_CONT_CODE,  "  + "ACC_NAME, " + "ACC_BANK, "
//				+ "ACC_NUM) " + "values (worker_cont_sq.nextval, worker_sq.nextval, ?, ?, ?)";
//		
//		System.out.println(sql);
//
//		ps = conn.prepareStatement(sql);
//		ps.setString(1, vo.getAccName());
//		ps.setString(2, vo.getAccBank());
//		ps.setString(3, vo.getAccNum());
//
//		ps.executeUpdate();
//		System.out.println("파견인력 계좌정보 등록완료");
//		ps.close();
//	}
//
//	
//	public int workerContupdate(WorkerContVO vo) throws Exception {
//		String sql = " update worker_cont set " + "ACC_BANK=?, " + "ACC_NUM=? " + "where ACC_NAME=? ";
//
//		ps = conn.prepareStatement(sql);
//		ps.setString(1, vo.getAccBank());
//		ps.setString(2, vo.getAccNum());
//		ps.setString(3, vo.getAccName());
//
//	//	System.out.println(sql);
//		int state = ps.executeUpdate();
//		System.out.println("파견인력 계좌정보 변경완료");
//		ps.close();
//		return state;
//
//	}

//	public void workerAccInsert(WorkerContVO vo) {
//		// TODO Auto-generated method stub
//		
//	}

}
