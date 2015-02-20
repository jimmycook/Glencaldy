package glencaldy;

import java.sql.*;

public class LoginRecord {
	
	private String computerID = "1"; //Hard coded for prototype
	private Timestamp timestamp;
	private String userID;
	
	LoginRecord(String uID){
		java.util.Date date= new java.util.Date();
		this.timestamp = new Timestamp(date.getTime());
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}