import LinkedList.DoubleLinkNode;
import LinkedList.DoublyLinkedList;

public class Hw2_p3 {

	// implement reverse method
	// you may want to write a separate method with additional parameters, which is recursive
	// This method takes in a Doubly Linked List and reverses its nodes' order. There is no return value.
	public static void reverse(DoublyLinkedList<Integer> intList) {
	// The node reversal reverses all pointers between nodes.
	reverseNode(intList.getTrailer());
	// However, the DoublyLinkedList's Header & Trailer are not reversed because they are assigned at the linked list level
	// Temp Node to hold the old header (= new Trailer)
	DoubleLinkNode<Integer> temp = intList.getHeader();
	// Set the old trailer to be the new header
	intList.setHeader(intList.getTrailer());
	// Set the new header to be the old trailer
	intList.setTrailer(temp);

	// PrintLns for Debugging
	// System.out.println(intList.getHeader().getNext().getElement());
	// System.out.println(intList.getTrailer().getPrev().getElement());

	}

	// This method swaps the pointers for each node within a DoubleLinkedList
	// It takes in a DoubleLinkNode and does not return anything.
	public static void reverseNode(DoubleLinkNode<Integer> intNode) {

		if (intNode.getPrev() == null) {
			// If we are here, it means we need to reverse the header.
			// The header will become the new trailer, so its Next needs to become its prev.
			intNode.setPrev(intNode.getNext());
		}

		else
		// Recursive swaps
		{
			// Debugging Printouts
			// System.out.println("current node:"+intNode.getElement());
			// Set up a temporary node to store the previous node.
			DoubleLinkNode<Integer> tempNode = intNode.getPrev();
			// Reverse Previous & Next.
			intNode.setPrev(intNode.getNext());
			intNode.setNext(tempNode);

			// More text for Debugging
			// if(!(intNode.getPrev() == null)) {System.out.println("Prev node:"+intNode.getPrev().getElement());}
			// System.out.println("Next node:"+intNode.getNext().getElement());
			// Run the same method recursively with the next (formerly previous node from the one we just swapped)
			reverseNode(intNode.getNext());
		}
	}

	
	// use the main method for testing
	// test with arrays of different lenghts
	public static void main(String[] args) {

		
		DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
		
		int[] a = {10, 20, 30, 40, 50};
		for (int i=0; i<a.length; i++) {
			intList.addLast(a[i]);
		}
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());
		
		intList = new DoublyLinkedList<>();
		int[] b = {10, 20, 30, 40, 50, 60};
		for (int i=0; i<b.length; i++) {
			intList.addLast(b[i]);
		}
		System.out.println();
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());

	}

}
