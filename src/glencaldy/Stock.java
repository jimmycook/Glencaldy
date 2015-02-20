package glencaldy;

public abstract class Stock {
	
	private String stockID;
	private String title;
	private double cost;
	private String publisher;
	private String reservedBy;
	private String loanedTo;
	
	Stock(String stockID, String title, double cost, String publisher){
		this.setStockID(stockID);
		this.setTitle(title);
		this.setCost(cost);
		this.setPublisher(publisher);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLoanedTo() {
		return loanedTo;
	}

	public void setLoanedTo(String loanedTo) {
		this.loanedTo = loanedTo;
	}

	public String getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(String reservedBy) {
		this.reservedBy = reservedBy;
	}
}
