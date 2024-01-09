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
					// 기본 프레임 명령어
					WorkerInsertView frame = new WorkerInsertView();
					frame.setVisible(true);

					// 팝업창 x 버튼 누를시 창만 꺼지게 해주는 명령어
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
					// 여기까지
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
		setBounds(100, 100, 491, 643);
		workerInsertMainPanel = new JPanel();
		workerInsertMainPanel.setBackground(new Color(242, 170, 76));
		workerInsertMainPanel.setBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(workerInsertMainPanel);
		workerInsertMainPanel.setLayout(null);

		JLabel workerInsertLabel = new JLabel("파견인력등록");
		workerInsertLabel.setBounds(12, 10, 112, 27);
		workerInsertLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		workerInsertMainPanel.add(workerInsertLabel);

		JLabel loginLabel = new JLabel("로그인 정보");
		loginLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		loginLabel.setBounds(12, 84, 81, 15);
		workerInsertMainPanel.add(loginLabel);

		JPanel loginInfoPanel = new JPanel();
		loginInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		loginInfoPanel.setBackground(new Color(16, 24, 32));
		loginInfoPanel.setBounds(12, 104, 451, 74);
		workerInsertMainPanel.add(loginInfoPanel);
		loginInfoPanel.setLayout(null);

		JLabel workerIDLabel = new JLabel("아이디");
		workerIDLabel.setForeground(new Color(255, 255, 255));
		workerIDLabel.setBounds(76, 10, 45, 21);
		workerIDLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		loginInfoPanel.add(workerIDLabel);

		workerIDTx = new JTextField();
		workerIDTx.setBounds(159, 10, 116, 21);
		workerIDTx.setColumns(10);
		loginInfoPanel.add(workerIDTx);

		JLabel workerPWLabel = new JLabel("비밀번호");
		workerPWLabel.setForeground(new Color(255, 255, 255));
		workerPWLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPWLabel.setBounds(76, 41, 69, 21);
		loginInfoPanel.add(workerPWLabel);

		workerPWTx = new JTextField();
		workerPWTx.setColumns(10);
		workerPWTx.setBounds(159, 41, 116, 21);
		loginInfoPanel.add(workerPWTx);

		JButton workerIDCkBtn = new JButton("중복체크");
		workerIDCkBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerIDCkBtn.setForeground(new Color(255, 255, 255));
		workerIDCkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 중복체크 메소드
			}
		});
		workerIDCkBtn.setBackground(new Color(16, 24, 32));
		workerIDCkBtn.setBounds(287, 10, 97, 21);
		loginInfoPanel.add(workerIDCkBtn);

		JLabel basicLabel = new JLabel("기본정보");
		basicLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		basicLabel.setBounds(12, 188, 70, 15);
		workerInsertMainPanel.add(basicLabel);

		JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setBackground(new Color(16, 24, 32));
		basicInfoPanel.setLayout(null);
		basicInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		basicInfoPanel.setBounds(12, 207, 451, 107);
		workerInsertMainPanel.add(basicInfoPanel);

		JLabel workerNameLabel = new JLabel("이름");
		workerNameLabel.setForeground(new Color(255, 255, 255));
		workerNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerNameLabel.setBounds(12, 10, 45, 21);
		basicInfoPanel.add(workerNameLabel);

		workerNameTx = new JTextField();
		workerNameTx.setColumns(10);
		workerNameTx.setBounds(51, 10, 116, 21);
		basicInfoPanel.add(workerNameTx);

		JLabel workerTelLabel = new JLabel("전화번호");
		workerTelLabel.setForeground(new Color(255, 255, 255));
		workerTelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerTelLabel.setBounds(12, 41, 69, 21);
		basicInfoPanel.add(workerTelLabel);

		workerTelTx = new JTextField();
		workerTelTx.setColumns(10);
		workerTelTx.setBounds(83, 41, 106, 21);
		basicInfoPanel.add(workerTelTx);

		JLabel workerAddrLabel = new JLabel("주소");
		workerAddrLabel.setForeground(new Color(255, 255, 255));
		workerAddrLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerAddrLabel.setBounds(12, 72, 45, 21);
		basicInfoPanel.add(workerAddrLabel);

		workerAddrTx = new JTextField();
		workerAddrTx.setColumns(10);
		workerAddrTx.setBounds(51, 72, 388, 21);
		basicInfoPanel.add(workerAddrTx);

		JLabel workerRnumLabel = new JLabel("주민번호");
		workerRnumLabel.setForeground(new Color(255, 255, 255));
		workerRnumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerRnumLabel.setBounds(179, 10, 69, 21);
		basicInfoPanel.add(workerRnumLabel);

		workerRnumTx = new JTextField();
		workerRnumTx.setColumns(10);
		workerRnumTx.setBounds(248, 10, 191, 21);
		basicInfoPanel.add(workerRnumTx);

		JLabel workerEmailLabel = new JLabel("이메일");
		workerEmailLabel.setForeground(new Color(255, 255, 255));
		workerEmailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerEmailLabel.setBounds(201, 41, 69, 21);
		basicInfoPanel.add(workerEmailLabel);

		workerEmailTx = new JTextField();
		workerEmailTx.setColumns(10);
		workerEmailTx.setBounds(258, 41, 181, 21);
		basicInfoPanel.add(workerEmailTx);

		JLabel careerInfoLabel = new JLabel("경력정보");
		careerInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		careerInfoLabel.setBounds(12, 324, 70, 15);
		workerInsertMainPanel.add(careerInfoLabel);

		JPanel careerInfoPanel = new JPanel();
		careerInfoPanel.setLayout(null);
		careerInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		careerInfoPanel.setBackground(new Color(16, 24, 32));
		careerInfoPanel.setBounds(12, 343, 451, 176);
		workerInsertMainPanel.add(careerInfoPanel);

		JLabel careerPeriodLabel = new JLabel("경력기간");
		careerPeriodLabel.setForeground(new Color(255, 255, 255));
		careerPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerPeriodLabel.setBounds(12, 13, 69, 21);
		careerInfoPanel.add(careerPeriodLabel);

		careerPeriodTx = new JTextField();
		careerPeriodTx.setColumns(10);
		careerPeriodTx.setBounds(93, 13, 106, 21);
		careerInfoPanel.add(careerPeriodTx);

		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setForeground(new Color(255, 255, 255));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerDetailLabel.setBounds(12, 72, 69, 21);
		careerInfoPanel.add(careerDetailLabel);

		careerDetailTx = new JTextField();
		careerDetailTx.setColumns(10);
		careerDetailTx.setBounds(12, 96, 427, 66);
		careerInfoPanel.add(careerDetailTx);

		JLabel skillcodeLabel = new JLabel("기술분류");
		skillcodeLabel.setForeground(new Color(255, 255, 255));
		skillcodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		skillcodeLabel.setBounds(12, 44, 69, 21);
		careerInfoPanel.add(skillcodeLabel);

		skillcodeTx = new JTextField();
		skillcodeTx.setColumns(10);
		skillcodeTx.setBounds(93, 44, 106, 21);
		careerInfoPanel.add(skillcodeTx);

		JButton workerInsertBtn = new JButton("계약정보등록");
		workerInsertBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerInsertBtn.setForeground(new Color(255, 255, 255));
		workerInsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WorkerContInsertView().Action();
			}
		});

		workerInsertBtn.setBackground(new Color(16, 24, 32));
		workerInsertBtn.setBounds(173, 540, 129, 37);
		workerInsertMainPanel.add(workerInsertBtn);

		ImageIcon icon = new ImageIcon("C:\\Users\\bri\\Desktop\\1x\\Artboard 1.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);

		JLabel logoLabel = new JLabel(changeIcon);
		logoLabel.setBounds(351, 10, 112, 91);
		workerInsertMainPanel.add(logoLabel);
	}

	public WorkerInsertView getInstance() {
		return this;
	}
}
