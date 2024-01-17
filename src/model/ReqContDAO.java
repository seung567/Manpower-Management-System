package model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReqContDAO {
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


}
