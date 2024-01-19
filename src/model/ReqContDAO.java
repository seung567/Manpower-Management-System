package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ReqContVO;
import model.rec.WorkerVO;

public class ReqContDAO extends Connect{

	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;

	public ReqContDAO() throws Exception {

		super();
		conn = super.connectValue("ReqContDAO");

	}
	
	
	// 유승민	
	// 사용업체와 계약된 파견요청건의 상세정보 가져오는 메소드
	public ReqContVO reqContInfo(int reqContCode) throws Exception  {
		
		String sql = "select "
				+ "	req_cont_code, "
				+ "	req_code, "
				+ "	req_cont_ck, "
				+ "	req_cont_sdate, "
				+ "	req_cont_edate, "
				+ "	req_cont_ewhy,"
				+ " actual_sdate,"
				+ "	actual_edate "
				+ "from req_cont "
				+ "where req_cont_code = " + reqContCode;
		
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		
		ReqContVO vo = null;
		
		if(res.next()) {
			
			int contCode = res.getInt("req_cont_code");
			int code = res.getInt("req_code");
			String contCk = res.getString("req_cont_ck");
			String contSdate = res.getString("req_cont_sdate");
			String contEdate = res.getString("req_cont_edate");
			String contEwhy = res.getString("req_cont_ewhy");
			String actualSdate = res.getString("actual_sdate");
			String actualEdate = res.getString("actual_edate");
			
			vo = new ReqContVO(contCk,contSdate,contEdate,contEwhy,actualSdate,actualEdate,contCode,code,0);
		}
		
		return vo;
	}
	
	
	// 유승민
	public ArrayList reqContList() throws Exception {

		// 파견계약번호 , 파견요청번호, 계약성사여부, 계약체결일, 계약만기일, 계약만기사유, 업체정산여부

		String sql = "select "
				+ "	req_cont_code, "
				+ "	req_code, "
				+ "	req_cont_ck, "
				+ "	req_cont_sdate, "
				+ "	req_cont_edate, "
				+ "	(select count(a.apply_code) from apply a where a.req_cont_code = r.req_cont_code) count "
				+ "from req_cont r "
				+ "order by 1";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		ArrayList reqContList = new ArrayList();

		while(res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("req_cont_code"));
			temp.add(res.getInt("req_code"));
			temp.add(res.getString("req_cont_ck"));
			temp.add(res.getDate("req_cont_sdate"));
			temp.add(res.getDate("req_cont_edate"));
			temp.add(res.getInt("count"));


			reqContList.add(temp);

		}

		res.close();
		stmt.close();

		return reqContList;
	}

	// 유승민
	// ArrayList 2차원 배열 변환 메소드
	
//	changeArrayList
	
	public String[][] changeArrayList(ArrayList list, String[] col) throws Exception {

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
