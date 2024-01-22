package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.MyDatabase;
import model.Menu;

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
                int latestTimesOrder = getLatestTimesOrder(connection);
                String insertQuery = "INSERT INTO orderitem (drinkID, quantity, drinkPrice, timesOrder) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    for (Map.Entry<Integer, Integer> entry : currentOrder.entrySet()) {
                        int drinkID = entry.getKey();
                        int quantity = entry.getValue();
                        Menu menu = getMenuItem(drinkID);
                        double itemPrice = menu.getDrinkPrice();
                        
                        insertStatement.setInt(1, drinkID);
                        insertStatement.setInt(2, quantity);
                        insertStatement.setDouble(3, itemPrice);
                        insertStatement.setInt(4, latestTimesOrder + 1);

                        insertStatement.executeUpdate();

                        try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int generatedOrderID = generatedKeys.getInt(1);
                                // Handle the generated OrderID as needed
                            }
                        }
                    }
                }
                currentOrder.clear();
            } catch (SQLException e) {
                e.printStackTrace();
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
    
}
