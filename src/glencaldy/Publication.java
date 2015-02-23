package glencaldy;

public abstract class Publication extends Stock {

	private String subjectArea;
	private String serialNum;
	private int numPages;
	
	Publication(String stockID, String title, double cost, String publisher) {
		super(stockID, title, cost, publisher);
	}
	
	Publication(String stockID, String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages) {
		super(stockID, title, cost, publisher);
		
		this.setSubjectArea(subjectArea);
		this.setSerialNum(serialNum);
		this.setNumPages(numPages);
		
	}
	
	Publication(String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages) {
		super(title, cost, publisher);
		
		this.setSubjectArea(subjectArea);
		this.setSerialNum(serialNum);
		this.setNumPages(numPages);
		
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getSubjectArea() {
		return subjectArea;
	}

	public void setSubjectArea(String subjectArea) {
		this.subjectArea = subjectArea;
	}

	public String toString(){
		return super.toString() +
				"\nSubject Area\t: " + subjectArea +
				"\nSerial Number\t: " + serialNum +
				"\nNumber of Pages\t: " + String.valueOf(numPages);
	}

}
