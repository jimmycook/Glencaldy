package glencaldy;

import java.util.ArrayList;

public class FullMember extends User{
	
	/*
	 * Borrower only
	 */
	@SuppressWarnings("unused")
	private final int borrowingQuota = 	4;
	private boolean isSuspended = false;
	private ArrayList<Loan> userLoans = new ArrayList<Loan>();
	private ArrayList<Reservation> userReservations = new ArrayList<Reservation>();
	
	/*
	 * Personal Information
	 */
	private String address;
	private String town;
	private String postcode;
	private String dateOfBirth;
	
	

	FullMember(String userID, String password, String firstname, String surname) {
		super(userID, password, firstname, surname);
	}
	
	FullMember(String userID, String password, String firstname, String surname, String address, String town, String postcode, String dateOfBirth){
		super(userID, password, firstname, surname);
		this.setAddress(address);
		this.setTown(town);
		this.setPostcode(postcode);
		this.setDateOfBirth(dateOfBirth);
	}
	
	FullMember(CasualUser u){
		this(u.getUsername(), u.getPassword(), u.getFirstname(), u.getSurname(), u.getAddress(), u.getTown(), u.getPostcode(), u.getDateOfBirth());
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
	
	public boolean getIsSuspended(){
		return isSuspended;
	}
	public void setIsSuspended(boolean suspended) {
		this.isSuspended = suspended;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

}
