package glencaldy;

import java.util.ArrayList;


/**
 * General user abstract class for the Glencaldy learning center system
 * 
 * @author jimmycook
 */
public abstract class User {
	
	private static int nextUID = 1001;
	private String username;
	private String userID;
	private String password;
	private String firstname;
	private String surname;
	private LoginRecord lastLogin; 
	private ArrayList<LoginRecord> loginRecords = new ArrayList<LoginRecord>();
	
	User(String userID, String password, String firstname, String surname){
		this.userID = String.valueOf(User.getNextUID());
		this.username = userID;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
	}
	
	public boolean login(String un, String pw){
		
		if(un == this.userID & pw == this.password){
			
			LoginRecord lr = new LoginRecord(this.userID);
			lastLogin = lr;
			
			loginRecords.add(lastLogin);
			
			return true;
			
		}
		
		return false;
	}

	public String getUserID(){
		return userID;
	}
		
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
		
	public LoginRecord getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(LoginRecord lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public static int getNextUID() {
		return nextUID++;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * purely for inheritance 
	 * @return null
	 */
	public ArrayList<Reservation> getUserReservations() {
		return null;
	}
	
	public String toString(){
		
		return "User ID\t: "+ this.userID +
				"\nUsername\t: "+ this.username +
				"\nName\t: " + this.firstname + " " + this.surname;
	}
}
