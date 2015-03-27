package glencaldy;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.DateTime;

public class Loan implements Serializable {
	private static int nextID = 8001;
	private String loanID;
	private String stockID;
	private Date date;
	
	Loan(String stockID){
		this.loanID = String.valueOf(nextID++);
		this.stockID = stockID;
		this.setDate(new Date());
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
	
	public String toString(){
		return "Loan ID\t: " + loanID +
				"\nStock ID\t: " + stockID + 
				"\nCreated on: " + date.toString();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int daysSince(){
		DateTime today = new DateTime(new Date());
		DateTime start = new DateTime(this.date);
		return Days.daysBetween(start, today).getDays();
	}
}
