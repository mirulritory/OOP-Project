package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.User;
import javax.swing.SwingConstants;

public class SearchUser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private UserController userController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchUser frame = new SearchUser();
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
    public SearchUser() {
        userController = new UserController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(50, 32, 84, 19);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(131, 33, 149, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Search");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(290, 32, 89, 23);
        contentPane.add(btnNewButton);

        table = new JTable();
        table.setBounds(10, 94, 416, 44);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 94, 416, 102);
        contentPane.add(scrollPane);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StaffMenu frame = new StaffMenu();
				frame.setVisible(true);
				dispose();
        	}
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.setBounds(181, 216, 89, 23);
        contentPane.add(btnBack);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                try {
                    User user = userController.searchUser(username);
                    if (user != null) {
                        // Display user details in the table
                        displayUserDetails(user);
                    } else {
                        // Handle the case where the user is not found
                        clearTable();
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // Handle the exception (e.g., display an error message)
                }
            }
        });
    }

    // Method to display user details in the table
    private void displayUserDetails(User user) {
        clearTable();
        // Your implementation to add user details to the table model
        // You can set the values in the table model or any other suitable way
        String[] columnNames = {"Name", "Email", "Phone Number", "Points"};
        Object[][] data = {{user.getName(), user.getEmail(), user.getPhonenum(), user.getPoints()}};

        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    // Method to clear the table
    private void clearTable() {
        // Your implementation to clear the table
        // For example, set an empty model to the table
        table.setModel(new javax.swing.table.DefaultTableModel());
    }
}


