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
    
    public int addOrder(String username, List<OrderItem> orderItems) throws ClassNotFoundException, SQLException {
        int rowsAffected = 0;
        //int orderID = generateOrderID();

        // Iterate through the order items and add them to the orderitem table
        for (OrderItem item : orderItems)
        {
            try (Connection conn = MyDatabase.doConnection()) {
                // Move inside the loop

                String sql = "INSERT INTO orderitem (drinkName, quantity, drinkPrice, name) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                    preparedStatement.setString(1, item.getDrinkName());
                    preparedStatement.setInt(2, item.getQuantity());
                    preparedStatement.setDouble(3, item.getDrinkPrice());
                    preparedStatement.setString(4, username);
                    //preparedStatement.setInt(5, orderID);
                   // preparedStatement.setInt(5, latestTimesOrder + 1); // Increment for each iteration

                    rowsAffected += preparedStatement.executeUpdate();
                }
            }
        }

        return rowsAffected;
    }

    
   
}
