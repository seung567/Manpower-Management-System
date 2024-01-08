package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerView extends JFrame {

	private JPanel contentPane;
	private JTextField workerNameTx;
	private JTextField workerTelTx;
	private JTextField workerAgeTx;
	private JTextField workerEmailTx;
	private JTable certiTB;
	private JTextField careerDetailTx;
	private JTable workerListTB;

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

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1189, 692);
		contentPane = new JPanel();
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
		workerManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견인력관리", null, workerManagePanel, null);
		workerManagePanel.setLayout(null);

		JLabel workerListLabel = new JLabel("파견인력목록");
		workerListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerListLabel.setBounds(12, 10, 113, 33);
		workerManagePanel.add(workerListLabel);

		JPanel workerListPanel = new JPanel();
		workerListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel.setBounds(12, 46, 610, 493);
		workerManagePanel.add(workerListPanel);
	


		String[] header = {"이름", "영어", "수학", "국어"};
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
		workerListTB.setBounds(12, 50, 586, 433);
		workerListTB.setFont(new Font("나눔바른고딕 옛한글", Font.PLAIN, 12));
		workerListPanel.add(workerListTB);
		
		JScrollPane scrollPane = new JScrollPane(workerListTB);
		scrollPane.setBounds(10, 10, 588, 473);
		workerListPanel.add(scrollPane);






		JLabel workerInfoLabel_1 = new JLabel("파견인력정보");
		workerInfoLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerInfoLabel_1.setBounds(634, 10, 498, 33);
		workerManagePanel.add(workerInfoLabel_1);

		JPanel workerInfoPanel = new JPanel();
		workerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerInfoPanel.setBounds(634, 46, 498, 427);
		workerManagePanel.add(workerInfoPanel);
		workerInfoPanel.setLayout(null);

		JLabel workerNameLabel = new JLabel("이름");
		workerNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
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
		workerTelLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerTelLabel.setBounds(28, 103, 84, 31);
		workerInfoPanel.add(workerTelLabel);

		workerTelTx = new JTextField();
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(102, 103, 107, 31);
		workerInfoPanel.add(workerTelTx);

		JLabel workerAgeLabel = new JLabel("나이");
		workerAgeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerAgeLabel.setBounds(28, 62, 84, 31);
		workerInfoPanel.add(workerAgeLabel);

		workerAgeTx = new JTextField();
		workerAgeTx.setColumns(10);
		workerAgeTx.setBounds(102, 62, 107, 31);
		workerInfoPanel.add(workerAgeTx);

		JLabel careerPeriodLabel = new JLabel("경력기간");
		careerPeriodLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		careerPeriodLabel.setBounds(28, 149, 62, 31);
		workerInfoPanel.add(careerPeriodLabel);

		workerEmailTx = new JTextField();
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(290, 103, 180, 31);
		workerInfoPanel.add(workerEmailTx);

		JLabel certiLabel = new JLabel("취득 자격증");
		certiLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		certiLabel.setBounds(28, 273, 84, 31);
		workerInfoPanel.add(certiLabel);

		JPanel certiPanel = new JPanel();
		certiPanel.setBounds(28, 304, 443, 92);
		workerInfoPanel.add(certiPanel);

		certiTB = new JTable();
		certiPanel.add(certiTB);

		JLabel careerPeriodLabel_value = new JLabel("[경력기간]");
		careerPeriodLabel_value.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		careerPeriodLabel_value.setBounds(102, 149, 84, 31);
		workerInfoPanel.add(careerPeriodLabel_value);

		JLabel workerEmailLabel = new JLabel("이메일");
		workerEmailLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerEmailLabel.setBounds(221, 103, 67, 31);
		workerInfoPanel.add(workerEmailLabel);

		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		careerDetailLabel.setBounds(28, 190, 67, 31);
		workerInfoPanel.add(careerDetailLabel);

		careerDetailTx = new JTextField();
		careerDetailTx.setColumns(10);
		careerDetailTx.setBounds(28, 219, 443, 56);
		workerInfoPanel.add(careerDetailTx);

		JButton workerReqInfoBtn = new JButton("계약정보확인");
		workerReqInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WorkerContInfoView().Action();
			}
		});

		workerReqInfoBtn.setBounds(663, 483, 142, 46);
		workerManagePanel.add(workerReqInfoBtn);

		JButton workerVisatBtn = new JButton("비자정보등록");
		workerVisatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new WorkerVisaView().Action();

			}
		});
		workerVisatBtn.setBounds(813, 483, 142, 46);
		workerManagePanel.add(workerVisatBtn);

		JButton workerInsertBtn = new JButton("파견인력등록");
		workerInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new WorkerInsertView().workerInsertAction();

			}
		});
		workerInsertBtn.setForeground(new Color(0, 0, 0));
		workerInsertBtn.setBackground(Color.WHITE);
		workerInsertBtn.setBounds(963, 483, 142, 46);
		workerManagePanel.add(workerInsertBtn);

		// 파견요청관리 탭 메인
		JPanel reqManagePanel = new JPanel();
		reqManagePanel.setLayout(null);
		reqManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견요청관리", null, reqManagePanel, null);

		JLabel reqListLabel = new JLabel("파견요청목록");
		reqListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		reqListLabel.setBounds(12, 10, 113, 33);
		reqManagePanel.add(reqListLabel);

		JPanel reqListPanel = new JPanel();
		reqListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		reqListPanel.setBounds(12, 46, 610, 493);
		reqManagePanel.add(reqListPanel);

		JLabel reqInfoLabel = new JLabel("파견요청정보");
		reqInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
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

		JPanel reqManagePanel_1 = new JPanel();
		reqManagePanel_1.setLayout(null);
		reqManagePanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("파견계약관리", null, reqManagePanel_1, null);

		JLabel contListLabel = new JLabel("파견계약목록");
		contListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		contListLabel.setBounds(12, 10, 113, 33);
		reqManagePanel_1.add(contListLabel);

		JPanel contListPanel = new JPanel();
		contListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contListPanel.setBounds(12, 46, 610, 493);
		reqManagePanel_1.add(contListPanel);

		JLabel contInfoLabael = new JLabel("파견계약정보");
		contInfoLabael.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		contInfoLabael.setBounds(634, 10, 498, 33);
		reqManagePanel_1.add(contInfoLabael);

		JPanel contInfoPanel = new JPanel();
		contInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contInfoPanel.setBounds(634, 46, 498, 427);
		reqManagePanel_1.add(contInfoPanel);

		JButton contApprovalBtn = new JButton("파견계약승인");
		contApprovalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ContContractView().Action();
			}
		});

		contApprovalBtn.setBounds(813, 483, 142, 46);
		reqManagePanel_1.add(contApprovalBtn);

		JButton contCancelBtn = new JButton("파견계약반려");
		contCancelBtn.setForeground(Color.BLACK);
		contCancelBtn.setBackground(Color.WHITE);
		contCancelBtn.setBounds(963, 483, 142, 46);
		reqManagePanel_1.add(contCancelBtn);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mainViewLogoLabel.setBounds(12, 10, 120, 55);
		contentPane.add(mainViewLogoLabel);

		JLabel managerLogoLabel = new JLabel("<관리자>");
		managerLogoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		managerLogoLabel.setBounds(142, 10, 94, 55);
		contentPane.add(managerLogoLabel);
	}
}
