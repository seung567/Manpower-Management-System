package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.CareerDAO;
import model.ManagerReqDAO;
import model.ManagerWorkerDAO;
import model.rec.ReqVO;
import model.rec.WorkerVO;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

public class ManagerView extends JFrame {

	private JPanel contentPane;
	private JTextField workerNameTx;
	private JTextField workerTelTx;
	private JTextField workerAgeTx;
	private JTextField workerEmailTx;
	private JTable workerListTB;
	private JTable certiTB;
	private JLabel careerPeriodLabel_value;
	private JLabel workerCodeLabel;
	private JLabel reqCodeLabel;


	String id;
	private JTable reqTB;
	private JTextField sexTx;
	private JTextField totalCostTx;
	private JTextField qualiTx;
	private JTextField localTx;
	private JTextField ageRangeTx;
	private JTextField langTx;
	private JTextField cityNameTx;
	private JTextField reqLangLevelTx;
	private JTextField expecEdateTx;
	private JTextField expecSdateTx;
	private JTextField custNameTx;
	private JTextField sectorNameTx;
	private JTextField worekrNumTx;
	private JTextField localLangLevelTx;
	private JTable reqContTB;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable careerTB;
	
	// RecentListTableModel rListTable;
	private DefaultTableModel model;
	private DefaultTableModel certiModel;
	private DefaultTableModel reqModel;
	private DefaultTableModel reqContModel;
	private DefaultTableModel careerModel;
	
	private DefaultTableCellRenderer center;
	private ManagerWorkerDAO dao = null;
	private ManagerReqDAO reqDao = null;
	private CareerDAO careerDao = null;
	private String[][] contents = null;
	private String[] workerHeader = { "파견인력번호", "이름", "전화번호", "나이", "기술분야" };
	private String[] certiHeader = { "관리번호", "자격증명", "자격번호", "취득일", "유효기간" };
	private String[] reqHeader = { "파견요청번호", "업체명", "국가명", "도시명", "파견비용", "예상근무시작일", "승인처리" };
	private String[] reqContHeader = {"파견계약번호", "파견요청번호", "계약성사여부", "계약체결일", "계약만기일", "계약만기사유", "업체정산여부"};
	private String[] careerHeader = {"업체명","직무시작일","직무종료일","경력내용"};

	/**
	 * Launch the application.
	 */
	public static void managerMainView(String ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ManagerView frame = new ManagerView(ID);
					frame.setVisible(true);
					frame.setResizable(false);
					;

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
							} else if (result == JOptionPane.NO_OPTION) {

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
	
	// 윈도우 빌더 테스트용 생성자 함수
	public ManagerView() {
		
		this.managerMainView("테스트");
		
	};
	
	// 실행용 생성자 함수
	public ManagerView(int num) {};
	
	// 메인 뷰 생성자 함수 
	public ManagerView(String id) {

		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		this.id = id;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 820);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane.setLayout(null);
		
		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		
		// 파견인력 목록 model
		model = new DefaultTableModel(contents, workerHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		// 자격증 정보 목록 model
		certiModel = new DefaultTableModel(contents, certiHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// 파견요청 목록 model
		reqModel = new DefaultTableModel(contents, reqHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		reqContModel = new DefaultTableModel(contents, reqContHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		careerModel = new DefaultTableModel(contents, careerHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTabbedPane managerMainTab = new JTabbedPane(JTabbedPane.TOP);
		managerMainTab.setBorder(null);

		// managerMainTab.add("1",JPanel(new WorkerView().main(null);));
		managerMainTab.setBounds(12, 65, 1410, 706);
		contentPane.add(managerMainTab);

		JPanel workerManagePanel = new JPanel();
		workerManagePanel.setForeground(new Color(16, 24, 32));
		workerManagePanel.setBackground(new Color(16, 24, 32));
		workerManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견인력관리", null, workerManagePanel, null);
		workerManagePanel.setLayout(null);

		JLabel workerListLabel = new JLabel("파견인력목록");
		workerListLabel.setBackground(new Color(242, 170, 76));
		workerListLabel.setForeground(new Color(242, 170, 76));
		workerListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerListLabel.setBounds(12, 10, 113, 33);
		workerManagePanel.add(workerListLabel);

		JPanel workerListPanel = new JPanel();
		workerListPanel.setForeground(new Color(255, 255, 255));
		workerListPanel.setBackground(new Color(16, 24, 32));
		workerListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerListPanel.setBounds(12, 46, 750, 621);
		workerManagePanel.add(workerListPanel);
		workerListPanel.setLayout(null);

		JLabel workerInfoLabel_1 = new JLabel("파견인력정보");
		workerInfoLabel_1.setBackground(new Color(242, 170, 76));
		workerInfoLabel_1.setForeground(new Color(242, 170, 76));
		workerInfoLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerInfoLabel_1.setBounds(790, 10, 498, 33);
		workerManagePanel.add(workerInfoLabel_1);

		JPanel workerInfoPanel = new JPanel();
		workerInfoPanel.setForeground(new Color(255, 255, 255));
		workerInfoPanel.setBackground(new Color(16, 24, 32));
		workerInfoPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerInfoPanel.setBounds(774, 46, 619, 565);
		workerManagePanel.add(workerInfoPanel);
		workerInfoPanel.setLayout(null);

		JLabel workerCodeLabel = new JLabel("");
		workerCodeLabel.setForeground(new Color(242, 170, 76));
		workerCodeLabel.setHorizontalAlignment(JLabel.RIGHT);
		workerCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCodeLabel.setBounds(500, 10, 107, 31);
		workerInfoPanel.add(workerCodeLabel);

		JLabel workerNameLabel = new JLabel("이름");
		workerNameLabel.setForeground(new Color(242, 170, 76));
		workerNameLabel.setBackground(new Color(242, 170, 76));
		workerNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerNameLabel.setBounds(28, 72, 84, 31);
		workerInfoPanel.add(workerNameLabel);

		workerNameTx = new JTextField();
		workerNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerNameTx.setEditable(false);
		workerNameTx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				workerNameTx.setText("");

			}
		});
		workerNameTx.setToolTipText("");
		workerNameTx.setBounds(102, 72, 107, 31);
		workerInfoPanel.add(workerNameTx);
		workerNameTx.setColumns(10);

		JLabel workerTelLabel = new JLabel("연락처");
		workerTelLabel.setForeground(new Color(242, 170, 76));
		workerTelLabel.setBackground(new Color(242, 170, 76));
		workerTelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerTelLabel.setBounds(28, 123, 84, 31);
		workerInfoPanel.add(workerTelLabel);

		workerTelTx = new JTextField();
		workerTelTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerTelTx.setEditable(false);
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(102, 124, 107, 31);
		workerInfoPanel.add(workerTelTx);

		JLabel workerAgeLabel = new JLabel("나이");
		workerAgeLabel.setForeground(new Color(242, 170, 76));
		workerAgeLabel.setBackground(new Color(242, 170, 76));
		workerAgeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerAgeLabel.setBounds(244, 71, 84, 31);
		workerInfoPanel.add(workerAgeLabel);

		workerAgeTx = new JTextField();
		workerAgeTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerAgeTx.setEditable(false);
		workerAgeTx.setColumns(10);
		workerAgeTx.setBounds(329, 71, 62, 31);
		workerInfoPanel.add(workerAgeTx);

		JLabel careerPeriodLabel = new JLabel("경력기간");
		careerPeriodLabel.setForeground(new Color(242, 170, 76));
		careerPeriodLabel.setBackground(new Color(242, 170, 76));
		careerPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerPeriodLabel.setBounds(28, 201, 62, 31);
		workerInfoPanel.add(careerPeriodLabel);

		workerEmailTx = new JTextField();
		workerEmailTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerEmailTx.setEditable(false);
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(329, 123, 213, 31);
		workerInfoPanel.add(workerEmailTx);

		JLabel certiLabel = new JLabel("취득 자격증");
		certiLabel.setForeground(new Color(242, 170, 76));
		certiLabel.setBackground(new Color(242, 170, 76));
		certiLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiLabel.setBounds(28, 408, 84, 31);
		workerInfoPanel.add(certiLabel);

		JPanel certiPanel = new JPanel();
		certiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.setBounds(28, 449, 557, 92);
		workerInfoPanel.add(certiPanel);

		certiPanel.setLayout(null);

		JTable certiTB = new JTable(certiModel);
		certiTB.setColumnSelectionAllowed(true);
		certiTB.setCellSelectionEnabled(true);
		certiTB.setEnabled(false);
		certiTB.setBounds(47, 10, 309, 144);
		certiTB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.add(certiTB);

		certiTB.setModel(certiModel);

		JScrollPane certiTBscrollPane = new JScrollPane(certiTB);
		certiTBscrollPane.setBounds(0, 0, 557, 92);
		certiPanel.add(certiTBscrollPane);

		JLabel careerPeriodLabel_value = new JLabel("[경력기간]");
		careerPeriodLabel_value.setForeground(new Color(242, 170, 76));
		careerPeriodLabel_value.setBackground(new Color(242, 170, 76));
		careerPeriodLabel_value.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerPeriodLabel_value.setBounds(102, 201, 84, 31);
		workerInfoPanel.add(careerPeriodLabel_value);

		JLabel workerEmailLabel = new JLabel("이메일");
		workerEmailLabel.setForeground(new Color(242, 170, 76));
		workerEmailLabel.setBackground(new Color(242, 170, 76));
		workerEmailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerEmailLabel.setBounds(244, 122, 67, 31);
		workerInfoPanel.add(workerEmailLabel);

		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setForeground(new Color(242, 170, 76));
		careerDetailLabel.setBackground(new Color(242, 170, 76));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerDetailLabel.setBounds(28, 266, 67, 31);
		workerInfoPanel.add(careerDetailLabel);
		
		JPanel careerPanel = new JPanel();
		careerPanel.setLayout(null);
		careerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		careerPanel.setBounds(28, 307, 557, 92);
		workerInfoPanel.add(careerPanel);
		
		careerTB = new JTable(careerModel);
		careerTB.setEnabled(false);
		careerTB.setColumnSelectionAllowed(true);
		careerTB.setCellSelectionEnabled(true);
		careerTB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		careerTB.setBounds(28, 307, 557, 92);
		careerPanel.add(careerTB);
		
		JScrollPane certiTBscrollPane_1 = new JScrollPane(careerTB);
		certiTBscrollPane_1.setBounds(0, 0, 557, 92);
		careerPanel.add(certiTBscrollPane_1);

		JButton workerReqInfoBtn = new JButton("계약내역확인");
		workerReqInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerReqInfoBtn.setBackground(new Color(16, 24, 32));
		workerReqInfoBtn.setForeground(new Color(255, 255, 255));
		workerReqInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String workerCode = workerCodeLabel.getText();
				
				if (workerCode != "") {
					
//					new WorkerContInfoView(0).workerContAtion(codeValue,id);
					new SameContCode(0).sameContCodeAction(workerCode,id);
					
				} else {
					JOptionPane.showMessageDialog(null, "파견인력을 선택 하여 주세요");
				}

			}
		});

		workerReqInfoBtn.setBounds(1097, 621, 142, 46);
		workerManagePanel.add(workerReqInfoBtn);

		JButton workerVisatBtn = new JButton("비자정보등록");
		workerVisatBtn.setBackground(new Color(16, 24, 32));
		workerVisatBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerVisatBtn.setForeground(new Color(255, 255, 255));
		workerVisatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new WorkerVisaView().Action();

			}
		});

		workerVisatBtn.setBounds(1251, 621, 142, 46);
		workerManagePanel.add(workerVisatBtn);


		
		
		workerListTB = new JTable(model);
		
		workerListTB.getColumn("파견인력번호").setPreferredWidth(3);
		workerListTB.getColumn("이름").setPreferredWidth(3);
		workerListTB.getColumn("전화번호").setPreferredWidth(50);
		workerListTB.getColumn("나이").setPreferredWidth(3);
		workerListTB.getColumn("기술분야").setPreferredWidth(3);
		
		workerListTB.getColumn("파견인력번호").setCellRenderer(center);
		workerListTB.getColumn("이름").setCellRenderer(center);
		workerListTB.getColumn("전화번호").setCellRenderer(center);
		workerListTB.getColumn("나이").setCellRenderer(center);
		workerListTB.getColumn("기술분야").setCellRenderer(center);
		
		

		workerListTB.setBounds(1, 27, 450, 16);
		workerListTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerListPanel.add(workerListTB);

		JScrollPane scrollPane = new JScrollPane(workerListTB);
		scrollPane.setBounds(10, 10, 728, 601);
		workerListPanel.add(scrollPane);



		JButton workerSerchBtn = new JButton("조회");
		workerSerchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				workerListTB(workerHeader);

			}
		});

		workerSerchBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerSerchBtn.setBounds(673, 15, 89, 23);
		workerManagePanel.add(workerSerchBtn);

		// 파견요청관리 탭 메인
		JPanel reqManagePanel = new JPanel();
		reqManagePanel.setBackground(new Color(16, 24, 32));
		reqManagePanel.setForeground(new Color(16, 24, 32));
		reqManagePanel.setLayout(null);
		reqManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견요청관리", null, reqManagePanel, null);

		JLabel reqListLabel = new JLabel("파견요청목록");
		reqListLabel.setForeground(new Color(242, 170, 76));
		reqListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqListLabel.setBounds(12, 10, 113, 33);
		reqManagePanel.add(reqListLabel);

		JPanel reqListPanel = new JPanel();
		reqListPanel.setBackground(new Color(16, 24, 32));
		reqListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqListPanel.setBounds(12, 46, 832, 621);
		reqManagePanel.add(reqListPanel);
		reqListPanel.setLayout(null);
		
	
		// ----------------------------------- 파견요청목록 테이블 -----------------------------
		reqTB = new JTable(reqModel);
		reqTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		

		
//		reqTB.getColumn("파견요청번호").setPreferredWidth(3);
//		reqTB.getColumn("업체명").setPreferredWidth(3);
//		reqTB.getColumn("국가명").setPreferredWidth(50);
//		reqTB.getColumn("도시명").setPreferredWidth(3);
//		reqTB.getColumn("파견비용").setPreferredWidth(35);
//		reqTB.getColumn("예상근무시작일").setPreferredWidth(3);
		
		reqTB.getColumn("파견요청번호").setCellRenderer(center);
		reqTB.getColumn("업체명").setCellRenderer(center);
		reqTB.getColumn("국가명").setCellRenderer(center);
		reqTB.getColumn("도시명").setCellRenderer(center);
		reqTB.getColumn("파견비용").setCellRenderer(center);
		reqTB.getColumn("예상근무시작일").setCellRenderer(center);
		reqTB.getColumn("승인처리").setCellRenderer(center);
		
		reqTB.setBounds(12, 10, 586, 473);
		reqListPanel.add(reqTB);
		
		JScrollPane reqScrollPane = new JScrollPane(reqTB);
		reqScrollPane.setBounds(10, 10, 810, 601);
		reqListPanel.add(reqScrollPane);
		
		// ----------------------------------- 파견요청목록 테이블 -----------------------------
		
		JLabel reqInfoLabel = new JLabel("파견요청정보");
		reqInfoLabel.setForeground(new Color(242, 170, 76));
		reqInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqInfoLabel.setBounds(856, 10, 100, 33);
		reqManagePanel.add(reqInfoLabel);

		JButton reqCancelBtn = new JButton("파견요청취소");
		reqCancelBtn.setBackground(new Color(16, 24, 32));
		reqCancelBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCancelBtn.setForeground(new Color(255, 255, 255));
		reqCancelBtn.setBounds(1085, 621, 142, 46);
		reqManagePanel.add(reqCancelBtn);
		
		JPanel reqInfoPanel = new JPanel();
		reqInfoPanel.setLayout(null);
		reqInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel.setBackground(new Color(16, 24, 32));
		reqInfoPanel.setBounds(856, 46, 537, 565);
		reqManagePanel.add(reqInfoPanel);
		
		JLabel reqCustCode = new JLabel("업체명");
		reqCustCode.setForeground(new Color(242, 170, 76));
		reqCustCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCustCode.setBounds(31, 48, 75, 33);
		reqInfoPanel.add(reqCustCode);
		
		JLabel city = new JLabel("도시명");
		city.setForeground(new Color(242, 170, 76));
		city.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		city.setBounds(141, 215, 43, 21);
		reqInfoPanel.add(city);
		
		JLabel expecSdate = new JLabel("예상근무시작일");
		expecSdate.setForeground(new Color(242, 170, 76));
		expecSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		expecSdate.setBounds(31, 161, 105, 21);
		reqInfoPanel.add(expecSdate);
		
		JLabel reqLangLevel = new JLabel("필수어학수준");
		reqLangLevel.setForeground(new Color(242, 170, 76));
		reqLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqLangLevel.setBounds(284, 365, 97, 21);
		reqInfoPanel.add(reqLangLevel);
		
		JLabel ageRange = new JLabel("연령대");
		ageRange.setForeground(new Color(242, 170, 76));
		ageRange.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		ageRange.setBounds(31, 421, 43, 21);
		reqInfoPanel.add(ageRange);
		
		JLabel local = new JLabel("상세근무장소");
		local.setForeground(new Color(242, 170, 76));
		local.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		local.setBounds(31, 263, 105, 21);
		reqInfoPanel.add(local);
		
		JLabel sex = new JLabel("성별");
		sex.setForeground(new Color(242, 170, 76));
		sex.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sex.setBounds(31, 365, 43, 21);
		reqInfoPanel.add(sex);
		
		
		JLabel expecEdate = new JLabel("예상근무종료일");
		expecEdate.setForeground(new Color(242, 170, 76));
		expecEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		expecEdate.setBounds(284, 161, 105, 21);
		reqInfoPanel.add(expecEdate);
		
		sexTx = new JTextField();
		sexTx.setEditable(false);
		sexTx.setHorizontalAlignment(SwingConstants.CENTER);
		sexTx.setColumns(10);
		sexTx.setBounds(141, 360, 61, 33);
		reqInfoPanel.add(sexTx);
		
		totalCostTx = new JTextField();
		totalCostTx.setEditable(false);
		totalCostTx.setHorizontalAlignment(SwingConstants.CENTER);
		totalCostTx.setColumns(10);
		totalCostTx.setBounds(97, 502, 343, 33);
		reqInfoPanel.add(totalCostTx);
		
		qualiTx = new JTextField();
		qualiTx.setEditable(false);
		qualiTx.setHorizontalAlignment(SwingConstants.CENTER);
		qualiTx.setColumns(10);
		qualiTx.setBounds(171, 416, 343, 33);
		reqInfoPanel.add(qualiTx);
		
		localTx = new JTextField();
		localTx.setEditable(false);
		localTx.setHorizontalAlignment(SwingConstants.CENTER);
		localTx.setColumns(10);
		localTx.setBounds(141, 258, 373, 33);
		reqInfoPanel.add(localTx);
		
		JLabel quali = new JLabel("자격요건");
		quali.setForeground(new Color(242, 170, 76));
		quali.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		quali.setBounds(203, 421, 61, 21);
		reqInfoPanel.add(quali);
		
		JLabel totalCost = new JLabel("총파견비용");
		totalCost.setForeground(new Color(242, 170, 76));
		totalCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		totalCost.setBounds(228, 471, 81, 21);
		reqInfoPanel.add(totalCost);
		
		ageRangeTx = new JTextField();
		ageRangeTx.setEditable(false);
		ageRangeTx.setHorizontalAlignment(SwingConstants.CENTER);
		ageRangeTx.setColumns(10);
		ageRangeTx.setBounds(141, 416, 50, 33);
		reqInfoPanel.add(ageRangeTx);
		
		langTx = new JTextField();
		langTx.setEditable(false);
		langTx.setHorizontalAlignment(SwingConstants.CENTER);
		langTx.setColumns(10);
		langTx.setBounds(141, 309, 122, 33);
		reqInfoPanel.add(langTx);
		
		cityNameTx = new JTextField();
		cityNameTx.setEditable(false);
		cityNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		cityNameTx.setColumns(10);
		cityNameTx.setBounds(221, 210, 196, 33);
		reqInfoPanel.add(cityNameTx);
		
		reqLangLevelTx = new JTextField();
		reqLangLevelTx.setEditable(false);
		reqLangLevelTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqLangLevelTx.setColumns(10);
		reqLangLevelTx.setBounds(392, 360, 122, 33);
		reqInfoPanel.add(reqLangLevelTx);
		
		expecEdateTx = new JTextField();
		expecEdateTx.setEditable(false);
		expecEdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		expecEdateTx.setColumns(10);
		expecEdateTx.setBounds(392, 156, 122, 33);
		reqInfoPanel.add(expecEdateTx);
		
		JLabel reqSectorName = new JLabel("업종명");
		reqSectorName.setForeground(new Color(242, 170, 76));
		reqSectorName.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqSectorName.setBounds(31, 101, 75, 36);
		reqInfoPanel.add(reqSectorName);
		
		JLabel workerNum = new JLabel("요청인원수");
		workerNum.setForeground(new Color(242, 170, 76));
		workerNum.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerNum.setBounds(317, 54, 75, 21);
		reqInfoPanel.add(workerNum);
		
		expecSdateTx = new JTextField();
		expecSdateTx.setEditable(false);
		expecSdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		expecSdateTx.setColumns(10);
		expecSdateTx.setBounds(141, 156, 122, 33);
		reqInfoPanel.add(expecSdateTx);
		
		custNameTx = new JTextField();
		custNameTx.setEditable(false);
		custNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		custNameTx.setForeground(Color.BLACK);
		custNameTx.setColumns(10);
		custNameTx.setBackground(Color.WHITE);
		custNameTx.setBounds(141, 49, 150, 33);
		reqInfoPanel.add(custNameTx);
		
		sectorNameTx = new JTextField();
		sectorNameTx.setEditable(false);
		sectorNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		sectorNameTx.setColumns(10);
		sectorNameTx.setBounds(141, 104, 373, 33);
		reqInfoPanel.add(sectorNameTx);
		
		JLabel lang = new JLabel("필요언어");
		lang.setForeground(new Color(242, 170, 76));
		lang.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lang.setBounds(31, 314, 97, 21);
		reqInfoPanel.add(lang);
		
		worekrNumTx = new JTextField();
		worekrNumTx.setEditable(false);
		worekrNumTx.setHorizontalAlignment(SwingConstants.CENTER);
		worekrNumTx.setColumns(10);
		worekrNumTx.setBounds(406, 48, 108, 33);
		reqInfoPanel.add(worekrNumTx);
		
		localLangLevelTx = new JTextField();
		localLangLevelTx.setEditable(false);
		localLangLevelTx.setHorizontalAlignment(SwingConstants.CENTER);
		localLangLevelTx.setColumns(10);
		localLangLevelTx.setBounds(391, 309, 123, 33);
		reqInfoPanel.add(localLangLevelTx);
		
		JLabel localLangLevel = new JLabel("현지어학수준");
		localLangLevel.setForeground(new Color(242, 170, 76));
		localLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		localLangLevel.setBounds(284, 306, 105, 36);
		reqInfoPanel.add(localLangLevel);
		
		reqCodeLabel = new JLabel("New label");
		reqCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		reqCodeLabel.setForeground(new Color(255, 255, 255));
		reqCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		reqCodeLabel.setBounds(382, 10, 143, 33);
		reqInfoPanel.add(reqCodeLabel);
		
		JButton supportBtn = new JButton("파견요청승인");
		supportBtn.setForeground(Color.WHITE);
		supportBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportBtn.setBackground(new Color(16, 24, 32));
		supportBtn.setBounds(1239, 621, 154, 46);
		reqManagePanel.add(supportBtn);

		JPanel contManagePanel = new JPanel();
		contManagePanel.setBackground(new Color(16, 24, 32));
		contManagePanel.setLayout(null);
		contManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견계약관리", null, contManagePanel, null);

		JLabel contListLabel = new JLabel("파견계약목록");
		contListLabel.setForeground(new Color(242, 170, 76));
		contListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contListLabel.setBounds(12, 10, 113, 33);
		contManagePanel.add(contListLabel);

		JPanel contListPanel = new JPanel();
		contListPanel.setBackground(new Color(16, 24, 32));
		contListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		contListPanel.setBounds(12, 46, 832, 621);
		contManagePanel.add(contListPanel);
		contListPanel.setLayout(null);
		
		reqContTB = new JTable(reqContModel);
		reqContTB.getColumn("파견계약번호").setCellRenderer(center);
		reqContTB.getColumn("파견요청번호").setCellRenderer(center);
		reqContTB.getColumn("계약성사여부").setCellRenderer(center);
		reqContTB.getColumn("계약체결일").setCellRenderer(center);
		reqContTB.getColumn("계약만기일").setCellRenderer(center);
		reqContTB.getColumn("계약만기사유").setCellRenderer(center);
		reqContTB.getColumn("업체정산여부").setCellRenderer(center);
		
		reqContTB.setBounds(12, 46, 750, 621);
		contListPanel.add(reqContTB);
		
		JScrollPane reqContScrollPane = new JScrollPane(reqContTB);
		reqContScrollPane.setBounds(10, 10, 810, 601);
		contListPanel.add(reqContScrollPane);
		
		JButton reqCancelBtn_1 = new JButton("파견계약인력");
		reqCancelBtn_1.setForeground(Color.WHITE);
		reqCancelBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCancelBtn_1.setBackground(new Color(16, 24, 32));
		reqCancelBtn_1.setBounds(1231, 10, 161, 25);
		contManagePanel.add(reqCancelBtn_1);
		
		JPanel reqInfoPanel_1 = new JPanel();
		reqInfoPanel_1.setLayout(null);
		reqInfoPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel_1.setBackground(new Color(16, 24, 32));
		reqInfoPanel_1.setBounds(856, 46, 537, 427);
		contManagePanel.add(reqInfoPanel_1);
		
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
		
		JLabel reqContEdate = new JLabel("계약만기일");
		reqContEdate.setForeground(new Color(242, 170, 76));
		reqContEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEdate.setBounds(275, 114, 105, 21);
		reqInfoPanel_1.add(reqContEdate);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(139, 196, 367, 208);
		reqInfoPanel_1.add(textField);
		
		JLabel reqContEwhy = new JLabel("계약만기사유");
		reqContEwhy.setForeground(new Color(242, 170, 76));
		reqContEwhy.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEwhy.setBounds(22, 196, 105, 21);
		reqInfoPanel_1.add(reqContEwhy);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(384, 152, 122, 33);
		reqInfoPanel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(139, 152, 122, 33);
		reqInfoPanel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(384, 109, 122, 33);
		reqInfoPanel_1.add(textField_3);
		
		JLabel reqContCk = new JLabel("계약성사여부");
		reqContCk.setForeground(new Color(242, 170, 76));
		reqContCk.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCk.setBounds(275, 63, 97, 36);
		reqInfoPanel_1.add(reqContCk);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(139, 109, 122, 33);
		reqInfoPanel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.BLACK);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(138, 25, 122, 33);
		reqInfoPanel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(384, 66, 123, 33);
		reqInfoPanel_1.add(textField_6);
		
		JLabel reqCode = new JLabel("파견요청번호");
		reqCode.setForeground(new Color(242, 170, 76));
		reqCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCode.setBounds(275, 25, 97, 33);
		reqInfoPanel_1.add(reqCode);
		
		textField_7 = new JTextField();
		textField_7.setForeground(Color.BLACK);
		textField_7.setColumns(10);
		textField_7.setBackground(Color.WHITE);
		textField_7.setBounds(384, 25, 122, 33);
		reqInfoPanel_1.add(textField_7);
		
		JLabel workerCode = new JLabel("파견인력번호");
		workerCode.setForeground(new Color(242, 170, 76));
		workerCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCode.setBounds(22, 63, 97, 36);
		reqInfoPanel_1.add(workerCode);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(138, 66, 123, 33);
		reqInfoPanel_1.add(textField_8);
		
		JLabel workerListLabel_1_2 = new JLabel("파견계약상세정보");
		workerListLabel_1_2.setForeground(new Color(242, 170, 76));
		workerListLabel_1_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerListLabel_1_2.setBounds(856, 10, 127, 33);
		contManagePanel.add(workerListLabel_1_2);

		// 정산관리
		JPanel sheetManagerPanel = new JPanel();
		sheetManagerPanel.setBackground(new Color(16, 24, 32));
		sheetManagerPanel.setLayout(null);
		sheetManagerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("정산관리", null, sheetManagerPanel, null);

		JLabel sheetListLabel = new JLabel("월별 정산 내역");
		sheetListLabel.setForeground(new Color(242, 170, 76));
		sheetListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetListLabel.setBounds(12, 10, 113, 33);
		sheetManagerPanel.add(sheetListLabel);

		JPanel sheetListPanel = new JPanel();
		sheetListPanel.setBackground(new Color(16, 24, 32));
		sheetListPanel.setForeground(new Color(16, 24, 32));
		sheetListPanel.setLayout(null);
		sheetListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetListPanel.setBounds(12, 46, 750, 621);
		sheetManagerPanel.add(sheetListPanel);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(10, 10, 728, 601);
		sheetListPanel.add(scrollPane_1);

		JLabel sheetCustLabel = new JLabel("업체별 정산 내역");
		sheetCustLabel.setForeground(new Color(242, 170, 76));
		sheetCustLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetCustLabel.setBounds(854, 10, 498, 33);
		sheetManagerPanel.add(sheetCustLabel);

		JPanel sheetCustPanel = new JPanel();
		sheetCustPanel.setBackground(new Color(16, 24, 32));
		sheetCustPanel.setForeground(new Color(16, 24, 32));
		sheetCustPanel.setLayout(null);
		sheetCustPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel.setBounds(854, 46, 498, 427);
		sheetManagerPanel.add(sheetCustPanel);

		JScrollPane scrollPane_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1.setBounds(12, 10, 474, 407);
		sheetCustPanel.add(scrollPane_1_1);

		JButton sheetInfoBtn = new JButton("정산 상세 내역정보");
		sheetInfoBtn.setForeground(new Color(255, 255, 255));
		sheetInfoBtn.setBackground(new Color(16, 24, 32));
		sheetInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetInfoBtn.setBounds(1030, 483, 168, 46);
		sheetManagerPanel.add(sheetInfoBtn);

		JButton sheetCheckBtn = new JButton("입금확인");
		sheetCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetCheckBtn.setForeground(new Color(255, 255, 255));
		sheetCheckBtn.setBackground(new Color(16, 24, 32));
		sheetCheckBtn.setBounds(1210, 483, 142, 46);
		sheetManagerPanel.add(sheetCheckBtn);

		JPanel payManagerPanel = new JPanel();
		payManagerPanel.setBackground(new Color(16, 24, 32));
		payManagerPanel.setLayout(null);
		payManagerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("수당관리", null, payManagerPanel, null);

		JLabel payListLabel = new JLabel("월별 수당 지급내역");
		payListLabel.setForeground(new Color(242, 170, 76));
		payListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payListLabel.setBounds(12, 10, 142, 33);
		payManagerPanel.add(payListLabel);

		JPanel payListPanel = new JPanel();
		payListPanel.setBackground(new Color(16, 24, 32));
		payListPanel.setLayout(null);
		payListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		payListPanel.setBounds(12, 46, 750, 621);
		payManagerPanel.add(payListPanel);

		JScrollPane scrollPane_1_2 = new JScrollPane((Component) null);
		scrollPane_1_2.setBounds(10, 10, 728, 601);
		payListPanel.add(scrollPane_1_2);

		JLabel workerPayLabel = new JLabel("인력별 수당 내역");
		workerPayLabel.setForeground(new Color(242, 170, 76));
		workerPayLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPayLabel.setBounds(870, 10, 498, 33);
		payManagerPanel.add(workerPayLabel);

		JPanel sheetCustPanel_1 = new JPanel();
		sheetCustPanel_1.setBackground(new Color(16, 24, 32));
		sheetCustPanel_1.setLayout(null);
		sheetCustPanel_1
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel_1.setBounds(870, 46, 498, 427);
		payManagerPanel.add(sheetCustPanel_1);

		JScrollPane scrollPane_1_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1_1.setBounds(12, 10, 474, 407);
		sheetCustPanel_1.add(scrollPane_1_1_1);

		JButton payContListCheckBtn = new JButton("계약내역확인");
		payContListCheckBtn.setForeground(new Color(255, 255, 255));
		payContListCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payContListCheckBtn.setBackground(new Color(16, 24, 32));
		payContListCheckBtn.setBounds(1226, 483, 142, 46);
		payManagerPanel.add(payContListCheckBtn);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		mainViewLogoLabel.setBounds(12, 10, 120, 55);
		contentPane.add(mainViewLogoLabel);

		JLabel managerLogoLabel = new JLabel("<관리자>");
		managerLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		managerLogoLabel.setBounds(142, 10, 94, 55);
		contentPane.add(managerLogoLabel);
		
		JLabel mgrName = new JLabel("New label");
		mgrName.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		mgrName.setBounds(985, 22, 145, 33);
		contentPane.add(mgrName);
		
		//-----------------------------이벤트 실행부 -------------------------

		// 파견요청 테이블 이벤트
		reqTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = reqTB.getSelectedRow();
				String reqCodeString = String.valueOf(reqTB.getValueAt(row, col));
				int reqCodeInt = Integer.parseInt(reqCodeString);
				
				
				try {
					
					reqDao = new ManagerReqDAO();
					ReqVO vo = reqDao.serachReqInfo(reqCodeInt);
					
					reqCodeLabel.setText(reqCodeString);
					
					custNameTx.setText(vo.getCustName()); // 업체명
					sectorNameTx.setText(vo.getSectorName()); // 업종명
					worekrNumTx.setText(String.valueOf(vo.getWorkerNum())); // 요청인원
					expecSdateTx.setText(vo.getExpecSdate()); // 예상근무시작일
					expecEdateTx.setText(vo.getExpecEdate()); // 예상근무종료일
					cityNameTx.setText(vo.getCityName()); // 도시명
					localTx.setText(vo.getLocal()); // 상세근무장소
					langTx.setText(vo.getLangLevel()); // 필요언어 ( 영어만 가능 or 영어현지가능 or 현지가능 )
					localLangLevelTx.setText(vo.getLocalLangLevel()); // 현지 어학수준
					reqLangLevelTx.setText(vo.getReqLangLevel()); // 필수 어학수준
					sexTx.setText(vo.getSex()); // 성별
					ageRangeTx.setText(vo.getAgeRange()); // 연령대
					qualiTx.setText(vo.getQuali()); // 자격요건
					totalCostTx.setText(vo.gettotalCost()); // 총 파견 비용
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("파견요청테이블 이벤트 " + e2.getMessage());
				}
				
			}
		});
		
		workerListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int col = 0; // 컬럼 위치
				int row = workerListTB.getSelectedRow(); // 내가 클릭한 행의 위치

				
				String vNum = String.valueOf(workerListTB.getValueAt(row, col));

				try {
					WorkerVO vo = dao.workerInfoSerch(vNum); // 인력목록에서 값을 받아 텍스트 필드 정보 가져오기

					// 상세정보 텍스트 필드 입력
					String code = String.valueOf(vo.getWorkerCode());

					workerCodeLabel.setText(code);
					workerNameTx.setText(vo.getWorkerName());
					workerAgeTx.setText(vo.getWorkerAge());
					workerTelTx.setText(vo.getWorkerTel());
					workerEmailTx.setText(vo.getWorkerEmail());
					careerPeriodLabel_value.setText(vo.getCareerPeriod());

					// 자격증 정보 목록 출력
					certiListTB(certiHeader, code);
					careerTB(careerHeader, code);

				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			}
		});
		
		reqContTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int col = 0; // 컬럼 위치
				int row = reqContTB.getSelectedRow(); // 내가 클릭한 행의 위치
				
				String vNum = String.valueOf(reqContTB.getValueAt(row, col));
				int result = Integer.parseInt(vNum);
				
				
				try {
					
//					reqDao = new ManagerReqDAO();
//					int num = reqDao.reqCodeReturn(result);
//					System.out.println(num);
//					textField_7.setText(String.valueOf(num));
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}
				
				
			}
		});
		
		reqCancelBtn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
			}
		});
		
		supportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String reqCode = reqCodeLabel.getText();
				new reqrContInsertView(0).workerContAction(reqCode,id);
				
			}
		});
		//-----------------------------명령 실행부----------------------------
		
		workerListTB(workerHeader); // 파견인력 목록 출력
		reqTB(reqHeader); // 파견요청 목록 출력
//		reqContTB(reqContHeader); // 계약 목록 출력
		
	}
// 메소드 정의부 -------------------------------------------------------------------------
	
	// 파견인력목록 메소드
	void workerListTB(String[] header) {
		try {
			
			dao = new ManagerWorkerDAO();
			
			ArrayList workerList = dao.serchWorkerInfo(); // 인력목록 ArrayList 형태로 가져오기
			System.out.println(workerList.get(0));
			String[][] contentsWorker = dao.workerList(workerList, header);

			model.setNumRows(0); // 초기화

			for (int i = 0; i < contentsWorker.length; i++) {

				model.addRow(contentsWorker[i]);

			}

		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "파견인력정보목록오류 : " + e1.getMessage());
		}
	}
	
	// 자격증목록 메소드
	void certiListTB(String[] header, String code) {
		try {

			dao = new ManagerWorkerDAO();

			ArrayList certiList = dao.serchCertiInfo(code); // 인력목록 ArrayList 형태로 가져오기
			String[][] certiContents = dao.workerList(certiList, header);
			

			
			certiModel.setNumRows(0);

			for (int i = 0; i < certiContents.length; i++) {
				certiModel.addRow(certiContents[i]);
	
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "자격증정보목록오류 : " + e1.getMessage());
		}
	}
	
	// 경력목록 메소드
	void careerTB(String[] header,String workerCode) {
		try {
			
			careerDao = new CareerDAO();
			
			ArrayList careerList = careerDao.careerList(workerCode); // 인력목록 ArrayList 형태로 가져오기
			String[][] reqContContents = reqDao.workerList(careerList, header);
			
			careerModel.setNumRows(0);
			
			for (int i = 0; i < reqContContents.length; i++) {
				careerModel.addRow(reqContContents[i]);
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "경력목록오류 : " + e1.getMessage());
		}
	}
	
	//파견요청목록 메소드
	void reqTB(String[] header) {
		try {
			
			reqDao = new ManagerReqDAO();
			
			ArrayList reqList = reqDao.reqList(); // 인력목록 ArrayList 형태로 가져오기
			String[][] reqContents = reqDao.workerList(reqList, header);
			
			reqModel.setNumRows(0);
			
			for (int i = 0; i < reqContents.length; i++) {
				reqModel.addRow(reqContents[i]);
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "파견요청목록오류 : " + e1.getMessage());
		}
	}
	
	
	//계약목록 메소드
//	void reqContTB(String[] header) {
//		try {
//			
//			reqDao = new ManagerReqDAO();
//			
//			ArrayList reqContList = reqDao.reqContList(); // 인력목록 ArrayList 형태로 가져오기
//			String[][] reqContContents = reqDao.workerList(reqContList, header);
//			
//			reqContModel.setNumRows(0);
//			
//			for (int i = 0; i < reqContContents.length; i++) {
//				reqContModel.addRow(reqContContents[i]);
//			}
//			
//		} catch (Exception e1) {
//			// TODO: handle exception
//			e1.printStackTrace();
//			JOptionPane.showMessageDialog(null, "계약목록오류 : " + e1.getMessage());
//		}
//	}
//	
	
	void setReqCont(int reqCode) {
		
		try {
			

			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}
}
