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

public class WorkerInsertView extends JFrame {

	private JPanel workerInsertMainPanel;
	private JTextField workerIDTx;
	private JTextField workerPWTx;
	private JTextField workerNameTx;
	private JTextField workerTelTx;
	private JTextField workerAddrTx;
	private JTextField workerRnumTx;
	private JTextField workerEmailTx;
	private JTextField careerPeriodTx;
	private JTextField careerDetailTx;
	private JTextField skillcodeTx;

	/**
	 * Launch the application.
	 */
	public static void workerInsertAction() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ±âº» ÇÁ·¹ÀÓ ¸í·É¾î
					WorkerInsertView frame = new WorkerInsertView();
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
	public WorkerInsertView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 633);
		workerInsertMainPanel = new JPanel();
		workerInsertMainPanel.setBackground(new Color(181, 218, 255));
		workerInsertMainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(workerInsertMainPanel);
		workerInsertMainPanel.setLayout(null);
		
		JLabel workerInsertLabel = new JLabel("ÆÄ°ßÀÎ·Âµî·Ï");
		workerInsertLabel.setBounds(12, 10, 112, 27);
		workerInsertLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		workerInsertMainPanel.add(workerInsertLabel);
		
		JLabel loginLabel = new JLabel("·Î±×ÀÎ Á¤º¸");
		loginLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		loginLabel.setBounds(12, 84, 70, 15);
		workerInsertMainPanel.add(loginLabel);
		
		JPanel loginInfoPanel = new JPanel();
		loginInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		loginInfoPanel.setBackground(new Color(181, 218, 255));
		loginInfoPanel.setBounds(12, 101, 451, 74);
		workerInsertMainPanel.add(loginInfoPanel);
		loginInfoPanel.setLayout(null);
		
		JLabel workerIDLabel = new JLabel("¾ÆÀÌµð");
		workerIDLabel.setBounds(76, 10, 45, 21);
		workerIDLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		loginInfoPanel.add(workerIDLabel);
		
		workerIDTx = new JTextField();
		workerIDTx.setBounds(159, 10, 116, 21);
		workerIDTx.setColumns(10);
		loginInfoPanel.add(workerIDTx);
		
		JLabel workerPWLabel = new JLabel("ºñ¹Ð¹øÈ£");
		workerPWLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerPWLabel.setBounds(76, 41, 69, 21);
		loginInfoPanel.add(workerPWLabel);
		
		workerPWTx = new JTextField();
		workerPWTx.setColumns(10);
		workerPWTx.setBounds(159, 41, 116, 21);
		loginInfoPanel.add(workerPWTx);
		
		JButton workerIDCkBtn = new JButton("Áßº¹Ã¼Å©");
		workerIDCkBtn.setForeground(new Color(0, 0, 0));
		workerIDCkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		workerIDCkBtn.setBackground(new Color(0, 0, 0));
		workerIDCkBtn.setBounds(287, 10, 97, 21);
		loginInfoPanel.add(workerIDCkBtn);
		
		JLabel basicLabel = new JLabel("±âº»Á¤º¸");
		basicLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		basicLabel.setBounds(12, 185, 70, 15);
		workerInsertMainPanel.add(basicLabel);
		
		JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setBackground(new Color(181, 218, 255));
		basicInfoPanel.setLayout(null);
		basicInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		basicInfoPanel.setBounds(12, 202, 451, 107);
		workerInsertMainPanel.add(basicInfoPanel);
		
		JLabel workerNameLabel = new JLabel("ÀÌ¸§");
		workerNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerNameLabel.setBounds(12, 10, 45, 21);
		basicInfoPanel.add(workerNameLabel);
		
		workerNameTx = new JTextField();
		workerNameTx.setColumns(10);
		workerNameTx.setBounds(51, 10, 116, 21);
		basicInfoPanel.add(workerNameTx);
		
		JLabel workerTelLabel = new JLabel("ÀüÈ­¹øÈ£");
		workerTelLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerTelLabel.setBounds(12, 41, 69, 21);
		basicInfoPanel.add(workerTelLabel);
		
		workerTelTx = new JTextField();
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(83, 41, 106, 21);
		basicInfoPanel.add(workerTelTx);
		
		JLabel workerAddrLabel = new JLabel("ÁÖ¼Ò");
		workerAddrLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerAddrLabel.setBounds(12, 72, 45, 21);
		basicInfoPanel.add(workerAddrLabel);
		
		workerAddrTx = new JTextField();
		workerAddrTx.setColumns(10);
		workerAddrTx.setBounds(51, 72, 388, 21);
		basicInfoPanel.add(workerAddrTx);
		
		JLabel workerRnumLabel = new JLabel("ÁÖ¹Î¹øÈ£");
		workerRnumLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerRnumLabel.setBounds(179, 10, 69, 21);
		basicInfoPanel.add(workerRnumLabel);
		
		workerRnumTx = new JTextField();
		workerRnumTx.setColumns(10);
		workerRnumTx.setBounds(248, 10, 191, 21);
		basicInfoPanel.add(workerRnumTx);
		
		JLabel workerEmailLabel = new JLabel("ÀÌ¸ÞÀÏ");
		workerEmailLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		workerEmailLabel.setBounds(201, 41, 69, 21);
		basicInfoPanel.add(workerEmailLabel);
		
		workerEmailTx = new JTextField();
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(258, 41, 181, 21);
		basicInfoPanel.add(workerEmailTx);
		
		JLabel careerInfoLabel = new JLabel("°æ·ÂÁ¤º¸");
		careerInfoLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		careerInfoLabel.setBounds(12, 319, 70, 15);
		workerInsertMainPanel.add(careerInfoLabel);
		
		JPanel careerInfoPanel = new JPanel();
		careerInfoPanel.setLayout(null);
		careerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		careerInfoPanel.setBackground(new Color(181, 218, 255));
		careerInfoPanel.setBounds(12, 336, 451, 176);
		workerInsertMainPanel.add(careerInfoPanel);
		
		JLabel careerPeriodLabel = new JLabel("°æ·Â±â°£");
		careerPeriodLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerPeriodLabel.setBounds(12, 13, 69, 21);
		careerInfoPanel.add(careerPeriodLabel);
		
		careerPeriodTx = new JTextField();
		careerPeriodTx.setColumns(10);
		careerPeriodTx.setBounds(93, 13, 106, 21);
		careerInfoPanel.add(careerPeriodTx);
		
		JLabel careerDetailLabel = new JLabel("°æ·Â³»¿ë");
		careerDetailLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		careerDetailLabel.setBounds(12, 72, 69, 21);
		careerInfoPanel.add(careerDetailLabel);
		
		careerDetailTx = new JTextField();
		careerDetailTx.setColumns(10);
		careerDetailTx.setBounds(12, 96, 427, 66);
		careerInfoPanel.add(careerDetailTx);
		
		JLabel skillcodeLabel = new JLabel("±â¼úºÐ·ù");
		skillcodeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		skillcodeLabel.setBounds(12, 44, 69, 21);
		careerInfoPanel.add(skillcodeLabel);
		
		skillcodeTx = new JTextField();
		skillcodeTx.setColumns(10);
		skillcodeTx.setBounds(93, 44, 106, 21);
		careerInfoPanel.add(skillcodeTx);
		
		JButton workerInsertBtn = new JButton("ÆÄ°ßÀÎ¿øµî·Ï");
		workerInsertBtn.setBackground(new Color(0, 0, 0));
		workerInsertBtn.setBounds(173, 535, 129, 37);
		workerInsertMainPanel.add(workerInsertBtn);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(351, 10, 112, 91);
		workerInsertMainPanel.add(logoLabel);
	}
}
