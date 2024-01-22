package view;

import controller.MenuController;
import model.Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class MenuView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JTextField quantityField;
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;
    private JLabel totalPriceLabel;
    private MenuController menuController;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuView frame = new MenuView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuView() throws ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 634, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Use BorderLayout
        contentPane.setLayout(new BorderLayout());

        JLabel lblNewLabel = new JLabel("COFFEE & TEA");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        // Fetch menu items from MenuController
        menuController = new MenuController();
        List<Menu> menuItems = menuController.getMenuItems("jdbc:mysql://localhost/myoop");

        // Create an array to hold menu item names
        String[] menuItemNames = new String[menuItems.size()];
        for (int i = 0; i < menuItems.size(); i++) {
            menuItemNames[i] = menuItems.get(i).getDrinkID() + " - " +
                    menuItems.get(i).getDrinkName() + " - " +
                    menuItems.get(i).getDrinkPrice();
        }

        // Create a JList with menu item names
        listModel = new DefaultListModel<>();
        for (String itemName : menuItemNames) {
            listModel.addElement(itemName);
        }
        list = new JList<>(listModel);

        // Create a JScrollPane to hold the JList and add it to the CENTER position
        JScrollPane scrollPane = new JScrollPane(list);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for order section and add it to the SOUTH position
        JPanel panelBelowList = new JPanel();
        panelBelowList.setPreferredSize(new Dimension(300, 270));
        contentPane.add(panelBelowList, BorderLayout.SOUTH);
        panelBelowList.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Your Order");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblNewLabel_1.setBounds(10, 10, 166, 32);
        panelBelowList.add(lblNewLabel_1);

        // Create a text field for quantity input
        quantityField = new JTextField();
        quantityField.setBounds(10, 52, 86, 20);
        panelBelowList.add(quantityField);
        quantityField.setColumns(10);

        // Create a button to add items to the order
        JButton addToOrderButton = new JButton("Add to Order");
        addToOrderButton.setBounds(106, 51, 120, 23);
        panelBelowList.add(addToOrderButton);

        // Create labels to display item details
        itemNameLabel = new JLabel("Item Name: ");
        itemNameLabel.setBounds(10, 100, 200, 20);
        panelBelowList.add(itemNameLabel);

        itemPriceLabel = new JLabel("Item Price: ");
        itemPriceLabel.setBounds(10, 130, 200, 20);
        panelBelowList.add(itemPriceLabel);

        totalPriceLabel = new JLabel("Total Price: ");
        totalPriceLabel.setBounds(10, 160, 200, 20);
        panelBelowList.add(totalPriceLabel);

        // Create a button to place the order
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(10, 200, 150, 23);
        panelBelowList.add(placeOrderButton);

        // Action listener for the "Add to Order" button
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder();
            }
        });

        // Action listener for the "Place Order" button
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					placeOrder();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        // List selection listener
        list.addListSelectionListener(e -> {
            updateItemDetails();
        });

        // Update the UI on the EDT
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    private void addToOrder() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = listModel.get(selectedIndex);
            String[] parts = selectedItem.split(" - ");
            int drinkID = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(quantityField.getText());

            // Add the selected item and quantity to the order
            menuController.addToOrder(drinkID, quantity);

            // Update the list of ordered items without replacing the entire menu
           
        }
    }


    

    private void updateItemDetails() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = listModel.get(selectedIndex);
            String[] parts = selectedItem.split(" - ");
            int drinkID = Integer.parseInt(parts[0]);

            // Fetch the details of the selected item from the controller
            Menu selectedMenu = menuController.getMenuItem(drinkID);

            // Display the details in the labels
            itemNameLabel.setText("Item Name: " + selectedMenu.getDrinkName());
            itemPriceLabel.setText("Item Price: " + selectedMenu.getDrinkPrice());

            // Validate and parse quantity from the quantityField
            String quantityText = quantityField.getText().trim();
            int quantity = quantityText.isEmpty() ? 0 : Integer.parseInt(quantityText);

            // Assume totalPrice is quantity * item price
            double totalPrice = quantity * selectedMenu.getDrinkPrice();
            totalPriceLabel.setText("Total Price: " + totalPrice);
        }
    }


    
 // Inside the placeOrder() method
    private void placeOrder() throws ClassNotFoundException {
        // Call the controller method to place the order
        menuController.placeOrder();

        // Update the list of ordered items
       

        // Reset the UI components
        list.clearSelection();
        quantityField.setText("");
        itemNameLabel.setText("Item Name: ");
        itemPriceLabel.setText("Item Price: ");
        totalPriceLabel.setText("Total Price: ");
    }


    
    
}
