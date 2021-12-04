package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.User;

public class UserRepository {
	
  public User getUserById(int id) {
	  User u = new User();
	  String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
      ResultSet resultSet = null;
      
	  try (Connection connection = DriverManager.getConnection(connectionUrl);
          		Statement statement = connection.createStatement()) {
             System.out.println("Connected");
             
          // Create and execute a SELECT SQL statement.
              String selectSql = "SELECT * FROM prs.dbo.Users" +
                                  "Where id = " + id;
              
              resultSet = statement.executeQuery(selectSql);
              
                 
              while (resultSet.next()) {
                
                 String username = resultSet.getString(2);
                 String password = resultSet.getString(3);
                 String firstName = resultSet.getString(4);
                 String lastName = resultSet.getString(5); 
                 String phone = resultSet.getString(6);
                 String email = resultSet.getString(7);
                 boolean isReviewer = resultSet.getBoolean(8);
                 boolean isAdmin = resultSet.getBoolean(9);
                  
                 u = new User(id, username, password, firstName, lastName,
                 		           phone, email, isReviewer, isAdmin);
                
                
              }
              
             
          } catch (SQLException e) {
              e.printStackTrace();
          }
          
	  
	  return u; 
	  
  }	
       
  public ArrayList<User> getAllUsers(){
       String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
       
  	 ResultSet resultSet = null;
  	 ArrayList<User> users = new ArrayList<User>(); 
  	 
         try (Connection connection = DriverManager.getConnection(connectionUrl);
         		Statement statement = connection.createStatement()) {
            System.out.println("Connected");
            
         // Create and execute a SELECT SQL statement.
             String selectSql = "SELECT * FROM prs.dbo.Users";
             resultSet = statement.executeQuery(selectSql);
             
                
             while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5); 
                String phone = resultSet.getString(6);
                String email = resultSet.getString(7);
                boolean isReviewer = resultSet.getBoolean(8);
                boolean isAdmin = resultSet.getBoolean(9);
                 
                User u = new User(id, username, password, firstName, lastName,
                		           phone, email, isReviewer, isAdmin);
                users.add(u); 
               
             }
             
            
         } catch (SQLException e) {
             e.printStackTrace();
         }
         
         return users;
       
   }
}
