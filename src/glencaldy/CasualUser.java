package glencaldy;

public class CasualUser extends User{

	private String address;
	private String town;
	private String postcode;
	private String dateOfBirth;
	
	CasualUser(String userID, String password, String firstname, String surname) {
		super(userID, password, firstname, surname);
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

}
