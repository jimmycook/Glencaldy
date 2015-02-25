package glencaldy;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {
	private static int nextID = 9001;
	private String reservationID;
	private String stockID;
	private Timestamp timestamp;
	
	Reservation(String stockID){
		this.reservationID = String.valueOf(nextID++);
		this.stockID = stockID;
		
		Date date= new java.util.Date();
		this.timestamp = new Timestamp(date.getTime());
	}

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
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
		return "Reservation ID\t: " + reservationID +
				"\nStock ID\t: " + stockID; 
	}
}
