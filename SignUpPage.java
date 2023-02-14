package projectyear2oosd;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpPage {

    private static JLabel firstNameLabel;
    private static JTextField firstNameText;
    private static JLabel surnameLabel;
    private static JTextField surnameText;
    private static JLabel emailLabel;
    private static JTextField emailText;
    private static JLabel phoneLabel;
    private static JTextField phoneText;
    private static JLabel streetLabel;
    private static JTextField streetText;
    private static JLabel cityLabel;
    private static JTextField cityText;
    private static JLabel countyLabel;
    private static JTextField countyText;
    private static JLabel eircodeLabel;
    private static JTextField eircodeText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JLabel confirmPasswordLabel;
    private static JPasswordField confirmPasswordText;
    private static JButton submitButton;
    private static JButton goBackButton;

    public static void main(String[] args) {
      JPanel panel = new JPanel();
      JFrame frame = new JFrame();
      frame.setSize(500,500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.add(panel);
      
      panel.setLayout(null);

      //Creates First Name Label and Text Field

      firstNameLabel = new JLabel("First Name: ");
      firstNameLabel.setBounds(10,20,100,25);
      panel.add(firstNameLabel);

      firstNameText = new JTextField();
      firstNameText.setBounds(180, 20, 165, 25);
      panel.add(firstNameText);

      //Creates Surname Label and Text Field

      surnameLabel = new JLabel("Surname: ");
      surnameLabel.setBounds(10,50,100,25);
      panel.add(surnameLabel);

      surnameText = new JTextField();
      surnameText.setBounds(180, 50, 165, 25);
      panel.add(surnameText);

      //Creates Email Label and Text Field

      emailLabel = new JLabel("Email: ");
      emailLabel.setBounds(10,80,100,25);
      panel.add(emailLabel);

      emailText = new JTextField();
      emailText.setBounds(180, 80, 165, 25);
      panel.add(emailText);

      //Creates Phone Number Label and Text Field

      phoneLabel = new JLabel("Phone Number: ");
      phoneLabel.setBounds(10,110,100,25);
      panel.add(phoneLabel);

      phoneText = new JTextField();
      phoneText.setBounds(180, 110, 165, 25);
      panel.add(phoneText);

      //Creates Address Line 1 Label and Text Field

      streetLabel = new JLabel("Address Street: ");
      streetLabel.setBounds(10,140,100,25);
      panel.add(streetLabel);

      streetText = new JTextField();
      streetText.setBounds(180, 140, 165, 25);
      panel.add(streetText);

      //Creates Address Line 2 Label and Text Field

      cityLabel = new JLabel("City: ");
      cityLabel.setBounds(10,170,100,25);
      panel.add(cityLabel);

      cityText = new JTextField();
      cityText.setBounds(180, 170, 165, 25);
      panel.add(cityText);

      //Creates Address Line 3 Label and Text Field

      countyLabel = new JLabel("County: ");
      countyLabel.setBounds(10,200,100,25);
      panel.add(countyLabel);

      countyText = new JTextField();
      countyText.setBounds(180, 200, 165, 25);
      panel.add(countyText);

      //Creates Address Line 4 Label and Text Field

      eircodeLabel = new JLabel("Eircode: ");
      eircodeLabel.setBounds(10,230,100,25);
      panel.add(eircodeLabel);

      eircodeText = new JTextField();
      eircodeText.setBounds(180, 230, 165, 25);
      panel.add(eircodeText);

      //Creates password label and text field

      passwordLabel = new JLabel("Password: ");
      passwordLabel.setBounds(10, 260, 100, 25);
      panel.add(passwordLabel);

      passwordText = new JPasswordField();
      passwordText.setBounds(180,260,165,25);
      panel.add(passwordText);

      //Label and text field to confirm password inputted by user in line above

      confirmPasswordLabel = new JLabel("Confirm Password: ");
      confirmPasswordLabel.setBounds(10, 290, 100, 25);
      panel.add(confirmPasswordLabel);

      confirmPasswordText = new JPasswordField();
      confirmPasswordText.setBounds(180,290,165,25);
      panel.add(confirmPasswordText);

      //Submit and go back buttons 

      submitButton = new JButton("Submit");
      submitButton.setBounds(180,330,80,25);
      submitButton.addActionListener(new LoginPage());
      panel.add(submitButton);

      goBackButton = new JButton("Go Back");
      goBackButton.setBounds(10,330,165,25);
      panel.add(goBackButton);

    }
    
}
