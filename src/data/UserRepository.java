package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.User;

public class UserRepository {
	
	public void deleteUserById(String id) {
		
		  String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
		  int idInt = Integer.parseInt(id);
	     	      
		  try (Connection connection = DriverManager.getConnection(connectionUrl)){
	          		
	             System.out.println("Connected");
	             
	          // Create and execute a SELECT SQL statement.
	              String deleteSql = "DELETE FROM prs.dbo.Users " + 
	            		  			 "Where id = ?";
	              
	              PreparedStatement ps = connection.prepareStatement(deleteSql);
	              ps.setInt(1, Integer.parseInt(id));
	              
	             ps.executeUpdate();
	                          
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
		
		
	}
	
	public void updateUserPassword(int id, String newPassword) {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
		
		 try (Connection connection = DriverManager.getConnection(connectionUrl);
	          		Statement statement = connection.createStatement();) {
			    System.out.println("Connected");
			    
			    String selectSql = "UPDATE prs.dbo.Users " +
			    				   "SET password = '" + newPassword + "' " +
			    				   "WHERE id = " + id;
	                         
	             
	             statement.executeUpdate(selectSql);      
	             
		 }catch (SQLException e) {
	          e.printStackTrace();          
	}
		
 }
	
	public void insertUser(User user) {
		 String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
		 
		 try (Connection connection = DriverManager.getConnection(connectionUrl);
	          		Statement statement = connection.createStatement();) {
			 
	             System.out.println("Connected");
	             
	             int adminBit = user.isAdmin() ? 1 : 0;
	             int reviewerBit = user.isReviewer() ? 1 : 0;
	             
//	             String selectSql = "INSERT INTO prs.dbo.users(Username, Password, FirstName, LastName, Phone, Email, Reviewer, Admin)" +
//	            		            "Values ('" + user.getUsername() + "', '" + user.getPassword() + "', '"
//	                                            + user.getFirstName() + "', '" + user.getLastName() + "', '"  
//	                                            + user.getPhone() + "', '" + user.getEmail() + "'"
//	                                            + ", " + reviewerBit + ", " + adminBit + ")";
//	             
	             String insertSql = "INSERT INTO prs.dbo.Users (Username, Password, FirstName, LastName, Phone, Email, Reviewer, Admin)" +
	                              "Values (?, ?, ?, ?, ?, ?, ?, ?)";
	             PreparedStatement ps = connection.prepareStatement(insertSql);
	             ps.setString(1,  user.getUsername());
	             ps.setString(2,  user.getPassword());
	             ps.setString(3,  user.getFirstName());
	             ps.setString(4,  user.getLastName());
	             ps.setString(5,  user.getPhone());
	             ps.setString(6,  user.getEmail());
	             ps.setInt(7,  reviewerBit);
	             ps.setInt(8, adminBit);

	             ps.executeUpdate();
	             
		 }catch (SQLException e) {
	          e.printStackTrace();          
	}
 }      
	
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
