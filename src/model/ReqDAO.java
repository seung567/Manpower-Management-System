package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.CustVO;
import model.rec.ReqContVO;
import model.rec.ReqVO;
import model.rec.WorkerVO;




public class ReqDAO extends Connect{

	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;
	
	
	// 유승민
	public ReqDAO() throws Exception {

		super();
		conn = super.connectValue("ReqDAO");

	}
	
	
	// 유승민
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
				+ "	decode(rc.req_cont_code,null,'미승인',rc.req_cont_ck) state \r\n"
				+ "from req re, cust cu, country con, city ci, req_cont rc \r\n"
				+ "where re.cust_code = cu.cust_code and \r\n"
				+ "re.city_code = ci.city_code and \r\n"
				+ "ci.country_code = con.country_code and \r\n"
				+ "re.req_code = rc.req_code(+) "
				+ "order by 1";
		
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
	
	
	// 유승민
	// 파견 요청 상세정보 가져오는 메소드
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
                + "r.TOTAL_COST, "
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
			int totalCost = res.getInt("TOTAL_COST");
			
			vo = new ReqVO(reqCode,custName,sectorName,workerNum,expecSdate,expecEdate,langLevel,cityName,local,reqLangLevel,
					localLangLevel,etcReq,sex,ageRange,quali,totalCost);
		}
		
		res.close();
		stmt.close();
		
		return vo;
		
	}
	
	// 유승민
	public int reqCodeReturn(String reqContCode) throws Exception {
		
		String sql = "select req_code from req_cont where req_cont_code = " + reqContCode;
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		int result = 0;
		
		if(res.next()) {
			result = res.getInt("req_code");
		}
		
		res.close();
		stmt.close();
		return result;
	}
	
	
	// 유승민
	// 파견요청 수락시 계약 DB 입력 메소드
	public int reqContInsert(String reqCode, ReqContVO vo, String acceptState) throws Exception {

		String sql = "insert into req_cont("
				+ "req_cont_code,"
				+ "req_code,"
				+ "req_cont_ck,"
				+ "req_cont_sdate,"
				+ "actual_sdate,"
				+ "actual_edate) " 
				+ "values(req_cont_sq.nextval,?,?,?,?,?)";

		ps = conn.prepareStatement(sql);

		ps.setString(1, reqCode);
		
		if(acceptState.equals("승인")) {
			ps.setString(2, "계약승인");
		}else if(acceptState.equals("반려")) {
			ps.setString(2, "계약반려");
		}

		ps.setString(3, vo.getReqContSdate());
		ps.setString(4, vo.getActualSdate());
		ps.setString(5, vo.getActualEdate());
		
		int state = ps.executeUpdate();
		System.out.println("파견 계약 정보 등록완료");
		
		ps.close();		
		return state;

	}
	
	
	// 유승민
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
	
	
	// 유승민
	// 예상근무 종료일을 가져오는 메소드
	public ReqVO expecEdateGet(String reqCode)  throws Exception  {
		
		ReqVO vo = new ReqVO();
		
		String sql = "select expec_edate from req where req_code = '" + reqCode + "'";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		if(res.next()) {
			vo.setExpecEdate(res.getString("expec_edate"));
		}
		
		res.close();
		stmt.close();

		return vo;
		
	}
	
	
	// 이수정 ================================================================
	// 파견 요청정보를 등록하는 메소드
	public void regist(ReqVO vo) throws Exception {

		String sql = "insert into req"
				+ "(req_code, "
				+ "req_date, " // 요청서등록일자 새로 추가
				+ "cust_code, "
				+ "sector_code, "
				+ "expec_sdate, "
				+ "expec_edate, "
				+ "lang_code, "
				+ "req_lang_level, "
				+ "local_lang_level, "
				+ "city_code, "
				+ "local, "
				+ "sex, "
				+ "worker_num, "
				+ "age_range, "
				+ "quali, "
				+ "total_cost, "
				+ "etc_req) "
				+ "values(req_sq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// 2. sql  전송객체 얻어오기 (PreparedStatement가 더 적합할 듯)
		ps = conn.prepareStatement(sql);

		// 3.  sql  전송	 (전송전에 ?로 지정)

		ps.setString(1, vo.getReqDate());    // 요청서등록일자 새로 추가
		ps.setInt(2, vo.getCustCode());
		ps.setInt(3, vo.getSectorCode());
		ps.setString(4, vo.getExpecSdate());
		ps.setString(5, vo.getExpecEdate());
		ps.setInt(6, vo.getLangCode());
		ps.setString(7, vo.getReqLangLevel());
		ps.setString(8, vo.getLocalLangLevel());
		ps.setInt(9, vo.getCityCode());
		ps.setString(10, vo.getLocal());
		ps.setString(11, vo.getSex());
		ps.setInt(12, vo.getWorkerNum());
		ps.setString(13, vo.getAgeRange());
		ps.setString(14, vo.getQuali());
		ps.setInt(15, vo.getTotalCost());
		ps.setString(16, vo.getEtcReq());

		// 4.  닫기 (Connection은 닫으면 안됨)
		ps.executeUpdate();
		
		ps.close(); // 닫기 추가
	}


	// 파견요청목록을 조회하는 메소드
	public ArrayList searchReqInfo(int custCode) throws Exception {

		String sql = "select "
				+ "r.req_code, " // 파견요청번호(req)
				+ "se.sector_name, " // 업종명(sector)
				+ "r.req_lang_level, " // 필수어학수준(req)
				+ "ci.city_name, " // 도시명(city)
				+ "r.worker_num, " // 요청인원수(req)
				+ "to_char(r.total_cost, '999,999,999') total_cost, " // 총파견비용(req)
				+ "decode(rc.req_cont_code,null,'미승인',rc.req_cont_ck) state " // 모집상태번호
				+ "from req r, sector se, city ci, req_cont rc, recru re, cust cu "
				+ "where "
				+ "r.cust_code = cu.cust_code and "
				+ "r.sector_code = se.sector_code and "
				+ "r.city_code = ci.city_code and "
				+ "r.req_code = rc.req_code(+) and "
				+ "rc.req_cont_code = re.req_cont_code(+) and "
				+ "cu.cust_code = " + custCode
				+ " order by r.req_code";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList reqList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("req_code"));
			temp.add(res.getString("sector_name"));
			temp.add(res.getString("req_lang_level"));
			temp.add(res.getString("city_name"));
			temp.add(res.getInt("worker_num"));
			temp.add(res.getString("total_cost"));		
			temp.add(res.getString("state"));		

//			String recruState = res.getString("recru_state");
//			if (recruState == null || recruState.equals("0")) {
//				temp.add("관리자 미승인");
//			} else {
//				temp.add("관리자 승인");
//			}

			reqList.add(temp);
		}

		res.close();
		stmt.close();

		System.out.println("파견요청목록 조회완료");

		return reqList;
	}


	// 요청목록을 출력하는 메소드
	public String[][] reqList(ArrayList list, String[] col) throws Exception {

		String[][] result = new String[list.size()][col.length];

		for(int i=0; i<result.length; i++) {
			ArrayList temp = (ArrayList) list.get(i);

			for(int j=0; j<result[i].length; j++) {

				try {
		               result[i][j] = temp.get(j).toString().trim();
				} catch (Exception e) {
					result[i][j] = (String) temp.get(j);
				}
			}
		}
		System.out.println("파견요청목록 출력완료");

		return result;

	}

	// 요청상세정보를 조회하는 메소드
	public ReqVO reqInfoSearch(String vNum) throws Exception {
		ReqVO vo = new ReqVO();

		Statement stmt = conn.createStatement();

		String sql = "SELECT "
				+"r.req_code, "
				+ "r.req_date, "
				+ "r.cust_code, "
				+ "s.sector_name, "
				+ "s.sector_code, "
				+ "r.expec_sdate, "
				+ "r.expec_edate,"
				+ "l.lang_level, "
				+ "c.city_name, "
				+ "r.req_lang_level, "
				+ "r.local_lang_level, "
				+ "r.local, "
				+ "r.sex, "
				+ "r.worker_num, "
				+ "r.age_range, "
				+ "r.quali, "
				+ "r.total_cost "
				+ "FROM req r "
				+ "JOIN sector s ON r.sector_code = s.sector_code "
				+ "JOIN lang l ON r.lang_code = l.lang_code "
				+ "JOIN city c ON r.city_code = c.city_code "
				+ "WHERE r.req_code = " + vNum;

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setSectorCode(rs.getInt("sector_code"));
			vo.setReqDate(rs.getString("req_date"));
			vo.setReqCode(rs.getInt("req_code"));
			vo.setCustCode(rs.getInt("cust_code"));
			vo.setSectorName(rs.getString("sector_name"));
			vo.setWorkerNum(rs.getInt("worker_num"));
			vo.setExpecSdate(rs.getString("expec_sdate"));
			vo.setExpecEdate(rs.getString("expec_edate"));
			vo.setLangLevel(rs.getString("lang_level"));
			vo.setCityName(rs.getString("city_name"));
			vo.setLocal(rs.getString("local"));
			vo.setReqLangLevel(rs.getString("req_lang_level"));
			vo.setLocalLangLevel(rs.getString("local_lang_level"));
			vo.setSex(rs.getString("sex"));
			vo.setAgeRange(rs.getString("age_range"));
			vo.setQuali(rs.getString("quali"));
			vo.setTotalCost(rs.getInt("total_cost"));
		}

		rs.close();
		stmt.close();

		System.out.println("요청상세정보 출력완료");
		return vo;


	}


	// 기타요구사항을 수정하는 메소드
	public void etcModify(ReqVO vo) throws Exception {
		String sql = "update req " +
				"set etc_req = ? " +
				"where req_code = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getEtcReq());
		ps.setInt(2, vo.getReqCode());

		ps.executeUpdate();
		
		ps.close(); // 닫기 추가

	}


	public String CustName(String id) {
		return null;
	}


	// 정보를 수정하는 파견재요청 메소드
	public int reDemand(ReqVO vo) throws Exception {
		String sql = "update req set "
				+ "EXPEC_SDATE = ?, "
				+ "EXPEC_EDATE = ?, "
				+ "REQ_LANG_LEVEL = ?, "
				+ "CITY_CODE = (select CITY_CODE from city where CITY_NAME = ?), "
				+ "LOCAL = ?, "
				+ "LANG_CODE = (select LANG_CODE from lang where LANG_LEVEL = ?), "
				+ "LOCAL_LANG_LEVEL = ?, "
				+ "SEX = ?, "
				+ "AGE_RANGE = ?, "
				+ "WORKER_NUM = ?, "
				+ "QUALI = ?, "
				+ "TOTAL_COST = ? "
				+ "where REQ_CODE = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getExpecSdate()); 
		ps.setString(2, vo.getExpecEdate()); 
		ps.setString(3, vo.getReqLangLevel()); 
		ps.setString(4, vo.getCityName()); 
		ps.setString(5, vo.getLocal()); 
		ps.setString(6, vo.getLangLevel()); 
		ps.setString(7, vo.getLocalLangLevel()); 
		ps.setString(8, vo.getSex()); 
		ps.setString(9, vo.getAgeRange()); 
		ps.setInt(10, vo.getWorkerNum()); 
		ps.setString(11, vo.getQuali()); 
		ps.setInt(12, vo.getTotalCost()); 
		ps.setInt(13, vo.getReqCode());
		
		
		int state = ps.executeUpdate();
		System.out.println("파견재요청 완료");
		
		ps.close(); // 닫기 추가
		return state;
	}

	// 파견요청을 취소하는 요청삭제 메소드
	public int reqDelete(ReqVO vo) throws Exception {

		String sql = "delete from req where req_code=? ";

		ps = conn.prepareStatement(sql);
		ps.setInt(1, vo.getReqCode()); 

		int state = ps.executeUpdate();

		System.out.println("요청삭제 완료");
		
		ps.close(); // 닫기 추가
		return state;
	}

	// 업종선택 시 파견요청서 인당파견비용에 호출되는 메소드
	public String sectorSelected(int ItemName) throws Exception {

		String sql = "SELECT "
				+ "to_char(cs.per_cost, '999,999,999') per_cost, "
				+ "s.sector_name "
				+ "FROM cost_standard cs, sector s "
				+ "WHERE cs.sector_code = s.sector_code AND "
				+ "s.sector_code  = ?";

		// PreparedStatement를 사용하여 쿼리 실행을 준비
		ps = conn.prepareStatement(sql);

		// 바인딩된 변수에 값 설정
		ps.setInt(1, ItemName);

		// 쿼리 실행 및 결과 가져오기
		ResultSet rs = ps.executeQuery();

		String result = null;

		if(rs.next()) {
			result = rs.getString("per_cost");
		}
		//		// 결과 처리
		//		while (rs.next()) {
		//			// 결과에서 필요한 작업 수행
		//			int perCost = rs.getInt("per_cost");
		//			String sectorName = rs.getString("sector_name");
		//
		//			// 결과를 사용하여 필요한 작업 수행
		//			// 예: 화면에 표시, 변수에 저장 등
		//		}

		// 사용이 끝난 자원 해제
		rs.close();
		ps.close();

		return result;
	}
	// 이수정 ================================================================
}
