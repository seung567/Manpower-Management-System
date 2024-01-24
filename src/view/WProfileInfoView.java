package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.ProfileInfoDAO;
import model.rec.WorkerVO;

public class WProfileInfoView extends JFrame {

	private JPanel WorkerPersonalInfoP;
	private JTextField WorkerPf_IdTx;
	private JTextField WorkerPf_nameTx;
	private JTextField WorkerPf_telTx;
	private JTextField WorkerPf_emailTx;
	private JTextField WorkerPf_addrTx;
	private JTextField WorkerPf_ageTx;
	private JTextField WorkerPf_rNumTx;
	private JPasswordField passwordField;

	ProfileInfoDAO dao;
	WorkerVO worker;
	String id;

	/**
	 * Launch the application.
	 */
	public static void profile_Info(String id) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WProfileInfoView frame = new WProfileInfoView(id);

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
							} else {
								frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
							}
						}
					});

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

	// 윈도우빌더 실행용
	public WProfileInfoView() {
		this.profile_Info("테스트");
	}

	// 다른 뷰에서 전환될 때 실행용
	public WProfileInfoView(int num) {
	}

	// 메인 실행
	public WProfileInfoView(String id) {
		this.id = id;
		// ┌─────────────────────── 개인프로필 상세보기
		// ───────────────────────────────────────────

		setTitle("\uAC1C\uC778\uD504\uB85C\uD544 \uC0C1\uC138\uBCF4\uAE30");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 465);
		setLocationRelativeTo(null);
		WorkerPersonalInfoP = new JPanel();
		WorkerPersonalInfoP.setBackground(new Color(242, 170, 76));
		WorkerPersonalInfoP.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(WorkerPersonalInfoP);
		WorkerPersonalInfoP.setLayout(null);

		JLabel WorkerProfilelInfo_perInfoLabel = new JLabel("\uAC1C\uC778\uC815\uBCF4");
		JPanel panel = new JPanel();
		JLabel WorkerPf_IdLabel = new JLabel("\uC544\uC774\uB514");
		JLabel WorkerPf_chngPWLabel = new JLabel("\uD604\uC7AC \uBE44\uBC00\uBC88\uD638 ");
		JLabel WorkerPf_nameLabel = new JLabel("\uC774\uB984");
		JLabel WorkerPf_telLabel = new JLabel("\uC5F0\uB77D\uCC98");
		WorkerPf_addrTx = new JTextField();
		WorkerPf_addrTx.setEditable(true);
		WorkerPf_nameTx = new JTextField();
		WorkerPf_nameTx.setEditable(true);
		WorkerPf_IdTx = new JTextField();
		WorkerPf_IdTx.setEditable(false);
		WorkerPf_telTx = new JTextField();
		WorkerPf_telTx.setEditable(true);
		WorkerPf_emailTx = new JTextField();
		WorkerPf_emailTx.setEditable(true);
		JLabel WorkerPf_emailLabel = new JLabel("Email");
		JLabel WorkerPf_addrLabel = new JLabel("\uC8FC\uC18C");
		JLabel WorkerPf_ageLabel = new JLabel("\uB098\uC774");
		WorkerPf_ageTx = new JTextField();
		WorkerPf_ageTx.setEditable(true);
		JLabel WorkerPf_rNumLabel = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		WorkerPf_rNumTx = new JTextField();
		WorkerPf_rNumTx.setEditable(true);
		passwordField = new JPasswordField();
		passwordField.setEditable(true);

		WorkerProfilelInfo_perInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		WorkerProfilelInfo_perInfoLabel.setBounds(12, 10, 147, 30);
		WorkerPersonalInfoP.add(WorkerProfilelInfo_perInfoLabel);

		JButton WorkerPf_ConfBtn = new JButton("\uD655\uC778");
		WorkerPf_ConfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		WorkerPf_ConfBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		WorkerPf_ConfBtn.setBackground(new Color(16, 24, 32));
		WorkerPf_ConfBtn.setBounds(416, 381, 97, 23);
		WorkerPersonalInfoP.add(WorkerPf_ConfBtn);

		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(16, 24, 32));
		panel.setBounds(12, 39, 499, 306);
		WorkerPersonalInfoP.add(panel);

		WorkerPf_IdLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_IdLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_IdLabel.setBounds(24, 11, 118, 23);
		panel.add(WorkerPf_IdLabel);

		WorkerPf_IdTx.setForeground(Color.BLACK);
		WorkerPf_IdTx.setColumns(10);
		WorkerPf_IdTx.setBounds(212, 11, 169, 23);
		panel.add(WorkerPf_IdTx);

		WorkerPf_chngPWLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_chngPWLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_chngPWLabel.setBounds(24, 45, 118, 23);
		panel.add(WorkerPf_chngPWLabel);

		WorkerPf_nameLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_nameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_nameLabel.setBounds(24, 78, 57, 23);
		panel.add(WorkerPf_nameLabel);

		WorkerPf_nameTx.setColumns(10);
		WorkerPf_nameTx.setBounds(212, 78, 170, 23);
		panel.add(WorkerPf_nameTx);

		WorkerPf_telLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_telLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_telLabel.setBounds(24, 180, 57, 23);
		panel.add(WorkerPf_telLabel);

		WorkerPf_telTx.setColumns(10);
		WorkerPf_telTx.setBounds(212, 180, 169, 23);
		panel.add(WorkerPf_telTx);

		WorkerPf_emailLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_emailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_emailLabel.setBounds(24, 214, 57, 23);
		panel.add(WorkerPf_emailLabel);

		WorkerPf_emailTx.setColumns(10);
		WorkerPf_emailTx.setBounds(212, 214, 169, 23);
		panel.add(WorkerPf_emailTx);

		WorkerPf_addrLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_addrLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_addrLabel.setBounds(24, 248, 57, 23);
		panel.add(WorkerPf_addrLabel);

		WorkerPf_addrTx.setColumns(10);
		WorkerPf_addrTx.setBounds(212, 248, 169, 23);
		panel.add(WorkerPf_addrTx);

		WorkerPf_ageLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_ageLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_ageLabel.setBounds(24, 112, 118, 23);
		panel.add(WorkerPf_ageLabel);

		WorkerPf_ageTx.setColumns(10);
		WorkerPf_ageTx.setBounds(212, 112, 170, 23);
		panel.add(WorkerPf_ageTx);

		WorkerPf_rNumLabel.setForeground(new Color(242, 170, 76));
		WorkerPf_rNumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerPf_rNumLabel.setBounds(24, 146, 118, 23);
		panel.add(WorkerPf_rNumLabel);

		WorkerPf_rNumTx.setColumns(10);
		WorkerPf_rNumTx.setBounds(211, 146, 170, 23);
		panel.add(WorkerPf_rNumTx);

		passwordField.setBounds(212, 44, 169, 21);
		panel.add(passwordField);

		JButton WorkerPf_ModifyBtn = new JButton("\uC218\uC815");
		WorkerPf_ModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String workerID = WorkerPf_IdTx.getText();
				String workerPW = passwordField.getText();
				String workerName = WorkerPf_nameTx.getText();
				String workerAddr = WorkerPf_addrTx.getText();
				String workerTel = WorkerPf_telTx.getText();
				String workerEmail = WorkerPf_emailTx.getText();
				String workerAge = WorkerPf_ageTx.getText();
				String workerRnum = WorkerPf_rNumTx.getText();

				try {
					worker = new WorkerVO(workerName, workerPW, workerTel, workerEmail, workerAddr, workerID, workerAge,
							workerRnum);
					dao.workerPfInfoupdate(worker);
					
					JOptionPane.showMessageDialog(null, "개인 프로필 수정 완료");
					
					
				} catch (Exception e4) {
					// TODO: handle exception
					System.out.println(e4.getMessage());
					e4.printStackTrace();
					
				}
			}
		});

		WorkerPf_ModifyBtn.setForeground(new Color(242, 170, 76));
		WorkerPf_ModifyBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		WorkerPf_ModifyBtn.setBackground(new Color(16, 24, 32));
		WorkerPf_ModifyBtn.setBounds(294, 382, 97, 23);
		WorkerPersonalInfoP.add(WorkerPf_ModifyBtn);

		// 개인등록정보 -> 개인프로필에 불러오기
		// public void insertPfModifyView() {

		try {
			dao = new ProfileInfoDAO();
			worker = dao.workerPfInfoSelect(id);

			WorkerPf_IdTx.setText(id);
			passwordField.setText(worker.getWorkerPW());
			WorkerPf_nameTx.setText(worker.getWorkerName());
			WorkerPf_ageTx.setText(worker.getWorkerAge());
			WorkerPf_rNumTx.setText(worker.getWorkerRnum());
			WorkerPf_telTx.setText(worker.getWorkerTel());
			WorkerPf_emailTx.setText(worker.getWorkerEmail());
			WorkerPf_addrTx.setText(worker.getWorkerAddr());

		} catch (Exception e3) {
			// TODO: handle exception
			System.out.println(e3.getMessage());
			e3.printStackTrace();
		}
	}

	public void updatePfInfoView() {

		try {
			dao = new ProfileInfoDAO();
			worker = dao.workerPfInfoSelect(id);

			WorkerPf_IdTx.setText(id);
			WorkerPf_nameTx.setText(worker.getWorkerName());
			passwordField.setText(worker.getWorkerPW());
			WorkerPf_telTx.setText(worker.getWorkerTel());
			WorkerPf_rNumTx.setText(worker.getWorkerRnum());
			WorkerPf_emailTx.setText(worker.getWorkerEmail());
			WorkerPf_ageTx.setText(worker.getWorkerAge());
			WorkerPf_addrTx.setText(worker.getWorkerAddr());
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		}
	}

}
