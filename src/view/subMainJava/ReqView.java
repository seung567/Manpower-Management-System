//package view.subMainJava;
//
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.UIManager;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.EtchedBorder;
//import javax.swing.border.TitledBorder;
//import javax.swing.ImageIcon;
//import javax.swing.JTextField;
//
//public class ReqView extends JFrame {
//
//	private JPanel managerMainPanel;
//	private JTextField textField;
//	private JTextField textField_1;
//	private JTextField textField_2;
//	private JTextField textField_3;
//	private JTextField textField_4;
//	private JTextField textField_5;
//	private JTextField textField_6;
//	private JTextField textField_7;
//	private JTextField textField_8;
//	private JTextField textField_9;
//	private JTextField textField_10;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReqView frame = new ReqView();
//					
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public ReqView() {
//
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// 메인 설정
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1636, 891);
//		managerMainPanel = new JPanel();
//		managerMainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		managerMainPanel.setBackground(new Color(181, 218, 255));
//		managerMainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
//
//		setContentPane(managerMainPanel);
//		managerMainPanel.setLayout(null);
//
//		// 우측상단 "해외파견시스템" 로고
//		JLabel mainLogo = new JLabel("파견요청관리");
//		mainLogo.setBounds(1478, 9, 155, 32);
//		mainLogo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		managerMainPanel.add(mainLogo);
//		
//		//테이블박스 변수명은 workerListTB
//		
//		JPanel workerListPanel = new JPanel();
//		workerListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//		workerListPanel.setBounds(12, 109, 880, 659);
//		managerMainPanel.add(workerListPanel);
//
//		JPanel workerInfoPanel = new JPanel();
//		workerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//		workerInfoPanel.setBounds(904, 109, 704, 659);
//		managerMainPanel.add(workerInfoPanel);
//		workerInfoPanel.setLayout(null);
//		
//		JLabel workerInsertLabel = new JLabel("파견요청정보");
//		workerInsertLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
//		workerInsertLabel.setBounds(12, 10, 112, 27);
//		workerInfoPanel.add(workerInsertLabel);
//		
//		JLabel workerInsertLabel_1 = new JLabel("파견요청번호");
//		workerInsertLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
//		workerInsertLabel_1.setBounds(12, 50, 112, 27);
//		workerInfoPanel.add(workerInsertLabel_1);
//		
//		JLabel workerInsertLabel_1_1 = new JLabel("파견요청업체");
//		workerInsertLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
//		workerInsertLabel_1_1.setBounds(12, 87, 112, 27);
//		workerInfoPanel.add(workerInsertLabel_1_1);
//		
//		JLabel workerInsertLabel_1_1_1 = new JLabel("[파견요청업체]");
//		workerInsertLabel_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
//		workerInsertLabel_1_1_1.setBounds(131, 87, 114, 27);
//		workerInfoPanel.add(workerInsertLabel_1_1_1);
//		
//		JLabel loginLabel = new JLabel("파견요청일정정보");
//		loginLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
//		loginLabel.setBounds(12, 138, 112, 15);
//		workerInfoPanel.add(loginLabel);
//		
//		JPanel loginInfoPanel = new JPanel();
//		loginInfoPanel.setLayout(null);
//		loginInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
//		loginInfoPanel.setBackground(new Color(181, 218, 255));
//		loginInfoPanel.setBounds(12, 156, 451, 42);
//		workerInfoPanel.add(loginInfoPanel);
//		
//		JLabel workerIDLabel = new JLabel("근무시작일");
//		workerIDLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerIDLabel.setBounds(12, 10, 81, 21);
//		loginInfoPanel.add(workerIDLabel);
//		
//		textField = new JTextField();
//		textField.setColumns(10);
//		textField.setBounds(98, 10, 116, 21);
//		loginInfoPanel.add(textField);
//		
//		JLabel workerPWLabel = new JLabel("근무종료일");
//		workerPWLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerPWLabel.setBounds(226, 10, 85, 21);
//		loginInfoPanel.add(workerPWLabel);
//		
//		textField_1 = new JTextField();
//		textField_1.setColumns(10);
//		textField_1.setBounds(311, 10, 116, 21);
//		loginInfoPanel.add(textField_1);
//		
//		JLabel basicLabel = new JLabel("파견지정보");
//		basicLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
//		basicLabel.setBounds(12, 208, 85, 15);
//		workerInfoPanel.add(basicLabel);
//		
//		JPanel basicInfoPanel = new JPanel();
//		basicInfoPanel.setLayout(null);
//		basicInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
//		basicInfoPanel.setBackground(new Color(181, 218, 255));
//		basicInfoPanel.setBounds(12, 227, 451, 74);
//		workerInfoPanel.add(basicInfoPanel);
//		
//		JLabel workerNameLabel = new JLabel("근무국가");
//		workerNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerNameLabel.setBounds(12, 10, 72, 21);
//		basicInfoPanel.add(workerNameLabel);
//		
//		textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		textField_2.setBounds(83, 10, 116, 21);
//		basicInfoPanel.add(textField_2);
//		
//		JLabel workerTelLabel = new JLabel("필수어학수준");
//		workerTelLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerTelLabel.setBounds(232, 10, 90, 21);
//		basicInfoPanel.add(workerTelLabel);
//		
//		textField_3 = new JTextField();
//		textField_3.setColumns(10);
//		textField_3.setBounds(333, 10, 106, 21);
//		basicInfoPanel.add(textField_3);
//		
//		JLabel workerEmailLabel = new JLabel("상세근무지");
//		workerEmailLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerEmailLabel.setBounds(12, 41, 90, 21);
//		basicInfoPanel.add(workerEmailLabel);
//		
//		textField_4 = new JTextField();
//		textField_4.setColumns(10);
//		textField_4.setBounds(103, 41, 336, 21);
//		basicInfoPanel.add(textField_4);
//		
//		JLabel careerInfoLabel = new JLabel("경력정보");
//		careerInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
//		careerInfoLabel.setBounds(12, 322, 70, 15);
//		workerInfoPanel.add(careerInfoLabel);
//		
//		JPanel careerInfoPanel = new JPanel();
//		careerInfoPanel.setLayout(null);
//		careerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
//		careerInfoPanel.setBackground(new Color(181, 218, 255));
//		careerInfoPanel.setBounds(12, 341, 451, 278);
//		workerInfoPanel.add(careerInfoPanel);
//		
//		JLabel careerPeriodLabel = new JLabel("파견업종");
//		careerPeriodLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerPeriodLabel.setBounds(12, 13, 69, 21);
//		careerInfoPanel.add(careerPeriodLabel);
//		
//		textField_5 = new JTextField();
//		textField_5.setColumns(10);
//		textField_5.setBounds(93, 13, 106, 21);
//		careerInfoPanel.add(textField_5);
//		
//		JLabel careerDetailLabel = new JLabel("자격요건");
//		careerDetailLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerDetailLabel.setBounds(12, 75, 69, 21);
//		careerInfoPanel.add(careerDetailLabel);
//		
//		textField_6 = new JTextField();
//		textField_6.setColumns(10);
//		textField_6.setBounds(93, 75, 346, 53);
//		careerInfoPanel.add(textField_6);
//		
//		JLabel skillcodeLabel = new JLabel("요청인원");
//		skillcodeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		skillcodeLabel.setBounds(219, 13, 69, 21);
//		careerInfoPanel.add(skillcodeLabel);
//		
//		textField_7 = new JTextField();
//		textField_7.setColumns(10);
//		textField_7.setBounds(300, 13, 106, 21);
//		careerInfoPanel.add(textField_7);
//		
//		JLabel careerPeriodLabel_1 = new JLabel("성별");
//		careerPeriodLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerPeriodLabel_1.setBounds(12, 44, 69, 21);
//		careerInfoPanel.add(careerPeriodLabel_1);
//		
//		textField_8 = new JTextField();
//		textField_8.setColumns(10);
//		textField_8.setBounds(93, 44, 106, 21);
//		careerInfoPanel.add(textField_8);
//		
//		JLabel careerPeriodLabel_1_1 = new JLabel("연령대");
//		careerPeriodLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerPeriodLabel_1_1.setBounds(219, 44, 69, 21);
//		careerInfoPanel.add(careerPeriodLabel_1_1);
//		
//		textField_9 = new JTextField();
//		textField_9.setColumns(10);
//		textField_9.setBounds(300, 44, 106, 21);
//		careerInfoPanel.add(textField_9);
//		
//		JLabel careerDetailLabel_1 = new JLabel("총 파견 비용");
//		careerDetailLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerDetailLabel_1.setBounds(127, 142, 90, 21);
//		careerInfoPanel.add(careerDetailLabel_1);
//		
//		JLabel careerDetailLabel_1_1 = new JLabel("총 파견 비용");
//		careerDetailLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerDetailLabel_1_1.setBounds(233, 142, 90, 21);
//		careerInfoPanel.add(careerDetailLabel_1_1);
//		
//		JLabel careerDetailLabel_2 = new JLabel("기타 요구사항");
//		careerDetailLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		careerDetailLabel_2.setBounds(12, 171, 106, 21);
//		careerInfoPanel.add(careerDetailLabel_2);
//		
//		textField_10 = new JTextField();
//		textField_10.setColumns(10);
//		textField_10.setBounds(12, 202, 427, 53);
//		careerInfoPanel.add(textField_10);
//
//		JButton workerBtn = new JButton("파견인력관리");
//		workerBtn.setForeground(new Color(0, 0, 0));
//		workerBtn.setBackground(new Color(0, 0, 0));
//		workerBtn.setBounds(147, 36, 142, 46);
//		managerMainPanel.add(workerBtn);
//
//		JButton reqBtn = new JButton("파견요청관리");
//		reqBtn.setBounds(297, 36, 142, 46);
//		managerMainPanel.add(reqBtn);
//
//		JButton reqCountBtn = new JButton("계약관리");
//		reqCountBtn.setBounds(447, 36, 142, 46);
//		managerMainPanel.add(reqCountBtn);
//
//		JButton sheetBtn = new JButton("정산관리");
//		sheetBtn.setBounds(597, 36, 142, 46);
//		managerMainPanel.add(sheetBtn);
//
//		JButton payBtn = new JButton("수당관리");
//		payBtn.setBounds(747, 36, 142, 46);
//		
//		managerMainPanel.add(payBtn);
//		
//		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 2.png");
//		Image img = icon.getImage();
//		Image changeImg = img.getScaledInstance(162, 50, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);
//		
//		JButton workerInsertBtn = new JButton("파견요청승인");
////		workerInsertBtn.setBackground(new Color(255, 255, 255));
////		workerInsertBtn.setForeground(new Color(255, 255, 255));
////		workerInsertBtn.setIcon(changeIcon);
//		workerInsertBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new WorkerInsertView().workerInsertAction();
//			}
//		});
//		workerInsertBtn.setBounds(1466, 784, 142, 46);
//		managerMainPanel.add(workerInsertBtn);
//
//		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
//		mainViewLogoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		mainViewLogoLabel.setBounds(12, -2, 208, 55);
//		managerMainPanel.add(mainViewLogoLabel);
//
//		JLabel workerListLabel = new JLabel("파견요청목록");
//		workerListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerListLabel.setBounds(12, 75, 113, 33);
//		managerMainPanel.add(workerListLabel);
//
//		JLabel workerinfoLabel = new JLabel("파견요청상세정보");
//		workerinfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		workerinfoLabel.setBounds(904, 75, 135, 33);
//		managerMainPanel.add(workerinfoLabel);
//
//		JLabel managerLogoLabel = new JLabel("<관리자>");
//		managerLogoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		managerLogoLabel.setBounds(22, 27, 208, 55);
//		managerMainPanel.add(managerLogoLabel);
//		
//		JButton workerInsertBtn_1 = new JButton("파견요청취소");
//		workerInsertBtn_1.setBounds(1312, 784, 142, 46);
//		managerMainPanel.add(workerInsertBtn_1);
//		
//		JButton workerInsertBtn_1_1 = new JButton("파견요청수정");
//		workerInsertBtn_1_1.setBounds(1158, 784, 142, 46);
//		managerMainPanel.add(workerInsertBtn_1_1);
//	}
//}