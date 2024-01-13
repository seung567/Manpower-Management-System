package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.workerDAO;
import java.awt.event.MouseEvent;

public class SameContCode extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel defaultModel;
	
	private String[] contHeader = {"계약번호", "이름", "계약시작일", "계약만료일", "계약일","계약상태"};
	private String[][] contents = null;
	
	private String codeText = null;
	private String idText = null;
//	private ActionListener viewText = null;
	
	private workerDAO dao = null;


	/**
	 * Launch the application.
	 */
	public static void sameContCodeAction(String code, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SameContCode frame = new SameContCode(code,id);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SameContCode() {
		

		this.sameContCodeAction("test","test");
		
	}
	
	public SameContCode(int num) {
		
	}
	
	public SameContCode(String codeValue, String id) {
		
		this.codeText = codeValue;
		this.idText = id;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 248);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		
		JLabel lblNewLabel = new JLabel("계약건 선택");
		lblNewLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel.setBounds(179, 10, 76, 15);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(16, 24, 32));
		panel.setBounds(12, 35, 410, 164);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		defaultModel = new DefaultTableModel(contents, contHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(defaultModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int col = 5;
				int row = table.getSelectedRow();
				
				String vNum = String.valueOf(table.getValueAt(row, col));
				System.out.println(vNum);
				
				if(vNum == null) {
					new WorkerContModify(0).workerContAtion(vNum,id);
					dispose();	
				}else {
					new WorkerContInfoView(0).workerContAtion(vNum,id);
					dispose();	
				}
				
				
			}
		});
		table.setBounds(12, 35, 410, 164);
		getContentPane().add(table);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		workerContList();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 410, 164);
		panel.add(scrollPane);
		
		
	}
	
	public void workerContList() {
		try {
			
			dao = new workerDAO();
			ArrayList contList = dao.workerContInfo(codeText);
			String[][] contContent = dao.workerList(contList, contHeader);
			
			for(int i=0; i<contContent.length; i++) {
				defaultModel.addRow(contContent[i]);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
