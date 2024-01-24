package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.JoinWorkerDAO;
import model.rec.WorkerVO;
import javax.swing.JPasswordField;

public class WLoginJoinView extends JFrame {

	private JPanel LoginJoinInfoP;
	private JTextField WorJoinInfo_nameTx;
	private JTextField WorJoinInfo_emailTx;
	private JTextField WorJoinInfo_IdTx;
	private JTextField WorkerJoinInfo_telTx;
	private JTextField WorJoinInfo_addrTx;
	private JTextField WorJoinInfo_ageTx;
	private JTextField WorJoinInfo_rnumTx;
	private JPasswordField WorJoinInfo_PwTx;
	private JPasswordField WorJoinInfo_PwConfTx;

	/**
	 * Launch the application.
	 */
	public static void login_Join() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					WLoginJoinView frame = new WLoginJoinView();

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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WLoginJoinView() {

		setTitle(" \uD30C\uACAC\uC778\uB825 \uAC1C\uC778\uC815\uBCF4 \uC2E0\uADDC \uB4F1\uB85D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 481);
		setLocationRelativeTo(null);
		LoginJoinInfoP = new JPanel();
		LoginJoinInfoP.setBackground(new Color(242, 170, 76));
		LoginJoinInfoP.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginJoinInfoP);
		LoginJoinInfoP.setLayout(null);

		JLabel WorkerJoinInfo_perInfoLabel = new JLabel("개인정보 등록");
		WorkerJoinInfo_perInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		WorkerJoinInfo_perInfoLabel.setBounds(12, 10, 147, 30);
		LoginJoinInfoP.add(WorkerJoinInfo_perInfoLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(16, 24, 32));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 45, 499, 353);
		LoginJoinInfoP.add(panel);
		panel.setLayout(null);

		JLabel WorJoinInfo_IdLabel = new JLabel("아이디");
		WorJoinInfo_IdLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_IdLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_IdLabel.setBounds(24, 11, 118, 23);
		panel.add(WorJoinInfo_IdLabel);

		WorJoinInfo_IdTx = new HintTextField("(영문, 숫자 조합 최대 15자리)");
		WorJoinInfo_IdTx.setBounds(182, 11, 169, 23);
		panel.add(WorJoinInfo_IdTx);
		WorJoinInfo_IdTx.setForeground(new Color(0, 0, 0));
		WorJoinInfo_IdTx.setColumns(10);

		// 아이디 중복확인 버튼
		JButton WorJoinInfo_IdConftn = new JButton("아이디 중복확인");
		WorJoinInfo_IdConftn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String workerID = WorJoinInfo_IdTx.getText();

				try {
					JoinWorkerDAO dao = new JoinWorkerDAO();

					String res = dao.DupCheckId(workerID);

					if (workerID.equals("(영문, 숫자 조합 최대 15자리)")) {
						JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");

					} else {
						if (res.equals("사용가능")) {
							JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.");
						} else if (res.equals("불가능")) {
							JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다.");
						} else {

						}
					}

				} catch (Exception e2) {
					// TODO: handle exceptione
					e2.printStackTrace();
				}

			}
		});

		WorJoinInfo_IdConftn.setForeground(new Color(242, 170, 76));
		WorJoinInfo_IdConftn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		WorJoinInfo_IdConftn.setBounds(363, 11, 124, 23);
		panel.add(WorJoinInfo_IdConftn);
		WorJoinInfo_IdConftn.setBackground(new Color(16, 24, 32));

		JLabel WorkerJoinInfo_PwLabel = new JLabel("비밀번호");
		WorkerJoinInfo_PwLabel.setForeground(new Color(242, 170, 76));
		WorkerJoinInfo_PwLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorkerJoinInfo_PwLabel.setBounds(24, 45, 118, 23);
		panel.add(WorkerJoinInfo_PwLabel);

		JLabel WorJoinInfo_PwConfLabel = new JLabel("비밀번호 확인");
		WorJoinInfo_PwConfLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_PwConfLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_PwConfLabel.setBounds(24, 79, 118, 23);
		panel.add(WorJoinInfo_PwConfLabel);

		JLabel WorJoinInfo_nameLabel = new JLabel("이름");
		WorJoinInfo_nameLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_nameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_nameLabel.setBounds(24, 113, 57, 23);
		panel.add(WorJoinInfo_nameLabel);

		WorJoinInfo_nameTx = new JTextField();
		WorJoinInfo_nameTx.setBounds(182, 113, 170, 23);
		panel.add(WorJoinInfo_nameTx);
		WorJoinInfo_nameTx.setColumns(10);

		JLabel WorJoinInfo_telLabel = new JLabel("연락처");
		WorJoinInfo_telLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_telLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_telLabel.setBounds(24, 215, 57, 23);
		panel.add(WorJoinInfo_telLabel);

		WorkerJoinInfo_telTx = new JTextField();
		WorkerJoinInfo_telTx.setBounds(182, 215, 169, 23);
		panel.add(WorkerJoinInfo_telTx);
		WorkerJoinInfo_telTx.setColumns(10);

		JLabel WorJoinInfo_emailLabel = new JLabel("Email");
		WorJoinInfo_emailLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_emailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_emailLabel.setBounds(24, 249, 57, 23);
		panel.add(WorJoinInfo_emailLabel);

		WorJoinInfo_emailTx = new JTextField();
		WorJoinInfo_emailTx.setBounds(182, 249, 169, 23);
		panel.add(WorJoinInfo_emailTx);
		WorJoinInfo_emailTx.setColumns(10);

		JLabel WorJoinInfo_addrLabel = new JLabel("주소");
		WorJoinInfo_addrLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_addrLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_addrLabel.setBounds(24, 283, 57, 23);
		panel.add(WorJoinInfo_addrLabel);

		WorJoinInfo_addrTx = new JTextField();
		WorJoinInfo_addrTx.setBounds(182, 283, 169, 23);
		panel.add(WorJoinInfo_addrTx);
		WorJoinInfo_addrTx.setColumns(10);

		JLabel WorJoinInfo_birthLabel_1 = new JLabel("\uB098\uC774");
		WorJoinInfo_birthLabel_1.setForeground(new Color(242, 170, 76));
		WorJoinInfo_birthLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_birthLabel_1.setBounds(24, 147, 118, 23);
		panel.add(WorJoinInfo_birthLabel_1);

		WorJoinInfo_ageTx = new JTextField();
		WorJoinInfo_ageTx.setColumns(10);
		WorJoinInfo_ageTx.setBounds(182, 147, 170, 23);
		panel.add(WorJoinInfo_ageTx);

		JLabel WorJoinInfo_birthLabel_1_1 = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		WorJoinInfo_birthLabel_1_1.setForeground(new Color(242, 170, 76));
		WorJoinInfo_birthLabel_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_birthLabel_1_1.setBounds(24, 181, 118, 23);
		panel.add(WorJoinInfo_birthLabel_1_1);

		WorJoinInfo_rnumTx = new JTextField();
		WorJoinInfo_rnumTx.setColumns(10);
		WorJoinInfo_rnumTx.setBounds(181, 181, 170, 23);
		panel.add(WorJoinInfo_rnumTx);

		JLabel WorJoinInfo_skillLabel = new JLabel("\uAE30\uC220\uBD84\uB958\uCF54\uB4DC");
		WorJoinInfo_skillLabel.setForeground(new Color(242, 170, 76));
		WorJoinInfo_skillLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		WorJoinInfo_skillLabel.setBounds(24, 319, 118, 23);
		panel.add(WorJoinInfo_skillLabel);

		JComboBox WorJoinInfo_skillCbox = new JComboBox();
		WorJoinInfo_skillCbox.setModel(new DefaultComboBoxModel(new String[] {"지형조사 및 공학",
				"구조물 설계 및 강도 분석",
				"건축 디자인 및 시공 기술",
				"건축 자재 및 기술적 특성 이해",
				"종합적 프로젝트 관리",
				"안전 관리 및 품질관리",
				"공정 설계 및 자동화 기술",
				"환경 보전 및 대기 정화 기술",
				"프로그래밍 언어 및 기술 습득",
				"시스템 관리 및 유지보수",
				"소프트웨어 개발 라이프사이클 이해",
				"알고리즘 및 문제 해결 능력",
				"데이터베이스 설계 및 관리",
				"온라인 정보 시스템 구축",
				"교육 방법론 및 교수 기술",
				"교육정책 및 컨설팅 능력",
				"프로젝트 관리 및 계획 능력",
				"의료기기 설계 및 개발",
				"소재 및 섬유 기술 이해",
				"의류 제작 기술",
				"자동차 부품 설계 및 제작",
				"품질 관리 및 테스트",
				"전자 제품 제조 기술",
				"재무 분석",
				"세무 및 법률 이해",
				"전문 지식 및 분석 능력",
				"의사소통 능력",
				"법류 지식 및 해석 능력"}));
		WorJoinInfo_skillCbox.setBounds(182, 316, 291, 23);
		panel.add(WorJoinInfo_skillCbox);

		JButton WorJoinInfo_IdConftn_1 = new JButton("비밀번호 확인");
		WorJoinInfo_IdConftn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String workerPW = WorJoinInfo_PwTx.getText();
				String workerPwConf = WorJoinInfo_PwConfTx.getText();
				
				try {
					if(workerPW.equals(workerPwConf)) {
						JOptionPane.showMessageDialog(null, "비밀번호가 확인되었습니다.");
					}else{
						JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
					}					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		WorJoinInfo_IdConftn_1.setForeground(new Color(242, 170, 76));
		WorJoinInfo_IdConftn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		WorJoinInfo_IdConftn_1.setBackground(new Color(16, 24, 32));
		WorJoinInfo_IdConftn_1.setBounds(363, 79, 124, 23);
		panel.add(WorJoinInfo_IdConftn_1);
		
		WorJoinInfo_PwTx = new JPasswordField();
		WorJoinInfo_PwTx.setBounds(182, 45, 169, 21);
		panel.add(WorJoinInfo_PwTx);
		
		WorJoinInfo_PwConfTx = new JPasswordField();
		WorJoinInfo_PwConfTx.setBounds(182, 79, 169, 21);
		panel.add(WorJoinInfo_PwConfTx);

		JButton WorkerJoinInfo_IdConfirmBtn_1 = new JButton("\uB4F1\uB85D");
		WorkerJoinInfo_IdConfirmBtn_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String workerID = WorJoinInfo_IdTx.getText();
				String workerPW = WorJoinInfo_PwTx.getText();
				String workerName = WorJoinInfo_nameTx.getText();
				String workerAddr = WorJoinInfo_addrTx.getText();
				String workerTel = WorkerJoinInfo_telTx.getText();
				String workerEmail = WorJoinInfo_emailTx.getText();
				String workerAge = WorJoinInfo_ageTx.getText();
				String workerRnum = WorJoinInfo_rnumTx.getText();

				int code = WorJoinInfo_skillCbox.getSelectedIndex();

				try {
					JoinWorkerDAO dao = new JoinWorkerDAO();

					int indexNum = dao.switchResult(code);

					WorkerVO vo = new WorkerVO(workerID, workerPW, workerName, workerAddr, workerTel, workerAge,
							workerRnum, workerEmail, indexNum);

					if (workerID.equals("") || workerPW.equals("") || workerName.equals("") || workerAddr.equals("")
							|| workerTel.equals("") || workerEmail.equals("") || workerAge.equals("")
							|| workerAge.equals("") || workerRnum.equals("")) {
						JOptionPane.showMessageDialog(null, "필수 값을 입력하세요");
					
					} else {
						dao.workerInsert(vo);
						JOptionPane.showMessageDialog(null, "신규 회원 등록이 완료되었습니다.");
						dispose();
					}

				} catch (
						Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});

		WorkerJoinInfo_IdConfirmBtn_1.setForeground(new Color(242, 170, 76));
		WorkerJoinInfo_IdConfirmBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		WorkerJoinInfo_IdConfirmBtn_1.setBackground(new Color(16, 24, 32));
		WorkerJoinInfo_IdConfirmBtn_1.setBounds(416, 408, 97, 23);
		LoginJoinInfoP.add(WorkerJoinInfo_IdConfirmBtn_1);

	}

	void clear() {
		WorJoinInfo_IdTx.setText("");
		WorJoinInfo_PwTx.setText("");
		WorJoinInfo_nameTx.setText("");
		WorJoinInfo_addrTx.setText("");
		WorkerJoinInfo_telTx.setText("");
		WorJoinInfo_emailTx.setText("");
		WorJoinInfo_ageTx.setText("");
		WorJoinInfo_rnumTx.setText("");
	}
}
