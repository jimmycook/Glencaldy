package glencaldy;

public class FullMember extends Borrower{

	/*
	 * Personal Information
	 */
	private String address;
	private String town;
	private String postcode;
	private String dateOfBirth;
	
	FullMember(String userID, String password, String firstname, String surname, String address, String town, String postcode, String dateOfBirth){
		super(userID, password, firstname, surname);
		this.setAddress(address);
		this.setTown(town);
		this.setPostcode(postcode);
		this.setDateOfBirth(dateOfBirth);
		super.setBorrowingQuota(4);
	}
	
	FullMember(CasualUser u){
		this(u.getUsername(), u.getPassword(), u.getFirstname(), u.getSurname(), u.getAddress(), u.getTown(), u.getPostcode(), u.getDateOfBirth());
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String toString(){
		return super.toString() +
				"\nAddress\t: " + address +
				"\nTown\t: " + town +
				"\nPostcode\t: " + postcode +
				"\nDate of birth\t: " + dateOfBirth + 
				"\nSuspended\t: " + isSuspended +
				"\nQuota\t: " + borrowingQuota + " items";
	}

}
