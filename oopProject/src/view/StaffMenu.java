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
		lblNewLabel.setBounds(118, 11, 181, 55);
		contentPane.add(lblNewLabel);
		
		JButton btnRegister = new JButton("Register Staff");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffRegister frame = new StaffRegister();
				frame.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(149, 61, 116, 23);
		contentPane.add(btnRegister);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMenu frame = new EditMenu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnMenu.setBounds(149, 91, 116, 23);
		contentPane.add(btnMenu);
				
		JButton btnView = new JButton("View Report");
		btnView.setBounds(149, 151, 116, 23);
		contentPane.add(btnView);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMenu frame = new LoginMenu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(149, 181, 116, 23);
		contentPane.add(btnLogout);
		
		JButton btnSearch = new JButton("Search User");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUser frame = new SearchUser();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSearch.setBounds(149, 121, 116, 23);
		contentPane.add(btnSearch);
	}

}
