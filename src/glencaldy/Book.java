package glencaldy;

public class Book extends Publication {

	private String author;
	
	Book(String stockID, String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages) {
		super(stockID, title, cost, publisher, subjectArea,
				serialNum, numPages);
	}
	
	Book(String stockID, String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages, String author) {
		super(stockID, title, cost, publisher, subjectArea,
				serialNum, numPages);
		this.setAuthor(author);
	}
	
	Book(String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages, String author) {
		super(title, cost, publisher, subjectArea,
				serialNum, numPages);
		this.setAuthor(author);
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString(){
		return super.toString() +
				"\nAuthor\t\t: " + author;
	}
	
}
