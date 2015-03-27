package glencaldy;

import java.io.Serializable;
import java.util.Date;

public class LoginRecord implements Serializable{
	
	private String computerID = "1"; //Hard coded for prototype
	private Date date = new Date();
	private String userID;
	
	LoginRecord(String uID){
		this.userID = uID;
	}
	
	public String getComputerID() {
		return computerID;
	}
	
	public void setComputerID(String computerID) {
		this.computerID = computerID;
	}

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String toString(){
		return "User ID: \t" + userID +
				"\nComp ID: \t" + String.valueOf(computerID) + 
				"\nDate: \t" + date.toString();
	}
}