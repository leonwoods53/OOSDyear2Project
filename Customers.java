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
    
    //instance of customers that is invoked when customer button in main menu is clicked
    public Customers() {
    	//setting dimensions of the JFrame
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

        
        ////action listener that displays the add customer page when the button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomer addCustomerPage = new AddCustomer();
                addCustomerPage.setVisible(true);
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = customerTable.getSelectedRow();
                if (selectedRow >= 0) //checks if a row has been selected
                {
                	//message to confirm if user wants to delete a customer, displayed if a row is selected
                    int result = JOptionPane.showConfirmDialog(Customers.this, "Are you sure you want to delete this customer?", "Delete Customer", JOptionPane.YES_NO_OPTION);
                    
                    if (result == JOptionPane.YES_OPTION) {
                    	//retrieves the customer ID from the selected row (from customerData), which is cast to an int, because getValueAt returns an Object Value
                        int customerId = (int) customerData.getValueAt(selectedRow, 0); 
                        //now the try block calls the deleteCustomer method, and removes all the data from the selected row
                        try {
                            deleteCustomer(customerId);
                            customerData.removeRow(selectedRow);
                            //the catch block displays the error message if there is an SQL exception
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Customers.this, "Error deleting customer.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else { //message to display if a row has not been selected by the user
                    JOptionPane.showMessageDialog(Customers.this, "Select a customer to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        //action listener that returns the user to the main menu when the goback button is clicked
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
        
        //adds a button panels position at the bottom of the display
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goBackButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
       //Action Listener for when a user edits the JTable Data
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
	                default:
	                    return;
	            }
	            
	            //sql statement that updates the database based on user input into a selected columnName from the JTable
	            String sql = "UPDATE Customer SET " + columnName + "=? WHERE CustomerID=?";
	
	            //try to connect to the database
	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
	                PreparedStatement pstat = conn.prepareStatement(sql);
	
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


    //defaultTableModel method to display info from the database in the JTable
    private DefaultTableModel getCustomerData() {
        String[] columnNames = {"Customer ID", "First Name", "Last Name", "Phone Number",
                "Email", "Street", "City", "County", "Eircode"
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
                int phone = resultSet.getInt("CustomerPhone");
                String email = resultSet.getString("CustomerEmail");
                String street = resultSet.getString("CustomerStreet");
                String city = resultSet.getString("City");
                String county = resultSet.getString("County");
                String eircode = resultSet.getString("Eircode");

                Object[] rowData = {customerId, firstName, lastName, phone, email, street, city, county, eircode};
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
        
        
    }
    //method to delete a customer from the table that is invoked by the action listener on the delete button
    private void deleteCustomer(int customerId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
    		String sql = "DELETE FROM Customer WHERE CustomerID = ?";
    		PreparedStatement pstat = conn.prepareStatement(sql);
    		pstat.setInt(1, customerId);
    		pstat.executeUpdate();
    	} 	
    }
    
}

