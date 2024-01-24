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
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.table.TableModel;

import model.SheetDAO;
import model.Connect;
import model.LoginDAO;
import model.ReqContDAO;
import model.ReqDAO;
import model.SheetDAO;
import model.rec.CustVO;
import model.rec.ReqContVO;
import model.rec.ReqVO;
import model.rec.SheetVO;
import java.awt.Component;

//================================== 사용업체 메인 (이수정) ==================================//

public class CCustManagerView extends Connect{

	private JPanel contentPane;

	private JTable reqListTB;
	private JTable reqContListTB;
	private JTable sheetListTB;

	private JScrollPane reqListscrollPane;
	private JScrollPane reqContListscrollPane;
	private JScrollPane sheetListscrollPane;

	private JTextField tf_reqContEwhy;
	private JTextField tf_reqContSdate;
	private JTextField tf_reqContEdate;
	private JTextField tf_actualSdate;
	private JTextField tf_reqCode;
	private JTextField tf_reqCode1;
	private JTextField tf_reqContCode1;
	private JTextField tf_actualEdate;
	private JTextField tf_reqContCk;
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
	private JTextField tf_reqDate;
	private JTextField tf_sectorName;
	private JTextField tf_workerNum;
	private JTextField tf_custTax;
	private JTextField tf_settleCost;
	private JTextField tf_workerCost;
	private JTextField tf_reqContCode;
	private JTextField tf_sheetDate;
	private JTextField tf_localLangLevel;
	private JTextField tf_custCode;
	private JTextField tf_charge;
	private JTextField tf_sectorCode;

	private JLabel reqContSdate;

	private DefaultTableCellRenderer center;
	private DefaultTableModel reqModel;
	private DefaultTableModel sheetModel;
	private DefaultTableModel reqContModel;

	private ReqDAO dao = null;
	private ReqContDAO dao2 = null;
	private SheetDAO dao1 = null;

	private String[] reqHeader = { "파견요청번호", "업종명", "필수어학수준", "도시명", "요청인원수", "총파견비용", "관리자승인여부" };
	private String[] reqContHeader = { "파견계약번호", "파견요청번호", "계약성사여부", "계약체결일", "계약만기일", "지원현황" };
	private String[] sheetHeader = { "명세표번호", "파견계약번호", "대금지급일", "실정산금액", "송금여부" };
	private String[][] contents = null;

	private String id;
	private String vNum;
	private String reqContCode;
	private String custName;
	private int custCode;
	private int sheetCode;
	private int total;
	private int modifyNum = 0;

	// ==================================Launch the application ==================================//

	public static void CustManagerAction(String ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CCustManagerView frame = new CCustManagerView(ID);
					frame.setVisible(true);
					frame.setResizable(false);

					// 팝업창 x 버튼 누를시 창만 꺼지게 해주는 명령어
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.addWindowListener(new WindowAdapter() {

						public void windowClosing(WindowEvent e) {
							// super.windowClosing(e);

							int result = JOptionPane.showConfirmDialog(frame, "창을 닫으시겠습니까?", "확인",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								frame.dispose();
							} else {
								frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
							}
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CCustManagerView() {

		this.CustManagerAction("test");

	}

	public CCustManagerView(int num) {

	}

	public CCustManagerView(String ID) {
		this.id = ID;

		codeSearch(ID);

		try {
			System.out.println("파견요청 DB연결 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "파견요청 DB연결 실패 : " + e.getMessage());
		}

		// =========================================UI=========================================//

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 820);
		setLocationRelativeTo(null); // 창 가운데 정렬
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane managerMainTab = new JTabbedPane(JTabbedPane.TOP);
		managerMainTab.setBorder(null);
		managerMainTab.setBounds(12, 64, 1410, 707);
		contentPane.add(managerMainTab);

		// =========================================파견요청=========================================//

		JPanel CustManagePanel = new JPanel();
		CustManagePanel.setForeground(new Color(16, 24, 32));
		CustManagePanel.setLayout(null);
		CustManagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CustManagePanel.setBackground(new Color(16, 24, 32));
		managerMainTab.addTab("파견요청관리", null, CustManagePanel, null);

		JLabel reqListLabel = new JLabel("파견요청목록");
		reqListLabel.setForeground(new Color(242, 170, 76));
		reqListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqListLabel.setBounds(12, 10, 113, 33);
		CustManagePanel.add(reqListLabel);

		JPanel reqListPanel = new JPanel();
		reqListPanel.setForeground(new Color(242, 170, 76));
		reqListPanel.setBackground(new Color(16, 24, 32));
		reqListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqListPanel.setBounds(12, 46, 832, 621);
		CustManagePanel.add(reqListPanel);
		reqListPanel.setLayout(null);
		reqListPanel.setLayout(null);

		reqModel = new DefaultTableModel(contents, reqHeader);
		reqListTB = new JTable(reqModel);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		reqListTB.getColumn("파견요청번호").setPreferredWidth(10);
		reqListTB.getColumn("업종명").setPreferredWidth(50);
		reqListTB.getColumn("필수어학수준").setPreferredWidth(10);
		reqListTB.getColumn("도시명").setPreferredWidth(50);
		reqListTB.getColumn("요청인원수").setPreferredWidth(10);
		reqListTB.getColumn("총파견비용").setPreferredWidth(10);
		reqListTB.getColumn("관리자승인여부").setPreferredWidth(10);

		reqListTB.getColumn("파견요청번호").setCellRenderer(center);
		reqListTB.getColumn("업종명").setCellRenderer(center);
		reqListTB.getColumn("필수어학수준").setCellRenderer(center);
		reqListTB.getColumn("도시명").setCellRenderer(center);
		reqListTB.getColumn("요청인원수").setCellRenderer(center);
		reqListTB.getColumn("총파견비용").setCellRenderer(center);
		reqListTB.getColumn("관리자승인여부").setCellRenderer(center);

		reqListTB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int col = 0;
				int row = reqListTB.getSelectedRow();

				vNum = (String) reqListTB.getValueAt(row, col);
				modifyNum = 0;

				try {

					// vo의 각각의 값을 오른쪽 화면에 출력
					dao = new ReqDAO();
					ReqVO vo1 = dao.reqInfoSearch(vNum);

					tf_sectorCode.setText(String.valueOf(vo1.getSectorCode()));
					tf_reqDate.setText(vo1.getReqDate().substring(0, 10));
					tf_custCode.setText(String.valueOf(vo1.getCustCode()));
					tf_sectorName.setText(vo1.getSectorName()); // 업종명 (코드를 명으로 변환)
					tf_expecSdate.setText(vo1.getExpecSdate().substring(0, 10));
					tf_expecEdate.setText(vo1.getExpecEdate().substring(0, 10));
					tf_reqLangLevel.setText(vo1.getReqLangLevel());
					tf_localLangLevel.setText(vo1.getLocalLangLevel());
					tf_cityName.setText(vo1.getCityName()); // 도시명 (코드를 명으로 변환)
					tf_local.setText(vo1.getLocal());
					tf_langLevel.setText(vo1.getLangLevel()); // 필요언어
					tf_workerNum.setText(String.valueOf(vo1.getWorkerNum()));
					tf_sex.setText(vo1.getSex());
					tf_ageRange.setText(vo1.getAgeRange());
					tf_quali.setText(vo1.getQuali());
					NumberFormat numberFormat = NumberFormat.getInstance();
					String totalCost = numberFormat.format(vo1.getTotalCost());
					tf_totalCost.setText(totalCost);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "요청정보 검색 실패 : " + e1.getMessage());
				}
			}
		});

		reqListTB.setBounds(1, 27, 536, 6);
		reqListPanel.add(reqListTB);

		reqListscrollPane = new JScrollPane(reqListTB);
		reqListscrollPane.setBounds(10, 10, 810, 601);
		reqListPanel.add(reqListscrollPane);

		JLabel reqInfoLabel = new JLabel("파견요청상세정보");
		reqInfoLabel.setForeground(new Color(242, 170, 76));
		reqInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqInfoLabel.setBounds(856, 10, 127, 33);
		CustManagePanel.add(reqInfoLabel);

		JButton reqRegistBtn = new JButton("요청서등록");
		reqRegistBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqRegistBtn.setForeground(new Color(255, 255, 255));
		reqRegistBtn.setBackground(new Color(16, 24, 32));
		reqRegistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CReqView reqView = new CReqView(0);
				CReqView.reqMain(ID);

			}
		});
		reqRegistBtn.setBounds(856, 621, 131, 46);
		CustManagePanel.add(reqRegistBtn);

		JButton reqCorrectBtn = new JButton("요청서수정");
		reqCorrectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견 요청건 수정 및 재요청 확인", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					if (modifyNum == 1) {
						int reqCode = Integer.parseInt(vNum);
						String expecSdate = tf_expecSdate.getText();
						String expecEdate = tf_expecEdate.getText();
						String reqLangLevel = tf_reqLangLevel.getText();
						String localLangLevel = tf_localLangLevel.getText();
						String cityName = tf_cityName.getText();
						String local = tf_local.getText();
						String langLevel = tf_langLevel.getText();
						int workerNum = Integer.parseInt(tf_workerNum.getText());
						String sex = tf_sex.getText();
						String ageRange = tf_ageRange.getText();
						String quali = tf_quali.getText();
						int totalCost = Integer.parseInt(tf_totalCost.getText().replace(",", ""));

						ReqVO vo = new ReqVO(reqCode, expecSdate, expecEdate, reqLangLevel, localLangLevel, cityName, local,
								langLevel, workerNum, sex, ageRange, quali, totalCost);

						try {

							// 파견 재요청 
							int state = dao.reDemand(vo);

							if (state > 0) {
								System.out.println("변경 성공!");
							} else {
								System.out.println("변경 실패!");
							}

							ReqContDAO reqContdao = new ReqContDAO();
							int stateUpdate = reqContdao.stateUpdate(reqCode);

							if (stateUpdate > 0) {
								System.out.println("요청 상태 변경 성공!");
							} else {
								System.out.println("요청 상태 변경 실패!");
							}

							System.out.println("파견 재요청 완료!");

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "파견 재요청 실패 : " + e1.getMessage());
						}
					} else {
						JOptionPane.showConfirmDialog(null, "파견비용을 다시 계산 해주세요.");
					} // end if //

				} else if (result == JOptionPane.NO_OPTION) {
					
				}



			}
		});
		reqCorrectBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCorrectBtn.setBackground(new Color(16, 24, 32));
		reqCorrectBtn.setForeground(new Color(255, 255, 255));
		reqCorrectBtn.setBounds(1164, 621, 113, 46);
		CustManagePanel.add(reqCorrectBtn);

		JButton etcRegistBtn = new JButton("기타요구사항등록");
		etcRegistBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		etcRegistBtn.setForeground(new Color(255, 255, 255));
		etcRegistBtn.setBackground(new Color(16, 24, 32));
		etcRegistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CEtcDemandView etcDemandView = new CEtcDemandView(0);
				CEtcDemandView.EtcDemand(vNum);
			}
		});
		etcRegistBtn.setBounds(999, 621, 153, 46);
		CustManagePanel.add(etcRegistBtn);

		JPanel reqInfoPanel = new JPanel();
		reqInfoPanel.setBackground(new Color(16, 24, 32));
		reqInfoPanel.setLayout(null);
		reqInfoPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel.setBounds(856, 46, 537, 565);
		CustManagePanel.add(reqInfoPanel);

		JLabel reqDate = new JLabel("요청일자");
		reqDate.setForeground(new Color(242, 170, 76));
		reqDate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqDate.setBounds(27, 43, 75, 33);
		reqInfoPanel.add(reqDate);

		JLabel cityName = new JLabel("도시");
		cityName.setForeground(new Color(242, 170, 76));
		cityName.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		cityName.setBounds(280, 217, 75, 21);
		reqInfoPanel.add(cityName);

		JLabel expecSdate = new JLabel("예상근무시작일");
		expecSdate.setForeground(new Color(242, 170, 76));
		expecSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		expecSdate.setBounds(27, 161, 105, 21);
		reqInfoPanel.add(expecSdate);

		JLabel reqLangLevel = new JLabel("필수어학수준");
		reqLangLevel.setForeground(new Color(242, 170, 76));
		reqLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqLangLevel.setBounds(27, 274, 105, 21);
		reqInfoPanel.add(reqLangLevel);

		JLabel ageRange = new JLabel("연령대");
		ageRange.setForeground(new Color(242, 170, 76));
		ageRange.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		ageRange.setBounds(386, 385, 55, 21);
		reqInfoPanel.add(ageRange);

		JLabel local = new JLabel("상세근무장소");
		local.setForeground(new Color(242, 170, 76));
		local.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		local.setBounds(27, 323, 105, 21);
		reqInfoPanel.add(local);

		JLabel sex = new JLabel("성별");
		sex.setForeground(new Color(242, 170, 76));
		sex.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sex.setBounds(27, 385, 43, 21);
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
		expecEdate.setBounds(280, 161, 105, 21);
		reqInfoPanel.add(expecEdate);

		tf_sex = new JTextField();
		tf_sex.setHorizontalAlignment(SwingConstants.CENTER);
		tf_sex.setColumns(10);
		tf_sex.setBounds(144, 380, 67, 33);
		reqInfoPanel.add(tf_sex);

		tf_totalCost = new JTextField();
		tf_totalCost.setEditable(false);
		tf_totalCost.setHorizontalAlignment(SwingConstants.CENTER);
		tf_totalCost.setColumns(10);
		tf_totalCost.setBounds(144, 497, 233, 33);
		reqInfoPanel.add(tf_totalCost);

		tf_quali = new JTextField();
		tf_quali.setHorizontalAlignment(SwingConstants.CENTER);
		tf_quali.setColumns(10);
		tf_quali.setBounds(143, 439, 368, 33);
		reqInfoPanel.add(tf_quali);

		tf_local = new JTextField();
		tf_local.setHorizontalAlignment(SwingConstants.CENTER);
		tf_local.setColumns(10);
		tf_local.setBounds(144, 323, 368, 33);
		reqInfoPanel.add(tf_local);

		JLabel quali = new JLabel("자격요건");
		quali.setForeground(new Color(242, 170, 76));
		quali.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		quali.setBounds(27, 444, 105, 21);
		reqInfoPanel.add(quali);

		JLabel totalCost = new JLabel("총파견비용");
		totalCost.setForeground(new Color(242, 170, 76));
		totalCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		totalCost.setBounds(27, 502, 81, 21);
		reqInfoPanel.add(totalCost);

		tf_ageRange = new JTextField();
		tf_ageRange.setHorizontalAlignment(SwingConstants.CENTER);
		tf_ageRange.setColumns(10);
		tf_ageRange.setBounds(443, 380, 68, 33);
		reqInfoPanel.add(tf_ageRange);

		tf_langLevel = new JTextField();
		tf_langLevel.setHorizontalAlignment(SwingConstants.CENTER);
		tf_langLevel.setColumns(10);
		tf_langLevel.setBounds(144, 212, 122, 33);
		reqInfoPanel.add(tf_langLevel);

		tf_cityName = new JTextField();
		tf_cityName.setHorizontalAlignment(SwingConstants.CENTER);
		tf_cityName.setColumns(10);
		tf_cityName.setBounds(389, 212, 122, 33);
		reqInfoPanel.add(tf_cityName);

		tf_reqLangLevel = new JTextField();
		tf_reqLangLevel.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqLangLevel.setColumns(10);
		tf_reqLangLevel.setBounds(144, 269, 122, 33);
		reqInfoPanel.add(tf_reqLangLevel);

		tf_expecEdate = new JTextField();
		tf_expecEdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_expecEdate.setColumns(10);
		tf_expecEdate.setBounds(389, 156, 122, 33);
		reqInfoPanel.add(tf_expecEdate);

		JLabel sectorName = new JLabel("업종");
		sectorName.setForeground(new Color(242, 170, 76));
		sectorName.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sectorName.setBounds(26, 86, 75, 36);
		reqInfoPanel.add(sectorName);

		JLabel workerNum = new JLabel("요청인원수");
		workerNum.setForeground(new Color(242, 170, 76));
		workerNum.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerNum.setBounds(226, 385, 75, 21);
		reqInfoPanel.add(workerNum);

		tf_expecSdate = new JTextField();
		tf_expecSdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_expecSdate.setColumns(10);
		tf_expecSdate.setBounds(144, 156, 122, 33);
		reqInfoPanel.add(tf_expecSdate);

		tf_reqDate = new JTextField();
		tf_reqDate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqDate.setEditable(false);
		tf_reqDate.setForeground(Color.BLACK);
		tf_reqDate.setBackground(Color.WHITE);
		tf_reqDate.setColumns(10);
		tf_reqDate.setBounds(143, 43, 122, 33);
		reqInfoPanel.add(tf_reqDate);

		tf_sectorName = new JTextField();
		tf_sectorName.setHorizontalAlignment(SwingConstants.CENTER);
		tf_sectorName.setEditable(false);
		tf_sectorName.setColumns(10);
		tf_sectorName.setBounds(186, 89, 325, 33);
		reqInfoPanel.add(tf_sectorName);

		JLabel langLevel = new JLabel("필요언어");
		langLevel.setForeground(new Color(242, 170, 76));
		langLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		langLevel.setBounds(27, 217, 105, 21);
		reqInfoPanel.add(langLevel);

		tf_workerNum = new JTextField();
		tf_workerNum.setHorizontalAlignment(SwingConstants.CENTER);
		tf_workerNum.setColumns(10);
		tf_workerNum.setBounds(313, 380, 61, 33);
		reqInfoPanel.add(tf_workerNum);

		tf_localLangLevel = new JTextField();
		tf_localLangLevel.setHorizontalAlignment(SwingConstants.CENTER);
		tf_localLangLevel.setColumns(10);
		tf_localLangLevel.setBounds(389, 269, 122, 33);
		reqInfoPanel.add(tf_localLangLevel);

		JLabel localLangLevel = new JLabel("현지어학수준");
		localLangLevel.setForeground(new Color(242, 170, 76));
		localLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		localLangLevel.setBounds(280, 266, 105, 36);
		reqInfoPanel.add(localLangLevel);

		JLabel custCodeLable = new JLabel("업체번호");
		custCodeLable.setForeground(new Color(242, 170, 76));
		custCodeLable.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		custCodeLable.setBounds(273, 43, 75, 33);
		reqInfoPanel.add(custCodeLable);

		tf_custCode = new JTextField();
		tf_custCode.setEditable(false);
		tf_custCode.setHorizontalAlignment(SwingConstants.CENTER);
		tf_custCode.setForeground(Color.BLACK);
		tf_custCode.setColumns(10);
		tf_custCode.setBackground(Color.WHITE);
		tf_custCode.setBounds(389, 43, 122, 33);
		reqInfoPanel.add(tf_custCode);

		JButton btnNewButton = new JButton("파견비용계산");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String sectorCode = tf_sectorCode.getText();

					String price = dao.sectorSelected(Integer.parseInt(sectorCode));
					int vPrice = Integer.parseInt(price.replace(",", "").trim());

					NumberFormat numberFormat = NumberFormat.getInstance();
					String result = numberFormat.format(vPrice * Integer.parseInt(tf_workerNum.getText()));

					tf_totalCost.setText(result);

					modifyNum += 1;
					System.out.println(modifyNum);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();

				}

			}
		});
		btnNewButton.setBounds(383, 497, 128, 33);
		reqInfoPanel.add(btnNewButton);

		tf_sectorCode = new JTextField();
		tf_sectorCode.setFont(new Font("굴림", Font.PLAIN, 12));
		tf_sectorCode.setHorizontalAlignment(SwingConstants.CENTER);
		tf_sectorCode.setEditable(false);
		tf_sectorCode.setBounds(144, 89, 36, 33);
		reqInfoPanel.add(tf_sectorCode);
		tf_sectorCode.setColumns(10);

		JButton reqListShowBtn = new JButton("조회");
		reqListShowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reqListTB(reqHeader);
			}
		});
		reqListShowBtn.setForeground(new Color(255, 255, 255));
		reqListShowBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqListShowBtn.setBackground(new Color(16, 24, 32));
		reqListShowBtn.setBounds(754, 9, 90, 34);
		CustManagePanel.add(reqListShowBtn);

		JButton reqDeleteBtn = new JButton("요청서삭제");
		reqDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견 요청 삭제 확인", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					if (reqListTB.getSelectedRow() == -1) { // table.getSelectedRow() -> 선택한 행이 없으면 -1 반환
						JOptionPane.showMessageDialog(null, "테이블에서 삭제할 행을 선택하세요.");
					}

					int reqCode = Integer.parseInt(vNum);

					ReqVO vo = new ReqVO(reqCode);

					try {

						int state = dao.reqDelete(vo);
						reqListTB(reqHeader);
						if (state > 0) {
							System.out.println("작업 성공!");
						} else {
							System.out.println("작업 실패!");
						}
						System.out.println("파견요청 삭제 성공!");
					} catch (Exception e1) {
						// e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "파견요청 삭제 실패 : " + e1.getMessage());
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}



			}
		});

		reqDeleteBtn.setForeground(Color.WHITE);
		reqDeleteBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqDeleteBtn.setBackground(new Color(16, 24, 32));
		reqDeleteBtn.setBounds(1289, 621, 104, 46);
		CustManagePanel.add(reqDeleteBtn);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리 <사용업체>");
		mainViewLogoLabel.setForeground(new Color(16, 24, 32));
		mainViewLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		mainViewLogoLabel.setBounds(12, 10, 220, 55);
		contentPane.add(mainViewLogoLabel);

		// =========================================계약=========================================//

		JPanel CustManagePanel_1 = new JPanel();
		CustManagePanel_1.setLayout(null);
		CustManagePanel_1.setForeground(new Color(16, 24, 32));
		CustManagePanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CustManagePanel_1.setBackground(new Color(16, 24, 32));
		managerMainTab.addTab("파견계약관리", null, CustManagePanel_1, null);

		JLabel reqContListLabel = new JLabel("파견계약목록");
		reqContListLabel.setForeground(new Color(242, 170, 76));
		reqContListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqContListLabel.setBounds(12, 10, 113, 33);
		CustManagePanel_1.add(reqContListLabel);

		JPanel reqContListPanel = new JPanel();
		reqContListPanel.setLayout(null);
		reqContListPanel.setForeground(new Color(242, 170, 76));
		reqContListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqContListPanel.setBackground(new Color(16, 24, 32));
		reqContListPanel.setBounds(12, 46, 832, 621);
		CustManagePanel_1.add(reqContListPanel);

		reqContModel = new DefaultTableModel(contents, reqContHeader);
		reqContListTB = new JTable(reqContModel);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		reqContListTB.getColumn("파견계약번호").setPreferredWidth(10);
		reqContListTB.getColumn("파견요청번호").setPreferredWidth(10);
		reqContListTB.getColumn("계약성사여부").setPreferredWidth(50);
		reqContListTB.getColumn("계약체결일").setPreferredWidth(10);
		reqContListTB.getColumn("계약만기일").setPreferredWidth(10);
		reqContListTB.getColumn("지원현황").setPreferredWidth(10);

		reqContListTB.getColumn("파견계약번호").setCellRenderer(center);
		reqContListTB.getColumn("파견요청번호").setCellRenderer(center);
		reqContListTB.getColumn("계약성사여부").setCellRenderer(center);
		reqContListTB.getColumn("계약체결일").setCellRenderer(center);
		reqContListTB.getColumn("계약만기일").setCellRenderer(center);
		reqContListTB.getColumn("지원현황").setCellRenderer(center);

		reqContListTB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int col = 0;
				int row = reqContListTB.getSelectedRow();
				vNum = (String) reqContListTB.getValueAt(row, col);

				try {

					dao2 = new ReqContDAO();
					ReqContVO vo2 = dao2.reqContInfoSearch(vNum);

					// vo의 각각의 값을 오른쪽 화면에 출력
					tf_reqContCk.setText(vo2.getReqContCk());
					tf_reqContCode1.setText(String.valueOf(vo2.getReqContCode()));
					tf_reqCode1.setText(String.valueOf(vo2.getReqCode()));
					tf_reqContSdate.setText(vo2.getReqContSdate().substring(0, 10));
					tf_reqContEdate.setText(vo2.getReqContEdate());
					tf_actualSdate.setText(vo2.getActualSdate().substring(0, 10));
					tf_actualEdate.setText(vo2.getActualEdate());
					tf_reqContEwhy.setText(vo2.getReqContEwhy());

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "요청정보 검색 실패 : " + e2.getMessage());
				}
			}
		});

		reqContListTB.setBounds(1, 27, 536, 6);
		reqContListPanel.add(reqContListTB);

		reqContListscrollPane = new JScrollPane(reqContListTB);
		reqContListscrollPane.setBounds(12, 10, 808, 601);
		reqContListPanel.add(reqContListscrollPane);

		JLabel reqContInfoLabel = new JLabel("파견계약상세정보");
		reqContInfoLabel.setForeground(new Color(242, 170, 76));
		reqContInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqContInfoLabel.setBounds(856, 10, 127, 33);
		CustManagePanel_1.add(reqContInfoLabel);

		JButton applyCkBtn = new JButton("파견지원자확인");
		applyCkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String state = tf_reqContCode1.getText();

				if (state == null) {
					JOptionPane.showMessageDialog(null, "계약건을 선택하여 주세요.");

				} else {
					CWorkerSupportListView workerSupportListView = new CWorkerSupportListView(0);
					workerSupportListView.workerSupportAction(Integer.parseInt(state), custCode);

				}

			}
		});
		applyCkBtn.setForeground(Color.WHITE);
		applyCkBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		applyCkBtn.setBackground(new Color(16, 24, 32));
		applyCkBtn.setBounds(856, 595, 153, 46);
		CustManagePanel_1.add(applyCkBtn);

		JPanel reqContInfoPanel = new JPanel();
		reqContInfoPanel.setLayout(null);
		reqContInfoPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqContInfoPanel.setBackground(new Color(16, 24, 32));
		reqContInfoPanel.setBounds(856, 46, 537, 522);
		CustManagePanel_1.add(reqContInfoPanel);

		JLabel reqContEdate = new JLabel("계약만기일");
		reqContEdate.setForeground(new Color(242, 170, 76));
		reqContEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEdate.setBounds(280, 159, 75, 21);
		reqContInfoPanel.add(reqContEdate);

		JLabel reqContCode1 = new JLabel("파견계약번호");
		reqContCode1.setForeground(new Color(242, 170, 76));
		reqContCode1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCode1.setBounds(27, 100, 105, 21);
		reqContInfoPanel.add(reqContCode1);

		JLabel actualSdate = new JLabel("실근무시작일");
		actualSdate.setForeground(new Color(242, 170, 76));
		actualSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualSdate.setBounds(27, 224, 105, 21);
		reqContInfoPanel.add(actualSdate);

		JPanel workerManagePanel_1_2_1_2 = new JPanel();
		workerManagePanel_1_2_1_2.setLayout(null);
		workerManagePanel_1_2_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerManagePanel_1_2_1_2.setBounds(376, 95, 1, 1);
		reqContInfoPanel.add(workerManagePanel_1_2_1_2);

		JLabel workerListLabel_2_2_1_2 = new JLabel("파견계약목록");
		workerListLabel_2_2_1_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_2_2_1_2.setBounds(12, 10, 113, 33);
		workerManagePanel_1_2_1_2.add(workerListLabel_2_2_1_2);

		JPanel workerListPanel_2_2_1_2 = new JPanel();
		workerListPanel_2_2_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_2_2_1_2.setBounds(12, 46, 560, 427);
		workerManagePanel_1_2_1_2.add(workerListPanel_2_2_1_2);

		JLabel workerListLabel_1_1_2_1_2 = new JLabel("파견계약상세정보");
		workerListLabel_1_1_2_1_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_1_1_2_1_2.setBounds(595, 10, 127, 33);
		workerManagePanel_1_2_1_2.add(workerListLabel_1_1_2_1_2);

		JPanel workerListPanel_1_1_2_2 = new JPanel();
		workerListPanel_1_1_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_1_1_2_2.setBounds(595, 46, 537, 427);
		workerManagePanel_1_2_1_2.add(workerListPanel_1_1_2_2);

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

		JLabel reqCode1 = new JLabel("파견요청번호");
		reqCode1.setForeground(new Color(242, 170, 76));
		reqCode1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCode1.setBounds(280, 100, 105, 21);
		reqContInfoPanel.add(reqCode1);

		tf_reqContEwhy = new JTextField();
		tf_reqContEwhy.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqContEwhy.setEditable(false);
		tf_reqContEwhy.setColumns(10);
		tf_reqContEwhy.setBounds(144, 282, 367, 198);
		reqContInfoPanel.add(tf_reqContEwhy);

		JLabel reqContEwhy = new JLabel("계약만기사유");
		reqContEwhy.setForeground(new Color(242, 170, 76));
		reqContEwhy.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContEwhy.setBounds(27, 287, 105, 21);
		reqContInfoPanel.add(reqContEwhy);

		tf_reqContSdate = new JTextField();
		tf_reqContSdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqContSdate.setColumns(10);
		tf_reqContSdate.setBounds(144, 154, 122, 33);
		reqContInfoPanel.add(tf_reqContSdate);

		tf_reqContEdate = new JTextField();
		tf_reqContEdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqContEdate.setColumns(10);
		tf_reqContEdate.setBounds(389, 154, 122, 33);
		reqContInfoPanel.add(tf_reqContEdate);

		tf_actualSdate = new JTextField();
		tf_actualSdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_actualSdate.setColumns(10);
		tf_actualSdate.setBounds(144, 219, 122, 33);
		reqContInfoPanel.add(tf_actualSdate);

		tf_reqCode1 = new JTextField();
		tf_reqCode1.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqCode1.setColumns(10);
		tf_reqCode1.setBounds(389, 95, 122, 33);
		reqContInfoPanel.add(tf_reqCode1);

		tf_reqContCode1 = new JTextField();
		tf_reqContCode1.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqContCode1.setColumns(10);
		tf_reqContCode1.setBounds(144, 95, 122, 33);
		reqContInfoPanel.add(tf_reqContCode1);

		reqContSdate = new JLabel("계약체결일");
		reqContSdate.setForeground(new Color(242, 170, 76));
		reqContSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContSdate.setBounds(27, 159, 105, 21);
		reqContInfoPanel.add(reqContSdate);

		tf_actualEdate = new JTextField();
		tf_actualEdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_actualEdate.setColumns(10);
		tf_actualEdate.setBounds(389, 219, 122, 33);
		reqContInfoPanel.add(tf_actualEdate);

		JLabel actualEdate = new JLabel("실근무종료일");
		actualEdate.setForeground(new Color(242, 170, 76));
		actualEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualEdate.setBounds(280, 216, 105, 36);
		reqContInfoPanel.add(actualEdate);

		JLabel reqContCk = new JLabel("계약성사여부");
		reqContCk.setForeground(new Color(242, 170, 76));
		reqContCk.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCk.setBounds(281, 34, 104, 33);
		reqContInfoPanel.add(reqContCk);

		tf_reqContCk = new JTextField();
		tf_reqContCk.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqContCk.setForeground(Color.BLACK);
		tf_reqContCk.setEditable(false);
		tf_reqContCk.setColumns(10);
		tf_reqContCk.setBackground(Color.WHITE);
		tf_reqContCk.setBounds(389, 35, 122, 33);
		reqContInfoPanel.add(tf_reqContCk);

		JButton reqContListShowBtn = new JButton("조회");
		reqContListShowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reqContListTB(reqContHeader);
			}
		});
		reqContListShowBtn.setForeground(Color.WHITE);
		reqContListShowBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContListShowBtn.setBackground(new Color(16, 24, 32));
		reqContListShowBtn.setBounds(754, 9, 90, 34);
		CustManagePanel_1.add(reqContListShowBtn);

		// =========================================정산=========================================//

		JPanel CustManagePanel1 = new JPanel();
		CustManagePanel1.setBackground(new Color(16, 24, 32));
		CustManagePanel1.setLayout(null);
		CustManagePanel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		managerMainTab.addTab("정산관리", null, CustManagePanel1, null);
		managerMainTab.setBackgroundAt(2, new Color(220, 226, 240));

		JLabel sheetListLabel = new JLabel("월별정산목록");
		sheetListLabel.setBackground(new Color(255, 255, 255));
		sheetListLabel.setForeground(new Color(242, 170, 76));
		sheetListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		sheetListLabel.setBounds(12, 10, 113, 33);
		CustManagePanel1.add(sheetListLabel);

		JPanel sheetListPanel = new JPanel();
		sheetListPanel.setForeground(new Color(242, 170, 76));
		sheetListPanel.setBackground(new Color(16, 24, 32));
		sheetListPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetListPanel.setBounds(12, 46, 832, 621);
		CustManagePanel1.add(sheetListPanel);
		sheetListPanel.setLayout(null);
		sheetListPanel.setLayout(null);

		sheetModel = new DefaultTableModel(contents, sheetHeader);
		sheetListTB = new JTable(sheetModel);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		sheetListTB.getColumn("명세표번호").setPreferredWidth(10);
		sheetListTB.getColumn("파견계약번호").setPreferredWidth(10);
		sheetListTB.getColumn("대금지급일").setPreferredWidth(50);
		sheetListTB.getColumn("실정산금액").setPreferredWidth(50);
		sheetListTB.getColumn("송금여부").setPreferredWidth(50);

		sheetListTB.getColumn("명세표번호").setCellRenderer(center);
		sheetListTB.getColumn("파견계약번호").setCellRenderer(center);
		sheetListTB.getColumn("대금지급일").setCellRenderer(center);
		sheetListTB.getColumn("실정산금액").setCellRenderer(center);
		sheetListTB.getColumn("송금여부").setCellRenderer(center);

		sheetListTB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int col = 0;
				int row = sheetListTB.getSelectedRow();		
				vNum = (String) sheetListTB.getValueAt(row, col);

				try {			
					
					dao1 = new SheetDAO();
					SheetVO vo3 = dao1.sheetInfoSearch(vNum);
					
					// vo의 각각의 값을 오른쪽 화면에 출력
					tf_reqContCode.setText(String.valueOf(vo3.getReqContCode())); // 파견계약번호
					tf_sheetDate.setText(vo3.getSheetDate()); // 대금지급일
									
					NumberFormat numberFormat = NumberFormat.getInstance();
					
					String workerCost = numberFormat.format(vo3.getWorkerCost());  // 파견인력수당			
					tf_workerCost.setText(workerCost); 		
					String charge = numberFormat.format(vo3.getCharge()); // 파견요청수수료
					tf_charge.setText(charge); 
					String custTax = numberFormat.format(vo3.getCustTax()); // 세금
					tf_custTax.setText(custTax); 
					String settleCost = numberFormat.format(vo3.getSettleCost());
					tf_settleCost.setText(settleCost); // 실정산금액

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "정산정보 검색 실패 : " + e1.getMessage());
				}
			}
		});

		sheetListTB.setBounds(1, 27, 536, 6);
		sheetListPanel.add(sheetListTB);

		JScrollPane sheetListscrollPane = new JScrollPane(sheetListTB);
		sheetListscrollPane.setBounds(12, 10, 808, 601);
		sheetListPanel.add(sheetListscrollPane);
		
		JLabel sheetInfoLabel = new JLabel("월별정산상세정보");
		sheetInfoLabel.setForeground(new Color(242, 170, 76));
		sheetInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		sheetInfoLabel.setBounds(856, 10, 127, 33);
		CustManagePanel1.add(sheetInfoLabel);

		JButton sheetListShowBtn = new JButton("조회");
		sheetListShowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sheetListTB(sheetHeader);
			}
		});
		sheetListShowBtn.setForeground(new Color(255, 255, 255));
		sheetListShowBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetListShowBtn.setBackground(new Color(16, 24, 32));
		sheetListShowBtn.setBounds(754, 9, 90, 34);
		CustManagePanel1.add(sheetListShowBtn);

		JPanel sheetInfoPanel = new JPanel();
		sheetInfoPanel.setLayout(null);
		sheetInfoPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetInfoPanel.setBackground(new Color(16, 24, 32));
		sheetInfoPanel.setBounds(856, 46, 537, 427);
		CustManagePanel1.add(sheetInfoPanel);

		JLabel reqContCode = new JLabel("파견계약번호");
		reqContCode.setForeground(new Color(242, 170, 76));
		reqContCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContCode.setBounds(14, 50, 97, 33);
		sheetInfoPanel.add(reqContCode);

		JLabel workerCost = new JLabel("파견인력수당");
		workerCost.setForeground(new Color(242, 170, 76));
		workerCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCost.setBounds(14, 166, 105, 21);
		sheetInfoPanel.add(workerCost);

		JLabel custTax = new JLabel("세금");
		custTax.setForeground(new Color(242, 170, 76));
		custTax.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		custTax.setBounds(14, 280, 105, 21);
		sheetInfoPanel.add(custTax);

		JPanel workerManagePanel_1_2_1_1 = new JPanel();
		workerManagePanel_1_2_1_1.setLayout(null);
		workerManagePanel_1_2_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerManagePanel_1_2_1_1.setBounds(376, 95, 1, 1);
		sheetInfoPanel.add(workerManagePanel_1_2_1_1);

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

		JLabel settleCost = new JLabel("실정산금액");
		settleCost.setForeground(new Color(242, 170, 76));
		settleCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		settleCost.setBounds(14, 336, 105, 21);
		sheetInfoPanel.add(settleCost);

		tf_custTax = new JTextField();
		tf_custTax.setHorizontalAlignment(SwingConstants.CENTER);
		tf_custTax.setColumns(10);
		tf_custTax.setBounds(123, 275, 392, 33);
		sheetInfoPanel.add(tf_custTax);

		tf_settleCost = new JTextField();
		tf_settleCost.setHorizontalAlignment(SwingConstants.CENTER);
		tf_settleCost.setColumns(10);
		tf_settleCost.setBounds(123, 331, 392, 33);
		sheetInfoPanel.add(tf_settleCost);

		JLabel sheetDate = new JLabel("대금지급일");
		sheetDate.setForeground(new Color(242, 170, 76));
		sheetDate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetDate.setBounds(14, 103, 97, 36);
		sheetInfoPanel.add(sheetDate);

		tf_workerCost = new JTextField();
		tf_workerCost.setHorizontalAlignment(SwingConstants.CENTER);
		tf_workerCost.setColumns(10);
		tf_workerCost.setBounds(123, 161, 392, 33);
		sheetInfoPanel.add(tf_workerCost);

		tf_reqContCode = new JTextField();
		tf_reqContCode.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqContCode.setForeground(Color.BLACK);
		tf_reqContCode.setColumns(10);
		tf_reqContCode.setBackground(Color.WHITE);
		tf_reqContCode.setBounds(121, 51, 392, 33);
		sheetInfoPanel.add(tf_reqContCode);

		tf_sheetDate = new JTextField();
		tf_sheetDate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_sheetDate.setColumns(10);
		tf_sheetDate.setBounds(123, 106, 392, 33);
		sheetInfoPanel.add(tf_sheetDate);

		JLabel charge = new JLabel("파견요청수수료");
		charge.setForeground(new Color(242, 170, 76));
		charge.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		charge.setBounds(12, 223, 99, 21);
		sheetInfoPanel.add(charge);

		tf_charge = new JTextField();
		tf_charge.setHorizontalAlignment(SwingConstants.CENTER);
		tf_charge.setColumns(10);
		tf_charge.setBounds(123, 218, 392, 33);
		sheetInfoPanel.add(tf_charge);

		JButton sheetDeleteBtn = new JButton("송금확인");
		sheetDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견 비용 송금 처리 확인", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int col = 0;
					int row = sheetListTB.getSelectedRow();
					int vNum = Integer.parseInt((String) sheetListTB.getValueAt(row, col));
					
					String date = tf_sheetDate.getText();
					SheetVO vo = new SheetVO(date, vNum);
							
					try {
						SheetDAO sheetdao = new SheetDAO();
						sheetdao.updateSheetDate(vo);
						System.out.println("대금지급일 등록완료!");
						
						JOptionPane.showMessageDialog(null,  "송금이 완료 되었습니다!");
						
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,  "송금 실패 : " + e1.getMessage());
						e1.printStackTrace();
					}	

				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				
	      		
	      	}	
		});
		
		sheetDeleteBtn.setForeground(Color.WHITE);
		sheetDeleteBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetDeleteBtn.setBackground(new Color(16, 24, 32));
		sheetDeleteBtn.setBounds(1251, 483, 142, 46);
		CustManagePanel1.add(sheetDeleteBtn);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(1247, 10, 175, 33);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(1247, 38, 175, 33);
		contentPane.add(lblNewLabel_1);

		lblNewLabel.setText(String.valueOf(custCode));
		lblNewLabel_1.setText(custName);
		
		JButton loginOutBtn = new JButton("로그아웃");
		loginOutBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		loginOutBtn.setForeground(new Color(255, 255, 255));
		loginOutBtn.setBackground(new Color(16, 24, 32));
		loginOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] login = new String[0];
				new LoginView().main(login);;
				dispose();
				
			}
		});
		loginOutBtn.setBounds(1134, 10, 97, 23);
		contentPane.add(loginOutBtn);

	}

	// =========================================요청 목록조회=========================================//

	public void reqListTB(String[] header) {

		try {
			dao = new ReqDAO();

			ArrayList reqList = dao.searchReqInfo(custCode); // 요청목록 ArrayList 형태로 가져오기
			String[][] contentsReq = super.changeArrayList(reqList, header);

			reqModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsReq.length; i++) {

				reqModel.addRow(contentsReq[i]);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// =========================================계약 목록조회=========================================//

	public void reqContListTB(String[] header2) {

		try {
			dao2 = new ReqContDAO();

			ArrayList reqContList = dao2.searchReqContInfo(custCode); // 요청목록 ArrayList 형태로 가져오기
			String[][] contentsReqCont = super.changeArrayList(reqContList, header2);

			reqContModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsReqCont.length; i++) {

				reqContModel.addRow(contentsReqCont[i]);

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// =========================================정산 목록조회=========================================//

	public void sheetListTB(String[] header1) {

		try {
			dao1 = new SheetDAO();

			ArrayList sheetList = dao1.searchSheetInfo(custCode); // 요청목록 ArrayList 형태로 가져오기
			String[][] contentsSheet = super.changeArrayList(sheetList, header1);

			sheetModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsSheet.length; i++) {

				sheetModel.addRow(contentsSheet[i]);

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// ============================내 업체코드만 조회하는 메소드 (타업체껀 볼 수 없게)===============================//

	void codeSearch(String id) {

		try {

			LoginDAO dao = new LoginDAO();
			CustVO vo = dao.custCode(id);

			custName = vo.getCustName();
			custCode = vo.getCustCode();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
