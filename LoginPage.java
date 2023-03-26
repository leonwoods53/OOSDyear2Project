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
import javax.swing.SwingUtilities;

public class LoginPage {

   	   private static JLabel userLabel;
	   private static JTextField userText;
	   private static JLabel passwordLabel;
	   private static JPasswordField passwordText;
	   private static JButton loginButton;
	

	   public static void main(String []args) {
		   
		   SwingUtilities.invokeLater(() -> {
	            JFrame frame = createLoginFrame();
	            frame.setVisible(true);
	        });
	    }

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

	      loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String username = userText.getText();
	                String password = new String(passwordText.getPassword());

	                try {
	                    if (authenticateUser(username, password)) {
	                        JOptionPane.showMessageDialog(frame, "Login successful!");
	                        frame.dispose();
	                        SwingUtilities.invokeLater(() -> {
	                            JFrame mainMenu = new MainMenu();
	                            mainMenu.setVisible(true);
	                            
	                           
	                        });
	                        // Perform any post-login actions here
	                    } else {
	                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
	                    }
	                } catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
	                }
	            }	
				
	        });
	      
	      frame.add(panel);
	      frame.setVisible(true);
	      return frame;
  	   	 
	   }
	   

	    private static boolean authenticateUser(String username, String password) throws SQLException {
	        String url = "jdbc:mysql://localhost/oosdProject";
	        String user = "root";
	        String pass = "";

	        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
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
