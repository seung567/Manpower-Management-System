package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.ManagerWorkerDAO;

public class MWorkerReqMatchingView extends JFrame {

	private JPanel contentPane;
	private JTable reqWorkerLisetTB;
	private DefaultTableModel defaultModel;

	private String[] contHeader = { "파견인력번호", "이름", "전화번호", "기술분류코드"};
	private String[][] contents = null;

	private String reqCode = null;
	private String idText = null;
	// private ActionListener viewText = null;

	private ManagerWorkerDAO dao = null;

	/**
	 * Launch the application.
	 * 
	 * 
	 * MWorkerReqMatchingView
	 * workerReqMatchingAction
	 */
	public static void workerReqMatchingAction(String code, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MWorkerReqMatchingView frame = new MWorkerReqMatchingView(code, id);
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
	public MWorkerReqMatchingView() {

		this.workerReqMatchingAction("test", "test");

	}

	public MWorkerReqMatchingView(int num) {

	}

	public MWorkerReqMatchingView(String reqCode, String id) {

		this.reqCode = reqCode;
		this.idText = id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 294);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(242, 170, 76));
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]

		JLabel reqWorkerListLabel = new JLabel("파견인력목록");
		reqWorkerListLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		reqWorkerListLabel.setBounds(216, 10, 84, 15);
		getContentPane().add(reqWorkerListLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 35, 493, 210);
		panel.setLayout(null);
		getContentPane().add(panel);

		defaultModel = new DefaultTableModel(contents, contHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		
		reqWorkerLisetTB = new JTable(defaultModel);
		
		reqWorkerLisetTB.getColumn("파견인력번호").setPreferredWidth(3);
		reqWorkerLisetTB.getColumn("이름").setPreferredWidth(3);
		reqWorkerLisetTB.getColumn("전화번호").setPreferredWidth(35);
		reqWorkerLisetTB.getColumn("기술분류코드").setPreferredWidth(35);
		
		reqWorkerLisetTB.getColumn("파견인력번호").setCellRenderer(center);
		reqWorkerLisetTB.getColumn("이름").setCellRenderer(center);
		reqWorkerLisetTB.getColumn("전화번호").setCellRenderer(center);
		reqWorkerLisetTB.getColumn("기술분류코드").setCellRenderer(center);
		
		reqWorkerLisetTB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		reqWorkerLisetTB.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		reqWorkerLisetTB.setBackground(new Color(255, 255, 255));
		reqWorkerLisetTB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int col = 0;
				int row = reqWorkerLisetTB.getSelectedRow();

				String vNum = String.valueOf(reqWorkerLisetTB.getValueAt(row, col));
				System.out.println(vNum);

				new MWorkerContModify(0).workerContAtion(vNum, id);
				dispose();

			}
		});
		reqWorkerLisetTB.setBounds(0, 0, 491, 211);
		panel.add(reqWorkerLisetTB);

		JScrollPane scrollPane = new JScrollPane(reqWorkerLisetTB);
		scrollPane.setBounds(0, 0, 493, 210);
		panel.add(scrollPane);

		workerContList();

	}

	public void workerContList() {
		try {

			dao = new ManagerWorkerDAO();
			ArrayList reqWorkerContList = dao.reqWorkerCont(reqCode);
			String[][] reqWorkerContent = dao.workerList(reqWorkerContList, contHeader);

			for (int i = 0; i < reqWorkerContent.length; i++) {
				defaultModel.addRow(reqWorkerContent[i]);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
