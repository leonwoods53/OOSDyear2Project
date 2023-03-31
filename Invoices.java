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

public class Invoices extends JFrame {

	
    private JTable invoiceTable;
    private DefaultTableModel invoiceData;
    
    //instance of invoices that is invoked when invoices button in main menu is clicked
    public Invoices() {
    	//setting dimensions of the JFrame
        setTitle("Invoices");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        invoiceData = getInvoiceData();
        invoiceTable = new JTable(invoiceData);
        JScrollPane scrollPane = new JScrollPane(invoiceTable);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton goBackButton = new JButton("Main Menu");

        
        ////action listener that displays the add invoice page when the button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInvoice addInvoicePage = new AddInvoice();
                addInvoicePage.setVisible(true);
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = invoiceTable.getSelectedRow();
                if (selectedRow >= 0) //checks if a row has been selected
                {
                	//message to confirm if user wants to delete an invoice, displayed if a row is selected
                    int result = JOptionPane.showConfirmDialog(Invoices.this, "Are you sure you want to delete this invoice?", "Delete Invoice", JOptionPane.YES_NO_OPTION);
                    
                    if (result == JOptionPane.YES_OPTION) {
                    	//retrieves the invoice ID from the selected row (from invoiceData), which is cast to an int, because getValueAt returns an Object Value
                        int invoiceId = (int) invoiceData.getValueAt(selectedRow, 0); 
                        //now the try block calls the deleteInfoice method, and removes all the data from the selected row
                        try {
                            deleteInvoice(invoiceId);
                            invoiceData.removeRow(selectedRow);
                            //the catch block displays the error message if there is an SQL exception
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Invoices.this, "Error deleting invoice.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else { //message to display if a row has not been selected by the user
                    JOptionPane.showMessageDialog(Invoices.this, "Select an invoice to delete.", "Error", JOptionPane.ERROR_MESSAGE);
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
        invoiceData.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
	        if (e.getType() == TableModelEvent.UPDATE) {
	            int row = e.getFirstRow();
	            int column = e.getColumn();
	            Object value = invoiceData.getValueAt(row, column);
	            int invoiceId = (int) invoiceData.getValueAt(row, 0);
	            
	            String columnName;
	            switch (column) {
	                case 1:
	                    columnName = "ProductID";
	                    break;
	                case 2:
	                    columnName = "Price";
	                    break;
	                case 3:
	                	columnName = "CustomerID";
	                	break;
	                case 4:
	                    columnName = "Quantity";
	                    break;
	                
	                default:
	                    return;
	            }
	            
	            //sql statement that updates the database based on user input into a selected columnName from the JTable
	            String sql = "UPDATE Invoice SET " + columnName + "=? WHERE InvoiceID=?";
	
	            //try to connect to the database
	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
	                PreparedStatement pstat = conn.prepareStatement(sql);
	
	                pstat.setObject(1, value);
	                pstat.setInt(2, invoiceId);
	
	                pstat.executeUpdate();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(Invoices.this, "Error updating invoice.", "Error",JOptionPane.ERROR_MESSAGE);
	
	                }		
	
	        }
	      }
	    });
	}


    //defaultTableModel method to display info from the database in the JTable
    private DefaultTableModel getInvoiceData() {
        String[] columnNames = {"Invoice ID", "Price", "Product ID", "Customer ID", "Quantity"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
            String sql = "SELECT * FROM Invoice";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet resultSet = pstat.executeQuery();

            while (resultSet.next()) {
            	int invoiceId = resultSet.getInt("InvoiceID");
            	int productId = resultSet.getInt("ProductID");
                int price = resultSet.getInt("Price");
                int customerId = resultSet.getInt("CustomerID");
                int quantity = resultSet.getInt("Quantity");

                Object[] rowData = {invoiceId, price, productId, customerId, quantity };
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
        
        
    }
    //method to delete an invoice from the table that is invoked by the action listener on the delete button
    private void deleteInvoice(int invoiceId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
    		String sql = "DELETE FROM Invoice WHERE InvoiceID = ?";
    		PreparedStatement pstat = conn.prepareStatement(sql);
    		pstat.setInt(1, invoiceId);
    		pstat.executeUpdate();
    	} 	
    }
    
}
