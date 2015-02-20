package glencaldy;

public class Administrator extends User {

	private String staffID;
	
	Administrator(String userID, String password, String firstname,
			String surname) {
		super(userID, password, firstname, surname);
	}
	
	Administrator(String userID, String password, String firstname, String surname, String staffID) {
		super(userID, password, firstname, surname);
		this.staffID = staffID;
	}
	
	
	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

}
