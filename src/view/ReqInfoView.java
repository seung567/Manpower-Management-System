package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;

public class ReqInfoView extends JFrame {

	private JPanel workerInsertMainPanel;
	private JTextField workerIDTx;
	private JTextField workerPWTx;
	private JTextField workerNameTx;
	private JTextField workerTelTx;
	private JTextField workerEmailTx;
	private JTextField careerPeriodTx;
	private JTextField careerDetailTx;
	private JTextField skillcodeTx;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	

	/**
	 * Launch the application.
	 */
	public static void workerInsertAction() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ±âº» ÇÁ·¹ÀÓ ¸í·É¾î
					ReqInfoView frame = new ReqInfoView();
					frame.setVisible(true);
					
					// ÆË¾÷Ã¢ x ¹öÆ° ´©¸¦½Ã Ã¢¸¸ ²¨Áö°Ô ÇØÁÖ´Â ¸í·É¾î
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							// super.windowClosing(e);

							int result = JOptionPane.showConfirmDialog(frame, "Ã¢À» ´ÝÀ¸½Ã°Ú½À´Ï±î?", "È®ÀÎ",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								frame.dispose();
							}
						}
					});
					// ¿©±â±îÁö
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReqInfoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 741);
		workerInsertMainPanel = new JPanel();
		workerInsertMainPanel.setBackground(new Color(181, 218, 255));
		workerInsertMainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(workerInsertMainPanel);
		workerInsertMainPanel.setLayout(null);
		
		JLabel workerInsertLabel = new JLabel("ÆÄ°ß¿äÃ»Á¤º¸");
		workerInsertLabel.setBounds(12, 10, 112, 27);
		workerInsertLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		workerInsertMainPanel.add(workerInsertLabel);
		
		JLabel loginLabel = new JLabel("ÆÄ°ß¿äÃ»ÀÏÁ¤Á¤º¸");
		loginLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		loginLabel.setBounds(12, 138, 112, 15);
		workerInsertMainPanel.add(loginLabel);
		
		JPanel loginInfoPanel = new JPanel();
		loginInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		loginInfoPanel.setBackground(new Color(181, 218, 255));
		loginInfoPanel.setBounds(12, 156, 451, 42);
		workerInsertMainPanel.add(loginInfoPanel);
		loginInfoPanel.setLayout(null);
		
		JLabel workerIDLabel = new JLabel("±Ù¹«½ÃÀÛÀÏ");
		workerIDLabel.setBounds(12, 10, 81, 21);
		workerIDLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		loginInfoPanel.add(workerIDLabel);
		
		workerIDTx = new JTextField();
		workerIDTx.setBounds(98, 10, 116, 21);
		workerIDTx.setColumns(10);
		loginInfoPanel.add(workerIDTx);
		
		JLabel workerPWLabel = new JLabel("±Ù¹«Á¾·áÀÏ");
		workerPWLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerPWLabel.setBounds(226, 10, 85, 21);
		loginInfoPanel.add(workerPWLabel);
		
		workerPWTx = new JTextField();
		workerPWTx.setColumns(10);
		workerPWTx.setBounds(311, 10, 116, 21);
		loginInfoPanel.add(workerPWTx);
		
		JLabel basicLabel = new JLabel("ÆÄ°ßÁöÁ¤º¸");
		basicLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		basicLabel.setBounds(12, 215, 85, 15);
		workerInsertMainPanel.add(basicLabel);
		
		JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setBackground(new Color(181, 218, 255));
		basicInfoPanel.setLayout(null);
		basicInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		basicInfoPanel.setBounds(12, 234, 451, 74);
		workerInsertMainPanel.add(basicInfoPanel);
		
		JLabel workerNameLabel = new JLabel("±Ù¹«±¹°¡");
		workerNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerNameLabel.setBounds(12, 10, 72, 21);
		basicInfoPanel.add(workerNameLabel);
		
		workerNameTx = new JTextField();
		workerNameTx.setColumns(10);
		workerNameTx.setBounds(83, 10, 116, 21);
		basicInfoPanel.add(workerNameTx);
		
		JLabel workerTelLabel = new JLabel("ÇÊ¼ö¾îÇÐ¼öÁØ");
		workerTelLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerTelLabel.setBounds(232, 10, 90, 21);
		basicInfoPanel.add(workerTelLabel);
		
		workerTelTx = new JTextField();
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(333, 10, 106, 21);
		basicInfoPanel.add(workerTelTx);
		
		JLabel workerEmailLabel = new JLabel("»ó¼¼±Ù¹«Áö");
		workerEmailLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerEmailLabel.setBounds(12, 41, 90, 21);
		basicInfoPanel.add(workerEmailLabel);
		
		workerEmailTx = new JTextField();
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(103, 41, 336, 21);
		basicInfoPanel.add(workerEmailTx);
		
		JLabel careerInfoLabel = new JLabel("°æ·ÂÁ¤º¸");
		careerInfoLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		careerInfoLabel.setBounds(12, 329, 70, 15);
		workerInsertMainPanel.add(careerInfoLabel);
		
		JPanel careerInfoPanel = new JPanel();
		careerInfoPanel.setLayout(null);
		careerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		careerInfoPanel.setBackground(new Color(181, 218, 255));
		careerInfoPanel.setBounds(12, 348, 451, 278);
		workerInsertMainPanel.add(careerInfoPanel);
		
		JLabel careerPeriodLabel = new JLabel("ÆÄ°ß¾÷Á¾");
		careerPeriodLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerPeriodLabel.setBounds(12, 13, 69, 21);
		careerInfoPanel.add(careerPeriodLabel);
		
		careerPeriodTx = new JTextField();
		careerPeriodTx.setColumns(10);
		careerPeriodTx.setBounds(93, 13, 106, 21);
		careerInfoPanel.add(careerPeriodTx);
		
		JLabel careerDetailLabel = new JLabel("ÀÚ°Ý¿ä°Ç");
		careerDetailLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerDetailLabel.setBounds(12, 75, 69, 21);
		careerInfoPanel.add(careerDetailLabel);
		
		careerDetailTx = new JTextField();
		careerDetailTx.setColumns(10);
		careerDetailTx.setBounds(93, 75, 346, 53);
		careerInfoPanel.add(careerDetailTx);
		
		JLabel skillcodeLabel = new JLabel("¿äÃ»ÀÎ¿ø");
		skillcodeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		skillcodeLabel.setBounds(219, 13, 69, 21);
		careerInfoPanel.add(skillcodeLabel);
		
		skillcodeTx = new JTextField();
		skillcodeTx.setColumns(10);
		skillcodeTx.setBounds(300, 13, 106, 21);
		careerInfoPanel.add(skillcodeTx);
		
		JLabel careerPeriodLabel_1 = new JLabel("¼ºº°");
		careerPeriodLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerPeriodLabel_1.setBounds(12, 44, 69, 21);
		careerInfoPanel.add(careerPeriodLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(93, 44, 106, 21);
		careerInfoPanel.add(textField);
		
		JLabel careerPeriodLabel_1_1 = new JLabel("¿¬·É´ë");
		careerPeriodLabel_1_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerPeriodLabel_1_1.setBounds(219, 44, 69, 21);
		careerInfoPanel.add(careerPeriodLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(300, 44, 106, 21);
		careerInfoPanel.add(textField_1);
		
		JLabel careerDetailLabel_1 = new JLabel("ÃÑ ÆÄ°ß ºñ¿ë");
		careerDetailLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerDetailLabel_1.setBounds(127, 142, 90, 21);
		careerInfoPanel.add(careerDetailLabel_1);
		
		JLabel careerDetailLabel_1_1 = new JLabel("\uCD1D \uD30C\uACAC \uBE44\uC6A9");
		careerDetailLabel_1_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerDetailLabel_1_1.setBounds(233, 142, 90, 21);
		careerInfoPanel.add(careerDetailLabel_1_1);
		
		JLabel careerDetailLabel_2 = new JLabel("±âÅ¸ ¿ä±¸»çÇ×");
		careerDetailLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerDetailLabel_2.setBounds(12, 171, 106, 21);
		careerInfoPanel.add(careerDetailLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 202, 427, 53);
		careerInfoPanel.add(textField_2);
		
		JButton workerInsertBtn = new JButton("°è¾àÁ¤º¸µî·Ï");
		workerInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				WorkerInsertView addr = this.getInstance();
//				new WorkerContInsertView(addr);
				
			}

//			private ActionListener getInstance() {
//				// TODO Auto-generated method stub
//				return this;
//			}
		});
		workerInsertBtn.setBackground(new Color(0, 0, 0));
		workerInsertBtn.setBounds(173, 636, 129, 37);
		workerInsertMainPanel.add(workerInsertBtn);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(351, 10, 112, 91);
		workerInsertMainPanel.add(logoLabel);
		
		JLabel workerInsertLabel_1 = new JLabel("ÆÄ°ß¿äÃ»¹øÈ£");
		workerInsertLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		workerInsertLabel_1.setBounds(12, 50, 112, 27);
		workerInsertMainPanel.add(workerInsertLabel_1);
		
		JLabel workerInsertLabel_1_1 = new JLabel("ÆÄ°ß¿äÃ»¾÷Ã¼");
		workerInsertLabel_1_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		workerInsertLabel_1_1.setBounds(12, 87, 112, 27);
		workerInsertMainPanel.add(workerInsertLabel_1_1);
		
		JLabel workerInsertLabel_1_1_1 = new JLabel("[ÆÄ°ß¿äÃ»¾÷Ã¼]");
		workerInsertLabel_1_1_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		workerInsertLabel_1_1_1.setBounds(131, 87, 114, 27);
		workerInsertMainPanel.add(workerInsertLabel_1_1_1);
	}
	

}
