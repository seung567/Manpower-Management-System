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
import javax.swing.JSeparator;

public class MSheetCheckView extends JFrame {

	private JPanel contentPane;

	
	MWorkerInsertViewXXXXXX mWorkerInsertViewXXXXXX = null;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void Action() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSheetCheckView frame = new MSheetCheckView();
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
	public MSheetCheckView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 640);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel sheetCheckMainLabel = new JLabel("정산입금확인");
		sheetCheckMainLabel.setBounds(12, 10, 132, 34);
		sheetCheckMainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		contentPane.add(sheetCheckMainLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setBackground(new Color(242, 170, 76));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
//		workerContPanel.setBackground(new Color(181, 218, 255));
		workerContPanel.setBounds(22, 155, 499, 268);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);
		
		JLabel workerCostLabel = new JLabel("인건비");
		workerCostLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerCostLabel.setBounds(150, 33, 77, 27);
		workerContPanel.add(workerCostLabel);
		
		JLabel workerCostLabel_val = new JLabel("[인건비]");
		workerCostLabel_val.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerCostLabel_val.setBounds(239, 33, 77, 27);
		workerContPanel.add(workerCostLabel_val);
		
		JLabel chargeLabel = new JLabel("파견수수료");
		chargeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chargeLabel.setBounds(150, 70, 77, 27);
		workerContPanel.add(chargeLabel);
		
		JLabel chargeLabel_val = new JLabel("[파견수수료]");
		chargeLabel_val.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chargeLabel_val.setBounds(239, 70, 84, 27);
		workerContPanel.add(chargeLabel_val);
		
		JLabel workerContEdateTitel_1 = new JLabel("정산금액");
		workerContEdateTitel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel_1.setBounds(150, 119, 77, 27);
		workerContPanel.add(workerContEdateTitel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(134, 107, 201, 2);
		workerContPanel.add(separator);
		
		JLabel workerContEdateTitel_1_1 = new JLabel("[정산금액]");
		workerContEdateTitel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel_1_1.setBounds(239, 119, 84, 27);
		workerContPanel.add(workerContEdateTitel_1_1);
		
		JLabel workerContEdateTitel_1_1_1 = new JLabel("[세금]");
		workerContEdateTitel_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel_1_1_1.setBounds(239, 150, 84, 27);
		workerContPanel.add(workerContEdateTitel_1_1_1);
		
		JLabel workerContEdateTitel_1_2 = new JLabel("세금");
		workerContEdateTitel_1_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel_1_2.setBounds(150, 150, 77, 27);
		workerContPanel.add(workerContEdateTitel_1_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(134, 186, 201, 2);
		workerContPanel.add(separator_1);
		
		JLabel workerContEdateTitel_1_2_1 = new JLabel("실정산금액");
		workerContEdateTitel_1_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel_1_2_1.setBounds(150, 203, 77, 27);
		workerContPanel.add(workerContEdateTitel_1_2_1);
		
		JLabel workerContEdateTitel_1_1_1_1 = new JLabel("[실정산금액]");
		workerContEdateTitel_1_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		workerContEdateTitel_1_1_1_1.setBounds(239, 203, 84, 27);
		workerContPanel.add(workerContEdateTitel_1_1_1_1);

		JLabel sheetLabel = new JLabel("명세표번호");
		sheetLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		sheetLabel.setBounds(22, 130, 90, 15);
		contentPane.add(sheetLabel);

		JLabel contDateLabel = new JLabel("[입금날짜]");
		contDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		contDateLabel.setBounds(241, 450, 132, 28);
		contentPane.add(contDateLabel);

		JButton applyBtn = new JButton("확인");
//		applyBtn.setForeground(new Color(80, 88, 108));
//		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.setBackground(Color.BLACK);
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mWorkerInsertViewXXXXXX.dispose();
			
			}
		});
		applyBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		applyBtn.setBounds(166, 519, 210, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);
		
		JLabel workernumberLabel = new JLabel("업체명");
		workernumberLabel.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 17));
		workernumberLabel.setBounds(22, 70, 56, 28);
		contentPane.add(workernumberLabel);
		
		JLabel workernumberLabel_1 = new JLabel("업체번호");
		workernumberLabel_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 17));
		workernumberLabel_1.setBounds(85, 70, 69, 28);
		contentPane.add(workernumberLabel_1);
		
		JLabel contDateLabel_1 = new JLabel("관리자 [관리자]");
		contDateLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		contDateLabel_1.setBounds(385, 450, 132, 28);
		contentPane.add(contDateLabel_1);
		
		JLabel workernumberLabel_1_1 = new JLabel("대표자명");
		workernumberLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 17));
		workernumberLabel_1_1.setBounds(166, 70, 67, 28);
		contentPane.add(workernumberLabel_1_1);
		
		JLabel sheetLabel_1 = new JLabel("[명세표번호]");
		sheetLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		sheetLabel_1.setBounds(106, 130, 90, 15);
		contentPane.add(sheetLabel_1);

	}
}