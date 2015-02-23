package glencaldy;

public class Journal extends Publication {

	private String issueNum;
	private String dateOfIssue;
	
	Journal(String stockID, String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages) {
		super(stockID, title, cost, publisher, subjectArea, serialNum, numPages);
	}
	
	Journal(String stockID, String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages, String issueNum, String dateOfIssue) {
		super(stockID, title, cost, publisher, subjectArea, serialNum, numPages);
		
		this.setDateOfIssue(dateOfIssue);
		this.setIssueNum(issueNum);
		
	}
	
	Journal(String title, double cost, String publisher,
			String subjectArea, String serialNum, int numPages, String issueNum, String dateOfIssue) {
		super(title, cost, publisher, subjectArea, serialNum, numPages);
		this.setDateOfIssue(dateOfIssue);
		this.setIssueNum(issueNum);
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	
	public String toString(){
		return super.toString() +
				"\nIssue Number\t: " + issueNum +
				"\nDate of issue\t: " + dateOfIssue;
	}

}
