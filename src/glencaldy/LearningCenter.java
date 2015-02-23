package glencaldy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LearningCenter {
	
	private ArrayList<User> allUsers = new ArrayList<User>();
	private ArrayList<Stock> allStock = new ArrayList<Stock>();
	private ArrayList<LoginRecord> allLogins = new ArrayList<LoginRecord>();
	private InputStreamReader converter = new InputStreamReader(System.in);
	private boolean logout = false;
	private User activeUser = null;
	private LoginRecord activeLogin = null;
	private BufferedReader in = new BufferedReader(converter);
	
	LearningCenter(){
		boolean quit = false;
		System.out.println("Controller starting\n\n");
		
		populateUsers();
		populateStock();
		
		do{
			welcomeMessage();
			if(activeUser == null){
				quit = loginMenu();
			}
			
			while(!logout)
			{
				getMenu();
			}
			
			if(logout){
				logout();
			}
		}
		while(!quit);
		
	}
	
	
	private boolean loginMenu() {
		String input = null;
		boolean quit = false;
		while(!quit){
			System.out.println("Login Menu");
			System.out.println("----------------");
			System.out.println("1. Login");
			System.out.println("0. Quit");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
					System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				activeUser = null;
				activeUser = login();
				
				if(activeUser != null){
					activeLogin = new LoginRecord(activeUser.getUserID());
					allLogins.add(activeLogin);
					return false;
				}				
				break;
			case "0":
				return true;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		return false;
	}


	private void logout() {
		this.activeUser = null;
	}


	/*
	 * Gets the menu for the correct type of user 
	 */
	private void getMenu(){
		String userType = activeUser.getClass().getName();
		
		if(userType.equals("glencaldy.CasualUser")){
			casualMenu();
		}
		else if(userType.equals("glencaldy.Administrator")){
			adminMenu();
		}
		else{
			fullMenu();
		}
	}
	
	/*
	 * Displays a menu for users that can borrow items
	 */
	private void fullMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Full Menu");
			System.out.println("----------------");
			System.out.println("1. View Stock Catalogue");
			System.out.println("2. Change Password");
			System.out.println("3. Request Reservation");
			System.out.println("4. Check your fines");

			System.out.println("0. Logout");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewCatalogue();
				break;
			case "2":
				changePassword();
				break;
			case "3":
				requestReservation();
				break;
			case "4": 
				checkFines(activeUser.getUserID());
				break;
			case "0":
				this.logout = true;
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}


	/*
	 * Checks if there are any fines tied to a user
	 */
	private void checkFines(String userID) {
		// TODO Auto-generated method stub
		
	}

	private void requestReservation() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Reserve an item");
			System.out.println("---------------");
			System.out.println("Please enter the stockID of the item you want to reserve, or to cancel, just enter nothing");
			try{
				input = in.readLine();
				
				if(input.equals("")) 
					quit = true;
				else{
					Iterator<Stock> stockIt = allStock.iterator();
					boolean found = false;
					while(stockIt.hasNext() && !found){
						Stock curItem = stockIt.next();
						
						if(curItem.getStockID().equals(input)){
							found = true;
							System.out.println("Stock item found");
							System.out.println("-----------------");
							System.out.println(curItem.toString());
							System.out.println("-----------------");
							System.out.println("Reserve this item? (y for yes, to cancel enter anything else)");
							
							if(in.readLine().equals("y")){
								if(activeUser.getClass().getName() == "glencaldy.FullMember"){
									((FullMember) activeUser).getUserReservations().add(new Reservation(input));
								}
								else if(activeUser.getClass().getName() == "glencaldy.StaffMember"){
									((StaffMember) activeUser).getUserReservations().add(new Reservation(input));
								}
								
								System.out.println("Reservation created\n");
								
								quit = true;
							}
							else{
								System.out.println("Reservation of item cancelled");
							}
							
						}
					}
					
					if(!found){
						System.out.println("Item not found");
					}
				}
			}
			catch (IOException e){
				System.err.println("Error: " + e);
			}		
		}
		while(!quit);
	}

	/*
	 * changes the password for the login user, requires password confirmation
	 */
	private void changePassword() {
		String oldPassword = null;
		String confirmPassword = null;
		String newPassword = null;
		
		boolean finished = false;
		while(!finished){
			System.out.println("Enter new password");
			try{
				newPassword = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}	
			
			System.out.println("Confirm new password");
			try{
				confirmPassword = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}	
			
			if(confirmPassword.equals(newPassword) && !newPassword.equals(null) &&!confirmPassword.equals(null)){
				System.out.println("New password matches");
				int attempts = 3;
				
				while(attempts > 0 && !finished){

					System.out.println("Enter old password");
					System.out.println(attempts + " attempts remaining");
					
					try{
						oldPassword = in.readLine();
					}
					catch (IOException e){
						System.err.println("Error : " + e);
					}
					
					if(oldPassword.equals(this.activeUser.getPassword())){
						activeUser.setPassword(newPassword);
						finished = true;
						System.out.println("Password changed successfully");
					}
					else
						attempts--;
				}
			}
		}
		
	}

	private void viewCatalogue() {
		Iterator<Stock> stockIt = allStock.iterator();
		
		System.out.println("--------------------------");
		System.out.println("Stock Catalogue");
		System.out.println("--------------------------");
		
		while(stockIt.hasNext()){
			System.out.println(stockIt.next().toString());
			System.out.println("--------------------------");
		}
		
		System.out.println("Press enter to continue");
		try{
			in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}	
	}

	private void adminMenu() {
		
	}


	private void casualMenu() {
		
	}


	/*
	 * Login - asks the user for a username and password until they are successfully logged in to the system
	 * 
	 * @return User if login was successful returns the user object, otherwise returns null
	 */
	public User login(){
		User curUser = null;
		User foundUser = null;
		String username = null;
		String password = null;
		Iterator<User> userIt = allUsers.iterator();
				
		/*
		 * get username and password from the user
		 */
		System.out.println("Log in to Glencaldy Learning Center");
		System.out.println("Please enter your username");
		try{
			username = in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}
		System.out.println("Please enter your password");
		
		try{
			password = in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}
		
		/*
		 * runs through the iterator until the user is found or all users have been checked
		 */
		while(userIt.hasNext() && foundUser == null){
			curUser = userIt.next();
			
			if(username.equals(curUser.getUsername())){
				
				foundUser = curUser;
				if(password.equals(curUser.getPassword())){
					System.out.println("\n\nLogging in as '" + curUser.getUsername() + "'\n\n");
					return curUser;
				}
				else{
					System.out.println("Login failed - incorrect password\n\n");	
				}
			}
		}
		
		if(foundUser == null){
			System.out.println("Login failed - user now found");
		}
		
		return null;
		
	}
	
	public void welcomeMessage(){
		System.out.println("--------------------------------------\n");
		System.out.println("Welcome to Glencaldy Learning Center\n");
		System.out.println("--------------------------------------\n");
	}
	
	public void populateUsers(){
		allUsers.add(
				new CasualUser("casualuser", "password", "John", "Doe", "44 Big Street", "Big Town", 
						"AA1 AA2", "01/01/1990")
				);
		allUsers.add(
				new Administrator("admin", "password", "Admin", "Admin", "2001")
				);
		allUsers.add(
				new StaffMember("staffmember", "password", "John", "Doe", StaffMember.getNextID(),
						"staffmember@glencaldy.com", "100")
				);
		allUsers.add(
				new FullMember("fullmember", "password", "John", "Doe", "44 Big Street", "Big Town", 
						"AA1 AA2", "01/01/1990")
				);
	}
	
	public void populateStock(){
		allStock.add(new CD("Sonic Highways", 7.99, "Sony",
				40, "Normal", "Audio", "Foo Fighters"));
		allStock.add(new Video("Sharknado", 99.99, "Fox",
				40, "Normal", "Thriller", "BluRay"));
		allStock.add(new Book("Life of Pi", 8.99, "Penguin",
				"Novel", "0544115287", 300, "Yann Martel"));
		allStock.add(new Journal("Some Journal", 2.5, "Oxford",
				"Science", "123456789", 240 , "Issue 2", "1/1/2000"));
	}
	
	
}
