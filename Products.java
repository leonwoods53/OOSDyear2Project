package project;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Products extends JFrame {

    private JTable productTable;
    private DefaultTableModel productData;

    //instance of products invoked when product button is clickedin main menu
    public Products() {
    	//setting dimensions of JFrame
        setTitle("Products");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productData = getProductData();
        productTable = new JTable(productData);
        JScrollPane scrollPane = new JScrollPane(productTable);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton goBackButton = new JButton("Main Menu");

        //actions listener that loads addProduct page when the Add button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProduct addProductPage = new AddProduct();
                addProductPage.setVisible(true);
            }
        });
      //checks if a row is selected from the table and calls the deleteProduct method
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) //checks if row is selected
                {
                	//asks user to confirm if they want to delete a product
                    int result = JOptionPane.showConfirmDialog(Products.this, "Are you sure you want to delete this product?", "Delete product", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                    	//if yes, retrieves product ID from the selected row
                        int productId = (int) productData.getValueAt(selectedRow, 0);
                        //calls deleteProduct method, and removes the row from the JTable
                        try {
                            deleteProduct(productId);
                            productData.removeRow(selectedRow);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Products.this, "Error deleting product.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(Products.this, "Select a product to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //returns user to main menu when Main Menu button is clicked
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
        
        
        //adds button panel to JFrame and positions it at the bottom of the display
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goBackButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        //table model listener to listen for changes in the JTable
        productData.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
            	//checks if table model event type is an update
    	        if (e.getType() == TableModelEvent.UPDATE) {
    	        	//retrieves the affected row and column
    	            int row = e.getFirstRow();
    	            int column = e.getColumn();
    	            //retrives the new value at affected row/column
    	            Object value = productData.getValueAt(row, column);
    	            //retrieves product ID of affected row (which is at index 0 in the table)
    	            int productId = (int) productData.getValueAt(row, 0);
    	            
    	            String columnName;
    	            //switch block to map columnName to the chosen column index
    	            switch (column) {
    	                case 1:
    	                    columnName = "Price";
    	                    break;
    	                case 2:
    	                    columnName = "StockQuantity";
    	                    break;
    	                case 3:
    	                    columnName = "Description";
    	                    break;
    	                case 4:
    	                    columnName = "ProductCondition";
    	                    break;
    	                default:
    	                    return;
    	            }
    	            
    	            String sql = "UPDATE Product SET " + columnName + "=? WHERE ProductID=?";
    	
    	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
    	                PreparedStatement pstat = conn.prepareStatement(sql);
    	
    	                pstat.setObject(1, value);
    	                pstat.setInt(2, productId);
    	
    	                pstat.executeUpdate();
    	            } catch (SQLException ex) {
    	                ex.printStackTrace();
    	                JOptionPane.showMessageDialog(Products.this, "Error updating product.", "Error",JOptionPane.ERROR_MESSAGE);
    	
    	                }		
    	
    	        }
    	      }
    	    });
    	}
    

    //defaultTableModel method to display info from the database in the JTable
    private DefaultTableModel getProductData() {
    	//array to declare the column names in the JTable
        String[] columnNames = {"Product ID", "Price", "Stock Quantity", "Description", "Product Condition"
        	};
        
      //creating a new default table model with an initial row value of 0, and with column names declared above
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        //trying connection to db
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
            String sql = "SELECT * FROM Product";
            PreparedStatement pstat = conn.prepareStatement(sql);
            //storing the sql query result in a ResultSet object
            ResultSet resultSet = pstat.executeQuery();

            //while loop through each row 
            while (resultSet.next()) {
            	//retrieves values at each column and stores them locally
            	int productId = resultSet.getInt("ProductID");
                int price = resultSet.getInt("Price");
                int quantity = resultSet.getInt("StockQuantity");
                String description = resultSet.getString("Description");
                String condition = resultSet.getString("ProductCondition");
               
               
                //array to store coloumn values for each row
                Object[] rowData = {productId, price, quantity, description, condition};
                //adding a new row to the jtable with the updated values
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
    //delete method for deleting a product from the db, called by the action listener on the delete button
    private void deleteProduct(int productId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
    		String sql = "DELETE FROM Product WHERE ProductID = ?";
    		PreparedStatement pstat = conn.prepareStatement(sql);
    		pstat.setInt(1, productId);
    		pstat.executeUpdate();
    	} 	
    }
    
}