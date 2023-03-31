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



public class AddCustomer extends JFrame {
	
	//declaring gui components, JLabel,JTextFields,JButtons etc
	private static JLabel firstNameLabel;
	private static JTextField firstName;
	private static JLabel lastNameLabel;
	private static JTextField lastName;
	private static JLabel phoneLabel;
	private static JTextField phone;
	private static JLabel emailLabel;
	private static JTextField email;
	private static JLabel streetLabel;
	private static JTextField street;
	private static JLabel cityLabel;
	private static JTextField city;
	private static JLabel countyLabel;
	private static JTextField county;
	private static JLabel eircodeLabel;
	private static JTextField eircode;
	private static JButton submit;
	
	
    
	//an instance of the AddCustomer class to be called when the add customer button in Customers class is clicked
	public AddCustomer() {
		
		
		//Setting the JFrame and panel dimensions 
		JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setSize(500,500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.add(panel);
	    panel.setLayout(null);
	    
	    
	    //Set first name label and text field
	    firstNameLabel = new JLabel("First Name: ");
	    firstNameLabel.setBounds(10,20,100,25);
	    panel.add(firstNameLabel);
	    
	    firstName = new JTextField();
	    firstName.setBounds(180,20,165,25);
	    panel.add(firstName);
	    
	    //Set last name label and text field
	    lastNameLabel = new JLabel("Last Name: ");
	    lastNameLabel.setBounds(10,50,100,25);
	    panel.add(lastNameLabel);
	    
	    lastName = new JTextField();
	    lastName.setBounds(180,50,165,25);
	    panel.add(lastName);
	    
	    //Set phone label and text field
	    phoneLabel = new JLabel("Phone Number: ");
	    phoneLabel.setBounds(10,80,100,25);
	    panel.add(phoneLabel);
	    
	    phone = new JTextField();
	    phone.setBounds(180,80,165,25);
	    panel.add(phone);
	    
	    //Set email label and text field
	    emailLabel = new JLabel("Email: ");
	    emailLabel.setBounds(10,110,100,25);
	    panel.add(emailLabel);
	    
	    email = new JTextField();
	    email.setBounds(180,110,165,25);
	    panel.add(email);
	    
	    //Set street label and text field
	    streetLabel = new JLabel("Street: ");
	    streetLabel.setBounds(10,140,100,25);
	    panel.add(streetLabel);
	    
	    street = new JTextField();
	    street.setBounds(180,140,165,25);
	    panel.add(street);
	    
	    //Set city label and text field
	    cityLabel = new JLabel("City: ");
	    cityLabel.setBounds(10,170,100,25);
	    panel.add(cityLabel);
	    
	    city = new JTextField();
	    city.setBounds(180,170,165,25);
	    panel.add(city);
	    
	    //Set county label and text field
	    countyLabel = new JLabel("County: ");
	    countyLabel.setBounds(10,200,100,25);
	    panel.add(countyLabel);
	    
	    county = new JTextField();
	    county.setBounds(180,200,165,25);
	    panel.add(county);
	    
	    //Set eircode label and text field
	    eircodeLabel = new JLabel("Eircode: ");
	    eircodeLabel.setBounds(10,230,100,25);
	    panel.add(eircodeLabel);
	    
	    eircode = new JTextField();
	    eircode.setBounds(180,230,165,25);
	    panel.add(eircode);
	    
	    //submit and go back buttons
	    submit = new JButton("Submit");
	    submit.setBounds(180,290,80,25);
	    panel.add(submit);
	    
	    //Action Listener that calls the action performed method when the submit button is clicked
	    submit.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		//Stores user data from JTextField
	    		String CustomerFirstName = firstName.getText();
	    		String CustomerLastName = lastName.getText();
	    		int CustomerPhone = Integer.parseInt(phone.getText());
	    		String CustomerEmail = email.getText();
	    		String CustomerStreet = street.getText();
	    		String City = city.getText();
	    		String County = county.getText();
	    		String Eircode = eircode.getText();
	    		
	    		//Trying to call the insertUser method and passing in values declared above, with a catch block to catch and sql exception that may occur
	    		//The messageDialog is shown if this is succesful
	    		try {
	    			insertUser(CustomerFirstName,CustomerLastName,CustomerPhone,CustomerEmail,CustomerStreet,City,County,Eircode);
	    			JOptionPane.showMessageDialog(frame, "User Succesfully Added");
	    		} catch (SQLException ex) {
	    			JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
	    		}
	    	}
	    });
	    	frame.add(panel);
	    	frame.setVisible(true);
	    }
	    //Method to Insert a new User into the SQL database
	    private static void insertUser(String CustomerFirstName,String CustomerLastName, int CustomerPhone, String CustomerEmail, String CustomerStreet, String City, String County, String Eircode) throws SQLException {
	        
	        //trying to connect to the database with values declared above
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
	        	
	        	//SQL command to insert new values into the Customer Table 	
	        	String sql = "INSERT INTO Customer (CustomerFirstName, CustomerLastName, CustomerPhone, CustomerEmail, CustomerStreet, City, County, Eircode) VALUES (?,?,?,?,?,?,?,?)";
	        	//Prepared Statement to prevent an SQL Injection attack, and to allow the use of the same statement for different values
	        	try (PreparedStatement pstat = connection.prepareStatement(sql)) {
	        		pstat.setString(1, CustomerFirstName);
	    			pstat.setString(2, CustomerLastName);
	    			pstat.setInt(3, CustomerPhone);
	    			pstat.setString(4, CustomerEmail);
	    			pstat.setString(5, CustomerStreet);
	    			pstat.setString(6, City);
	    			pstat.setString(7, County);
	    			pstat.setString(8, Eircode);
	    			pstat.executeUpdate();
	        	}
	        }
	    }

}