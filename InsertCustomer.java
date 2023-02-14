package projectyear2oosd;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCustomer {

   
    public static void main(String [] args) {

        // database URL
        final String DATABASE_URL =  "jdbc:mysql://localhost/oosdProject"; 
        Connection connection = null ;
        PreparedStatement pstat = null;
        String CustomerFirstName = " ";
        String CustomerLastName = " ";
        String CustomerPhone = " ";
        String CustomerEmail = " ";
        String CustomerStreet = " ";
        String City = " ";
        String County = " ";
        String Eircode = " ";
        String Password = " ";

        int i =0;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password" );

            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Customers (CustomerFirstName, CustomerLastName, CustomerPhone, CustomerEmail, CustomerStreet, City, County, Eircode, Password) VALUES (?,?,?,?,?,?,?,?,?)");
            pstat.setString(1, CustomerFirstName);
            pstat.setString(2, CustomerLastName);
            pstat.setString(3, CustomerPhone);
            pstat.setString(3, CustomerEmail);
            pstat.setString(4, CustomerStreet);
            pstat.setString(5, City);
            pstat.setString(6, County);
            pstat.setString(7, Eircode);
            pstat.setString(8, Password);

            // insert data into table
            i = pstat .executeUpdate();
            System.out. println ( i + "Customer successfully added. ") ;
        }

        catch(SQLException sqlException ) { 
            
            sqlException . printStackTrace () ;
        } 
        finally {
            try {
               pstat . close () ; connection . close () ;
            }
        
            catch ( Exception exception ){
                exception . printStackTrace () ;
            }
    }
}
}