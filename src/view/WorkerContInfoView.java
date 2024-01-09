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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 542);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(220,226,240));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("계약정보확인");
		reqContLabel.setBounds(12, 10, 132, 34);
		reqContLabel.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		contentPane.add(reqContLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setBackground(new Color(192, 192, 192));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		workerContPanel.setBackground(new Color(181, 218, 255));
		workerContPanel.setBounds(12, 155, 255, 168);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel workerContSdateTitel = new JLabel("계약시작일");
		workerContSdateTitel.setBounds(28, 12, 77, 27);
		workerContSdateTitel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContPanel.add(workerContSdateTitel);

		workerContSdateTx = new JTextField();
		workerContSdateTx.setBounds(117, 13, 116, 30);
		workerContSdateTx.setColumns(10);
		workerContPanel.add(workerContSdateTx);

		JLabel workerContEdateTitel = new JLabel("계약만료일");
		workerContEdateTitel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel.setBounds(28, 50, 77, 27);
		workerContPanel.add(workerContEdateTitel);

		workerContEdateTx = new JTextField();
		workerContEdateTx.setColumns(10);
		workerContEdateTx.setBounds(117, 50, 116, 30);
		workerContPanel.add(workerContEdateTx);

		JLabel recontNumTitel = new JLabel("재계약횟수");
		recontNumTitel.setBackground(new Color(255, 128, 128));
		recontNumTitel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		recontNumTitel.setBounds(28, 86, 70, 27);
		workerContPanel.add(recontNumTitel);

		recontNumTx = new JTextField();
		recontNumTx.setColumns(10);
		recontNumTx.setBounds(117, 87, 116, 30);
		workerContPanel.add(recontNumTx);

		JLabel contPeriodLabel = new JLabel("계약기간");
		contPeriodLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		contPeriodLabel.setBackground(new Color(255, 128, 128));
		contPeriodLabel.setBounds(28, 123, 70, 27);
		workerContPanel.add(contPeriodLabel);

		contPeriodTx = new JTextField();
		contPeriodTx.setColumns(10);
		contPeriodTx.setBounds(117, 124, 116, 30);
		workerContPanel.add(contPeriodTx);

		JLabel contPeriodLabel1 = new JLabel("계약기간");
		contPeriodLabel1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		contPeriodLabel1.setBounds(12, 130, 67, 15);
		contentPane.add(contPeriodLabel1);

		JLabel contDateLabel = new JLabel("계약일 [계약일]");
		contDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		contDateLabel.setBounds(405, 334, 132, 28);
		contentPane.add(contDateLabel);

		JPanel accInfoPanel = new JPanel();
		accInfoPanel.setLayout(null);
		accInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		accInfoPanel.setBackground(new Color(181, 218, 255));
		accInfoPanel.setBounds(282, 155, 255, 128);
		contentPane.add(accInfoPanel);

		JLabel accNumLabel = new JLabel("입금계좌번호");
		accNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		accNumLabel.setBounds(21, 12, 84, 27);
		accInfoPanel.add(accNumLabel);

		accNumTx = new JTextField();
		accNumTx.setColumns(10);
		accNumTx.setBounds(117, 13, 116, 30);
		accInfoPanel.add(accNumTx);

		JLabel accBankLabel = new JLabel("입금은행명");
		accBankLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		accBankLabel.setBounds(21, 49, 77, 27);
		accInfoPanel.add(accBankLabel);

		accBankTx = new JTextField();
		accBankTx.setColumns(10);
		accBankTx.setBounds(117, 50, 116, 30);
		accInfoPanel.add(accBankTx);

		JLabel accNameLabel = new JLabel("예금주");
		accNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		accNameLabel.setBackground(new Color(255, 128, 128));
		accNameLabel.setBounds(21, 86, 70, 27);
		accInfoPanel.add(accNameLabel);

		accNameTx = new JTextField();
		accNameTx.setColumns(10);
		accNameTx.setBounds(117, 87, 116, 30);
		accInfoPanel.add(accNameTx);

		JLabel accInfoLabel = new JLabel("수당지급정보");
		accInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		accInfoLabel.setBounds(282, 130, 91, 15);
		contentPane.add(accInfoLabel);

		JButton applyBtn = new JButton("확인");
		applyBtn.setForeground(new Color(80, 88, 108));
		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				workerInsertView.dispose();
			
			}
		});
		applyBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		applyBtn.setBounds(167, 422, 210, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);
		
		JLabel workernumberLabel = new JLabel("파견인력번호");
		workernumberLabel.setBounds(12, 73, 84, 28);
		contentPane.add(workernumberLabel);
		
		JLabel workernumberLabel_1 = new JLabel("파견인력번호");
		workernumberLabel_1.setBounds(101, 73, 84, 28);
		contentPane.add(workernumberLabel_1);
		
		JLabel contDateLabel_1 = new JLabel("관리자 [관리자]");
		contDateLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		contDateLabel_1.setBounds(405, 375, 132, 28);
		contentPane.add(contDateLabel_1);

	}
}