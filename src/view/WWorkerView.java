package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.CareerDAO;
import model.Connect;
import model.LoginDAO;
import model.SheetDAO;
import model.WorkerContInfoDAO;
import model.WorkerPayDAO;
import model.WorkerReqDAO;
import model.rec.ApplyVO;
import model.rec.CareerVO;
import model.rec.CertiVO;
import model.rec.PayVO;
import model.rec.ReqVO;
import model.rec.WorkerContVO;

public class WWorkerView extends Connect {

	private JPanel contentPane;
	private JTable reqListTB;
	private JTable certiTB;
	private JLabel careerPeriodLabel_value;
	private JLabel workerCodeLabel;
	private JTable careerInfoTB;
	private JTable contListTB;
	private JTable table_1;
	private JTextField contSdateTx;
	private JTextField contEdateTx;
	private JTextField contReconTx;
	private JTextField contPeriodTx;
	private JTextField accNumTx;
	private JTextField accBankTx;
	private JTextField accNameTx;
	private JTextField contNumTx;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField recontPay_tf;
	private JTextField tax_tf;
	private JTextField total_tf;
	private JTextField workerPay_tf;
	private JTextField textField;
	private JTextField workerCode_tf;

	private int code;
	String id;
	String name;
	String workerCode;
	String contCode = null;
	String CareerCode; // 텍스트
	WorkerContVO vo;
	WorkerReqDAO dao = null;
	WorkerPayDAO workerPayDao = null;
	WorkerContInfoDAO wcInfodao = null;
	CareerDAO careerdao = null;
	String vNum;
	String vNum1;
	String vNum2;
	String vNum3;
	String vNum4;
	String vNum5;
	String vNum6;
	String vNum7;
	String vNum8;
	String vNum9;
	String vNum10;
	
	

	String[] reqHeader = { "파견요청번호", "요청서등록일", "업종명", "국가", "필수어학수준", "모집상태", "지원등록일자", "지원번호" };
	String[] contHeader = { "고용계약번호", "계약시작일", "계약만료일", "계약일", "계약성사여부" };
	String[] careerHeader = { "경력코드", "업체명", "직무시작일", "직무종료일", "직무내용" };
	String[] certiHeader = { "자격증코드", "자격증명", "자격번호", "자격취득일", "자격증 유효기간" };
	String[] payHeader = { "고용계약번호", "계약체결일", "지급금액","지급여부"};
	String[][] contents = null;

	DefaultTableModel reqModel; // 파견목록 정보 생성
	DefaultTableModel contModel; // 고용계목록 정보 생성
	DefaultTableModel careerModel; // 고용계목록 정보 생성
	DefaultTableModel certiModel; // 고용계목록 정보 생성
	DefaultTableModel payModel; // 수당목록 정보 생성

	DefaultTableCellRenderer center;
	private JTable certiTb;
	private JTable careerTb;
	private JTextField careerNameTx;
	private JTextField careerSdateTx;
	private JTextField careerEdateTx;
	private JTextField careerDetailTx;
	private JTextField certiExpPeriodTx;
	private JTextField certiDateTx;
	private JTextField certiNumTx;
	private JTextField certiNameTx;
	private JTextField careerCodeTx;
	private JTextField certiCodeTx;
	private JTextField sexTx;
	private JTextField qualiTx;
	private JTextField localTx;
	private JTextField ageRangeTx;
	private JTextField langLevelTx;
	private JTextField cityNameTx;
	private JTextField reqLangTx;
	private JTextField expecEdateTx;
	private JTextField expecSdateTx;
	private JTextField reqDateTx;
	private JTextField sectorNameTx;
	private JTextField localLangLevelTx;
	private JTextField custCodeTx;
	private JTextField actualSdateTx;
	private JTextField actualEdateTx;
	JLabel workerContCodeLabel;
	private JTable payTabel;

	/**
	 * Launch the application.
	 */
	public static void workerMainView(String ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					WWorkerView frame = new WWorkerView(ID);
					frame.setVisible(true);
					frame.setResizable(false);
					;

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
							} else if (result == JOptionPane.NO_OPTION) {
								frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

	// 윈도우 빌더 테스트용 생성자 함수
	public WWorkerView() {

		this.workerMainView("테스트");

	};

	// 실행용 생성자 함수
	public WWorkerView(int num) {
	};

	// 메인 뷰 생성자 함수
	public WWorkerView(String id) {

		this.id = id;
		codeSearch(id);

		try {
			careerdao = new CareerDAO();
			this.workerCode = careerdao.workerCode(id);

		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

		// 탭 별 모델

		// 1. 파견요청목록
		reqModel = new DefaultTableModel(contents, reqHeader) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		contModel = new DefaultTableModel(contents, contHeader) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		careerModel = new DefaultTableModel(contents, careerHeader) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		certiModel = new DefaultTableModel(contents, certiHeader) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		payModel = new DefaultTableModel(contents, payHeader) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 820);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 170, 76));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel loginCode = new JLabel("New label");
		loginCode.setBounds(1205, 10, 127, 23);
		loginCode.setHorizontalAlignment(SwingConstants.RIGHT);
		loginCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		contentPane.add(loginCode);

		loginCode.setText(id);

		JTabbedPane workerMainTab = new JTabbedPane(JTabbedPane.TOP);
		workerMainTab.setBounds(12, 65, 1410, 706);
		workerMainTab.setBorder(null);
		contentPane.add(workerMainTab);

		JPanel careerPanel = new JPanel();
		careerPanel.setLayout(null);
		careerPanel.setForeground(new Color(16, 24, 32));
		careerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		careerPanel.setBackground(new Color(16, 24, 32));
		workerMainTab.addTab("\uACBD\uB825\uAD00\uB9AC", null, careerPanel, null);

		JLabel careerListLabel = new JLabel("\uACBD\uB825\uC0AC\uD56D \uBAA9\uB85D");
		careerListLabel.setForeground(new Color(242, 170, 76));
		careerListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		careerListLabel.setBounds(12, 25, 158, 33);
		careerPanel.add(careerListLabel);

		JPanel careerP = new JPanel();
		careerP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		careerP.setBackground(new Color(16, 24, 32));
		careerP.setBounds(12, 58, 750, 297);
		careerPanel.add(careerP);
		careerP.setLayout(null);

		JScrollPane careerScr = new JScrollPane();
		careerScr.setBounds(12, 10, 726, 277);
		careerP.add(careerScr);

		careerTb = new JTable(careerModel);
		careerScr.setViewportView(careerTb);

		careerTb.getColumn("경력코드").setPreferredWidth(20);
		careerTb.getColumn("업체명").setPreferredWidth(50);
		careerTb.getColumn("직무시작일").setPreferredWidth(50);
		careerTb.getColumn("직무종료일").setPreferredWidth(50);
		careerTb.getColumn("직무내용").setPreferredWidth(50);

		careerTb.getColumn("경력코드").setCellRenderer(center);
		careerTb.getColumn("업체명").setCellRenderer(center);
		careerTb.getColumn("직무시작일").setCellRenderer(center);
		careerTb.getColumn("직무종료일").setCellRenderer(center);
		careerTb.getColumn("직무내용").setCellRenderer(center);

		careerTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = careerTb.getSelectedRow();
				vNum2 = (String) careerTb.getValueAt(row, col);

				CareerVO vo = new CareerVO();

				try {
					CareerDAO careerdao = new CareerDAO();
					careerdao.workersearchCareerInfo(vNum2, vo);

					// vo의 각각의 값을 오른쪽 화면에 출력
					careerCodeTx.setText(String.valueOf(vo.getCareerCode()));
					careerNameTx.setText(vo.getCareerName());
					careerSdateTx.setText(vo.getCareerSdate().substring(0, 10));
					careerEdateTx.setText(vo.getCareerEdate().substring(0, 10));
					careerDetailTx.setText(vo.getCareerDetail());

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "경력 정보 출력 실패 : " + e1.getMessage());
				}
			}
		});

		JLabel career_certiListLabel = new JLabel("\uACBD\uB825 \uBC0F \uC790\uACA9\uC99D \uC0C1\uC138 \uC815\uBCF4");
		career_certiListLabel.setForeground(new Color(242, 170, 76));
		career_certiListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		career_certiListLabel.setBounds(774, 25, 498, 33);
		careerPanel.add(career_certiListLabel);

		JPanel career_certiListP = new JPanel();
		career_certiListP
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		career_certiListP.setBackground(new Color(16, 24, 32));
		career_certiListP.setBounds(774, 58, 619, 609);
		careerPanel.add(career_certiListP);
		career_certiListP.setLayout(null);

		JLabel careerInfoLabel = new JLabel("\uACBD\uB825\uC815\uBCF4");
		careerInfoLabel.setForeground(new Color(242, 170, 76));
		careerInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		careerInfoLabel.setBounds(12, 10, 112, 33);
		career_certiListP.add(careerInfoLabel);

		JLabel certiInfoLabel = new JLabel("\uC790\uACA9\uC99D \uC815\uBCF4");
		certiInfoLabel.setForeground(new Color(242, 170, 76));
		certiInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		certiInfoLabel.setBounds(12, 311, 112, 33);
		career_certiListP.add(certiInfoLabel);

		JPanel careerInfoPanel = new JPanel();
		careerInfoPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		careerInfoPanel.setBackground(new Color(16, 24, 32));
		careerInfoPanel.setBounds(12, 38, 595, 255);
		career_certiListP.add(careerInfoPanel);
		careerInfoPanel.setLayout(null);

		JLabel careerCodeLabel = new JLabel("\uACBD\uB825\uCF54\uB4DC");
		careerCodeLabel.setBounds(28, 10, 124, 30);
		careerInfoPanel.add(careerCodeLabel);
		careerCodeLabel.setForeground(new Color(242, 170, 76));
		careerCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel careerNameLabel = new JLabel("\uC5C5\uCCB4\uBA85");
		careerNameLabel.setBounds(28, 50, 124, 30);
		careerInfoPanel.add(careerNameLabel);
		careerNameLabel.setForeground(new Color(242, 170, 76));
		careerNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel careerSdateLabel = new JLabel("\uC9C1\uBB34\uC2DC\uC791\uC77C");
		careerSdateLabel.setBounds(28, 90, 124, 30);
		careerInfoPanel.add(careerSdateLabel);
		careerSdateLabel.setForeground(new Color(242, 170, 76));
		careerSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		careerSdateLabel.setBackground(new Color(255, 128, 128));

		JLabel careerEdateLabel = new JLabel("\uC9C1\uBB34\uC885\uB8CC\uC77C");
		careerEdateLabel.setBounds(28, 130, 124, 30);
		careerInfoPanel.add(careerEdateLabel);
		careerEdateLabel.setForeground(new Color(242, 170, 76));
		careerEdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel careerDetailLabel = new JLabel("\uC9C1\uBB34\uB0B4\uC6A9");
		careerDetailLabel.setBounds(28, 170, 124, 30);
		careerInfoPanel.add(careerDetailLabel);
		careerDetailLabel.setForeground(new Color(242, 170, 76));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		careerDetailLabel.setBackground(new Color(255, 128, 128));

		careerDetailTx = new JTextField();
		careerDetailTx.setBounds(223, 170, 360, 30);
		careerInfoPanel.add(careerDetailTx);
		careerDetailTx.setColumns(10);

		careerEdateTx = new JTextField();
		careerEdateTx.setBounds(223, 130, 267, 30);
		careerInfoPanel.add(careerEdateTx);
		careerEdateTx.setColumns(10);

		careerSdateTx = new JTextField();
		careerSdateTx.setBounds(223, 90, 267, 30);
		careerInfoPanel.add(careerSdateTx);
		careerSdateTx.setColumns(10);

		careerNameTx = new JTextField();
		careerNameTx.setBounds(223, 50, 267, 30);
		careerInfoPanel.add(careerNameTx);
		careerNameTx.setColumns(10);

		careerCodeTx = new JTextField();
		careerCodeTx.setEditable(false);
		careerCodeTx.setBounds(223, 10, 267, 30);
		careerInfoPanel.add(careerCodeTx);
		careerCodeTx.setColumns(10);
		// @@@
		JButton careerAddBtn = new JButton("\uACBD\uB825 \uB4F1\uB85D");
		careerAddBtn.setBounds(223, 210, 112, 33);
		careerInfoPanel.add(careerAddBtn);
		careerAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "경력 정보 등록", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					String careerName = careerNameTx.getText();
					String careerSdate = careerSdateTx.getText().substring(0, 10);
					String careerEdate = careerEdateTx.getText().substring(0, 10);
					String careerDetail = careerDetailTx.getText();

					try {
						CareerDAO dao = new CareerDAO();
						CareerVO vo = new CareerVO(careerName, careerSdate, careerEdate, careerDetail);

						dao.CareerInsert(vo, workerCode);
						careerListTB(careerHeader);
						JOptionPane.showMessageDialog(null, "경력 정보가 등록되었습니다.");

					} catch (Exception ev) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "경력 정보가 등록되지 않았습니다." + ev.getMessage());
						ev.printStackTrace();
					}
					clearCareer();

				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				
				
				// int careerCode = Integer.parseInt(careerCodeTx.getText());

			}
		});
		careerAddBtn.setForeground(Color.WHITE);
		careerAddBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerAddBtn.setBackground(new Color(16, 24, 32));

		JButton careerDelBtn = new JButton("\uACBD\uB825 \uC0AD\uC81C");
		careerDelBtn.setBounds(347, 210, 112, 33);
		careerInfoPanel.add(careerDelBtn);
		careerDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
				
				int result = JOptionPane.showConfirmDialog(null, "경력 정보 삭제", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int vNum4 = Integer.parseInt(careerCodeTx.getText());

					try {
						careerdao.careerDelete(vNum4);
						careerListTB(careerHeader);
						JOptionPane.showMessageDialog(null, "해당 경력 정보가 삭제되었습니다.");

					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "해당 경력 정보가 삭제되지 않았습니다." + e2.getMessage());
						e2.printStackTrace();
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				
				


			}
		});
		careerDelBtn.setForeground(Color.WHITE);
		careerDelBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerDelBtn.setBackground(new Color(16, 24, 32));

		JButton careerModBtn = new JButton("\uACBD\uB825 \uC218\uC815");
		careerModBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				int result = JOptionPane.showConfirmDialog(null, "경력 정보 수정", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int careerCode = Integer.parseInt(careerCodeTx.getText());
					String careerName = careerNameTx.getText();
					String careerSdate = careerSdateTx.getText().substring(0, 10);
					String careerEdate = careerEdateTx.getText().substring(0, 10);
					String careerDetail = careerDetailTx.getText();

					try {
						careerdao = new CareerDAO();
						CareerVO vo = new CareerVO(careerCode, careerName, careerSdate, careerEdate, careerDetail);
						careerdao.careerModify(vo);
						careerListTB(careerHeader);
						JOptionPane.showMessageDialog(null, "해당 경력 정보가 수정되었습니다.");

					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "해당 경력 정보가 수정되지 않았습니다." + e2.getMessage());
						e2.getStackTrace();
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				
				


			}
		});
		careerModBtn.setBounds(471, 210, 112, 33);
		careerInfoPanel.add(careerModBtn);
		careerModBtn.setForeground(Color.WHITE);
		careerModBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerModBtn.setBackground(new Color(16, 24, 32));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_1.setBackground(new Color(16, 24, 32));
		panel_1.setBounds(12, 339, 595, 260);
		career_certiListP.add(panel_1);
		panel_1.setLayout(null);

		JButton certiAddBtn = new JButton("\uC790\uACA9\uC99D \uB4F1\uB85D");
		certiAddBtn.setBounds(223, 218, 112, 33);
		panel_1.add(certiAddBtn);
		certiAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "자격증 정보 등록", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// int certiCode = Integer.parseInt(certiCodeTx.getText());
					String certiName = certiNameTx.getText();
					int certiNum = Integer.parseInt(certiNumTx.getText());
					String certiDate = certiDateTx.getText().substring(0, 10);
					String certiExpPeriod = certiExpPeriodTx.getText().substring(0, 10);

					try {

						CareerDAO dao = new CareerDAO();
						CertiVO vo = new CertiVO(certiName, certiNum, certiDate, certiExpPeriod);
						dao.CertiInsert(vo, workerCode);
						certiListTB(certiHeader);
						JOptionPane.showMessageDialog(null, "자격증 정보가 등록되었습니다.");

					} catch (Exception ev) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "자격증 정보가 등록되지 않았습니다.");
						ev.printStackTrace();
					}
				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				
				
				

			}
		});
		certiAddBtn.setForeground(Color.WHITE);
		certiAddBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiAddBtn.setBackground(new Color(16, 24, 32));

		JLabel certiCodeLabel = new JLabel("\uC790\uACA9\uC99D \uCF54\uB4DC");
		certiCodeLabel.setBounds(28, 10, 124, 30);
		panel_1.add(certiCodeLabel);
		certiCodeLabel.setForeground(new Color(242, 170, 76));
		certiCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel certiNameLabel = new JLabel("\uC790\uACA9\uC99D\uBA85");
		certiNameLabel.setBounds(28, 52, 124, 30);
		panel_1.add(certiNameLabel);
		certiNameLabel.setForeground(new Color(242, 170, 76));
		certiNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel certiNumLabel = new JLabel("\uC790\uACA9\uBC88\uD638");
		certiNumLabel.setBounds(28, 94, 124, 30);
		panel_1.add(certiNumLabel);
		certiNumLabel.setForeground(new Color(242, 170, 76));
		certiNumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		certiNumLabel.setBackground(new Color(255, 128, 128));

		JLabel certiDateLabel = new JLabel("\uC790\uACA9\uCDE8\uB4DD\uC77C");
		certiDateLabel.setBounds(28, 136, 124, 30);
		panel_1.add(certiDateLabel);
		certiDateLabel.setForeground(new Color(242, 170, 76));
		certiDateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel certiExpPeriodLabel = new JLabel("\uC790\uACA9\uC99D \uC720\uD6A8\uAE30\uAC04");
		certiExpPeriodLabel.setBounds(28, 178, 124, 30);
		panel_1.add(certiExpPeriodLabel);
		certiExpPeriodLabel.setForeground(new Color(242, 170, 76));
		certiExpPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		certiExpPeriodLabel.setBackground(new Color(255, 128, 128));

		certiCodeTx = new JTextField();
		certiCodeTx.setEditable(false);
		certiCodeTx.setBounds(223, 10, 267, 30);
		panel_1.add(certiCodeTx);
		certiCodeTx.setColumns(10);

		certiNameTx = new JTextField();
		certiNameTx.setBounds(223, 52, 267, 30);
		panel_1.add(certiNameTx);
		certiNameTx.setColumns(10);

		certiNumTx = new JTextField();
		certiNumTx.setBounds(223, 94, 267, 30);
		panel_1.add(certiNumTx);
		certiNumTx.setColumns(10);

		certiDateTx = new JTextField();
		certiDateTx.setBounds(223, 136, 267, 30);
		panel_1.add(certiDateTx);
		certiDateTx.setColumns(10);

		certiExpPeriodTx = new JTextField();
		certiExpPeriodTx.setBounds(223, 178, 267, 30);
		panel_1.add(certiExpPeriodTx);
		certiExpPeriodTx.setColumns(10);

		JButton certiDelBtn = new JButton("\uC790\uACA9\uC99D \uC0AD\uC81C");
		certiDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
				
				
				int result = JOptionPane.showConfirmDialog(null, "자격증 정보 삭제", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int vNum5 = Integer.parseInt(certiCodeTx.getText());

					try {
						careerdao.certiDelete(vNum5);
						certiListTB(certiHeader);
						JOptionPane.showMessageDialog(null, "해당 자격증 정보가 삭제되었습니다.");

					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "해당 자격증 정보가 삭제되지 않았습니다." + e2.getMessage());
						e2.printStackTrace();
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}

			}
		});
		certiDelBtn.setBounds(347, 218, 112, 33);
		panel_1.add(certiDelBtn);
		certiDelBtn.setForeground(Color.WHITE);
		certiDelBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiDelBtn.setBackground(new Color(16, 24, 32));

		JButton certiModBtn = new JButton("\uC790\uACA9\uC99D \uC218\uC815");
		certiModBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "자격증 정보 수정", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int certiCode = Integer.parseInt(certiCodeTx.getText());
					int certiNum = Integer.parseInt(certiNumTx.getText());
					String certiName = certiNameTx.getText();
					String certiDate = certiDateTx.getText().substring(0, 10);
					String certiExpPeriod = certiExpPeriodTx.getText().substring(0, 10);

					try {
						careerdao = new CareerDAO();
						CertiVO vo = new CertiVO(certiCode, certiNum, certiName, certiDate, certiExpPeriod);
						careerdao.certiModify(vo);
						certiListTB(certiHeader);
						JOptionPane.showMessageDialog(null, "해당 자격증 정보가 수정되었습니다.");

					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "해당 자격증 정보가 수정되지 않았습니다." + e2.getMessage());
						e2.getStackTrace();
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}


			}
		});

		certiModBtn.setBounds(471, 218, 112, 33);
		panel_1.add(certiModBtn);
		certiModBtn.setForeground(Color.WHITE);
		certiModBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiModBtn.setBackground(new Color(16, 24, 32));

		JButton clearCareerBtn = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		clearCareerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCareer();
			}
		});
		clearCareerBtn.setBounds(510, 16, 97, 23);
		career_certiListP.add(clearCareerBtn);

		JButton clearCertiBtn = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		clearCertiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCerti();
			}
		});
		clearCertiBtn.setBounds(510, 317, 97, 23);
		career_certiListP.add(clearCertiBtn);

		JPanel certiListP = new JPanel();
		certiListP
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		certiListP.setBackground(new Color(16, 24, 32));
		certiListP.setBounds(12, 386, 750, 281);
		careerPanel.add(certiListP);

		certiTb = new JTable(certiModel);
		certiTb.setBounds(10, 10, 728, 249);

		certiTb.getColumn("자격증코드").setPreferredWidth(5);
		certiTb.getColumn("자격증명").setPreferredWidth(5);
		certiTb.getColumn("자격번호").setPreferredWidth(5);
		certiTb.getColumn("자격취득일").setPreferredWidth(5);
		certiTb.getColumn("자격증 유효기간").setPreferredWidth(5);

		certiTb.getColumn("자격증코드").setCellRenderer(center);
		certiTb.getColumn("자격증명").setCellRenderer(center);
		certiTb.getColumn("자격번호").setCellRenderer(center);
		certiTb.getColumn("자격취득일").setCellRenderer(center);
		certiTb.getColumn("자격증 유효기간").setCellRenderer(center);

		certiTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = certiTb.getSelectedRow();
				vNum3 = (String) certiTb.getValueAt(row, col);

				CertiVO vo = new CertiVO();

				try {
					CareerDAO careerdao = new CareerDAO();
					careerdao.workersearchCertiInfo(vNum3, vo);

					// vo의 각각의 값을 오른쪽 화면에 출력

					certiCodeTx.setText(String.valueOf(vo.getCertiCode()));
					certiNameTx.setText(vo.getCertiName());
					certiNumTx.setText(String.valueOf(vo.getCertiNum()));
					certiDateTx.setText(vo.getCertiDate().substring(0, 10));
					certiExpPeriodTx.setText(vo.getCertiExpPeriod().substring(0, 10));

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "자격증 정보 출력 실패 : " + e1.getMessage());
				}
			}
		});
		certiListP.setLayout(null);
		certiListP.add(certiTb);

		JScrollPane certiScr = new JScrollPane(certiTb);
		certiScr.setBounds(10, 10, 728, 261);
		certiListP.add(certiScr);

		JLabel certiLabel = new JLabel("\uC790\uACA9\uC99D \uBAA9\uB85D");
		certiLabel.setForeground(new Color(242, 170, 76));
		certiLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		certiLabel.setBounds(12, 355, 158, 33);
		careerPanel.add(certiLabel);

		JPanel reqPanel = new JPanel();
		reqPanel.setForeground(new Color(16, 24, 32));
		reqPanel.setBackground(new Color(16, 24, 32));
		reqPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerMainTab.addTab("\uD30C\uACAC\uC694\uCCAD\uC815\uBCF4", null, reqPanel, null);
		reqPanel.setLayout(null);

		JLabel ReqListLabel = new JLabel("\uD30C\uACAC\uC694\uCCAD\uBAA9\uB85D");
		ReqListLabel.setBackground(new Color(242, 170, 76));
		ReqListLabel.setForeground(new Color(242, 170, 76));
		ReqListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		ReqListLabel.setBounds(12, 10, 113, 33);
		reqPanel.add(ReqListLabel);

		JPanel workerListPanel = new JPanel();
		workerListPanel.setForeground(new Color(255, 255, 255));
		workerListPanel.setBackground(new Color(16, 24, 32));
		workerListPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		workerListPanel.setBounds(12, 46, 750, 621);
		reqPanel.add(workerListPanel);

		JLabel workerInfoLabel_1 = new JLabel("\uD30C\uACAC\uC694\uCCAD \uC0C1\uC138\uC815\uBCF4");
		workerInfoLabel_1.setBackground(new Color(242, 170, 76));
		workerInfoLabel_1.setForeground(new Color(242, 170, 76));
		workerInfoLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		workerInfoLabel_1.setBounds(774, 10, 250, 33);
		reqPanel.add(workerInfoLabel_1);

		workerListPanel.setLayout(null);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		reqListTB = new JTable(reqModel);

		reqListTB.getColumn("파견요청번호").setPreferredWidth(20);
		reqListTB.getColumn("요청서등록일").setPreferredWidth(50);
		reqListTB.getColumn("업종명").setPreferredWidth(50);
		reqListTB.getColumn("국가").setPreferredWidth(50);
		reqListTB.getColumn("필수어학수준").setPreferredWidth(50);
		reqListTB.getColumn("모집상태").setPreferredWidth(3);
		reqListTB.getColumn("지원등록일자").setPreferredWidth(50);
		reqListTB.getColumn("지원번호").setPreferredWidth(50);

		reqListTB.getColumn("파견요청번호").setCellRenderer(center);
		reqListTB.getColumn("요청서등록일").setCellRenderer(center);
		reqListTB.getColumn("업종명").setCellRenderer(center);
		reqListTB.getColumn("국가").setCellRenderer(center);
		reqListTB.getColumn("필수어학수준").setCellRenderer(center);
		reqListTB.getColumn("모집상태").setCellRenderer(center);
		reqListTB.getColumn("지원등록일자").setCellRenderer(center);
		reqListTB.getColumn("지원번호").setCellRenderer(center);

		reqListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = reqListTB.getSelectedRow();
				vNum = (String) reqListTB.getValueAt(row, col);
				ReqVO vo = new ReqVO();

				try {
					WorkerReqDAO dao = new WorkerReqDAO();
					ReqVO vo1 = dao.workerreqInfoSearch(vNum);

					// vo의 각각의 값을 오른쪽 화면에 출력
					reqDateTx.setText(vo1.getReqDate());
					custCodeTx.setText(String.valueOf(vo1.getCustCode()));
					sectorNameTx.setText(vo1.getSectorName()); // 업종명 (코드를 명으로 변환)
					expecSdateTx.setText(vo1.getExpecSdate());
					expecEdateTx.setText(vo1.getExpecEdate());
					reqLangTx.setText(vo1.getReqLangLevel());
					localLangLevelTx.setText(vo1.getLocalLangLevel());
					cityNameTx.setText(vo1.getCityName()); // 도시명 (코드를 명으로 변환)
					localTx.setText(vo1.getLocal());
					langLevelTx.setText(vo1.getLangLevel()); // 필요언어
					sexTx.setText(vo1.getSex());
					ageRangeTx.setText(vo1.getAgeRange());
					qualiTx.setText(vo1.getQuali());

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "파견요청정보 검색 실패 : " + e1.getMessage());
				}
			}
		});
		reqListTB.setBounds(12, 46, 610, 493);
		reqListTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		workerListPanel.add(reqListTB);

		JScrollPane reqListScr = new JScrollPane(reqListTB);
		reqListScr.setBounds(10, 10, 728, 601);
		workerListPanel.add(reqListScr);

		// 파견요청정보 조회
		JButton workerSerchBtn = new JButton("조회");
		workerSerchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reqListTB(reqHeader);
			}
		});

		workerSerchBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerSerchBtn.setBounds(673, 15, 89, 23);
		reqPanel.add(workerSerchBtn);

		JButton joinConfBtn = new JButton("\uC9C0\uC6D0\uB4F1\uB85D");
		joinConfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				int result = JOptionPane.showConfirmDialog(null, "파견 지원 등록처리", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int col = 0; 
					int row = reqListTB.getSelectedRow();
					int vNum6 = Integer.parseInt((String) reqListTB.getValueAt(row, col));

					try {

						WorkerReqDAO wkdao = new WorkerReqDAO();
						int nodupApply = wkdao.noDupApply(vNum6, workerCode);

						if (nodupApply == 0) {

							ApplyVO vo = new ApplyVO();

							vo.setReqContCode(vNum6);
							vo.setWorkerCode(Integer.parseInt(workerCode));

							wkdao.applyDateInsert(vo);

							JOptionPane.showMessageDialog(null, "지원 등록이 완료되었습니다.");

						} else {
							JOptionPane.showMessageDialog(null, "중복지원은 불가합니다.");

						}

						reqListTB(reqHeader);

					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "지원등록을 실패하였습니다." + e2.getMessage());
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}


			}
		});

		joinConfBtn.setBounds(1262, 621, 131, 46);
		reqPanel.add(joinConfBtn);
		joinConfBtn.setForeground(Color.WHITE);
		joinConfBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		joinConfBtn.setBackground(new Color(16, 24, 32));

		JPanel reqInfoPanel = new JPanel();
		reqInfoPanel.setLayout(null);
		reqInfoPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqInfoPanel.setBackground(new Color(16, 24, 32));
		reqInfoPanel.setBounds(774, 46, 619, 565);
		reqPanel.add(reqInfoPanel);

		JLabel reqDateLabel = new JLabel("요청일자");
		reqDateLabel.setForeground(new Color(242, 170, 76));
		reqDateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqDateLabel.setBounds(27, 30, 75, 33);
		reqInfoPanel.add(reqDateLabel);

		JLabel cityNameLabel = new JLabel("도시");
		cityNameLabel.setForeground(new Color(242, 170, 76));
		cityNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		cityNameLabel.setBounds(333, 231, 75, 33);
		reqInfoPanel.add(cityNameLabel);

		JLabel expecSdateLabel = new JLabel("예상근무시작일");
		expecSdateLabel.setForeground(new Color(242, 170, 76));
		expecSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		expecSdateLabel.setBounds(27, 165, 105, 33);
		reqInfoPanel.add(expecSdateLabel);

		JLabel reqLangLevel = new JLabel("필수어학수준");
		reqLangLevel.setForeground(new Color(242, 170, 76));
		reqLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqLangLevel.setBounds(27, 297, 105, 33);
		reqInfoPanel.add(reqLangLevel);

		JLabel ageRangeLabel = new JLabel("연령대");
		ageRangeLabel.setForeground(new Color(242, 170, 76));
		ageRangeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		ageRangeLabel.setBounds(333, 429, 55, 33);
		reqInfoPanel.add(ageRangeLabel);

		JLabel localLabel = new JLabel("상세근무장소");
		localLabel.setForeground(new Color(242, 170, 76));
		localLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		localLabel.setBounds(27, 363, 105, 33);
		reqInfoPanel.add(localLabel);

		JLabel sexLabel = new JLabel("성별");
		sexLabel.setForeground(new Color(242, 170, 76));
		sexLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sexLabel.setBounds(27, 429, 43, 33);
		reqInfoPanel.add(sexLabel);

		JPanel workerManagePanel_1_2_1 = new JPanel();
		workerManagePanel_1_2_1.setLayout(null);
		workerManagePanel_1_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerManagePanel_1_2_1.setBounds(376, 95, 1, 1);
		reqInfoPanel.add(workerManagePanel_1_2_1);

		JLabel workerListLabel_2_2_1 = new JLabel("파견계약목록");
		workerListLabel_2_2_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_2_2_1.setBounds(12, 10, 113, 33);
		workerManagePanel_1_2_1.add(workerListLabel_2_2_1);

		JPanel workerListPanel_2_2_1 = new JPanel();
		workerListPanel_2_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_2_2_1.setBounds(12, 46, 560, 427);
		workerManagePanel_1_2_1.add(workerListPanel_2_2_1);

		JLabel workerListLabel_1_1_2_1 = new JLabel("파견계약상세정보");
		workerListLabel_1_1_2_1.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_1_1_2_1.setBounds(595, 10, 127, 33);
		workerManagePanel_1_2_1.add(workerListLabel_1_1_2_1);

		JPanel workerListPanel_1_1_2 = new JPanel();
		workerListPanel_1_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_1_1_2.setBounds(595, 46, 537, 427);
		workerManagePanel_1_2_1.add(workerListPanel_1_1_2);

		JPanel workerManagePanel_1_1_2 = new JPanel();
		workerManagePanel_1_1_2.setLayout(null);
		workerManagePanel_1_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_1_1_2.add(workerManagePanel_1_1_2);

		JLabel workerListLabel_2_1_2 = new JLabel("파견계약목록");
		workerListLabel_2_1_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_2_1_2.setBounds(12, 10, 113, 33);
		workerManagePanel_1_1_2.add(workerListLabel_2_1_2);

		JPanel workerListPanel_2_1_2 = new JPanel();
		workerListPanel_2_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerListPanel_2_1_2.setBounds(12, 46, 560, 427);
		workerManagePanel_1_1_2.add(workerListPanel_2_1_2);

		JLabel workerListLabel_1_1_1_2 = new JLabel("파견계약상세정보");
		workerListLabel_1_1_1_2.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		workerListLabel_1_1_1_2.setBounds(595, 10, 127, 33);
		workerManagePanel_1_1_2.add(workerListLabel_1_1_1_2);

		JLabel expecEdateLabel = new JLabel("예상근무종료일");
		expecEdateLabel.setForeground(new Color(242, 170, 76));
		expecEdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		expecEdateLabel.setBounds(333, 165, 105, 33);
		reqInfoPanel.add(expecEdateLabel);

		sexTx = new JTextField();
		sexTx.setEditable(false);
		sexTx.setHorizontalAlignment(SwingConstants.CENTER);
		sexTx.setColumns(10);
		sexTx.setBounds(175, 429, 122, 33);
		reqInfoPanel.add(sexTx);

		qualiTx = new JTextField();
		qualiTx.setEditable(false);
		qualiTx.setHorizontalAlignment(SwingConstants.CENTER);
		qualiTx.setColumns(10);
		qualiTx.setBounds(174, 495, 406, 33);
		reqInfoPanel.add(qualiTx);

		localTx = new JTextField();
		localTx.setEditable(false);
		localTx.setHorizontalAlignment(SwingConstants.CENTER);
		localTx.setColumns(10);
		localTx.setBounds(175, 363, 405, 33);
		reqInfoPanel.add(localTx);

		JLabel qualiLabel = new JLabel("자격요건");
		qualiLabel.setForeground(new Color(242, 170, 76));
		qualiLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		qualiLabel.setBounds(27, 495, 105, 33);
		reqInfoPanel.add(qualiLabel);

		ageRangeTx = new JTextField();
		ageRangeTx.setEditable(false);
		ageRangeTx.setHorizontalAlignment(SwingConstants.CENTER);
		ageRangeTx.setColumns(10);
		ageRangeTx.setBounds(458, 429, 122, 33);
		reqInfoPanel.add(ageRangeTx);

		langLevelTx = new JTextField();
		langLevelTx.setEditable(false);
		langLevelTx.setHorizontalAlignment(SwingConstants.CENTER);
		langLevelTx.setColumns(10);
		langLevelTx.setBounds(175, 231, 122, 33);
		reqInfoPanel.add(langLevelTx);

		cityNameTx = new JTextField();
		cityNameTx.setEditable(false);
		cityNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		cityNameTx.setColumns(10);
		cityNameTx.setBounds(458, 231, 122, 33);
		reqInfoPanel.add(cityNameTx);

		reqLangTx = new JTextField();
		reqLangTx.setEditable(false);
		reqLangTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqLangTx.setColumns(10);
		reqLangTx.setBounds(175, 297, 122, 33);
		reqInfoPanel.add(reqLangTx);

		expecEdateTx = new JTextField();
		expecEdateTx.setEditable(false);
		expecEdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		expecEdateTx.setColumns(10);
		expecEdateTx.setBounds(458, 165, 122, 33);
		reqInfoPanel.add(expecEdateTx);

		JLabel sectorNameLabel = new JLabel("업종");
		sectorNameLabel.setForeground(new Color(242, 170, 76));
		sectorNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sectorNameLabel.setBounds(27, 99, 75, 33);
		reqInfoPanel.add(sectorNameLabel);

		expecSdateTx = new JTextField();
		expecSdateTx.setEditable(false);
		expecSdateTx.setHorizontalAlignment(SwingConstants.CENTER);
		expecSdateTx.setColumns(10);
		expecSdateTx.setBounds(175, 165, 122, 33);
		reqInfoPanel.add(expecSdateTx);

		reqDateTx = new JTextField();
		reqDateTx.setHorizontalAlignment(SwingConstants.CENTER);
		reqDateTx.setForeground(Color.BLACK);
		reqDateTx.setEditable(false);
		reqDateTx.setColumns(10);
		reqDateTx.setBackground(Color.WHITE);
		reqDateTx.setBounds(174, 30, 122, 33);
		reqInfoPanel.add(reqDateTx);

		sectorNameTx = new JTextField();
		sectorNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		sectorNameTx.setEditable(false);
		sectorNameTx.setColumns(10);
		sectorNameTx.setBounds(175, 99, 405, 33);
		reqInfoPanel.add(sectorNameTx);

		JLabel langLevelLabel = new JLabel("필요언어");
		langLevelLabel.setForeground(new Color(242, 170, 76));
		langLevelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		langLevelLabel.setBounds(27, 231, 105, 33);
		reqInfoPanel.add(langLevelLabel);

		localLangLevelTx = new JTextField();
		localLangLevelTx.setEditable(false);
		localLangLevelTx.setHorizontalAlignment(SwingConstants.CENTER);
		localLangLevelTx.setColumns(10);
		localLangLevelTx.setBounds(458, 297, 122, 33);
		reqInfoPanel.add(localLangLevelTx);

		JLabel localLangLevel = new JLabel("현지어학수준");
		localLangLevel.setForeground(new Color(242, 170, 76));
		localLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		localLangLevel.setBounds(333, 297, 105, 36);
		reqInfoPanel.add(localLangLevel);

		JLabel custCodeLabel = new JLabel("업체번호");
		custCodeLabel.setForeground(new Color(242, 170, 76));
		custCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		custCodeLabel.setBounds(333, 30, 75, 33);
		reqInfoPanel.add(custCodeLabel);

		custCodeTx = new JTextField();
		custCodeTx.setHorizontalAlignment(SwingConstants.CENTER);
		custCodeTx.setForeground(Color.BLACK);
		custCodeTx.setEditable(false);
		custCodeTx.setColumns(10);
		custCodeTx.setBackground(Color.WHITE);
		custCodeTx.setBounds(458, 30, 122, 33);
		reqInfoPanel.add(custCodeTx);

		JLabel label = new JLabel("New label");
		label.setBounds(1030, 20, 57, 15);
		reqPanel.add(label);

		JLabel reqCodeLabel = new JLabel("");
		reqCodeLabel.setForeground(new Color(255, 128, 0));
		reqCodeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqCodeLabel.setBounds(1228, 10, 165, 33);
		reqPanel.add(reqCodeLabel);

		JButton joinConfBtn_1 = new JButton("\uC9C0\uC6D0\uCDE8\uC18C");
		joinConfBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견 지원 취소 처리", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int col = 7;
					int row = reqListTB.getSelectedRow();
					int vNum6 = Integer.parseInt(String.valueOf(reqListTB.getValueAt(row, col)).trim());

					try {
						WorkerReqDAO wrdao = new WorkerReqDAO();

						int state = wrdao.cancelApply(vNum6);
						reqListTB(reqHeader);
						JOptionPane.showMessageDialog(null, "지원등록이 취소되었습니다.");

						if (state > 0) {
							System.out.println("지원코드 삭제 성공");
						} else {
							System.out.println("지원코드 삭제 실패");
						}

					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "지원등록 취소 실패" + e2.getMessage());
						e2.printStackTrace();
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}

			}
		});

		joinConfBtn_1.setForeground(Color.WHITE);
		joinConfBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		joinConfBtn_1.setBackground(new Color(16, 24, 32));
		joinConfBtn_1.setBounds(1119, 621, 131, 46);
		reqPanel.add(joinConfBtn_1);

		// 파견요청관리 탭 메인
		JPanel contInfoP = new JPanel();
		contInfoP.setBackground(new Color(16, 24, 32));
		contInfoP.setForeground(new Color(16, 24, 32));
		contInfoP.setLayout(null);
		contInfoP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerMainTab.addTab("\uACE0\uC6A9\uACC4\uC57D\uAD00\uB9AC", null, contInfoP, null);

		JLabel contListLabel = new JLabel("\uACE0\uC6A9\uACC4\uC57D\uBAA9\uB85D");
		contListLabel.setForeground(new Color(242, 170, 76));
		contListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contListLabel.setBounds(12, 10, 113, 33);
		contInfoP.add(contListLabel);

		JPanel contLinstPanel = new JPanel();
		contLinstPanel.setBackground(new Color(16, 24, 32));
		contLinstPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		contLinstPanel.setBounds(12, 46, 750, 621);
		contInfoP.add(contLinstPanel);
		contLinstPanel.setLayout(null);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		contListTB = new JTable(contModel);

		contListTB.getColumn("고용계약번호").setPreferredWidth(3);
		contListTB.getColumn("계약시작일").setPreferredWidth(50);
		contListTB.getColumn("계약만료일").setPreferredWidth(6);
		contListTB.getColumn("계약일").setPreferredWidth(3);
		contListTB.getColumn("계약성사여부").setPreferredWidth(3);

		contListTB.getColumn("고용계약번호").setCellRenderer(center);
		contListTB.getColumn("계약시작일").setCellRenderer(center);
		contListTB.getColumn("계약만료일").setCellRenderer(center);
		contListTB.getColumn("계약일").setCellRenderer(center);
		contListTB.getColumn("계약성사여부").setCellRenderer(center);

		contListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = contListTB.getSelectedRow();
				vNum1 = (String) contListTB.getValueAt(row, col);


				// -----------------------------------------------------------------------------------------------------------------------------------------------고용계약관리
				try {
					WorkerContInfoDAO wcInfodao = new WorkerContInfoDAO();
					WorkerContVO vo = wcInfodao.workerserchContInfo(vNum1);

					// vo의 각각의 값을 오른쪽 화면에 출력
					workerContCodeLabel.setText(String.valueOf(vo.getReqContCode()));
					contNumTx.setText(String.valueOf(vo.getWorkerContCode()));
					contSdateTx.setText(vo.getWorkerContSdate());
					contEdateTx.setText(vo.getWorkerContEdate());
					contPeriodTx.setText(vo.getContPeriod());
					contReconTx.setText(String.valueOf(vo.getRecontNum()));
					actualSdateTx.setText(vo.getActualSdate());
					actualEdateTx.setText(vo.getActualEdate());
					accBankTx.setText(vo.getAccBank());
					accNumTx.setText(String.valueOf(vo.getAccNum()));
					
					accNameTx.setEditable(true);
					accBankTx.setEditable(true);
					accNumTx.setEditable(true);



				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "고용계약정보 출력 실패 : " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		contListTB.setBounds(0, 0, 610, 493);
		contLinstPanel.add(contListTB);

		JScrollPane contListScr = new JScrollPane(contListTB);
		contListScr.setBounds(10, 10, 728, 601);
		contLinstPanel.add(contListScr);

		JLabel contInfoLabel = new JLabel("\uACE0\uC6A9\uACC4\uC57D\uC815\uBCF4");
		contInfoLabel.setForeground(new Color(242, 170, 76));
		contInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contInfoLabel.setBounds(774, 10, 498, 33);
		contInfoP.add(contInfoLabel);

		JPanel contInfoPanel = new JPanel();
		contInfoPanel.setBackground(new Color(16, 24, 32));
		contInfoPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		contInfoPanel.setBounds(774, 46, 619, 565);
		contInfoP.add(contInfoPanel);
		contInfoPanel.setLayout(null);

		JLabel contNumLabel = new JLabel("\uACE0\uC6A9\uACC4\uC57D \uBC88\uD638");
		contNumLabel.setForeground(new Color(242, 170, 76));
		contNumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contNumLabel.setBounds(36, 41, 112, 33);
		contInfoPanel.add(contNumLabel);

		contNumTx = new JTextField();
		contNumTx.setEditable(false);
		contNumTx.setBounds(156, 41, 157, 33);
		contInfoPanel.add(contNumTx);
		contNumTx.setColumns(10);

		JLabel accInfoLabel = new JLabel("\uC785\uAE08\uACC4\uC88C\uC815\uBCF4");
		accInfoLabel.setForeground(new Color(242, 170, 76));
		accInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accInfoLabel.setBounds(36, 337, 112, 33);
		contInfoPanel.add(accInfoLabel);

		JLabel workerContSdateTitel = new JLabel("\uACC4\uC57D\uC2DC\uC791\uC77C");
		workerContSdateTitel.setBounds(36, 115, 77, 33);
		contInfoPanel.add(workerContSdateTitel);
		workerContSdateTitel.setForeground(new Color(242, 170, 76));
		workerContSdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel recontNumTitel = new JLabel("\uC7AC\uACC4\uC57D\uD69F\uC218");
		recontNumTitel.setBounds(36, 189, 77, 33);
		contInfoPanel.add(recontNumTitel);
		recontNumTitel.setForeground(new Color(242, 170, 76));
		recontNumTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		recontNumTitel.setBackground(new Color(255, 128, 128));

		contSdateTx = new JTextField();
		contSdateTx.setEditable(false);
		contSdateTx.setBounds(156, 115, 133, 33);
		contInfoPanel.add(contSdateTx);
		contSdateTx.setColumns(10);

		contReconTx = new JTextField();
		contReconTx.setEditable(false);
		contReconTx.setBounds(156, 189, 133, 33);
		contInfoPanel.add(contReconTx);
		contReconTx.setColumns(10);

		contEdateTx = new JTextField();
		contEdateTx.setEditable(false);
		contEdateTx.setBounds(446, 115, 133, 33);
		contInfoPanel.add(contEdateTx);
		contEdateTx.setColumns(10);

		contPeriodTx = new JTextField();
		contPeriodTx.setEditable(false);
		contPeriodTx.setBounds(446, 189, 133, 33);
		contInfoPanel.add(contPeriodTx);
		contPeriodTx.setColumns(10);

		JLabel contPeriodLabel = new JLabel("\uACC4\uC57D\uAE30\uAC04");
		contPeriodLabel.setBounds(318, 189, 70, 33);
		contInfoPanel.add(contPeriodLabel);
		contPeriodLabel.setForeground(new Color(242, 170, 76));
		contPeriodLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		contPeriodLabel.setBackground(new Color(255, 128, 128));

		JLabel workerContEdateTitel = new JLabel("\uACC4\uC57D\uB9CC\uB8CC\uC77C");
		workerContEdateTitel.setBounds(318, 115, 77, 33);
		contInfoPanel.add(workerContEdateTitel);
		workerContEdateTitel.setForeground(new Color(242, 170, 76));
		workerContEdateTitel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel accNameLabel = new JLabel("\uC608\uAE08\uC8FC");
		accNameLabel.setBounds(36, 427, 70, 33);
		contInfoPanel.add(accNameLabel);
		accNameLabel.setForeground(new Color(242, 170, 76));
		accNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		accNameLabel.setBackground(new Color(255, 128, 128));

		JLabel accNumLabel = new JLabel("\uC785\uAE08\uACC4\uC88C\uBC88\uD638");
		accNumLabel.setBounds(36, 501, 108, 33);
		contInfoPanel.add(accNumLabel);
		accNumLabel.setForeground(new Color(242, 170, 76));
		accNumLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		accNameTx = new JTextField();
		accNameTx.setBounds(156, 428, 133, 33);
		contInfoPanel.add(accNameTx);
		accNameTx.setText((String) null);
		accNameTx.setEditable(false);
		accNameTx.setColumns(10);

		accNumTx = new JTextField();
		accNumTx.setBounds(156, 501, 423, 33);
		contInfoPanel.add(accNumTx);
		accNumTx.setColumns(10);

		JLabel accBankLabel = new JLabel("\uC785\uAE08\uC740\uD589\uBA85");
		accBankLabel.setBounds(318, 427, 77, 33);
		contInfoPanel.add(accBankLabel);
		accBankLabel.setForeground(new Color(242, 170, 76));
		accBankLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		accBankTx = new JTextField();
		accBankTx.setBounds(430, 428, 149, 33);
		contInfoPanel.add(accBankTx);
		accBankTx.setText((String) null);
		accBankTx.setColumns(10);

		JLabel actualSdateLabel = new JLabel("\uC2E4\uADFC\uBB34\uC2DC\uC791\uC77C");
		actualSdateLabel.setForeground(new Color(242, 170, 76));
		actualSdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		actualSdateLabel.setBackground(new Color(255, 128, 128));
		actualSdateLabel.setBounds(36, 263, 96, 33);
		contInfoPanel.add(actualSdateLabel);

		actualSdateTx = new JTextField();
		actualSdateTx.setEditable(false);
		actualSdateTx.setColumns(10);
		actualSdateTx.setBounds(156, 263, 133, 33);
		contInfoPanel.add(actualSdateTx);

		JLabel actualEdateLabel = new JLabel("\uC2E4\uADFC\uBB34\uC885\uB8CC\uC77C");
		actualEdateLabel.setForeground(new Color(242, 170, 76));
		actualEdateLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		actualEdateLabel.setBackground(new Color(255, 128, 128));
		actualEdateLabel.setBounds(318, 266, 96, 27);
		contInfoPanel.add(actualEdateLabel);

		actualEdateTx = new JTextField();
		actualEdateTx.setEditable(false);
		actualEdateTx.setColumns(10);
		actualEdateTx.setBounds(446, 263, 133, 33);
		contInfoPanel.add(actualEdateTx);

		JButton accIntoModifyBtn = new JButton("\uACC4\uC88C\uC815\uBCF4 \uB4F1\uB85D/\uC218\uC815");
		accIntoModifyBtn.setBounds(430, 338, 149, 33);
		contInfoPanel.add(accIntoModifyBtn);
		accIntoModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견계약 등록/수정 확인", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int workerContCode = Integer.parseInt(contNumTx.getText());
					String accName = accNameTx.getText();
					String accBank = accBankTx.getText();
					String accNum = accNumTx.getText();

					try {
						WorkerContInfoDAO dao = new WorkerContInfoDAO();
						vo = new WorkerContVO(workerContCode, accName, accBank, accNum);
						// dao.workerAccUpdate(vo);

						int srrr = dao.workerAccUpdate(vo);
						accBankTx.setEditable(false);
						accNumTx.setEditable(false);

						if (srrr > 0) {
							System.out.println("찍기 성공");
						} else {
							System.out.println("찍기 실패");
						}

						JOptionPane.showMessageDialog(null, "계좌정보가 수정되었습니다.");

					} catch (Exception ev) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "계좌정보가 수정되지 않았습니다.");
						ev.printStackTrace();
					}

				} else if (result == JOptionPane.NO_OPTION) {
					
				}


			}
		});

		workerContCodeLabel = new JLabel("New label");
		workerContCodeLabel.setForeground(new Color(255, 255, 0));
		workerContCodeLabel.setBounds(446, 41, 96, 33);
		contInfoPanel.add(workerContCodeLabel);

		JLabel lblNewLabel = new JLabel(
				"* \uACC4\uC57D\uCCB4\uACB0 \uD76C\uB9DD \uC2DC, \uACC4\uC88C\uC815\uBCF4\uB97C \uC6B0\uC120 \uB4F1\uB85D\uD574\uC8FC\uC138\uC694.");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(46, 360, 295, 25);
		contInfoPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"* \uB2E8, \uACC4\uC88C\uC815\uBCF4\uB294 \uB2E8 1\uD68C\uB9CC \uC218\uC815 \uAC00\uB2A5");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(46, 380, 466, 25);
		contInfoPanel.add(lblNewLabel_1);

		
		//고용계약서 "수락버튼" 누를 시,  '계약요청' --->  '계약수락' 으로 상태 변경
		JButton reqContentBtn = new JButton("\uC218\uB77D");
		reqContentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "파견 계약 확인 처리", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {

					int col = 0;
					int row = contListTB.getSelectedRow();
					int vNum7 = Integer.parseInt((String) contListTB.getValueAt(row, col));

					int col1 = 4;
					String vNum8 = (String) contListTB.getValueAt(row, col1);
					
					if (vNum8.equals("계약수락")) {
						JOptionPane.showMessageDialog(null, "수락 중복 처리 불가");
					} else if(vNum8.equals("계약체결")){	
						JOptionPane.showMessageDialog(null, "이미 계약체결 된 건은 수락 처리 불가");
					} else if(vNum8.equals("계약거절")){	
						JOptionPane.showMessageDialog(null, "이미 계약거절된 건은 수락 처리 불가");
					} else if(vNum8.equals("계약요청")){					
						try {

							WorkerContInfoDAO wdao = new WorkerContInfoDAO();
							WorkerContVO vo = new WorkerContVO();
							
							vo.setWorkerContCode(vNum7);

							int state = wdao.acceptContUpdate(vo);

							if (state > 0) {
								System.out.println("상태변경 성공");
							} else {
								System.out.println("상태변경 실패");
							}
							JOptionPane.showMessageDialog(null, "해당 계약 건을 수락하였습니다.");
						
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "수락 실패하였습니다." + e2.getMessage());
						}
					}
					vContListTB(contHeader);
				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				
				

			}
		});
				
		reqContentBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqContentBtn.setForeground(new Color(255, 255, 255));
		reqContentBtn.setBackground(new Color(16, 24, 32));
		reqContentBtn.setBounds(1251, 621, 142, 46);
		contInfoP.add(reqContentBtn);

		
		
		//고용계약서 "거절버튼" 누를 시,  '계약요청' --->  '계약거절' 로 상태 변경
		JButton rejectBtn = new JButton("\uAC70\uC808");
		rejectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int result = JOptionPane.showConfirmDialog(null, "파견 계약 거절 처리", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					int col = 0;
					int row = contListTB.getSelectedRow();
					int vNum10 = Integer.parseInt((String) contListTB.getValueAt(row, col));

					int col1 = 4;
					String vNum9 = (String) contListTB.getValueAt(row, col1);
					
					if (vNum9.equals("계약수락")) {
						JOptionPane.showMessageDialog(null, "이미 계약수락한 건은 거절 처리 불가");
					} else if(vNum9.equals("계약거절")){
						JOptionPane.showMessageDialog(null, "거절 중복 처리 불가");

					} else if(vNum9.equals("계약체결")){
						JOptionPane.showMessageDialog(null, "이미 계약체결된 건은 거절 처리 불가");
						
					} else if(vNum9.equals("계약요청")){					
						try {

							WorkerContInfoDAO wdao = new WorkerContInfoDAO();
							WorkerContVO vo1 = new WorkerContVO();
							
							vo1.setWorkerContCode(vNum10);

							int state = wdao.rejectContUpdate(vo1);

							if (state > 0) {
								System.out.println("상태변경 성공");
							} else {
								System.out.println("상태변경 실패");
							}
						
							JOptionPane.showMessageDialog(null, "해당 계약요청을 거절하였습니다.");

						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "거절 실패하였습니다." + e2.getMessage());
						}
					}
					vContListTB(contHeader);

				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				

			}
		});
		
		
		rejectBtn.setForeground(Color.WHITE);
		rejectBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		rejectBtn.setBackground(new Color(16, 24, 32));
		rejectBtn.setBounds(1097, 621, 142, 46);
		contInfoP.add(rejectBtn);

		JButton workerSerchBtn_1 = new JButton("\uC870\uD68C");
		workerSerchBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vContListTB(contHeader);
			}
		});
		workerSerchBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerSerchBtn_1.setBounds(673, 15, 89, 23);
		contInfoP.add(workerSerchBtn_1);
		
		//========================================================================================
		
		JPanel payManagerPanel = new JPanel();
		payManagerPanel.setBackground(new Color(16, 24, 32));
		payManagerPanel.setLayout(null);
		payManagerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		workerMainTab.addTab("수당관리", null, payManagerPanel, null);

		JLabel payListLabel = new JLabel("월별 수당 지급내역");
		payListLabel.setForeground(new Color(242, 170, 76));
		payListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		payListLabel.setBounds(12, 10, 142, 33);
		payManagerPanel.add(payListLabel);

		JPanel payListPanel = new JPanel();
		payListPanel.setBackground(new Color(16, 24, 32));
		payListPanel.setLayout(null);
		payListPanel
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		payListPanel.setBounds(12, 46, 824, 621);
		payManagerPanel.add(payListPanel);
		
		payTabel = new JTable(payModel);
		payTabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				int col = 0; // 컬럼 위치
				int row = payTabel.getSelectedRow(); // 내가 클릭한 행의 위치
				int vNum = Integer.parseInt((String)payTabel.getValueAt(row, col));
				
				NumberFormat numberFormat = NumberFormat.getInstance();
				
				try {
					PayVO vo = workerPayDao.workerPayOutput(vNum);
					
					workerCode_tf.setText(String.valueOf(vo.getWorkerCode()));
					recontPay_tf.setText(numberFormat.format(vo.getRecontIncen()));
					tax_tf.setText(numberFormat.format(vo.getWorkerTax()));
					total_tf.setText(numberFormat.format(vo.getActualPay()));
					workerPay_tf.setText(numberFormat.format(vo.getPay()));
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
				
				
			}
		});
		payTabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		payTabel.setBounds(12, 10, 800, 601);
		payListPanel.add(payTabel);
		
		JScrollPane scrollPane = new JScrollPane(payTabel);
		scrollPane.setBounds(12, 10, 800, 601);
		payListPanel.add(scrollPane);
		
		table_1 = new JTable((TableModel) null);
		table_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		table_1.setBounds(0, 0, 798, 0);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(271, 62, 176, 33);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(271, 195, 176, 33);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(271, 259, 176, 33);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(271, 322, 176, 33);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(271, 133, 176, 33);

		JLabel workerPayLabel = new JLabel("인력별 수당 내역");
		workerPayLabel.setForeground(new Color(242, 170, 76));
		workerPayLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPayLabel.setBounds(848, 10, 119, 33);
		payManagerPanel.add(workerPayLabel);

		JPanel sheetCustPanel_1 = new JPanel();
		sheetCustPanel_1.setBackground(new Color(16, 24, 32));
		sheetCustPanel_1.setLayout(null);
		sheetCustPanel_1
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		sheetCustPanel_1.setBounds(848, 46, 545, 427);
		payManagerPanel.add(sheetCustPanel_1);
		
		JLabel lblNewLabel_3 = new JLabel("파견 지원자 번호");
		lblNewLabel_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(125, 62, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3);
		
		workerCode_tf = new JTextField();
		workerCode_tf.setHorizontalAlignment(SwingConstants.CENTER);
		workerCode_tf.setEditable(false);
		workerCode_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerCode_tf.setBounds(271, 62, 176, 33);
		sheetCustPanel_1.add(workerCode_tf);
		workerCode_tf.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("재계약 추가 수당");
		lblNewLabel_3_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setBounds(125, 195, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_1);
		
		recontPay_tf = new JTextField();
		recontPay_tf.setHorizontalAlignment(SwingConstants.CENTER);
		recontPay_tf.setEditable(false);
		recontPay_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		recontPay_tf.setColumns(10);
		recontPay_tf.setBounds(271, 195, 176, 33);
		sheetCustPanel_1.add(recontPay_tf);
		
		JLabel lblNewLabel_3_2 = new JLabel("세금");
		lblNewLabel_3_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setBounds(125, 259, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_2);
		
		tax_tf = new JTextField();
		tax_tf.setHorizontalAlignment(SwingConstants.CENTER);
		tax_tf.setEditable(false);
		tax_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		tax_tf.setColumns(10);
		tax_tf.setBounds(271, 259, 176, 33);
		sheetCustPanel_1.add(tax_tf);
		
		JLabel lblNewLabel_3_3 = new JLabel("실 수당 정산금액");
		lblNewLabel_3_3.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setBounds(125, 322, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_3);
		
		total_tf = new JTextField();
		total_tf.setHorizontalAlignment(SwingConstants.CENTER);
		total_tf.setEditable(false);
		total_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		total_tf.setColumns(10);
		total_tf.setBounds(271, 322, 176, 33);
		sheetCustPanel_1.add(total_tf);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("재계약 추가 수당");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel_3_1_1.setBounds(125, 133, 119, 33);
		sheetCustPanel_1.add(lblNewLabel_3_1_1);
		
		workerPay_tf = new JTextField();
		workerPay_tf.setHorizontalAlignment(SwingConstants.CENTER);
		workerPay_tf.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerPay_tf.setEditable(false);
		workerPay_tf.setColumns(10);
		workerPay_tf.setBounds(271, 133, 176, 33);
		sheetCustPanel_1.add(workerPay_tf);
		
		JButton sheetJoinBtn_1 = new JButton("조회");
		sheetJoinBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				workerPayTB(payHeader);
				
				
			}
		});
		sheetJoinBtn_1.setForeground(Color.WHITE);
		sheetJoinBtn_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		sheetJoinBtn_1.setBackground(new Color(16, 24, 32));
		sheetJoinBtn_1.setBounds(747, 15, 89, 23);
		payManagerPanel.add(sheetJoinBtn_1);
		
		
		
		
		
		
		// ==========================================================================================

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);

		JLabel mainViewLogoLabel = new JLabel("해외파견관리");
		mainViewLogoLabel.setBounds(12, 10, 120, 55);
		mainViewLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		contentPane.add(mainViewLogoLabel);

		JLabel workerLogoLabel = new JLabel("<파견인력>");
		workerLogoLabel.setBounds(131, 10, 120, 55);
		workerLogoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		contentPane.add(workerLogoLabel);


		JButton profileBtn = new JButton("");
		profileBtn.setBounds(1341, 10, 81, 73);
		profileBtn.setIcon(new ImageIcon("C:\\Users\\Koreavc4\\Desktop\\make.png"));
		profileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				WProfileInfoView profileInfo = new WProfileInfoView(0);
				System.out.println(id);
				profileInfo.profile_Info(id);

			}
		});
		contentPane.add(profileBtn);

		JButton logOutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logOutBtn.setBounds(1219, 43, 98, 23);
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] login = new String[0];
				new LoginView().main(login);;
				dispose();
				
			}
		});
		contentPane.add(logOutBtn);
		accAddname(id);
		careerListTB(careerHeader);
		certiListTB(certiHeader);
	//	contListTB(contHeader);

	}

	public void accAddname(String ID) {

		try {

			WorkerContInfoDAO dao = new WorkerContInfoDAO();
			String vo = dao.workerAccNameSearch(id);
			accNameTx.setText(vo);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void reqListTB(String[] reqHeader) {

		try {
			WorkerReqDAO dao = new WorkerReqDAO();

			ArrayList reqList = dao.serchReqInfo(workerCode); // 요청목록 ArrayList 형태로 가져오기
			// String[][] contentsReq = dao.reqList(reqList, reqHeader);
			String[][] contentsReq = super.changeArrayList(reqList, reqHeader);

			reqModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsReq.length; i++) {
				reqModel.addRow(contentsReq[i]);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void vContListTB(String[] contHeader) {

		try {
			WorkerContInfoDAO dao = new WorkerContInfoDAO();

			ArrayList contList = dao.serchContInfo(workerCode); // 요청목록 ArrayList 형태로 가져오기
			String[][] contentsReq = super.changeArrayList(contList, contHeader);
			
			contModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsReq.length; i++) {
				contModel.addRow(contentsReq[i]);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void careerListTB(String[] careerHeader) {

		try {
			CareerDAO dao = new CareerDAO();

			ArrayList careerList = dao.searchCareerInfo(workerCode); // 요청목록 ArrayList 형태로 가져오기
			String[][] contentsReq = super.changeArrayList(careerList, careerHeader);

			careerModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsReq.length; i++) {
				careerModel.addRow(contentsReq[i]);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void certiListTB(String[] certiHeader) {

		try {
			CareerDAO dao = new CareerDAO();

			ArrayList certiList = dao.searchCertiInfo(workerCode); // 요청목록 ArrayList 형태로 가져오기
			String[][] contentsReq = super.changeArrayList(certiList, certiHeader);
			
			// System.out.println(contentsReq[0][0]);
			certiModel.setNumRows(0); // 초기화

			for (int i = 0; i < contentsReq.length; i++) {
				certiModel.addRow(contentsReq[i]);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	// 인력 정산목록 메소드
	void workerPayTB(String[] header) {
		// TODO: 계약목록 출력 메소드
		try {
			
			
			workerPayDao = new WorkerPayDAO();
			
			ArrayList payList = workerPayDao.workerPayArrayList(workerCode); // 인력목록 ArrayList 형태로 가져오기
			String[][] payContents = super.changeArrayList(payList, header);			
			
			payModel.setNumRows(0);
			
			for (int i = 0; i < payContents.length; i++) {
				payModel.addRow(payContents[i]);
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "인력수당목록오류 : " + e1.getMessage());
		}
	}

	void codeSearch(String id) {

		try {

			LoginDAO dao = new LoginDAO();

			code = dao.workerCode(id);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	void clearCareer() {

		careerCodeTx.setText("");
		careerNameTx.setText("");
		careerSdateTx.setText("");
		careerEdateTx.setText("");
		careerDetailTx.setText("");
	}

	void clearCerti() {

		certiCodeTx.setText("");
		certiExpPeriodTx.setText("");
		certiDateTx.setText("");
		certiNumTx.setText("");
		certiNameTx.setText("");
	}

	void clearWorkerCont() {
		contNumTx.setText("");
		contSdateTx.setText("");
		contEdateTx.setText("");
		contPeriodTx.setText("");
		contReconTx.setText("");
		actualSdateTx.setText("");
		actualEdateTx.setText("");
		accNameTx.setText("");
		accBankTx.setText("");
		accNumTx.setText("");
	}

	void clearAcc() {
		accNameTx.setText("");
		accBankTx.setText("");
		accNumTx.setText("");
	}
}