package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.ReqContDAO;
import model.WorkerConfirmDAO;
import model.rec.ReqVO;
import model.rec.SupportVO;

public class CWorkerSupportListView extends JFrame {

	private JPanel supportListPane;
	private JTable supportListTB;
	private JTextField supportNameTx;
	private JTextField supportTelTx;
	private JTextField supportAgeTx;
	private JTextField supportEmailTx;
	private JTable certiTb;

	private JTextField textField;
	private JTable careerTb;

	private DefaultTableModel workerModel, certiModel, careerModel;

	private String[] workerHead = { "고용계약번호", "파견지원번호", "지원일자", "이름", "전화번호", "나이", "지원자확인", "업체확인" };
	private String[] careerHeader = { "업체명", "직무시작일", "직무종료일", "경력내용" };
	private String[] certiHeader = { "관리번호", "자격증명", "자격번호", "취득일", "유효기간" };

	private DefaultTableCellRenderer center;
	private String[][] contents = null;

	private WorkerConfirmDAO supportDAO;
	private int reqContCode;
	private String applyCode;
	private JTextField supportSkillTx;

	/**
	 * Launch the application.
	 */

	public static void workerSupportAction(int reqContCode, int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CWorkerSupportListView frame = new CWorkerSupportListView(reqContCode, id);
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public CWorkerSupportListView() {

		this.workerSupportAction(0, 0);

	}

	public CWorkerSupportListView(int num) {

	}

	public CWorkerSupportListView(int reqContCode, int id) {

		workerModel = new DefaultTableModel(contents, workerHead) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		certiModel = new DefaultTableModel(contents, certiHeader) {
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

		this.reqContCode = reqContCode;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 735);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]

		supportListPane = new JPanel();
		supportListPane.setBackground(new Color(242, 170, 76));
		supportListPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(supportListPane);
		supportListPane.setLayout(null);

		JPanel supportListPanel = new JPanel();
		supportListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		supportListPanel.setBackground(new Color(16, 24, 32));
		supportListPanel.setBounds(12, 101, 941, 585);
		supportListPane.add(supportListPanel);
		supportListPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 917, 565);
		supportListPanel.add(scrollPane);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		supportListTB = new JTable(workerModel);

		supportListTB.getColumn("고용계약번호").setCellRenderer(center);
		supportListTB.getColumn("파견지원번호").setCellRenderer(center);
		supportListTB.getColumn("지원일자").setCellRenderer(center);
		supportListTB.getColumn("이름").setCellRenderer(center);
		supportListTB.getColumn("전화번호").setCellRenderer(center);
		supportListTB.getColumn("나이").setCellRenderer(center);
		supportListTB.getColumn("지원자확인").setCellRenderer(center);
		supportListTB.getColumn("업체확인").setCellRenderer(center);

		scrollPane.setViewportView(supportListTB);

		JLabel supportListLabel = new JLabel("파견 지원자 목록");
		supportListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		supportListLabel.setBounds(12, 65, 143, 26);
		supportListPane.add(supportListLabel);

		JLabel supportInfoLabel = new JLabel("지원자 상세 정보");
		supportInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		supportInfoLabel.setBounds(1157, 65, 143, 26);
		supportListPane.add(supportInfoLabel);

		JLabel lblNewLabel_2 = new JLabel("파견 지원 현황");
		lblNewLabel_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(672, 15, 143, 30);
		supportListPane.add(lblNewLabel_2);

		JButton workerContInsertBtn = new JButton("계약확인");

		workerContInsertBtn.setForeground(new Color(255, 255, 255));
		workerContInsertBtn.setBackground(new Color(16, 24, 32));
		workerContInsertBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerContInsertBtn.setBounds(1300, 634, 176, 52);
		supportListPane.add(workerContInsertBtn);

		JPanel supportInfoPanel = new JPanel();
		supportInfoPanel.setLayout(null);
		supportInfoPanel.setForeground(Color.WHITE);
		supportInfoPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		supportInfoPanel.setBackground(new Color(16, 24, 32));
		supportInfoPanel.setBounds(965, 101, 511, 523);
		supportListPane.add(supportInfoPanel);

		JLabel supportNameLabel = new JLabel("이름");
		supportNameLabel.setForeground(new Color(242, 170, 76));
		supportNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportNameLabel.setBackground(new Color(242, 170, 76));
		supportNameLabel.setBounds(52, 21, 84, 31);
		supportInfoPanel.add(supportNameLabel);

		supportNameTx = new JTextField();
		supportNameTx.setToolTipText("");
		supportNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportNameTx.setEditable(false);
		supportNameTx.setColumns(10);
		supportNameTx.setBounds(126, 21, 107, 31);
		supportInfoPanel.add(supportNameTx);

		JLabel supportTelLabel = new JLabel("연락처");
		supportTelLabel.setForeground(new Color(242, 170, 76));
		supportTelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportTelLabel.setBackground(new Color(242, 170, 76));
		supportTelLabel.setBounds(52, 62, 84, 31);
		supportInfoPanel.add(supportTelLabel);

		supportTelTx = new JTextField();
		supportTelTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportTelTx.setEditable(false);
		supportTelTx.setColumns(10);
		supportTelTx.setBounds(126, 62, 107, 31);
		supportInfoPanel.add(supportTelTx);

		JLabel supportAgeLabel = new JLabel("나이");
		supportAgeLabel.setForeground(new Color(242, 170, 76));
		supportAgeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportAgeLabel.setBackground(new Color(242, 170, 76));
		supportAgeLabel.setBounds(274, 21, 84, 31);
		supportInfoPanel.add(supportAgeLabel);

		supportAgeTx = new JTextField();
		supportAgeTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportAgeTx.setEditable(false);
		supportAgeTx.setColumns(10);
		supportAgeTx.setBounds(348, 21, 107, 31);
		supportInfoPanel.add(supportAgeTx);

		supportEmailTx = new JTextField();
		supportEmailTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportEmailTx.setEditable(false);
		supportEmailTx.setColumns(10);
		supportEmailTx.setBounds(126, 103, 329, 31);
		supportInfoPanel.add(supportEmailTx);

		JLabel certiLabel = new JLabel("취득 자격증");
		certiLabel.setForeground(new Color(242, 170, 76));
		certiLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiLabel.setBackground(new Color(242, 170, 76));
		certiLabel.setBounds(12, 331, 84, 31);
		supportInfoPanel.add(certiLabel);

		JPanel certiPanel = new JPanel();
		certiPanel.setLayout(null);
		certiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.setBounds(12, 360, 487, 138);
		supportInfoPanel.add(certiPanel);

		certiTb = new JTable(certiModel);
		certiTb.setEnabled(false);
		certiTb.setColumnSelectionAllowed(true);
		certiTb.setCellSelectionEnabled(true);
		certiTb.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiTb.setBounds(0, 0, 487, 138);
		certiPanel.add(certiTb);

		JScrollPane certiTBscrollPane = new JScrollPane(certiTb);
		certiTBscrollPane.setBounds(0, 0, 487, 138);
		certiPanel.add(certiTBscrollPane);

		JLabel supportEmailLabel = new JLabel("이메일");
		supportEmailLabel.setForeground(new Color(242, 170, 76));
		supportEmailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportEmailLabel.setBackground(new Color(242, 170, 76));
		supportEmailLabel.setBounds(52, 103, 67, 31);
		supportInfoPanel.add(supportEmailLabel);

		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setForeground(new Color(242, 170, 76));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerDetailLabel.setBackground(new Color(242, 170, 76));
		careerDetailLabel.setBounds(12, 183, 67, 31);
		supportInfoPanel.add(careerDetailLabel);

		JPanel careerPanel = new JPanel();
		careerPanel.setBounds(12, 212, 487, 97);
		supportInfoPanel.add(careerPanel);
		careerPanel.setLayout(null);

		careerTb = new JTable(careerModel);
		careerTb.setBounds(12, 198, 487, 97);
		careerPanel.add(careerTb);

		JScrollPane scrollPane_1 = new JScrollPane(careerTb);
		scrollPane_1.setBounds(0, 0, 487, 97);
		careerPanel.add(scrollPane_1);

		JLabel supportAgeLabel_1 = new JLabel("경력기간");
		supportAgeLabel_1.setForeground(new Color(242, 170, 76));
		supportAgeLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportAgeLabel_1.setBackground(new Color(242, 170, 76));
		supportAgeLabel_1.setBounds(274, 62, 56, 31);
		supportInfoPanel.add(supportAgeLabel_1);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(348, 62, 107, 31);
		supportInfoPanel.add(textField);

		JLabel supportSkillLabel = new JLabel("상세기술");
		supportSkillLabel.setForeground(new Color(242, 170, 76));
		supportSkillLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportSkillLabel.setBackground(new Color(242, 170, 76));
		supportSkillLabel.setBounds(52, 144, 67, 31);
		supportInfoPanel.add(supportSkillLabel);

		supportSkillTx = new JTextField();
		supportSkillTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportSkillTx.setEditable(false);
		supportSkillTx.setColumns(10);
		supportSkillTx.setBounds(126, 144, 329, 31);
		supportInfoPanel.add(supportSkillTx);

		JButton btnNewButton = new JButton("조회");

		btnNewButton.setBackground(new Color(242, 170, 76));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		btnNewButton.setBounds(856, 68, 97, 23);
		supportListPane.add(btnNewButton);

		// ┌───────────────────────────────────이벤트
		// 선언부──────────────────────────────────────────────┐

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				supportTB(workerHead);

			}
		});

		supportListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					int col = 1; // 컬럼 위치
					int row = supportListTB.getSelectedRow(); // 내가 클릭한 행의 위치
					applyCode = String.valueOf(supportListTB.getValueAt(row, col)); // 파견지원번호(Apply)

					SupportVO vo = supportDAO.supportInfoSearch(Integer.parseInt(applyCode));

					// workerCodeLabel.setText(reqCode);
					supportNameTx.setText(vo.getWorkerName());
					supportAgeTx.setText(String.valueOf(vo.getWorkerAge()));
					supportTelTx.setText(vo.getWorkerTel());
					supportEmailTx.setText(vo.getWorkerEmail());
					supportSkillTx.setText(vo.getSkillName());

					long dateValue = (vo.getCareerEdate().getTime() - vo.getCareerSdate().getTime())
							/ (24 * 60 * 60 * 1000);
					int date = (int) dateValue / 365;

					textField.setText(String.valueOf(date) + "년");

					careerTB(careerHeader, applyCode);
					certiListTB(certiHeader, applyCode);

				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}

			}
		});

		workerContInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견 계약건 확인", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// 이수정
					// 계약승인을 눌렀을 경우, 파견계약목록의 업체확인 컬럼 데이터가 null -> 확인으로 바뀌도록

					SupportVO vo = new SupportVO();

					try {

						WorkerConfirmDAO workerConfirmDao = new WorkerConfirmDAO();

						int col = 0; // 컬럼 위치
						int row = supportListTB.getSelectedRow(); // 내가 클릭한 행의 위치
						String workerContCode = String.valueOf(supportListTB.getValueAt(row, col)); // 파견지원번호(Apply)

						int stateUpdate = workerConfirmDao.stateUpdate(workerContCode);

						if (stateUpdate > 0) {
							System.out.println("요청 상태 변경 성공!");
						} else {
							System.out.println("요청 상태 변경 실패!");
						}

						System.out.println("업체 확인 완료!");
						supportTB(workerHead);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "업체 확인 실패 : " + e1.getMessage());
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}


			}

		});

		// └───────────────────────────────────이벤트
		// 선언부──────────────────────────────────────────────┘

		supportTB(workerHead);

	}

	void supportTB(String[] workerHead) {

		try {

			supportDAO = new WorkerConfirmDAO();

			ArrayList supportList = supportDAO.searchSupportList(reqContCode);
			String[][] supportArray = supportDAO.changeArray(supportList, workerHead);

			workerModel.setNumRows(0);

			for (int i = 0; i < supportArray.length; i++) {
				workerModel.addRow(supportArray[i]);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 경력목록 메소드
	void careerTB(String[] header, String workerCode) {
		try {

			ArrayList careerList = supportDAO.careerList(workerCode); // 인력목록 ArrayList 형태로 가져오기
			String[][] reqContContents = supportDAO.changeArray(careerList, header);

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

	// 자격증목록 메소드
	void certiListTB(String[] header, String code) {
		try {

			supportDAO = new WorkerConfirmDAO();

			ArrayList certiList = supportDAO.serchCertiInfo(code); // 인력목록 ArrayList 형태로 가져오기
			String[][] certiContents = supportDAO.changeArray(certiList, header);

			certiModel.setNumRows(0);

			for (int i = 0; i < certiContents.length; i++) {
				certiModel.addRow(certiContents[i]);

			}

		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "자격증정보목록오류 : " + e1.getMessage());
		}
	}
}