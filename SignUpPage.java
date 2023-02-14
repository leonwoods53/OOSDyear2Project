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
    private static JLabel addressLine1Label;
    private static JTextField addressLine1Text;
    private static JLabel addressLine2Label;
    private static JTextField addressLine2Text;
    private static JLabel addressLine3Label;
    private static JTextField addressLine3Text;
    private static JLabel addressLine4Label;
    private static JTextField addressLine4Text;

    public static void main(String[] args) {
      JPanel panel = new JPanel();
      JFrame frame = new JFrame();
      frame.setSize(500,500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.add(panel);
      
      panel.setLayout(null);

      //Creates First Name Label and Text Field

      firstNameLabel = new JLabel("First Name:");
      firstNameLabel.setBounds(10,20,100,25);
      panel.add(firstNameLabel);

      firstNameText = new JTextField();
      firstNameText.setBounds(180, 20, 165, 25);
      panel.add(firstNameText);

      //Creates Surname Label and Text Field

      surnameLabel = new JLabel("Surname:");
      surnameLabel.setBounds(10,50,100,25);
      panel.add(surnameLabel);

      surnameText = new JTextField();
      surnameText.setBounds(180, 50, 165, 25);
      panel.add(surnameText);

      //Creates Email Label and Text Field

      emailLabel = new JLabel("Email:");
      emailLabel.setBounds(10,80,100,25);
      panel.add(emailLabel);

      emailText = new JTextField();
      emailText.setBounds(180, 80, 165, 25);
      panel.add(emailText);

      //Creates Phone Number Label and Text Field

      phoneLabel = new JLabel("Phone Number:");
      phoneLabel.setBounds(10,110,100,25);
      panel.add(phoneLabel);

      phoneText = new JTextField();
      phoneText.setBounds(180, 110, 165, 25);
      panel.add(phoneText);

      //Creates Address Line 1 Label and Text Field

      addressLine1Label = new JLabel("Address Line 1:");
      addressLine1Label.setBounds(10,140,100,25);
      panel.add(addressLine1Label);

      addressLine1Text = new JTextField();
      addressLine1Text.setBounds(180, 140, 165, 25);
      panel.add(addressLine1Text);

      //Creates Address Line 2 Label and Text Field

      addressLine2Label = new JLabel("Address Line 2:");
      addressLine2Label.setBounds(10,170,100,25);
      panel.add(addressLine2Label);

      addressLine2Text = new JTextField();
      addressLine2Text.setBounds(180, 170, 165, 25);
      panel.add(addressLine2Text);

      //Creates Address Line 3 Label and Text Field

      addressLine3Label = new JLabel("Address Line 3:");
      addressLine3Label.setBounds(10,200,100,25);
      panel.add(addressLine3Label);

      addressLine3Text = new JTextField();
      addressLine3Text.setBounds(180, 200, 165, 25);
      panel.add(addressLine3Text);

      //Creates Address Line 4 Label and Text Field

      addressLine4Label = new JLabel("Address Line 4:");
      addressLine4Label.setBounds(10,230,100,25);
      panel.add(addressLine4Label);

      addressLine4Text = new JTextField();
      addressLine4Text.setBounds(180, 230, 165, 25);
      panel.add(addressLine4Text);

    }
    
}
