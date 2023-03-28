package project;

import javax.swing.*;
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
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton goBackButton = new JButton("Main Menu");

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProduct addProductPage = new AddProduct();
                addProductPage.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goBackButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private DefaultTableModel getProductData() {
        String[] columnNames = {"ProductID", "Price", "StockQuantity", "Description", "ProductCondition"
        	};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
            String query = "SELECT * FROM Product";
            PreparedStatement pstat = conn.prepareStatement(query);
            ResultSet rs = pstat.executeQuery();

            while (rs.next()) {
            	int productId = rs.getInt("ProductID");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("StockQuantity");
                String description = rs.getString("Description");
                String condition = rs.getString("ProductCondition");
               
               

                Object[] rowData = {productId, price, quantity, description, condition};
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}