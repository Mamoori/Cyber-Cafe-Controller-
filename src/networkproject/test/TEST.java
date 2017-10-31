package networkproject.test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;

import com.esotericsoftware.kryonet.Client;

public class TEST {

	private JFrame frame;
	private DefaultListModel<String> Model;
	private Client client;
	private JList<String> list;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEST window = new TEST();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TEST() {
		try {
			client = new Client();
			client.start();
			client.getKryo().setRegistrationRequired(false);
		} catch (Exception e) {}
		initialize();
	}

	private void initialize() {
		frame = new JFrame(); // TODO: handle exception

		frame.setBounds(100, 100, 700, 700 / 16 * 9);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("START");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		ImageIcon img = loadIcon("start.png");
		btnNewButton.setIcon(img);

		btnNewButton.addActionListener(a -> {
			String ip = Model.getElementAt(list.getSelectedIndex());
			try {
				client.connect(500, ip, 512, 512);
				client.sendUDP("Start");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 9; i++) {
					Model.addElement("element" + i);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(33, 48, 218, 53);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("STOP");
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		ImageIcon img2 = loadIcon("stop.png");
		btnNewButton_1.setIcon(img2);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = Model.getElementAt(list.getSelectedIndex());
				try {
					client.connect(500, ip, 512, 512);
					client.sendUDP("Stop");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(33, 112, 218, 52);
		frame.getContentPane().add(btnNewButton_1);

		Model = new DefaultListModel<String>();
		list = new JList<>(Model);
		list.setBounds(498, 46, 147, 224);

		frame.getContentPane().add(list);

		JButton btnNewButton_2 = new JButton("REFRESH");
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.discoverHosts(54777, 1000).forEach(host -> {
					Model.addElement(host.getHostAddress());
				});

			}
		});
		ImageIcon img3 = loadIcon("REFRESH1.png");
		btnNewButton_2.setIcon(img3);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(498, 281, 147, 52);
		frame.getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("LIST OF USERS");
		ImageIcon img4 = loadIcon("users.png");
		lblNewLabel.setIcon(img4);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(507, 11, 153, 24);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("NOTES");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(33, 268, 123, 24);
		frame.getContentPane().add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(33, 291, 231, 24);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel_2 = new JLabel("some Notes...");
		lblNewLabel_2.setBounds(43, 291, 231, 36);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton_3 = new JButton("HOLD PC");
		ImageIcon img5 = loadIcon("pause.png");
		btnNewButton_3.setIcon(img5);

		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(330, 48, 140, 36);
		frame.getContentPane().add(btnNewButton_3);

		JLabel lblCurrentTime = new JLabel("CURRENT TIME:");
		lblCurrentTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentTime.setBounds(330, 122, 110, 14);
		frame.getContentPane().add(lblCurrentTime);

		JLabel lblNewLabel_3 = new JLabel("REMAINING TIME:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(330, 143, 150, 21);
		frame.getContentPane().add(lblNewLabel_3);
	}

	public ImageIcon loadIcon(String name) {
		return new ImageIcon(this.getClass().getResource("/img/" + name));
	}

}
