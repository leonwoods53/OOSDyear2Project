package project;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InsertCustomer extends JFrame implements ActionListener{
	
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
	private static JLabel passwordLabel;
	private static JPasswordField password;
	private static JButton submit;
	private static JButton goBack;
	
    
	
	public static void main(String [] args) {
		
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
	    panel.add(cityLabel);
	    
	    county = new JTextField();
	    county.setBounds(180,200,165,25);
	    panel.add(county);
	    
	    //Set eircode label and text field
	    eircodeLabel = new JLabel("Eircode: ");
	    eircodeLabel.setBounds(10,230,100,25);
	    panel.add(cityLabel);
	    
	    eircode = new JTextField();
	    eircode.setBounds(180,230,165,25);
	    panel.add(eircode);
	    
	    //Set password label and text field
	    passwordLabel = new JLabel("Password: ");
	    passwordLabel.setBounds(10,260,100,25);
	    panel.add(cityLabel);
	    
	    password = new JPasswordField();
	    password.setBounds(180,260,165,25);
	    panel.add(password);
	    
	    //submit and go back buttons
	    submit = new JButton("Submit");
	    submit.setBounds(180,290,80,25);
	    panel.add(submit);
	    
	    goBack = new JButton("Go Back");
	    goBack.setBounds(10,290,165,25);
	    panel.add(goBack);
	    
	    
		
		// database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/oosdProject";
		Connection connection = null ;
		PreparedStatement pstat = null;
		String CustomerFirstName = firstName.getText();
		String CustomerLastName = lastName.getText();
		String CustomerPhone = phone.getText();
		String CustomerEmail = email.getText();
		String CustomerStreet = street.getText();
		String City = city.getText();
		String County = county.getText();
		String Eircode = eircode.getText();
		String Password = password.getText();
		int i=0;
		try {
		// insert data into table
		
			// establish connection to database
			connection = DriverManager.getConnection(DATABASE_URL, "root", "" );
			// create Prepared Statement for inserting data into table
			pstat = connection.prepareStatement("INSERT INTO Customer (CustomerFirstName, CustomerLastName, CustomerPhone, CustomerEmail, CustomerStreet, City, County, Eircode, Password) VALUES (?,?,?,?,?,?,?,?,?)");
			pstat . setString (1, CustomerFirstName);
			pstat . setString (2, CustomerLastName);
			pstat . setString (3, CustomerPhone);
			pstat . setString (4, CustomerEmail);
			pstat . setString (5, CustomerStreet);
			pstat . setString (6, City);
			pstat . setString (7, County);
			pstat . setString (8, Eircode);
			pstat . setString (9, Password);
			
			
			i = pstat .executeUpdate();
			System.out. println ( i + " record successfully added to the table .");
		}
		catch(SQLException sqlException){
			sqlException . printStackTrace () ;
		}
		finally {
			try {
				pstat . close () ;
				connection. close () ;
			}
			catch (Exception exception){
				exception . printStackTrace () ;
			}
		}
	}
}
	
		

	 // end main
	 // end class