package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ApplyVO;
import model.rec.WorkerContVO;

public class WorkerContInfoDAO {

	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;

	public WorkerContInfoDAO() throws Exception {
		// TODO Auto-generated constructor stub
		Class.forName(driver);
		System.out.println("프로필인포DAO 로딩 성공!");
		conn = DriverManager.getConnection(url, user, pw);
		System.out.println("WorkerContInfoDAO DB 연결 성공!");

	}


	// 고용계약 리스트 값 반환
	public ArrayList serchContInfo(String workerCode) throws Exception {

		stmt = conn.createStatement();

		String sql = "select "
				+ "	worker_cont_code, "
				+ "	worker_cont_sdate, "
				+ "	worker_cont_edate, "
				+ "	cont_date, "
				+ "	worker_cont_ck "
				+ "from worker_cont wc, apply ap "
				+ "where wc.apply_code = ap.apply_code and "
				+ "ap.worker_code = " + workerCode;

		ResultSet res = stmt.executeQuery(sql);

		ArrayList contList = new ArrayList();

		while (res.next()) {

			ArrayList temp = new ArrayList();

			temp.add(res.getInt("worker_cont_code"));
			temp.add(res.getString("worker_cont_sdate"));
			temp.add(res.getString("worker_cont_edate"));
			temp.add(res.getString("cont_date"));
			temp.add(res.getString("worker_cont_ck"));

			contList.add(temp);
		}
		
		res.close();
		stmt.close();
		return contList;
	}


	// 고용계약 상세정보 값 반환
	public WorkerContVO workerserchContInfo(String vNum1) throws Exception {

		WorkerContVO vo = new WorkerContVO();
		
		stmt = conn.createStatement();

		String sql = "SELECT " +
	             "a.req_cont_code, " +
	             "wc.worker_cont_code, " +
	             "to_char(wc.worker_cont_sdate,'yyyy-mm-dd') worker_cont_sdate, " +
	             "to_char(wc.worker_cont_edate,'yyyy-mm-dd') worker_cont_edate, " +
	             "to_char(wc.cont_date,'yyyy-mm-dd') cont_date, " +
	             "wc.cont_period, " +
	             "wc.recont_num, " +
	             "wc.acc_name, " +
	             "decode(wc.acc_num,null,'',wc.acc_num) acc_num, " +
	             "wc.acc_bank, " +
	             "wc.worker_cont_ck, " +
	             "to_char(rc.actual_sdate,'yyyy-mm-dd') actual_sdate, " +
	             "to_char(rc.actual_edate,'yyyy-mm-dd') actual_edate " +
	             "FROM worker w, worker_cont wc, apply a, req_cont rc " +
	             "WHERE a.apply_code = wc.apply_code AND " +
	             "a.req_cont_code = rc.req_cont_code AND " +
	             "a.worker_code = w.worker_code AND wc.worker_cont_code = " + vNum1;
		
		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			vo.setReqContCode(res.getInt("req_cont_code"));
			vo.setWorkerContCode(res.getInt("worker_cont_code"));
			vo.setWorkerContSdate(res.getString("worker_cont_sdate"));
			vo.setWorkerContEdate(res.getString("worker_cont_edate"));
			vo.setContPeriod(res.getString("cont_period"));
			vo.setRecontNum(res.getInt("recont_num"));
			vo.setAccName(res.getString("acc_name"));
			vo.setAccNum(res.getString("acc_num"));
			vo.setAccBank(res.getString("acc_bank"));
			vo.setActualSdate(res.getString("actual_sdate"));
			vo.setActualEdate(res.getString("actual_edate"));
			vo.setWorkerContCK(res.getNString("worker_cont_ck"));
		}
		res.close();
		stmt.close();
		return vo;
	}



	public String[][] contList(ArrayList list, String[] col) throws Exception {

		//	System.out.println("고용계약목록 출력");
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


	//인력-고용계약서 계좌정보 수정
	public int workerAccUpdate(WorkerContVO vo) throws Exception {

		String sql = "update worker_cont set ACC_NAME=?,  ACC_BANK = ?, ACC_NUM = ? where worker_cont_code = ?";

		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, vo.getAccName());
		ps.setString(2, vo.getAccBank());
		ps.setInt(3, Integer.parseInt(vo.getAccNum()));
		ps.setInt(4, vo.getWorkerContCode());
		
		int state = ps.executeUpdate();
		
		System.out.println(ps.executeUpdate());
		
		System.out.println("파견인력 계좌정보 수정완료");
		ps.close();
		
		return state;
	}


	public String workerAccNameSearch(String ID) throws Exception {

		String sql = "select worker_name from worker where worker_id =  '" + ID + "'";
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);	

		String code = null;

		if (res.next()) {
			code = res.getString("worker_name");
		}
		return code;
	}	
	
	
	//(인력)고용계약 수락 했을 경우, '계약요청' --->  '계약확인' 으로 상태 변경
	public int acceptContUpdate(WorkerContVO vo) throws Exception {

		String sql = "update worker_cont set worker_cont_ck = '계약수락' where worker_cont_code = ?";

		System.out.println(sql);
		ps = conn.prepareStatement(sql);

		ps.setInt(1, vo.getWorkerContCode());
	
		int state = ps.executeUpdate();
		
		ps.close();
		
		return state;
	}

	
	//(인력)고용계약 수락 했을 경우, '계약요청' --->  '거절'로 상태 변경
	public int rejectContUpdate(WorkerContVO vo) throws Exception {
		
		String sql = "update worker_cont set worker_cont_ck = '계약거절' where worker_cont_code = ?";
		
		System.out.println(sql);
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, vo.getWorkerContCode());
		
		int state = ps.executeUpdate();
		
		ps.close();
		
		return state;
	}
	
	
	

}
