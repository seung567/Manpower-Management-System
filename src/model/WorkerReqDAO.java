package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ApplyVO;
import model.rec.ReqVO;

// 파견요청을 등록하는 DAO 클래스
public class WorkerReqDAO {
	// ###########################################################
	// 멤버변수
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker";
	String pw = "1111";
	PreparedStatement ps = null;
	Statement stmt = null;

	public WorkerReqDAO() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {

		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pw);

	}

	// 요청목록을 조회하는 메소드
	public ArrayList serchReqInfo(String workerCode) throws Exception {
		
		String sql = "select "
				+ "rc.req_cont_code,"
				+ "r.req_date, "
				+ "se.sector_name,"
				+ "con.country_name,"
				+ "r.req_lang_level,"
				+ "ru.recru_state,"
				+ "(select to_char(apply_date,'yyyy-mm-dd') from apply a where a.req_cont_code = rc.req_cont_code and worker_code = ?) apply_date,"
				+ "(select apply_code from apply a where a.req_cont_code = rc.req_cont_code and worker_code = ?) apply_code "
				+ "from req_cont rc, req r, sector se, city ci, country con, recru ru "
				+ "where "
				+ "rc.req_code = r.req_code and "
				+ "r.sector_code = se.sector_code and "
				+ "r.city_code = ci.city_code and "
				+ "ci.country_code = con.country_code and "
				+ "rc.req_cont_code = ru.req_cont_code";

		
		ps = conn.prepareStatement(sql);

		ps.setString(1, workerCode);
		ps.setString(2, workerCode);

		ResultSet rs = ps.executeQuery();

		ArrayList reqList = new ArrayList();

		while (rs.next()) {

			ArrayList temp = new ArrayList();

			temp.add(rs.getInt("req_cont_code"));
			temp.add(rs.getString("req_date"));
			temp.add(rs.getString("sector_name"));
			temp.add(rs.getString("country_name"));
			temp.add(rs.getString("req_lang_level"));
			temp.add(rs.getString("recru_state"));
			temp.add(rs.getString("apply_date"));
			temp.add(rs.getString("apply_code"));

			reqList.add(temp);
		}

		ps.close();
		return reqList;
	}

	public String[][] reqList(ArrayList list, String[] col) throws Exception {

		System.out.println("요청목록 출력");
		String[][] result = new String[list.size()][col.length];

		for (int i = 0; i < result.length; i++) {
			ArrayList temp = (ArrayList) list.get(i);

			for (int j = 0; j < result[i].length; j++) {

				try {
					result[i][j] = temp.get(j).toString().trim();
				} catch (Exception e) {
					result[i][j] = (String) temp.get(j);
				}
			}
		}
		System.out.println("요청목록 출력완료");

		return result;

	}

	// 기타요구사항을 수정하는 메소드
	public void etcModify(ReqVO vo) throws Exception {
		String sql = "update req " + "set etc_req = ? " + "where req_code = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getEtcReq());
		ps.setInt(2, vo.getReqCode());
		ps.executeUpdate();
	}

	// @장경희 - (인력메인) 파견요청목록 상세정보
	public ReqVO workerreqInfoSearch(String vNum) throws Exception {
		ReqVO vo = new ReqVO();

		Statement stmt = conn.createStatement();

		String sql = "SELECT r.req_code, r.cust_code, r.req_date, s.sector_name, " + "r.expec_sdate, r.expec_edate,"
				+ "l.lang_level, c.city_name, r.local, r.req_lang_level, r.sex, r.age_range, r.quali, r.etc_req, r.local_lang_level "
				+ "FROM req r " + "join req_cont rc on r.req_code = rc.req_code "
				+ "JOIN sector s ON r.sector_code = s.sector_code " + "JOIN lang l ON r.lang_code = l.lang_code "
				+ "JOIN city c ON r.city_code = c.city_code " + "WHERE rc.req_cont_code = " + vNum;

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setReqCode(rs.getInt("req_code"));
			vo.setCustCode(rs.getInt("cust_code"));
			vo.setReqDate(rs.getString("req_date"));
			vo.setSectorName(rs.getString("sector_name"));
			vo.setExpecSdate(rs.getString("expec_sdate"));
			vo.setExpecEdate(rs.getString("expec_edate"));
			vo.setLangLevel(rs.getString("lang_level"));
			vo.setCityName(rs.getString("city_name")); // corrected alias
			vo.setLocal(rs.getString("local"));
			vo.setReqLangLevel(rs.getString("req_lang_level"));
			vo.setLocalLangLevel(rs.getString("local_lang_level"));
			vo.setSex(rs.getString("sex"));
			vo.setAgeRange(rs.getString("age_range"));
			vo.setQuali(rs.getString("quali"));
			vo.setEtcReq(rs.getString("etc_req"));
		}

		rs.close();
		stmt.close();
		return vo;

	}

	// 지원등록 일자 출력 메소드
	public void applyDateInsert(ApplyVO vo) throws Exception {

		String sql = "insert into apply(apply_code, req_cont_code, worker_code, apply_date)  values (apply_sq.nextval, ?, ?, sysdate)";

		System.out.println(sql);
		ps = conn.prepareStatement(sql);

		ps.setInt(1, vo.getReqContCode());
		System.out.println(vo.getReqContCode());
		ps.setInt(2, vo.getWorkerCode());
		System.out.println(vo.getWorkerCode());
		ps.executeUpdate();

	}

	// 중복지원 불가 메소드
	public int noDupApply(int vRcc, String vWc) throws Exception {
		
		Statement stmt = conn.createStatement();
		String sql = "select  apply_code from apply  where req_cont_code =" + vRcc + " and worker_code=" + vWc;
		ResultSet rs = stmt.executeQuery(sql);

		int result = 0;
		if (rs.next()) {
			result = rs.getInt("apply_code");
		}

		rs.close();
		stmt.close();
		return result;
	}

	// (지원현황) 지원취소 메소드
	public int cancelApply(int vAc) throws Exception {

		Statement stmt = conn.createStatement();
		String sql = "delete from apply where apply_code =" + vAc;
		int result = stmt.executeUpdate(sql);
		return result;
	}

}
