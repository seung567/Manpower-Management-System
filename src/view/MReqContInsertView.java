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

import model.ReqDAO;
import model.WorkerDAO;
import model.rec.CustVO;
import model.rec.ReqContVO;
import model.rec.ReqVO;

public class MReqContInsertView extends JFrame {

	private JPanel contentPane;

	private JTextField reqCodeTx;
	private JTextField custNameTx;
	private JTextField contSdateTx;


	private WorkerDAO dao = null;
	private ReqDAO reqDAO = null;
	private String[] contPeriod = { "1년", "3년", "5년" };

	private String mgrID;
	private String workerCode,acceptState;
	private JTextField actualSdateTx;
	private JTextField actualEdateTx;

	/**
	 * Launch the application.
	 */

	// 계약정보 view

	public static void workerContAction(String workerCode, String mgrID, String acceptState) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MReqContInsertView frame = new MReqContInsertView(workerCode, mgrID, acceptState);

					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {

							int result = JOptionPane.showConfirmDialog(frame, "창을 닫으시겠습니까?", "확인",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								frame.dispose();
							} else if (result == JOptionPane.NO_OPTION) {
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

	/**
	 * Create the frame.
	 */

	public MReqContInsertView() {

		this.workerContAction(null, null, null);

	}

	public MReqContInsertView(int numm) {

	}

	public MReqContInsertView(String workerCode, String mgrID, String acceptState) {

		this.workerCode = workerCode;
		this.mgrID = mgrID;
		this.acceptState = acceptState;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 467);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
		// contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

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
		workerContPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel.setBounds(24, 186, 496, 132);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel contSdateLabel = new JLabel("계약시작일");
		contSdateLabel.setForeground(new Color(242, 170, 76));
		contSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contSdateLabel.setBounds(12, 25, 77, 27);
		workerContPanel.add(contSdateLabel);
		
		if(acceptState.equals("승인")) {
			contSdateLabel.setText("계약시작일");
		}else {
			contSdateLabel.setText("임시시작일");
		}

		contSdateTx = new HintTextField("ex) yyyy-mm-dd");
		contSdateTx.setColumns(10);
		contSdateTx.setBounds(118, 23, 116, 30);
		workerContPanel.add(contSdateTx);

		JLabel actualSdateLabel = new JLabel("실근무시작일");
		actualSdateLabel.setForeground(new Color(242, 170, 76));
		actualSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		actualSdateLabel.setBounds(12, 77, 97, 27);
		workerContPanel.add(actualSdateLabel);

		actualSdateTx = new JTextField();
		actualSdateTx.setEditable(false);
		actualSdateTx.setColumns(10);
		actualSdateTx.setBounds(118, 75, 116, 30);
		workerContPanel.add(actualSdateTx);

		JLabel actualEdateLabel = new JLabel("실근무종료일");
		actualEdateLabel.setForeground(new Color(242, 170, 76));
		actualEdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		actualEdateLabel.setBounds(260, 79, 96, 27);
		workerContPanel.add(actualEdateLabel);

		actualEdateTx = new JTextField();
		actualEdateTx.setEditable(false);
		actualEdateTx.setColumns(10);
		actualEdateTx.setBounds(368, 77, 116, 30);
		workerContPanel.add(actualEdateTx);

		JButton actualDateAutoBtn = new JButton("실근무일자계산");
		actualDateAutoBtn.setBackground(new Color(16, 24, 32));
		actualDateAutoBtn.setForeground(new Color(255, 255, 255));
		actualDateAutoBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualDateAutoBtn.setBounds(246, 23, 136, 30);
		workerContPanel.add(actualDateAutoBtn);

		JButton actualDateBtn = new JButton("직접입력");

		actualDateBtn.setBackground(new Color(16, 24, 32));
		actualDateBtn.setForeground(new Color(255, 255, 255));
		actualDateBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		actualDateBtn.setBounds(386, 23, 98, 30);
		workerContPanel.add(actualDateBtn);

		JButton applyBtn = new JButton("계약요청");
		applyBtn.setForeground(new Color(255, 255, 255));
		applyBtn.setBackground(new Color(0, 0, 0));

		applyBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		applyBtn.setBounds(189, 352, 167, 44);
		contentPane.add(applyBtn);

		JPanel workerContPanel_1 = new JPanel();
		workerContPanel_1.setLayout(null);
		workerContPanel_1.setForeground(Color.BLACK);
		workerContPanel_1
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel_1.setBackground(Color.BLACK);
		workerContPanel_1.setBounds(24, 104, 496, 72);
		contentPane.add(workerContPanel_1);

		JLabel reqCodeLabel = new JLabel("요청번호");
		reqCodeLabel.setForeground(new Color(242, 170, 76));
		reqCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqCodeLabel.setBounds(32, 24, 77, 27);
		workerContPanel_1.add(reqCodeLabel);

		reqCodeTx = new JTextField();
		reqCodeTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqCodeTx.setEditable(false);
		reqCodeTx.setColumns(10);
		reqCodeTx.setBounds(118, 22, 116, 30);
		workerContPanel_1.add(reqCodeTx);

		JLabel custNameLabel = new JLabel("업체명");
		custNameLabel.setForeground(new Color(242, 170, 76));
		custNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		custNameLabel.setBounds(260, 25, 77, 27);
		workerContPanel_1.add(custNameLabel);

		custNameTx = new JTextField();
		custNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		custNameTx.setEditable(false);
		custNameTx.setColumns(10);
		custNameTx.setBounds(346, 23, 116, 30);
		workerContPanel_1.add(custNameTx);

//		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
//		Image img = icon.getImage();
//		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);

		actualDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				actualSdateTx.setEditable(true);
				actualEdateTx.setEditable(true);

			}
		});

		// 버튼입력시 실근무일자 자동 계산 버튼
		actualDateAutoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String contSdate = contSdateTx.getText(); // 계약일 가져오기

					// 계약일을 Date 타입으로 변환
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					Date date1 = format.parse(contSdate);

					// 변환된 계약일을 M+1 계산처리
					Calendar cal = Calendar.getInstance();
					cal.setTime(date1);
					cal.add(Calendar.MONTH, 1);
					// ----------------------------------------------------------------------------

					// 예상 근무 종료일 계산하는 부분

					ReqDAO dao = new ReqDAO();

					ReqVO vo = dao.expecEdateGet(workerCode);

					Date date2 = format.parse(vo.getExpecEdate());
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(date2);
					cal2.add(Calendar.MONTH, 1);

					actualSdateTx.setText(df.format(cal.getTime()));
					actualEdateTx.setText(df.format(cal2.getTime()));

				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "날짜 형식을 '년도'-'월'-'일' 에 맞게 입력 하여주세요!" + "\n 예시) 2024-01-18 ");
				}

			}
		});

		// 버튼 클릭시 파견계약 DB 테이블 입력 메소드
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				reqAcceptInsert(workerCode);
				dispose();

			}
		});

		setMain(workerCode);

	}

	// view 실행이 되면 기본 입력이 되는 메소드
	void setMain(String reqCode) {

		try {

			reqDAO = new ReqDAO();
			CustVO vo = reqDAO.custSearch(reqCode);

			ReqVO vo1 = reqDAO.expecEdateGet(reqCode);

			reqCodeTx.setText(reqCode);
			custNameTx.setText(vo.getCustName());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	// 파견계약 승인시 계약 DB 입력 메소드

	void reqAcceptInsert(String reqCode) {

		try {

			reqDAO = new ReqDAO();
			ReqContVO vo = new ReqContVO();

			vo.setReqCode(Integer.parseInt(reqCode)); // 파견 요청번호
			vo.setReqContSdate(contSdateTx.getText()); // 계약체결일
			vo.setActualSdate(actualSdateTx.getText()); // 실 근무 시작일
			vo.setActualEdate(actualEdateTx.getText()); // 실 근무 종료일

			int state = reqDAO.reqContInsert(reqCode, vo, acceptState);

			if (state > 0) {
				System.out.println("정상등록완료");
			} else {
				System.out.println("등록실패");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}