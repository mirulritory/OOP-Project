package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.MenuController;
import model.Menu;


public class EditMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private MenuController menuController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditMenu frame = new EditMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws ClassNotFoundException 
     */
    public EditMenu() throws ClassNotFoundException {
        menuController = new MenuController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 11, 416, 170);
        contentPane.add(scrollPane);

        // Define the column names for the table
        String[] columnNames = {"Drink ID", "Drink Name", "Drink Price"};

        // Create a DefaultTableModel with no data and column names
        DefaultTableModel model = new DefaultTableModel(null, columnNames);

        table = new JTable(model);
        scrollPane.setViewportView(table);

        // Fetch and display data from the database
        try {
            List<Menu> menuList = menuController.fetchAllMenus();
            for (Menu menu : menuList) {
                // Add a new row to the table for each menu item
                model.addRow(new Object[]{menu.getDrinkID(), menu.getDrinkName(), menu.getDrinkPrice()});
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StaffMenu frame = new StaffMenu();
				frame.setVisible(true);
        	}
        });
        btnBack.setBounds(160, 222, 89, 23);
        contentPane.add(btnBack);

        JButton btnUpdate = new JButton("Update");
     // Add action listener for the Update button
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    for (int row = 0; row < model.getRowCount(); row++) {
                        int drinkID = (int) model.getValueAt(row, 0);
                        String drinkName = (String) model.getValueAt(row, 1);
                        
                        // Handle converting the String to Double
                        double drinkPrice;
                        try {
                            drinkPrice = Double.parseDouble(model.getValueAt(row, 2).toString());
                        } catch (NumberFormatException ex) {
                            // Handle the case where the cell contains a non-numeric value
                            drinkPrice = 0.0; // You can set a default value or handle it according to your needs
                        }

                        Menu updatedMenu = new Menu(drinkID, drinkName, drinkPrice);
                        menuController.updateMenu(updatedMenu);
                    }
                    JOptionPane.showMessageDialog(contentPane, "Update successful!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnUpdate.setBounds(160, 192, 89, 23);
        contentPane.add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to delete this item?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        int drinkID = (int) table.getValueAt(selectedRow, 0);
                        try {
							menuController.deleteMenu(drinkID);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(contentPane, "Item deleted successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please select an item to delete.");
                }
            }
        });
        btnDelete.setBounds(260, 192, 89, 23);
        contentPane.add(btnDelete);
        
        setContentPane(contentPane);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AddMenu frame = new AddMenu();
				frame.setVisible(true);
        	}
        });
        btnAdd.setBounds(60, 192, 89, 23);
        contentPane.add(btnAdd);
        setVisible(true);
    }
}
