package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.MyDatabase;
import model.user;

public class UserController {

    public int insertUser(user user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO user (name, password, email, phonenum) VALUES (?,?,?,?)";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhonenum());

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
}
