/**
 * Stores the name, category, price and rating of a product
 */
public class Product {

	private String name;
	private String category;
	private int price;
	private float rating;

	/**
	 * Constructs a Product with a name, category, price and rating. 
	 * 
	 * @param name name of product
	 * @param category category of product
	 * @param price price of product in $ 
	 * @param rating rating of product out of 5
	 */
	public Product(String name, String category, int price, float rating){

		if ((name == null) || (category == null) || (price <= 0) || (rating < 0.0)) {

			throw new IllegalArgumentException();

		}

		this.name = name;
		this.category = category;
		this.price = price;
		this.rating = rating;

	}

	/** 
	 * Returns the name of the product
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}

	/** 
	 * Returns the category of the product
	 * @return the category
	 */
	public String getCategory(){
		return this.category;
	}

	/** 
	 * Returns the price of the product
	 * @return the price
	 */
	public int getPrice(){
		return this.price;
	}

	/** 
	 * Returns the rating of the product
	 * @return the rating
	 */
	public float getRating(){
		return this.rating;
	}

	/** 
	 * Returns the Product's information in the following format: <NAME> 
	 * [Price:$<PRICE> Rating:<RATING> stars]
	 */
	public String toString(){

		String temp = (this.name + " [Price:$" + this.price + " Rating:" +
				this.rating + " stars]");

		return temp;
	}

}
