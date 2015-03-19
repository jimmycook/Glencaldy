package glencaldy;

import java.util.ArrayList;

public class Borrower extends User{
	
	Borrower(String userID, String password, String firstname, String surname) {
		super(userID, password, firstname, surname);
		// TODO Auto-generated constructor stub
	}
	protected int borrowingQuota = 	4;
	protected boolean isSuspended = false;
	protected double fine;
	protected ArrayList<Loan> userLoans = new ArrayList<Loan>();
	protected ArrayList<Reservation> userReservations = new ArrayList<Reservation>();
	
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
	
	public void setBorrowingQuota(int i){
		borrowingQuota = i;
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
	
	public int getBorrowingQuota() {
		return borrowingQuota;
	}
	
	public boolean getIsSuspended(){
		return isSuspended;
	}
	public void setIsSuspended(boolean suspended) {
		this.isSuspended = suspended;
	}

	public String getReservedBy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getFine(){
		return fine;
	}
	
	public void setFine(double f){
		this.fine = f;
	}
	
}
