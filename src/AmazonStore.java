///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Amazon Store
// Files:            DLinkedList.java, Product.java, User.java, 
//                   InsufficientCreditException.java,
// Semester:         CS367 Spring 2015
//
// Author:           Thomas Whitburn
// Email:            twhitburn@wisc.edu
// CS Login:         whitburn
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Haomin Li
// Email:            hli256@wisc.edu
// CS Login:         haomin
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class with menu interface that allows you to login and manipulate the 
 * store and products.
 *
 * <p>Bugs: No known bugs
 *
 * @author Thomas Whitburn, Haomin Li
 */
public class AmazonStore {

	//Store record of users and products
	private static ListADT<Product> products = new DLinkedList<Product>();
	private static ListADT<User> users = new DLinkedList<User>();
	private static User currentUser = null;//current user logged in

	//scanner for console input
	public static final Scanner stdin= new Scanner(System.in);


	/**
	 * Main program method that handles all user operations.
	 *
	 * @param args command line inputs
	 */
	public static void main(String args[]) throws FileNotFoundException {


		//Populate the two lists using the input files: Products.txt User1.txt 
		if (args.length < 2) {
			System.out.println("Usage: java AmazonStore [PRODUCT_FILE] "
					+ "[USER1_FILE] [USER2_FILE] ...");
			System.exit(0);
		}

		//load store products
		loadProducts(args[0]);

		//load users one file at a time
		for(int i=1; i<args.length; i++)
			loadUser(args[i]);

		//User Input for login
		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter username : ");
			String username = stdin.nextLine();
			System.out.print("Enter password : ");
			String passwd = stdin.nextLine();

			if(login(username, passwd)!=null)
			{
				//generate random items in stock based on this user's wish list
				ListADT<Product> inStock=currentUser.generateStock();
				//show user menu
				userMenu(inStock);
			}
			else
				System.out.println("Incorrect username or password");

			System.out.println("Enter 'exit' to exit program or anything else "
					+ "to go back to login");
			if(stdin.nextLine().equals("exit"))
				done = true;
		}

	}

	/**
	 * Tries to login for the given credentials. Updates the currentUser if 
	 * successful login
	 * 
	 * @param username name of user
	 * @param passwd password of user
	 * @returns the currentUser 
	 */
	public static User login(String username, String passwd){
		if ((username == null) || (passwd == null)){
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).checkLogin(username, passwd)){
				currentUser = users.get(i);
				return currentUser;
			}
		}
		return null;
	}

	/**
	 * Reads the specified file to create and load products into the store.
	 * Every line in the file has the format: <NAME>#<CATEGORY>#<PRICE>#<RATING>
	 * Create new products based on the attributes specified in each line and 
	 * insert them into the products list
	 * Order of products list should be the same as the products in the file
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 * @throws FileNotFoundException 
	 */
	public static void loadProducts(String fileName) 
			throws FileNotFoundException {
		Scanner fileIn = null;
		try{
			fileIn = new Scanner(new File(fileName));
			String delims = "[#]";
			Product tempProduct;
			while (fileIn.hasNext()){
				String s = fileIn.nextLine();
				String[] tokens = s.split(delims);
				String name = tokens[0];
				String category = tokens[1];
				int price = Integer.valueOf(tokens[2]);
				float rating = Float.parseFloat(tokens[3]);
				tempProduct =  new Product(name, category, price, rating);
				products.add(tempProduct);		
			}
			fileIn.close();
		} catch (FileNotFoundException ex){
			System.out.println("Error: Cannot access file");
		}
	}

	/**
	 * Reads the specified file to create and load a user into the store.
	 * The first line in the file has the format:<NAME>#<PASSWORD>#<CREDIT>
	 * Every other line after that is a name of a product in the user's wishlist, format:<NAME>
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 * @throws FileNotFoundException 
	 */
	public static void loadUser(String fileName) throws FileNotFoundException{
		Scanner fileIn = null;
		try{
			fileIn = new Scanner(new File(fileName));
			String delims1 = "[#]";
			User tempUser;
			//Read in first line for creating user.
			String userInfo = fileIn.nextLine();
			String[] tokens1 = userInfo.split(delims1);
			String username = tokens1[0];
			String passwd = tokens1[1];
			int credit = Integer.valueOf(tokens1[2]);
			tempUser = new User(username, passwd, credit);
			users.add(tempUser);
			//Read the products for user wishlist.
			while(fileIn.hasNext()){
				String productName = fileIn.nextLine();
				for (int i = 0; i < products.size(); i++){
					if (products.get(i).getName().equals(productName)){
						tempUser.addToWishList(products.get(i));
					}
				}

			}
		} catch (FileNotFoundException ex){
			System.out.println("Error: Cannot access file");
		}
	}

	/**
	 * See sample outputs
	 * Prints the entire store inventory formatted by category
	 * The input text file for products is already grouped by category, use the same order as given in the text file 
	 * format:
	 * <CATEGORY1>
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * ...
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * 
	 * <CATEGORY2>
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * ...
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 */
	public static void printByCategory(){
		String tempCate = null;
		String tempName = null;
		int tempPrice = 0;
		float tempRating = 0;
		for (int i = 0; i < products.size(); i++) {
			String s = products.get(i).getCategory();
			if (!s.equals(tempCate)) {
				tempCate = s;
				System.out.println("\n" + tempCate + ":");
			}
			tempName = products.get(i).getName();
			tempPrice = products.get(i).getPrice();
			tempRating = products.get(i).getRating();
			System.out.println(tempName + " [Price:$" + tempPrice + " Rating:"
					+ tempRating + " stars]");
		}
	}


	/**
	 * Interacts with the user by processing commands
	 * 
	 * @param inStock list of products that are in stock
	 */
	public static void userMenu(ListADT<Product> inStock){

		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter option : ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) 
			{
				String[] commands = input.split(":");//split on colon, because names have spaces in them
				if(commands[0].length()>1)
				{
					System.out.println("Invalid Command");
					continue;
				}
				switch(commands[0].charAt(0)) {
				case 'v':
					try {
						if (commands.length != 2)
							throw new IllegalArgumentException();
						if (commands[1].equals("all")) {
							printByCategory();
						}
						else if (commands[1].equals("wishlist")) {
							if (currentUser == null) {
								System.out.println("No user logged in. Log in "
										+ "first.");
							}
							currentUser.printWishList(System.out);

						}
						else if (commands[1].equals("instock")){
							for (int i = 0; i < inStock.size(); i++){
								System.out.println(inStock.get(i).toString());
							}
						}
					}catch(IllegalArgumentException ex) {
						break;
					}
					break;

				case 's':
					try {
						if (commands.length != 2)
							throw new IllegalArgumentException();
						if (commands[1] == null) {
							throw new IllegalArgumentException();
						}
						String query = commands[1];
						for (int i = 0; i < products.size(); i++) {
							boolean flag1 = false;
							String[] nameString = products.get(i).getName().split(" ");
							for (int j = 0; j < nameString.length; j++) {
								if (nameString[j].equals(query)){
									flag1 = true;
								}
							}
							if (flag1){
								System.out.println(products.get(i).toString());
							}		
						}
					}catch(IllegalArgumentException ex) {
						break;
					}
					break;
				case 'a':
					try {
						if (commands.length != 2)
							throw new IllegalArgumentException();
						if (commands[1] == null) {
							throw new IllegalArgumentException();
						}

						String productName = commands[1];
						boolean added = false;
						for (int i = 0; i < products.size(); i++) {
							if (products.get(i).getName().equals(productName)) {
								currentUser.addToWishList(products.get(i));
								System.out.println("Added to wishlist");
								added = true;
							}
						}
						if (!added) System.out.println("Product not found.");
					}catch(IllegalArgumentException ex) {
						break;
					}

					break;

				case 'r':
					try {
						if (commands.length != 2)
							throw new IllegalArgumentException();
						if (commands[1] == null) {
							throw new IllegalArgumentException();
						}

						String productName2 = commands[1];
						if ((currentUser.removeFromWishList(productName2)) == null) {
							System.out.println("Product not found.");
						}
					}catch(IllegalArgumentException ex) {
						break;
					}
					break;

				case 'b':


					for (int i = 0; i < inStock.size(); i++) {
						String productName3 = inStock.get(i).getName();

						try {
							if (currentUser.buy(productName3)) {
								System.out.println("Bought " + productName3);

							}
						}catch (InsufficientCreditException ex) {
							System.out.println("Insufficient funds for " + productName3);
						}

					}
					break;

				case 'c':

					System.out.println("$" + currentUser.getCredit());

					break;

				case 'l':
					done = true;
					System.out.println("Logged Out");
					break;

				default:  //a command with no argument
					System.out.println("Invalid Command");
					break;
				}
			}
		}
	}

}
