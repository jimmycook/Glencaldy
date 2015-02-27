package glencaldy;

public class CasualUser extends User{

	private String address;
	private String town;
	private String postcode;
	private String dateOfBirth;
	
	CasualUser(String userID, String password, String firstname, String surname) {
		super(userID, password, firstname, surname);
	}
	
	CasualUser(String username, String password, String firstname, String surname,
			String address, String town, String postcode, String dateOfBirth) {
		super(username, password, firstname, surname);
		this.address = address;
		this.town = town;
		this.postcode = postcode;
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString(){
		return super.toString() +
				"\nAddress\t: " + address +
				"\nTown\t: " + town +
				"\nPostcode\t: " + postcode +
				"\nDate of birth\t: " + dateOfBirth;
	}
}
