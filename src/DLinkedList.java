
public class DLinkedList<E> implements ListADT<E> {


	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;

	public DLinkedList() {
		head = null;
		tail = null;
		numItems = 0;
	}


	@Override
	public void add(E item) {

		if (item == null) throw new IllegalArgumentException();

		Listnode<E> newnode = new Listnode<E>(item);

		//Special case: empty list
		if (head == null) {
			head = newnode;
			tail = newnode;
		}
		
		//General case:
		else {
			tail.setNext(newnode);
			newnode.setPrev(tail);
			tail = newnode;
		}
		
		numItems++;

	}

	@Override
	public void add(int pos, E item) {
		if ((item == null) || (pos < 0) || (pos > numItems-1)) {
			throw new IllegalArgumentException();
		}


		// TODO 

	}

	@Override
	public boolean contains(E item) {
		if (item == null) throw new IllegalArgumentException();
		return false;
		//TODO


	}

	@Override
	public E get(int pos) {

		if ((pos < 0) || (pos > numItems-1)) throw new IllegalArgumentException();

		// TODO 
		return null;
	}

	@Override
	public boolean isEmpty() {

		if (numItems == 0) return true;
		else return false;

	}

	@Override
	public E remove(int pos) {

		if ((pos < 0) || (pos > numItems-1)) throw new IllegalArgumentException();


		// TODO 
		return null;
	}

	@Override
	public int size() {

		return numItems;

	}


}
