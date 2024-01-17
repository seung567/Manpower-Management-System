package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.WorkerVO;

public class CareerDAO {
	
	private Connection conn = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.73:1521:game1";
	//	String url = "jdbc:oracle:thin:@192.168.0.2:1521:bridb";
	String user = "worker";
	String pw = "1111";
	Statement stmt = null;
	PreparedStatement ps = null;
	WorkerVO workervo = null;

	public CareerDAO() throws Exception {

		System.out.println("=====================================");
		System.out.println("ManagerWorkerDAO 실행");

		Class.forName(driver);

		System.out.println("ManagerWorkerDAO 로딩 성공!");

		conn = DriverManager.getConnection(url, user, pw);

		System.out.println("DB 연결 성공!");
		System.out.println("=====================================");

	}
	
	public ArrayList careerList(String workerCode) throws Exception {

		String sql = "select "
				+ "	career_name,"
				+ "	career_sdate,"
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
	
	
	// ArrayList 에서 2차원 Array 로 변환 하는 메소드
	public String[][] careerChangeArray(ArrayList list, String[] col) throws Exception {

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
