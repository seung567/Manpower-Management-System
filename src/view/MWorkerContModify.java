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

import model.ManagerWorkerDAO;
import model.rec.MgrVO;
import model.rec.WorkerContVO;

public class MWorkerContModify extends JFrame {

	private JPanel contentPane;
	private JTextField workerContSdateTx;
	private JTextField workerContEdateTx;
	private JTextField recontNumTx;
	private JTextField contPeriodTx;
	private JTextField accNumTx;
	private JTextField accBankTx;
	private JTextField accNameTx;
	private JLabel contDateLabel_1;
	private JLabel contDateLabel;
	private JLabel contStateLabel;
	private MWorkerInsertViewXXXXXX mWorkerInsertViewXXXXXX = null;
	private String workerCode;
	
	private String contCode = null;
	private String id = null;
	
	private ManagerWorkerDAO dao;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void workerContAtion(String contCode, String idText) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MWorkerContModify frame = new MWorkerContModify(contCode,idText);
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
	
	// 윈도우 빌더용 
	public MWorkerContModify(){
		
		this.workerContAtion("13","13");
		
	}
	
	// 실행용 생성자 함수
	public MWorkerContModify(int num){
		
	}
	
	// 실행용 메인 생성자 함수
	public MWorkerContModify(String contCode, String idText) {
		
		this.contCode = contCode;
		this.id = idText;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 641);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("계약정보확인");
		reqContLabel.setForeground(new Color(0, 0, 0));
		reqContLabel.setBounds(223, 56, 113, 34);
		reqContLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		contentPane.add(reqContLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setForeground(new Color(0, 0, 0));
		workerContPanel.setBackground(new Color(0, 0, 0));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel.setBounds(27, 150, 496, 128);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel workerContSdateTitel = new JLabel("계약시작일");
		workerContSdateTitel.setForeground(new Color(242, 170, 76));
		workerContSdateTitel.setBounds(31, 24, 77, 27);
		workerContSdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContPanel.add(workerContSdateTitel);

		workerContSdateTx = new JTextField();
		workerContSdateTx.setEditable(false);
		workerContSdateTx.setBounds(120, 22, 116, 30);
		workerContSdateTx.setColumns(10);
		workerContPanel.add(workerContSdateTx);

		JLabel workerContEdateTitel = new JLabel("계약만료일");
		workerContEdateTitel.setForeground(new Color(242, 170, 76));
		workerContEdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContEdateTitel.setBounds(262, 25, 77, 27);
		workerContPanel.add(workerContEdateTitel);

		workerContEdateTx = new JTextField();
		workerContEdateTx.setEditable(false);
		workerContEdateTx.setColumns(10);
		workerContEdateTx.setBounds(351, 23, 116, 30);
		workerContPanel.add(workerContEdateTx);

		JLabel recontNumTitel = new JLabel("재계약횟수");
		recontNumTitel.setForeground(new Color(242, 170, 76));
		recontNumTitel.setBackground(new Color(255, 128, 128));
		recontNumTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		recontNumTitel.setBounds(31, 78, 77, 27);
		workerContPanel.add(recontNumTitel);

		recontNumTx = new JTextField();
		recontNumTx.setEditable(false);
		recontNumTx.setColumns(10);
		recontNumTx.setBounds(120, 76, 116, 30);
		workerContPanel.add(recontNumTx);

		JLabel contPeriodLabel = new JLabel("계약기간");
		contPeriodLabel.setForeground(new Color(242, 170, 76));
		contPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contPeriodLabel.setBackground(new Color(255, 128, 128));
		contPeriodLabel.setBounds(262, 78, 70, 27);
		workerContPanel.add(contPeriodLabel);

		contPeriodTx = new JTextField();
		contPeriodTx.setEditable(false);
		contPeriodTx.setColumns(10);
		contPeriodTx.setBounds(351, 76, 116, 30);
		workerContPanel.add(contPeriodTx);

		JLabel contPeriodLabel1 = new JLabel("계약기간");
		contPeriodLabel1.setForeground(new Color(0, 0, 0));
		contPeriodLabel1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contPeriodLabel1.setBounds(27, 122, 67, 18);
		contentPane.add(contPeriodLabel1);

		contDateLabel = new JLabel("계약일 [계약일]");
		contDateLabel.setForeground(new Color(0, 0, 0));
		contDateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel.setBounds(198, 485, 181, 28);
		contentPane.add(contDateLabel);

		JPanel accInfoPanel = new JPanel();
		accInfoPanel.setForeground(new Color(0, 0, 0));
		accInfoPanel.setLayout(null);
		accInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		accInfoPanel.setBackground(new Color(0, 0, 0));
		accInfoPanel.setBounds(27, 328, 496, 128);
		contentPane.add(accInfoPanel);

		JLabel accNumLabel = new JLabel("입금계좌번호");
		accNumLabel.setForeground(new Color(242, 170, 76));
		accNumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accNumLabel.setBounds(28, 70, 108, 27);
		accInfoPanel.add(accNumLabel);

		accNumTx = new JTextField();
		accNumTx.setEditable(false);
		accNumTx.setColumns(10);
		accNumTx.setBounds(124, 68, 340, 30);
		accInfoPanel.add(accNumTx);

		JLabel accBankLabel = new JLabel("입금은행명");
		accBankLabel.setForeground(new Color(242, 170, 76));
		accBankLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accBankLabel.setBounds(259, 25, 77, 27);
		accInfoPanel.add(accBankLabel);

		accBankTx = new JTextField();
		accBankTx.setEditable(false);
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

		JLabel accInfoLabel = new JLabel("수당지급정보");
		accInfoLabel.setForeground(new Color(0, 0, 0));
		accInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accInfoLabel.setBounds(27, 300, 91, 18);
		contentPane.add(accInfoLabel);

		JButton applyBtn = new JButton("확인");
		applyBtn.setForeground(new Color(255, 255, 255));
//		applyBtn.setForeground(new Color(80, 88, 108));
//		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.setBackground(new Color(0, 0, 0));
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mWorkerInsertViewXXXXXX.dispose();
				dispose();
			
			}
		});
		applyBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		applyBtn.setBounds(170, 538, 210, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel workernumberLabel = new JLabel("고용계약번호");
		workernumberLabel.setForeground(new Color(0, 0, 0));
		workernumberLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel.setBounds(316, 100, 101, 28);
		contentPane.add(workernumberLabel);
		
		JLabel workernumberLabel_1 = new JLabel("파견인력번호");
		workernumberLabel_1.setForeground(new Color(0, 0, 0));
		workernumberLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel_1.setBounds(422, 100, 101, 28);
		workernumberLabel_1.setText(contCode);
		contentPane.add(workernumberLabel_1);
		
		contDateLabel_1 = new JLabel("관리자 [관리자]");
		contDateLabel_1.setForeground(new Color(0, 0, 0));
		contDateLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel_1.setBounds(391, 485, 132, 28);
		contentPane.add(contDateLabel_1);
		
		contStateLabel = new JLabel("New label");
		contStateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contStateLabel.setBounds(27, 26, 145, 26);
		contentPane.add(contStateLabel);
		
		
		Early(contCode,id);
	}
	

	
	public void Early(String code,String id) {

		try {

			String contCode = code;
			String mgrID = id;

			dao = new ManagerWorkerDAO();

			WorkerContVO workerVo = dao.workerCont(contCode);

			workerContSdateTx.setText(workerVo.getWorkeContSdate());
			workerContEdateTx.setText(workerVo.getWorkerContEdate());
			recontNumTx.setText(String.valueOf(workerVo.getRecontNum()));
			contPeriodTx.setText(workerVo.getContPeriod());
			contStateLabel.setText("계약상태 : " + workerVo.getContState());
			
			
			accNumTx.setText(String.valueOf(workerVo.getAccNum()));
			accBankTx.setText(workerVo.getAccBank());
			accNameTx.setText(workerVo.getAccName());

			String now = String.valueOf(LocalDate.now());
			
			
			contDateLabel.setText("계약일 " + now);

			MgrVO mgrVo = dao.mgrNameSerch(mgrID);
			System.out.println("관리자 호출 실행");
			contDateLabel_1.setText("관리자 " + mgrVo.getMgrName());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public void insertCont() {
		
	}
}
