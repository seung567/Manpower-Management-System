package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.SupportVO;

public class WorkerConfirmDAO extends Connect {


	// ┌───────────────────────────────────────────멤버변수 선언부────────────────────────────────────┐

	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	// └───────────────────────────────────────────멤버변수 선언부────────────────────────────────────┘

	public   WorkerConfirmDAO() throws Exception {
		super();
		conn = super.connectValue("ManagerWorkerDAO");
	}


	// 유승민
	//지원자 목록을 조회하는 메소드
	public ArrayList searchSupportList(int reqContCode) throws Exception {   

		   String sql = "select"
		            + "   ap.req_cont_code,"
		            + " ap.apply_code, "
		            + "   wo.worker_code,"
		            + "   ap.apply_date,"
		            + "   wo.worker_name,"
		            + "   wo.worker_tel,"
		            + "   wo.worker_email,"
		            + "   wo.worker_age,"
		            + "   wc.worker_cont_ck,"
		            + "   wc.cont_state,"
		            + " wc.worker_cont_code "
		            + "from apply ap, worker wo, skill sk, worker_cont wc "
		            + "where ap.worker_code = wo.worker_code and "
		            + "ap.apply_code = wc.apply_code(+) and "
		            + "wo.skill_code = sk.skill_code and "
		            + "ap.req_cont_code = " + reqContCode 
		            + " order by 1";

		      stmt = conn.createStatement();
		      ResultSet res = stmt.executeQuery(sql);

		      ArrayList supportList = new ArrayList();

		      while(res.next()) {

		         ArrayList temp = new ArrayList();

		         temp.add(res.getInt("worker_cont_code"));
		         temp.add(res.getInt("apply_code"));
		         temp.add(res.getString("apply_date").substring(0,10));
		         temp.add(res.getString("worker_name"));
		         temp.add(res.getString("worker_tel"));
		         temp.add(res.getString("worker_age"));
		         temp.add(res.getString("worker_cont_ck"));
		         temp.add(res.getString("cont_state"));

		         supportList.add(temp);
		      }
		      
		      res.close();
		      stmt.close();
		      System.out.println("지원자목록 조회완료");
		      return supportList;
	}
	

	// 유승민
	// ArrayList 2차원 배열 변환 메소드
	public String[][] changeArray(ArrayList list, String[] col) throws Exception {

		String[][] result = new String[list.size()][col.length];

		for (int i = 0; i < result.length; i++) {
			ArrayList temp = (ArrayList) list.get(i);

			for (int j = 0; j < result[i].length; j++) {

				try {
		               result[i][j] = temp.get(j).toString().trim();
				} catch (Exception e) {
					// TODO: handle exception
					result[i][j] = (String) temp.get(j);
				}
			}
		}

		return result;

	}

	// 유승민
	// 지원자상세정보를 조회하는 메소드
	public SupportVO supportInfoSearch(int applyCode) throws Exception {

		SupportVO vo = new SupportVO();

		Statement stmt = conn.createStatement();

		String sql = "select "
				+ "a.apply_code, "
				+ "a.apply_date, "
				+ "a.worker_code, "
				+ "w.worker_name, "
				+ "w.worker_age, "
				+ "w.worker_tel, "
				+ "w.worker_email, "
				+ "s.skill_name, "
				+ "(select min(c.career_sdate) from career c where c.worker_code = w.worker_code) as minsdate, "
				+ "(select max(c.career_Edate) from career c where c.worker_code = w.worker_code) as maxedate "
				+ "from worker w,apply a,skill s "
				+ "where a.worker_code = w.worker_code and "
				+ "w.skill_code = s.skill_code and "
				+ "a.apply_code = " + applyCode;

		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			vo.setWorkerCode(rs.getInt("worker_code"));
			vo.setWorkerName(rs.getString("worker_name"));
			vo.setWorkerAge(rs.getInt("worker_age"));
			vo.setWorkerTel(rs.getString("worker_tel"));
			vo.setWorkerEmail(rs.getString("worker_email"));
			vo.setSkillName(rs.getString("skill_name"));
			vo.setCareerSdate(rs.getDate("minsdate"));
			vo.setCareerEdate(rs.getDate("maxedate"));
		}

		rs.close();
		stmt.close();

		System.out.println("상세정보 출력완료");
		return vo;
	}

	//유승민
	//지원자 자격증 목록 조회 하는 메소드

	public ArrayList serchCertiInfo(String applyCode) throws Exception {



		String sql = "select CERTI_CODE, "
				+ "CERTI_NAME, "
				+ "CERTI_NUM, "
				+ "to_char(CERTI_DATE, 'yyyy-mm-dd')CERTI_DATE, "		
				+ "to_char(CERTI_EXP_PERIOD, 'yyyy-mm-dd') CERTI_EXP_PERIOD "
				+ "from certi c, worker w, apply a"
				+ " where "
				+ "a.worker_code = w.worker_code and "
				+ "w.worker_code = c.worker_code and "
				+ "a.apply_code = " +  applyCode
				+ " order by 1"; 

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

	//유승민
	//지원자 경력 목록 조회 하는 메소드
	public ArrayList careerList(String applyCode) throws Exception {

		String sql = "select "
				+ "   career_name,"
				+ "   to_char(career_sdate, 'yyyy-mm-dd') career_sdate,"
				+ "   to_char(career_edate, 'yyyy-mm-dd') career_edate, "
				+ "   career_detail "
				+ "from career c, worker w, apply a "
				+ "where a.worker_code = w.worker_code and "
				+ "w.worker_code = c.worker_code and "
				+ "a.apply_code = " + applyCode 
				+ " order by 1"; 

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList contList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getString("career_name"));
			temp.add(res.getString("career_sdate"));
			temp.add(res.getString("career_edate"));
			temp.add(res.getString("career_detail"));

			contList.add(temp);

		}

		res.close();
		stmt.close();

		return contList;
	}
	
	
	// 이수정	
	//지원자 목록의 업체확인을 업데이트하는 메소드
	public int stateUpdate(String workerContCode) throws Exception {   
		
		stmt = conn.createStatement();
		
		String sql = "update worker_cont set cont_state = '계약확인' where worker_cont_code = " + workerContCode;
		int state = stmt.executeUpdate(sql);		
		return state;
	}
	
	
}




