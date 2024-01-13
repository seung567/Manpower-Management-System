package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.MgrVO;
import model.rec.WorkerContVO;
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

	public workerDAO() throws Exception {
		
		System.out.println("=====================================");
		System.out.println("workerDAO 실행");
		
		Class.forName(driver);
		
		System.out.println("workerDAO 로딩 성공!");
		
		conn = DriverManager.getConnection(url, user, pw);
		
		System.out.println("DB 연결 성공!");
		System.out.println("=====================================");
		
	}

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
			temp.add(res.getString("WORKER_CONT_SDATE"));
			temp.add(res.getString("WORKER_CONT_EDATE"));
			temp.add(res.getString("CONT_DATE"));
			temp.add(res.getString("CONT_STATE"));
			
			contList.add(temp);
		}
		
		res.close();
		stmt.close();
		
		return contList;
	}

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

	public WorkerContVO workerCont(String workerCode) throws Exception {
		
		System.out.println("=======================================");
		System.out.println("파견인력 계약정보 검색중");
		
		String sql = "select WORKER_CONT_CODE,ACC_NUM,ACC_BANK,ACC_NAME "
				+ "from worker_cont "
				+ "where worker_code = ?";

		ps = conn.prepareStatement(sql);		
		ps.setString(1, workerCode);

		ResultSet res = ps.executeQuery();

		WorkerContVO vo = null;
		if (res.next()) {
			int workerContCode = res.getInt("WORKER_CONT_CODE");
			int accNum = res.getInt("ACC_NUM");
			String accBank = res.getString("ACC_BANK");
			String accName = res.getString("ACC_NAME");
			
			vo = new WorkerContVO(workerContCode, accNum, accBank, accName);
		}
		
		System.out.println("파견인력 계약정보 검색완료");
		System.out.println("=======================================\n");
		
		return vo;

	}
	
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
	
	public void workerContInsert(WorkerContVO vo, String id) {
		
		String sql = 
		
	}
}
