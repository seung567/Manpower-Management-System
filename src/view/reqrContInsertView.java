package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.ManagerReqDAO;
import model.ManagerWorkerDAO;
import model.rec.CustVO;
import model.rec.ReqVO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class reqrContInsertView extends JFrame {

	private JPanel contentPane;

	
	private JTextField workekNameTx;
	private JTextField workerCodeTx;
	private JTextField contSdateTx;
	private JLabel mgrNameLabel;
	
	private WorkerInsertViewXXXXXX workerInsertViewXXXXXX = null;

	private ManagerWorkerDAO dao = null;
	private ManagerReqDAO reqDAO = null;
	private String[] contPeriod = {"1년","3년","5년"};
	
	private String mgrID;
	private String workerCode;
	private JTextField actualSdateTx;
	private JTextField actualEdateTx;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void workerContAction(String workerCode, String mgrID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reqrContInsertView frame = new reqrContInsertView(workerCode,mgrID);
					
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
	
	public reqrContInsertView() {
		
		this.workerContAction(null,null);
		
	}

	reqrContInsertView(int numm) {

	}

	public reqrContInsertView(String workerCode, String mgrID) {
		
		this.workerCode = workerCode;
		this.mgrID = mgrID;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 467);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("파견요청승인");
		reqContLabel.setForeground(new Color(0, 0, 0));
		reqContLabel.setBounds(203, 12, 138, 34);
		reqContLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 26));
		contentPane.add(reqContLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setForeground(new Color(0, 0, 0));
		workerContPanel.setBackground(new Color(0, 0, 0));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel.setBounds(24, 186, 496, 132);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);
		
		JLabel contSdateLabel = new JLabel("계약시작일");
		contSdateLabel.setForeground(new Color(242, 170, 76));
		contSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contSdateLabel.setBounds(160, 23, 77, 27);
		workerContPanel.add(contSdateLabel);
		
		contSdateTx = new JTextField();
	

		contSdateTx.setColumns(10);
		contSdateTx.setBounds(260, 23, 116, 30);
		workerContPanel.add(contSdateTx);
		
		JLabel actualSdateLabel = new JLabel("계약시작일");
		actualSdateLabel.setForeground(new Color(242, 170, 76));
		actualSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		actualSdateLabel.setBounds(32, 77, 77, 27);
		workerContPanel.add(actualSdateLabel);
		
		actualSdateTx = new JTextField();
		actualSdateTx.setColumns(10);
		actualSdateTx.setBounds(118, 75, 116, 30);
		workerContPanel.add(actualSdateTx);
		
		JLabel actualEdateLabel = new JLabel("계약시작일");
		actualEdateLabel.setForeground(new Color(242, 170, 76));
		actualEdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		actualEdateLabel.setBounds(260, 79, 77, 27);
		workerContPanel.add(actualEdateLabel);
		
		actualEdateTx = new JTextField();
		actualEdateTx.setColumns(10);
		actualEdateTx.setBounds(346, 77, 116, 30);
		workerContPanel.add(actualEdateTx);

		JButton applyBtn = new JButton("계약요청");
		applyBtn.setForeground(new Color(255, 255, 255));
//		applyBtn.setForeground(new Color(80, 88, 108));
//		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.setBackground(new Color(0, 0, 0));
	
		applyBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		applyBtn.setBounds(189, 338, 167, 44);
		contentPane.add(applyBtn);
		
		JLabel mgrLabel = new JLabel("관리자");
		mgrLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		mgrLabel.setBounds(341, 56, 68, 34);
		contentPane.add(mgrLabel);
		
		mgrNameLabel = new JLabel("New label");
		mgrNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
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
		
		JLabel workekNameLabel = new JLabel("요청번호");
		workekNameLabel.setForeground(new Color(242, 170, 76));
		workekNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workekNameLabel.setBounds(32, 24, 77, 27);
		workerContPanel_1.add(workekNameLabel);
		
		workekNameTx = new JTextField();
		workekNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		workekNameTx.setEditable(false);
		workekNameTx.setColumns(10);
		workekNameTx.setBounds(118, 22, 116, 30);
		workerContPanel_1.add(workekNameTx);
		
		JLabel workerCodeLabel = new JLabel("업체명");
		workerCodeLabel.setForeground(new Color(242, 170, 76));
		workerCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerCodeLabel.setBounds(260, 25, 77, 27);
		workerContPanel_1.add(workerCodeLabel);
		
		workerCodeTx = new JTextField();
		workerCodeTx.setHorizontalAlignment(SwingConstants.CENTER);
		workerCodeTx.setEditable(false);
		workerCodeTx.setColumns(10);
		workerCodeTx.setBounds(346, 23, 116, 30);
		workerContPanel_1.add(workerCodeTx);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		// 계약시작일에 정보가 입력 되면 콤보박스 활성화 메소드
		
		// 버튼 클릭시 인력계약 DB 테이블 입력 메소드
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				reqAcceptInsert(workerCode);
				dispose();

			}
		});

		setMain(workerCode);
		
		contSdateTx.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					
					String contSdate = contSdateTx.getText();
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					
					Date date1 = format.parse(contSdate);
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(date1);
					cal.add(Calendar.MONTH, 1);
					
					ManagerReqDAO dao = new ManagerReqDAO();
					dao.expecEdateGet(workerCode);
					
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(date1);
					cal2.add(Calendar.MONTH, 1);
					
					
					System.out.println(df.format(cal.getTime()));
					
					
					actualSdateTx.setText(df.format(cal.getTime()));
					actualEdateTx.setText(contSdate);
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}

				
			}
		});

	}

	// view 실행이 되면 기본 입력이 되는 메소드
	void setMain(String reqCode) {

		try {

			reqDAO = new ManagerReqDAO();
			CustVO vo = reqDAO.custSearch(reqCode);
			
			ReqVO vo1 =  reqDAO.expecEdateGet(reqCode);
			
			
			workekNameTx.setText(reqCode);
			workerCodeTx.setText(vo.getCustName());

			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	// 파견계약 승인시 계약 DB 입력 메소드

	void reqAcceptInsert(String reqCode) {

		try {
			
			reqDAO = new ManagerReqDAO();
			
			String name = workerCodeTx.getText();
			int code = Integer.parseInt(workekNameTx.getText());


//			reqDAO.reqContInsert(reqCode,contSdate);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}