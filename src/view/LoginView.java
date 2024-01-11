package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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

	private LoginDAO dao = null;
	


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
//
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel LoginCustP = new JPanel();
		LoginCustP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		LoginCustP.setBounds(520, 69, 327, 454);
		contentPane.add(LoginCustP);
		LoginCustP.setLayout(null);

		LoginIdTx = new JTextField();
		LoginIdTx.setBounds(85, 152, 148, 36);
		LoginCustP.add(LoginIdTx);
		LoginIdTx.setColumns(10);

		LoginIdL = new JLabel("ID");
		LoginIdL.setBounds(85, 130, 57, 24);
		LoginIdL.setFont(new Font("    ", Font.BOLD, 13));
		LoginCustP.add(LoginIdL);

		LoginPwLabel = new JLabel("PASSWORD");
		LoginPwLabel.setBounds(86, 213, 81, 25);
		LoginPwLabel.setFont(new Font("    ", Font.BOLD, 13));
		LoginCustP.add(LoginPwLabel);

		JButton newWorkerJoinBtn = new JButton(" 파견인력 개인정보 신규 등록");
		newWorkerJoinBtn.setBounds(18, 405, 290, 23);
		newWorkerJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//            LoginJoin loginJoin = new LoginJoin();
				//            loginJoin.login_Join();

			}
		});
		LoginCustP.add(newWorkerJoinBtn);

		JButton LoginConfirmBtn = new JButton("로그인");
		LoginConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				try {
//					
//					idText = LoginIdTx.getText();
//					dao = new LoginDAO();
//					String state = dao.loginCustomCheck(idText);			
//					
//					if(state == "관리자") {
//						new ManagerView();
//						System.out.println("관리자 로그인");// 관리자 뷰
//						
//					}else if(state == "사용업체") {
//						new WorkerContInfoView().Action(); // 업체 뷰
//						
//					}else if(state == "파견인력") {
//						new WorkerContInsertView().Action(); // 인력 뷰
//						
//					}else {
//						System.out.println("검색불가");
//					}
//					
//				} catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//				}
				
				String idText = LoginIdTx.getText();
				String[] arrayID = new String[1];
				
				arrayID[0] = idText;
				
				System.out.println(arrayID[0] + " 로그인view");
				
				new ManagerView().main(arrayID);;
			}
		});
		LoginConfirmBtn.setBounds(114, 292, 97, 23);
		LoginCustP.add(LoginConfirmBtn);

		JLabel LabelLogin_mgr_1_1 = new JLabel("\uB85C\uADF8\uC778");
		LabelLogin_mgr_1_1.setBounds(85, 34, 141, 36);
		LoginCustP.add(LabelLogin_mgr_1_1);
		LabelLogin_mgr_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		LabelLogin_mgr_1_1.setFont(new Font("        ", Font.BOLD, 16));

		LoginPwTx = new JPasswordField();
		LoginPwTx.setBounds(85, 236, 148, 36);
		LoginCustP.add(LoginPwTx);

		separator = new JSeparator();
		separator.setBounds(114, 66, 81, 17);
		LoginCustP.add(separator);
	}
	


}