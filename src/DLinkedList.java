///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             DLinkedList.java
// Semester:         CS367 Spring 2015
//
// Author:           Thomas Whitburn
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

/**
 * Implements the List ADT using a doubly-linked list.
 *
 * <p>Bugs: No known bugs
 *
 * @author Thomas Whitburn, Haomin Li
 */
public class DLinkedList<E> implements ListADT<E> {


	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;

	/**
	 * Constructs an empty doubly-linked list with a head and a tail reference 
	 * both pointing to null.
	 */
	public DLinkedList() {
		head = null;
		tail = null;
		numItems = 0;
	}

	/**
	 * Adds item to the end of the List.
	 * 
	 * @param item the item to add
	 * @throws IllegalArgumentException if item is null 
	 */
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

	/**
	 * Adds item at position pos in the List, moving the items originally in 
	 * positions pos through size() - 1 one place to the right to make room.
	 * 
	 * @param pos the position at which to add the item
	 * @param item the item to add
	 * @throws IllegalArgumentException if item is null 
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater 
	 * than size()
	 */
	@Override
	public void add(int pos, E item) {
		if ((item == null) || (pos < 0) || (pos > numItems)) {
			throw new IllegalArgumentException();
		}

		Listnode<E> newnode = new Listnode<E>(item);

		//Special case: empty list 
		if (head == null) {
			head = newnode;
			tail = newnode;
		}

		//Special case: trying to add at the front
		else if (pos == 0) {

			newnode.setNext(head);
			head.setPrev(newnode);
			head = newnode;
		}

		//General case:
		else {

			Listnode<E> temp = head;

			for (int i = 0; i < pos-1; i++) {
				temp = temp.getNext();
			}

			newnode.setNext(temp.getNext());
			newnode.setPrev(temp);
			temp.setNext(newnode);
			if (newnode.getNext() != null) {
				newnode.getNext().setPrev(newnode);	
			}
			else {
				tail = newnode;
			}

		}

		numItems++;

	}

	/**
	 * Returns true iff item is in the List (i.e., there is an item x in the List 
	 * such that x.equals(item))
	 * 
	 * @param item the item to check
	 * @return true if item is in the List, false otherwise
	 */
	@Override
	public boolean contains(E item) {
		if (item == null) throw new IllegalArgumentException();

		Listnode<E> temp = head; 

		//Special case: empty list
		if(numItems == 0){
			return false;
		}
		//General case:
		else {
			do {
				if (temp.getData().equals(item)) {
					return true;
				}
				temp = temp.getNext();
			} while (temp != null);
		}
		return false;

	}

	/**
	 * Returns the item at position pos in the List.
	 * 
	 * @param pos the position of the item to return
	 * @return the item at position pos
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
	 * or equal to size()
	 */
	@Override
	public E get(int pos) {

		if ((pos < 0) || (pos > numItems-1)) throw new IllegalArgumentException();

		Listnode<E> temp = head;

		for (int i = 0; i < pos; i++) {
			temp = temp.getNext();
		}

		return temp.getData();

	}

	/**
	 * Returns true iff the List is empty.
	 * 
	 * @return true if the List is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {

		if (numItems == 0) return true;
		else return false;

	}

	/**
	 * Removes and returns the item at position pos in the List, moving the items 
	 * originally in positions pos+1 through size() - 1 one place to the left to 
	 * fill in the gap.
	 * 
	 * @param pos the position at which to remove the item
	 * @return the item at position pos
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
	 * or equal to size()
	 */
	@Override
	public E remove(int pos) {

		if ((pos < 0) || (pos >= numItems)) throw new IllegalArgumentException();

		Listnode<E> temp = null;
		//special case: removing the first item
		if(pos == 0){
			temp = head;
			head.getNext().setPrev(null);
			head = head.getNext();
		}

		//special case: removing the last item
		else if (pos == numItems-1){
			temp = tail;
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
		}

		//general case
		else {
			temp = head;
			for (int i = 0; i < pos; i++) {
				temp = temp.getNext();
			}
			temp.getNext().setPrev(temp.getPrev());
			temp.getPrev().setNext(temp.getNext());

		}

		numItems--;
		return temp.getData();
	}

	/**
	 * Returns the number of items in the List.
	 * 
	 * @return the number of items in the List
	 */
	@Override
	public int size() {

		return numItems;

	}


}
