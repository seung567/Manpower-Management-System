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

import model.managerWorkerDAO;

public class SameContCode extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel defaultModel;

	private String[] contHeader = { "계약번호", "이름", "계약시작일", "계약만료일", "계약일", "계약상태" };
	private String[][] contents = null;

	private String workerCode = null;
	private String idText = null;
	// private ActionListener viewText = null;

	private managerWorkerDAO dao = null;

	/**
	 * Launch the application.
	 */
	public static void sameContCodeAction(String code, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SameContCode frame = new SameContCode(code, id);
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

		this.sameContCodeAction("test", "test");

	}

	public SameContCode(int num) {

	}

	public SameContCode(String codeValue, String id) {

		this.workerCode = codeValue;
		this.idText = id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 294);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(242, 170, 76));
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]

		JLabel lblNewLabel = new JLabel("계약건 선택");
		lblNewLabel.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		lblNewLabel.setBounds(220, 10, 76, 15);
		getContentPane().add(lblNewLabel);

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
		
		
		table = new JTable(defaultModel);
		
		table.getColumn("계약번호").setPreferredWidth(3);
		table.getColumn("이름").setPreferredWidth(3);
		table.getColumn("계약시작일").setPreferredWidth(35);
		table.getColumn("계약만료일").setPreferredWidth(35);
		table.getColumn("계약일").setPreferredWidth(35);
		table.getColumn("계약상태").setPreferredWidth(3);
		
		table.getColumn("계약번호").setCellRenderer(center);
		table.getColumn("이름").setCellRenderer(center);
		table.getColumn("계약시작일").setCellRenderer(center);
		table.getColumn("계약만료일").setCellRenderer(center);
		table.getColumn("계약일").setCellRenderer(center);
		table.getColumn("계약상태").setCellRenderer(center);
		
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		table.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		table.setBackground(new Color(255, 255, 255));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int col = 0;
				int row = table.getSelectedRow();

				String vNum = String.valueOf(table.getValueAt(row, col));
				System.out.println(vNum);

				new WorkerContModify(0).workerContAtion(vNum, id);
				dispose();

			}
		});
		table.setBounds(0, 0, 491, 211);
		panel.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 493, 210);
		panel.add(scrollPane);

		JButton btnNewButton = new JButton("신규등록");

		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(16, 24, 32));
		btnNewButton.setBounds(408, 6, 97, 23);
		getContentPane().add(btnNewButton);

		workerContList();

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					dao = new managerWorkerDAO();
					Date endDate = dao.workerEdateOut(workerCode);

					if (endDate != null) {

						Date date = new Date();

						SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

						String dateStr = format.format(date);
						Date now = format.parse(dateStr);

						if (endDate.after(now)) {
							JOptionPane.showMessageDialog(null, "현재 계약중인 파견인력 입니다.");
						} else {
							new WorkerContInsertView(0).workerContAction(workerCode, id);
							dispose();
						}
					} else {
						new WorkerContInsertView(0).workerContAction(workerCode, id);
						dispose();
					}

				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}

			}
		});

	}

	public void workerContList() {
		try {

			dao = new managerWorkerDAO();
			ArrayList contList = dao.workerContInfo(workerCode);
			String[][] contContent = dao.workerList(contList, contHeader);

			System.out.println(contContent[0][0]);

			for (int i = 0; i < contContent.length; i++) {
				defaultModel.addRow(contContent[i]);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
