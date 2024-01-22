package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MyDatabase;
import model.User;

public class UserController {

	public int insertUser(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO user (name, password, email, phonenum, accountType) VALUES (?,?,?,?,?)";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhonenum());
            preparedStatement.setString(5, user.getaccountType() != null ? user.getaccountType() : "Customer");

            return preparedStatement.executeUpdate();
        }
    }
    
    public boolean loginUser(String name, String password) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a matching user is found
            }
        }
    }

    public String getAccountType(User user) throws ClassNotFoundException {
        String sql = "SELECT accountType FROM user WHERE name = ?";
        
        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("accountType");
                } else {
                    return null;  // User not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int insertStaff(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO user (name, password, email, phonenum, accountType) VALUES (?,?,?,?,?)";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhonenum());
            preparedStatement.setString(5, user.getaccountType() != null ? user.getaccountType() : "Staff");

            return preparedStatement.executeUpdate();
        }
    }

	
    
}
