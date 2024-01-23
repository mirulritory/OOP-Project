package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.MenuController;
import model.Menu;
import model.OrderItem;

public class ViewMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private MenuController menuController;
    private static String loggedInUserName;
    private List<OrderItem> cartItems;  // List to hold items in the cart

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewMenu frame = new ViewMenu(loggedInUserName);
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
    public ViewMenu(String loggedInUserName) {
        this.loggedInUserName = loggedInUserName;
        menuController = new MenuController();
        cartItems = new ArrayList<>();  // Initialize the cart items list

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 180);
        contentPane.add(scrollPane);

        String[] columnNames = {"Drink ID", "Drink Name", "Drink Price"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);

        table = new JTable(model);
        scrollPane.setViewportView(table);

        try {
            List<Menu> menuList = menuController.fetchAllMenus();
            for (Menu menu : menuList) {
                model.addRow(new Object[]{menu.getDrinkID(), menu.getDrinkName(), menu.getDrinkPrice()});
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        JButton btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String drinkName = (String) table.getValueAt(selectedRow, 1);
                    double drinkPrice = (double) table.getValueAt(selectedRow, 2);

                    String quantityString = JOptionPane.showInputDialog(contentPane, "Enter Quantity:");
                    try {
                        int quantity = Integer.parseInt(quantityString);

                        if (cartItems == null) {
                            cartItems = new ArrayList<>();
                        }

                        // Use totalPayment instead of drinkPrice in OrderItem
                        cartItems.add(new OrderItem(drinkName, quantity, drinkPrice));

                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(contentPane, "Invalid quantity. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please select a drink from the menu.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAddToCart.setBounds(20, 202, 120, 23);
        contentPane.add(btnAddToCart);

        JButton btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!cartItems.isEmpty()) {
                    // Display a confirmation dialog
                    int response = JOptionPane.showConfirmDialog(contentPane,
                            "Do you want to place the order?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            // Pass the connection to the addOrder method
                           double totalPayment = calculateTotalPayment(cartItems);
                        int rowsAffected = menuController.addOrder(loggedInUserName, cartItems, totalPayment);


                            if (rowsAffected > 0) {
                                // Display an information dialog with the order summary
                                JOptionPane.showMessageDialog(contentPane, "Order placed successfully!\n" + getOrderSummary(cartItems));
                                ViewPurchase frame = new ViewPurchase();
                                frame.setVisible(true);
                                dispose();
                                // Clear the cart after placing the order
                                cartItems.clear();
                            } else {
                                JOptionPane.showMessageDialog(contentPane, "Failed to place order.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(contentPane, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // User chose to cancel the order, so clear the cart
                        cartItems.clear();
                        JOptionPane.showMessageDialog(contentPane, "Order cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Your cart is empty. Add items to the cart before placing an order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnPlaceOrder.setBounds(160, 202, 120, 23);
        contentPane.add(btnPlaceOrder);

        setContentPane(contentPane);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainMenu frame = new MainMenu(loggedInUserName);
                frame.setVisible(true);
                dispose();
        	}
        });
        btnBack.setBounds(293, 202, 120, 23);
        contentPane.add(btnBack);
        setVisible(true);
    }

    private String getOrderSummary(List<OrderItem> orderItems) {
        StringBuilder summary = new StringBuilder();
        double total = 0.0;

        for (OrderItem item : orderItems) {
            summary.append("Drink: ").append(item.getDrinkName()).append("\n")
                    .append("Quantity: ").append(item.getQuantity()).append("\n")
                    .append("Subtotal: ").append(item.getTotalPayment()).append("\n\n");

            total += item.getTotalPayment();
        }

        summary.append("Total: ").append(total);
        return summary.toString();
    }

    private double calculateTotalPayment(List<OrderItem> orderItems) {
        double total = 0.0;

        for (OrderItem item : orderItems) {
            total += item.getTotalPayment();
        }

        return total;
    }
}