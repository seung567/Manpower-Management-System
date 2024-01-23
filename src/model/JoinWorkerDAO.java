package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.WorkerContVO;
import model.rec.WorkerVO;

public class JoinWorkerDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;

	public JoinWorkerDAO() throws Exception {
		// TODO Auto-generated constructor stub
		Class.forName(driver);
		System.out.println("로딩 성공!");
		conn = DriverManager.getConnection(url, user, pw);
		System.out.println("joinworker DB 연결 성공!");

	}

	// WORKER_CODE
	// WORKER_ADDR
	// WORKER_NAME
	// WORKER_RNUM
	// WORKER_TEL
	// CAREER_DETAIL
	// WORKER_EMAIL
	// WORKER_AGE
	// CAREER_PERIOD
	// SKILL_CODE
	// WORKER_ID

	public void workerInsert(WorkerVO vo) throws Exception {
		// 개인정보 등록 : 아이디 비번 비번확인 이름 주민등록번호 연락처 이메일 주소

		String sql = "insert into worker" 
				+ "(WORKER_CODE, "
				+ "WORKER_ID, "
				+ "WORKER_PW, "
				+ "WORKER_NAME,  "
				+ "WORKER_RNUM, " 
				+ "WORKER_AGE, " 
				+ "WORKER_TEL, " 
				+ "WORKER_EMAIL, " 
				+ "WORKER_ADDR, "
				+ "SKILL_CODE) "
				+ "values(worker_sq.nextval,?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);

		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getWorkerID());
		ps.setString(2, vo.getWorkerPW());
		ps.setString(3, vo.getWorkerName());
		ps.setString(4, vo.getWorkerRnum());
		ps.setString(5, vo.getWorkerAge());
		ps.setString(6, vo.getWorkerTel());
		ps.setString(7, vo.getWorkerEmail());
		ps.setString(8, vo.getWorkerAddr());
		ps.setInt(9, vo.getSkillCode());

		System.out.println(ps);
		ps.executeUpdate();
		System.out.println("파견인력정보 등록완료");
		ps.close();
	}



	public int switchResult(int code) {
		int indexNum = 0 ;

		switch (code) {
		case 0 :  indexNum = 301 ;
		break;
		case 1 :  indexNum = 302 ;
		break;
		case 2 :  indexNum = 303 ;
		break;
		case 3 :  indexNum = 304 ;
		break;
		case 4 :  indexNum = 305 ;
		break;
		case 5 :  indexNum = 306 ;
		break;
		case 6 :  indexNum = 307 ;
		break;
		case 7 :  indexNum = 308 ;
		break;
		case 8 :  indexNum = 309 ;
		break;
		case 9 :  indexNum = 310 ;
		break;
		case 10 :  indexNum = 311 ;
		break;
		case 11 :  indexNum = 312 ;
		break;
		case 12 :  indexNum = 313 ;
		break;
		case 13 :  indexNum = 314 ;
		break;
		case 14 :  indexNum = 315 ;
		break;
		case 15 :  indexNum = 316 ;
		break;
		case 16 :  indexNum = 317 ;
		break;
		case 17 :  indexNum = 318 ;
		break;
		case 18 :  indexNum = 319 ;
		break;
		case 19 :  indexNum = 320 ;
		break;
		case 20 :  indexNum = 321 ;
		break;
		case 21 :  indexNum = 322 ;
		break;
		case 22 :  indexNum = 323 ;
		break;
		case 23 :  indexNum = 324 ;
		break;
		case 24 :  indexNum = 325 ;
		break;
		case 25 :  indexNum = 326 ;
		break;
		case 26 :  indexNum = 327 ;
		break;
		case 27 :  indexNum = 328 ;
		break;
		default:
			break;
		}
		return indexNum;
	}
	
	
	//아이디 중복확인

	public String DupCheckId(String id) throws Exception {
		
		String sql = "select decode(worker_id,null,'사용가능','불가능') worker_id from worker where worker_id = '" + id + "'";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		String result = "사용가능";
		
		if (res.next()) {
			result = res.getString("worker_id");
		}

		return result;
	}
	
	//비밀번호 확인
	public String CheckPw(String pw) throws Exception {
		
		String sql = "select worker_password from worker where worker_password = '" + pw + "'";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		String result = null;
		
		if (res.next()) {
			result = res.getString("worker_password");
		}
		
		return result;
	}
	
}
