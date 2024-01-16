package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.SupportVO;

public class WorkerSupportListDAO {


	// ┌───────────────────────────────────────────멤버변수 선언부────────────────────────────────────┐
	
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker2";
	String pw = "1111";
	PreparedStatement ps = null;
	Statement stmt = null;	
	
	// └───────────────────────────────────────────멤버변수 선언부────────────────────────────────────┘

	public   WorkerSupportListDAO() throws Exception {
		connectDB();
	}

	void  connectDB() throws Exception {

		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pw);
	}

	//지원자 목록을 조회하는 메소드
	public ArrayList searchSupportList(int reqCode) throws Exception {   

		String sql = "select "
				+ "	r.req_code,"
				+ "	w.worker_code,"
				+ "	w.worker_name,"
				+ "	w.worker_tel,"
				+ "	w.worker_email,"
				+ " w.worker_age, "
				+ "	s.skill_name "
				+ "from worker w, support su, req r, skill s "
				+ "where r.req_code = su.req_code and "
				+ "su.worker_code = w.worker_code and "
				+ "w.skill_code = s.skill_code and "
				+ "r.req_code = '" + reqCode + "'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList supportList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getString("worker_name"));
			temp.add(res.getString("worker_tel"));
			temp.add(res.getString("worker_email"));
			temp.add(res.getString("worker_age"));
			temp.add(res.getString("skill_name"));

			supportList.add(temp);
		}

		stmt.close();
		System.out.println(sql);
		System.out.println("지원자목록 조회완료");
		return supportList;
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
	
	// 지원자상세정보를 조회하는 메소드
	public SupportVO supportInfoSearch(int reqCode) throws Exception {
		
		SupportVO vo = new SupportVO();

		Statement stmt = conn.createStatement();

		String sql = "select "
				+ "	w.worker_code, "
				+ "	wc.worker_career_code, "
				+ "	w.worker_name, "
				+ "	w.worker_age, "
				+ "	w.worker_tel, "
				+ "	w.worker_email, "
				+ "	wc.career_sdate, "
				+ "	wc.career_edate, "
				+ "	wc.career_detail, "
				+ "	c.certi_name "
				+ " from worker w, certi c, worker_career wc, req r, support su"
				+ " where "
				+ "	w.worker_code = c.worker_code and "
				+ "	w.worker_code = wc.worker_code and "
				+ " w.worker_code = su.worker_code and  "
				+ " su.req_code = r.req_code and "				
				+ " r.req_code = '" + reqCode + "'";

		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			vo.setWorkerCode(rs.getInt("worker_code"));
			vo.setWorkerCareerCode(rs.getInt("worker_career_code"));
			vo.setWorkerName(rs.getString("worker_name"));
			vo.setWorkerAge(rs.getInt("worker_age"));
			vo.setWorkerTel(rs.getString("worker_tel"));
			vo.setWorkerEmail(rs.getString("worker_email"));
			
			vo.setCareerSdate(rs.getDate("career_sdate"));
			vo.setCareerEdate(rs.getDate("career_edate"));
			vo.setCareerDetail(rs.getString("career_detail"));
			vo.setCertiName(rs.getString("certi_name"));
		}
		
		rs.close();
		stmt.close();

		System.out.println("상세정보 출력완료");
		return vo;
	}

}