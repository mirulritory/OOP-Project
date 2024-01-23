package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.MyDatabase;

public class ViewPurchase extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewPurchase frame = new ViewPurchase();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewPurchase() throws ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 345);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Retrieve the latest orderID
        int latestOrderID = fetchLatestOrderID();

        // Now, you can use this latestOrderID to fetch and display the order details
        String[] orderDetails = retrieveOrderDetails(latestOrderID);

        // Display order details using JLabels
        JLabel lblName = new JLabel("Name: " + orderDetails[0]);
        lblName.setBounds(10, 10, 200, 20);
        contentPane.add(lblName);

        JLabel lblTotalPayment = new JLabel("Total Payment: RM" + orderDetails[1] +"0");
        lblTotalPayment.setBounds(10, 40, 200, 20);
        contentPane.add(lblTotalPayment);

        JLabel lblPoints = new JLabel("Points Obtained: " + orderDetails[2]);
        lblPoints.setBounds(10, 70, 200, 20);
        contentPane.add(lblPoints);

        JLabel lblPaymentMethod = new JLabel("Payment Method:");
        lblPaymentMethod.setBounds(10, 200, 150, 20);
        contentPane.add(lblPaymentMethod);

        JButton btnCash = new JButton("Cash");
        btnCash.setBounds(170, 200, 80, 20);
        btnCash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Payment Method: Cash");
            }
        });
        contentPane.add(btnCash);

        JButton btnOnlinePayment = new JButton("Online Payment");
        btnOnlinePayment.setBounds(260, 200, 150, 20);
        btnOnlinePayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Payment Method: Online Payment");
            }
        });
        contentPane.add(btnOnlinePayment);
    }

    private int fetchLatestOrderID() throws ClassNotFoundException {
        int latestOrderID = 0;

        try (Connection conn = MyDatabase.doConnection()) {
            String sql = "SELECT MAX(orderID) FROM orderlist";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    latestOrderID = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latestOrderID;
    }

    private String[] retrieveOrderDetails(int orderID) throws ClassNotFoundException {
        String[] orderDetails = new String[3];

        try (Connection conn = MyDatabase.doConnection()) {
            String sql = "SELECT name, totalPayment, points FROM orderlist WHERE orderID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    orderDetails[0] = resultSet.getString("name");
                    orderDetails[1] = String.valueOf(resultSet.getDouble("totalPayment"));
                    orderDetails[2] = String.valueOf(resultSet.getInt("points"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }
}
