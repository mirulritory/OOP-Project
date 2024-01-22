package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.User;

public class LoginMenu extends JFrame {

    // Serial version UID for serialization
    private static final long serialVersionUID = 1L;

    // Components declaration
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    // Main method for testing the class
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

    // Constructor for creating the frame
    public LoginMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 569, 402);

        // Content pane initialization
        contentPane = new JPanel();
        contentPane.setForeground(new Color(0, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // JLabel for displaying the welcome message
        JLabel lblloginPage = new JLabel("Welcome to Coffee Shop");
        lblloginPage.setHorizontalAlignment(SwingConstants.CENTER);
        lblloginPage.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblloginPage.setBounds(92, 29, 370, 59);
        lblloginPage.setPreferredSize(new Dimension(370, 59));  // Set preferred size
        contentPane.add(lblloginPage);

        // JLabels for Username and Password labels
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

        // JTextField for entering the username
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBounds(219, 128, 203, 32);
        contentPane.add(textField);
        textField.setColumns(10);

        // JPasswordField for entering the password
        passwordField = new JPasswordField();
        passwordField.setBounds(219, 181, 203, 32);
        contentPane.add(passwordField);

        // JButton for Sign Up
        JButton btnNewSignUp = new JButton("Sign up");
        btnNewSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewSignUp.setBackground(new Color(50, 205, 50)); // Green color
        btnNewSignUp.setForeground(Color.WHITE);
        btnNewSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register frame = new Register();
                frame.setVisible(true);
                dispose();
            }
        });
        btnNewSignUp.setBounds(219, 305, 203, 40);
        contentPane.add(btnNewSignUp);

        // JButton for Sign In
        JButton btnSignIn = new JButton("Sign in");
        btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSignIn.setBackground(new Color(0, 191, 255)); // Deep Sky Blue color
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the username and password from the text fields
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                // Create a UserController instance for handling user-related operations
                UserController userController = new UserController();
                try {
                    // Check if the login is successful
                    if (userController.loginUser(username, password)) {
                        JOptionPane.showMessageDialog(contentPane, "Login successful!");

                        // Create a User instance for the logged-in user
                        User loggedInUser = new User();
                        loggedInUser.setName(username);

                        // Get the account type of the logged-in user
                        String accountType = userController.getAccountType(loggedInUser);
                        System.out.println(userController.getAccountType(loggedInUser));

                        // Open different frames based on the account type
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
                        // Display an error message if login fails
                        JOptionPane.showMessageDialog(contentPane, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // Display an error message for any exceptions that occur
                    JOptionPane.showMessageDialog(contentPane, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSignIn.setBounds(219, 227, 203, 40);
        contentPane.add(btnSignIn);

        // JLabel for the "Don't have an account?" message
        JLabel lblDontHaveAn = new JLabel("Don't have an account ? ");
        lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDontHaveAn.setBounds(219, 272, 203, 36);
        contentPane.add(lblDontHaveAn);

        // JLabel for background (Note: Background image is not provided in the code)
//        JLabel lblBackground = new JLabel("New label");
//        lblBackground.setForeground(new Color(0, 0, 0));
//        lblBackground.setVerticalAlignment(SwingConstants.TOP);
//        lblBackground.setBounds(0, 0, 555, 365);
//        contentPane.add(lblBackground);
    }
}
