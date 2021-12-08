package ui;

import java.util.ArrayList;

import business.Product;
import business.User;
import data.ProductRepository;
import data.UserRepository;

public class PrsWebApp {

	public static void main(String[] args) {
		Console.displayLine("Welcome to the Java PRS");

		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			Console.displayLine("Which table?");
			Console.displayLine("(1) Users");
			Console.displayLine("(2) Products");
			Console.displayLine("(3) Vendors");
			Console.displayLine("(4) Requests");
			Console.displayLine("(5) LineItems");
			int tableInput = Console.getInt("Table #");

			if (tableInput == 1) {

				UserRepository userRepo = new UserRepository();

				Console.displayLine("Which action?");
				Console.displayLine("(1) Get All Users");
				Console.displayLine("(2) Get User by ID");
				Console.displayLine("(3) Add a User");
				Console.displayLine("(4) Edit a User");
				Console.displayLine("(5) Delete a User");
				int actionInput = Console.getInt("Action #: ");

				if (actionInput == 1) {
					ArrayList<User> users = userRepo.getAllUsers();
//					for (User u : users) {
//						Console.displayLine(u.toString());						
//					}
					users.forEach(u -> Console.displayLine(u.toString()));
				} else if (actionInput == 2) {
					int userId = Console.getInt("Which ID?");
					User u = userRepo.getUserById(userId);
					Console.displayLine(u.toString());
				} else if (actionInput == 3) {
					String username = Console.getString("Username? ");
					String password = Console.getString("Password? ");
					String firstName = Console.getString("First name? ");
					String lastName = Console.getString("Last name? ");	
					String phone = Console.getString("Phone? ");
					String email = Console.getString("Email? ");
					
					User u = new User(0, username, password, firstName, lastName, phone, email, false, false );
					userRepo.insertUser(u);
				}
					else if (actionInput == 4) {
						int id = Console.getInt("User id: ");
						String newPassword = Console.getString("New password: ");
						
						userRepo.updateUserPassword(id, newPassword);
					}
					
					else if (actionInput == 5) {
						String id = Console.getString("User id: ");
						
						userRepo.deleteUserById(id);
					}
			}else if (tableInput == 2) {				
				ProductRepository productRepo = new ProductRepository();
				
				Console.displayLine("Which action?");
				Console.displayLine("(1) Get All Products");
				Console.displayLine("(2) Get Product by ID");
				Console.displayLine("(3) Add a Product");
				Console.displayLine("(4) Edit a Product");
				Console.displayLine("(5) Delete a Product");
				int actionInput = Console.getInt("Action #: ");
				
				if (actionInput == 1) {
					ArrayList<Product> products = productRepo.getAllProducts();

					products.forEach(p -> Console.displayLine(p.toString()));
				
			}
			
		}

		Console.displayLine("Thank you. Come again.");
	}

}
}
