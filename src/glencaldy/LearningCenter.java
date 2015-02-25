package glencaldy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Controller class for the Glencaldy Learning Center CLI
 * 
 * @author jimmycook
 *
 */
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
	
	/**
	 * 
	 * Menu for logging in to the system
	 * Sets this.activeUser
	 * 
	 * @return boolean based on the login success
	 */
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

	/**
	 * Logs the current user out and serialises the allUsers ArrayList
	 * 
	 * @return void
	 */
	private void logout() {
		this.activeUser = null;
		this.logout = false;
	}


	/**
	 * displays the right type of menu for the users that is currently logged in
	 * @return void
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
	/**
	 * Admin menu for Administrator users only
	 * @return void
	 */
	private void adminMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Administrator Menu");
			System.out.println("----------------");
			System.out.println("1. Manage users");
			System.out.println("2. Manage stock");
			System.out.println("3. Manage reports");
			System.out.println("4. Change Password");
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
				manageUsers();
				break;
			case "2":
				manageStock();
				break;
			case "3":
				manageReports();
				break;
			case "4":
				changePassword();
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
	
	/**
	 * Menu for managing stock
	 * 
	 * @return void
	 */
	private void manageStock(){
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Stock");
			System.out.println("----------------");
			System.out.println("1. View all items");
			System.out.println("2. Add an item");
			System.out.println("3. Remove an item");
			System.out.println("0. Cancel");
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
				addStockMenu();
				break;
			case "3":
				removeStockMenu();
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}
	
	/**
	 * Menu for managing users
	 * 
	 * @return void
	 */
	private void manageUsers(){
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Users");
			System.out.println("----------------");
			System.out.println("1. View all users");
			System.out.println("2. Add a user");
			System.out.println("3. Remove a user");
			System.out.println("4. Edit a user's details");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewUsers();
				break;
			case "2":
				addUserMenu();
				break;
			case "3":
				removeUserMenu();
				break;
			case "4":
				editUserMenu();
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}
	
	/**
	 * Menu for managing reports
	 * 
	 * @return void
	 */
	private void manageReports(){
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Reports");
			System.out.println("----------------");
			System.out.println("1. View users");
			System.out.println("2. View stock");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewUsers();
				break;
			case "2":
				viewCatalogue();
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}

	private void removeStockMenu() {
		// TODO Auto-generated method stub
		
	}

	private void addStockMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Select type of stock to add");
			System.out.println("----------------");
			System.out.println("1. Book");
			System.out.println("2. Journal");
			System.out.println("3. CD");
			System.out.println("4. Video");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			switch (input){ 
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}
	
	private void editUserMenu() {
		String input = null;
		
		System.out.println("Please enter the ID or ID of the user you want to edit.");
		
		try{
			input = in.readLine();
		}
		catch(IOException e){
			System.err.println("Error: " + e);
		}
		
		System.out.println(input);
		User foundUser = getUserByID(input);
		
		if(foundUser == null){
			foundUser = getUserByUsername(input);
		}
	
		System.out.println(foundUser.toString());
		
		
	}

	private void removeUserMenu() {
		// TODO Auto-generated method stub
		
	}

	private void addUserMenu() {
		// TODO Auto-generated method stub
		
	}

	private void viewUsers() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Menu for CasualUsers
	 * @return void
	 */
	private void casualMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Casual User Menu");
			System.out.println("----------------");
			System.out.println("1. View Stock Catalogue");
			System.out.println("2. Change Password");
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
	
	/**
	 * Displays the full menu of options for a staffmember or a fullmember user
	 * @return void
	 */
	private void fullMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Full Menu");
			System.out.println("----------------");
			System.out.println("1. View Stock Catalogue");
			System.out.println("2. Change Password");
			System.out.println("3. Request a Reservation");
			System.out.println("4. Show your Reservations");
			System.out.println("5. Check your fines");
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
				showReservations();
				break;
			case "5": 
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

	/**
	 * Shows all the fines a user may have, works off of a userID rather than a user object
	 * @param userID
	 */
	private void checkFines(String userID) {
		
	}
	
	/**
	 * Users a userID to return a user object from the users ArrayList
	 * @param String userID
	 * @return User
	 */
	private User getUserByID(String userID){
		
		Iterator<User> uIt = allUsers.iterator();
		
		while(uIt.hasNext()){
			User curUser = uIt.next();
			
			if(userID == curUser.getUserID()){
				return curUser;
			}
		}

		return null;
	}
	
	/**
	 * 
	 * Users a username to return a user object from the users ArrayList
	 * 
	 * @param String username
	 * @return User
	 */
	private User getUserByUsername(String username){
		
		Iterator<User> uIt = allUsers.iterator();
		
		while(uIt.hasNext()){
			User curUser = uIt.next();
			
			if(username == curUser.getUsername()){
				return curUser;
			}
		}

		return null;
	}
	
	/**
	 * Provides the subclass name of a user object
	 * @param user 
	 * @return user type as string
	 */
	private String getUserType(User user){
		String userType = user.getClass().getName();
		
		if(userType.equals("glencaldy.FullMember")){
			return  "FullMember";
		}
		else if(userType.equals("glencaldy.StaffMember")){
			return "StaffMember";
		}
		else if(userType.equals("glencaldy.Administrator")){
			return "Administrator";
		}
		else if(userType.equals("glencaldy.CasualUser")){
			return "CasualUser";
		}
		
		return "User";
		
	}
	
	/**
	 * Gets all the reservations from a user, if they have any
	 * @param user
	 */
	private void showReservations(User user){
		if(activeUser.getUserReservations() == null){
			System.out.println("-------------------------");
			System.out.println("User has no reservations." + activeUser.getUsername());
			System.out.println("-------------------------");
		}
		else{
			Iterator<Reservation> it = user.getUserReservations().iterator();
			System.out.println("-------------------------");
			System.out.println("Reservations for " + user.getUsername());
			System.out.println("-------------------------");
			while(it.hasNext()){
				Reservation cur = it.next();
				System.out.println(cur.toString());
				System.out.println("----------------");	
			}	
		}
	}
	
	/**
	 * Overloaded showReservations(User user) to run off of the active user instead
	 */
	private void showReservations(){
		if(activeUser.getUserReservations() == null){
			System.out.println("-------------------------");
			System.out.println("User has no reservations.");
			System.out.println("-------------------------");
		}
		else{
			Iterator<Reservation> it = activeUser.getUserReservations().iterator();

			System.out.println("-------------------------");
			System.out.println("Reservations for " + activeUser.getUsername());
			System.out.println("-------------------------");
			while(it.hasNext()){
				Reservation cur = it.next();
				System.out.println(cur.toString());
				System.out.println("----------------");	
			}	
		}
		
	}
	
	/**
	 * Allows a full or staff member to create a reservation on an item
	 */
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
								
								activeUser.getUserReservations().add(new Reservation(input));
								
								
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

	
	/**
	 * Changes the password for the login user, requires password confirmation
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

	


	/**
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
	
	/**
	 * Welcome message for the system
	 */
	public void welcomeMessage(){
		System.out.println("--------------------------------------\n");
		System.out.println("Welcome to Glencaldy Learning Center\n");
		System.out.println("--------------------------------------\n");
	}
	
	/**
	 * Populates the all users ArrayList
	 */
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
	
	/**
	 * Populates the allStock ArrayList
	 */
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