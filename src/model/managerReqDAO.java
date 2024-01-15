package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ReqVO;
import model.rec.WorkerVO;




public class managerReqDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	//	String url = "jdbc:oracle:thin:@192.168.0.2:1521:bridb";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;
	
	public managerReqDAO() throws Exception {

		System.out.println("=====================================");
		System.out.println("managerWorkerDAO 실행");

		Class.forName(driver);

		System.out.println("managerWorkerDAO 로딩 성공!");

		conn = DriverManager.getConnection(url, user, pw);

		System.out.println("DB 연결 성공!");
		System.out.println("=====================================");

	}
	
	// 파견요청 목록 출력 메소드
	public ArrayList reqList() throws Exception {



		String sql = "select "
				+ "req.req_code,"
				+ "cust.cust_name,"
				+ "country.country_name,"
				+ "city.city_name,"
				+ "to_char(req.total_cost,'999,999,999') total,"
				+ "req.EXPEC_SDATE,"
				+ "cont.req_cont_code,"
				+ "cont.req_cont_ck " // 계약성사여부
				+ "from req req,req_cont cont,cust cust, country country, city city "
				+ "where "
				+ "req.cust_code = cust.cust_code and "
				+ "country.country_code  = (select country_code from city c where c.city_code = req.city_code) and "
				+ "req.city_code = city.city_code and "
				+ "req.req_code = cont.req_code(+) "
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
			temp.add(res.getString("total"));
			temp.add(res.getString("EXPEC_SDATE").substring(0,10));
			
			if(res.getString("req_cont_ck") == null) {
				temp.add("파견요청");
			}else if(res.getString("req_cont_ck").equals("계약요청")){
				temp.add("계약요청");
			}else if(res.getString("req_cont_ck").equals("계약승인")) {
				temp.add("계약승인");
			}else {
				temp.add("확인필요");
			}
				
			reqList.add(temp);

		}

		res.close();
		stmt.close();

		return reqList;
	}
	
	
	// 계약목록 ArrayList 반환
	public ArrayList reqContList() throws Exception {
		
		// 계약번호, 계약체결일, 계약만기일, 실근무시작일, 실근무종료일, 계약만기사유, 정산여부
		
		 String sql = "select " 
		            + "c.req_cont_code, "
		            + "c.req_cont_sdate, "
		            + "c.req_cont_edate, "
		            + "c.actual_sdate, "
		            + "c.actual_edate, "
		            + "c.req_cont_ewhy, "
		            + "s.sheet_code "
		            + "from req_cont c, sheet s "
		            + "where "
		            + "c.req_cont_code = s.req_cont_code(+) "
		            + "order by 1";
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		ArrayList reqContList = new ArrayList();
		
		while(res.next()) {
			
			ArrayList temp = new ArrayList();
			
			temp.add(res.getInt("req_cont_code"));
			temp.add(res.getDate("req_cont_sdate"));
			temp.add(res.getDate("req_cont_edate"));
			temp.add(res.getDate("actual_sdate"));
			temp.add(res.getDate("actual_edate"));
			temp.add(res.getString("req_cont_ewhy"));
			temp.add(res.getString("sheet_code"));

			
			reqContList.add(temp);
			
		}
		
		res.close();
		stmt.close();
		
		return reqContList;
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
	
	public ReqVO serachReqInfo(int reqCodeValue) throws Exception  {

		String sql = "select "
				+ "r.REQ_CODE,"
				+ "r.EXPEC_SDATE,"
				+ "r.EXPEC_EDATE,"
				+ "r.LOCAL,"
				+ "r.ETC_REQ,"
				+ "city.CITY_NAME,"
				+ "r.WORKER_NUM,"
				+ "cust.cust_name,"
				+ "to_char(r.TOTAL_COST,'999,999,999') total,"
				+ "r.SEX,"
				+ "r.AGE_RANGE,"
				+ "r.QUALI,"
				+ "s.SECTOR_NAME,"
				+ "r.REQ_LANG_LEVEL,"
				+ "l.LANG_LEVEL," //
				+ "r.LOCAL_LANG_LEVEL,"
				+ "c.req_cont_code "
				+ "from req r, req_cont c, lang l,sector s,cust cust,city city "
				+ "where r.req_code = c.req_code(+) and "
				+ "r.lang_code = l.lang_code and "
				+ "r.sector_code = s.sector_code and "
				+ "r.cust_code =  cust.cust_code and "
				+ "r.req_code = " + reqCodeValue;
		
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
	
	public int reqCodeReturn(int reqContCode) throws Exception {
		
		String sql = "select req_code from req_cont where req_cont_code = " + reqContCode;
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		int result = 0;
		
		if(res.next()) {
			result = res.getInt("req_code");
		}
		
		return result;
	}
}
