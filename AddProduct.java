package project;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
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



public class AddProduct extends JFrame {
	
	private static JLabel priceLabel;
	private static JTextField price;
	private static JLabel stockLabel;
	private static JTextField stock;
	private static JLabel descriptionLabel;
	private static JTextField description;
	private static JLabel conditionLabel;
	private static JTextField condition;
	private static JButton submit;
	
    
	
	public AddProduct() {
		
		
		
		JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setSize(500,500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.add(panel);
	    panel.setLayout(null);
	    
	    
	    //Sets price label and text field
	    priceLabel = new JLabel("Price: ");
	    priceLabel.setBounds(10,20,100,25);
	    panel.add(priceLabel);
	    
	    price = new JTextField();
	    price.setBounds(180,20,165,25);
	    panel.add(price);
	    
	    //Sets quantity label and text field
	    stockLabel = new JLabel("Quantity in Stock: ");
	    stockLabel.setBounds(10,50,100,25);
	    panel.add(stockLabel);
	    
	    stock = new JTextField();
	    stock.setBounds(180,50,165,25);
	    panel.add(stock);
	    
	    //Sets description label and text field
	    descriptionLabel = new JLabel("Product Description: ");
	    descriptionLabel.setBounds(10,80,100,25);
	    panel.add(descriptionLabel);
	    
	    description = new JTextField();
	    description.setBounds(180,80,165,25);
	    panel.add(description);
	    
	    //Sets Condition label and text field
	    conditionLabel = new JLabel("Product Condition: ");
	    conditionLabel.setBounds(10,110,100,25);
	    panel.add(conditionLabel);
	    
	    condition = new JTextField();
	    condition.setBounds(180,110,165,25);
	    panel.add(condition);
	    
	  
	    
	    //submit and go back buttons
	    submit = new JButton("Submit");
	    submit.setBounds(180,290,80,25);
	    panel.add(submit);
	    
	    
	    submit.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		String Price = price.getText();
	    		String StockQuantity = stock.getText();
	    		String Description = description.getText();
	    		String ProductCondition = condition.getText();
	    		
	    		try {
	    			insertProduct(Price, StockQuantity, Description, ProductCondition);
	    			JOptionPane.showMessageDialog(frame, "Product Succesfully Added");
	    		} catch (SQLException ex) {
	    			JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
	    		}
	    	}
	    });
	    	frame.add(panel);
	    	frame.setVisible(true);
	    }
	    
	    private static void insertProduct(String Price, String StockQuantity, String Description, String ProductCondition) throws SQLException {
	    	String url = "jdbc:mysql://localhost/oosdProject";
	        String user = "root";
	        String pass = "";
	        
	        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
	        	
	        	String sql = "INSERT INTO Product (Price, StockQuantity, Description, ProductCondition) VALUES (?,?,?,?)";
	        	try (PreparedStatement pstat = connection.prepareStatement(sql)) {
	        		pstat.setString(1, Price);
	    			pstat.setString(2, StockQuantity);
	    			pstat.setString(3, Description);
	    			pstat.setString(4, ProductCondition);
	    			pstat.executeUpdate();
	        	}
	        }
	    }

}