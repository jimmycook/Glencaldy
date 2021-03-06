package glencaldy;

public abstract class Digital extends Stock {
	
	private double runTime;
	private String caseType;
	
	Digital(String stockID, String title, double cost, String publisher) {
		super(stockID, title, cost, publisher);
	}
	
	Digital(String stockID, String title, double cost, String publisher, double runTime, String caseType) {
		super(stockID, title, cost, publisher);
		this.setRunTime(runTime);
		this.setCaseType(caseType);
	}
	
	Digital(String title, double cost, String publisher, double runTime, String caseType) {
		super(title, cost, publisher);
		this.setRunTime(runTime);
		this.setCaseType(caseType);
	}

	public double getRunTime() {
		return runTime;
	}

	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	
	public String toString(){
		return super.toString() +
				"\nRun time\t: " + String.valueOf(Math.round(runTime * 100) /100) +
				"\nCase type \t: " + caseType;
	}
}
