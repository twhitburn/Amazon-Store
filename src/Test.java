
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
		
		list.remove(1);
		list.remove(0);
		list.remove(2);
		
		if(list.contains(1)){
			System.out.println("Contains 1");
		}
		if(list.contains(4)){
			System.out.println("Contains 4");
		}
		
		list.add(0,0);
		int asdf = list.get(1);
		System.out.println(asdf);
		asdf = list.get(0);
		System.out.println(asdf);
		
		int i = 0;
		
		
	
	
	
	}
	

}
