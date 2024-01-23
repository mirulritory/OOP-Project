package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Database.MyDatabase;
import model.Reward;

public class ViewRewards extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String loggedInUserName;
    private JLabel lblUserName;
    private JLabel lblPoints;
    private JLabel lblRewards;
    private JList<String> rewardsList;
    private JButton btnRedeem;
    private JButton btnMain;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewRewards frame = new ViewRewards(loggedInUserName);
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
    public ViewRewards(String loggedInUserName) {
        this.loggedInUserName = loggedInUserName;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblUserName = new JLabel("User: " + loggedInUserName);
        lblUserName.setBounds(10, 10, 150, 20);
        contentPane.add(lblUserName);

        lblPoints = new JLabel("Points: " + fetchPoints(loggedInUserName));
        lblPoints.setBounds(10, 40, 150, 20);
        contentPane.add(lblPoints);

        lblRewards = new JLabel("Available Rewards:");
        lblRewards.setBounds(10, 70, 200, 20);
        contentPane.add(lblRewards);

        rewardsList = new JList<>();
        rewardsList.setBounds(10, 100, 200, 90);
        contentPane.add(rewardsList);

        btnRedeem = new JButton("Redeem Selected Reward");
        btnRedeem.setBounds(10, 230, 200, 20);
        btnRedeem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redeemSelectedReward();
            }
        });
        contentPane.add(btnRedeem);

        btnMain = new JButton("Main Menu");
        btnMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu frame = new MainMenu(loggedInUserName);
                frame.setVisible(true);
                dispose();
            }
        });
        btnMain.setBounds(266, 230, 112, 21);
        contentPane.add(btnMain);

        displayRewards(fetchPoints(loggedInUserName));
    }

    private int fetchPoints(String username) {
        int points = 0;
        String query = "SELECT points FROM user WHERE name = ?";
        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    points = resultSet.getInt("points");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }

    private void displayRewards(int userPoints) {
        List<Reward> rewardsListData = fetchRewards(userPoints);
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Reward reward : rewardsListData) {
            model.addElement(reward.getRewardName() + " (" + reward.getPointN() + " points)");
        }

        rewardsList.setModel(model);
    }

    private List<Reward> fetchRewards(int userPoints) {
        List<Reward> rewardsList = new ArrayList<>();
        String query = "SELECT * FROM rewards WHERE pointN <= ?";
        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, userPoints);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Reward reward = new Reward();
                    reward.setRewardName(resultSet.getString("rewardName"));
                    reward.setPointN(resultSet.getInt("pointN"));

                    rewardsList.add(reward);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rewardsList;
    }

    private void redeemSelectedReward() {
        int selectedIndex = rewardsList.getSelectedIndex();

        if (selectedIndex != -1) {
            String selectedReward = rewardsList.getSelectedValue();
            String rewardName = selectedReward.split("\\(")[0].trim();

            // Fetch the points associated with the selected reward
            int rewardPoints = fetchRewardPoints(rewardName);

            if (rewardPoints > 0) {
                int userPoints = fetchPoints(loggedInUserName);

                if (userPoints >= rewardPoints) {
                    // Deduct points from the user
                    int updatedPoints = deductPointsFromUser(loggedInUserName, rewardPoints);

                    // Display the updated points
                    lblPoints.setText("Points: " + updatedPoints);

                    // Implement additional logic here if needed, e.g., update a redemption table, etc.

                    JOptionPane.showMessageDialog(contentPane, "Reward Redeemed Successfully: " + rewardName);

                    // Refresh the rewards list after redemption
                    displayRewards(updatedPoints);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Insufficient points to redeem this reward.");
                }
            } else {
                JOptionPane.showMessageDialog(contentPane, "Failed to fetch reward points.");
            }
        } else {
            JOptionPane.showMessageDialog(contentPane, "Please select a reward to redeem.");
        }
    }

    private int fetchRewardPoints(String rewardName) {
        int points = 0;
        String query = "SELECT pointN FROM rewards WHERE rewardName = ?";
        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, rewardName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    points = resultSet.getInt("pointN");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }

    private int deductPointsFromUser(String username, int pointsToDeduct) {
        String updateQuery = "UPDATE user SET points = points - ? WHERE name = ?";
        int updatedPoints = 0;

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {

            preparedStatement.setInt(1, pointsToDeduct);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            // Fetch the updated points after deduction
            updatedPoints = fetchPoints(username);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedPoints;
    }
}
