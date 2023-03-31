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

    public Products() {
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

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProduct addProductPage = new AddProduct();
                addProductPage.setVisible(true);
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int result = JOptionPane.showConfirmDialog(Products.this, "Are you sure you want to delete this product?", "Delete product", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int customerId = (int) productData.getValueAt(selectedRow, 0);
                        try {
                            deleteProduct(customerId);
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
        
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
        
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goBackButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        productData.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
    	        if (e.getType() == TableModelEvent.UPDATE) {
    	            int row = e.getFirstRow();
    	            int column = e.getColumn();
    	            Object value = productData.getValueAt(row, column);
    	            int productId = (int) productData.getValueAt(row, 0);
    	            
    	            String columnName;
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
    	
    	            String updateQuery = "UPDATE Product SET " + columnName + "=? WHERE ProductID=?";
    	
    	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
    	                PreparedStatement pstat = conn.prepareStatement(updateQuery);
    	
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
    

    private DefaultTableModel getProductData() {
        String[] columnNames = {"Product ID", "Price", "Stock Quantity", "Description", "Product Condition"
        	};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
            String query = "SELECT * FROM Product";
            PreparedStatement pstat = conn.prepareStatement(query);
            ResultSet resultSet = pstat.executeQuery();

            while (resultSet.next()) {
            	int productId = resultSet.getInt("ProductID");
                int price = resultSet.getInt("Price");
                int quantity = resultSet.getInt("StockQuantity");
                String description = resultSet.getString("Description");
                String condition = resultSet.getString("ProductCondition");
               
               

                Object[] rowData = {productId, price, quantity, description, condition};
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
    
    private void deleteProduct(int productId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
    		String deleteQuery = "DELETE FROM Product WHERE ProductID = ?";
    		PreparedStatement pstat = conn.prepareStatement(deleteQuery);
    		pstat.setInt(1, productId);
    		pstat.executeUpdate();
    	} 	
    }
    
}