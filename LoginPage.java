package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPage extends JFrame {

	   //declaration of gui components 
   	   private static JLabel userLabel;
	   private static JTextField userText;
	   private static JLabel passwordLabel;
	   private static JPasswordField passwordText;
	   private static JButton loginButton;
	
	   //main driver for the program
	   public static void main(String []args) {
		   
		   //declares a new JFrame and makes it visible when the main driver is ran
		   JFrame frame = createLoginFrame();
           frame.setVisible(true);
	    }

	   	//Jframe method that creates the dimensions and values of the gui components
	    private static JFrame createLoginFrame() {
	        
	    	  JFrame frame = new JFrame("Login");
	    	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  frame.setSize(300, 200);

		      JPanel panel = new JPanel();
		      frame.setSize(350,200);
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      frame.setVisible(true);
		      frame.add(panel);
		      
		      panel.setLayout(null);
	
		      userLabel = new JLabel("Username");
		      userLabel.setBounds(10, 20, 80, 25);
		      panel.add(userLabel);
	
		      userText = new JTextField();
		      userText.setBounds(100, 20, 165, 25);
		      panel.add(userText);
	
		      passwordLabel = new JLabel("Password");
		      passwordLabel.setBounds(10, 50, 80, 25);
		      panel.add(passwordLabel);
	
		      passwordText = new JPasswordField();
		      passwordText.setBounds(100,50,165,25);
		      panel.add(passwordText);
	
		      loginButton = new JButton("Login");
		      loginButton.setBounds(10,80,80,25);
		      panel.add(loginButton);
	
		      //action listener for the login button that calls the action performed method
		      loginButton.addActionListener(new ActionListener() {
		            @Override
		            
		            //Stores user inputted data from the gui as local variables
		            public void actionPerformed(ActionEvent e) {
		                String username = userText.getText();
		                String password = new String(passwordText.getPassword());
		                
		                //try block to call the authenticateUser method, and displays a success message if it works
		                try {
		                    if (authenticateUser(username, password)) {
		                        JOptionPane.showMessageDialog(frame, "Login successful!");
		                        frame.dispose();
		                        JFrame mainMenu = new MainMenu();
		                        mainMenu.setVisible(true);	
		                        
		                    } else {
		                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
		                    }
		                } catch (SQLException ex) {
		                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
		                }
		            }	
					
		        });
		      
		     
		      return frame;
	  	   	 
		   }
	   
	    //boolean method that checks if username and password inputted match data in the sql database
	    private static boolean authenticateUser(String username, String password) throws SQLException {
	       

	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oosdProject", "root", "")) {
	            String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
	            try (PreparedStatement pstat = connection.prepareStatement(sql)) {
	                pstat.setString(1, username);
	                pstat.setString(2, password);

	                try (ResultSet resultSet = pstat.executeQuery()) {
	                    return resultSet.next();
	                }
	            }
	        }
	    }
}
