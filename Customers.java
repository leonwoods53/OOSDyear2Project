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

public class Customers extends JFrame {

    private JTable customerTable;
    private DefaultTableModel customerData;
    

    public Customers() {
        setTitle("Customer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        customerData = getCustomerData();
        customerTable = new JTable(customerData);
        JScrollPane scrollPane = new JScrollPane(customerTable);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton goBackButton = new JButton("Main Menu");

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomer addCustomerPage = new AddCustomer();
                addCustomerPage.setVisible(true);
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
        
        
       
        customerData.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
	        if (e.getType() == TableModelEvent.UPDATE) {
	            int row = e.getFirstRow();
	            int column = e.getColumn();
	            Object value = customerData.getValueAt(row, column);
	            int customerId = (int) customerData.getValueAt(row, 0);
	            
	            String columnName;
	            switch (column) {
	                case 1:
	                    columnName = "CustomerFirstName";
	                    break;
	                case 2:
	                    columnName = "CustomerLastName";
	                    break;
	                case 3:
	                    columnName = "CustomerPhone";
	                    break;
	                case 4:
	                    columnName = "CustomerEmail";
	                    break;
	                case 5:
	                    columnName = "CustomerStreet";
	                    break;
	                case 6:
	                    columnName = "City";
	                    break;
	                case 7:
	                    columnName = "County";
	                    break;
	                case 8:
	                    columnName = "Eircode";
	                    break;
	                case 9:
	                    columnName = "Password";
	                    break;
	                default:
	                    return;
	            }
	
	            String updateQuery = "UPDATE Customer SET " + columnName + "=? WHERE CustomerID=?";
	
	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
	                PreparedStatement pstat = conn.prepareStatement(updateQuery);
	
	                pstat.setObject(1, value);
	                pstat.setInt(2, customerId);
	
	                pstat.executeUpdate();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(Customers.this, "Error updating customer.", "Error",JOptionPane.ERROR_MESSAGE);
	
	                }		
	
	        }
	      }
	    });
	}



    private DefaultTableModel getCustomerData() {
        String[] columnNames = {"CustomerID", "CustomerFirstName", "CustomerLastName", "CustomerPhone",
                "CustomerEmail", "CustomerStreet", "City", "County", "Eircode", "Password"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
            String query = "SELECT * FROM Customer";
            PreparedStatement pstat = conn.prepareStatement(query);
            ResultSet resultSet = pstat.executeQuery();

            while (resultSet.next()) {
            	int customerId = resultSet.getInt("CustomerID");
                String firstName = resultSet.getString("CustomerFirstName");
                String lastName = resultSet.getString("CustomerLastName");
                String phone = resultSet.getString("CustomerPhone");
                String email = resultSet.getString("CustomerEmail");
                String street = resultSet.getString("CustomerStreet");
                String city = resultSet.getString("City");
                String county = resultSet.getString("County");
                String eircode = resultSet.getString("Eircode");
                String password = resultSet.getString("Password");

                Object[] rowData = {customerId, firstName, lastName, phone, email, street, city, county, eircode, password};
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
        
        
    }
    
}

