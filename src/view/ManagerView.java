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
import javax.swing.table.DefaultTableModel;

import model.LoginDAO;
import model.workerDAO;
import model.rec.WorkerVO;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManagerView extends JFrame {

	private JPanel contentPane;
	private JTextField workerNameTx;
	private JTextField workerTelTx;
	private JTextField workerAgeTx;
	private JTextField workerEmailTx;
	private JTable certiTB;
	private JTextField careerDetailTx;
	private JTable workerListTB;
	private JLabel careerPeriodLabel_value;
	DefaultTableModel model;
	workerDAO dao = null;

	String id;

	/**
	 * Launch the application.
	 */
	public static void managerAction(String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerView frame = new ManagerView(id);
					frame.setVisible(true);
					frame.resize(1200,700);
					frame.setResizable(false);;

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
							}else if(result == JOptionPane.NO_OPTION) {

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
	public ManagerView(String id) {
		this.id = id;
	}
	public ManagerView() {


		//		try {
		//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1189, 692);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane.setLayout(null);

		JTabbedPane managerMainTab = new JTabbedPane(JTabbedPane.TOP);
		managerMainTab.setBorder(null);

		//		managerMainTab.add("1",JPanel(new WorkerView().main(null);));
		managerMainTab.setBounds(12, 65, 1149, 578);
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
		workerListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerListPanel.setBounds(12, 46, 610, 493);
		workerManagePanel.add(workerListPanel);


		JLabel workerInfoLabel_1 = new JLabel("파견인력정보");
		workerInfoLabel_1.setBackground(new Color(242, 170, 76));
		workerInfoLabel_1.setForeground(new Color(242, 170, 76));
		workerInfoLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerInfoLabel_1.setBounds(634, 10, 498, 33);
		workerManagePanel.add(workerInfoLabel_1);

		JPanel workerInfoPanel = new JPanel();
		workerInfoPanel.setForeground(new Color(255, 255, 255));
		workerInfoPanel.setBackground(new Color(16, 24, 32));
		workerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerInfoPanel.setBounds(634, 46, 498, 427);
		workerManagePanel.add(workerInfoPanel);
		workerInfoPanel.setLayout(null);

		JLabel workerNameLabel = new JLabel("이름");
		workerNameLabel.setForeground(new Color(242, 170, 76));
		workerNameLabel.setBackground(new Color(242, 170, 76));
		workerNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerNameLabel.setBounds(28, 21, 84, 31);
		workerInfoPanel.add(workerNameLabel);

		workerNameTx = new JTextField();
		workerNameTx.setEditable(false);
		workerNameTx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				workerNameTx.setText("");




			}
		});
		workerNameTx.setToolTipText("");
		workerNameTx.setBounds(102, 21, 107, 31);
		workerInfoPanel.add(workerNameTx);
		workerNameTx.setColumns(10);

		JLabel workerTelLabel = new JLabel("연락처");
		workerTelLabel.setForeground(new Color(242, 170, 76));
		workerTelLabel.setBackground(new Color(242, 170, 76));
		workerTelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerTelLabel.setBounds(28, 103, 84, 31);
		workerInfoPanel.add(workerTelLabel);

		workerTelTx = new JTextField();
		workerTelTx.setEditable(false);
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(102, 103, 107, 31);
		workerInfoPanel.add(workerTelTx);

		JLabel workerAgeLabel = new JLabel("나이");
		workerAgeLabel.setForeground(new Color(242, 170, 76));
		workerAgeLabel.setBackground(new Color(242, 170, 76));
		workerAgeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerAgeLabel.setBounds(28, 62, 84, 31);
		workerInfoPanel.add(workerAgeLabel);

		workerAgeTx = new JTextField();
		workerAgeTx.setEditable(false);
		workerAgeTx.setColumns(10);
		workerAgeTx.setBounds(102, 62, 107, 31);
		workerInfoPanel.add(workerAgeTx);

		JLabel careerPeriodLabel = new JLabel("경력기간");
		careerPeriodLabel.setForeground(new Color(242, 170, 76));
		careerPeriodLabel.setBackground(new Color(242, 170, 76));
		careerPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerPeriodLabel.setBounds(28, 149, 62, 31);
		workerInfoPanel.add(careerPeriodLabel);

		workerEmailTx = new JTextField();
		workerEmailTx.setEditable(false);
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(290, 103, 180, 31);
		workerInfoPanel.add(workerEmailTx);

		JLabel certiLabel = new JLabel("취득 자격증");
		certiLabel.setForeground(new Color(242, 170, 76));
		certiLabel.setBackground(new Color(242, 170, 76));
		certiLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiLabel.setBounds(28, 273, 84, 31);
		workerInfoPanel.add(certiLabel);

		JPanel certiPanel = new JPanel();
		certiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.setBounds(28, 304, 443, 92);
		workerInfoPanel.add(certiPanel);

		String[] certiHeader = {"관리번호","자격증명","자격번호","취득일","유효기간"};
		String[][] certiContents = {
				{"이정현", "50", "60", "70"},
				{"김영호", "70", "80", "75"},
				{"전수용", "80", "65", "95"},
				{"김진희", "80", "65", "95"},
				{"신정섭", "85", "60", "85"},
				{"김승현", "80", "65", "95"},
				{"김영석", "80", "65", "95"},
				{"이정석", "80", "65", "95"},
				{"이승근", "80", "65", "95"},
		};

		DefaultTableModel modelCerti;

		modelCerti = new DefaultTableModel(certiContents, certiHeader);
		certiPanel.setLayout(null);


		JTable certiTB = new JTable(modelCerti);
		certiTB.setEnabled(false);
		certiTB.setBounds(47, 10, 309, 144);
		certiTB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.add(certiTB);

		JScrollPane certiTBscrollPane = new JScrollPane(certiTB);
		certiTBscrollPane.setBounds(0, 0, 443, 92);
		certiPanel.add(certiTBscrollPane);

		JLabel careerPeriodLabel_value = new JLabel("[경력기간]");
		careerPeriodLabel_value.setForeground(new Color(242, 170, 76));
		careerPeriodLabel_value.setBackground(new Color(242, 170, 76));
		careerPeriodLabel_value.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerPeriodLabel_value.setBounds(102, 149, 84, 31);
		workerInfoPanel.add(careerPeriodLabel_value);

		JLabel workerEmailLabel = new JLabel("이메일");
		workerEmailLabel.setForeground(new Color(242, 170, 76));
		workerEmailLabel.setBackground(new Color(242, 170, 76));
		workerEmailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerEmailLabel.setBounds(221, 103, 67, 31);
		workerInfoPanel.add(workerEmailLabel);

		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setForeground(new Color(242, 170, 76));
		careerDetailLabel.setBackground(new Color(242, 170, 76));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerDetailLabel.setBounds(28, 190, 67, 31);
		workerInfoPanel.add(careerDetailLabel);

		careerDetailTx = new JTextField();
		careerDetailTx.setEditable(false);
		careerDetailTx.setColumns(10);
		careerDetailTx.setBounds(28, 219, 443, 56);
		workerInfoPanel.add(careerDetailTx);
		
		JLabel workerCodeLabel = new JLabel("");
		workerCodeLabel.setForeground(new Color(242, 170, 76));
		workerCodeLabel.setHorizontalAlignment(JLabel.RIGHT);
		workerCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCodeLabel.setBounds(379, 10, 107, 31);
		workerInfoPanel.add(workerCodeLabel);

		JButton workerReqInfoBtn = new JButton("계약정보확인");
		workerReqInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerReqInfoBtn.setBackground(new Color(16, 24, 32));
		workerReqInfoBtn.setForeground(new Color(255, 255, 255));
		workerReqInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codeValue = workerCodeLabel.getText();
				System.out.println(codeValue);
				if(codeValue == "") {
					JOptionPane.showMessageDialog(null, "파견인력을 선택 하여 주세요");
				}else {
					WorkerContInfoView workerCOnt = new WorkerContInfoView(codeValue);
					workerCOnt.Action();
				}

			}
		});

		workerReqInfoBtn.setBounds(663, 483, 142, 46);
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

		workerVisatBtn.setBounds(813, 483, 142, 46);
		workerManagePanel.add(workerVisatBtn);

		JButton workerInsertBtn = new JButton("파견인력등록");
		workerInsertBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new WorkerInsertView().workerInsertAction();

			}
		});




		workerInsertBtn.setForeground(new Color(255, 255, 255));
		workerInsertBtn.setBackground(new Color(16, 24, 32));
		workerInsertBtn.setBounds(963, 483, 142, 46);
		workerManagePanel.add(workerInsertBtn);

		String[] header = {"파견인력번호", "이름", "전화번호", "나이", "경력내용", "경력기간"};
		String[][] contents= {{}};

		model = new DefaultTableModel(contents, header);
		workerListPanel.setLayout(null);

		workerListTB = new JTable(model);
		workerListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int col = 0;
				int row = workerListTB.getSelectedRow();

				String vNum = String.valueOf(workerListTB.getValueAt(row, col));

				try {					
					WorkerVO vo = dao.workerInfoSerch(vNum); // 텍스트 필드 입력 값 가져오

					// 자격증 정보 가져오기
					
					workerCodeLabel.setText(String.valueOf(vo.getWorkerCode()));
					workerNameTx.setText(vo.getWorkerName());
					workerAgeTx.setText(vo.getWorkerAge());
					workerTelTx.setText(vo.getWorkerTel());
					workerEmailTx.setText(vo.getWorkerEmail());
					careerPeriodLabel_value.setText(vo.getCareerPeriod());
					careerDetailTx.setText(vo.getCareerDetail());

				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			}
		});

		workerListTB.setBounds(1, 27, 450, 16);
		workerListTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerListPanel.add(workerListTB);

		JScrollPane scrollPane = new JScrollPane(workerListTB);
		scrollPane.setBounds(10, 10, 588, 473);
		workerListPanel.add(scrollPane);

		workerListTB(header);
		
		JButton workerSerchBtn = new JButton("조회");
		workerSerchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				workerListTB(header);
				
			}
		});


		workerSerchBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerSerchBtn.setBounds(533, 13, 89, 23);
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
		reqListPanel.setBounds(12, 46, 610, 493);
		reqManagePanel.add(reqListPanel);

		JLabel reqInfoLabel = new JLabel("파견요청정보");
		reqInfoLabel.setForeground(new Color(242, 170, 76));
		reqInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqInfoLabel.setBounds(634, 10, 498, 33);
		reqManagePanel.add(reqInfoLabel);

		JPanel reqInfoPanel = new JPanel();
		reqInfoPanel.setBackground(new Color(16, 24, 32));
		reqInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel.setBounds(634, 46, 498, 427);
		reqManagePanel.add(reqInfoPanel);

		JButton reqUpdateBtn = new JButton("파견요청수정");
		reqUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



			}
		});
		reqUpdateBtn.setBackground(new Color(16, 24, 32));
		reqUpdateBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqUpdateBtn.setForeground(new Color(255, 255, 255));
		reqUpdateBtn.setBounds(663, 483, 142, 46);
		reqManagePanel.add(reqUpdateBtn);

		JButton reqCancelBtn = new JButton("파견요청취소");
		reqCancelBtn.setBackground(new Color(16, 24, 32));
		reqCancelBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCancelBtn.setForeground(new Color(255, 255, 255));
		reqCancelBtn.setBounds(813, 483, 142, 46);
		reqManagePanel.add(reqCancelBtn);

		JButton reqContentBtn = new JButton("파견요청승인");
		reqContentBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContentBtn.setForeground(new Color(255, 255, 255));
		reqContentBtn.setBackground(new Color(16, 24, 32));
		reqContentBtn.setBounds(963, 483, 142, 46);
		reqManagePanel.add(reqContentBtn);

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
		contListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		contListPanel.setBounds(12, 46, 610, 493);
		contManagePanel.add(contListPanel);

		JLabel contInfoLabael = new JLabel("파견계약정보");
		contInfoLabael.setForeground(new Color(242, 170, 76));
		contInfoLabael.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contInfoLabael.setBounds(634, 10, 498, 33);
		contManagePanel.add(contInfoLabael);

		JPanel contInfoPanel = new JPanel();
		contInfoPanel.setBackground(new Color(16, 24, 32));
		contInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		contInfoPanel.setBounds(634, 46, 498, 427);
		contManagePanel.add(contInfoPanel);

		JButton contApprovalBtn = new JButton("파견계약승인");
		contApprovalBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contApprovalBtn.setForeground(new Color(255, 255, 255));
		contApprovalBtn.setBackground(new Color(16, 24, 32));
		contApprovalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ContContractView().Action();
			}
		});

		contApprovalBtn.setBounds(836, 483, 142, 46);
		contManagePanel.add(contApprovalBtn);

		JButton contCancelBtn = new JButton("파견계약반려");
		contCancelBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contCancelBtn.setForeground(new Color(255, 255, 255));
		contCancelBtn.setBackground(new Color(16, 24, 32));
		contCancelBtn.setBounds(990, 483, 142, 46);
		contManagePanel.add(contCancelBtn);


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
		sheetListPanel.setBounds(12, 46, 610, 493);
		sheetManagerPanel.add(sheetListPanel);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(10, 10, 588, 473);
		sheetListPanel.add(scrollPane_1);

		JLabel sheetCustLabel = new JLabel("업체별 정산 내역");
		sheetCustLabel.setForeground(new Color(242, 170, 76));
		sheetCustLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetCustLabel.setBounds(634, 10, 498, 33);
		sheetManagerPanel.add(sheetCustLabel);

		JPanel sheetCustPanel = new JPanel();
		sheetCustPanel.setBackground(new Color(16, 24, 32));
		sheetCustPanel.setForeground(new Color(16, 24, 32));
		sheetCustPanel.setLayout(null);
		sheetCustPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel.setBounds(634, 46, 498, 427);
		sheetManagerPanel.add(sheetCustPanel);

		JScrollPane scrollPane_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1.setBounds(12, 10, 474, 407);
		sheetCustPanel.add(scrollPane_1_1);

		JButton sheetInfoBtn = new JButton("정산 상세 내역정보");
		sheetInfoBtn.setForeground(new Color(255, 255, 255));
		sheetInfoBtn.setBackground(new Color(16, 24, 32));
		sheetInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetInfoBtn.setBounds(810, 483, 168, 46);
		sheetManagerPanel.add(sheetInfoBtn);

		JButton sheetCheckBtn = new JButton("입금확인");
		sheetCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetCheckBtn.setForeground(new Color(255, 255, 255));
		sheetCheckBtn.setBackground(new Color(16, 24, 32));
		sheetCheckBtn.setBounds(990, 483, 142, 46);
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
		payListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		payListPanel.setBounds(12, 46, 610, 493);
		payManagerPanel.add(payListPanel);

		JScrollPane scrollPane_1_2 = new JScrollPane((Component) null);
		scrollPane_1_2.setBounds(10, 10, 588, 473);
		payListPanel.add(scrollPane_1_2);

		JLabel workerPayLabel = new JLabel("인력별 수당 내역");
		workerPayLabel.setForeground(new Color(242, 170, 76));
		workerPayLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPayLabel.setBounds(634, 10, 498, 33);
		payManagerPanel.add(workerPayLabel);

		JPanel sheetCustPanel_1 = new JPanel();
		sheetCustPanel_1.setBackground(new Color(16, 24, 32));
		sheetCustPanel_1.setLayout(null);
		sheetCustPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel_1.setBounds(634, 46, 498, 427);
		payManagerPanel.add(sheetCustPanel_1);

		JScrollPane scrollPane_1_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1_1.setBounds(12, 10, 474, 407);
		sheetCustPanel_1.add(scrollPane_1_1_1);

		JButton payContListCheckBtn = new JButton("계약내역확인");
		payContListCheckBtn.setForeground(new Color(255, 255, 255));
		payContListCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payContListCheckBtn.setBackground(new Color(16, 24, 32));
		payContListCheckBtn.setBounds(990, 483, 142, 46);
		payManagerPanel.add(payContListCheckBtn);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		mainViewLogoLabel.setBounds(12, 10, 120, 55);
		contentPane.add(mainViewLogoLabel);

		JLabel managerLogoLabel = new JLabel("<관리자>");
		managerLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		managerLogoLabel.setBounds(142, 10, 94, 55);
		contentPane.add(managerLogoLabel);
	}
	
	public void workerListTB(String[] header) {
		try {
			dao = new workerDAO();
			ArrayList workerList = dao.serchWorkerInfo(); // 인력목록 ArrayList 형태로 가져오기
			String[][] contentsWorker = dao.workerList(workerList,header);
			
			DefaultTableModel modelTest = new DefaultTableModel(contentsWorker, header);
			workerListTB.setModel(modelTest);

		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
	
	public void setIDText(String id) {
		this.id = id;
	}
}
