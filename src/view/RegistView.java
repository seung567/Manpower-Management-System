//package view;
//
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.EtchedBorder;
//
//import model.rec.WorkerContVO;
//import model.rec.WorkerVO;
//
//public class RegistView extends JFrame {
//
//	private JPanel LoginJoinInfoP;
//	private JTextField WorJoinInfo_nameTx;
//	private JTextField WorJoinInfo_emailTx;
//	private JTextField WorJoinInfo_IdTx;
//	private JTextField WorkerJoinInfo_telTx;
//	private JTextField WorJoinInfo_addrTx;
//	private JTextField WorJoinInfo_PwTx;
//	private JTextField WorJoinInfo_PwConfTx;
//	private JTextField WorJoinInfo_ageTx;
//	private JTextField WorJoinInfo_rnumTx;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void login_Join() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//
//				try {
//					RegistView frame = new RegistView();
//
//					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//					frame.addWindowListener(new WindowAdapter() {
//						@Override
//						public void windowClosing(WindowEvent e) {
//							// TODO Auto-generated method stub
//							// super.windowClosing(e);
//
//							int result = JOptionPane.showConfirmDialog(frame, "창을 닫으시겠습니까?", "확인",
//									JOptionPane.YES_NO_OPTION);
//							if (result == JOptionPane.YES_OPTION) {
//								frame.dispose();
//							}else  {
//							}
//						}
//					});
//
//					frame.setVisible(true);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public RegistView() {
//
//		//       try {
//		//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		//            
//		//         } catch (Exception e) {
//		//            e.printStackTrace();
//		//         }
//
//
//		setTitle(" \uD30C\uACAC\uC778\uB825 \uAC1C\uC778\uC815\uBCF4 \uC2E0\uADDC \uB4F1\uB85D");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 541, 481);
//		setLocationRelativeTo(null);
//		LoginJoinInfoP = new JPanel();
//		LoginJoinInfoP.setBackground(new Color(242, 170, 76));
//		LoginJoinInfoP.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(LoginJoinInfoP);
//		LoginJoinInfoP.setLayout(null);
//
//		JLabel WorkerJoinInfo_perInfoLabel = new JLabel("개인정보 등록");
//		WorkerJoinInfo_perInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
//		WorkerJoinInfo_perInfoLabel.setBounds(12, 10, 147, 30);
//		LoginJoinInfoP.add(WorkerJoinInfo_perInfoLabel);
//
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(16, 24, 32));
//		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//		panel.setBounds(12, 45, 499, 353);
//		LoginJoinInfoP.add(panel);
//		panel.setLayout(null);
//
//		JLabel WorJoinInfo_IdLabel = new JLabel("아이디");
//		WorJoinInfo_IdLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_IdLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_IdLabel.setBounds(24, 11, 118, 23);
//		panel.add(WorJoinInfo_IdLabel);
//
//		WorJoinInfo_IdTx = new JTextField();
//		WorJoinInfo_IdTx.setBounds(182, 11, 169, 23);
//		panel.add(WorJoinInfo_IdTx);
//		WorJoinInfo_IdTx.setForeground(new Color(0, 0, 0));
//		WorJoinInfo_IdTx.setColumns(10);
//
//		JButton WorJoinInfo_IdConftn = new JButton("중복확인");
//		WorJoinInfo_IdConftn.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_IdConftn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
//		WorJoinInfo_IdConftn.setBounds(376, 11, 97, 23);
//		panel.add(WorJoinInfo_IdConftn);
//		WorJoinInfo_IdConftn.setBackground(new Color(16, 24, 32));
//
//		JLabel WorkerJoinInfo_PwLabel = new JLabel("비밀번호");
//		WorkerJoinInfo_PwLabel.setForeground(new Color(242, 170, 76));
//		WorkerJoinInfo_PwLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorkerJoinInfo_PwLabel.setBounds(24, 45, 118, 23);
//		panel.add(WorkerJoinInfo_PwLabel);
//
//		WorJoinInfo_PwTx = new JTextField();
//		WorJoinInfo_PwTx.setBounds(182, 45, 169, 23);
//		panel.add(WorJoinInfo_PwTx);
//		WorJoinInfo_PwTx.setColumns(10);
//
//		JLabel WorJoinInfo_PwConfLabel = new JLabel("비밀번호 확인");
//		WorJoinInfo_PwConfLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_PwConfLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_PwConfLabel.setBounds(24, 79, 118, 23);
//		panel.add(WorJoinInfo_PwConfLabel);
//
//		WorJoinInfo_PwConfTx = new JTextField();
//		WorJoinInfo_PwConfTx.setBounds(182, 79, 169, 23);
//		panel.add(WorJoinInfo_PwConfTx);
//		WorJoinInfo_PwConfTx.setColumns(10);
//
//		JLabel WorJoinInfo_nameLabel = new JLabel("이름");
//		WorJoinInfo_nameLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_nameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_nameLabel.setBounds(24, 113, 57, 23);
//		panel.add(WorJoinInfo_nameLabel);
//
//		WorJoinInfo_nameTx = new JTextField();
//		WorJoinInfo_nameTx.setBounds(182, 113, 170, 23);
//		panel.add(WorJoinInfo_nameTx);
//		WorJoinInfo_nameTx.setColumns(10);
//
//		JLabel WorJoinInfo_telLabel = new JLabel("연락처");
//		WorJoinInfo_telLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_telLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_telLabel.setBounds(24, 215, 57, 23);
//		panel.add(WorJoinInfo_telLabel);
//
//		WorkerJoinInfo_telTx = new JTextField();
//		WorkerJoinInfo_telTx.setBounds(182, 215, 169, 23);
//		panel.add(WorkerJoinInfo_telTx);
//		WorkerJoinInfo_telTx.setColumns(10);
//
//		JLabel WorJoinInfo_emailLabel = new JLabel("Email");
//		WorJoinInfo_emailLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_emailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_emailLabel.setBounds(24, 249, 57, 23);
//		panel.add(WorJoinInfo_emailLabel);
//
//		WorJoinInfo_emailTx = new JTextField();
//		WorJoinInfo_emailTx.setBounds(182, 249, 169, 23);
//		panel.add(WorJoinInfo_emailTx);
//		WorJoinInfo_emailTx.setColumns(10);
//
//		JLabel WorJoinInfo_addrLabel = new JLabel("주소");
//		WorJoinInfo_addrLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_addrLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_addrLabel.setBounds(24, 283, 57, 23);
//		panel.add(WorJoinInfo_addrLabel);
//
//		WorJoinInfo_addrTx = new JTextField();
//		WorJoinInfo_addrTx.setBounds(182, 283, 169, 23);
//		panel.add(WorJoinInfo_addrTx);
//		WorJoinInfo_addrTx.setColumns(10);
//
//		JLabel WorJoinInfo_birthLabel_1 = new JLabel("\uB098\uC774");
//		WorJoinInfo_birthLabel_1.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_birthLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_birthLabel_1.setBounds(24, 147, 118, 23);
//		panel.add(WorJoinInfo_birthLabel_1);
//
//		WorJoinInfo_ageTx = new JTextField();
//		WorJoinInfo_ageTx.setColumns(10);
//		WorJoinInfo_ageTx.setBounds(182, 147, 170, 23);
//		panel.add(WorJoinInfo_ageTx);
//
//		JLabel WorJoinInfo_birthLabel_1_1 = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
//		WorJoinInfo_birthLabel_1_1.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_birthLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_birthLabel_1_1.setBounds(24, 181, 118, 23);
//		panel.add(WorJoinInfo_birthLabel_1_1);
//
//		WorJoinInfo_rnumTx = new JTextField();
//		WorJoinInfo_rnumTx.setColumns(10);
//		WorJoinInfo_rnumTx.setBounds(181, 181, 170, 23);
//		panel.add(WorJoinInfo_rnumTx);
//
//		JLabel WorJoinInfo_skillLabel = new JLabel("\uAE30\uC220\uBD84\uB958\uCF54\uB4DC");
//		WorJoinInfo_skillLabel.setForeground(new Color(242, 170, 76));
//		WorJoinInfo_skillLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
//		WorJoinInfo_skillLabel.setBounds(24, 319, 118, 23);
//		panel.add(WorJoinInfo_skillLabel);
//
//		JComboBox WorJoinInfo_skillCbox = new JComboBox();
//		WorJoinInfo_skillCbox.setModel(new DefaultComboBoxModel(new String[] {"\uC9C0\uD615\uC870\uC0AC \uBC0F \uACF5\uD559", "\uAD6C\uC870\uBB3C \uC124\uACC4 \uBC0F \uAC15\uB3C4 \uBD84\uC11D", "\uAC74\uCD95 \uB514\uC790\uC778 \uBC0F \uC2DC\uACF5 \uAE30\uC220", "\uAC74\uCD95 \uC790\uC7AC \uBC0F \uAE30\uC220\uC801 \uD2B9\uC131 \uC774\uD574", "\uC885\uD569\uC801 \uD504\uB85C\uC81D\uD2B8 \uAD00\uB9AC", "\uC548\uC804 \uAD00\uB9AC \uBC0F \uD488\uC9C8\uAD00\uB9AC", "\uACF5\uC815 \uC124\uACC4 \uBC0F \uC790\uB3D9\uD654 \uAE30\uC220", "\uD658\uACBD \uBCF4\uC804 \uBC0F \uB300\uAE30 \uC815\uD654 \uAE30\uC220", "\uD504\uB85C\uADF8\uB798\uBC0D \uC5B8\uC5B4 \uBC0F \uAE30\uC220 \uC2B5\uB4DD", "\uC2DC\uC2A4\uD15C \uAD00\uB9AC \uBC0F \uC720\uC9C0\uBCF4\uC218", "\uC18C\uD504\uD2B8\uC6E8\uC5B4 \uAC1C\uBC1C \uB77C\uC774\uD504\uC0AC\uC774\uD074 \uC774\uD574", "\uC54C\uACE0\uB9AC\uC998 \uBC0F \uBB38\uC81C \uD574\uACB0 \uB2A5\uB825", "\uB370\uC774\uD130\uBCA0\uC774\uC2A4 \uC124\uACC4 \uBC0F \uAD00\uB9AC", "\uC628\uB77C\uC778 \uC815\uBCF4 \uC2DC\uC2A4\uD15C \uAD6C\uCD95", "\uAD50\uC721 \uBC29\uBC95\uB860 \uBC0F \uAD50\uC218 \uAE30\uC220", "\uAD50\uC721\uC815\uCC45 \uBC0F \uCEE8\uC124\uD305 \uB2A5\uB825", "\uD504\uB85C\uC81D\uD2B8 \uAD00\uB9AC \uBC0F \uACC4\uD68D \uB2A5\uB825", "\uC758\uB8CC\uAE30\uAE30 \uC124\uACC4 \uBC0F \uAC1C\uBC1C", "\uC18C\uC7AC \uBC0F \uC12C\uC720 \uAE30\uC220 \uC774\uD574", "\uC758\uB958 \uC81C\uC791 \uAE30\uC220", "\uC790\uB3D9\uCC28 \uBD80\uD488 \uC124\uACC4 \uBC0F \uC81C\uC791", "\uD488\uC9C8 \uAD00\uB9AC \uBC0F \uD14C\uC2A4\uD2B8", "\uC804\uC790 \uC81C\uD488 \uC81C\uC870 \uAE30\uC220", "\uC7AC\uBB34 \uBD84\uC11D", "\uC138\uBB34 \uBC0F \uBC95\uB960 \uC774\uD574", "\uC804\uBB38 \uC9C0\uC2DD \uBC0F \uBD84\uC11D \uB2A5\uB825", "\uC758\uC0AC\uC18C\uD1B5 \uB2A5\uB825", "\uBC95\uB958 \uC9C0\uC2DD \uBC0F \uD574\uC11D \uB2A5\uB825"}));
//		WorJoinInfo_skillCbox.setBounds(182, 316, 291, 23);
//		panel.add(WorJoinInfo_skillCbox);
//
//
//
//		JButton WorkerJoinInfo_IdConfirmBtn_1 = new JButton("\uB4F1\uB85D");
//		WorkerJoinInfo_IdConfirmBtn_1.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//				String workerID = WorJoinInfo_IdTx.getText();
//				String workerPW = WorJoinInfo_PwTx.getText();
//				String workerName = WorJoinInfo_nameTx.getText();
//				String workerAddr = WorJoinInfo_addrTx.getText();
//				String workerTel = WorkerJoinInfo_telTx.getText();
//				String workerEmail = WorJoinInfo_emailTx.getText();
//				String workerAge = WorJoinInfo_ageTx.getText();
//				String workerRnum = WorJoinInfo_rnumTx.getText();
//
//				int code = WorJoinInfo_skillCbox.getSelectedIndex();
//
//				try {
//					JoinWorkerDAO dao = new JoinWorkerDAO();
//
//					int indexNum = dao.switchResult(code) ;
//
//					//               WorkerVO vo = new WorkerVO(workerID,  workerPW,  workerName,  workerAddr,
//					//                     workerTel,  workerAge, workerRnum,  workerEmail,  indexNum);
//
//					dao.workerInsert(vo);
//
//					dispose();
//
//				} catch (Exception e1) {
//					// TODO: handle exception
//					e1.printStackTrace();
//				}
//
//
//			}
//		});
//
//
//		WorkerJoinInfo_IdConfirmBtn_1.setForeground(new Color(242, 170, 76));
//		WorkerJoinInfo_IdConfirmBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
//		WorkerJoinInfo_IdConfirmBtn_1.setBackground(new Color(16, 24, 32));
//		WorkerJoinInfo_IdConfirmBtn_1.setBounds(416, 408, 97, 23);
//		LoginJoinInfoP.add(WorkerJoinInfo_IdConfirmBtn_1);
//
//	}
//
//	void clear() {
//		WorJoinInfo_IdTx.setText("");
//		WorJoinInfo_PwTx.setText("");
//		WorJoinInfo_nameTx.setText("");
//		WorJoinInfo_addrTx.setText("");
//		WorkerJoinInfo_telTx.setText("");
//		WorJoinInfo_emailTx.setText("");
//		WorJoinInfo_ageTx.setText("");
//		WorJoinInfo_rnumTx.setText("");
//	}
//
//}
