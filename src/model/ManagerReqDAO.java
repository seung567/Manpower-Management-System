package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.CustVO;
import model.rec.ReqVO;
import model.rec.WorkerVO;




public class ManagerReqDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	//	String url = "jdbc:oracle:thin:@192.168.0.2:1521:bridb";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;
	
	public ManagerReqDAO() throws Exception {

		System.out.println("=====================================");
		System.out.println("ManagerWorkerDAO 실행");

		Class.forName(driver);

		System.out.println("ManagerWorkerDAO 로딩 성공!");

		conn = DriverManager.getConnection(url, user, pw);

		System.out.println("DB 연결 성공!");
		System.out.println("=====================================");

	}
	
	// 파견요청 목록 출력 메소드
	public ArrayList reqList() throws Exception {

		String sql = 
				"select\r\n"
				+ "	re.req_code,\r\n"
				+ "	cu.cust_name,\r\n"
				+ "	con.country_name,\r\n"
				+ "	ci.city_name,\r\n"
				+ "	to_char(re.total_cost,'999,999,999') total_cost,\r\n"
				+ "	re.expec_sdate,\r\n"
				+ "	decode(rc.req_cont_code,null,'미승인','승인') state \r\n"
				+ "from req re, cust cu, country con, city ci, req_cont rc \r\n"
				+ "where re.cust_code = cu.cust_code and \r\n"
				+ "re.city_code = ci.city_code and \r\n"
				+ "ci.country_code = con.country_code and \r\n"
				+ "re.req_code = rc.req_code(+)";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		ArrayList reqList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("req_code"));
			temp.add(res.getString("cust_name"));
			temp.add(res.getString("country_name"));
			temp.add(res.getString("city_name"));
			temp.add(res.getString("total_cost"));
			temp.add(res.getString("expec_sdate").substring(0,10));
			temp.add(res.getString("state"));
				
			reqList.add(temp);

		}
		
	
		res.close();
		stmt.close();

		return reqList;
		
	}
	
	
	// 계약목록 ArrayList 반환
	
	public ReqVO serachReqInfo(int reqCodeValue) throws Exception  {

		String sql = "SELECT "
				+ "r.REQ_CODE,"
				+ "r.EXPEC_SDATE, "
				+ "r.EXPEC_EDATE, "
				+ "r.LOCAL, "
				+ "r.ETC_REQ, "
				+ "city.CITY_NAME, "
                + "r.WORKER_NUM, "
                + "cust.cust_name, "
                + "TO_CHAR(r.TOTAL_COST, '999,999,999') AS total, "
                + "r.SEX, "
                + "r.AGE_RANGE, "
                + "r.QUALI, "
                + "s.SECTOR_NAME, "
                + "r.REQ_LANG_LEVEL, "
                + "l.LANG_LEVEL, "
                + "r.LOCAL_LANG_LEVEL "
                + "FROM req r, lang l, sector s, cust cust, city city "
                + "WHERE "
                + "r.city_code = city.city_code AND "
                + "r.cust_code = cust.cust_code AND "
                + "r.sector_code = s.sector_code AND "
                + "r.lang_code = l.lang_code AND "
                + "r.req_code = '" + reqCodeValue + "'";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		ReqVO vo = null;
		
		if(res.next()) {
			int reqCode = res.getInt("REQ_CODE");
			String custName = res.getString("cust_name");
			String sectorName = res.getString("SECTOR_NAME");
			int workerNum = res.getInt("WORKER_NUM");
			String expecSdate = res.getString("EXPEC_SDATE").substring(0,10);
			String expecEdate = res.getString("EXPEC_EDATE").substring(0,10);
			String langLevel = res.getString("LANG_LEVEL");
			String cityName = res.getString("CITY_NAME");
			String local = res.getString("LOCAL");
			String reqLangLevel = res.getString("REQ_LANG_LEVEL");
			String localLangLevel = res.getString("LOCAL_LANG_LEVEL");
			String etcReq = res.getString("ETC_REQ"); // 기타요구사항
			String sex = res.getString("SEX");
			String ageRange = res.getString("AGE_RANGE"); // 연령대
			String quali = res.getString("QUALI"); 
			String totalCost = res.getString("total");
			
			vo = new ReqVO(reqCode,custName,sectorName,workerNum,expecSdate,expecEdate,langLevel,cityName,local,reqLangLevel,
					localLangLevel,etcReq,sex,ageRange,quali,totalCost);
		}
		
		res.close();
		stmt.close();
		
		return vo;
		
	}
	
	public int reqCodeReturn(String reqContCode) throws Exception {
		
		String sql = "select req_code from req_cont where req_cont_code = " + reqContCode;
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		int result = 0;
		
		if(res.next()) {
			result = res.getInt("req_code");
		}
		
		return result;
	}
	
	// 파견요청 수락시 계약 DB 입력 메소드
	public void reqContInsert(String reqCode, String contSdate) throws Exception {

		String sql = "intsert into req_cont("
				+ " req_cont_code," // 파견 계약번호
				+ "	req_code," // 파견 요청번호
				+ "	req_cont_ck," // 파견 승인여부
				+ "	req_cont_sdate,"
				+ " actual_sdate,"
				+ " actual_edate) " // 계약체결일
				+ "values(" 
				+ "	req_cont.nextval," 
				+ "	?," 
				+ "	?," 
				+ "	?,"
				+ " ?,"
				+ " ?," 
				+ "	sysdate)";

		ps = conn.prepareStatement(sql);

		ps.setString(1, reqCode);
		ps.setString(2, "승인");
		ps.setString(3, contSdate);
		
		

	}
	
	// 파견요청번호 , 업체명 검색 메소드
	public CustVO custSearch(String reqCode) throws Exception {

		CustVO vo = new CustVO();

		String sql = "select cust_name from req r, cust s where r.cust_code = s.cust_code and r.req_code = '" + reqCode + "'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			vo.setCustName(res.getString("cust_name"));
		}

		res.close();
		stmt.close();

		return vo;

	}
	
	// 예상근무 종료일을 가져오는 메소드
	public ReqVO expecEdateGet(String reqCode)  throws Exception  {
		
		ReqVO vo = new ReqVO();
		
		String sql = "select expec_edate from req where req_code = '" + reqCode + "'";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		if(res.next()) {
			vo.setExpecEdate("expec_edate");
		}
		
		res.close();
		stmt.close();

		return vo;
		
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
}