package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
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
	public LoginMenu() {
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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(219, 128, 203, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 181, 203, 32);
		contentPane.add(passwordField);
		
		JButton btnNewSignUp = new JButton("Sign up");
		btnNewSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register frame = new Register();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewSignUp.setBounds(256, 305, 89, 23);
		contentPane.add(btnNewSignUp);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();  // Use getText() instead of getName()
		        String password = new String(passwordField.getPassword());

		        UserController userController = new UserController();
		        try {
		            if (userController.loginUser(username, password)) {
		                // Successful login, you can open the main application window or perform other actions
		                // For now, let's just display a message
		                JOptionPane.showMessageDialog(contentPane, "Login successful!");
		                User loggedInUser = new User();
	                    loggedInUser.setName(username);

	                    // Check the account type
	                    String accountType = userController.getAccountType(loggedInUser);
	                    System.out.println(userController.getAccountType(loggedInUser));

	                    if ("Customer".equals(accountType)) {
	                        Register frame = new Register();
	                        frame.setVisible(true);
	                        dispose();

	                    } else {
	                        StaffMenu frame = new StaffMenu();
	                        frame.setVisible(true);
	                        dispose();
	                    }
		            } else {
		                // Failed login
		                JOptionPane.showMessageDialog(contentPane, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		            // Display exception message
		            JOptionPane.showMessageDialog(contentPane, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnSignIn.setBounds(256, 227, 89, 23);
		contentPane.add(btnSignIn);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account ? ");
		lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDontHaveAn.setBounds(219, 272, 183, 36);
		contentPane.add(lblDontHaveAn);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setForeground(new Color(0, 0, 0));
		lblBackground.setVerticalAlignment(SwingConstants.TOP);
		lblBackground.setBounds(0, 0, 555, 365);
		contentPane.add(lblBackground);
		
	}
}
