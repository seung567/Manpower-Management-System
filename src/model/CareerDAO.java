package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.CareerVO;
import model.rec.CertiVO;
import model.rec.WorkerVO;

public class CareerDAO extends Connect{
	
	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;

	public CareerDAO() throws Exception {

		super();
		conn = super.connectValue("WorkerContDAO");

	}
	
	
	// 유승민
	public ArrayList careerList(String workerCode) throws Exception {

		String sql = "select "
				+ "	career_name,"
				+ "	to_char(career_sdate,'yyyy-mm-dd') career_sdate,"
				+ "	career_edate, "
				+ "	career_detail "
				+ "from career "
				+ "where WORKER_CODE = " + workerCode;

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
//장경희 ==================================================================
	
	public WorkerVO workerPfInfoSelect(String id) throws Exception {
		return null;
	}

	public String workerCode(String id) throws Exception {

		String sql = "select worker_code from worker where worker_id = '" + id + "'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		String result = null;

		if (res.next()) {
			result = res.getString("worker_code");
		}

		return result;
	}


	// 경력 등록
	public void CareerInsert(CareerVO vo, String workerCode) throws Exception {

		String sql = "insert into career" + "(CAREER_CODE, " + "WORKER_CODE, " + "CAREER_NAME, " + "CAREER_SDATE, "
				+ "CAREER_EDATE, " + "CAREER_DETAIL) " + "values(worker_career_sq.nextval, ?,?,?,?,?)";

		ps = conn.prepareStatement(sql);
		ps.setString(1, workerCode);
		ps.setString(2, vo.getCareerName());
		ps.setString(3, vo.getCareerSdate());
		ps.setString(4, vo.getCareerEdate());
		ps.setString(5, vo.getCareerDetail());

		System.out.println(ps);
		ps.executeUpdate();
		System.out.println("경력정보 등록완료");
		ps.close();
	}

	// 경력 목록 출력 메소드
	public ArrayList searchCareerInfo(String workerCode) throws Exception {

		stmt = conn.createStatement();
		String sql = "select " + "CAREER_CODE, " + "CAREER_NAME, " + "CAREER_SDATE, "
				+ "CAREER_EDATE, " + "CAREER_DETAIL " + "from career "
				+ "WHERE worker_code =" + workerCode;

	//	System.out.println(sql);

		ResultSet res = stmt.executeQuery(sql);

		ArrayList careerList = new ArrayList();

		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("CAREER_CODE"));
			temp.add(res.getString("CAREER_NAME"));
			temp.add(res.getString("CAREER_SDATE"));
			temp.add(res.getString("CAREER_EDATE"));
			temp.add(res.getString("CAREER_DETAIL"));

			careerList.add(temp);
		}

		res.close();
		stmt.close();
		return careerList;
	}

	// 경력 목록 상세정보 값 반환
	public CareerVO workersearchCareerInfo(String vNum2, CareerVO vo) throws Exception {

		stmt = conn.createStatement();

		String sql = "select " + "CAREER_CODE, " + "CAREER_NAME, " + "CAREER_SDATE, "
				+ "CAREER_EDATE, " + "CAREER_DETAIL " + "from career "
				+ "where career_code = " + vNum2;
		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {

			vo.setCareerCode(res.getInt("CAREER_CODE"));
			vo.setCareerName(res.getString("CAREER_NAME"));
			vo.setCareerSdate(res.getString("CAREER_SDATE"));
			vo.setCareerEdate(res.getString("CAREER_EDATE"));
			vo.setCareerDetail(res.getString("CAREER_DETAIL"));

		}

		res.close();
		stmt.close();
		return vo;
	}

	// 경력 삭제
	public void careerDelete(int careerCode) throws Exception {

		Statement stmt = conn.createStatement();
		String sql = " delete from " + "career " + "where career_code =" + careerCode;
		stmt.executeUpdate(sql);
		stmt.close();

	}
	
	
	//경력정보 수정 
	public void careerModify(CareerVO vo) throws Exception {

		String sql = " update career set CAREER_NAME=?, CAREER_SDATE=?, CAREER_EDATE=?, CAREER_DETAIL=? where career_code =?"; 

	//	System.out.println(sql);
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, vo.getCareerName());
		ps.setString(2, vo.getCareerSdate());
		ps.setString(3, vo.getCareerEdate());
		ps.setString(4, vo.getCareerDetail());
		ps.setInt(5, vo.getCareerCode());
		
		ps.executeUpdate();
		ps.close();

	}
	

	// 경력 목록 ArrayList 2차원 배열 변환 메소드
	public String[][] careerList(ArrayList list, String[] col) throws Exception {

		String[][] result = new String[list.size()][col.length];

		for (int i = 0; i < result.length; i++) {
			ArrayList temp = (ArrayList) list.get(i);

			for (int j = 0; j < result[i].length; j++) {

				try {
					result[i][j] = temp.get(j).toString();
				} catch (Exception e) {
					result[i][j] = (String) temp.get(j);
				}
			}
		}
		return result;
	}

	// 자격증 등록
	public void CertiInsert(CertiVO vo, String workerCode) throws Exception {

		String sql = "insert into certi" + "(CERTI_CODE," + "WORKER_CODE, " + "CERTI_NAME, " + "CERTI_NUM, "
				+ "CERTI_DATE, " + "CERTI_EXP_PERIOD) " + "values(certi_sq.nextval, ?,?,?,?,?)";

		ps = conn.prepareStatement(sql);
		ps.setString(1, workerCode);
		ps.setString(2, vo.getCertiName());
		ps.setInt(3, vo.getCertiNum());
		ps.setString(4, vo.getCertiDate());
		ps.setString(5, vo.getCertiExpPeriod());

		System.out.println(ps);
		ps.executeUpdate();
		System.out.println("자격증 정보 등록완료");
		ps.close();
	}

	// 자격증 삭제
	public void certiDelete(int certiCode) throws Exception {

		Statement stmt = conn.createStatement();
		String sql = " delete from " + "certi " + "where certi_code =" + certiCode;
		stmt.executeUpdate(sql);
		stmt.close();
	}

	//자격증 정보 수정
	public void certiModify(CertiVO vo) throws Exception {

		String sql = " update certi set CERTI_NAME=?, CERTI_NUM=?, CERTI_DATE=?, CERTI_EXP_PERIOD=? where certi_code =?"; 

		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getCertiName());
		ps.setInt(2, vo.getCertiNum());
		ps.setString(3, vo.getCertiDate());
		ps.setString(4, vo.getCertiExpPeriod());
		ps.setInt(5, vo.getCertiCode());
		ps.executeUpdate();
		ps.close();

	}
	

	// 자격증 목록 출력 메소드
	public ArrayList searchCertiInfo(String workerCode) throws Exception {

		String sql = "select " + "CERTI_CODE, " + "CERTI_NAME, " + "CERTI_NUM, "
				+ "CERTI_DATE, " + "CERTI_EXP_PERIOD " + "from certi "
				+ "where worker_code=" + workerCode;

	//	System.out.println(sql);

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList certiList = new ArrayList();

		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("CERTI_CODE"));
			temp.add(res.getString("CERTI_NAME"));
			temp.add(res.getInt("CERTI_NUM"));
			temp.add(res.getString("CERTI_DATE"));
			temp.add(res.getString("CERTI_EXP_PERIOD"));

			certiList.add(temp);
		}

		res.close();
		stmt.close();
		return certiList;
	}

	// 자격증 목록 상세정보 출력 메소드
	public CertiVO workersearchCertiInfo(String vNum3, CertiVO vo) throws Exception {

		stmt = conn.createStatement();
		String sql = "select " +  "CERTI_CODE, " + "CERTI_NAME, " + "CERTI_NUM, "
				+ "CERTI_DATE, " + "CERTI_EXP_PERIOD " + "from certi "
				+ "where certi_code = " + vNum3;

		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {

			vo.setCertiCode(res.getInt("CERTI_CODE"));
			vo.setCertiName(res.getString("CERTI_NAME"));
			vo.setCertiNum(res.getInt("CERTI_NUM"));
			vo.setCertiDate(res.getString("CERTI_DATE"));
			vo.setCertiExpPeriod(res.getString("CERTI_EXP_PERIOD"));

		}

		res.close();
		stmt.close();
		return vo;
	}

	
//장경희 ==================================================================
}
