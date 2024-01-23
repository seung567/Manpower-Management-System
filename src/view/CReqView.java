package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LoginDAO;
import model.ReqContDAO;
import model.ReqDAO;
import model.rec.CustVO;
import model.rec.ReqVO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class CReqView extends JFrame {

	private JPanel contentPane;
	private JTextField tf_custCode;
	private JTextField tf_expecSdate;
	private JTextField tf_expecEdate;
	private JTextField tf_local;
	private JTextField tf_ageRange;
	private JTextField tf_quali;
	private JTextField tf_totalCost;
	private JTextField tf_workerNum;
	private JTextField tf_reqDate;
	private JTextField tf_perCost;
	private JComboBox reqLangcomboBox;
	private JComboBox sexComboBox;
	private JComboBox levelComboBox;
	private JComboBox regionComboBox;
	private JComboBox sectorComboBox;

	ReqDAO dao;		// DAO
	ReqVO vo;	 // VO


	// 화면구성
	public static void reqMain(String ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CReqView frame = new CReqView(ID);
					frame.setVisible(true);
					// 팝업창 x 버튼 누를시 창만 꺼지게 해주는 명령어
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {

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
	public CReqView() {
		// TODO Auto-generated constructor stub
		this.reqMain(null);
	}
	
	public CReqView(int num) {
		
	}
	
	public CReqView(String ID) {

		
		Map<Integer , Integer> sectorMap= new HashMap<>();
		sectorMap.put(0,201);
		sectorMap.put(1,202);
		sectorMap.put(2,203);
		sectorMap.put(3,204);
		sectorMap.put(4,205);
		sectorMap.put(5,206);
		sectorMap.put(6,207);
		sectorMap.put(7,208);
		sectorMap.put(8,209);
		sectorMap.put(9,210);
		sectorMap.put(10,211);
		sectorMap.put(11,212);
		sectorMap.put(12,213);
		sectorMap.put(13,214);
		sectorMap.put(14,215);
		sectorMap.put(15,216);
		sectorMap.put(16,217);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 720);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(253, 154, 108, -79);
		contentPane.add(horizontalBox);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(242, 170, 76));
		panel.setBounds(0, 0, 602, 681);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel req = new JLabel("파견요청서");
		req.setForeground(new Color(16, 24, 32));
		req.setBounds(251, 33, 100, 27);
		panel.add(req);
		req.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));

		tf_custCode = new JTextField();
		tf_custCode.setEditable(false);
		tf_custCode.setHorizontalAlignment(SwingConstants.CENTER);
		tf_custCode.setBounds(417, 85, 162, 27);
		panel.add(tf_custCode);
		tf_custCode.setColumns(10);
	

		tf_expecSdate = new JTextField();
		tf_expecSdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_expecSdate.setBounds(144, 183, 151, 27);
		panel.add(tf_expecSdate);
		tf_expecSdate.setColumns(10);

		tf_expecEdate = new JTextField();
		tf_expecEdate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_expecEdate.setBounds(417, 183, 162, 27);
		panel.add(tf_expecEdate);
		tf_expecEdate.setColumns(10);

		tf_local = new JTextField();
		tf_local.setHorizontalAlignment(SwingConstants.CENTER);
		tf_local.setBounds(144, 329, 435, 32);
		panel.add(tf_local);
		tf_local.setColumns(10);

		tf_ageRange = new JTextField();
		tf_ageRange.setHorizontalAlignment(SwingConstants.CENTER);
		tf_ageRange.setBounds(144, 433, 435, 32);
		panel.add(tf_ageRange);
		tf_ageRange.setColumns(10);

		tf_quali = new JTextField();
		tf_quali.setHorizontalAlignment(SwingConstants.CENTER);
		tf_quali.setBounds(144, 475, 435, 65);
		panel.add(tf_quali);
		tf_quali.setColumns(10);

		tf_totalCost = new JTextField();
		tf_totalCost.setEditable(false);
		tf_totalCost.setHorizontalAlignment(SwingConstants.CENTER);
		tf_totalCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 총 파견비용 = ((업종에따른금액) * 요청인원수) * 30%
				// tf_totalCost = ( tf_perCost * tf_workerNum) * 30%
				
			}
		});
		tf_totalCost.setBounds(144, 550, 273, 42);
		panel.add(tf_totalCost);
		tf_totalCost.setColumns(10);

		JComboBox langcomboBox = new JComboBox();
		langcomboBox.setModel(new DefaultComboBoxModel(new String[] {"영어만 가능", "현지어만 가능", "영어, 현지어 가능"}));
		langcomboBox.setBounds(144, 228, 151, 32);
		panel.add(langcomboBox);

		JComboBox sexComboBox = new JComboBox();
		sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"남자", "여자", "성별무관"}));
		sexComboBox.setBounds(144, 382, 134, 32);
		panel.add(sexComboBox);

		JComboBox reqLangLevelComboBox = new JComboBox();
		reqLangLevelComboBox.setModel(new DefaultComboBoxModel(new String[] {"상", "중", "하"}));
		reqLangLevelComboBox.setBounds(144, 279, 151, 32);
		panel.add(reqLangLevelComboBox);
		
		JComboBox localLangLevelComboBox = new JComboBox();
		localLangLevelComboBox.setModel(new DefaultComboBoxModel(new String[] {"상", "중", "하"}));
		localLangLevelComboBox.setBounds(417, 279, 162, 32);
		panel.add(localLangLevelComboBox);

		JComboBox cityComboBox = new JComboBox();
		cityComboBox.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Chicago", "Paris", "Marseille", "Berlin", "Frankfurt", "Madrid", "Barcelona", "Beijing", "Hangzhou", "Sao Pualo", "Rio de Janeiro"}));
		cityComboBox.setBounds(417, 230, 162, 29);
		panel.add(cityComboBox);

		JComboBox sectorComboBox = new JComboBox();
		sectorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					// 1. 값 가져오기
					int comboItem = sectorComboBox.getSelectedIndex();
					System.out.println(sectorMap.get(comboItem));
					
					dao = new ReqDAO();
					String result = dao.sectorSelected(sectorMap.get(comboItem));
					
					tf_perCost.setText(String.valueOf(result));
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		sectorComboBox.setModel(new DefaultComboBoxModel(new String[] {"토목공사업", "건축공사업", "토목건축공사업", "산업 및 환경설비공사업", "컴퓨터 프로그래밍, 시스템 통합 및 관리업 ", "소프트웨어 개발 및 공급업", "데이터베이스 및 온라인 정보 제공업", "한국어 교육 및 문화 교류", "국제 교육 컨설팅", "교육 프로젝트 관리 및 개발", "의료기기 제조업", "섬유 및 의류 제조업", "자동차 부품 제조업", "전자제품 제조업", "회계 및 재무 서비스", "컨설팅 서비스", "법률 서비스"}));
		sectorComboBox.setBounds(144, 135, 151, 27);
		panel.add(sectorComboBox);

		JButton registButton = new JButton("등록");
		registButton.setForeground(new Color(242, 170, 76));
		registButton.setBackground(new Color(16, 24, 32));
		registButton.setBounds(240, 617, 121, 34);
		panel.add(registButton);
		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "파견 요청 등록 확인", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					ReqVO vo = new ReqVO();

					try {					

						// 텍스트 필드에서 사용자가 입력한 문자열을 가져오는 메소드
						String reqDate = tf_reqDate.getText();		
						int custCode = Integer.parseInt(tf_custCode.getText());			
						String expecSdate = tf_expecSdate.getText();		
						String expecEdate = tf_expecEdate.getText();	
						String local = tf_local.getText();		
						int workerNum = Integer.parseInt(tf_workerNum.getText());									
						String ageRange = tf_ageRange.getText();		
						String quali = tf_quali.getText();		
						int totalCost = Integer.parseInt(tf_totalCost.getText().replace(",", "").trim());		

						String sex = null; 
						switch (sexComboBox.getSelectedIndex()) {
						case 0 : sex="남자";
						break;
						case 1 : sex="여자";
						break;
						case 2 : sex="성별무관";
						break;
						}

						int level = 0; 
						switch (langcomboBox.getSelectedIndex()) {
						case 0 : level=1;
						break;
						case 1 : level=2;
						break;
						case 2 : level=3;
						break;
						}

						String reqLangLevel = null;
						switch (reqLangLevelComboBox.getSelectedIndex()) {
						case 0 : reqLangLevel="상";
						break;
						case 1 : reqLangLevel="중";
						break;
						case 2 : reqLangLevel="하";
						break;				
						}
						
						String localLangLevel = null;
						switch (localLangLevelComboBox.getSelectedIndex()) {
						case 0 : localLangLevel="상";
						break;
						case 1 : localLangLevel="중";
						break;
						case 2 : localLangLevel="하";
						break;				
						}

						int region = 0;
						switch (cityComboBox.getSelectedIndex()) {
						case 0 : region=501;
						break;
						case 1 : region=502;
						break;
						case 2 : region=503;
						break;		
						case 3 : region=504;
						break;
						case 4 : region=505;
						break;
						case 5 : region=506;
						break;		
						case 6 : region=507;
						break;
						case 7 : region=508;
						break;
						case 8 : region=509;
						break;		
						case 9 : region=510;
						break;		
						case 10 : region=511;
						break;
						case 11 : region=512;
						break;
						}

						int sector = 0;
						switch (sectorComboBox.getSelectedIndex()) {
						case 0 : sector=201;
						break;
						case 1 : sector=202;
						break;
						case 2 : sector=203;
						break;		
						case 3 : sector=204;
						break;
						case 4 : sector=205;
						break;
						case 5 : sector=206;
						break;		
						case 6 : sector=207;
						break;
						case 7 : sector=208;
						break;
						case 8 : sector=209;
						break;		
						case 9 : sector=210;
						break;		
						case 10 : sector=211;
						break;
						case 11 : sector=212;
						break;
						case 12 : sector=213;
						break;		
						case 13 : sector=214;
						break;
						case 14 : sector=215;
						break;
						case 15 : sector=216;
						break;
						case 16 : sector=217;
						break;
						}

						ReqVO vo1 = new ReqVO(reqDate, custCode, sector, 
								expecSdate, expecEdate, level,
								reqLangLevel, localLangLevel, 
								region, local, sex,
								workerNum, ageRange, quali,
								totalCost, null);	
						
						ReqDAO dao = new ReqDAO();
						//					 ReqDAO의 regist 메소드 호출하여 데이터베이스에 등록
						dao.regist(vo1);
						//					 성공적으로 등록되면 메시지 출력 (예시)
						JOptionPane.showMessageDialog(null, "등록 성공!");
					} catch (Exception ex) {
						// 등록 중 오류가 발생하면 오류 메시지 출력
						JOptionPane.showMessageDialog(null, "등록 실패 : " + ex.getMessage());
					}
					dispose();
				} else if (result == JOptionPane.NO_OPTION) {
					
				}
	
			}
		});


		registButton.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));

		JLabel quali = new JLabel("자격요건");
		quali.setForeground(new Color(16, 24, 32));
		quali.setBounds(27, 475, 134, 23);
		panel.add(quali);
		quali.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel ageRange = new JLabel("연령대");
		ageRange.setForeground(new Color(16, 24, 32));
		ageRange.setBounds(27, 436, 102, 23);
		panel.add(ageRange);
		ageRange.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel sex = new JLabel("성별");
		sex.setForeground(new Color(16, 24, 32));
		sex.setBounds(27, 384, 89, 26);
		panel.add(sex);
		sex.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel reqLangLevel = new JLabel("필수어학수준");
		reqLangLevel.setForeground(new Color(16, 24, 32));
		reqLangLevel.setBounds(27, 283, 89, 23);
		panel.add(reqLangLevel);
		reqLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel local = new JLabel("상세근무장소");
		local.setForeground(new Color(16, 24, 32));
		local.setBounds(27, 326, 134, 27);
		panel.add(local);
		local.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel city = new JLabel("도시");
		city.setForeground(new Color(16, 24, 32));
		city.setBounds(307, 231, 102, 25);
		panel.add(city);
		city.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel lang = new JLabel("필요언어");
		lang.setForeground(new Color(16, 24, 32));
		lang.setBounds(30, 231, 79, 27);
		panel.add(lang);
		lang.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel expecEdate = new JLabel("예상근무종료일");
		expecEdate.setForeground(new Color(16, 24, 32));
		expecEdate.setBounds(307, 185, 102, 23);
		panel.add(expecEdate);
		expecEdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel expecSdate = new JLabel("예상근무시작일");
		expecSdate.setForeground(new Color(16, 24, 32));
		expecSdate.setBounds(30, 185, 102, 23);
		panel.add(expecSdate);
		expecSdate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel workerNum = new JLabel("요청인원수");
		workerNum.setForeground(new Color(16, 24, 32));
		workerNum.setBounds(307, 386, 84, 23);
		panel.add(workerNum);
		workerNum.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel sector = new JLabel("업종");
		sector.setForeground(new Color(16, 24, 32));
		sector.setBounds(30, 134, 52, 27);
		panel.add(sector);
		sector.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel custCode = new JLabel("업체번호");
		custCode.setForeground(new Color(16, 24, 32));
		custCode.setBounds(307, 84, 67, 27);
		panel.add(custCode);
		custCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));

		JLabel totalCost = new JLabel("총파견비용");
		totalCost.setForeground(new Color(16, 24, 32));
		totalCost.setBounds(27, 558, 79, 23);
		panel.add(totalCost);
		totalCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		
		tf_workerNum = new JTextField();
		tf_workerNum.setHorizontalAlignment(SwingConstants.CENTER);
		tf_workerNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				

				
			}
		});
		tf_workerNum.setColumns(10);
		tf_workerNum.setBounds(417, 383, 162, 31);
		panel.add(tf_workerNum);
		
		JLabel localLangLevel = new JLabel("현지어학수준");
		localLangLevel.setForeground(new Color(16, 24, 32));
		localLangLevel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		localLangLevel.setBounds(307, 283, 89, 23);
		panel.add(localLangLevel);
		
		tf_reqDate = new JTextField();
		tf_reqDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				// 업체번호 자동으로 가져오기
				try {
							
					LoginDAO logindao = new LoginDAO();
					CustVO vo = logindao.custCode(ID);
					tf_custCode.setText(String.valueOf(vo.getCustCode()));
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}		
		});
		
		tf_reqDate.setHorizontalAlignment(SwingConstants.CENTER);
		tf_reqDate.setColumns(10);
		tf_reqDate.setBounds(144, 85, 151, 27);
		panel.add(tf_reqDate);
		
		JLabel reqDate = new JLabel("요청일자");
		reqDate.setForeground(new Color(16, 24, 32));
		reqDate.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		reqDate.setBounds(27, 84, 67, 27);
		panel.add(reqDate);
		
		JLabel perCost = new JLabel("인당파견비용");
		perCost.setForeground(new Color(16, 24, 32));
		perCost.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 15));
		perCost.setBounds(309, 135, 100, 27);
		panel.add(perCost);
		
		tf_perCost = new JTextField();
		tf_perCost.setEditable(false);
		tf_perCost.setHorizontalAlignment(SwingConstants.CENTER);
		tf_perCost.setColumns(10);
		tf_perCost.setBounds(417, 136, 162, 27);
		panel.add(tf_perCost);
		
		JButton workerPriceCalBtn = new JButton("파견비용계산");
		workerPriceCalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				int price =Integer.parseInt(tf_perCost.getText().replace(",", "").trim());
				int workerNum = Integer.parseInt(tf_workerNum.getText());
				
				int result = price * workerNum;
				
				NumberFormat numberFormat = NumberFormat.getInstance();
				String val = numberFormat.format(result);
				tf_totalCost.setText(String.valueOf(val));
				
			}
		});
		workerPriceCalBtn.setBounds(429, 550, 150, 42);
		panel.add(workerPriceCalBtn);
	

	}
}