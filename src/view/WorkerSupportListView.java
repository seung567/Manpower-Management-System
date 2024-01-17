package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.WorkerSupportListDAO;
import model.rec.SupportVO;

public class WorkerSupportListView extends JFrame {

	private JPanel supportListPane;
	private JTable supportListTB;
	private JTextField supportNameTx;
	private JTextField supportTelTx;
	private JTextField supportAgeTx;
	private JTextField supportEmailTx;
	private JTable table_1;
	
	
	private String[] workerHead = {"이름","전화번호","이메일","나이","상세기술"};
	private String[][] content = null;
	private DefaultTableModel workerModel;
	
	int reqCode;
	WorkerSupportListDAO supportDAO;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	
	public static void workerSupportAction(String reqCode) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerSupportListView frame = new WorkerSupportListView(reqCode);
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
	
	public WorkerSupportListView() {
		
		this.workerSupportAction(null);
	}
	
	public WorkerSupportListView(int num) {
		
	}
	
	public WorkerSupportListView(String reqCode) {
		
		this.reqCode = Integer.parseInt(reqCode);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		supportListPane = new JPanel();
		supportListPane.setBackground(new Color(242, 170, 76));
		supportListPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(supportListPane);
		supportListPane.setLayout(null);
		
		JPanel supportListPanel = new JPanel();
		supportListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		supportListPanel.setBackground(new Color(16, 24, 32));
		supportListPanel.setBounds(12, 101, 537, 550);
		supportListPane.add(supportListPanel);
		supportListPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 513, 530);
		supportListPanel.add(scrollPane);
		
		
		workerModel = new DefaultTableModel(content,workerHead);
		
		supportListTB = new JTable(workerModel);
		scrollPane.setViewportView(supportListTB);
		
		JLabel supportListLabel = new JLabel("파견 지원자 목록");
		supportListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		supportListLabel.setBounds(200, 65, 143, 26);
		supportListPane.add(supportListLabel);
		
		JLabel supportInfoLabel = new JLabel("지원자 상세 정보");
		supportInfoLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 20));
		supportInfoLabel.setBounds(753, 65, 143, 26);
		supportListPane.add(supportInfoLabel);
		
		JLabel lblNewLabel_2 = new JLabel("파견 지원 현황");
		lblNewLabel_2.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(470, 15, 143, 30);
		supportListPane.add(lblNewLabel_2);
		
		JButton workerContInsertBtn = new JButton("계약요청");
		workerContInsertBtn.setForeground(new Color(255, 255, 255));
		workerContInsertBtn.setBackground(new Color(16, 24, 32));
		workerContInsertBtn.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		workerContInsertBtn.setBounds(896, 599, 176, 52);
		supportListPane.add(workerContInsertBtn);
		
		JPanel supportInfoPanel = new JPanel();
		supportInfoPanel.setLayout(null);
		supportInfoPanel.setForeground(Color.WHITE);
		supportInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		supportInfoPanel.setBackground(new Color(16, 24, 32));
		supportInfoPanel.setBounds(561, 101, 511, 488);
		supportListPane.add(supportInfoPanel);
		
		JLabel supportNameLabel = new JLabel("이름");
		supportNameLabel.setForeground(new Color(242, 170, 76));
		supportNameLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportNameLabel.setBackground(new Color(242, 170, 76));
		supportNameLabel.setBounds(52, 21, 84, 31);
		supportInfoPanel.add(supportNameLabel);
		
		supportNameTx = new JTextField();
		supportNameTx.setToolTipText("");
		supportNameTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportNameTx.setEditable(false);
		supportNameTx.setColumns(10);
		supportNameTx.setBounds(126, 21, 107, 31);
		supportInfoPanel.add(supportNameTx);
		
		JLabel supportTelLabel = new JLabel("연락처");
		supportTelLabel.setForeground(new Color(242, 170, 76));
		supportTelLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportTelLabel.setBackground(new Color(242, 170, 76));
		supportTelLabel.setBounds(52, 62, 84, 31);
		supportInfoPanel.add(supportTelLabel);
		
		supportTelTx = new JTextField();
		supportTelTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportTelTx.setEditable(false);
		supportTelTx.setColumns(10);
		supportTelTx.setBounds(126, 62, 107, 31);
		supportInfoPanel.add(supportTelTx);
		
		JLabel supportAgeLabel = new JLabel("나이");
		supportAgeLabel.setForeground(new Color(242, 170, 76));
		supportAgeLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportAgeLabel.setBackground(new Color(242, 170, 76));
		supportAgeLabel.setBounds(274, 21, 84, 31);
		supportInfoPanel.add(supportAgeLabel);
		
		supportAgeTx = new JTextField();
		supportAgeTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportAgeTx.setEditable(false);
		supportAgeTx.setColumns(10);
		supportAgeTx.setBounds(348, 21, 107, 31);
		supportInfoPanel.add(supportAgeTx);
		
		supportEmailTx = new JTextField();
		supportEmailTx.setHorizontalAlignment(SwingConstants.CENTER);
		supportEmailTx.setEditable(false);
		supportEmailTx.setColumns(10);
		supportEmailTx.setBounds(126, 103, 329, 31);
		supportInfoPanel.add(supportEmailTx);
		
		JLabel certiLabel = new JLabel("취득 자격증");
		certiLabel.setForeground(new Color(242, 170, 76));
		certiLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		certiLabel.setBackground(new Color(242, 170, 76));
		certiLabel.setBounds(12, 299, 84, 31);
		supportInfoPanel.add(certiLabel);
		
		JPanel certiPanel = new JPanel();
		certiPanel.setLayout(null);
		certiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		certiPanel.setBounds(12, 340, 487, 138);
		supportInfoPanel.add(certiPanel);
		
		table_1 = new JTable((TableModel) null);
		table_1.setEnabled(false);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table_1.setBounds(0, 0, 487, 138);
		certiPanel.add(table_1);
		
		JScrollPane certiTBscrollPane = new JScrollPane((Component) null);
		certiTBscrollPane.setBounds(0, 0, 487, 138);
		certiPanel.add(certiTBscrollPane);
		
		JLabel supportEmailLabel = new JLabel("이메일");
		supportEmailLabel.setForeground(new Color(242, 170, 76));
		supportEmailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportEmailLabel.setBackground(new Color(242, 170, 76));
		supportEmailLabel.setBounds(52, 103, 67, 31);
		supportInfoPanel.add(supportEmailLabel);
		
		JLabel careerDetailLabel = new JLabel("경력내용");
		careerDetailLabel.setForeground(new Color(242, 170, 76));
		careerDetailLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		careerDetailLabel.setBackground(new Color(242, 170, 76));
		careerDetailLabel.setBounds(12, 157, 67, 31);
		supportInfoPanel.add(careerDetailLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 198, 487, 97);
		supportInfoPanel.add(panel);
		
		JLabel supportAgeLabel_1 = new JLabel("경력기간");
		supportAgeLabel_1.setForeground(new Color(242, 170, 76));
		supportAgeLabel_1.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		supportAgeLabel_1.setBackground(new Color(242, 170, 76));
		supportAgeLabel_1.setBounds(274, 62, 67, 31);
		supportInfoPanel.add(supportAgeLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(348, 62, 107, 31);
		supportInfoPanel.add(textField);
		
		JButton btnNewButton = new JButton("새로고침");
		
		btnNewButton.setBackground(new Color(242, 170, 76));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		btnNewButton.setBounds(452, 69, 97, 23);
		supportListPane.add(btnNewButton);
		
		//┌───────────────────────────────────이벤트 선언부──────────────────────────────────────────────┐
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				supportTB(workerHead);
				
			}
		});
		
		supportListTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				System.out.println("클릭이벤트");
				try {
					
					supportDAO = new WorkerSupportListDAO();
					SupportVO vo = supportDAO.supportInfoSearch(Integer.parseInt(reqCode));
					System.out.println("vo 반환 완료");
					
//					workerCodeLabel.setText(reqCode);
					supportNameTx.setText(vo.getWorkerName());
					supportAgeTx.setText(String.valueOf(vo.getWorkerAge()));
					supportTelTx.setText(vo.getWorkerTel());
					supportEmailTx.setText(vo.getWorkerEmail());
					
					
					
					long dateValue = (vo.getCareerEdate().getTime() - vo.getCareerSdate().getTime())/(24*60*60*1000);
					int date = (int)dateValue/365;
					
					textField.setText(String.valueOf(date) + "년");
					
					
					

					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}
				
				
			}
		});
		//└───────────────────────────────────이벤트 선언부──────────────────────────────────────────────┘		

		
		supportTB(workerHead);
		
	}
	
	void supportTB(String[] workerHead) {
		
		try {
			
			supportDAO = new WorkerSupportListDAO();
			
			ArrayList supportList = supportDAO.searchSupportList(reqCode);
			String[][] supportArray = supportDAO.workerList(supportList,workerHead);
			
			workerModel.setNumRows(0);
			
			for (int i = 0; i < supportArray.length; i++) {
				workerModel.addRow(supportArray[i]);
	
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
