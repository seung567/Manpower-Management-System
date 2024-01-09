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

public class SheetInfoListView extends JFrame {

	private JPanel contentPane;

	
	WorkerInsertView workerInsertView = null;
	/**
	 * Launch the application.
	 */
	
	// 계약정보 view
	
	public static void Action() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SheetInfoListView frame = new SheetInfoListView();
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
	public SheetInfoListView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 640);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel sheetCheckMainLabel = new JLabel("계약내역정보");
		sheetCheckMainLabel.setBounds(12, 10, 132, 34);
		sheetCheckMainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		contentPane.add(sheetCheckMainLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setBackground(new Color(242, 170, 76));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
//		workerContPanel.setBackground(new Color(181, 218, 255));
		workerContPanel.setBounds(23, 155, 499, 354);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel sheetLabel = new JLabel("파견계약내역");
		sheetLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		sheetLabel.setBounds(22, 130, 90, 15);
		contentPane.add(sheetLabel);

		JButton applyBtn = new JButton("확인");
		applyBtn.setForeground(new Color(255, 255, 255));
//		applyBtn.setForeground(new Color(80, 88, 108));
//		applyBtn.setBackground(new Color(80, 88, 108));
		applyBtn.setBackground(new Color(16, 24, 32));
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				workerInsertView.dispose();
			
			}
		});
		applyBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		applyBtn.setBounds(168, 530, 210, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);
		
		JLabel workernumberLabel = new JLabel("업체명");
		workernumberLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel.setBounds(22, 70, 56, 28);
		contentPane.add(workernumberLabel);
		
		JLabel workernumberLabel_1 = new JLabel("업체번호");
		workernumberLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel_1.setBounds(85, 70, 69, 28);
		contentPane.add(workernumberLabel_1);
		
		JLabel workernumberLabel_1_1 = new JLabel("대표자명");
		workernumberLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workernumberLabel_1_1.setBounds(166, 70, 67, 28);
		contentPane.add(workernumberLabel_1_1);

	}
}