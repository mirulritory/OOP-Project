package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.MyDatabase;
import model.Menu;
import model.OrderItem;

public class MenuController {
	
    public int updateMenu(Menu menu) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE beveragesmenu SET drinkName=?, drinkPrice=? WHERE drinkID=?";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, menu.getDrinkName());
            preparedStatement.setDouble(2, menu.getDrinkPrice());
            preparedStatement.setInt(3, menu.getDrinkID());

            return preparedStatement.executeUpdate();
        }
    }

    public List<Menu> fetchAllMenus() throws ClassNotFoundException, SQLException {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM beveragesmenu";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setDrinkID(resultSet.getInt("drinkID"));
                menu.setDrinkName(resultSet.getString("drinkName"));
                menu.setDrinkPrice(resultSet.getDouble("drinkPrice"));
                // Set other attributes if needed

                menuList.add(menu);
            }
        }

        return menuList;
    }
    public int deleteMenu(int drinkID) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM beveragesmenu WHERE drinkID=?";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, drinkID);
            return preparedStatement.executeUpdate();
        }
    }
    public int addMenu(Menu menu) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO beveragesmenu (drinkName, drinkPrice) VALUES (?, ?)";

        try (Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, menu.getDrinkName());
            preparedStatement.setDouble(2, menu.getDrinkPrice());

            return preparedStatement.executeUpdate();
        }
    }
    
    public int addOrder(String username, List<OrderItem> orderItems, double totalPayment) throws ClassNotFoundException, SQLException {
        int rowsAffected = 0;

        try (Connection conn = MyDatabase.doConnection()) {
            // Insert order information into orderlist table
            String orderSql = "INSERT INTO orderlist (name, totalPayment, points) VALUES (?, ?, ?)";
            try (PreparedStatement orderStatement = conn.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                orderStatement.setString(1, username);
                orderStatement.setDouble(2, totalPayment);

                // Cast and round down the totalPayment to the nearest integer for points
                int points = (int) Math.floor(totalPayment);
                orderStatement.setInt(3, points);

                // Execute the insert into orderlist
                rowsAffected = orderStatement.executeUpdate();

                // Retrieve the generated keys (including the orderID)
                ResultSet rs = orderStatement.getGeneratedKeys();
                int orderID = 0;
                if (rs.next()) {
                    orderID = rs.getInt(1);

                    // Now, insert each item into the orderitem table
                    for (OrderItem item : orderItems) {
                        String itemSql = "INSERT INTO orderitem (orderID, drinkName, quantity, drinkPrice) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement itemStatement = conn.prepareStatement(itemSql)) {
                            itemStatement.setInt(1, orderID);
                            itemStatement.setString(2, item.getDrinkName());
                            itemStatement.setInt(3, item.getQuantity());
                            itemStatement.setDouble(4, item.getDrinkPrice());

                            rowsAffected += itemStatement.executeUpdate();
                        }
                    }

                    // Update user points in the user table
                    String userUpdateSql = "UPDATE user SET points = points + ? WHERE name = ?";
                    try (PreparedStatement userUpdateStatement = conn.prepareStatement(userUpdateSql)) {
                        userUpdateStatement.setInt(1, points);
                        userUpdateStatement.setString(2, username);

                        rowsAffected += userUpdateStatement.executeUpdate();
                    }
                }
            }
        }

        return rowsAffected;
    }

    
   
}
