package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;

public class UserView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
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
	public UserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 402);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblloginPage = new JLabel("Welcome to Coffee Shop");
		lblloginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblloginPage.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblloginPage.setBounds(92, 29, 370, 59);
		contentPane.add(lblloginPage);
		
		JLabel lbluserName = new JLabel("Username");
		lbluserName.setHorizontalAlignment(SwingConstants.CENTER);
		lbluserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbluserName.setBounds(92, 125, 102, 36);
		contentPane.add(lbluserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(92, 169, 102, 36);
		contentPane.add(lblPassword);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setBounds(219, 128, 203, 32);
		contentPane.add(name);
		name.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tests frame = new tests();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(256, 305, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignIn.setBounds(256, 227, 89, 23);
		contentPane.add(btnSignIn);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account ? ");
		lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDontHaveAn.setBounds(219, 272, 183, 36);
		contentPane.add(lblDontHaveAn);
		
		JPasswordField password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.setBounds(219, 166, 203, 32);
		contentPane.add(password);
		
		JLabel lblBackground_1 = new JLabel("New label");
		lblBackground_1.setVerticalAlignment(SwingConstants.TOP);
		lblBackground_1.setForeground(Color.BLACK);
		lblBackground_1.setBounds(0, 0, 555, 365);
		contentPane.add(lblBackground_1);
	}
}
