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

public class WorkerContView extends JFrame {

	private JPanel contentPane;
	private JTextField workerContNameTF;
	private JTextField workerCodeTx;
	private JTextField workerContSdateTx;
	private JTextField workerContEdateTx;
	private JTextField recontNumTx;
	private JTextField contPeriodTx;
	private JTextField accNumTx;
	private JTextField accBankTx;
	private JTextField accNameTx;

	/**
	 * Launch the application.
	 */
	
	// ������� view
	
	public static void workerAction() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerContView frame = new WorkerContView();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							// super.windowClosing(e);

							int result = JOptionPane.showConfirmDialog(frame, "â�� �����ðڽ��ϱ�?", "Ȯ��",
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
	public WorkerContView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 623);
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBackground(new Color(181, 218, 255));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel reqContLabel = new JLabel("��������");
		reqContLabel.setBounds(12, 10, 132, 34);
		reqContLabel.setFont(new Font("���� ����", Font.BOLD, 21));
		contentPane.add(reqContLabel);

		JLabel workerContCodeLabel = new JLabel("[�������ȣ]");
		workerContCodeLabel.setBackground(new Color(255, 255, 255));
		workerContCodeLabel.setForeground(new Color(0, 0, 0));
		workerContCodeLabel.setBounds(11, 85, 120, 28);
		workerContCodeLabel.setFont(new Font("���� ����", Font.BOLD, 18));
		contentPane.add(workerContCodeLabel);

		JLabel workerNameLabel = new JLabel("�̸�");
		workerNameLabel.setBounds(51, 143, 40, 27);
		workerNameLabel.setFont(new Font("���� ����", Font.BOLD, 20));
		contentPane.add(workerNameLabel);

		workerContNameTF = new JTextField();
		workerContNameTF.setBounds(103, 141, 120, 29);
		contentPane.add(workerContNameTF);
		workerContNameTF.setColumns(10);

		workerCodeTx = new JTextField();
		workerCodeTx.setBounds(388, 141, 120, 29);
		workerCodeTx.setColumns(10);
		contentPane.add(workerCodeTx);

		JLabel workerCodeLabel = new JLabel("�İ��η¹�ȣ");
		workerCodeLabel.setBounds(253, 141, 120, 27);
		workerCodeLabel.setFont(new Font("���� ����", Font.BOLD, 20));
		contentPane.add(workerCodeLabel);

		JPanel workerContPanel = new JPanel();
		workerContPanel.setBackground(new Color(192, 192, 192));
		workerContPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		workerContPanel.setBackground(new Color(181, 218, 255));
		workerContPanel.setBounds(12, 234, 255, 168);
		contentPane.add(workerContPanel);
		workerContPanel.setLayout(null);

		JLabel workerContSdateTitel = new JLabel("��������");
		workerContSdateTitel.setBounds(28, 12, 77, 27);
		workerContSdateTitel.setFont(new Font("���� ����", Font.PLAIN, 14));
		workerContPanel.add(workerContSdateTitel);

		workerContSdateTx = new JTextField();
		workerContSdateTx.setBounds(117, 13, 116, 30);
		workerContSdateTx.setColumns(10);
		workerContPanel.add(workerContSdateTx);

		JLabel workerContEdateTitel = new JLabel("��ุ����");
		workerContEdateTitel.setFont(new Font("���� ����", Font.PLAIN, 14));
		workerContEdateTitel.setBounds(28, 50, 77, 27);
		workerContPanel.add(workerContEdateTitel);

		workerContEdateTx = new JTextField();
		workerContEdateTx.setColumns(10);
		workerContEdateTx.setBounds(117, 50, 116, 30);
		workerContPanel.add(workerContEdateTx);

		JLabel recontNumTitel = new JLabel("����Ƚ��");
		recontNumTitel.setBackground(new Color(255, 128, 128));
		recontNumTitel.setFont(new Font("���� ����", Font.PLAIN, 14));
		recontNumTitel.setBounds(28, 86, 70, 27);
		workerContPanel.add(recontNumTitel);

		recontNumTx = new JTextField();
		recontNumTx.setColumns(10);
		recontNumTx.setBounds(117, 87, 116, 30);
		workerContPanel.add(recontNumTx);

		JLabel contPeriodLabel = new JLabel("���Ⱓ");
		contPeriodLabel.setFont(new Font("���� ����", Font.PLAIN, 14));
		contPeriodLabel.setBackground(new Color(255, 128, 128));
		contPeriodLabel.setBounds(28, 123, 70, 27);
		workerContPanel.add(contPeriodLabel);

		contPeriodTx = new JTextField();
		contPeriodTx.setColumns(10);
		contPeriodTx.setBounds(117, 124, 116, 30);
		workerContPanel.add(contPeriodTx);

		JLabel contPeriodLabel1 = new JLabel("���Ⱓ");
		contPeriodLabel1.setFont(new Font("���� ����", Font.BOLD, 15));
		contPeriodLabel1.setBounds(12, 209, 67, 15);
		contentPane.add(contPeriodLabel1);

		JLabel contDateLabel = new JLabel("����� [�����]");
		contDateLabel.setFont(new Font("���� ����", Font.PLAIN, 17));
		contDateLabel.setBounds(405, 434, 132, 28);
		contentPane.add(contDateLabel);

		JPanel accInfoPanel = new JPanel();
		accInfoPanel.setLayout(null);
		accInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		accInfoPanel.setBackground(new Color(181, 218, 255));
		accInfoPanel.setBounds(282, 234, 255, 128);
		contentPane.add(accInfoPanel);

		JLabel accNumLabel = new JLabel("�Աݰ��¹�ȣ");
		accNumLabel.setFont(new Font("���� ����", Font.PLAIN, 14));
		accNumLabel.setBounds(21, 12, 84, 27);
		accInfoPanel.add(accNumLabel);

		accNumTx = new JTextField();
		accNumTx.setColumns(10);
		accNumTx.setBounds(117, 13, 116, 30);
		accInfoPanel.add(accNumTx);

		JLabel accBankLabel = new JLabel("�Ա������");
		accBankLabel.setFont(new Font("���� ����", Font.PLAIN, 14));
		accBankLabel.setBounds(21, 49, 77, 27);
		accInfoPanel.add(accBankLabel);

		accBankTx = new JTextField();
		accBankTx.setColumns(10);
		accBankTx.setBounds(117, 50, 116, 30);
		accInfoPanel.add(accBankTx);

		JLabel accNameLabel = new JLabel("������");
		accNameLabel.setFont(new Font("���� ����", Font.PLAIN, 14));
		accNameLabel.setBackground(new Color(255, 128, 128));
		accNameLabel.setBounds(21, 86, 70, 27);
		accInfoPanel.add(accNameLabel);

		accNameTx = new JTextField();
		accNameTx.setColumns(10);
		accNameTx.setBounds(117, 87, 116, 30);
		accInfoPanel.add(accNameTx);

		JLabel accInfoLabel = new JLabel("������������");
		accInfoLabel.setFont(new Font("���� ����", Font.BOLD, 15));
		accInfoLabel.setBounds(282, 209, 91, 15);
		contentPane.add(accInfoLabel);

		JLabel contDateLabel_1 = new JLabel("������ [������]");
		contDateLabel_1.setFont(new Font("���� ����", Font.PLAIN, 17));
		contDateLabel_1.setBounds(405, 472, 132, 28);
		contentPane.add(contDateLabel_1);

		JButton applyBtn = new JButton("\uD655\uC778");
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WorkerView();
			}
		});
		applyBtn.setFont(new Font("���� ����", Font.BOLD, 20));
		applyBtn.setBounds(193, 506, 159, 48);
		contentPane.add(applyBtn);
		
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(413, 10, 120, 100);
		contentPane.add(logoLabel);

	}
}