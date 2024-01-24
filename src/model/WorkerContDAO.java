package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.WorkerContVO;
import model.rec.WorkerVO;

public class WorkerContDAO extends Connect{

	private Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;


	public WorkerContDAO() throws Exception {

		super();
		conn = super.connectValue("WorkerContDAO");

	}
	
	
	public String workerNameSerch(String ApplyCode) throws Exception {

		String sql = "select worker_name from worker w, apply a "
				+ "where a.worker_code = w.worker_code and a.apply_code = " + ApplyCode;

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		String name = null;

		if (res.next()) {
			name = res.getString("worker_name");

		}
		
		res.close();
		stmt.close();
		return name;
	}
	
	public String mgrCodeSerch(String id) throws Exception {

		String sql = "select mgr_name from mgr where mgr_id = '" + id +"'";

		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		String mgrName = null;

		if (res.next()) {
			mgrName = res.getString("mgr_name");

		}
		
		res.close();
		stmt.close();
		return mgrName;

	}
	
	public int workerContInsert(WorkerContVO vo, String mgrID) throws Exception  {
		
		String sql  = "insert into worker_cont("
					+ "worker_cont_code,"
					+ "worker_cont_sdate,"
					+ "worker_cont_edate,"
					+ "recont_num,"
					+ "cont_period,"
					+ "cont_date,"
					+ "mgr_code,"
					+ "apply_code,"
					+ "worker_cont_ck,"
					+ "cont_state) "
				+ "values("
					+ "worker_cont_sq.nextval," // worker_cont_code
					+ "?," // worker_cont_sdate
					+ "?," // worker_cont_edate
					+ "(select "
					+ "count(worker_code) "
					+ "from apply a, worker_cont wc "
					+ "where "
					+ "a.apply_code = wc.apply_code and "
					+ "a.worker_code in (select ap.worker_code from apply ap where ap.apply_code = ?))," // recont_num
					+ "?," // cont_period
					+ "?," // cont_date
					+ "(select mgr_code from mgr where mgr_id = ?)," // mgr_code
					+ "?," // apply_code
					+ "?," // worker_cont_ck
					+ "?)"; // cont_state
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getWorkerContSdate());
		ps.setString(2, vo.getWorkerContEdate());
		ps.setInt(3, vo.getApplyCode());
		ps.setString(4, vo.getContPeriod());
		ps.setString(5, vo.getContDate());
		ps.setString(6, vo.getMgrID());
		ps.setInt(7, vo.getApplyCode());
		ps.setString(8, vo.getWorkerContCK());
		ps.setString(9, vo.getContState());
		
		int state = ps.executeUpdate();
		
		ps.close();
		return state;
	}
}
