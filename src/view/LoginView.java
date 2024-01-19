package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.LoginDAO;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField LoginIdTx;
	private JLabel LoginIdL;
	private JLabel LoginPwLabel;
	private JPasswordField LoginPwTx;
	private JSeparator separator;

	
	private String[] loginTypeSelectVal = { "관리자", "사업자회원", "개인회원" };
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();

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
	public LoginView() {

		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel LoginCustP = new JPanel();
		LoginCustP.setBackground(new Color(16, 24, 32));
		LoginCustP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		LoginCustP.setBounds(35, 112, 327, 454);
		contentPane.add(LoginCustP);
		LoginCustP.setLayout(null);

		

		JComboBox loginTypeComboBox = new JComboBox(loginTypeSelectVal);
		loginTypeComboBox.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		loginTypeComboBox.setForeground(new Color(242, 170, 76));
		loginTypeComboBox.setBackground(new Color(16, 24, 32));
		loginTypeComboBox.setBounds(89, 110, 148, 35);
		LoginCustP.add(loginTypeComboBox);

		LoginIdTx = new JTextField();
		LoginIdTx.setBounds(89, 177, 148, 36);
		LoginCustP.add(LoginIdTx);
		LoginIdTx.setColumns(10);

		LoginIdL = new JLabel("ID");
		LoginIdL.setForeground(new Color(242, 170, 76));
		LoginIdL.setBounds(89, 155, 57, 24);
		LoginIdL.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 12));
		LoginCustP.add(LoginIdL);

		LoginPwLabel = new JLabel("PASSWORD");
		LoginPwLabel.setForeground(new Color(242, 170, 76));
		LoginPwLabel.setBounds(89, 238, 81, 25);
		LoginPwLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 12));
		LoginCustP.add(LoginPwLabel);

		JButton newWorkerJoinBtn = new JButton(" 파견인력 개인정보 신규 등록");
		newWorkerJoinBtn.setBackground(new Color(16, 24, 32));
		newWorkerJoinBtn.setForeground(new Color(242, 170, 76));
		newWorkerJoinBtn.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 15));
		newWorkerJoinBtn.setBounds(18, 405, 290, 23);
		newWorkerJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// LoginJoinView loginJoin = new LoginJoinView();
				// loginJoin.login_Join();

			}
		});
		LoginCustP.add(newWorkerJoinBtn);
		// ┌───────────────────────────────────로그인 확인 버튼부분───────────────────────────────┐
		JButton LoginConfirmBtn = new JButton("\uD655\uC778");
		LoginConfirmBtn.setBackground(new Color(242, 170, 76));
		LoginConfirmBtn.setForeground(new Color(16, 24, 32));
		LoginConfirmBtn.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 15));
		LoginConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 로그인 타입
				String loginType = loginTypeComboBox.getSelectedItem().toString();
				// 아이디
//				String ID = LoginIdTx.getText();
				// 비밀번호
//				String PW = LoginPwTx.getText();
				
				String ID = "ysm94";
				String PW = "sm1234";

				try {
					LoginDAO dao = new LoginDAO();

					if (loginType == loginTypeSelectVal[0]) {

						// 관리자 view 실행부분
						if(dao.managerLoginCheck(ID, PW)) {
							new MManagerView(0).managerMainView(ID);
							dispose();
							
						}else {
							JOptionPane.showMessageDialog(LoginCustP, "로그인 실패 정보를 확인 해주세요");
						}


					} else if (loginType == loginTypeSelectVal[1]) {

						// 파견업체 view 실행부분
						if(dao.custLoginCheck(ID, PW)) {
							System.out.println("파견업체 view");
							dispose();
						}else {
							JOptionPane.showMessageDialog(LoginCustP, "로그인 실패 정보를 확인 해주세요");
						}

					} else if(loginType == loginTypeSelectVal[2]){

						// 파견자 view 실행부분
						if(dao.workerLoginCheck(ID, PW)) {
							System.out.println("파견자 view");
							dispose();
						}else {
							JOptionPane.showMessageDialog(LoginCustP, "로그인 실패 정보를 확인 해주세요");
						}

					}
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		// └───────────────────────────────────로그인 확인 버튼 부분───────────────────────────────┘
		LoginConfirmBtn.setBounds(115, 324, 97, 23);
		LoginCustP.add(LoginConfirmBtn);

		JLabel LabelLogin_mgr_1_1 = new JLabel("\uB85C\uADF8\uC778");
		LabelLogin_mgr_1_1.setForeground(new Color(242, 170, 76));
		LabelLogin_mgr_1_1.setBounds(89, 50, 148, 36);
		LoginCustP.add(LabelLogin_mgr_1_1);
		LabelLogin_mgr_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		LabelLogin_mgr_1_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 17));

		LoginPwTx = new JPasswordField();
		LoginPwTx.setBounds(89, 261, 148, 36);
		LoginCustP.add(LoginPwTx);

		separator = new JSeparator();
		separator.setBounds(89, 83, 148, 17);
		LoginCustP.add(separator);

		JLabel dispatchMainLabel = new JLabel("해외인력파견관리");
		dispatchMainLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 25));
		dispatchMainLabel.setBounds(107, 40, 184, 34);
		contentPane.add(dispatchMainLabel);
	}

	@SuppressWarnings("unused")
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
