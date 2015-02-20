package glencaldy;

import java.util.ArrayList;

public class LearningCenter {
	
	private ArrayList<User> allUsers = new ArrayList<User>();
	private ArrayList<Stock> allStock = new ArrayList<Stock>();
	
	LearningCenter(){
		System.out.println("Controller starting\n\n");
		System.out.println("testing");
		
		this.populateUsers();
		
		System.out.println(allUsers.toString());
		
		
	}
	
	public void populateUsers(){
				
		allUsers.add(
				new CasualUser("casualuser", "password", "John", "Doe", "44 Big Street", "Big Town", "AA1 AA2", "01/01/1990")
				);
		allUsers.add(
				new Administrator("admin", "password", "Admin", "Admin", "2001")
				);
		allUsers.add(
				new StaffMember("staffmember", "password", "John", "Doe", StaffMember.getNextID(), "staffmember@glencaldy.com", "100")
				);
		allUsers.add(
				new FullMember("fullmember", "password", "John", "Doe", "44 Big Street", "Big Town", "AA1 AA2", "01/01/1990")
				);
	}
}
