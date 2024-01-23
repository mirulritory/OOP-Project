package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String loggedInUserName;

    // Constructor with username parameter
    public MainMenu(String username) {
        this.loggedInUserName = username;
        initialize();  // Call a method to initialize GUI components
    }

    // Method to initialize GUI components
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 333, 359);
        contentPane = new JPanel();
        contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnMenu = new JButton("MENU");
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // You can now use loggedInUserName in this class
                System.out.println("Logged in as: " + loggedInUserName);
            }
        });
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewMenu frame = new ViewMenu(loggedInUserName);
                frame.setVisible(true);
                dispose();
            }
        });
        btnMenu.setBounds(75, 83, 137, 36);
        contentPane.add(btnMenu);

        JButton btnReward = new JButton("REWARD");
        btnReward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewRewards frame = new ViewRewards(loggedInUserName);
                frame.setVisible(true);
                dispose();
            }
        });
        btnReward.setBounds(75, 143, 137, 36);
        contentPane.add(btnReward);

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginMenu frame = new LoginMenu();
                frame.setVisible(true);
                dispose();
            }
        });
        btnLogout.setBounds(75, 203, 137, 36);
        contentPane.add(btnLogout);
    }
}
