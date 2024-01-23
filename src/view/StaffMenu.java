package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMenu frame = new StaffMenu();
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
	public StaffMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STAFF MENU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(118, 11, 181, 72);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Register Staff");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffRegister frame = new StaffRegister();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(149, 92, 116, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMenu frame = new EditMenu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(149, 126, 116, 23);
		contentPane.add(btnNewButton_1);
				
		JButton btnNewButton_3 = new JButton("View Report");
		btnNewButton_3.setBounds(149, 160, 116, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Logout");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMenu frame = new LoginMenu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3_1.setBounds(149, 196, 116, 23);
		contentPane.add(btnNewButton_3_1);
	}

}
