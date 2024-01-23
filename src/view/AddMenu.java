package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MenuController;
import model.Menu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMenu frame = new AddMenu();
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
	public AddMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddMenu = new JLabel("ADD MENU");
		lblAddMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddMenu.setBounds(123, 26, 181, 55);
		contentPane.add(lblAddMenu);
		
		JLabel lblDrinkName = new JLabel("Drink Name");
		lblDrinkName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrinkName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrinkName.setBounds(111, 92, 105, 20);
		contentPane.add(lblDrinkName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(226, 92, 96, 20);
		contentPane.add(textField);
		
		JLabel lblDrinkPrice = new JLabel("Drink Price");
		lblDrinkPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrinkPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrinkPrice.setBounds(111, 123, 105, 20);
		contentPane.add(lblDrinkPrice);
		
		JButton btnAddItem = new JButton("Add Item");
        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Menu newItem = new Menu();
                    newItem.setDrinkName(textField.getText());
                    newItem.setDrinkPrice(Double.parseDouble(textField_1.getText()));

                    MenuController menuController = new MenuController();
					int success = menuController.addMenu(newItem);
                    if (success > 0) {
                        // Display success message
                        JOptionPane.showMessageDialog(contentPane, "Item registered successfully!");

                        // Open the login window
                        EditMenu editMenuFrame = new EditMenu();
                        editMenuFrame.setVisible(true);
                        setVisible(false);  // Use setVisible(false) instead of dispose()
                    } else {
                        // Display failure message
                        JOptionPane.showMessageDialog(contentPane, "Item registration failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Invalid input for Drink Price.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException | SQLException ex) {
                    // Handle exceptions more gracefully (e.g., show an error dialog)
                    ex.printStackTrace();
                }
            }
        });
		btnAddItem.setBounds(123, 182, 89, 23);
		contentPane.add(btnAddItem);
		
		JButton btnBack = new JButton("Back");
		 btnBack.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		EditMenu frame = new EditMenu();
                    frame.setVisible(true);
                    dispose();

	        	}
	        });
		btnBack.setBounds(228, 182, 89, 23);
		contentPane.add(btnBack);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(226, 123, 96, 20);
		contentPane.add(textField_1);
	}
}
