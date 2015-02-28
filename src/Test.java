
public class Test {

	public static void main(String[] args) {
		DLinkedList<Integer> list = new DLinkedList<Integer>();
		
		if (list.isEmpty()) System.out.println("Empty");
		list.add(0);
		list.add(1);
		list.add(2);
		if (!list.isEmpty()) System.out.println("Not Empty");
	
		list.add(3,3);
		list.add(1,10);
		try {
			list.add(123, 3);
		}
		catch (IllegalArgumentException ex) {
			System.out.println("Oops, you broke it.");
		}
		
		int i = 0;
	
		
	
	
	
	}
	

}
