package ui;

import java.util.ArrayList;

import business.User;
import data.UserRepository;

public class PrsWebApp {

	public static void main(String[] args) {
		Console.displayLine("Welcome to the Java PRS");
		
		UserRepository userRep = new UserRepository();
		
		UserRepository userRepo = new UserRepository();
		ArrayList<User> users = userRepo.getAllUsers();
		
		for (User u : users) {
			Console.displayLine(u.toString());
			
		}
		
//		User user = userRepo.getUserById(1);
//		Console.displayLine(user.toString());
		
		
		Console.displayLine("Thank you. Come again.");
	}

}
