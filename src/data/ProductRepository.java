package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import business.Product;
import business.User;
import business.Vendor;

public class ProductRepository {
	
	private final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";	
	
	public ArrayList<Product> getAllProducts(){
		ResultSet resultSet = null;
	  	 ArrayList<Product> products = new ArrayList<Product>(); 
	  	 
	         try (Connection connection = DriverManager.getConnection(CONNECTION_URL);
	         		Statement statement = connection.createStatement()) {
	            System.out.println("Connected");
	            
	         // Create and execute a SELECT SQL statement.
	             String selectSql = "SELECT * FROM prs.dbo.Products " + 
	                                "JOIN prs.dbo.vendors ON prs.dbo.vendors.id = prs.dbo.products.vendorId";
	             PreparedStatement ps = connection.prepareStatement(selectSql);
	             
	             resultSet = ps.executeQuery();	             
	                
	             while (resultSet.next()) {
	                int id = resultSet.getInt(1);
//                    int vendorId = resultSet.getInt(2);
	                String partNumber = resultSet.getString(3);
	                String name = resultSet.getString(4);
	                double price = resultSet.getDouble(5); 
	                String unit = resultSet.getString(6);
	                if(resultSet.wasNull()) {
	                	unit = "null";
	                }
	                String photoPath = resultSet.getString(7);
	                if(resultSet.wasNull()) {
	                	photoPath = "null";
	                }
	                
	                int vendorId = resultSet.getInt(8);
	                String code = resultSet.getString(9);
	                String vendorName = resultSet.getString(10);
	                String address = resultSet.getString(11);
	                String city = resultSet.getString(12);
	                String state = resultSet.getString(13);
	                String zip = resultSet.getString(14);
	                String phone = resultSet.getString(15);
	                String email = resultSet.getString(16);	                
	                
	                		
	               	Vendor v = new Vendor(vendorId, code, vendorName, address, city, state, zip, phone, email);                 
	                Product p = new Product(id, v, partNumber, name, price, unit, photoPath);
	                
	                products.add(p);	                
	             }
	             
	            
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	         
	         return products;
		
	}
}
