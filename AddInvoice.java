package project;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class AddInvoice extends JFrame {
	
	//declaring gui components, JLabel,JTextFields,JButtons etc
	private static JLabel productIdLabel;
	private static JTextField productId;
	private static JLabel customerIdLabel;
	private static JTextField customerId;
	private static JLabel quantityLabel;
	private static JTextField quantity;
	private static JButton submit;
	
	
	
    
	//an instance of the AddCustomer class to be called when the add customer button in Customers class is clicked
	public AddInvoice() {
		
		
		//Setting the JFrame and panel dimensions 
		JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setSize(500,500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.add(panel);
	    panel.setLayout(null);
	    
	    
	    //Set first name label and text field
	    productIdLabel = new JLabel("Product ID: ");
	    productIdLabel.setBounds(10,20,100,25);
	    panel.add(productIdLabel);
	    
	    productId = new JTextField();
	    productId.setBounds(180,20,165,25);
	    panel.add(productId);
	    
	    
	    //Set phone label and text field
	    customerIdLabel = new JLabel("Customer ID: ");
	    customerIdLabel.setBounds(10,50,100,25);
	    panel.add(customerIdLabel);
	    
	    customerId = new JTextField();
	    customerId.setBounds(180,50,165,25);
	    panel.add(customerId);
	    
	    //Set email label and text field
	    quantityLabel = new JLabel("Quantity: ");
	    quantityLabel.setBounds(10,80,100,25);
	    panel.add(quantityLabel);
	    
	    quantity = new JTextField();
	    quantity.setBounds(180,80,165,25);
	    panel.add(quantity);
	    
	    
	    //submit and go back buttons
	    submit = new JButton("Submit");
	    submit.setBounds(180,110,80,25);
	    panel.add(submit);
	    
	    //Action Listener that calls the action performed method when the submit button is clicked
	    submit.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		//Stores user data from JTextField
	    		int ProductID = Integer.parseInt(productId.getText());
	    		int CustomerID = Integer.parseInt(customerId.getText());
	    		int Quantity = Integer.parseInt(quantity.getText());
	    		
	    		
	    		//Trying to call the insertUser method and passing in values declared above, with a catch block to catch and sql exception that may occur
	    		//The messageDialog is shown if this is succesful
	    		try {
	    			int price = getProductPrice(ProductID);
	    			double totalPrice = price * Quantity;
	    			insertInvoice(ProductID, totalPrice, CustomerID, Quantity);
	    			JOptionPane.showMessageDialog(frame, "Invoice Succesfully Added");
	    			
	    			
	    		} catch (SQLException ex) {
	    			JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
	    		} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
	    	}
	    });
	    	frame.add(panel);
	    	frame.setVisible(true);
	    }
	
		public static int getProductPrice(int productId) throws Exception {
			try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
				String sql = "SELECT Price FROM Product WHERE ProductID=?";
				PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setInt(1, productId);
		        ResultSet resultSet = statement.executeQuery();
		        
		        if (resultSet.next()) {
		            return resultSet.getInt("Price");
		        } else {
		            throw new Exception("Product not found");
		        }
			}
		}
	    //Method to Insert a new User into the SQL database
	    private static void insertInvoice(int ProductID, double totalPrice, int CustomerID, int Quantity) throws SQLException {
	        
	        //trying to connect to the database with values declared above
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
	        	
	        	//SQL command to insert new values into the Customer Table 	
	        	String sql = "INSERT INTO Invoice (ProductID, Price, CustomerID, Quantity) VALUES (?,?,?,?)";
	        	//Prepared Statement to prevent an SQL Injection attack, and to allow the use of the same statement for different values
	        	try (PreparedStatement pstat = connection.prepareStatement(sql)) {
	        		pstat.setInt(1, ProductID);
	    			pstat.setDouble(2, totalPrice);
	    			pstat.setInt(3, CustomerID);
	    			pstat.setInt(4, Quantity);
	    			pstat.executeUpdate();
	        	}
	        }
	    }

}