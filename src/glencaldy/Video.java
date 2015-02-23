package glencaldy;

public class Video extends Digital {

	private String genre;
	private String videoFormat;
	
	Video(String stockID, String title, double cost, String publisher,
			double runTime, String caseType) {
		super(stockID, title, cost, publisher, runTime, caseType);
	}
	
	Video(String stockID, String title, double cost, String publisher,
			double runTime, String caseType, String genre, String videoFormat) {
		super(stockID, title, cost, publisher, runTime, caseType);
		this.setGenre(genre);
		this.setVideoFormat(videoFormat);
	}
	
	Video(String title, double cost, String publisher,
			double runTime, String caseType, String genre, String videoFormat) {
		super(title, cost, publisher, runTime, caseType);
		this.setGenre(genre);
		this.setVideoFormat(videoFormat);
	}

	public String getVideoFormat() {
		return videoFormat;
	}

	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String toString(){
		return super.toString() +
				"\nGenre \t\t: " + genre +
				"\nVideo Format \t: " + videoFormat;
	}
}