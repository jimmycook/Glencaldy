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
	User activeUser = null;
	LoginRecord activeLogin = null;
	BufferedReader in = new BufferedReader(converter);
	
	LearningCenter(){
		boolean logout = false;
		
		System.out.println("Controller starting\n\n");
		
		populateUsers();
		
		welcomeMessage();
		
		while(activeUser == null){
			activeUser = login();
		}
		
		activeLogin = new LoginRecord(activeUser.getUserID());
		allLogins.add(activeLogin);
		while(!logout)
		{
			getMenu();
		}
		
		logout();
	}
	
	
	private void logout() {
		
	}


	/*
	 * Gets the menu for the correct type of user 
	 */
	public void getMenu(){
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
	
	private void fullMenu() {
		int input = null;
		
		do{
			System.out.println("Full Menu");
			System.out.println("----------------");
			System.out.println("1. View Stock Catalogue");
			System.out.println("2. Change Password");
			System.out.println("3. Request Reservation");
			System.out.println("0. Logout");
			System.out.println("\nEnter an option");
			
			try{
				String tempInput = in.readLine();
			}
			catch (IOException e){
					System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case 1:
				viewCatalogue();
				break;
			case 2:
				changePassword();
				break;
			case 3:
				requestReservation();
				break;
			}
		}
		while(input != 0);
		
		
	}


	private void requestReservation() {
		// TODO Auto-generated method stub
		
	}


	private void changePassword() {
		// TODO Auto-generated method stub
		
	}


	private void viewCatalogue() {
		// TODO Auto-generated method stub
		
	}


	private void until(boolean equals) {
		// TODO Auto-generated method stub
		
	}


	private void adminMenu() {
		
	}


	private void casualMenu() {
		
	}


	/*
	 * Login - asks the user for a username and password until they are successfully logged in to the system
	 * 
	 * @return User made if login was successful, otherwise returns null
	 */
	public User login(){
		User curUser = null;
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
		while(userIt.hasNext()){
			curUser = userIt.next();
			
			if(username.equals(curUser.getUsername())){
				if(password.equals(curUser.getPassword())){
					System.out.println("\n\nLogging in as '" + curUser.getUsername() + "'\n\n");
					return curUser;
				}
				else{
					System.out.println("Login failed - incorrect password\n\n");	
				}
			}
		}
		
		System.out.println("Login failed - user not found\n\n");

		return null;
		
	}
	
	public void welcomeMessage(){
		System.out.println("--------------------------------------\n");
		System.out.println("Welcome to Glencaldy Learning Center\n");
		System.out.println("--------------------------------------\n");
	}
	
	public void populateUsers(){
				
		allUsers.add(
				new CasualUser("casualuser", "password", "John", "Doe", "44 Big Street", "Big Town", "AA1 AA2", "01/01/1990")
				);
		allUsers.add(
				new Administrator("admin", "password", "Admin", "Admin", "2001")
				);
		allUsers.add(
				new StaffMember("staffmember", "password", "John", "Doe", StaffMember.getNextID(), "staffmember@glencaldy.com", "100")
				);
		allUsers.add(
				new FullMember("fullmember", "password", "John", "Doe", "44 Big Street", "Big Town", "AA1 AA2", "01/01/1990")
				);
	}
}
