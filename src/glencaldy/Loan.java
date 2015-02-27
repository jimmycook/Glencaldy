package glencaldy;

import java.sql.Timestamp;
import java.util.Date;

public class Loan {
	private static int nextID = 8001;
	private String loanID;
	private String stockID;
	private Timestamp timestamp;
	
	Loan(String stockID){
		this.loanID = String.valueOf(nextID++);
		this.stockID = stockID;
		
		Date date= new java.util.Date();
		this.timestamp = new Timestamp(date.getTime());
	}

	public String getLoanID() {
		return loanID;
	}

	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString(){
		return "Loan ID\t: " + loanID +
				"\nStock ID\t: " + stockID; 
	}
}
