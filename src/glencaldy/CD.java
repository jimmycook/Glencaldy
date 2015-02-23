package glencaldy;

public class CD extends Digital {
	
	private String cdType;
	private String artist;
	
	CD(String stockID, String title, double cost, String publisher,
			double runTime, String caseType) {
		super(stockID, title, cost, publisher, runTime, caseType);
	}
	
	CD(String stockID, String title, double cost, String publisher,
			double runTime, String caseType, String cdType, String artist) {
		super(stockID, title, cost, publisher, runTime, caseType);
		this.setCdType(cdType);
		this.setArtist(artist);
	}
	
	CD(String title, double cost, String publisher,
			double runTime, String caseType, String cdType, String artist) {
		super(title, cost, publisher, runTime, caseType);
		this.setCdType(cdType);
		this.setArtist(artist);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCdType() {
		return cdType;
	}

	public void setCdType(String cdType) {
		this.cdType = cdType;
	}
	
	public String toString(){
		return super.toString() +
				"\nCD Type \t: " + cdType +
				"\nArtist \t\t: " + artist;
	}
}