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
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.CareerDAO;
import model.Connect;
import model.LoginDAO;
import model.ReqDAO;
import model.WorkerDAO;
import model.ReqContDAO;
import model.SheetDAO;
import model.rec.MgrVO;
import model.rec.PayVO;
import model.rec.ReqContVO;
import model.rec.ReqVO;
import model.rec.SheetVO;
import model.rec.WorkerVO;
import javax.swing.table.TableModel;

public class MManagerView extends Connect {

	private JPanel contentPane;
	private JTextField workerNameTx;
	private JTextField workerTelTx;
	private JTextField workerAgeTx;
	private JTextField workerEmailTx;
	private JTable workerListTB;
	private JTable certiTB;
	private JLabel careerPeriodLabel_value;
	private JLabel workerCodeLabel;

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
	private JTextField reqContEwhyTx;
	private JTextField actualEdateTx;
	private JTextField actualSdateTx;
	private JTextField reqContEdateTx;
	private JTextField reqContSdateTx;
	private JTextField reqContCodeTx;
	private JTextField reqCheckTx;
	private JTextField reqCodeTx;
	private JTable careerTB;
	private JButton supportBtn;

	// RecentListTableModel rListTable;
	private DefaultTableModel model;
	private DefaultTableModel certiModel;
	private DefaultTableModel reqModel;
	private DefaultTableModel reqContModel;
	private DefaultTableModel careerModel;
	private DefaultTableModel sheetModel;
	private DefaultTableModel priceModel;

	private DefaultTableCellRenderer center;

	private WorkerDAO dao = null;
	private ReqDAO reqDao = null;
	private CareerDAO careerDao = null;
	private ReqContDAO reqContDao = null;
	private SheetDAO sheetDao = null;

	private String[][] contents = null;
	private String[] workerHeader = { "파견인력번호", "이름", "전화번호", "나이", "기술분야" };
	private String[] certiHeader = { "관리번호", "자격증명", "자격번호", "취득일", "유효기간" };
	private String[] reqHeader = { "파견요청번호", "업체명", "국가명", "도시명", "파견비용", "예상근무시작일", "승인처리" };
	private String[] reqContHeader = { "파견계약번호", "파견요청번호", "계약성사여부", "계약체결일", "계약만기일", "지원현황" };
	private String[] careerHeader = { "업체명", "직무시작일", "직무종료일", "경력내용" };
	private String[] sheetHeader = {"고용계약번호" , "파견계약번호" , "계약체결일" , "명세표번호"};
	private String[] priceHeader = {"고용계약번호" , "파견계약번호" , "계약체결일" , "업체명세서발행번호","업체명세서발행일","지급여부"};

	private String id;
	private int code;
	private JTable sheetTb;
	private JTable sheetTabel;
	private JTextField contCodeTx;
	private JTextField workerCostTx;
	private JTextField contFeeTx;
	private JTextField custTaxTx;
	private JTextField contTotalTx;
	private JTable table;
	private JTextField workerCode_tf;
	private JTextField recontPay_tf;
	private JTextField tax_tf;
	private JTextField total_tf;
	private JTextField workerPay_tf;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void managerMainView(String ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MManagerView frame = new MManagerView(ID);
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
							} else if (result == JOptionPane.NO_OPTION) {
								frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
	public MManagerView() {

		this.managerMainView("테스트");

	};

	// 실행용 생성자 함수
	public MManagerView(int num) {

	};

	// 메인 뷰 생성자 함수
	public MManagerView(String id) {

		this.id = id;

		codeSearch(id);

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
		
		sheetModel = new DefaultTableModel(contents, sheetHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		priceModel = new DefaultTableModel(contents, priceHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JLabel mgrName = new JLabel("New label");
		mgrName.setHorizontalAlignment(SwingConstants.CENTER);
		mgrName.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		mgrName.setBounds(1310, 38, 112, 33);
		contentPane.add(mgrName);

		JLabel mgrName_1 = new JLabel("New label");
		mgrName_1.setHorizontalAlignment(SwingConstants.CENTER);
		mgrName_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		mgrName_1.setBounds(1310, 10, 112, 33);
		contentPane.add(mgrName_1);

		try {

			LoginDAO logindao = new LoginDAO();
			MgrVO vo = logindao.mgrInfoGet(id);

			mgrName_1.setText(String.valueOf(vo.getMgrCode()));
			mgrName.setText(vo.getMgrName());


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}




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
		workerNameLabel.setBounds(68, 78, 84, 31);
		workerInfoPanel.add(workerNameLabel);

		workerNameTx = new JTextField();
		workerNameTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerNameTx.setEditable(false);
		workerNameTx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				workerNameTx.setText("");

			}
		});
		workerNameTx.setToolTipText("");
		workerNameTx.setBounds(142, 78, 107, 31);
		workerInfoPanel.add(workerNameTx);
		workerNameTx.setColumns(10);

		JLabel workerTelLabel = new JLabel("연락처");
		workerTelLabel.setForeground(new Color(242, 170, 76));
		workerTelLabel.setBackground(new Color(242, 170, 76));
		workerTelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerTelLabel.setBounds(274, 78, 84, 31);
		workerInfoPanel.add(workerTelLabel);

		workerTelTx = new JTextField();
		workerTelTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerTelTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerTelTx.setEditable(false);
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(348, 78, 213, 31);
		workerInfoPanel.add(workerTelTx);

		JLabel workerAgeLabel = new JLabel("나이");
		workerAgeLabel.setForeground(new Color(242, 170, 76));
		workerAgeLabel.setBackground(new Color(242, 170, 76));
		workerAgeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerAgeLabel.setBounds(68, 119, 67, 31);
		workerInfoPanel.add(workerAgeLabel);

		workerAgeTx = new JTextField();
		workerAgeTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerAgeTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerAgeTx.setEditable(false);
		workerAgeTx.setColumns(10);
		workerAgeTx.setBounds(142, 119, 107, 31);
		workerInfoPanel.add(workerAgeTx);

		JLabel careerPeriodLabel = new JLabel("경력기간");
		careerPeriodLabel.setForeground(new Color(242, 170, 76));
		careerPeriodLabel.setBackground(new Color(242, 170, 76));
		careerPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerPeriodLabel.setBounds(28, 201, 62, 31);
		workerInfoPanel.add(careerPeriodLabel);

		workerEmailTx = new JTextField();
		workerEmailTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerEmailTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerEmailTx.setEditable(false);
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(348, 119, 213, 31);
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
		workerEmailLabel.setBounds(274, 119, 67, 31);
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

		JButton workerReqInfoBtn = new JButton("계약이력확인");
		workerReqInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerReqInfoBtn.setBackground(new Color(16, 24, 32));
		workerReqInfoBtn.setForeground(new Color(255, 255, 255));
		workerReqInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String workerCode = workerCodeLabel.getText();

				if (workerCode != "") {

					// new MWorkerContInfoView(0).workerContAtion(codeValue,id);
					new MSameContCode(0).sameContCodeAction(workerCode, id);

				} else {
					JOptionPane.showMessageDialog(null, "파견인력을 선택 하여 주세요");
				}

			}
		});

		workerReqInfoBtn.setBounds(1251, 621, 142, 46);
		workerManagePanel.add(workerReqInfoBtn);

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
		workerSerchBtn.setForeground(new Color(255, 255, 255));
		workerSerchBtn.setBackground(new Color(16, 24, 32));
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
		reqListPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqListPanel.setBounds(12, 46, 832, 621);
		reqManagePanel.add(reqListPanel);
		reqListPanel.setLayout(null);

		// ----------------------------------- 파견요청목록 테이블 -----------------------------
		reqTB = new JTable(reqModel);
		reqTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));

		// reqTB.getColumn("파견요청번호").setPreferredWidth(3);
		// reqTB.getColumn("업체명").setPreferredWidth(3);
		// reqTB.getColumn("국가명").setPreferredWidth(50);
		// reqTB.getColumn("도시명").setPreferredWidth(3);
		// reqTB.getColumn("파견비용").setPreferredWidth(35);
		// reqTB.getColumn("예상근무시작일").setPreferredWidth(3);

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

		JButton reqCancelBtn = new JButton("취소");
		reqCancelBtn.setBackground(new Color(16, 24, 32));
		reqCancelBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCancelBtn.setForeground(new Color(255, 255, 255));
		reqCancelBtn.setBounds(1251, 621, 142, 46);
		reqManagePanel.add(reqCancelBtn);

		JPanel reqInfoPanel = new JPanel();
		reqInfoPanel.setLayout(null);
		reqInfoPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
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
		sexTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		sexTx.setEditable(false);
		sexTx.setHorizontalAlignment(SwingConstants.CENTER);
		sexTx.setColumns(10);
		sexTx.setBounds(141, 360, 61, 33);
		reqInfoPanel.add(sexTx);

		totalCostTx = new JTextField();
		totalCostTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		totalCostTx.setEditable(false);
		totalCostTx.setHorizontalAlignment(SwingConstants.CENTER);
		totalCostTx.setColumns(10);
		totalCostTx.setBounds(97, 502, 343, 33);
		reqInfoPanel.add(totalCostTx);

		qualiTx = new JTextField();
		qualiTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		qualiTx.setEditable(false);
		qualiTx.setHorizontalAlignment(SwingConstants.CENTER);
		qualiTx.setColumns(10);
		qualiTx.setBounds(284, 416, 230, 33);
		reqInfoPanel.add(qualiTx);

		localTx = new JTextField();
		localTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		localTx.setEditable(false);
		localTx.setHorizontalAlignment(SwingConstants.CENTER);
		localTx.setColumns(10);
		localTx.setBounds(141, 258, 373, 33);
		reqInfoPanel.add(localTx);

		JLabel quali = new JLabel("자격요건");
		quali.setForeground(new Color(242, 170, 76));
		quali.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		quali.setBounds(211, 421, 61, 21);
		reqInfoPanel.add(quali);

		JLabel totalCost = new JLabel("총파견비용");
		totalCost.setForeground(new Color(242, 170, 76));
		totalCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		totalCost.setBounds(228, 471, 81, 21);
		reqInfoPanel.add(totalCost);

		ageRangeTx = new JTextField();
		ageRangeTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		ageRangeTx.setEditable(false);
		ageRangeTx.setHorizontalAlignment(SwingConstants.CENTER);
		ageRangeTx.setColumns(10);
		ageRangeTx.setBounds(141, 416, 61, 33);
		reqInfoPanel.add(ageRangeTx);

		langTx = new JTextField();
		langTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		langTx.setEditable(false);
		langTx.setHorizontalAlignment(SwingConstants.CENTER);
		langTx.setColumns(10);
		langTx.setBounds(141, 309, 122, 33);
		reqInfoPanel.add(langTx);

		cityNameTx = new JTextField();
		cityNameTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		cityNameTx.setEditable(false);
		cityNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		cityNameTx.setColumns(10);
		cityNameTx.setBounds(221, 210, 196, 33);
		reqInfoPanel.add(cityNameTx);

		reqLangLevelTx = new JTextField();
		reqLangLevelTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqLangLevelTx.setEditable(false);
		reqLangLevelTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqLangLevelTx.setColumns(10);
		reqLangLevelTx.setBounds(392, 360, 122, 33);
		reqInfoPanel.add(reqLangLevelTx);

		expecEdateTx = new JTextField();
		expecEdateTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
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
		expecSdateTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		expecSdateTx.setEditable(false);
		expecSdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		expecSdateTx.setColumns(10);
		expecSdateTx.setBounds(141, 156, 122, 33);
		reqInfoPanel.add(expecSdateTx);

		custNameTx = new JTextField();
		custNameTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		custNameTx.setEditable(false);
		custNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		custNameTx.setForeground(Color.BLACK);
		custNameTx.setColumns(10);
		custNameTx.setBackground(Color.WHITE);
		custNameTx.setBounds(141, 49, 150, 33);
		reqInfoPanel.add(custNameTx);

		sectorNameTx = new JTextField();
		sectorNameTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
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
		worekrNumTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		worekrNumTx.setEditable(false);
		worekrNumTx.setHorizontalAlignment(SwingConstants.CENTER);
		worekrNumTx.setColumns(10);
		worekrNumTx.setBounds(406, 48, 108, 33);
		reqInfoPanel.add(worekrNumTx);

		localLangLevelTx = new JTextField();
		localLangLevelTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
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

		ButtonGroup contStateRaido = new ButtonGroup();

		JRadioButton contAccept = new JRadioButton("승인");
		contAccept.setBackground(new Color(16, 24, 32));
		contAccept.setForeground(new Color(255, 255, 255));
		contAccept.setBounds(8, 12, 61, 23);
		reqInfoPanel.add(contAccept);

		JRadioButton contCancel = new JRadioButton("거절");
		contCancel.setBackground(new Color(16, 24, 32));
		contCancel.setForeground(new Color(255, 255, 255));
		contCancel.setBounds(81, 12, 61, 23);
		reqInfoPanel.add(contCancel);

		JRadioButton contReAccept = new JRadioButton("재요청 승인");
		contReAccept.setBackground(new Color(16, 24, 32));
		contReAccept.setForeground(new Color(255, 255, 255));
		contReAccept.setBounds(8, 12, 105, 23);
		reqInfoPanel.add(contReAccept);

		JRadioButton contRecCancel = new JRadioButton("재요청 반려");
		contRecCancel.setBackground(new Color(16, 24, 32));
		contRecCancel.setForeground(new Color(255, 255, 255));
		contRecCancel.setBounds(122, 12, 105, 23);
		reqInfoPanel.add(contRecCancel);

		contStateRaido.add(contAccept);
		contStateRaido.add(contCancel);
		contStateRaido.add(contReAccept);
		contStateRaido.add(contRecCancel);

		contAccept.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contCancel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contReAccept.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contRecCancel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		
		contReAccept.setVisible(false);
		contRecCancel.setVisible(false);
		contAccept.setVisible(false);
		contCancel.setVisible(false);

		supportBtn = new JButton("확인");
		supportBtn.setForeground(Color.WHITE);
		supportBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportBtn.setBackground(new Color(16, 24, 32));
		supportBtn.setBounds(1097, 621, 142, 46);
		reqManagePanel.add(supportBtn);

		JButton reqSerchBtn = new JButton("조회");
		reqSerchBtn.setBackground(new Color(16, 24, 32));
		reqSerchBtn.setForeground(new Color(255, 255, 255));

		reqSerchBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqSerchBtn.setBounds(755, 16, 89, 23);
		reqManagePanel.add(reqSerchBtn);

		JLabel conStateLabel = new JLabel("New label");
		conStateLabel.setForeground(new Color(255, 255, 255));
		conStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		conStateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		conStateLabel.setBounds(1293, 10, 100, 33);
		reqManagePanel.add(conStateLabel);

		JLabel reqCodeLabel = new JLabel("New label");
		reqCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reqCodeLabel.setForeground(Color.WHITE);
		reqCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		reqCodeLabel.setBounds(1194, 10, 100, 33);
		reqManagePanel.add(reqCodeLabel);

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
		reqContTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqContTB.getColumn("파견계약번호").setCellRenderer(center);
		reqContTB.getColumn("파견요청번호").setCellRenderer(center);
		reqContTB.getColumn("계약성사여부").setCellRenderer(center);
		reqContTB.getColumn("계약체결일").setCellRenderer(center);
		reqContTB.getColumn("계약만기일").setCellRenderer(center);
		reqContTB.getColumn("지원현황").setCellRenderer(center);

		reqContTB.setBounds(12, 46, 750, 621);
		contListPanel.add(reqContTB);

		JScrollPane reqContScrollPane = new JScrollPane(reqContTB);
		reqContScrollPane.setBounds(10, 10, 810, 601);
		contListPanel.add(reqContScrollPane);

		JPanel reqInfoPanel_1 = new JPanel();
		reqInfoPanel_1.setLayout(null);
		reqInfoPanel_1
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel_1.setBackground(new Color(16, 24, 32));
		reqInfoPanel_1.setBounds(856, 46, 537, 535);
		contManagePanel.add(reqInfoPanel_1);

		JLabel reqContCodeLabel = new JLabel("파견계약번호");
		reqContCodeLabel.setForeground(new Color(242, 170, 76));
		reqContCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCodeLabel.setBounds(20, 95, 97, 33);
		reqInfoPanel_1.add(reqContCodeLabel);

		JLabel actualEdate = new JLabel("실근무종료일");
		actualEdate.setForeground(new Color(242, 170, 76));
		actualEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualEdate.setBounds(273, 228, 97, 21);
		reqInfoPanel_1.add(actualEdate);

		JLabel reqContSdate = new JLabel("계약체결일");
		reqContSdate.setForeground(new Color(242, 170, 76));
		reqContSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContSdate.setBounds(20, 170, 105, 21);
		reqInfoPanel_1.add(reqContSdate);

		JLabel actualSdate = new JLabel("실근무시작일");
		actualSdate.setForeground(new Color(242, 170, 76));
		actualSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualSdate.setBounds(20, 228, 105, 21);
		reqInfoPanel_1.add(actualSdate);

		JLabel reqContEdate = new JLabel("계약만기일");
		reqContEdate.setForeground(new Color(242, 170, 76));
		reqContEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEdate.setBounds(273, 170, 105, 21);
		reqInfoPanel_1.add(reqContEdate);

		reqContEwhyTx = new JTextField();
		reqContEwhyTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqContEwhyTx.setHorizontalAlignment(SwingConstants.LEFT);
		reqContEwhyTx.setColumns(10);
		reqContEwhyTx.setBounds(137, 301, 367, 161);
		reqInfoPanel_1.add(reqContEwhyTx);

		JLabel reqContEwhy = new JLabel("계약만기사유");
		reqContEwhy.setForeground(new Color(242, 170, 76));
		reqContEwhy.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEwhy.setBounds(20, 301, 105, 21);
		reqInfoPanel_1.add(reqContEwhy);

		actualEdateTx = new JTextField();
		actualEdateTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		actualEdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		actualEdateTx.setColumns(10);
		actualEdateTx.setBounds(382, 222, 122, 33);
		reqInfoPanel_1.add(actualEdateTx);

		actualSdateTx = new JTextField();
		actualSdateTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		actualSdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		actualSdateTx.setColumns(10);
		actualSdateTx.setBounds(137, 222, 122, 33);
		reqInfoPanel_1.add(actualSdateTx);

		reqContEdateTx = new JTextField();
		reqContEdateTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqContEdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqContEdateTx.setColumns(10);
		reqContEdateTx.setBounds(382, 164, 122, 33);
		reqInfoPanel_1.add(reqContEdateTx);

		JLabel reqContCk = new JLabel("계약성사여부");
		reqContCk.setForeground(new Color(242, 170, 76));
		reqContCk.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCk.setBounds(273, 21, 97, 36);
		reqInfoPanel_1.add(reqContCk);

		reqContSdateTx = new JTextField();
		reqContSdateTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqContSdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqContSdateTx.setColumns(10);
		reqContSdateTx.setBounds(137, 164, 122, 33);
		reqInfoPanel_1.add(reqContSdateTx);

		reqContCodeTx = new JTextField();
		reqContCodeTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqContCodeTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqContCodeTx.setForeground(Color.BLACK);
		reqContCodeTx.setColumns(10);
		reqContCodeTx.setBackground(Color.WHITE);
		reqContCodeTx.setBounds(136, 95, 122, 33);
		reqInfoPanel_1.add(reqContCodeTx);

		reqCheckTx = new JTextField();
		reqCheckTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqCheckTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqCheckTx.setColumns(10);
		reqCheckTx.setBounds(382, 24, 123, 33);
		reqInfoPanel_1.add(reqCheckTx);

		JLabel reqCode = new JLabel("파견요청번호");
		reqCode.setForeground(new Color(242, 170, 76));
		reqCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCode.setBounds(273, 95, 97, 33);
		reqInfoPanel_1.add(reqCode);

		reqCodeTx = new JTextField();
		reqCodeTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqCodeTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqCodeTx.setForeground(Color.BLACK);
		reqCodeTx.setColumns(10);
		reqCodeTx.setBackground(Color.WHITE);
		reqCodeTx.setBounds(382, 95, 122, 33);
		reqInfoPanel_1.add(reqCodeTx);

		JLabel workerListLabel_1_2 = new JLabel("파견계약상세정보");
		workerListLabel_1_2.setForeground(new Color(242, 170, 76));
		workerListLabel_1_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerListLabel_1_2.setBounds(856, 10, 127, 33);
		contManagePanel.add(workerListLabel_1_2);

		JButton applyCheckBtn = new JButton("파견지원자확인");
		applyCheckBtn.setForeground(Color.WHITE);
		applyCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		applyCheckBtn.setBackground(new Color(16, 24, 32));
		applyCheckBtn.setBounds(856, 601, 142, 46);
		contManagePanel.add(applyCheckBtn);

		JButton reqContjoinBtn = new JButton("조회");
		reqContjoinBtn.setForeground(new Color(255, 255, 255));
		reqContjoinBtn.setBackground(new Color(16, 24, 32));

		reqContjoinBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContjoinBtn.setBounds(755, 16, 89, 23);
		contManagePanel.add(reqContjoinBtn);

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
		sheetListPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetListPanel.setBounds(12, 46, 830, 621);
		sheetManagerPanel.add(sheetListPanel);
		
		
		sheetTabel = new JTable(sheetModel);
		sheetTabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		sheetTabel.getColumn("고용계약번호").setCellRenderer(center);
		sheetTabel.getColumn("파견계약번호").setCellRenderer(center);
		sheetTabel.getColumn("계약체결일").setCellRenderer(center);
		sheetTabel.getColumn("명세표번호").setCellRenderer(center);
		
		sheetTabel.setBounds(12, 46, 830, 621);
		sheetListPanel.add(sheetTabel);
		
		JScrollPane sheetScrollPane = new JScrollPane(sheetTabel);
		sheetScrollPane.setBounds(10, 10, 808, 601);
		sheetListPanel.add(sheetScrollPane);


		JLabel sheetCustLabel = new JLabel("정산 발행 정보");
		sheetCustLabel.setForeground(new Color(242, 170, 76));
		sheetCustLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetCustLabel.setBounds(854, 10, 166, 33);
		sheetManagerPanel.add(sheetCustLabel);

		JPanel sheetCustPanel = new JPanel();
		sheetCustPanel.setBackground(new Color(16, 24, 32));
		sheetCustPanel.setForeground(new Color(16, 24, 32));
		sheetCustPanel.setLayout(null);
		sheetCustPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel.setBounds(854, 46, 539, 400);
		sheetManagerPanel.add(sheetCustPanel);
		
		JLabel lblNewLabel = new JLabel("파견계약번호");
		lblNewLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(142, 51, 101, 35);
		sheetCustPanel.add(lblNewLabel);
		
		contCodeTx = new JTextField();
		contCodeTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		contCodeTx.setEditable(false);
		contCodeTx.setHorizontalAlignment(SwingConstants.CENTER);
		contCodeTx.setBounds(255, 51, 162, 35);
		sheetCustPanel.add(contCodeTx);
		contCodeTx.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("파견인력수당");
		lblNewLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(142, 126, 101, 35);
		sheetCustPanel.add(lblNewLabel_1);
		
		workerCostTx = new JTextField();
		workerCostTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerCostTx.setEditable(false);
		workerCostTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerCostTx.setColumns(10);
		workerCostTx.setBounds(255, 126, 162, 35);
		sheetCustPanel.add(workerCostTx);
		
		JLabel lblNewLabel_1_1 = new JLabel("파견수수료");
		lblNewLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(142, 196, 101, 35);
		sheetCustPanel.add(lblNewLabel_1_1);
		
		contFeeTx = new JTextField();
		contFeeTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		contFeeTx.setEditable(false);
		contFeeTx.setHorizontalAlignment(SwingConstants.CENTER);
		contFeeTx.setColumns(10);
		contFeeTx.setBounds(255, 196, 162, 35);
		sheetCustPanel.add(contFeeTx);
		
		JLabel lblNewLabel_1_2 = new JLabel("세금");
		lblNewLabel_1_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(142, 261, 101, 35);
		sheetCustPanel.add(lblNewLabel_1_2);
		
		custTaxTx = new JTextField();
		custTaxTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		custTaxTx.setEditable(false);
		custTaxTx.setHorizontalAlignment(SwingConstants.CENTER);
		custTaxTx.setColumns(10);
		custTaxTx.setBounds(255, 261, 162, 35);
		sheetCustPanel.add(custTaxTx);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("실 정산 금액");
		lblNewLabel_1_2_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setBounds(142, 325, 101, 35);
		sheetCustPanel.add(lblNewLabel_1_2_1);
		
		contTotalTx = new JTextField();
		contTotalTx.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		contTotalTx.setEditable(false);
		contTotalTx.setHorizontalAlignment(SwingConstants.CENTER);
		contTotalTx.setColumns(10);
		contTotalTx.setBounds(255, 325, 162, 35);
		sheetCustPanel.add(contTotalTx);

		JButton sheetCheckBtn = new JButton("정산 명세표 발행");
		sheetCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetCheckBtn.setForeground(new Color(255, 255, 255));
		sheetCheckBtn.setBackground(new Color(16, 24, 32));
		sheetCheckBtn.setBounds(923, 481, 401, 46);
		sheetManagerPanel.add(sheetCheckBtn);
		
		JButton sheetJoinBtn = new JButton("조회");
		sheetJoinBtn.setForeground(Color.WHITE);
		sheetJoinBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetJoinBtn.setBackground(new Color(16, 24, 32));
		sheetJoinBtn.setBounds(753, 16, 89, 23);
		sheetManagerPanel.add(sheetJoinBtn);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(242, 170, 76));
		lblNewLabel_2.setBounds(1280, 14, 113, 25);
		sheetManagerPanel.add(lblNewLabel_2);

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
		payListPanel.setBounds(12, 46, 824, 621);
		payManagerPanel.add(payListPanel);
				
		table = new JTable(priceModel);
		table.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		table.getColumn("고용계약번호").setCellRenderer(center);
		table.getColumn("파견계약번호").setCellRenderer(center);
		table.getColumn("계약체결일").setCellRenderer(center);
		table.getColumn("업체명세서발행번호").setCellRenderer(center);
		table.getColumn("업체명세서발행일").setCellRenderer(center);
		table.getColumn("지급여부").setCellRenderer(center);
		
		table.setBounds(12, 10, 726, 601);
		payListPanel.add(table);
		
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(12, 10, 800, 601);
		payListPanel.add(scrollPane_1);
		
		JPanel payManagerPanel_1 = new JPanel();
		payManagerPanel_1.setLayout(null);
		payManagerPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		payManagerPanel_1.setBackground(new Color(16, 24, 32));
		scrollPane_1.setColumnHeaderView(payManagerPanel_1);
		
		JLabel payListLabel_1 = new JLabel("월별 수당 지급내역");
		payListLabel_1.setForeground(new Color(242, 170, 76));
		payListLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payListLabel_1.setBounds(12, 10, 142, 33);
		payManagerPanel_1.add(payListLabel_1);
		
		JPanel payListPanel_1 = new JPanel();
		payListPanel_1.setLayout(null);
		payListPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		payListPanel_1.setBackground(new Color(16, 24, 32));
		payListPanel_1.setBounds(12, 46, 824, 621);
		payManagerPanel_1.add(payListPanel_1);
		
		table_1 = new JTable((TableModel) null);
		table_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		table_1.setBounds(0, 0, 798, 0);
		payListPanel_1.add(table_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1.setBounds(12, 10, 800, 601);
		payListPanel_1.add(scrollPane_1_1);
		
		JLabel workerPayLabel_1 = new JLabel("인력별 수당 내역");
		workerPayLabel_1.setForeground(new Color(242, 170, 76));
		workerPayLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPayLabel_1.setBounds(848, 10, 119, 33);
		payManagerPanel_1.add(workerPayLabel_1);
		
		JPanel sheetCustPanel_1_1 = new JPanel();
		sheetCustPanel_1_1.setLayout(null);
		sheetCustPanel_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel_1_1.setBackground(new Color(16, 24, 32));
		sheetCustPanel_1_1.setBounds(848, 46, 545, 427);
		payManagerPanel_1.add(sheetCustPanel_1_1);
		
		JLabel lblNewLabel_3_4 = new JLabel("파견 지원자 번호");
		lblNewLabel_3_4.setForeground(Color.WHITE);
		lblNewLabel_3_4.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_4.setBounds(125, 62, 119, 33);
		sheetCustPanel_1_1.add(lblNewLabel_3_4);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(271, 62, 176, 33);
		sheetCustPanel_1_1.add(textField);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("재계약 추가 수당");
		lblNewLabel_3_1_2.setForeground(Color.WHITE);
		lblNewLabel_3_1_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_1_2.setBounds(125, 195, 119, 33);
		sheetCustPanel_1_1.add(lblNewLabel_3_1_2);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(271, 195, 176, 33);
		sheetCustPanel_1_1.add(textField_1);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("세금");
		lblNewLabel_3_2_1.setForeground(Color.WHITE);
		lblNewLabel_3_2_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_2_1.setBounds(125, 259, 119, 33);
		sheetCustPanel_1_1.add(lblNewLabel_3_2_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(271, 259, 176, 33);
		sheetCustPanel_1_1.add(textField_2);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("실 수당 정산금액");
		lblNewLabel_3_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_3_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_3_1.setBounds(125, 322, 119, 33);
		sheetCustPanel_1_1.add(lblNewLabel_3_3_1);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(271, 322, 176, 33);
		sheetCustPanel_1_1.add(textField_3);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("재계약 추가 수당");
		lblNewLabel_3_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_1_1_1.setBounds(125, 133, 119, 33);
		sheetCustPanel_1_1.add(lblNewLabel_3_1_1_1);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(271, 133, 176, 33);
		sheetCustPanel_1_1.add(textField_4);
		
		JButton payContListCheckBtn_1 = new JButton("파견 지원자 수당 입금 처리");
		payContListCheckBtn_1.setForeground(Color.WHITE);
		payContListCheckBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payContListCheckBtn_1.setBackground(new Color(16, 24, 32));
		payContListCheckBtn_1.setBounds(898, 501, 445, 46);
		payManagerPanel_1.add(payContListCheckBtn_1);
		
		JButton sheetJoinBtn_1_1 = new JButton("조회");
		sheetJoinBtn_1_1.setForeground(Color.WHITE);
		sheetJoinBtn_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetJoinBtn_1_1.setBackground(new Color(16, 24, 32));
		sheetJoinBtn_1_1.setBounds(747, 15, 89, 23);
		payManagerPanel_1.add(sheetJoinBtn_1_1);

		JLabel workerPayLabel = new JLabel("인력별 수당 내역");
		workerPayLabel.setForeground(new Color(242, 170, 76));
		workerPayLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPayLabel.setBounds(848, 10, 119, 33);
		payManagerPanel.add(workerPayLabel);

		JPanel sheetCustPanel_1 = new JPanel();
		sheetCustPanel_1.setBackground(new Color(16, 24, 32));
		sheetCustPanel_1.setLayout(null);
		sheetCustPanel_1
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel_1.setBounds(848, 46, 545, 427);
		payManagerPanel.add(sheetCustPanel_1);
		
		JLabel lblNewLabel_3 = new JLabel("파견 지원자 번호");
		lblNewLabel_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(125, 62, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3);
		
		workerCode_tf = new JTextField();
		workerCode_tf.setHorizontalAlignment(SwingConstants.CENTER);
		workerCode_tf.setEditable(false);
		workerCode_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCode_tf.setBounds(271, 62, 176, 33);
		sheetCustPanel_1.add(workerCode_tf);
		workerCode_tf.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("재계약 추가 수당");
		lblNewLabel_3_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setBounds(125, 195, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_1);
		
		recontPay_tf = new JTextField();
		recontPay_tf.setHorizontalAlignment(SwingConstants.CENTER);
		recontPay_tf.setEditable(false);
		recontPay_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		recontPay_tf.setColumns(10);
		recontPay_tf.setBounds(271, 195, 176, 33);
		sheetCustPanel_1.add(recontPay_tf);
		
		JLabel lblNewLabel_3_2 = new JLabel("세금");
		lblNewLabel_3_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setBounds(125, 259, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_2);
		
		tax_tf = new JTextField();
		tax_tf.setHorizontalAlignment(SwingConstants.CENTER);
		tax_tf.setEditable(false);
		tax_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		tax_tf.setColumns(10);
		tax_tf.setBounds(271, 259, 176, 33);
		sheetCustPanel_1.add(tax_tf);
		
		JLabel lblNewLabel_3_3 = new JLabel("실 수당 정산금액");
		lblNewLabel_3_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setBounds(125, 322, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_3);
		
		total_tf = new JTextField();
		total_tf.setHorizontalAlignment(SwingConstants.CENTER);
		total_tf.setEditable(false);
		total_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		total_tf.setColumns(10);
		total_tf.setBounds(271, 322, 176, 33);
		sheetCustPanel_1.add(total_tf);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("재계약 추가 수당");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_1_1.setBounds(125, 133, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_1_1);
		
		workerPay_tf = new JTextField();
		workerPay_tf.setHorizontalAlignment(SwingConstants.CENTER);
		workerPay_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPay_tf.setEditable(false);
		workerPay_tf.setColumns(10);
		workerPay_tf.setBounds(271, 133, 176, 33);
		sheetCustPanel_1.add(workerPay_tf);

		JButton payContListCheckBtn = new JButton("파견 지원자 수당 입금 처리");
		payContListCheckBtn.setForeground(new Color(255, 255, 255));
		payContListCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payContListCheckBtn.setBackground(new Color(16, 24, 32));
		payContListCheckBtn.setBounds(898, 501, 445, 46);
		payManagerPanel.add(payContListCheckBtn);
		
		JButton sheetJoinBtn_1 = new JButton("조회");
		sheetJoinBtn_1.setForeground(Color.WHITE);
		sheetJoinBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetJoinBtn_1.setBackground(new Color(16, 24, 32));
		sheetJoinBtn_1.setBounds(747, 15, 89, 23);
		payManagerPanel.add(sheetJoinBtn_1);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		mainViewLogoLabel.setBounds(12, 10, 120, 55);
		contentPane.add(mainViewLogoLabel);

		JLabel managerLogoLabel = new JLabel("<관리자>");
		managerLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		managerLogoLabel.setBounds(142, 10, 94, 55);
		contentPane.add(managerLogoLabel);
		
		JButton loginOutBtn = new JButton("로그아웃");
		loginOutBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		loginOutBtn.setBackground(new Color(16, 24, 32));
		loginOutBtn.setForeground(new Color(255, 255, 255));
		loginOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] login = new String[0];
				new LoginView().main(login);;
				dispose();
				
			}
		});
		loginOutBtn.setBounds(1201, 10, 97, 23);
		contentPane.add(loginOutBtn);



		// -----------------------------이벤트 실행부 -------------------------

		// 파견요청 테이블 이벤트
		reqTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = reqTB.getSelectedRow();
				String reqCodeString = String.valueOf(reqTB.getValueAt(row, col));
				int reqCodeInt = Integer.parseInt(reqCodeString);

				int col2 = 6;
				int row2 = reqTB.getSelectedRow();
				String reqContState = String.valueOf(reqTB.getValueAt(row2, col2));

				try {

					reqDao = new ReqDAO();
					ReqVO vo = reqDao.serachReqInfo(reqCodeInt);

					

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
					
					NumberFormat numberFormat = NumberFormat.getInstance();
					totalCostTx.setText(numberFormat.format(vo.getTotalCost())); // 총 파견 비용

					conStateLabel.setText(reqContState);
					reqCodeLabel.setText(reqCodeString);

				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("파견요청테이블 이벤트 " + e2.getMessage());
					e2.printStackTrace();
				}
				
				if(reqContState.equals("미승인")) {
					
					contAccept.setVisible(true);
					contCancel.setVisible(true);
					contReAccept.setVisible(false);
					contRecCancel.setVisible(false);
					supportBtn.setVisible(true);
					reqCancelBtn.setVisible(true);
					
				}else if(reqContState.equals("재요청")) {
					
					contAccept.setVisible(false);
					contCancel.setVisible(false);
					contReAccept.setVisible(true);
					contRecCancel.setVisible(true);
					supportBtn.setVisible(true);
					reqCancelBtn.setVisible(true);
					
				}else if(reqContState.equals("계약승인")){
					
					contAccept.setVisible(false);
					contCancel.setVisible(false);
					contReAccept.setVisible(false);
					contRecCancel.setVisible(false);					
					supportBtn.setVisible(false);
					reqCancelBtn.setVisible(false);
					
				}

			}
		});

		// 파견요청목록에서 선택시 상세정보 출력 해주는 이벤트
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

		// 계약목록 선택시 상세 계약정보 출력 해주는 이벤트
		reqContTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int col = 0; // 컬럼 위치
				int row = reqContTB.getSelectedRow(); // 내가 클릭한 행의 위치

				String vNum = String.valueOf(reqContTB.getValueAt(row, col));
				int result = Integer.parseInt(vNum);

				try {

					//					ReqContDAO reqContDao = new ReqContDAO();
					ReqContVO vo = reqContDao.reqContInfo(result);

					reqContCodeTx.setText(String.valueOf(vo.getReqContCode()));
					reqCodeTx.setText(String.valueOf(vo.getReqCode()));
					reqCheckTx.setText(vo.getReqContCk());
					reqContSdateTx.setText(vo.getReqContSdate().substring(0, 10));
					actualSdateTx.setText(vo.getActualSdate().substring(0, 10));
					actualEdateTx.setText(vo.getActualEdate().substring(0, 10));
					reqContEwhyTx.setText(vo.getReqContEwhy());

					if (vo.getReqContEdate() == null) {
						reqContEdateTx.setText(vo.getReqContEdate());
					} else {
						reqContEdateTx.setText(vo.getReqContEdate().substring(0, 10));
					}

				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
					e2.printStackTrace();
				}

			}
		});



		// 파견요청 조회 버튼 클릭 이벤트 발생시 목록 새로고침 해주는 이벤트
		reqSerchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				reqTB(reqHeader); // 파견요청 목록 출력

			}
		});

		// 파견계약목록 새로고침 버튼 이벤트
		reqContjoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				reqContTB(reqContHeader); // 계약 목록 출력

			}
		});

		// 파견지원자 확인 버튼 이벤트
		applyCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String selectState = reqCheckTx.getText();
				if (selectState.equals("")) {
					JOptionPane.showMessageDialog(null, "파견 요청건을 선택 하여주세요");
				} else {
					String reqContCode = reqContCodeTx.getText();
					new MWorkerSupportListView(0).workerSupportAction(reqContCode,id);
				}

				actualEdateTx.setEditable(false);

			}
		});

		// 사용업체 파견요청건 승인 처리 이벤트
		supportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "승인 처리 하시겠습니까 ?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					String vReqCode = String.valueOf(reqCodeLabel.getText());

					try {

						reqContDao = new ReqContDAO();

						if (contAccept.isSelected()) {
							new MReqContInsertView(0).workerContAction(vReqCode, id, "승인");
						} else if (contCancel.isSelected()) {
							new MReqContInsertView(0).workerContAction(vReqCode, id, "반려");
						} else if (contReAccept.isSelected()) {
							reqContDao.reqReAccept(vReqCode, "승인");
						} else if (contRecCancel.isSelected()) {
							reqContDao.reqReAccept(vReqCode, "반려");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(managerLogoLabel,
								"supportBtn.addActionListener " + e1.getMessage());
					}
					reqTB(reqHeader);
				} else if (result == JOptionPane.NO_OPTION) {

				}

			}
		});
		
		// 업체 정산목록 조회 하는 이벤트
		sheetJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vSheetTB(sheetHeader);
				
			}
		});
		
		// 업체 정산목록 클릭시 상세정보 보기 기능
		sheetTabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int col = 0; // 컬럼 위치
				int row = sheetTabel.getSelectedRow(); // 내가 클릭한 행의 위치

				String vNum = String.valueOf(sheetTabel.getValueAt(row, col));
				int result = Integer.parseInt(vNum);
				
				try {
					
					SheetVO vo = sheetDao.sheetReceipt(result);
					
					NumberFormat numberFormat = NumberFormat.getInstance();
					
					
					lblNewLabel_2.setText(vNum);
					contCodeTx.setText(String.valueOf(vo.getReqContCode()));
					workerCostTx.setText(numberFormat.format(vo.getWorkerCost()));
					contFeeTx.setText(numberFormat.format(vo.getCharge()));
					custTaxTx.setText(numberFormat.format(vo.getCustTax()));
					contTotalTx.setText(numberFormat.format(vo.getSettleCost()));
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				

				
			}
		});
		
		// 정산발행시 업체 정산내역 추가 이벤트
		sheetCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "사용업체 정산 명세표 발행 처리", "확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {

					int workerCode = Integer.parseInt(lblNewLabel_2.getText());

					int col = 3; // 컬럼 위치
					int row = sheetTabel.getSelectedRow(); // 내가 클릭한 행의 위치
					String vNum = String.valueOf(sheetTabel.getValueAt(row, col));

					if (vNum.equals("미발행")) {
						try {

							int state = sheetDao.sheetInput(workerCode);

							if (state > 0) {
								System.out.println("정산서 등록성공");
							} else {
								System.out.println("정산서 등록실패");
							}

						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}

						vSheetTB(sheetHeader);
					} else {
						JOptionPane.showMessageDialog(null, "이미 발행된 건 입니다.");
					}

				} else if (result == JOptionPane.NO_OPTION) {

				}
			}
		});
		
		
		sheetJoinBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				workerPayTB(priceHeader);
				
			}
		});

		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int col = 0; // 컬럼 위치
				int row = table.getSelectedRow(); // 내가 클릭한 행의 위치
				int vNum = Integer.parseInt((String)table.getValueAt(row, col));
				
				
				System.out.println(vNum);
				NumberFormat numberFormat = NumberFormat.getInstance();
				
				try {
					PayVO vo = sheetDao.workerPayOutput(vNum);
					
					workerCode_tf.setText(String.valueOf(vo.getWorkerCode()));
					recontPay_tf.setText(numberFormat.format(vo.getRecontIncen()));
					tax_tf.setText(numberFormat.format(vo.getWorkerTax()));
					total_tf.setText(numberFormat.format(vo.getActualPay()));
					workerPay_tf.setText(numberFormat.format(vo.getPay()));
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		payContListCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int result = JOptionPane.showConfirmDialog(null, "파견 인력 정산 입금처리", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int col = 0; // 컬럼 위치
					int row = table.getSelectedRow(); // 내가 클릭한 행의 위치
					int vNum = Integer.parseInt((String) table.getValueAt(row, col));

					int col1 = 5; // 컬럼 위치
					int row1 = table.getSelectedRow(); // 내가 클릭한 행의 위치
					String vNum1 = String.valueOf(table.getValueAt(row1, col1));

					if (vNum1.equals("미지급")) {

						try {

							int state = sheetDao.workerPayInsert(vNum);

							if (state > 0) {
								System.out.println("수당정보 입력 성공");
							} else {
								System.out.println("수당정보 입력 실패");
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "수당정보입력실패" + e2.getMessage());
						}

						workerPayTB(priceHeader);
					} else {
						JOptionPane.showMessageDialog(null, "이미 처리된 정산건입니다.");
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}



			}

		});
	}
	
	// 메소드 정의부
	// -------------------------------------------------------------------------

	// 파견인력목록 메소드
	void workerListTB(String[] header) {
		try {

			dao = new WorkerDAO();

			ArrayList workerList = dao.serchWorkerInfo(); // 인력목록 ArrayList 형태로 가져오기
			String[][] contentsWorker = super.changeArrayList(workerList, header);

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

			dao = new WorkerDAO();

			ArrayList certiList = dao.serchCertiInfo(code); // 인력목록 ArrayList 형태로 가져오기
			String[][] certiContents = super.changeArrayList(certiList, header);

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
	void careerTB(String[] header, String workerCode) {
		try {

			careerDao = new CareerDAO();

			ArrayList careerList = careerDao.careerList(workerCode); // 인력목록 ArrayList 형태로 가져오기
			String[][] reqContContents = super.changeArrayList(careerList, header);

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

	// 파견요청목록 메소드
	void reqTB(String[] header) {
		try {

			reqDao = new ReqDAO();

			ArrayList reqList = reqDao.reqList(); // 인력목록 ArrayList 형태로 가져오기
			String[][] reqContents = super.changeArrayList(reqList, header);

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

	// 계약목록 메소드
	void reqContTB(String[] header) {
		// TODO: 계약목록 출력 메소드
		try {

			reqContDao = new ReqContDAO();

			ArrayList reqContList = reqContDao.reqContListManger(); // 인력목록 ArrayList 형태로 가져오기
			String[][] reqContContents = super.changeArrayList(reqContList, header);

			reqContModel.setNumRows(0);

			for (int i = 0; i < reqContContents.length; i++) {
				reqContModel.addRow(reqContContents[i]);
			}

		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "계약목록오류 : " + e1.getMessage());
		}
	}
	
	// 업체 정산목록 메소드
	void vSheetTB(String[] header) {
		// TODO: 계약목록 출력 메소드
		try {
			
			sheetDao = new SheetDAO();
			
			ArrayList sheetList = sheetDao.sheetArrayList(); // 인력목록 ArrayList 형태로 가져오기
			String[][] sheetContents = super.changeArrayList(sheetList, header);			
			
			sheetModel.setNumRows(0);
			
			for (int i = 0; i < sheetContents.length; i++) {
				sheetModel.addRow(sheetContents[i]);
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "업체정산목록오류 : " + e1.getMessage());
		}
	}
	
	// 인력 정산목록 메소드
	void workerPayTB(String[] header) {
		// TODO: 계약목록 출력 메소드
		try {
			
			
			sheetDao = new SheetDAO();
			
			ArrayList payList = sheetDao.workerPayArrayList(); // 인력목록 ArrayList 형태로 가져오기
			String[][] payContents = super.changeArrayList(payList, header);			
			
			priceModel.setNumRows(0);
			
			for (int i = 0; i < payContents.length; i++) {
				priceModel.addRow(payContents[i]);
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "인력수당목록오류 : " + e1.getMessage());
		}
	}

	void codeSearch(String id) {

		try {

			LoginDAO dao = new LoginDAO();
			code = dao.managerCode(id);			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
