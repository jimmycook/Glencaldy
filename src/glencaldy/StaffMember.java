package glencaldy;

import java.util.ArrayList;

public class StaffMember extends User{
	private static int nextID;
	
	private String staffID;
	private String staffEmail;
	private String telephoneExtension;

	/*
	 * Borrower only
	 */
	private final int borrowingQuota = 	6;
	private boolean isSuspended = false;
	private ArrayList<Loan> userLoans = new ArrayList<Loan>();
	private ArrayList<Reservation> userReservations = new ArrayList<Reservation>();
	
	StaffMember(String userID, String password, String firstname, String surname, String staffID, String staffEmail, String telephoneExtension) {
		super(userID, password, firstname, surname);
		this.setStaffID(staffID);
		this.setStaffEmail(staffEmail);
		this.setTelephoneExtension(telephoneExtension);
	}
	
	public Loan createLoan(String stockID){
		Loan l = new Loan(stockID); 
		
		this.userLoans.add(l);
		
		return l;	  
	}
	
	public Reservation createReservation(String stockID){
		Reservation r = new Reservation(stockID);
		
		this.userReservations.add(r);
		
		return r;
	}


	public String getStaffID() {
		return staffID;
	}


	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}


	public int getBorrowingQuota() {
		return borrowingQuota;
	}


	public boolean isSuspended() {
		return isSuspended;
	}


	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}


	public ArrayList<Loan> getUserLoans() {
		return userLoans;
	}


	public void setUserLoans(ArrayList<Loan> userLoans) {
		this.userLoans = userLoans;
	}


	public ArrayList<Reservation> getUserReservations() {
		return userReservations;
	}


	public void setUserReservations(ArrayList<Reservation> userReservations) {
		this.userReservations = userReservations;
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

}
