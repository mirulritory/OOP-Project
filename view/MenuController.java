package controller;
import model.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Database.MyDatabase;

public class MenuController {

    private List<Menu> menuItems;
    private Map<Integer, Integer> currentOrder;

    public MenuController() throws ClassNotFoundException {
        // Fetch menu items from the database or initialize it as needed
        this.menuItems = getMenuItems("jdbc:mysql://localhost/myoop");

        // Initialize the current order map
        this.currentOrder = new HashMap<>();
    }

    public List<Menu> getMenuItems(String jdbcUrl) throws ClassNotFoundException {
        List<Menu> menuItems = new ArrayList<>();
        try (Connection connection = MyDatabase.doConnection()) {
            String query = "SELECT drinkID, drinkName, drinkPrice FROM beveragesmenu";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Menu menu = new Menu();
                    menu.setDrinkID(resultSet.getInt("drinkID"));
                    menu.setDrinkName(resultSet.getString("drinkName"));
                    menu.setDrinkPrice(resultSet.getDouble("drinkPrice"));
                    menuItems.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception as needed
        }

        return menuItems;
    }

    public void addToOrder(int drinkID, int quantity) {
        // Check if the item is already in the order
        if (currentOrder.containsKey(drinkID)) {
            // If yes, update the quantity
            int existingQuantity = currentOrder.get(drinkID);
            currentOrder.put(drinkID, existingQuantity + quantity);
        } else {
            // If no, add a new item to the order
            currentOrder.put(drinkID, quantity);
        }
    }

    public Map<Integer, Integer> getCurrentOrder() {
        return new HashMap<>(currentOrder);
    }

    public Menu getMenuItem(int drinkID) {
        // Find and return the menu item with the given drinkID
        for (Menu menu : menuItems) {
            if (menu.getDrinkID() == drinkID) {
                return menu;
            }
        }
        return null; // Return null if the item is not found (handle appropriately in MenuView)
    }

    public void placeOrder() throws ClassNotFoundException {
        if (!currentOrder.isEmpty()) {
        	
            try (Connection connection = MyDatabase.doConnection()) {
                // Fetch the latest timesOrder from the orderitem table
                int latestTimesOrder = getLatestTimesOrder(connection);

                // Insert order details into the database (excluding OrderID)
                String insertQuery = "INSERT INTO orderitem (drinkID, quantity, drinkPrice, timesOrder) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    for (Map.Entry<Integer, Integer> entry : currentOrder.entrySet()) {
                        int drinkID = entry.getKey();
                        int quantity = entry.getValue();

                        // Get the item price from the Menu object
                        Menu menu = getMenuItem(drinkID);
                        double itemPrice = menu.getDrinkPrice();

                        insertStatement.setInt(1, drinkID);
                        insertStatement.setInt(2, quantity);
                        insertStatement.setDouble(3, itemPrice);
                        insertStatement.setInt(4, latestTimesOrder + 1);
                       // Increment timesOrder

                        insertStatement.executeUpdate();

                        // Retrieve the auto-generated OrderID if needed
                        try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int generatedOrderID = generatedKeys.getInt(1);
                                // Handle the generated OrderID as needed
                            }
                        }
                    }
                }

                // Clear the current order after successful insertion
                currentOrder.clear();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle or log the exception as needed
            }
        }
    }


    private int getLatestTimesOrder(Connection connection) throws SQLException {
        int latestTimesOrder = 0;
        String query = "SELECT MAX(timesOrder) FROM orderitem";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                latestTimesOrder = resultSet.getInt(1);
            }
        }
        return latestTimesOrder;
    }
}
