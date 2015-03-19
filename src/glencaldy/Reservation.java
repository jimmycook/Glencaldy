package glencaldy;


public class Reservation {
	private static int nextID = 9001;
	private String reservationID;
	private String stockID;
	private java.util.Date date;
	
	Reservation(String stockID){
		this.reservationID = String.valueOf(nextID++);
		this.stockID = stockID;
		
		this.setDate(new java.util.Date());
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

	
	public String toString(){
		return "Reservation ID\t: " + reservationID +
				"\nStock ID\t: " + stockID +
				"\nCreated on: " + date.toString();		
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
}
