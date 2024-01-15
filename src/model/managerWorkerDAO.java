package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.MgrVO;
import model.rec.WorkerContVO;
import model.rec.WorkerVO;

public class managerWorkerDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	//	String url = "jdbc:oracle:thin:@192.168.0.2:1521:bridb";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;

	public managerWorkerDAO() throws Exception {

		System.out.println("=====================================");
		System.out.println("managerWorkerDAO 실행");

		Class.forName(driver);

		System.out.println("managerWorkerDAO 로딩 성공!");

		conn = DriverManager.getConnection(url, user, pw);

		System.out.println("DB 연결 성공!");
		System.out.println("=====================================");

	}


	// 인력 상세정보 DAO
	public WorkerVO workerInfoSerch(String code) throws Exception {
		workervo = new WorkerVO();

		String sql = "select" + " worker_name, worker_age, worker_tel," // 이름 , 나이 , 연락처
				+ " worker_email, career_period, career_detail" // 이메일 , 경력기간 , 경력내용
				+ " from worker" + " where worker_code = " + code;

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			workervo.setWorkerCode(Integer.parseInt(code));
			workervo.setWorkerName(res.getString("worker_name")); // 이름
			workervo.setWorkerAge(res.getString("worker_age")); // 나이
			workervo.setWorkerTel(res.getString("worker_tel")); // 연락처
			workervo.setWorkerEmail(res.getString("worker_email")); // 이메일
			workervo.setCareerPeriod(res.getString("career_period")); // 경력기간
			workervo.setCareerDetail(res.getString("career_detail")); // 경력내용
		} // end if

		res.close();
		stmt.close();

		return workervo;

	} // WorkerVO 메소드 끝

	// 인력목록 출력 메소드
	public ArrayList serchWorkerInfo() throws Exception {

		String sql = "select" + " worker_code,worker_name,worker_tel," + " worker_age,career_detail,career_period"
				+ " from worker" + " order by worker_code";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList workerList = new ArrayList();

		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("worker_code"));
			temp.add(res.getString("worker_name"));
			temp.add(res.getString("worker_tel"));
			temp.add(res.getString("worker_age"));
			temp.add(res.getString("career_detail"));
			temp.add(res.getString("career_period"));

			workerList.add(temp);
		}

		res.close();
		stmt.close();

		return workerList;
	}

	// 자격증 목록 출력 메소드
	public ArrayList serchCertiInfo(String workerCode) throws Exception {



		String sql = "select CERTI_CODE,CERTI_NAME,CERTI_NUM,CERTI_DATE,CERTI_EXP_PERIOD "
				+ "from certi"
				+ " where WORKER_CODE = " +  workerCode; 

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList certiList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("CERTI_CODE"));
			temp.add(res.getString("CERTI_NAME"));
			temp.add(res.getString("CERTI_NUM"));
			temp.add(res.getString("CERTI_DATE"));
			temp.add(res.getString("CERTI_EXP_PERIOD"));

			certiList.add(temp);
		}

		res.close();
		stmt.close();

		return certiList;
	}

	// 인력별 계약 목록 출력 메소드
	public ArrayList workerContInfo(String workerCode) throws Exception {



		String sql = "select cont.WORKER_CONT_CODE, worker.worker_name, cont.WORKER_CONT_SDATE, cont.WORKER_CONT_EDATE,cont.CONT_DATE,cont.CONT_STATE "
				+ "from worker_cont cont, worker worker "
				+ "where cont.worker_code = worker.worker_code and cont.worker_code = " + workerCode;

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList contList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("WORKER_CONT_CODE"));
			temp.add(res.getString("worker_name"));
			temp.add(res.getString("WORKER_CONT_SDATE").substring(0,10));
			temp.add(res.getString("WORKER_CONT_EDATE").substring(0,10));
			temp.add(res.getString("CONT_DATE").substring(0,10));
			temp.add(res.getString("CONT_STATE"));

			contList.add(temp);

		}

		res.close();
		stmt.close();

		return contList;
	}

	// ArrayList 2차원 배열 변환 메소드
	public String[][] workerList(ArrayList list, String[] col) throws Exception {

		String[][] result = new String[list.size()][col.length];

		for (int i = 0; i < result.length; i++) {
			ArrayList temp = (ArrayList) list.get(i);

			for (int j = 0; j < result[i].length; j++) {

				try {
					result[i][j] = temp.get(j).toString();
				} catch (Exception e) {
					// TODO: handle exception
					result[i][j] = (String) temp.get(j);
				}
			}
		}

		return result;

	}


	public String mgrName(String id) {

		return null;
	}

	// 계약정보 출력 메소드
	public WorkerContVO workerCont(String contCode) throws Exception {

		System.out.println(contCode);

		System.out.println("=======================================");
		System.out.println("파견인력 계약정보 검색중");

		String sql = "select "
				+ "WORKER_CONT_CODE,"// 계약번호
				+ "WORKER_CODE," // 파견인력번호
				+ "MGR_CODE," // 관리자 번호
				+ "WORKER_CONT_SDATE," // 계약시작일
				+ "WORKER_CONT_EDATE," // 계약만료일
				+ "CONT_DATE," // 계약일
				+ "CONT_PERIOD," // 계약기간
				+ "RECONT_NUM,"  // 재계약횟수
				+ "CONT_STATE," // 계약상태
				+ "ACC_NUM," // 계좌번호 
				+ "ACC_BANK," // 은행명
				+ "ACC_NAME " // 예금주명
				+ "from worker_cont "
				+ "where worker_cont_code = ?";

		ps = conn.prepareStatement(sql);		
		ps.setInt(1, Integer.parseInt(contCode));

		ResultSet res = ps.executeQuery();

		WorkerContVO vo = null;

		if (res.next()) {

			int workerContCode = res.getInt("WORKER_CONT_CODE");
			int workerCode = res.getInt("WORKER_CODE");
			int mgrCode = res.getInt("MGR_CODE");
			String workeContSdate = res.getString("WORKER_CONT_SDATE").substring(0,10);
			String workerContEdate = res.getString("WORKER_CONT_EDATE").substring(0,10);
			String contDate = res.getString("CONT_DATE");
			String contPeriod = res.getString("CONT_PERIOD");
			int recontNum = res.getInt("RECONT_NUM");
			String contState = res.getString("CONT_STATE");
			int accNum = res.getInt("ACC_NUM");
			String accBank = res.getString("ACC_BANK");
			String accName = res.getString("ACC_NAME");

			vo = new WorkerContVO(
					workerContCode,
					workerCode,
					accNum,
					accBank,
					accName,
					workeContSdate,
					workerContEdate,
					recontNum,
					contPeriod,
					contDate,
					mgrCode,
					contState);

		}

		System.out.println("파견인력 계약정보 검색완료");
		System.out.println("=======================================\n");

		return vo;

	}

	// 관리자 정보 출력 메소드
	public MgrVO mgrNameSerch(String id) throws Exception  {

		System.out.println("=======================================");
		System.out.println("관리자 정보 검색중");

		String sql = "select mgr_code, mgr_name from mgr where mgr_id = '" + id + "'";

		stmt = conn.createStatement();		
		ResultSet res = stmt.executeQuery(sql);

		MgrVO vo = null;

		if(res.next()) {

			int code = Integer.parseInt(res.getString("mgr_code"));
			String name = res.getString("mgr_name");

			vo = new MgrVO(code,name);

		}


		System.out.println("관리자 정보 검색완료");
		System.out.println("=======================================\n");

		return vo;

	}

	// 인력 계약정보 입력 메소드
	public int workerContInsert(WorkerContVO vo, String id) throws Exception {

		String sql = "insert into worker_cont("
				+ "worker_cont_code," // 고용계약번호
				+ "worker_code," // 인력번호
				+ "worker_cont_sdate," // 계약시작일
				+ "worker_cont_edate," // 계약만료일
				+ "recont_num," // 재계약횟수
				+ "cont_period," // 계약기간
				+ "cont_date," // 계약일
				+ "mgr_code,"
				+ "CONT_STATE) " // 관리자 코드
				+ "values("
				+ "worker_cont_sq.nextval," // 고용계약번호
				+ "?," // 인력번호
				+ "?," // 계약시작일
				+ "?," // 계약만료일
				+ "(select count(worker_name) from worker where worker_code = ? group by worker_name)," // 재계약횟수
				+ "?,"  // 계약기간
				+ "?," // 계약일
				+ "(select mgr_code from mgr where mgr_id = ?)," // 관리자 코드
				+ "?)"; // 요청상태

		ps = conn.prepareStatement(sql);
		ps.setInt(1, vo.getWorkerCode());
		ps.setString(2, vo.getWorkeContSdate());
		ps.setString(3, vo.getWorkerContEdate());
		ps.setInt(4, vo.getWorkerCode());
		ps.setString(5, null);
		ps.setString(6, vo.getContDate());
		ps.setString(7, id);
		ps.setString(8, "계약요청");

		int state = ps.executeUpdate();
		ps.close();
		return state;

	}

	// 파견인력 최근 계약만료일 출력 메소드
	public Date workerEdateOut(String workerCode) throws Exception {

		String sql = "select max(worker_cont_edate) from worker_cont " + "where worker_code = '" + workerCode + "'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		Date eDate = null;
		if(res.next()) {
			eDate = res.getDate("max(worker_cont_edate)");
		}

		return eDate;
	}

	// 계약건별 인력 목록 출력 메소드
	public ArrayList reqWorkerCont(String reqCode) throws Exception {



		String sql = "select"
				+ "	c.req_code,"
				+ "	c.worker_code,"
				+ "	w.worker_name,"
				+ "	w.worker_tel,"
				+ "	s.skill_name "
				+ "from req_cont c, worker w, skill s "
				+ "where c.worker_code = w.worker_code and w.skill_code = s.skill_code and c.req_code = " + reqCode;

		stmt = conn.createStatement();	
		ResultSet res = stmt.executeQuery(sql);

		ArrayList contList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("req_code"));
			temp.add(res.getInt("worker_code"));
			temp.add(res.getString("worker_name"));
			temp.add(res.getString("worker_tel"));
			temp.add(res.getString("skill_name"));

			contList.add(temp);

		}

		res.close();
		stmt.close();

		return contList;
	}

}
