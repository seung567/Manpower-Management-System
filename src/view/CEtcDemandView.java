package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ReqDAO;
import model.rec.ReqVO;

public class CEtcDemandView extends JFrame {

	private JPanel contentPane;
	private JTextField tf_etcReq;
	private JTextField tf_reqCode;

	ReqDAO dao;	
	ReqVO vo;	 

	/**
	 * Launch the application.
	 */
	public static void EtcDemand(String vNum) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CEtcDemandView frame = new CEtcDemandView(vNum);
					frame.setVisible(true);

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

	public CEtcDemandView() {
		this.EtcDemand("test");
	}

	public CEtcDemandView(int num) {

	}

	public CEtcDemandView(String vNum) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 670);
		setLocationRelativeTo(null); // 창 가운데 정렬 - [JIN]
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(253, 164, 108, -79);
		contentPane.add(horizontalBox);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(242, 170, 76));
		panel.setBounds(0, 0, 602, 631);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel req = new JLabel("기타요구사항");
		req.setForeground(new Color(16, 24, 32));
		req.setBounds(232, 77, 132, 27);
		panel.add(req);
		req.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 24));

		tf_etcReq = new JTextField();
		tf_etcReq.setForeground(new Color(16, 24, 32));
		tf_etcReq.setBounds(71, 132, 459, 397);
		panel.add(tf_etcReq);
		tf_etcReq.setColumns(10);

		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				int reqCode = Integer.parseInt(tf_reqCode.getText());	
				String etcReq = tf_etcReq.getText();

				ReqVO vo = new ReqVO(reqCode, etcReq);

				try {
					ReqDAO dao = new ReqDAO();
					dao.etcModify(vo);
					System.out.println("기타요구사항 등록완료!");

					JOptionPane.showMessageDialog(null,  "기타요구사항이 등록되었습니다!");

				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null,  "기타요구사항 등록실패 : " + e1.getMessage());
				}		      		
			}
		});


		btnNewButton.setForeground(new Color(242, 170, 76));
		btnNewButton.setBackground(new Color(16, 24, 32));
		btnNewButton.setBounds(263, 556, 75, 29);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 18));

		JLabel reqCode = new JLabel("파견요청번호");
		reqCode.setForeground(new Color(16, 24, 32));
		reqCode.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 17));
		reqCode.setBounds(71, 25, 100, 19);
		panel.add(reqCode);

		tf_reqCode = new JTextField();
		tf_reqCode.setEditable(false);
		tf_reqCode.setForeground(new Color(16, 24, 32));
		tf_reqCode.setColumns(10);
		tf_reqCode.setBounds(70, 51, 93, 29);
		panel.add(tf_reqCode);

		tf_reqCode.setText(vNum);
	}


}


