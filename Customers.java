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
        JButton editButton = new JButton("Edit");
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
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goBackButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private DefaultTableModel getCustomerData() {
        String[] columnNames = {"CustomerID", "CustomerFirstName", "CustomerLastName", "CustomerPhone",
                "CustomerEmail", "CustomerStreet", "City", "County", "Eircode", "Password"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
            String query = "SELECT * FROM Customer";
            PreparedStatement pstat = conn.prepareStatement(query);
            ResultSet rs = pstat.executeQuery();

            while (rs.next()) {
            	int customerId = rs.getInt("CustomerID");
                String firstName = rs.getString("CustomerFirstName");
                String lastName = rs.getString("CustomerLastName");
                String phone = rs.getString("CustomerPhone");
                String email = rs.getString("CustomerEmail");
                String street = rs.getString("CustomerStreet");
                String city = rs.getString("City");
                String county = rs.getString("County");
                String eircode = rs.getString("Eircode");
                String password = rs.getString("Password");

                Object[] rowData = {customerId, firstName, lastName, phone, email, street, city, county, eircode, password};
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}

