package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class WorkerContInfoView extends JFrame {

	private JPanel contentPane;
	private JTextField workerContSdateTx;
	private JTextField workerContEdateTx;
	private JTextField recontNumTx;
	private JTextField contPeriodTx;
	private JTextField accNumTx;
	private JTextField accBankTx;
	private JTextField accNameTx;

	
	WorkerInsertView workerInsertView = null;
	String workerCode;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void Action() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerContInfoView frame = new WorkerContInfoView();
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

	
	public WorkerContInfoView() {
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 647);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("계약정보확인");
		reqContLabel.setForeground(new Color(0, 0, 0));
		reqContLabel.setBounds(12, 10, 132, 34);
		reqContLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contentPane.add(reqContLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setForeground(new Color(0, 0, 0));
		workerContPanel.setBackground(new Color(0, 0, 0));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerContPanel.setBounds(22, 154, 496, 128);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel workerContSdateTitel = new JLabel("계약시작일");
		workerContSdateTitel.setForeground(new Color(242, 170, 76));
		workerContSdateTitel.setBounds(28, 31, 77, 27);
		workerContSdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContPanel.add(workerContSdateTitel);

		workerContSdateTx = new JTextField();
		workerContSdateTx.setBounds(117, 29, 116, 30);
		workerContSdateTx.setColumns(10);
		workerContPanel.add(workerContSdateTx);

		JLabel workerContEdateTitel = new JLabel("계약만료일");
		workerContEdateTitel.setForeground(new Color(242, 170, 76));
		workerContEdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContEdateTitel.setBounds(259, 32, 77, 27);
		workerContPanel.add(workerContEdateTitel);

		workerContEdateTx = new JTextField();
		workerContEdateTx.setColumns(10);
		workerContEdateTx.setBounds(348, 30, 116, 30);
		workerContPanel.add(workerContEdateTx);

		JLabel recontNumTitel = new JLabel("재계약횟수");
		recontNumTitel.setForeground(new Color(242, 170, 76));
		recontNumTitel.setBackground(new Color(255, 128, 128));
		recontNumTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		recontNumTitel.setBounds(28, 85, 77, 27);
		workerContPanel.add(recontNumTitel);

		recontNumTx = new JTextField();
		recontNumTx.setColumns(10);
		recontNumTx.setBounds(117, 83, 116, 30);
		workerContPanel.add(recontNumTx);

		JLabel contPeriodLabel = new JLabel("계약기간");
		contPeriodLabel.setForeground(new Color(242, 170, 76));
		contPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contPeriodLabel.setBackground(new Color(255, 128, 128));
		contPeriodLabel.setBounds(259, 85, 70, 27);
		workerContPanel.add(contPeriodLabel);

		contPeriodTx = new JTextField();
		contPeriodTx.setColumns(10);
		contPeriodTx.setBounds(348, 83, 116, 30);
		workerContPanel.add(contPeriodTx);

		JLabel contPeriodLabel1 = new JLabel("계약기간");
		contPeriodLabel1.setForeground(new Color(0, 0, 0));
		contPeriodLabel1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contPeriodLabel1.setBounds(22, 129, 67, 15);
		contentPane.add(contPeriodLabel1);

		JLabel contDateLabel = new JLabel("계약일 [계약일]");
		contDateLabel.setForeground(new Color(0, 0, 0));
		contDateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel.setBounds(242, 478, 132, 28);
		contentPane.add(contDateLabel);

		JPanel accInfoPanel = new JPanel();
		accInfoPanel.setForeground(new Color(0, 0, 0));
		accInfoPanel.setLayout(null);
		accInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		accInfoPanel.setBackground(new Color(0, 0, 0));
		accInfoPanel.setBounds(22, 328, 496, 128);
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
		accNameTx.setColumns(10);
		accNameTx.setBounds(124, 23, 116, 30);
		accInfoPanel.add(accNameTx);

		JLabel accInfoLabel = new JLabel("수당지급정보");
		accInfoLabel.setForeground(new Color(0, 0, 0));
		accInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accInfoLabel.setBounds(22, 303, 91, 15);
		contentPane.add(accInfoLabel);

		JButton applyBtn = new JButton("확인");
		applyBtn.setForeground(new Color(255, 255, 255));
//		applyBtn.setForeground(new Color(80, 88, 108));
//		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.setBackground(new Color(0, 0, 0));
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				workerInsertView.dispose();
				dispose();
			
			}
		});
		applyBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		applyBtn.setBounds(161, 535, 210, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);
		
		JLabel workernumberLabel = new JLabel("파견인력번호");
		workernumberLabel.setForeground(new Color(0, 0, 0));
		workernumberLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel.setBounds(12, 73, 101, 28);
		contentPane.add(workernumberLabel);
		
		JLabel workernumberLabel_1 = new JLabel("파견인력번호");
		workernumberLabel_1.setForeground(new Color(0, 0, 0));
		workernumberLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel_1.setBounds(118, 73, 101, 28);
		contentPane.add(workernumberLabel_1);
		
		JLabel contDateLabel_1 = new JLabel("관리자 [관리자]");
		contDateLabel_1.setForeground(new Color(0, 0, 0));
		contDateLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel_1.setBounds(386, 478, 132, 28);
		contentPane.add(contDateLabel_1);
		
		

	}
	
	public WorkerContInfoView(String workerCode) {
		this.workerCode = workerCode;
	}
	
	public void Early() {
		/*
		 * 계약시작일 worker_cont_sdate
계약만료일 worker_cont_edate
재계약횟수 recont_num
계약기간 cont_period
예금주 acc_name
입금은행명 acc_bank
입금계좌명 acc_num
계약일 cont_date
관리자 mgr_code
		 */
		
//		worekrContSdate
	}
}