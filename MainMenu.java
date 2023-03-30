package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

	//instance of main menu class that is invoked when the user has successfully logged into the system
	public MainMenu() {
        
		//setting GUI components 
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
        
       JButton Customers = new JButton("Customers");
       Customers.setBounds(100,200,150,25);
       panel.add(Customers);
       
       //action listener that displays the customers page when the customer button is clicked
       Customers.addActionListener(new ActionListener() {
    	   @Override	
    	   public void actionPerformed(ActionEvent e) {
    		   Customers customersPage = new Customers();
    		   customersPage.setVisible(true);
    		   MainMenu.this.dispose();
    	   }
       });
       
       JButton Products = new JButton("Products");
       Products.setBounds(300,200,150,25);
       panel.add(Products);
       
     //action listener that displays the products page when the customer button is clicked
       Products.addActionListener(new ActionListener() {
    	   @Override	
    	   public void actionPerformed(ActionEvent e) {
    		   Products productsPage = new Products();
    		   productsPage.setVisible(true);
    		   MainMenu.this.dispose();
    	   }
       });
       
       JButton Invoices = new JButton("Invoices");
       Invoices.setBounds(517,200,150,25);
       panel.add(Invoices);
       
     //action listener that displays the invoices page when the customer button is clicked
       Invoices.addActionListener(new ActionListener() {
    	   @Override	
    	   public void actionPerformed(ActionEvent e) {
    		   Invoices invoicesPage = new Invoices();
    		   invoicesPage.setVisible(true);
    		   MainMenu.this.dispose();
    	   }
       });      
	
}
}