package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

public class WorkerView extends JFrame {

	private JPanel managerMainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerView frame = new WorkerView();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WorkerView() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 메인 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1636, 891);
		managerMainPanel = new JPanel();
		managerMainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		managerMainPanel.setBackground(new Color(181, 218, 255));
		managerMainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(managerMainPanel);
		managerMainPanel.setLayout(null);

		// 우측상단 "해외파견시스템" 로고
		JLabel mainLogo = new JLabel("해외파견관리");
		mainLogo.setBounds(1478, 9, 155, 32);
		mainLogo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		managerMainPanel.add(mainLogo);
		
		//테이블박스 변수명은 workerListTB
		
		JPanel workerListPanel = new JPanel();
		workerListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel.setBounds(12, 109, 880, 659);
		managerMainPanel.add(workerListPanel);

		JPanel workerInfoPanel = new JPanel();
		workerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerInfoPanel.setBounds(904, 109, 704, 659);
		managerMainPanel.add(workerInfoPanel);

		JButton workerBtn = new JButton("파견인력관리");
		workerBtn.setForeground(new Color(0, 0, 0));
		workerBtn.setBackground(new Color(0, 0, 0));
		workerBtn.setBounds(147, 36, 142, 46);
		managerMainPanel.add(workerBtn);

		JButton reqBtn = new JButton("파견요청관리");
		reqBtn.setBounds(297, 36, 142, 46);
		managerMainPanel.add(reqBtn);

		JButton reqCountBtn = new JButton("계약관리");
		reqCountBtn.setBounds(447, 36, 142, 46);
		managerMainPanel.add(reqCountBtn);

		JButton sheetBtn = new JButton("정산관리");
		sheetBtn.setBounds(597, 36, 142, 46);
		managerMainPanel.add(sheetBtn);

		JButton payBtn = new JButton("수당관리");
		payBtn.setBounds(747, 36, 142, 46);
		
		managerMainPanel.add(payBtn);

		JButton reqInfoBtn = new JButton("계약정보확인");
		reqInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WorkerContView().workerAction();
			}
		});
		reqInfoBtn.setBounds(1166, 784, 142, 46);
		managerMainPanel.add(reqInfoBtn);

		JButton visaInsertBtn = new JButton("비자정보등록");
		visaInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VisaView().visaInfoAction();
			}
		});
		visaInsertBtn.setBounds(1316, 784, 142, 46);
		managerMainPanel.add(visaInsertBtn);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 2.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(162, 50, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JButton workerInsertBtn = new JButton("파견인력등록");
		workerInsertBtn.setBackground(new Color(255, 255, 255));
		workerInsertBtn.setForeground(new Color(255, 255, 255));
		workerInsertBtn.setIcon(changeIcon);
		workerInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WorkerInsertView().workerInsertAction();
			}
		});
		workerInsertBtn.setBounds(1466, 784, 142, 46);
		managerMainPanel.add(workerInsertBtn);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mainViewLogoLabel.setBounds(12, -2, 208, 55);
		managerMainPanel.add(mainViewLogoLabel);

		JLabel workerListLabel = new JLabel("파견인력목록");
		workerListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerListLabel.setBounds(12, 75, 113, 33);
		managerMainPanel.add(workerListLabel);

		JLabel workerinfoLabel = new JLabel("\uD30C\uACAC\uC778\uC6D0\uC0C1\uC138\uC815\uBCF4");
		workerinfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		workerinfoLabel.setBounds(904, 75, 135, 33);
		managerMainPanel.add(workerinfoLabel);

		JLabel managerLogoLabel = new JLabel("<관리자>");
		managerLogoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		managerLogoLabel.setBounds(22, 27, 208, 55);
		managerMainPanel.add(managerLogoLabel);
	}
}