package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.WorkerContInfoDAO;
import model.WorkerDAO;
import model.rec.MgrVO;
import model.rec.WorkerContVO;

public class WWorkerAccInfoView extends JFrame {

	private JPanel contentPane;
	private JTextField accNumTx;
	private JTextField accBankTx;
	private JTextField accNameTx;

	// private WorkerInsertView workerInsertView = null;
	private String workerCode;

	private String contCode = null;
	private String id = null;

	private WorkerDAO dao;
	WorkerContVO vo = null;

	/**
	 * Launch the application.
	 */

	// 계약정보 view

	public static void workerContAtion(String contCode, String idText) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					WWorkerAccInfoView frame = new WWorkerAccInfoView(contCode, idText);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setResizable(false);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							// super.windowClosing(e);

							int result = JOptionPane.showConfirmDialog(frame, "창을 닫으시겠습니까?", "확인",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								frame.dispose();
							}else {
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

	// 윈도우 빌더용
	public WWorkerAccInfoView() {
		setTitle("\uACC4\uC88C\uB4F1\uB85D");

		this.workerContAtion("테스트", "테스트");

	}

	// 실행용 생성자 함수
	public WWorkerAccInfoView(int num) {

	}

	// 실행용 메인 생성자 함수
	public WWorkerAccInfoView(String contCode, String idText) {

		this.contCode = contCode;
		this.id = idText;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 322);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("\uACC4\uC88C\uC815\uBCF4 \uB4F1\uB85D");
		reqContLabel.setForeground(new Color(0, 0, 0));
		reqContLabel.setBounds(12, 10, 132, 34);
		reqContLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contentPane.add(reqContLabel);

		JPanel accInfoPanel = new JPanel();
		accInfoPanel.setForeground(new Color(0, 0, 0));
		accInfoPanel.setLayout(null);
		accInfoPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		accInfoPanel.setBackground(new Color(0, 0, 0));
		accInfoPanel.setBounds(24, 54, 496, 128);
		contentPane.add(accInfoPanel);

		JLabel accNumLabel = new JLabel("입금계좌번호");
		accNumLabel.setForeground(new Color(242, 170, 76));
		accNumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accNumLabel.setBounds(28, 70, 108, 27);
		accInfoPanel.add(accNumLabel);

		accNumTx = new JTextField();
		accNumTx.setColumns(10);
		accNumTx.setBounds(124, 68, 340, 30);
		accInfoPanel.add(accNumTx);

		JLabel accBankLabel = new JLabel("입금은행명");
		accBankLabel.setForeground(new Color(242, 170, 76));
		accBankLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accBankLabel.setBounds(259, 25, 77, 27);
		accInfoPanel.add(accBankLabel);

		accBankTx = new JTextField();
		accBankTx.setColumns(10);
		accBankTx.setBounds(348, 23, 116, 30);
		accInfoPanel.add(accBankTx);

		JLabel accNameLabel = new JLabel("예금주");
		accNameLabel.setForeground(new Color(242, 170, 76));
		accNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accNameLabel.setBackground(new Color(255, 128, 128));
		accNameLabel.setBounds(28, 25, 70, 27);
		accInfoPanel.add(accNameLabel);

		accNameTx = new JTextField();
		accNameTx.setEditable(false);
		accNameTx.setColumns(10);
		accNameTx.setBounds(124, 23, 116, 30);
		accInfoPanel.add(accNameTx);

		// 인력 계좌정보 등록(수락버튼)
		JButton acceptBtn = new JButton("\uB4F1\uB85D");
		acceptBtn.setForeground(new Color(255, 255, 255));
		acceptBtn.setBackground(new Color(0, 0, 0));
		acceptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String accName = accNameTx.getText();
				String accBank = accBankTx.getText();
				String accNum = accNumTx.getText();

				try {
					WorkerContInfoDAO dao = new WorkerContInfoDAO();
					vo = new WorkerContVO(accName, accBank, accNum);
					dao.workerAccUpdate(vo);
					JOptionPane.showMessageDialog(null, "계좌정보가 등록되었습니다.");
					
				} catch (Exception ev) {
					// TODO: handle exception
					ev.printStackTrace();
				}
			}
		});
		acceptBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		acceptBtn.setBounds(167, 206, 210, 48);
		contentPane.add(acceptBtn);

		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);

		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);

		Early(contCode, id);
		accAddname(id);
		
	}

	//완료
	public void accAddname(String ID) {

		try {

			WorkerContInfoDAO dao = new WorkerContInfoDAO();
			String vo = dao.workerAccNameSearch(id);
			accNameTx.setText(vo);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}
	
	
	public void Early(String code, String id) {

		try {

			String workerCode = code;
			String mgrID = id;

			dao = new WorkerDAO();

			WorkerContVO workerVo = dao.workerCont(workerCode);

			accNumTx.setText(String.valueOf(workerVo.getAccNum()));
			accBankTx.setText(workerVo.getAccBank());
			accNameTx.setText(workerVo.getAccName());

			String now = String.valueOf(LocalDate.now());

			MgrVO mgrVo = dao.mgrNameSerch(mgrID);
			System.out.println("관리자 호출 실행");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	//   public void insertCont(String contCode,String id) {
	//      
	//      String sDate = workerContSdateTx.getText();
	//      String edate = workerContEdateTx.getText();
	//      int recontNum = Integer.parseInt(recontNumTx.getText());
	//      String contPeriod = contPeriodTx.getText();
	//      
	//      String contDate = contDateLabel.getText();
	//      
	//      WorkerContVO vo = new WorkerContVO(sDate,edate,recontNum,contPeriod,contDate);
	//      
	//      try {
	//         workerDAO dao = new workerDAO();
	//         
	//         dao.workerContInsert(vo, id);
	//         
	//      } catch (Exception e) {
	//         // TODO: handle exception
	//      }

	//      workerContSdateTx
	//      workerContEdateTx
	//      recontNumTx
	//      contPeriodTx

}
