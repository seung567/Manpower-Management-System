package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.workerDAO;
import model.rec.MgrVO;
import model.rec.WorkerContVO;
import model.rec.WorkerVO;

public class WorkerContInsertView extends JFrame {

	private JPanel contentPane;
	private JTextField contDateTx;
	private JTextField workerContEdateTx;

	
	private JTextField workekNameTx;
	private JTextField workerCodeTx;
	private JTextField contSdateTx;
	private JLabel mgrNameLabel;
	private JComboBox contPeriodCombox;
	
	private WorkerInsertView workerInsertView = null;

	private workerDAO dao = null;
	private String[] contPeriod = {"1년","3년","5년"};
	
	private String mgrID;
	private String workerCode;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void workerContAction(String workerCode, String mgrID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerContInsertView frame = new WorkerContInsertView(workerCode,mgrID);
					
					frame.setVisible(true);
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
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public WorkerContInsertView() {
		
		this.workerContAction(null,null);
		
	}

	WorkerContInsertView(int numm) {

	}

	public WorkerContInsertView(String workerCode, String mgrID) {
		
		this.workerCode = workerCode;
		this.mgrID = mgrID;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 427);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("계약정보등록");
		reqContLabel.setForeground(new Color(0, 0, 0));
		reqContLabel.setBounds(203, 12, 138, 34);
		reqContLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 26));
		contentPane.add(reqContLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setForeground(new Color(0, 0, 0));
		workerContPanel.setBackground(new Color(0, 0, 0));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel.setBounds(24, 186, 496, 119);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel contDateLabel = new JLabel("계약일");
		contDateLabel.setForeground(new Color(242, 170, 76));
		contDateLabel.setBounds(260, 70, 77, 27);
		contDateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContPanel.add(contDateLabel);

		contDateTx = new JTextField();
		contDateTx.setEditable(false);
		contDateTx.setBounds(349, 68, 116, 30);
		contDateTx.setColumns(10);
		workerContPanel.add(contDateTx);

		JLabel workerContEdateTitel = new JLabel("계약만료일");
		workerContEdateTitel.setForeground(new Color(242, 170, 76));
		workerContEdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContEdateTitel.setBounds(29, 70, 77, 27);
		workerContPanel.add(workerContEdateTitel);

		workerContEdateTx = new JTextField();
		workerContEdateTx.setEditable(false);
		workerContEdateTx.setColumns(10);
		workerContEdateTx.setBounds(118, 68, 116, 30);
		workerContPanel.add(workerContEdateTx);
		
		JLabel contPeriodLabel = new JLabel("계약기간");
		contPeriodLabel.setForeground(new Color(242, 170, 76));
		contPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contPeriodLabel.setBounds(260, 23, 77, 27);
		workerContPanel.add(contPeriodLabel);
		
		
		
		
		contPeriodCombox = new JComboBox(contPeriod);
		

		contPeriodCombox.setEnabled(false);
		contPeriodCombox.setBounds(349, 22, 116, 29);
		workerContPanel.add(contPeriodCombox);
		
		JLabel contSdateLabel = new JLabel("계약시작일");
		contSdateLabel.setForeground(new Color(242, 170, 76));
		contSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contSdateLabel.setBounds(29, 23, 77, 27);
		workerContPanel.add(contSdateLabel);
		
		contSdateTx = new JTextField();

		contSdateTx.setColumns(10);
		contSdateTx.setBounds(118, 21, 116, 30);
		workerContPanel.add(contSdateTx);

		JButton applyBtn = new JButton("계약요청");
		applyBtn.setForeground(new Color(255, 255, 255));
//		applyBtn.setForeground(new Color(80, 88, 108));
//		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.setBackground(new Color(0, 0, 0));
	
		applyBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		applyBtn.setBounds(189, 327, 167, 44);
		contentPane.add(applyBtn);
		
		JLabel mgrLabel = new JLabel("관리자");
		mgrLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		mgrLabel.setBounds(341, 56, 68, 34);
		contentPane.add(mgrLabel);
		
		mgrNameLabel = new JLabel("New label");
		mgrNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		mgrNameLabel.setBounds(421, 56, 99, 34);
		contentPane.add(mgrNameLabel);
		
		JPanel workerContPanel_1 = new JPanel();
		workerContPanel_1.setLayout(null);
		workerContPanel_1.setForeground(Color.BLACK);
		workerContPanel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel_1.setBackground(Color.BLACK);
		workerContPanel_1.setBounds(24, 104, 496, 72);
		contentPane.add(workerContPanel_1);
		
		JLabel workekNameLabel = new JLabel("파견지원자");
		workekNameLabel.setForeground(new Color(242, 170, 76));
		workekNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workekNameLabel.setBounds(29, 24, 77, 27);
		workerContPanel_1.add(workekNameLabel);
		
		workekNameTx = new JTextField();
		workekNameTx.setEditable(false);
		workekNameTx.setColumns(10);
		workekNameTx.setBounds(118, 22, 116, 30);
		workerContPanel_1.add(workekNameTx);
		
		JLabel workerCodeLabel = new JLabel("지원자번호");
		workerCodeLabel.setForeground(new Color(242, 170, 76));
		workerCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerCodeLabel.setBounds(260, 25, 77, 27);
		workerContPanel_1.add(workerCodeLabel);
		
		workerCodeTx = new JTextField();
		workerCodeTx.setEditable(false);
		workerCodeTx.setColumns(10);
		workerCodeTx.setBounds(349, 23, 116, 30);
		workerContPanel_1.add(workerCodeTx);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		// 각 텍스트박스에 정보 입력 해주는 메소드
		contPeriodCombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String value = String.valueOf(contPeriodCombox.getSelectedItem()).substring(0);

				int num = 0;

				switch (value) {
				case "1년":
					num = 1;
					break;
				case "3년":
					num = 3;
					break;
				case "5년":
					num = 5;
					break;
				default:
					break;
				}

				Calendar cal = Calendar.getInstance();
				String contDate = contSdateTx.getText();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

				try {

					Date dt = format.parse(contDate);
					cal.setTime(dt);
					cal.add(Calendar.YEAR, num);

					workerContEdateTx.setText(format.format(cal.getTime()));

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		
		// 계약시작일에 정보가 입력 되면 콤보박스 활성화 메소드
		contSdateTx.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				contPeriodCombox.setEnabled(true);
				
				LocalDate now = LocalDate.now();
				contDateTx.setText(String.valueOf(now));
				
				
			}
		});
		
		// 버튼 클릭시 인력계약 DB 테이블 입력 메소드
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				workerContInsert();
			
			}
		});
		setMain();
		
	}
	
	// view 실행이 되면 기본  입력이 되는 메소드
	void setMain() {
		
		try {
			
			dao = new workerDAO();
			WorkerVO vo = dao.workerInfoSerch(workerCode);
			
			workekNameTx.setText(vo.getWorkerName());
			workerCodeTx.setText(String.valueOf(vo.getWorkerCode()));
			
			MgrVO mgrvo = dao.mgrNameSerch(mgrID);
			mgrNameLabel.setText(mgrvo.getMgrName());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	// 인력 계약  DB insert 메소드
	
	void workerContInsert() {
		/*
		 * 				+ "worker_cont_code," // 고용계약번호
				+ "worker_code," // 인력번호
				+ "worker_cont_sdate," // 계약시작일
				+ "worker_cont_edate," // 계약만료일
				+ "recont_num," // 재계약횟수
				+ "cont_period," // 계약기간
				+ "cont_date," // 계약일
				+ "mgr_code " // 관리자 코드
		 */
		try {
			
			dao = new workerDAO();
			
			String sDate = contSdateTx.getText();
			String eDate = workerContEdateTx.getText();
			String period =  contPeriodCombox.getSelectedItem().toString();
			String contDate = contDateTx.getText();
			
			WorkerContVO vo = new WorkerContVO(Integer.parseInt(workerCode),sDate,eDate,period,contDate,mgrID);
			
			int state = dao.workerContInsert(vo,mgrID);
			
			if(state == 0) {
				System.out.println("등록 성공 !");
			}else {
				System.out.println("등록 실패 !");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}