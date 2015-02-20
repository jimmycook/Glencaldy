package glencaldy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Iterator;

public class LearningCenter {
	
	private ArrayList<User> allUsers = new ArrayList<User>();
	private ArrayList<Stock> allStock = new ArrayList<Stock>();
	private InputStreamReader converter = new InputStreamReader(System.in);
	BufferedReader in = new BufferedReader(converter);
	
	LearningCenter(){
		LoginRecord loginRecord = null;
		System.out.println("Controller starting\n\n");
		System.out.println("testing");
		
		this.populateUsers();
		while(loginRecord == null){
			loginRecord = login();
		}
		
		
	}
	
	/*
	 * 
	 * Login - asks the user for a username and password until they are successfully logged in to the system
	 * 
	 * @return LoginRecord made if login was successful
	 */
	public LoginRecord login(){
		boolean loggedIn = false;
		boolean found = false;
		User curUser = null;
		String username = null;
		String password = null;
		Iterator<User> userIt = allUsers.iterator();
		
		found = false;
		
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
				found = true;
				if(password.equals(curUser.getPassword())){
					
					System.out.println("Logged in as " + curUser.getUsername());
					return new LoginRecord(curUser.getUserID());
					
				}
				else{
					
					System.out.println("Login failed - incorrect password\n\n");
					
				}
			}
			else{
				
				System.out.println("Login failed - user not found\n\n");
			
			}
		}
		
		return null;
		
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
