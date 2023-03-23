package project;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;


public class InsertCustomer {
	
	
	public static void main(String [] args) {
		
		// database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/oosdProject";
		Connection connection = null ;
		PreparedStatement pstat = null;
		String CustomerFirstName = "Mark";
		String CustomerLastName = "Power";
		String CustomerPhone = "0851231234";
		String CustomerEmail = "mark@gmail.com";
		String CustomerStreet = "12 Pollerton";
		String City = "Carlow";
		String County = "Carlow";
		String Eircode = "R12 Y345";
		String Password = "carlow123";
		int i=0;
		try {
			// establish connection to database
			connection = DriverManager.getConnection(DATABASE_URL, "root", "" );
			// create Prepared Statement for inserting data into table
			pstat = connection.prepareStatement("INSERT INTO Customer (CustomerFirstName, CustomerLastName, CustomerPhone, CustomerEmail, CustomerStreet, City, County, Eircode, Password) VALUES (?,?,?,?,?,?,?,?,?)");
			pstat . setString (1, CustomerFirstName );
			pstat . setString (2, CustomerLastName);
			pstat . setString (3, CustomerPhone);
			pstat . setString (4, CustomerEmail);
			pstat . setString (5, CustomerStreet);
			pstat . setString (6, City);
			pstat . setString (7, County);
			pstat . setString (8, Eircode);
			pstat . setString (9, Password);
			
			// insert data into table
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
	} // end main
	} // end class