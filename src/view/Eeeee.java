package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//import model.ReqContDAO;
//import model.ReqDAO;
import model.rec.ReqContVO;
import model.rec.ReqVO;

public class Eeeee extends JFrame {

	private JPanel contentPane;
	private JTable reqListTB;
	private JTable contListTB;   
	private JTextField tf_sex;
	private JTextField tf_totalCost;
	private JTextField tf_quali;
	private JTextField tf_local;
	private JTextField tf_ageRange;
	private JTextField tf_langLevel;
	private JTextField tf_cityName;
	private JTextField tf_reqLangLevel;
	private JTextField tf_expecEdate;
	private JTextField tf_expecSdate;
	private JTextField tf_custCode;
	private JTextField tf_sectorName;
	private JTextField tf_workerNum;
	private JTextField tf_reqContEwhy;
	private JTextField tf_actualEdate;
	private JTextField tf_actualSdate;
	private JTextField tf_reqContEdate;
	private JTextField tf_reqContSdate;
	private JTextField tf_reqContCode;
	private JTextField tf_reqContCk;
	private JTextField tf_reqCode;
	private JTextField tf_workerCode;
	private JTextField tf_localLangLevel;

	//   ReqDAO dao = null; 
	//   ReqContDAO dao1 = null; 

	String vNum;

	int total;

	DefaultTableModel model1;
	DefaultTableModel model;
	DefaultTableCellRenderer center;

	String[][] contents= null;

	/**
	 * Launch the application.
	 */
	public static void CustManagerAction(String ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eeeee frame = new Eeeee(ID);
					frame.setVisible(true);
					frame.setResizable(false);
					// 팝업창 x 버튼 누를시 창만 꺼지게 해주는 명령어
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							// super.windowClosing(e);

							int result = JOptionPane.showConfirmDialog(frame, "창을 닫으시겠습니까?", "확인",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								frame.dispose();
							}
						}
					});
					// 여기까지         
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//   public Eeeee() {
	//      this.CustManagerAction("test");
	//   }

	//   public Eeeee(int num) {
	//
	//   }


	public Eeeee(String ID) {

		try {
			//         dao = new ReqDAO();
			System.out.println("파견요청 DB연결 성공");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "파견요청 DB연결 실패 : " + e.getMessage());
		}


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 820);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane managerMainTab = new JTabbedPane(JTabbedPane.TOP);
		managerMainTab.setBorder(null);
		//      managerMainTab.add("1",JPanel(new WorkerView().main(null);));
		managerMainTab.setBounds(12, 64, 1410, 707);
		contentPane.add(managerMainTab);

		JPanel workerManagePanel = new JPanel();
		workerManagePanel.setForeground(new Color(16, 24, 32));
		workerManagePanel.setLayout(null);
		workerManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerManagePanel.setBackground(new Color(16, 24, 32));
		managerMainTab.addTab("파견요청관리", null, workerManagePanel, null);

		JPanel workerManagePanel_2 = new JPanel();
		workerManagePanel_2.setBackground(new Color(16, 24, 32));
		workerManagePanel_2.setLayout(null);
		workerManagePanel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("계약관리", null, workerManagePanel_2, null);
		managerMainTab.setBackgroundAt(1, new Color(220, 226, 240));

		JLabel workerListLabel_3 = new JLabel("파견계약목록");
		workerListLabel_3.setBackground(new Color(255, 255, 255));
		workerListLabel_3.setForeground(new Color(242, 170, 76));
		workerListLabel_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerListLabel_3.setBounds(12, 10, 113, 33);
		workerManagePanel_2.add(workerListLabel_3);

		JLabel workerListLabel_1_2 = new JLabel("파견계약상세정보");
		workerListLabel_1_2.setForeground(new Color(242, 170, 76));
		workerListLabel_1_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerListLabel_1_2.setBounds(856, 10, 127, 33);
		workerManagePanel_2.add(workerListLabel_1_2);

		JButton reqRegistBtn = new JButton("계약서등록");
		reqRegistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//            ReqContView reqContView = new ReqContView();
				//            ReqContView.main(null);
			}
		});
		reqRegistBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqRegistBtn.setForeground(new Color(255, 255, 255));
		reqRegistBtn.setBackground(new Color(16, 24, 32));
		reqRegistBtn.setBounds(796, 483, 142, 46);
		workerManagePanel_2.add(reqRegistBtn);

		JButton reqOktBtn = new JButton("계약서승인");
		reqOktBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqOktBtn.setForeground(new Color(255, 255, 255));
		reqOktBtn.setBackground(new Color(16, 24, 32));
		reqOktBtn.setBounds(950, 483, 142, 46);
		workerManagePanel_2.add(reqOktBtn);

		JButton reqNoBtn = new JButton("계약서반려");
		reqNoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqNoBtn.setForeground(new Color(255, 255, 255));
		reqNoBtn.setBackground(new Color(16, 24, 32));
		reqNoBtn.setBounds(1100, 483, 142, 46);
		workerManagePanel_2.add(reqNoBtn);

		JButton reqNoReBtn = new JButton("계약서재요청");
		reqNoReBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqNoReBtn.setForeground(new Color(255, 255, 255));
		reqNoReBtn.setBackground(new Color(16, 24, 32));
		reqNoReBtn.setBounds(1251, 483, 142, 46);
		workerManagePanel_2.add(reqNoReBtn);

		JPanel contListPanel = new JPanel();
		contListPanel.setForeground(new Color(242, 170, 76));
		contListPanel.setBackground(new Color(16, 24, 32));
		contListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		contListPanel.setBounds(12, 46, 750, 621);
		workerManagePanel_2.add(contListPanel);
		contListPanel.setLayout(null);

		String[] header1 = {"계약번호", "계약체결일", "계약만기일", "실근무시작일", "실근무종료일", "계약만기사유", "정산여부"};


		model1 = new DefaultTableModel(contents, header1);
		contListPanel.setLayout(null);

		contListTB = new JTable(model1);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		contListTB.getColumn("계약번호").setPreferredWidth(3);
		contListTB.getColumn("계약체결일").setPreferredWidth(50);
		contListTB.getColumn("계약만기일").setPreferredWidth(6);
		contListTB.getColumn("실근무시작일").setPreferredWidth(50);
		contListTB.getColumn("실근무종료일").setPreferredWidth(3);
		contListTB.getColumn("계약만기사유").setPreferredWidth(3);
		contListTB.getColumn("정산여부").setPreferredWidth(3);

		contListTB.getColumn("계약번호").setCellRenderer(center);
		contListTB.getColumn("계약체결일").setCellRenderer(center);
		contListTB.getColumn("계약만기일").setCellRenderer(center);
		contListTB.getColumn("실근무시작일").setCellRenderer(center);
		contListTB.getColumn("실근무종료일").setCellRenderer(center);
		contListTB.getColumn("계약만기사유").setCellRenderer(center);
		contListTB.getColumn("정산여부").setCellRenderer(center);   

		contListTB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = contListTB.getSelectedRow();
				vNum = (String) contListTB.getValueAt(row, col);
				ReqContVO vo2 = new ReqContVO();

				try {

//					ReqContVO vo3 = dao1.reqContInfoSearch(vNum);

					// vo의 각각의 값을 오른쪽 화면에 출력
//					tf_reqContCode.setText(String.valueOf(vo3.getReqContCode()));
//					tf_reqCode.setText(String.valueOf(vo3.getReqCode()));
//					tf_workerCode.setText(String.valueOf(vo3.getWorkerCode()));
//					tf_reqContCk.setText(vo3.getReqContCk());   
//					tf_reqContSdate.setText(vo3.getReqContSdate().substring(0, 10));   
//					tf_reqContEdate.setText(vo3.getReqContEdate().substring(0, 10));
//					tf_actualSdate.setText(vo3.getActualSdate().substring(0, 10));   
//					tf_actualEdate.setText(vo3.getActualEdate().substring(0, 10));
//					tf_reqContEwhy.setText(vo3.getReqContEwhy());      

				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "요청정보 검색 실패 : " + e1.getMessage());
				}
			}
		});

		contListTB.setBounds(1, 27, 536, 6);
		contListPanel.add(contListTB);

		JScrollPane scrollPane_1 = new JScrollPane(contListTB);
		scrollPane_1.setBounds(12, 10, 728, 601);
		contListPanel.add(scrollPane_1);

		JButton showBtn_1 = new JButton("조회");
		showBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				contListTB(header1);
			}
		});
		showBtn_1.setForeground(new Color(255, 255, 255));
		showBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		showBtn_1.setBackground(new Color(16, 24, 32));
		showBtn_1.setBounds(672, 9, 90, 34);
		workerManagePanel_2.add(showBtn_1);

		JPanel reqInfoPanel_1 = new JPanel();
		reqInfoPanel_1.setLayout(null);
		reqInfoPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel_1.setBackground(new Color(16, 24, 32));
		reqInfoPanel_1.setBounds(856, 46, 537, 427);
		workerManagePanel_2.add(reqInfoPanel_1);

		JLabel reqContCode = new JLabel("파견계약번호");
		reqContCode.setForeground(new Color(242, 170, 76));
		reqContCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCode.setBounds(22, 25, 97, 33);
		reqInfoPanel_1.add(reqContCode);

		JLabel actualEdate = new JLabel("실근무종료일");
		actualEdate.setForeground(new Color(242, 170, 76));
		actualEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualEdate.setBounds(275, 157, 97, 21);
		reqInfoPanel_1.add(actualEdate);

		JLabel reqContSdate = new JLabel("계약체결일");
		reqContSdate.setForeground(new Color(242, 170, 76));
		reqContSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContSdate.setBounds(22, 114, 105, 21);
		reqInfoPanel_1.add(reqContSdate);

		JLabel actualSdate = new JLabel("실근무시작일");
		actualSdate.setForeground(new Color(242, 170, 76));
		actualSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualSdate.setBounds(22, 157, 105, 21);
		reqInfoPanel_1.add(actualSdate);

		JPanel workerManagePanel_1_2_1_1 = new JPanel();
		workerManagePanel_1_2_1_1.setLayout(null);
		workerManagePanel_1_2_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerManagePanel_1_2_1_1.setBounds(376, 95, 1, 1);
		reqInfoPanel_1.add(workerManagePanel_1_2_1_1);

		JLabel workerListLabel_2_2_1_1 = new JLabel("파견계약목록");
		workerListLabel_2_2_1_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_2_2_1_1.setBounds(12, 10, 113, 33);
		workerManagePanel_1_2_1_1.add(workerListLabel_2_2_1_1);

		JPanel workerListPanel_2_2_1_1 = new JPanel();
		workerListPanel_2_2_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_2_2_1_1.setBounds(12, 46, 560, 427);
		workerManagePanel_1_2_1_1.add(workerListPanel_2_2_1_1);

		JLabel workerListLabel_1_1_2_1_1 = new JLabel("파견계약상세정보");
		workerListLabel_1_1_2_1_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_1_1_2_1_1.setBounds(595, 10, 127, 33);
		workerManagePanel_1_2_1_1.add(workerListLabel_1_1_2_1_1);

		JPanel workerListPanel_1_1_2_1 = new JPanel();
		workerListPanel_1_1_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_1_1_2_1.setBounds(595, 46, 537, 427);
		workerManagePanel_1_2_1_1.add(workerListPanel_1_1_2_1);

		JPanel workerManagePanel_1_1_2_1 = new JPanel();
		workerManagePanel_1_1_2_1.setLayout(null);
		workerManagePanel_1_1_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_1_1_2_1.add(workerManagePanel_1_1_2_1);

		JLabel workerListLabel_2_1_2_1 = new JLabel("파견계약목록");
		workerListLabel_2_1_2_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_2_1_2_1.setBounds(12, 10, 113, 33);
		workerManagePanel_1_1_2_1.add(workerListLabel_2_1_2_1);

		JPanel workerListPanel_2_1_2_1 = new JPanel();
		workerListPanel_2_1_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_2_1_2_1.setBounds(12, 46, 560, 427);
		workerManagePanel_1_1_2_1.add(workerListPanel_2_1_2_1);

		JLabel workerListLabel_1_1_1_2_1 = new JLabel("파견계약상세정보");
		workerListLabel_1_1_1_2_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_1_1_1_2_1.setBounds(595, 10, 127, 33);
		workerManagePanel_1_1_2_1.add(workerListLabel_1_1_1_2_1);

		JLabel reqContEdate = new JLabel("계약만기일");
		reqContEdate.setForeground(new Color(242, 170, 76));
		reqContEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEdate.setBounds(275, 114, 105, 21);
		reqInfoPanel_1.add(reqContEdate);

		tf_reqContEwhy = new JTextField();
		tf_reqContEwhy.setColumns(10);
		tf_reqContEwhy.setBounds(139, 196, 367, 208);
		reqInfoPanel_1.add(tf_reqContEwhy);

		JLabel reqContEwhy = new JLabel("계약만기사유");
		reqContEwhy.setForeground(new Color(242, 170, 76));
		reqContEwhy.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEwhy.setBounds(22, 196, 105, 21);
		reqInfoPanel_1.add(reqContEwhy);

		tf_actualEdate = new JTextField();
		tf_actualEdate.setColumns(10);
		tf_actualEdate.setBounds(384, 152, 122, 33);
		reqInfoPanel_1.add(tf_actualEdate);

		tf_actualSdate = new JTextField();
		tf_actualSdate.setColumns(10);
		tf_actualSdate.setBounds(139, 152, 122, 33);
		reqInfoPanel_1.add(tf_actualSdate);

		tf_reqContEdate = new JTextField();
		tf_reqContEdate.setColumns(10);
		tf_reqContEdate.setBounds(384, 109, 122, 33);
		reqInfoPanel_1.add(tf_reqContEdate);

		JLabel reqContCk = new JLabel("계약성사여부");
		reqContCk.setForeground(new Color(242, 170, 76));
		reqContCk.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCk.setBounds(275, 63, 97, 36);
		reqInfoPanel_1.add(reqContCk);

		tf_reqContSdate = new JTextField();
		tf_reqContSdate.setColumns(10);
		tf_reqContSdate.setBounds(139, 109, 122, 33);
		reqInfoPanel_1.add(tf_reqContSdate);

		tf_reqContCode = new JTextField();
		tf_reqContCode.setForeground(Color.BLACK);
		tf_reqContCode.setColumns(10);
		tf_reqContCode.setBackground(Color.WHITE);
		tf_reqContCode.setBounds(138, 25, 122, 33);
		reqInfoPanel_1.add(tf_reqContCode);

		tf_reqContCk = new JTextField();
		tf_reqContCk.setColumns(10);
		tf_reqContCk.setBounds(384, 66, 123, 33);
		reqInfoPanel_1.add(tf_reqContCk);

		JLabel reqCode = new JLabel("파견요청번호");
		reqCode.setForeground(new Color(242, 170, 76));
		reqCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCode.setBounds(275, 25, 97, 33);
		reqInfoPanel_1.add(reqCode);

		tf_reqCode = new JTextField();
		tf_reqCode.setForeground(Color.BLACK);
		tf_reqCode.setColumns(10);
		tf_reqCode.setBackground(Color.WHITE);
		tf_reqCode.setBounds(384, 25, 122, 33);
		reqInfoPanel_1.add(tf_reqCode);

		JLabel workerCode = new JLabel("파견인력번호");
		workerCode.setForeground(new Color(242, 170, 76));
		workerCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCode.setBounds(22, 63, 97, 36);
		reqInfoPanel_1.add(workerCode);

		tf_workerCode = new JTextField();
		tf_workerCode.setColumns(10);
		tf_workerCode.setBounds(138, 66, 123, 33);
		reqInfoPanel_1.add(tf_workerCode);

		JLabel workerListLabel = new JLabel("파견요청목록");
		workerListLabel.setForeground(new Color(242, 170, 76));
		workerListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerListLabel.setBounds(12, 10, 113, 33);
		workerManagePanel.add(workerListLabel);

		JPanel reqListPanel = new JPanel();
		reqListPanel.setForeground(new Color(242, 170, 76));
		reqListPanel.setBackground(new Color(16, 24, 32));
		reqListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqListPanel.setBounds(12, 46, 750, 621);
		workerManagePanel.add(reqListPanel);
		reqListPanel.setLayout(null);


		String[] header = {"파견요청번호", "업종명", "요청인원수", "도시명", "필수어학수준", "총파견비용", "상태"};

		model = new DefaultTableModel(contents, header);
		reqListPanel.setLayout(null);

		reqListTB = new JTable(model);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		reqListTB.getColumn("파견요청번호").setPreferredWidth(3);
		reqListTB.getColumn("업종명").setPreferredWidth(50);
		reqListTB.getColumn("요청인원수").setPreferredWidth(6);
		reqListTB.getColumn("도시명").setPreferredWidth(50);
		reqListTB.getColumn("필수어학수준").setPreferredWidth(3);
		reqListTB.getColumn("총파견비용").setPreferredWidth(3);
		reqListTB.getColumn("상태").setPreferredWidth(3);

		reqListTB.getColumn("파견요청번호").setCellRenderer(center);
		reqListTB.getColumn("업종명").setCellRenderer(center);
		reqListTB.getColumn("요청인원수").setCellRenderer(center);
		reqListTB.getColumn("도시명").setCellRenderer(center);
		reqListTB.getColumn("필수어학수준").setCellRenderer(center);
		reqListTB.getColumn("총파견비용").setCellRenderer(center);
		reqListTB.getColumn("상태").setCellRenderer(center);

//		reqListTB.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				int col = 0;
//				int row = reqListTB.getSelectedRow();
//				vNum = (String) reqListTB.getValueAt(row, col);
				//            ReqVO vo = new ReqVO();
				//            
				//            try {
				//               
				////               ReqVO vo1 = dao.reqInfoSearch(vNum);
				//
				//               // vo의 각각의 값을 오른쪽 화면에 출력
				//               tf_custCode.setText(String.valueOf(vo1.getCustCode()));
				//               tf_sectorName.setText(vo1.getSectorName());    // 업종명 (코드를 명으로 변환)
				//               tf_expecSdate.setText(vo1.getExpecSdate().substring(0, 10));   
				//               tf_expecEdate.setText(vo1.getExpecEdate().substring(0, 10));
				//               tf_reqLangLevel.setText(vo1.getReqLangLevel());      
				//               tf_localLangLevel.setText(vo1.getLocalLangLevel());      
				//               tf_cityName.setText(vo1.getCityName()); // 도시명 (코드를 명으로 변환)
				//               tf_local.setText(vo1.getLocal());      
				//               tf_langLevel.setText(vo1.getLangLevel()); // 필요언어
				//               tf_workerNum.setText(String.valueOf(vo1.getWorkerNum()));   
				//               tf_sex.setText(vo1.getSex());      
				//               tf_ageRange.setText(vo1.getAgeRange());      
				//               tf_quali.setText(vo1.getQuali());               
				//               NumberFormat numberFormat = NumberFormat.getInstance();
				//               String totalCost = numberFormat.format(vo1.getTotalCost());         
				//               tf_totalCost.setText(totalCost);   
				//
				//            }catch (Exception e1) {
				//               JOptionPane.showMessageDialog(null, "요청정보 검색 실패 : " + e1.getMessage());
				//            }
				//         }
				//      });

				reqListTB.setBounds(1, 27, 536, 6);
				reqListPanel.add(reqListTB);

				JScrollPane scrollPane = new JScrollPane(reqListTB);
				scrollPane.setBounds(10, 10, 728, 601);
				reqListPanel.add(scrollPane);

				JLabel workerListLabel_1 = new JLabel("파견요청정보");
				workerListLabel_1.setForeground(new Color(242, 170, 76));
				workerListLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
				workerListLabel_1.setBounds(838, 10, 127, 33);
				workerManagePanel.add(workerListLabel_1);

				JButton reqInfoBtn = new JButton("요청서등록");
				reqInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				reqInfoBtn.setForeground(new Color(255, 255, 255));
				reqInfoBtn.setBackground(new Color(16, 24, 32));
				reqInfoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						ReqView reqView = new ReqView();
//						ReqView.reqMain();
					}
				});
				reqInfoBtn.setBounds(838, 483, 131, 46);
				workerManagePanel.add(reqInfoBtn);

				JButton visaInsertBtn = new JButton("파견재요청");
//				visaInsertBtn.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//
//						int reqCode = Integer.parseInt(vNum);      
//						String expecSdate = tf_expecSdate.getText(); 
//						String expecEdate = tf_expecEdate.getText(); 
//						String reqLangLevel = tf_reqLangLevel.getText();  
//						String localLangLevel = tf_localLangLevel.getText(); 
//						String cityName = tf_cityName.getText(); 
//						String local = tf_local.getText(); 
//						String langLevel = tf_langLevel.getText(); 
//						int workerNum = Integer.parseInt(tf_workerNum.getText()); 
//						String sex = tf_sex.getText(); 
//						String ageRange = tf_ageRange.getText(); 
//						String quali = tf_quali.getText(); 
//						int totalCost = Integer.parseInt(tf_totalCost.getText().replace(",", ""));
//						System.out.println(totalCost);             
//
//						//            ReqVO vo = new ReqVO(reqCode, expecSdate, expecEdate, reqLangLevel, localLangLevel, 
//						//                  cityName, local, langLevel, workerNum, sex, ageRange, quali, totalCost);
//
//						try {
//							//               ReqDAO dao = new ReqDAO();
//							//               
//							//               int state = dao.reDemand(vo);
//							if (state == 0) {
//								System.out.println("변경 성공!");
//							}else {
//								System.out.println("변경 실패!");
//							}
//							System.out.println("파견 재요청 완료!");
//						}catch (Exception e1) {
//							JOptionPane.showConfirmDialog(null,  "파견 재요청 실패 : " + e1.getMessage());
//						}   
//
//					}
//				});
				visaInsertBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				visaInsertBtn.setBackground(new Color(16, 24, 32));
				visaInsertBtn.setForeground(new Color(255, 255, 255));
				visaInsertBtn.setBounds(1146, 483, 113, 46);
				workerManagePanel.add(visaInsertBtn);

				JButton visaInsertBtn_2 = new JButton("기타요구사항등록");
				visaInsertBtn_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				visaInsertBtn_2.setForeground(new Color(255, 255, 255));
				visaInsertBtn_2.setBackground(new Color(16, 24, 32));
				visaInsertBtn_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						EtcDemandView etcDemandView = new EtcDemandView(0);
//						EtcDemandView.EtcDemand(vNum);
					}
				});
				visaInsertBtn_2.setBounds(981, 483, 153, 46);
				workerManagePanel.add(visaInsertBtn_2);

				JPanel reqInfoPanel = new JPanel();
				reqInfoPanel.setBackground(new Color(16, 24, 32));
				reqInfoPanel.setLayout(null);
				reqInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
				reqInfoPanel.setBounds(838, 46, 537, 427);
				workerManagePanel.add(reqInfoPanel);

				JLabel custCode = new JLabel("업체번호");
				custCode.setForeground(new Color(242, 170, 76));
				custCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				custCode.setBounds(27, 22, 75, 33);
				reqInfoPanel.add(custCode);

				JLabel city = new JLabel("도시명");
				city.setForeground(new Color(242, 170, 76));
				city.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				city.setBounds(280, 154, 75, 21);
				reqInfoPanel.add(city);

				JLabel expecSdate = new JLabel("예상근무시작일");
				expecSdate.setForeground(new Color(242, 170, 76));
				expecSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				expecSdate.setBounds(27, 111, 105, 21);
				reqInfoPanel.add(expecSdate);

				JLabel reqLangLevel = new JLabel("필수어학수준");
				reqLangLevel.setForeground(new Color(242, 170, 76));
				reqLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				reqLangLevel.setBounds(27, 154, 105, 21);
				reqInfoPanel.add(reqLangLevel);

				JLabel ageRange = new JLabel("연령대");
				ageRange.setForeground(new Color(242, 170, 76));
				ageRange.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				ageRange.setBounds(235, 286, 67, 21);
				reqInfoPanel.add(ageRange);

				JLabel local = new JLabel("상세근무장소");
				local.setForeground(new Color(242, 170, 76));
				local.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				local.setBounds(27, 197, 105, 21);
				reqInfoPanel.add(local);

				JLabel sex = new JLabel("성별");
				sex.setForeground(new Color(242, 170, 76));
				sex.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				sex.setBounds(27, 286, 43, 21);
				reqInfoPanel.add(sex);

				JPanel workerManagePanel_1_2_1 = new JPanel();
				workerManagePanel_1_2_1.setLayout(null);
				workerManagePanel_1_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerManagePanel_1_2_1.setBounds(376, 95, 1, 1);
				reqInfoPanel.add(workerManagePanel_1_2_1);

				JLabel workerListLabel_2_2_1 = new JLabel("파견계약목록");
				workerListLabel_2_2_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_2_2_1.setBounds(12, 10, 113, 33);
				workerManagePanel_1_2_1.add(workerListLabel_2_2_1);

				JPanel workerListPanel_2_2_1 = new JPanel();
				workerListPanel_2_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_2_2_1.setBounds(12, 46, 560, 427);
				workerManagePanel_1_2_1.add(workerListPanel_2_2_1);

				JLabel workerListLabel_1_1_2_1 = new JLabel("파견계약상세정보");
				workerListLabel_1_1_2_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_1_1_2_1.setBounds(595, 10, 127, 33);
				workerManagePanel_1_2_1.add(workerListLabel_1_1_2_1);

				JPanel workerListPanel_1_1_2 = new JPanel();
				workerListPanel_1_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_1_1_2.setBounds(595, 46, 537, 427);
				workerManagePanel_1_2_1.add(workerListPanel_1_1_2);

				JPanel workerManagePanel_1_1_2 = new JPanel();
				workerManagePanel_1_1_2.setLayout(null);
				workerManagePanel_1_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_1_1_2.add(workerManagePanel_1_1_2);

				JLabel workerListLabel_2_1_2 = new JLabel("파견계약목록");
				workerListLabel_2_1_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_2_1_2.setBounds(12, 10, 113, 33);
				workerManagePanel_1_1_2.add(workerListLabel_2_1_2);

				JPanel workerListPanel_2_1_2 = new JPanel();
				workerListPanel_2_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_2_1_2.setBounds(12, 46, 560, 427);
				workerManagePanel_1_1_2.add(workerListPanel_2_1_2);

				JLabel workerListLabel_1_1_1_2 = new JLabel("파견계약상세정보");
				workerListLabel_1_1_1_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_1_1_1_2.setBounds(595, 10, 127, 33);
				workerManagePanel_1_1_2.add(workerListLabel_1_1_1_2);

				JLabel expecEdate = new JLabel("예상근무종료일");
				expecEdate.setForeground(new Color(242, 170, 76));
				expecEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				expecEdate.setBounds(280, 111, 105, 21);
				reqInfoPanel.add(expecEdate);

				tf_sex = new JTextField();
				tf_sex.setHorizontalAlignment(SwingConstants.CENTER);
				tf_sex.setColumns(10);
				tf_sex.setBounds(144, 281, 67, 33);
				reqInfoPanel.add(tf_sex);

				tf_totalCost = new JTextField();
				tf_totalCost.setHorizontalAlignment(SwingConstants.CENTER);
				tf_totalCost.setColumns(10);
				tf_totalCost.setBounds(144, 367, 367, 33);
				reqInfoPanel.add(tf_totalCost);

				tf_quali = new JTextField();
				tf_quali.setHorizontalAlignment(SwingConstants.CENTER);
				tf_quali.setColumns(10);
				tf_quali.setBounds(143, 324, 368, 33);
				reqInfoPanel.add(tf_quali);

				tf_local = new JTextField();
				tf_local.setHorizontalAlignment(SwingConstants.CENTER);
				tf_local.setColumns(10);
				tf_local.setBounds(144, 192, 368, 33);
				reqInfoPanel.add(tf_local);

				JLabel quali = new JLabel("자격요건");
				quali.setForeground(new Color(242, 170, 76));
				quali.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				quali.setBounds(27, 329, 105, 21);
				reqInfoPanel.add(quali);

				JLabel totalCost = new JLabel("총파견비용");
				totalCost.setForeground(new Color(242, 170, 76));
				totalCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				totalCost.setBounds(27, 372, 81, 21);
				reqInfoPanel.add(totalCost);

				tf_ageRange = new JTextField();
				tf_ageRange.setHorizontalAlignment(SwingConstants.CENTER);
				tf_ageRange.setColumns(10);
				tf_ageRange.setBounds(294, 281, 61, 33);
				reqInfoPanel.add(tf_ageRange);

				tf_langLevel = new JTextField();
				tf_langLevel.setHorizontalAlignment(SwingConstants.CENTER);
				tf_langLevel.setColumns(10);
				tf_langLevel.setBounds(144, 238, 122, 33);
				reqInfoPanel.add(tf_langLevel);

				tf_cityName = new JTextField();
				tf_cityName.setHorizontalAlignment(SwingConstants.CENTER);
				tf_cityName.setColumns(10);
				tf_cityName.setBounds(389, 149, 122, 33);
				reqInfoPanel.add(tf_cityName);

				tf_reqLangLevel = new JTextField();
				tf_reqLangLevel.setHorizontalAlignment(SwingConstants.CENTER);
				tf_reqLangLevel.setColumns(10);
				tf_reqLangLevel.setBounds(144, 149, 122, 33);
				reqInfoPanel.add(tf_reqLangLevel);

				tf_expecEdate = new JTextField();
				tf_expecEdate.setHorizontalAlignment(SwingConstants.CENTER);
				tf_expecEdate.setColumns(10);
				tf_expecEdate.setBounds(389, 106, 122, 33);
				reqInfoPanel.add(tf_expecEdate);

				JLabel sectorName = new JLabel("업종명");
				sectorName.setForeground(new Color(242, 170, 76));
				sectorName.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				sectorName.setBounds(27, 65, 75, 36);
				reqInfoPanel.add(sectorName);

				JLabel workerNum = new JLabel("요청인원수");
				workerNum.setForeground(new Color(242, 170, 76));
				workerNum.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				workerNum.setBounds(367, 286, 75, 21);
				reqInfoPanel.add(workerNum);

				tf_expecSdate = new JTextField();
				tf_expecSdate.setHorizontalAlignment(SwingConstants.CENTER);
				tf_expecSdate.setColumns(10);
				tf_expecSdate.setBounds(144, 106, 122, 33);
				reqInfoPanel.add(tf_expecSdate);

				tf_custCode = new JTextField();
				tf_custCode.setHorizontalAlignment(SwingConstants.CENTER);
				tf_custCode.setEditable(false);
				tf_custCode.setForeground(Color.BLACK);
				tf_custCode.setBackground(Color.WHITE);
				tf_custCode.setColumns(10);
				tf_custCode.setBounds(143, 22, 368, 33);
				reqInfoPanel.add(tf_custCode);

				tf_sectorName = new JTextField();
				tf_sectorName.setHorizontalAlignment(SwingConstants.CENTER);
				tf_sectorName.setEditable(false);
				tf_sectorName.setColumns(10);
				tf_sectorName.setBounds(144, 63, 368, 33);
				reqInfoPanel.add(tf_sectorName);

				JLabel lang = new JLabel("필요언어");
				lang.setForeground(new Color(242, 170, 76));
				lang.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				lang.setBounds(27, 243, 105, 21);
				reqInfoPanel.add(lang);

				tf_workerNum = new JTextField();
				tf_workerNum.setHorizontalAlignment(SwingConstants.CENTER);
				tf_workerNum.setColumns(10);
				tf_workerNum.setBounds(450, 281, 61, 33);
				reqInfoPanel.add(tf_workerNum);

				tf_localLangLevel = new JTextField();
				tf_localLangLevel.setHorizontalAlignment(SwingConstants.CENTER);
				tf_localLangLevel.setColumns(10);
				tf_localLangLevel.setBounds(389, 238, 122, 33);
				reqInfoPanel.add(tf_localLangLevel);

				JLabel localLangLevel = new JLabel("현지어학수준");
				localLangLevel.setForeground(new Color(242, 170, 76));
				localLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				localLangLevel.setBounds(280, 235, 105, 36);
				reqInfoPanel.add(localLangLevel);

				JButton showBtn = new JButton("조회");
				showBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						reqListTB(header);
					}
				});
				showBtn.setForeground(new Color(255, 255, 255));
				showBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				showBtn.setBackground(new Color(16, 24, 32));
				showBtn.setBounds(672, 9, 90, 34);
				workerManagePanel.add(showBtn);

				JButton deleteBtn = new JButton("요청삭제");
//				deleteBtn.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//
//						if(reqListTB.getSelectedRow() == -1) {   // table.getSelectedRow() -> 선택한 행이 없으면 -1 반환
//							JOptionPane.showMessageDialog(null, "테이블에서 삭제할 행을 선택하세요.");
//						}    
//
//						int reqCode = Integer.parseInt(vNum);   
//
////						ReqVO vo = new ReqVO(reqCode);
//
//						try {
////							ReqDAO dao = new ReqDAO();
//
////							int state = dao.reqDelete(vo);
//							reqListTB(header);
//							if (state > 0) {
//								System.out.println("작업 성공!");
//							} else {
//								System.out.println("작업 실패!");
//							}
//							System.out.println("파견요청 삭제 성공!");
//						}catch (Exception e1) {
//							//               e1.printStackTrace();
//							JOptionPane.showConfirmDialog(null,  "파견요청 삭제 실패 : " + e1.getMessage());
//						}      
//
//					}
//				});

				deleteBtn.setForeground(Color.WHITE);
				deleteBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				deleteBtn.setBackground(new Color(16, 24, 32));
				deleteBtn.setBounds(1271, 483, 104, 46);
				workerManagePanel.add(deleteBtn);

				JPanel workerManagePanel_2_1 = new JPanel();
				workerManagePanel_2_1.setBackground(new Color(16, 24, 32));
				workerManagePanel_2_1.setLayout(null);
				workerManagePanel_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				managerMainTab.addTab("정산관리", null, workerManagePanel_2_1, null);

				JLabel workerListLabel_3_1 = new JLabel("월별정산목록");
				workerListLabel_3_1.setForeground(new Color(242, 170, 76));
				workerListLabel_3_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
				workerListLabel_3_1.setBounds(12, 10, 113, 33);
				workerManagePanel_2_1.add(workerListLabel_3_1);

				JPanel workerListPanel_3_1 = new JPanel();
				workerListPanel_3_1.setBackground(new Color(16, 24, 32));
				workerListPanel_3_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
				workerListPanel_3_1.setBounds(12, 46, 560, 483);
				workerManagePanel_2_1.add(workerListPanel_3_1);
				workerListPanel_3_1.setLayout(null);

				JLabel workerListLabel_1_2_1 = new JLabel("월별정산내역");
				workerListLabel_1_2_1.setForeground(new Color(242, 170, 76));
				workerListLabel_1_2_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
				workerListLabel_1_2_1.setBounds(595, 10, 127, 33);
				workerManagePanel_2_1.add(workerListLabel_1_2_1);

				JButton reqInfoBtn_3_1 = new JButton("정산승인");
				reqInfoBtn_3_1.setForeground(new Color(255, 255, 255));
				reqInfoBtn_3_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				reqInfoBtn_3_1.setBackground(new Color(16, 24, 32));
				reqInfoBtn_3_1.setBounds(991, 483, 142, 46);
				workerManagePanel_2_1.add(reqInfoBtn_3_1);

				JButton visaInsertBtn_2_2 = new JButton("계약내용보기");
				visaInsertBtn_2_2.setForeground(new Color(255, 255, 255));
				visaInsertBtn_2_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
				visaInsertBtn_2_2.setBackground(new Color(16, 24, 32));
				visaInsertBtn_2_2.setBounds(837, 483, 142, 46);
				workerManagePanel_2_1.add(visaInsertBtn_2_2);

				JPanel workerListPanel_1_4_2 = new JPanel();
				workerListPanel_1_4_2.setBackground(new Color(16, 24, 32));
				workerListPanel_1_4_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
				workerListPanel_1_4_2.setBounds(596, 46, 537, 427);
				workerManagePanel_2_1.add(workerListPanel_1_4_2);
				workerListPanel_1_4_2.setLayout(null);

				JPanel workerManagePanel_1_2_1_1_1 = new JPanel();
				workerManagePanel_1_2_1_1_1.setBounds(376, 95, 1, 1);
				workerManagePanel_1_2_1_1_1.setLayout(null);
				workerManagePanel_1_2_1_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_1_4_2.add(workerManagePanel_1_2_1_1_1);

				JLabel workerListLabel_2_2_1_1_1 = new JLabel("파견계약목록");
				workerListLabel_2_2_1_1_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_2_2_1_1_1.setBounds(12, 10, 113, 33);
				workerManagePanel_1_2_1_1_1.add(workerListLabel_2_2_1_1_1);

				JPanel workerListPanel_2_2_1_1_1 = new JPanel();
				workerListPanel_2_2_1_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_2_2_1_1_1.setBounds(12, 46, 560, 427);
				workerManagePanel_1_2_1_1_1.add(workerListPanel_2_2_1_1_1);

				JLabel workerListLabel_1_1_2_1_1_1 = new JLabel("파견계약상세정보");
				workerListLabel_1_1_2_1_1_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_1_1_2_1_1_1.setBounds(595, 10, 127, 33);
				workerManagePanel_1_2_1_1_1.add(workerListLabel_1_1_2_1_1_1);

				JPanel workerListPanel_1_1_2_2 = new JPanel();
				workerListPanel_1_1_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_1_1_2_2.setBounds(595, 46, 537, 427);
				workerManagePanel_1_2_1_1_1.add(workerListPanel_1_1_2_2);

				JPanel workerManagePanel_1_1_2_2 = new JPanel();
				workerManagePanel_1_1_2_2.setLayout(null);
				workerManagePanel_1_1_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_1_1_2_2.add(workerManagePanel_1_1_2_2);

				JLabel workerListLabel_2_1_2_2 = new JLabel("파견계약목록");
				workerListLabel_2_1_2_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_2_1_2_2.setBounds(12, 10, 113, 33);
				workerManagePanel_1_1_2_2.add(workerListLabel_2_1_2_2);

				JPanel workerListPanel_2_1_2_2 = new JPanel();
				workerListPanel_2_1_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				workerListPanel_2_1_2_2.setBounds(12, 46, 560, 427);
				workerManagePanel_1_1_2_2.add(workerListPanel_2_1_2_2);

				JLabel workerListLabel_1_1_1_2_2 = new JLabel("파견계약상세정보");
				workerListLabel_1_1_1_2_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
				workerListLabel_1_1_1_2_2.setBounds(595, 10, 127, 33);
				workerManagePanel_1_1_2_2.add(workerListLabel_1_1_1_2_2);

				JLabel mainViewLogoLabel = new JLabel("해외파견관리 <업체>");
				mainViewLogoLabel.setForeground(new Color(16, 24, 32));
				mainViewLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
				mainViewLogoLabel.setBounds(12, 10, 189, 55);
				contentPane.add(mainViewLogoLabel);
			}

//			public void reqListTB(String[] header) {
//
//				try {
//					dao = new ReqDAO();
//
//					ArrayList reqList = dao.searchReqInfo(); // 요청목록 ArrayList 형태로 가져오기
//					String[][] contentsReq = dao.reqList(reqList,header);
//
//					model.setNumRows(0); // 초기화
//
//					for (int i = 0; i < contentsReq.length; i++) {
//
//						model.addRow(contentsReq[i]);
//
//					}
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}

//			public void contListTB(String[] header1) {
//
//				try {
//					dao1 = new ReqContDAO();
//
//					ArrayList contList = dao1.searchReqContInfo(); // 요청목록 ArrayList 형태로 가져오기
//					String[][] contentsReqCont = dao1.reqContList(contList,header1);
//
//					model1.setNumRows(0); // 초기화
//
//					for (int i = 0; i < contentsReqCont.length; i++) {
//
//						model1.addRow(contentsReqCont[i]);
//
//					}
//
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
		}

