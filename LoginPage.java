package projectyear2oosd;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{

   private static JLabel userLabel;
   private static JTextField userText;
   private static JLabel passwordLabel;
   private static JPasswordField passwordText;
   private static JButton loginButton;
   private static JButton signupButton;
   private static JLabel success;

   public static void main(String []args) {

      JPanel panel = new JPanel();
      JFrame frame = new JFrame();
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
      loginButton.addActionListener(new LoginPage());
      panel.add(loginButton);

      signupButton = new JButton("Sign Up");
      signupButton.setBounds(100,80,80,25);
      panel.add(signupButton);

      success = new JLabel("");
      success.setBounds(10,110,300,25);
      panel.add(success);
      

      frame.setVisible(true);

      
   
 
   }
   


   @Override
   public void actionPerformed(java.awt.event.ActionEvent arg0) {
      // TODO Auto-generated method stub
      String user = userText.getText();
      String password = passwordText.getText();
      System.out.println(user + ", " + password);

      if(user.equals("Leon") && password.equals("hello")) {
         success.setText("Login Successful");
      }
      
   }
}