package project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

	
	public MainMenu() {
        
		JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setSize(750,350);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.add(panel);
	    panel.setLayout(null);
	    
        JLabel Header = new JLabel("CW Sports");
        Header.setBounds(340,20,150,25);
        panel.add(Header);
        
        JLabel Info = new JLabel("Select an Option Below");
        Info.setBounds(300,100,150,25);
        panel.add(Info);
        
       JButton Customer = new JButton("Customer");
       Customer.setBounds(100,200,150,25);
       panel.add(Customer);
       
       JButton Products = new JButton("Products");
       Products.setBounds(300,200,150,25);
       panel.add(Products);
       
       JButton Invoices = new JButton("Invoices");
       Invoices.setBounds(517,200,150,25);
       panel.add(Invoices);
       
       
	
}
}
