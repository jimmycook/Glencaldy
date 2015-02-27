package glencaldy;

public class StaffMember extends Borrower{
	private static int nextID;
	private String staffID;
	private String staffEmail;
	private String telephoneExtension;
	
	StaffMember(String userID, String password, String firstname, String surname, String staffID, String staffEmail, String telephoneExtension) {
		super(userID, password, firstname, surname);
		this.setStaffID(staffID);
		this.setStaffEmail(staffEmail);
		this.setTelephoneExtension(telephoneExtension);
		super.setBorrowingQuota(6);
	}
	
	public String getStaffID() {
		return staffID;
	}


	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getTelephoneExtension() {
		return telephoneExtension;
	}

	public void setTelephoneExtension(String telephoneExtension) {
		this.telephoneExtension = telephoneExtension;
	}

	public static String getNextID() {
		return String.valueOf(nextID++);
	}
	
	public String toString(){
		return super.toString() +
				"\nStaff ID\t: " + staffID + 
				"\nEmail\t: " + staffEmail + 
				"\nExtension\t: " + telephoneExtension +
				"\nSuspended\t: " + isSuspended +
				"\nQuota\t: " + borrowingQuota + " items";
 	}
}
