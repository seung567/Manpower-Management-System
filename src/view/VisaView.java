package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class VisaView extends JFrame {

	private JPanel visaContent;
	private JTextField visaSDateTx;
	private JTextField visaEsDateTx;
	private JTextField minPeriodTx;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void visaInfoAction() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					VisaView frame = new VisaView();
					frame.setVisible(true);

					// �⺻ x ������ â�������� ����
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

				} // try ����
				catch (Exception e) {
					e.printStackTrace();
				} // catch ����

			}
		}); // ��ϸӽ� Ŭ���� ����

	} // class ����

	/**
	 * Create the frame.
	 */
	public VisaView() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.put("Button.background", Color.RED);
			// Icon defaultIcon = new ImageIcon("path/to/defaultIcon.png");
			// UIManager.put("OptionPane.informationIcon", defaultIcon);

		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 585);
		visaContent = new JPanel();
		visaContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		visaContent.setBackground(new Color(181, 218, 255));
		visaContent.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(visaContent);
		visaContent.setLayout(null);

		JLabel visaMainLogoLabel = new JLabel("���ڵ��");
		visaMainLogoLabel.setBounds(12, 10, 68, 24);
		visaMainLogoLabel.setFont(new Font("���� ���", Font.BOLD, 17));
		visaContent.add(visaMainLogoLabel);

		JPanel visaIssuInfoPanel = new JPanel();
		visaIssuInfoPanel.setBounds(12, 250, 252, 128);
		visaIssuInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		visaIssuInfoPanel.setBackground(new Color(181, 218, 255));
		visaContent.add(visaIssuInfoPanel);
		visaIssuInfoPanel.setLayout(null);

		JLabel visaSDateLabel = new JLabel("���ڹ߱���");
		visaSDateLabel.setBounds(12, 12, 69, 24);
		visaIssuInfoPanel.add(visaSDateLabel);

		visaSDateTx = new JTextField();
		visaSDateTx.setBounds(123, 12, 116, 26);
		visaIssuInfoPanel.add(visaSDateTx);
		visaSDateTx.setColumns(10);

		JLabel visaEsDateLabel = new JLabel("���ڹ߱޿�����");
		visaEsDateLabel.setBounds(12, 47, 87, 24);
		visaIssuInfoPanel.add(visaEsDateLabel);

		visaEsDateTx = new JTextField();
		visaEsDateTx.setColumns(10);
		visaEsDateTx.setBounds(123, 47, 116, 26);
		visaIssuInfoPanel.add(visaEsDateTx);

		JLabel minPeriodLabel = new JLabel("���ڹ߱��ּұⰣ");
		minPeriodLabel.setBounds(12, 82, 96, 24);
		visaIssuInfoPanel.add(minPeriodLabel);

		minPeriodTx = new JTextField();
		minPeriodTx.setColumns(10);
		minPeriodTx.setBounds(123, 81, 116, 26);
		visaIssuInfoPanel.add(minPeriodTx);

		JLabel visaInfoLabel1 = new JLabel("���ڹ߱�����");
		visaInfoLabel1.setFont(new Font("���� ���", Font.BOLD, 12));
		visaInfoLabel1.setBounds(12, 225, 85, 15);
		visaContent.add(visaInfoLabel1);

		JLabel workerCodeLabel = new JLabel("�İ��η°�����ȣ");
		workerCodeLabel.setBounds(63, 126, 129, 15);
		workerCodeLabel.setFont(new Font("���� ���", Font.BOLD, 16));
		visaContent.add(workerCodeLabel);

		JLabel workerCodeNumLabel = new JLabel("[�η¹�ȣ�Է���ġ]");
		workerCodeNumLabel.setBounds(57, 158, 140, 15);
		workerCodeNumLabel.setFont(new Font("���� ���", Font.BOLD, 16));
		visaContent.add(workerCodeNumLabel);

		JLabel contryTitleLabel = new JLabel("��������");
		contryTitleLabel.setBounds(251, 109, 64, 24);
		contryTitleLabel.setFont(new Font("���� ���", Font.BOLD, 16));
		visaContent.add(contryTitleLabel);

		JLabel visaInfoLabel = new JLabel("��������");
		visaInfoLabel.setFont(new Font("���� ���", Font.BOLD, 12));
		visaInfoLabel.setBounds(276, 225, 57, 15);
		visaContent.add(visaInfoLabel);

		// new DefaultComboBoxModel �� �޺��ڽ��� �����͸� ȿ�������� ���� �ϱ� ���� ���

		String[] contryList = { "�ѱ�", "�̱�", "������", "�����" }; // �������� ����Ʈ

		JComboBox contryCombox = new JComboBox();
		contryCombox.setModel(new DefaultComboBoxModel(contryList));
		contryCombox.setBounds(364, 109, 146, 23);
		visaContent.add(contryCombox);

		JLabel visaCkLabel = new JLabel("���ڹ߱޿���");
		visaCkLabel.setFont(new Font("���� ���", Font.BOLD, 16));
		visaCkLabel.setBounds(251, 141, 96, 24);
		visaContent.add(visaCkLabel);

		String[] visaCheckList = { "�̹߱�", "�߱���", "�߱޿Ϸ�" }; // ���� �߱� ���� ���� ����Ʈ

		JComboBox visaCkCombox = new JComboBox();
		visaCkCombox.setModel(new DefaultComboBoxModel(visaCheckList));
		visaCkCombox.setBounds(364, 141, 146, 23);
		visaContent.add(visaCkCombox);

		JLabel visaIssuerLabel = new JLabel("���ڹ߱�ó");
		visaIssuerLabel.setFont(new Font("���� ���", Font.BOLD, 16));
		visaIssuerLabel.setBounds(251, 179, 80, 15);
		visaContent.add(visaIssuerLabel);

		String[] visaIssuerList = { "���Ѵ���", "�̴ֹ���", "������������", "�ֺ��������" }; // �������� ����Ʈ

		JComboBox visaIssuerLabelCombox = new JComboBox();
		visaIssuerLabelCombox.setModel(new DefaultComboBoxModel(visaIssuerList));
		visaIssuerLabelCombox.setBounds(364, 174, 146, 23);
		visaContent.add(visaIssuerLabelCombox);

		JPanel visaInfoPanel = new JPanel();
		visaInfoPanel.setLayout(null);
		visaInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0)));
		visaInfoPanel.setBackground(new Color(181, 218, 255));
		visaInfoPanel.setBounds(276, 250, 234, 128);
		visaContent.add(visaInfoPanel);

		JLabel visaSDateLabel_1 = new JLabel("������ȿ�Ⱓ");
		visaSDateLabel_1.setBounds(12, 12, 80, 24);
		visaInfoPanel.add(visaSDateLabel_1);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(104, 12, 116, 26);
		visaInfoPanel.add(textField);

		JLabel visaEsDateLabel_1 = new JLabel("��������");
		visaEsDateLabel_1.setBounds(12, 47, 87, 24);
		visaInfoPanel.add(visaEsDateLabel_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(104, 47, 116, 26);
		visaInfoPanel.add(textField_1);

		JButton visaInsertBtn = new JButton("�����������");
		visaInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WorkerView();
			}
		});
		visaInsertBtn.setBounds(196, 422, 129, 37);
		visaContent.add(visaInsertBtn);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(390, 10, 120, 100);
		visaContent.add(logoLabel);

	}

	// public static void windowClosing(WindowEvent e) {
	// JFrame visaContent = (JFrame)e.getWindow();
	// visaContent.dispose();
	// new Worker();
	// System.out.println("windowClosing()");
	// }
}
