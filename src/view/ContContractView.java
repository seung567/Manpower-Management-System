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

public class ContContractView extends JFrame {

	private JPanel contentPane;

	
	WorkerInsertView workerInsertView = null;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void Action() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContContractView frame = new ContContractView();
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
	
	public ContContractView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 608);
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("파견계약승인");
		reqContLabel.setBounds(12, 10, 154, 34);
		reqContLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		contentPane.add(reqContLabel);

		JLabel contDateLabel = new JLabel("계약관리자");
		contDateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel.setBounds(319, 299, 85, 28);
		contentPane.add(contDateLabel);

		JButton applyBtn = new JButton("파견 계약 승인");
		applyBtn.setBackground(new Color(16, 24, 32));
		applyBtn.setForeground(new Color(242, 170, 76));
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				workerInsertView.dispose();
			
			}
		});
		applyBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		applyBtn.setBounds(167, 490, 210, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);
		
		JLabel workerContSdateTitel = new JLabel("실근무시작일");
		workerContSdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContSdateTitel.setBounds(167, 187, 93, 27);
		contentPane.add(workerContSdateTitel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(272, 185, 116, 30);
		contentPane.add(textField);
		
		JLabel workerContEdateTitel = new JLabel("실근무종료일");
		workerContEdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerContEdateTitel.setBounds(167, 228, 98, 27);
		contentPane.add(workerContEdateTitel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(272, 226, 116, 30);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("파견인력번호");
		lblNewLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		lblNewLabel.setBounds(44, 106, 93, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("[파견인력번호]");
		lblNewLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(149, 106, 116, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("파견요청번호");
		lblNewLabel_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(280, 106, 93, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("[파견요청번호]");
		lblNewLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(385, 106, 131, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel contDateLabel_2 = new JLabel("[계약관리자]");
		contDateLabel_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contDateLabel_2.setBounds(416, 299, 100, 28);
		contentPane.add(contDateLabel_2);
		
		JLabel contDateLabel_1 = new JLabel("사용업체명");
		contDateLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel_1.setBounds(319, 337, 85, 28);
		contentPane.add(contDateLabel_1);
		
		JLabel contDateLabel_2_1 = new JLabel("[사용업체명]");
		contDateLabel_2_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contDateLabel_2_1.setBounds(416, 337, 100, 28);
		contentPane.add(contDateLabel_2_1);
		
		JLabel contDateLabel_1_1 = new JLabel("파견지원자");
		contDateLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contDateLabel_1_1.setBounds(319, 375, 85, 28);
		contentPane.add(contDateLabel_1_1);
		
		JLabel contDateLabel_2_1_1 = new JLabel("[파견지원자]");
		contDateLabel_2_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		contDateLabel_2_1_1.setBounds(416, 375, 100, 28);
		contentPane.add(contDateLabel_2_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("위 계약건에 대하여 승인 처리 합니다.");
		lblNewLabel_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(137, 420, 271, 73);
		contentPane.add(lblNewLabel_3);

	}
}