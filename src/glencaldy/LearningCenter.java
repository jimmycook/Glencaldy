package glencaldy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;



/**
 * 
 * Controller class for the Glencaldy Learning Center application
 * 
 * @author jimmycook
 *
 */
public class LearningCenter {
	
	private ArrayList<User> allUsers = new ArrayList<User>();
	private ArrayList<Stock> allStock = new ArrayList<Stock>();
	private ArrayList<LoginRecord> allLogins = new ArrayList<LoginRecord>();
	private InputStreamReader converter = new InputStreamReader(System.in);
	private boolean logout = false;
	private User activeUser = null;
	private LoginRecord activeLogin = null;
	private BufferedReader in = new BufferedReader(converter);
	
	LearningCenter(){
		boolean quit = false;
		System.out.println("Controller starting\n\n");
		
		populateUsers();
		populateStock();
		
		while(!quit){
			welcomeMessage();
			if(activeUser == null){
				quit = loginMenu();
			}
			else{
				while(!logout)
				{
					getMenu();
				}
				
				if(logout){
					logout();
				}
			}

		}
		
		System.out.println("System shutting down");
	}
	
	/**
	 * Logs the current user out and serialises the allUsers ArrayList
	 * 
	 * @return void
	 */
	public void logout() {
		this.activeUser = null;
		this.logout = false;
		
		try{
			FileOutputStream fos= new FileOutputStream("allUsers");
	        ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.allUsers);
			oos.close();
	        fos.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
	    }
	}

	/**
	 * displays the right type of menu for the users that is currently logged in
	 * @return void
	 */
	public void getMenu(){
		
		if(activeUser instanceof CasualUser){
			casualMenu();
		}
		else if(activeUser instanceof Administrator){
			adminMenu();
		}
		else if(activeUser instanceof StaffMember){
			fullMenu();
		}
		else if(activeUser instanceof FullMember){
			fullMenu();
		}
	}

	/**
	 * 
	 * Menu for logging in to the system
	 * Sets this.activeUser
	 * 
	 * @return boolean based on the login success
	 */
	public boolean loginMenu() {
		String input = null;
		boolean quit = false;
		while(!quit){
			System.out.println("Login Menu");
			System.out.println("----------------");
			System.out.println("1. Login");
			System.out.println("0. Quit");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
					System.err.println("Error : " + e);
			}
			
			switch (input){ 
			case "1":
				activeUser = null;
				activeUser = login();
				
				if(activeUser != null){
					activeLogin = new LoginRecord(activeUser.getUserID());
					allLogins.add(activeLogin);
					return false;
				}				
				break;
			case "0":
				return true;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		return false;
	}

	/**
	 * Admin menu for Administrator users only
	 * @return void
	 */
	public void adminMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Administrator Menu");
			System.out.println("----------------");
			System.out.println("1. Manage users");
			System.out.println("2. Manage stock");
			System.out.println("3. Manage reports");
			System.out.println("4. Manage loans");
			System.out.println("5. Change password");
			System.out.println("6. Issue fine");
			System.out.println("0. Logout");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			switch (input){ 
			case "1":
				manageUsers();
				break;
			case "2":
				manageStock();
				break;
			case "3":
				manageReports();
				break;
			case "4":
				manageLoans();
				break;
			case "5":
				changePassword();
				break;
			case "6": 
				issueFine();
			case "0":
				this.logout = true;
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}
	
	public void issueFine(){
		String i = askFor("the username of the user that the item "
				+ "is being loaned to");
		User u = getUserByUsername(i);
		
		if(u == null){
			System.out.println("User not found");
		}
		else{
			Borrower b = isBorrower(u);
			
			if(b == null){
				System.out.println("This user can not borrow items");
				return;
			}
			else{
				i = askFor("amount in pounds and pence (as a number with 2 decimal places) to fine the user");
				try{
					if(Double.valueOf(i) > 0){
						b.setFine(b.getFine() + Double.valueOf(i));
						b.setIsSuspended(true);
					}
					
				}
				catch(Exception e){
					System.out.println("Fine failed");
				}
				System.out.println("Fine issued");
			}
		}
		
		
	}
	
	/**
	 * Menu for CasualUsers
	 * @return void
	 */
	public void casualMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Casual User Menu");
			System.out.println("----------------");
			System.out.println("1. View Stock Catalogue");
			System.out.println("2. Change Password");
			System.out.println("3. Edit account details");
			System.out.println("0. Logout");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewCatalogue();
				break;
			case "2":
				changePassword();
				break;
			case "3": 
				editUser();
			case "0":
				this.logout = true;
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}

	/**
	 * Displays the full menu of options for a StaffMember or a FullMember 
	 * 
	 * @return void
	 */
	public void fullMenu() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Full Menu");
			System.out.println("----------------");
			System.out.println("1. View Stock Catalogue");
			System.out.println("2. Change Password");
			System.out.println("3. Request a Reservation");
			System.out.println("4. Show your Reservations");
			System.out.println("5. Check your fines");
			System.out.println("6. Change your account details");
			System.out.println("0. Logout");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewCatalogue();
				break;
			case "2":
				changePassword();
				break;
			case "3":
				requestReservation();
				break;
			case "4":
				showReservations();
				break;
			case "5": 
				checkFines();
				break;
			case "6": 
				editUser();
				break;
			case "0":
				this.logout = true;
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}
	

	/**
	 * Menu for managing stock
	 * 
	 * @return void
	 */
	public void manageStock(){
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Stock");
			System.out.println("----------------");
			System.out.println("1. View all items");
			System.out.println("2. Add an item");
			System.out.println("3. Remove an item");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewCatalogue();
				break;
			case "2":
				addStockMenu();
				break;
			case "3":
				removeStockMenu();
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}

	
	/**
	 * Menu for managing users
	 * 
	 * @return void
	 */
	public void manageUsers(){
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Users");
			System.out.println("----------------");
			System.out.println("1. View all users");
			System.out.println("2. Add a user");
			System.out.println("3. Remove a user");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			switch (input){ 
			case "1":
				viewUsers();
				break;
			case "2":
				addUserMenu();
				break;
			case "3":
				removeUserMenu();
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}


	/**
	 * Menu for managing reports
	 * 
	 * @return void
	 */
	public void manageReports(){
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Reports");
			System.out.println("----------------");
			System.out.println("1. View users");
			System.out.println("2. View stock");
			System.out.println("3. View loans");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			
			switch (input){ 
			case "1":
				viewUsers();
				break;
			case "2":
				viewCatalogue();
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}


	/**
	 * Loans menu
	 * @return void
	 */
	public void manageLoans() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Manage Loans");
			System.out.println("----------------");
			System.out.println("1. View loans");
			System.out.println("2. Loan item");
			System.out.println("3. Return item");
			System.out.println("0. Quit");
			
			input = getInput();
			
			switch(input){
			case "1":
				viewLoans();
				break;
			case "2":
				addLoan();
				break;
			case "3":
				removeLoan();
				break;
			case "0":
				
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		}
		while(!quit);
	}
	
	/**
	 * Returns a stock item to the system and removes the loan 
	 * If the item is late a fine will be created and the users
	 * account will be suspended, the loan will still be removed
	 * 
	 * @return void
	 */
	public void removeLoan() {
		String input = askFor("the username of the user that the item "
				+ "was loaned to");
		
		User u = getUserByUsername(input);
		
		if(u == null){
			System.out.println("User not found");
			return;
		}
		
		Borrower b = isBorrower(u);
		
		if(b == null){
			System.out.println("This user can not borrow items");
			return;
		}
		
		System.out.println("Loans for " + b.getUsername());
		System.out.println("----------------");
		Iterator<Loan> it = b.getUserLoans().iterator();
		while(it.hasNext()){
			Loan l = it.next();
			System.out.println(l.toString());
			System.out.println("----------------");
			
		}
		input = askFor("the ID of the loan to be returned");
		
		it = b.getUserLoans().iterator();
		while(it.hasNext()){
			Loan l = it.next();
			
			if(l.getLoanID().equals(input)){
				String i = askFor("anything to continue, or enter 0 to cancel the loan");
				
				switch(i){
				case "0":
					System.out.println("Cancelled");
					break;
				default:
					int days = l.daysSince();
					if(days > 10){						
						days = days - 10;				
						if(days > 0){
							double fine = days * 0.5;
							b.setFine(fine);
							System.out.println("Fine set of " + String.valueOf(fine));
						}
					}
					b.getUserLoans().remove(l);
					Stock item = getStockByID(l.getStockID());
					item.setLoanedTo(null);
					System.out.println("Item returned successfully");
					break;
				}
			}
		}
	}

	/**
	 * Creates a loan
	 * 
	 * @return void
	 */
	public void addLoan() {		
		String input = askFor("the username of the user that the item "
				+ "is being loaned to");
		User u = getUserByUsername(input);
		
		if(u == null){
			System.out.println("User not found");
			return;
		}
		
		Borrower b = isBorrower(u);
		
		if(b == null){
			System.out.println("This user can not borrow items");
			return;
		}
		else if(b.getIsSuspended()){
			System.out.println("This user is suspended until they pay any outstanding fines");
			return;
		}
		else if(b.getBorrowingQuota() <= b.getUserLoans().size()){
			System.out.println("Users borrowing quota reached");
			return;
		}
		
		input = askFor("the stock ID of the item to be loaned, or enter"
				+ " nothing to cancel");
		
		Stock item = getStockByID(input);
		
		if(item != null){
			System.out.println("Item found");
			
			if(item.getLoanedTo() != null){
				System.out.println("This item is already out on loan");
			}
			else{
				
				String reservedBy = b.getReservedBy();
				
				if(reservedBy != null){
					System.out.println("Item is reserved " + getUserByID(reservedBy).getUsername() + 
							"(ID: " + reservedBy + ")");
				}
				System.out.println("Issue loan?");
				String issue = askFor("y for yes, anything else for no");
				
				if(issue.equals("y")){
					b.createLoan(input);
					item.setReservedBy(null);
					item.setLoanedTo(b.getUserID());
					System.out.println("Loan created");
				}
				else 
					System.out.println("Loan cancelled");
			}
		}
		else{
			System.out.println("Item not found");
		}
	}
	
	/**
	 * Displays all the loans in the system
	 * 
	 * @return void
	 */
	public void viewLoans() {
		Iterator<User> uIt = allUsers.iterator();
		Borrower b = null;
		boolean found = false;
		System.out.println("All loans");
		System.out.println("----------------");

		while(uIt.hasNext()){
			b = null;
			User u = uIt.next();
			b = isBorrower(u);
			if(b != null){
				ArrayList<Loan> lList = b.getUserLoans();
				Iterator<Loan> lIt = lList.iterator(); 
				while(lIt.hasNext()){
					Loan l = lIt.next();
					System.out.println(l.toString());
					System.out.println("User\t\t: " + b.getUsername());
					System.out.println("----------------");
					found = true;
				}
				
			}
		}
		if(!found){
			System.out.println("No loans in system");
		}
		System.out.println("Press enter to continue");
		getInput();
	}
	
	/**
	 * Menu for removing stock items
	 * 
	 * @return void
	 */
	public void removeStockMenu() {
		String input = null;
		boolean quit = false;
		Stock s = null;
		
		do{
			System.out.println("Enter the username of the user to be removed, or 0 to cancel");
			System.out.println("----------------");
			
			input = getInput();
			
			if(input.equals("0")){
				quit = true;
			}
			else{
				try{
					s = getStockByID(input);
				}
				catch(Exception e){
					System.err.println("Error " + e);
				}
				
				if(s != null){
					System.out.println("Item found");
					allStock.remove(s);
					System.out.println("Item removed");
					quit = true;
				}	
				else{
					System.out.println("Item not found");
				}
			}
		}
		while(!quit);
	}

	/**
	 * Searches through the array list for a stock item with the ID you're searching for
	 * 
	 * @param input
	 * @return
	 */
	public Stock getStockByID(String input) {

		Iterator<Stock> it = allStock.iterator();
		
		while(it.hasNext()){
			Stock cur = it.next();
			
			if(cur.getStockID().equals(input)){
				return cur;
			}
		}

		return null;
	}

	
	/**
	 * 
	 * asks for the details for adding a stock item to the system
	 * 
	 * @param string type of stock, class names only accepted
	 * @return Stock the created stock item
	 */
	public Stock getStockDetails(String type){
		
		String[] details = new String[10]; 
		
		if(!type.equals("Book") && !type.equals("Journal") && !type.equals("CD") && !type.equals("Video")){
			return null;
		}
		details[0] = askFor("the stock item's title");
		if(details[0].length() < 4)
		{
			System.out.println("Item title too short");
			return null;
		}
		
		details[1] = askFor("the cost of " + details[0] + " as a number to two decimal places");
		if(!isNumeric(details[1]))
		{
			System.out.println("Item cost must be a numeric");
			return null;
		}
		
		details[2] = askFor("publisher");
		
		switch(type){
		case "Book":
			details[3] = askFor("the subject area of this book");
			if(details[3].length() < 1){
				System.out.println("Your entry was too short");
				return null;
			}
			
			details[4] = askFor("the ISBN of this book");
			if(details[4].length() < 1){
				System.out.println("Your entry was too short");
				return null;
			}
			
			details[5] = askFor("the number of pages in this book");
			if(!isNumeric(details[5])){
				System.out.println("Entry was not numeric");
				return null;
			}
			
			details[6] = askFor("the author of the book");
			return new Book(details[0], Double.parseDouble(details[1]), details[2], details[3], details[4], Integer.parseInt(details[5]), details[6]);
		case "Journal":
			details[3] = askFor("the subject area of the journal");
			if(details[3].length() < 1){
				System.out.println("Your entry was too short");
				return null;
			}
			
			details[4] = askFor("the ISSN of this journal");
			if(details[4].length() < 1){
				System.out.println("Your entry was too short");
				return null;
			}
			
			details[5] = askFor("the number of pages in this journal");
			if(!isNumeric(details[5])){
				System.out.println("Entry was not numeric");
				return null;
			}
			
			details[6] = askFor("issue number");
			details[7] = askFor("date of issue");
			
			return new Journal(details[0], Double.parseDouble(details[1]), details[2], details[3], details[4], Integer.parseInt(details[5]), details[6], details[7]);
		case "CD":
			details[3] = askFor("the run time");
			if(!isNumeric(details[3])){
				System.out.println("Input was not numeric");
				return null;
			}
			
			details[4] = askFor("case type");
			
			if(details[4].length() < 1){
				System.out.println("Input was too short");
				return null;
			}
			
			details[5] = askFor("the CD type");
			details[6] = askFor("the CD's artist");
			return new CD(details[0], Double.parseDouble(details[1]), details[2], Double.parseDouble(details[3]), details[4], details[5], details[6]);
		case "Video":
			details[3] = askFor("the run time");
			if(!isNumeric(details[3])){
				System.out.println("Input was not numeric");
				return null;
			}
			
			details[4] = askFor("case type");

			if(details[4].length() < 1){
				System.out.println("Input was too short");
				return null;
			}
			
			details[5] = askFor("the video genre");
			
			details[6] = askFor("the video format");
			return new Video(details[0], Double.parseDouble(details[1]), details[2], Double.parseDouble(details[3]), details[4], details[5], details[6]);

		}
		return null;
	
	}
	
	/**
	 * Menu to add stock items, loads them into the allStock array list
	 * 
	 * @return void
	 */
	public void addStockMenu() {
		String input = null;
		Stock s = null;
		boolean quit = false;
		while(!quit){
			System.out.println("Select type of stock to add");
			System.out.println("----------------");
			System.out.println("1. Book");
			System.out.println("2. Journal");
			System.out.println("3. CD");
			System.out.println("4. Video");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			
			input = getInput();
			
			switch (input){ 
			case "1":
				System.out.println("You have selected: Book");
				System.out.println("Please enter the details for your book");

				s = getStockDetails("Book");
					
				break;
			case "2":
				System.out.println("You have selected: Journal");
				System.out.println("Please enter the details for your journal");
				s = getStockDetails("Journal");
				break;
			case "3":
				System.out.println("You have selected: CD");
				System.out.println("Please enter the details for your CD");
				s = getStockDetails("CD");
				break;
			case "4":
				System.out.println("You have selected: Video");
				System.out.println("Please enter the details for your video");
				s = getStockDetails("Video");
				break;
			case "0":
				quit = true;
				return;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
			if(s != null){
				try{
					allStock.add(s);
					System.out.println("Stock added successfully");	
				}
				catch(Exception e){
					System.out.println("Item add failed");
				}
			}
			s = null;
		}
	}
	
	public void removeUserMenu() {
		String input = null;
		boolean quit = false;
		User u = null;
		
		do{
			System.out.println("Enter the username of the user to be removed, or 0 to cancel");
			System.out.println("----------------");
			
			input = getInput();
			
			if(input.equals("0")){
				quit = true;
			}
			else{
				try{
					u = getUserByUsername(input);
				}
				catch(Exception e){
					System.out.println("User not found");
				}
				
				if(u != null){
					System.out.println("User found");
					allUsers.remove(u);
					System.out.println("User removed");
					quit = true;
				}

				else{
					System.out.println("User not found");
				}
			}
				
		}
		while(!quit);
	}
	
	/**
	 * 
	 * Asks the user for the base attributes for creating an instance of the User
	 * class. These are: username, password, firstname, and surname. Will not go 
	 * any further if the username submitted is already in the system.
	 * 
	 * @return String[] All of those details in an array 
	 */
	public String[] getUserDetails(){
		String[] details = new String[10]; 
		
		details[0] = askFor("username");
		
		if(getUserByUsername(details[0]) != null){
			System.out.println("User with the username " + details[0] +" already exists");
			return null;
		}
		
		details[1] = askFor("password");
		details[2] = askFor("first name");
		details[3] = askFor("surname");
		
		return details;
	}
	
	/**
	 * Menu system to add a user
	 * 
	 * @return void
	 */
	public void addUserMenu() {
		String input = null;
		boolean quit = false;
		String[] details = null;

		do{
			System.out.println("Select type of user to add");
			System.out.println("----------------");
			System.out.println("1. Administrator");
			System.out.println("2. Casual User");
			System.out.println("3. Full Member");
			System.out.println("4. Staff Member");
			System.out.println("0. Cancel");
			System.out.println("\nEnter an option");
			details = null;
			
			try{
				input = in.readLine();
			}
			catch (IOException e){
				System.err.println("Error : " + e);
			}
			
			switch (input){ 
			case "1":
				System.out.println("Create administrator user");
				String[] adminParams = {"username","password","first name", "surname", "staff ID"};
				
				details = getUserDetails();
				
				if(details == null){
					break;
				}
				
				for(int i = 4; i < adminParams.length; i++){
					details[i] = askFor(adminParams[i]);
					if(details[i].equals("")){
						System.out.println("User creation failed");
						break;

					}
				}
				
				
				try{
					allUsers.add(new Administrator(details[0], details[1], 
							details[2], details[3], details[4]));
				}
				catch(Exception e){
					break;
				}
				
				System.out.println("User created successfully");
				
				break;
			case "2":
				System.out.println("Create casual user");
				String casual[] = {"username","password","first name", "surname", "address", "town", "postcode", "date of birth (format dd/MM/yy"};
				
				details = getUserDetails();
				
				if(details == null){
					break;
				}
				
				for(int i = 4; i < casual.length; i++){
					details[i] = askFor(casual[i]);
					if(details[i].equals("")){
						System.out.println("User creation failed");
						break;

					}
				}
				
				
				try{
					allUsers.add(new CasualUser(details[0], details[1], 
							details[2], details[3], details[4], details[5], 
							details[6], details[7]));
				}
				catch(Exception e){
					System.out.println("User creation failed");
					break;
				}
				
				System.out.println("User created successfully");
				
				break;
			case "3":
				System.out.println("Create full member");
				String full[] = {"username","password","first name", "surname", "address", "town", "postcode", "date of birth (format dd/MM/yyyy)"};

				details = getUserDetails();
				
				if(details == null){
					break;
				}
				
				for(int i = 4; i < full.length; i++){
					details[i] = askFor(full[i]);
					if(details[i].equals("")){
						System.out.println("User creation failed");
						break;

					}
				}
				
				try{
					allUsers.add(new FullMember(details[0], details[1], 
							details[2], details[3], details[4], details[5], 
							details[6], details[7]));
				}
				catch(Exception e){
					System.out.println("User creation failed");
					break;
				}
				
				System.out.println("User created successfully");
				
				break;
			case "4":
				System.out.println("Create staff member");
				String staff[] = {"username","password","first name", "surname", "staff ID", "staff email", "staff telephone extension"};

				details = getUserDetails();
				
				if(details == null){
					break;
				}
				
				for(int i = 4; i < staff.length; i++){
					details[i] = askFor(staff[i]);
					if(details[i].equals("")){
						System.out.println("User creation failed");
						break;

					}
				}
				
				try{
					allUsers.add(new StaffMember(details[0], details[1], 
							details[2], details[3], details[4], details[5], 
							details[6]));
				}
				catch(Exception e){
					System.out.println("User creation failed");
					break;
				}
				
				System.out.println("User created successfully");
				
				break;
			case "0":
				quit = true;
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
		while(!quit);
	}
	
	/** 
	 * Gets user input
	 * @return
	 */
	public String getInput(){
		String temp = null;
		try{
			temp = in.readLine();
		}
		catch (Exception e){
			System.err.println("Error : " + e);
		}
		return temp;
	};
		
	/**
	 * Asks the users for a thing
	 * @param thing to ask the user for
	 * @return user input String
	 */
	public String askFor(String thing){
		System.out.println("Please enter " + thing);
		return getInput();
	}

	/**
	 * Displays all the users
	 * 
	 * @return void
	 */
	public void viewUsers() {
		Iterator<User> uIt = allUsers.iterator();
		
		System.out.println("--------------------------");
		System.out.println("View All Users");
		System.out.println("--------------------------");
		
		while(uIt.hasNext()){
			System.out.println(uIt.next().toString());
			System.out.println("--------------------------");
		}
		
		System.out.println("Press enter to continue");
		try{
			in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}	
	}

	/**
	 * Shows all the fines a user may have, works off of a userID rather than a user object
	 * @param userID
	 */
	public void checkFines() {
		Borrower b = isBorrower(activeUser);
		boolean quit = false;
		if(b.getFine() > 0){
			while(!quit){
				System.out.println("You have fines of £" + b.getFine());
				System.out.println("1. Pay in full");
				System.out.println("0. Quit");
				
				String input = getInput();
				
				switch (input){ 
				case "1":
					b.setFine(0);
					b.setIsSuspended(false);
					quit = true;
					break;
				case "0":
					quit = true;
					break;
				default:
					System.out.println("Invalid input");
					break;
				}
			}

		}
		else{
			System.out.println("User has no fines");
		}
	}
	
	/**
	 * Users a userID to return a user object from the users ArrayList
	 * @param String userID
	 * @return User
	 */
	public User getUserByID(String userID){
		
		Iterator<User> uIt = allUsers.iterator();
		
		while(uIt.hasNext()){
			User curUser = uIt.next();
			
			if(curUser.getUserID().equals(userID)){
				return curUser;
			}
		}

		return null;
	}
	
	/**
	 * 
	 * Users a username to return a user object from the users ArrayList
	 * 
	 * @param String username
	 * @return User
	 */
	public User getUserByUsername(String username){
		
		Iterator<User> uIt = allUsers.iterator();
		
		while(uIt.hasNext()){
			User curUser = uIt.next();
			
			if(curUser.getUsername().equals(username)){
				return curUser;
			}
		}

		return null;
	}
	
	/**
	 * checks if the user is a borrower, if so returns the user as a borrower
	 * @return Borrower object
	 */
	public Borrower isBorrower(User u){
		if(u instanceof Borrower){
			return (Borrower) u;
		}
		
		return null;
	}
	
	/**
	 * Basic isNumeric helper function for a string 
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	/**
	 * Gets all the reservations from a user, if they have any
	 * @param user
	 */
	public void showReservations(User user){
		if(activeUser.getUserReservations() == null){
			System.out.println("-------------------------");
			System.out.println("User has no reservations." + activeUser.getUsername());
			System.out.println("-------------------------");
		}
		else{
			Iterator<Reservation> it = user.getUserReservations().iterator();
			System.out.println("-------------------------");
			System.out.println("Reservations for " + user.getUsername());
			System.out.println("-------------------------");
			while(it.hasNext()){
				Reservation cur = it.next();
				System.out.println(cur.toString());
				System.out.println("----------------");	
			}	
		}
	}
	
	/**
	 * Overloaded showReservations(User user) to run off of the active user instead
	 * 
	 * @return void
	 */
	public void showReservations(){
		if(activeUser.getUserReservations() == null){
			System.out.println("-------------------------");
			System.out.println("User has no reservations.");
			System.out.println("-------------------------");
		}
		else{
			Iterator<Reservation> it = activeUser.getUserReservations().iterator();

			System.out.println("-------------------------");
			System.out.println("Reservations for " + activeUser.getUsername());
			System.out.println("-------------------------");
			while(it.hasNext()){
				Reservation cur = it.next();
				System.out.println(cur.toString());
				System.out.println("----------------");	
			}	
		}
		
	}
	
	/**
	 * Allows a full or staff member to create a reservation on an item
	 * 
	 * @return void
	 */
	public void requestReservation() {
		String input = null;
		boolean quit = false;
		do{
			System.out.println("Reserve an item");
			System.out.println("---------------");
			System.out.println("Please enter the stockID of the item you want to reserve, or to cancel, just enter nothing");
			try{
				input = in.readLine();
				
				if(input.equals("")) 
					quit = true;
				else{
					Iterator<Stock> stockIt = allStock.iterator();
					boolean found = false;
					while(stockIt.hasNext() && !found){
						Stock curItem = stockIt.next();
						
						if(curItem.getStockID().equals(input)){
							found = true;
							System.out.println("Stock item found");
							System.out.println("-----------------");
							System.out.println(curItem.toString());
							System.out.println("-----------------");
							System.out.println("Reserve this item? (y for yes, to cancel enter anything else)");
							
							if(in.readLine().equals("y")){
								
								activeUser.getUserReservations().add(new Reservation(input));
								
								
								System.out.println("Reservation created\n");
								
								quit = true;
							}
							else{
								System.out.println("Reservation of item cancelled");
							}
							
						}
					}
					
					if(!found){
						System.out.println("Item not found");
					}
				}
			}
			catch (IOException e){
				System.err.println("Error: " + e);
			}		
		}
		while(!quit);
	}

	
	/**
	 * Changes the password for the login user, requires password confirmation
	 * 
	 * @return void
	 */
	public void changePassword() {
		String oldPassword = null;
		String confirmPassword = null;
		String newPassword = null;
		
		boolean finished = false;
		while(!finished){
			System.out.println("Enter new password");
			newPassword = getInput();
	
			System.out.println("Confirm new password");
			confirmPassword = getInput();
			
			if(confirmPassword.equals(newPassword) && !newPassword.equals(null) &&!confirmPassword.equals(null)){
				System.out.println("New password matches");
				int attempts = 3;
				
				while(attempts > 0 && !finished){

					System.out.println("Enter old password");
					System.out.println(attempts + " attempts remaining");
					
					oldPassword = getInput();
					
					if(oldPassword.equals(this.activeUser.getPassword())){
						activeUser.setPassword(newPassword);
						finished = true;
						System.out.println("Password changed successfully");
					}
					else{
						attempts--;
						
						if(attempts < 1){
							System.out.println("Failed try again");
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * Allows the logged in user to edit their general user details - username, 
	 * first name, and surname. Password is handled by the changePassword() 
	 * method of this class
	 * 
	 * @return void
	 */
	public void editUser(){
		boolean quit = false;
		
		while(!quit){
			System.out.println("Select a field to change about your user account");
			System.out.println("1. Username");
			System.out.println("2. First name");
			System.out.println("3. Surname");
			System.out.println("0. Quit");
			String input = askFor(" an option");
			
			switch(input){
			case "1":
				input = askFor("your new username");
				if(input.length() > 0){
					activeUser.setUsername(input);
					System.out.println("Edit made");
				}
				else
				{
					System.out.println("Input invalid");
				}
				break;
			case "2":
				input = askFor("your new username");
				if(input.length() > 0){
					activeUser.setFirstname(input);
					System.out.println("Edit made");

				}
				else
				{
					System.out.println("Input invalid");
				}
				break;
			case "3":
				input = askFor("your new username");
				if(input.length() > 0){
					activeUser.setSurname(input);					
					System.out.println("Edit made");

				}
				else
				{
					System.out.println("Input invalid");
				}
				break;
			case "0": 
				quit = true;
				break;
			default:
				System.out.println("Input invalid");
	
			}

		}
		
	}
	
	/**
	 * Shows the catalogue of all stock in the system
	 * 
	 * @return void
	 */
	public void viewCatalogue() {
		Iterator<Stock> stockIt = allStock.iterator();
		
		System.out.println("--------------------------");
		System.out.println("Stock Catalogue");
		System.out.println("--------------------------");
		
		while(stockIt.hasNext()){
			System.out.println(stockIt.next().toString());
			System.out.println("--------------------------");
		}
		
		System.out.println("Press enter to continue");
		try{
			in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}	
	}
	
	/**
	 * Search all the items by name and list an item if found
	 * 
	 * @return void
	 */
	public void searchCatalogue(){
		
	}
	
	/**
	 * show the logins for a user 
	 * @param u
	 */
	public void viewLogin(User u){
		System.out.println("Logins for user " + u.getUsername());
		System.out.println("-------------------------");
		Iterator<LoginRecord> it = u.getLoginRecords().iterator();
		
		while(it.hasNext()){
			LoginRecord l = it.next();
			System.out.println(l.toString());
			System.out.println("-------------------------");	
		}
	}
	
	/**
	 * Login - asks the user for a username and password until they are successfully logged in to the system
	 * 
	 * @return User if login was successful returns the user object, otherwise returns null
	 */
	public User login(){
		User curUser = null;
		User foundUser = null;
		String username = null;
		String password = null;
		Iterator<User> userIt = allUsers.iterator();
				
		/*
		 * get username and password from the user
		 */
		System.out.println("Log in to Glencaldy Learning Center");
		System.out.println("Please enter your username");
		try{
			username = in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}
		System.out.println("Please enter your password");
		
		try{
			password = in.readLine();
		}
		catch (IOException e){
				System.err.println("Error : " + e);
		}
		
		/*
		 * runs through the iterator until the user is found or all users have been checked
		 */
		while(userIt.hasNext() && foundUser == null){
			curUser = userIt.next();
			
			if(username.equals(curUser.getUsername())){
				
				foundUser = curUser;
				if(password.equals(curUser.getPassword())){
					System.out.println("\n\nLogging in as '" + curUser.getUsername() + "'\n\n");
					return curUser;
				}
				else{
					System.out.println("Login failed - incorrect password\n\n");	
				}
			}
		}
		
		if(foundUser == null){
			System.out.println("Login failed - user not found");
		}
		
		return null;
		
	}
	
	/**
	 * Welcome message for the system
	 */
	public void welcomeMessage(){
		System.out.println("--------------------------------------\n");
		System.out.println("Welcome to Glencaldy Learning Center\n");
		System.out.println("--------------------------------------\n");
	}
	
	/**
	 * Populates the all users ArrayList from the serialised file allUsers
	 */
	@SuppressWarnings("unchecked")
	public void populateUsers(){
//        try{
//            FileInputStream fis = new FileInputStream("allUsers");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            this.allUsers = (ArrayList<User>) ois.readObject();
//            ois.close();
//            fis.close();
//        }
//        catch(Exception e){
//             e.printStackTrace();
//             return;
//         };
		allUsers.add(new Administrator("admin", "admin", "admin", "admin", "admin"));
	}
	
	/**
	 * Populates the allStock ArrayList
	 */
	public void populateStock(){
		allStock.add(new CD("Sonic Highways", 7.99, "Sony",
				40, "Normal", "Audio", "Foo Fighters"));
		allStock.add(new Video("Sharknado", 99.99, "Fox",
				40, "Normal", "Thriller", "BluRay"));
		allStock.add(new Book("Life of Pi", 8.99, "Penguin",
				"Novel", "0544115287", 300, "Yann Martel"));
		allStock.add(new Journal("Some Journal", 2.5, "Oxford",
				"Science", "123456789", 240 , "Issue 2", "1/1/2000"));
	}
}