package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class StaffRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffRegister frame = new StaffRegister();
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
	public StaffRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterStaff = new JLabel("REGISTER STAFF");
		lblRegisterStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterStaff.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRegisterStaff.setBounds(129, 11, 181, 55);
		contentPane.add(lblRegisterStaff);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(214, 66, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(214, 128, 96, 20);
		contentPane.add(textField_2);
		
		JLabel phoneNo = new JLabel("phone number");
		phoneNo.setHorizontalAlignment(SwingConstants.LEFT);
		phoneNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneNo.setBounds(99, 158, 105, 20);
		contentPane.add(phoneNo);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(214, 158, 96, 20);
		contentPane.add(textField_3);
		
		JLabel email = new JLabel("email address");
		email.setHorizontalAlignment(SwingConstants.LEFT);
		email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		email.setBounds(99, 128, 105, 20);
		contentPane.add(email);
		
		JLabel password = new JLabel("password");
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		password.setBounds(99, 97, 105, 20);
		contentPane.add(password);
		
		JLabel username = new JLabel("username");
		username.setHorizontalAlignment(SwingConstants.LEFT);
		username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		username.setBounds(99, 66, 105, 20);
		contentPane.add(username);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Create a user object
		        User newUser = new User();
		        
		        // Set user properties from the text fields
		        newUser.setName(textField.getText());
		        newUser.setPassword(new String(passwordField.getPassword()));
		        newUser.setEmail(textField_2.getText());
		        newUser.setPhonenum(Integer.parseInt(textField_3.getText()));

		        // Call insertUser method from UserController
		        UserController userController = new UserController();
		        try {
		            int success = userController.insertStaff(newUser);
		            if (success > 0) {
	                    // Display success message
	                    JOptionPane.showMessageDialog(contentPane, "Account registered successfully!");
	                    dispose();

	                    // Open the login window
	                    LoginMenu loginFrame = new LoginMenu();
	                    loginFrame.setVisible(true);
	                } else {
	                    // Display failure message
	                    JOptionPane.showMessageDialog(contentPane, "Account registration failed.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		btnNewButton.setBounds(109, 198, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMenu frame = new StaffMenu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(214, 198, 89, 23);
		contentPane.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(214, 97, 96, 20);
		contentPane.add(passwordField);
	}

}
