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

	workerDAO dao = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerView frame = new ManagerView();
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
	public ManagerView() {
		String font = "한컴 윤고딕 250";

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
		workerManagePanel.setBackground(new Color(0, 0, 0));
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
		workerListPanel.setBackground(new Color(0, 0, 0));
		workerListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel.setBounds(12, 46, 610, 493);
		workerManagePanel.add(workerListPanel);



		String[] header = {"파견인력번호", "이름", "전화번호", "나이", "경력내용", "경력기간"};
		String[][] contents = {
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
		workerListPanel.setLayout(null);

		DefaultTableModel model;

		model = new DefaultTableModel(contents, header) { // Table 수정 할 수 없게 해주는 inner class
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
		
			}
		};

		JTable workerListTB = new JTable(model);
		workerListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = workerListTB.getSelectedRow();

				String vNum = String.valueOf(workerListTB.getValueAt(row, col));

				try {					
					WorkerVO vo = dao.workerInfoSerch(vNum); // 텍스트 필드 입력 값 가져오기
					
					// 자격증 정보 가져오기
					
					workerNameTx.setText(vo.getWorkerName());
					workerAgeTx.setText(vo.getWorkerAge());
					workerTelTx.setText(vo.getWorkerTel());
					workerEmailTx.setText(vo.getWorkerEmail());
					careerPeriodLabel_value.setText(vo.getCareerPeriod());
					careerDetailTx.setText(vo.getCareerDetail());
					
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		workerListTB.setBounds(12, 50, 586, 433);
		workerListTB.setFont(new Font(font, Font.PLAIN, 12));
		workerListPanel.add(workerListTB);

		JScrollPane scrollPane = new JScrollPane(workerListTB);
		scrollPane.setBounds(10, 10, 588, 473);
		workerListPanel.add(scrollPane);


		JLabel workerInfoLabel_1 = new JLabel("파견인력정보");
		workerInfoLabel_1.setBackground(new Color(242, 170, 76));
		workerInfoLabel_1.setForeground(new Color(242, 170, 76));
		workerInfoLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerInfoLabel_1.setBounds(634, 10, 498, 33);
		workerManagePanel.add(workerInfoLabel_1);

		JPanel workerInfoPanel = new JPanel();
		workerInfoPanel.setBackground(new Color(0, 0, 0));
		workerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerInfoPanel.setBounds(634, 46, 498, 427);
		workerManagePanel.add(workerInfoPanel);
		workerInfoPanel.setLayout(null);

		JLabel workerNameLabel = new JLabel("이름");
		workerNameLabel.setForeground(new Color(242, 170, 76));
		workerNameLabel.setBackground(new Color(242, 170, 76));
		workerNameLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		workerNameLabel.setBounds(28, 21, 84, 31);
		workerInfoPanel.add(workerNameLabel);

		workerNameTx = new JTextField();
		workerNameTx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				workerNameTx.setText("");
				
				
				
				
				
				
			}
		});

		workerNameTx.setText("TEST2");
		workerNameTx.setToolTipText("");
		workerNameTx.setBounds(102, 21, 107, 31);
		workerInfoPanel.add(workerNameTx);
		workerNameTx.setColumns(10);

		JLabel workerTelLabel = new JLabel("연락처");
		workerTelLabel.setForeground(new Color(242, 170, 76));
		workerTelLabel.setBackground(new Color(242, 170, 76));
		workerTelLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		workerTelLabel.setBounds(28, 103, 84, 31);
		workerInfoPanel.add(workerTelLabel);

		workerTelTx = new JTextField();
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(102, 103, 107, 31);
		workerInfoPanel.add(workerTelTx);

		JLabel workerAgeLabel = new JLabel("나이");
		workerAgeLabel.setForeground(new Color(242, 170, 76));
		workerAgeLabel.setBackground(new Color(242, 170, 76));
		workerAgeLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		workerAgeLabel.setBounds(28, 62, 84, 31);
		workerInfoPanel.add(workerAgeLabel);

		workerAgeTx = new JTextField();
		workerAgeTx.setColumns(10);
		workerAgeTx.setBounds(102, 62, 107, 31);
		workerInfoPanel.add(workerAgeTx);

		JLabel careerPeriodLabel = new JLabel("경력기간");
		careerPeriodLabel.setForeground(new Color(242, 170, 76));
		careerPeriodLabel.setBackground(new Color(242, 170, 76));
		careerPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		careerPeriodLabel.setBounds(28, 149, 62, 31);
		workerInfoPanel.add(careerPeriodLabel);

		workerEmailTx = new JTextField();
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(290, 103, 180, 31);
		workerInfoPanel.add(workerEmailTx);

		JLabel certiLabel = new JLabel("취득 자격증");
		certiLabel.setForeground(new Color(242, 170, 76));
		certiLabel.setBackground(new Color(242, 170, 76));
		certiLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
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
		certiTB.setBounds(47, 10, 309, 144);
		certiTB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.add(certiTB);

		JScrollPane certiTBscrollPane = new JScrollPane(certiTB);
		certiTBscrollPane.setBounds(0, 0, 443, 92);
		certiPanel.add(certiTBscrollPane);

		JLabel careerPeriodLabel_value = new JLabel("[경력기간]");
		careerPeriodLabel_value.setForeground(new Color(242, 170, 76));
		careerPeriodLabel_value.setBackground(new Color(242, 170, 76));
		careerPeriodLabel_value.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		careerPeriodLabel_value.setBounds(102, 149, 84, 31);
		workerInfoPanel.add(careerPeriodLabel_value);

		JLabel workerEmailLabel = new JLabel("이메일");
		workerEmailLabel.setForeground(new Color(242, 170, 76));
		workerEmailLabel.setBackground(new Color(242, 170, 76));
		workerEmailLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		workerEmailLabel.setBounds(221, 103, 67, 31);
		workerInfoPanel.add(workerEmailLabel);

		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setForeground(new Color(242, 170, 76));
		careerDetailLabel.setBackground(new Color(242, 170, 76));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 16));
		careerDetailLabel.setBounds(28, 190, 67, 31);
		workerInfoPanel.add(careerDetailLabel);

		careerDetailTx = new JTextField();
		careerDetailTx.setColumns(10);
		careerDetailTx.setBounds(28, 219, 443, 56);
		workerInfoPanel.add(careerDetailTx);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"지형조사 및 공학", "구조물 설계 및 강도 분석", "건축 디자인 및 시공 기술", "건축 자재 및 기술적 특성 이해", "종합적 프로젝트 관리", "안전 관리 및 품질관리", "공정 설계 및 자동화 기술", "환경 보전 및 대기 정화 기술", "프로그래밍 언어 및 기술 습득", "시스템 관리 및 유지보수", "소프트웨어 개발 라이프사이클 이해", "알고리즘 및 문제 해결 능력", "데이터베이스 설계 및 관리", "온라인 정보 시스템 구축", "교육 방법론 및 교수 기술", "교육정책 및 컨설팅 능력", "프로젝트 관리 및 계획 능력", "의료기기 설계 및 개발", "소재 및 섬유 기술 이해", "의류 제작 기술", "자동차 부품 설계 및 제작", "품질 관리 및 테스트", "전자 제품 제조 기술", "재무 분석", "세무 및 법률 이해", "전문 지식 및 분석 능력", "의사소통 능력", "법류 지식 및 해석 능력"}));
		comboBox.setBounds(281, 10, 162, 42);
		workerInfoPanel.add(comboBox);

		JButton workerReqInfoBtn = new JButton("계약정보확인");
		workerReqInfoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerReqInfoBtn.setBackground(new Color(0, 0, 0));
		workerReqInfoBtn.setForeground(new Color(255, 255, 255));
		workerReqInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new WorkerContInfoView().Action();
				
			}
		});

		workerReqInfoBtn.setBounds(663, 483, 142, 46);
		workerManagePanel.add(workerReqInfoBtn);

		JButton workerVisatBtn = new JButton("비자정보등록");
		workerVisatBtn.setBackground(new Color(0, 0, 0));
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
		workerInsertBtn.setBackground(new Color(0, 0, 0));
		workerInsertBtn.setBounds(963, 483, 142, 46);
		workerManagePanel.add(workerInsertBtn);

		// 파견요청관리 탭 메인
		JPanel reqManagePanel = new JPanel();
		reqManagePanel.setLayout(null);
		reqManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견요청관리", null, reqManagePanel, null);

		JLabel reqListLabel = new JLabel("파견요청목록");
		reqListLabel.setFont(new Font(font, Font.BOLD, 15));
		reqListLabel.setBounds(12, 10, 113, 33);
		reqManagePanel.add(reqListLabel);

		JPanel reqListPanel = new JPanel();
		reqListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		reqListPanel.setBounds(12, 46, 610, 493);
		reqManagePanel.add(reqListPanel);

		JLabel reqInfoLabel = new JLabel("파견요청정보");
		reqInfoLabel.setFont(new Font("font", Font.BOLD, 15));
		reqInfoLabel.setBounds(634, 10, 498, 33);
		reqManagePanel.add(reqInfoLabel);

		JPanel reqInfoPanel = new JPanel();
		reqInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		reqInfoPanel.setBounds(634, 46, 498, 427);
		reqManagePanel.add(reqInfoPanel);

		JButton reqUpdateBtn = new JButton("파견요청수정");
		reqUpdateBtn.setBounds(663, 483, 142, 46);
		reqManagePanel.add(reqUpdateBtn);

		JButton reqCancelBtn = new JButton("파견요청취소");
		reqCancelBtn.setBounds(813, 483, 142, 46);
		reqManagePanel.add(reqCancelBtn);

		JButton reqContentBtn = new JButton("파견요청승인");
		reqContentBtn.setForeground(Color.BLACK);
		reqContentBtn.setBackground(Color.WHITE);
		reqContentBtn.setBounds(963, 483, 142, 46);
		reqManagePanel.add(reqContentBtn);

		JPanel contManagePanel = new JPanel();
		contManagePanel.setLayout(null);
		contManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견계약관리", null, contManagePanel, null);

		JLabel contListLabel = new JLabel("파견계약목록");
		contListLabel.setFont(new Font("font", Font.BOLD, 15));
		contListLabel.setBounds(12, 10, 113, 33);
		contManagePanel.add(contListLabel);

		JPanel contListPanel = new JPanel();
		contListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contListPanel.setBounds(12, 46, 610, 493);
		contManagePanel.add(contListPanel);

		JLabel contInfoLabael = new JLabel("파견계약정보");
		contInfoLabael.setFont(new Font(font, Font.BOLD, 15));
		contInfoLabael.setBounds(634, 10, 498, 33);
		contManagePanel.add(contInfoLabael);

		JPanel contInfoPanel = new JPanel();
		contInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contInfoPanel.setBounds(634, 46, 498, 427);
		contManagePanel.add(contInfoPanel);

		JButton contApprovalBtn = new JButton("파견계약승인");
		contApprovalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ContContractView().Action();
			}
		});

		contApprovalBtn.setBounds(813, 483, 142, 46);
		contManagePanel.add(contApprovalBtn);

		JButton contCancelBtn = new JButton("파견계약반려");
		contCancelBtn.setForeground(Color.BLACK);
		contCancelBtn.setBackground(Color.WHITE);
		contCancelBtn.setBounds(963, 483, 142, 46);
		contManagePanel.add(contCancelBtn);


		// 정산관리
		JPanel sheetManagerPanel = new JPanel();
		sheetManagerPanel.setLayout(null);
		sheetManagerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("정산관리", null, sheetManagerPanel, null);

		JLabel sheetListLabel = new JLabel("월별 정산 내역");
		sheetListLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		sheetListLabel.setBounds(12, 10, 113, 33);
		sheetManagerPanel.add(sheetListLabel);

		JPanel sheetListPanel = new JPanel();
		sheetListPanel.setLayout(null);
		sheetListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sheetListPanel.setBounds(12, 46, 610, 493);
		sheetManagerPanel.add(sheetListPanel);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(10, 10, 588, 473);
		sheetListPanel.add(scrollPane_1);

		JLabel sheetCustLabel = new JLabel("업체별 정산 내역");
		sheetCustLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		sheetCustLabel.setBounds(634, 10, 498, 33);
		sheetManagerPanel.add(sheetCustLabel);

		JPanel sheetCustPanel = new JPanel();
		sheetCustPanel.setLayout(null);
		sheetCustPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sheetCustPanel.setBounds(634, 46, 498, 427);
		sheetManagerPanel.add(sheetCustPanel);

		JScrollPane scrollPane_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1.setBounds(12, 10, 474, 407);
		sheetCustPanel.add(scrollPane_1_1);

		JButton sheetInfoBtn = new JButton("정산 상세 내역정보");
		sheetInfoBtn.setBounds(813, 483, 142, 46);
		sheetManagerPanel.add(sheetInfoBtn);

		JButton sheetCheckBtn = new JButton("입금확인");
		sheetCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		sheetCheckBtn.setForeground(new Color(242, 170, 76));
		sheetCheckBtn.setBackground(new Color(0, 0, 0));
		sheetCheckBtn.setBounds(963, 483, 142, 46);
		sheetManagerPanel.add(sheetCheckBtn);

		JPanel payManagerPanel = new JPanel();
		payManagerPanel.setLayout(null);
		payManagerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("수당관리", null, payManagerPanel, null);

		JLabel payListLabel = new JLabel("월별 수당 지급내역");
		payListLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		payListLabel.setBounds(12, 10, 142, 33);
		payManagerPanel.add(payListLabel);

		JPanel payListPanel = new JPanel();
		payListPanel.setLayout(null);
		payListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		payListPanel.setBounds(12, 46, 610, 493);
		payManagerPanel.add(payListPanel);

		JScrollPane scrollPane_1_2 = new JScrollPane((Component) null);
		scrollPane_1_2.setBounds(10, 10, 588, 473);
		payListPanel.add(scrollPane_1_2);

		JLabel workerPayLabel = new JLabel("인력별 수당 내역");
		workerPayLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerPayLabel.setBounds(634, 10, 498, 33);
		payManagerPanel.add(workerPayLabel);

		JPanel sheetCustPanel_1 = new JPanel();
		sheetCustPanel_1.setLayout(null);
		sheetCustPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sheetCustPanel_1.setBounds(634, 46, 498, 427);
		payManagerPanel.add(sheetCustPanel_1);

		JScrollPane scrollPane_1_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1_1.setBounds(12, 10, 474, 407);
		sheetCustPanel_1.add(scrollPane_1_1_1);

		JButton payContListCheckBtn = new JButton("계약내역확인");
		payContListCheckBtn.setForeground(new Color(242, 170, 76));
		payContListCheckBtn.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		payContListCheckBtn.setBackground(Color.BLACK);
		payContListCheckBtn.setBounds(990, 483, 142, 46);
		payManagerPanel.add(payContListCheckBtn);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setFont(new Font(font, Font.BOLD, 20));
		mainViewLogoLabel.setBounds(12, 10, 120, 55);
		contentPane.add(mainViewLogoLabel);

		JLabel managerLogoLabel = new JLabel("<관리자>");
		managerLogoLabel.setFont(new Font(font, Font.BOLD, 20));
		managerLogoLabel.setBounds(142, 10, 94, 55);
		contentPane.add(managerLogoLabel);
	}
}
