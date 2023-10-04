package nodeTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	//private int size = 0;

	public IntBST() {	}

	public int size() {
		return size;
	}

	public void setSize(int s) { size = s; }
	
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */
	
	  public void print(Node<Integer> n){ print ("", n); }
	  
	  public void print(String prefix, Node<Integer> n){
		  if (n != null){
			  print(prefix + "       ", right(n));
			  System.out.println (prefix + ("|-- ") + n.getElement());
			  print(prefix + "       ", left(n));
		  }
	  }
	  
	  
	  public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	
	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2 
		if (left(n) != null) 
			snapshot.add(left(n)); 
		if (right(n) != null)
			snapshot.add(right(n)); return snapshot; 
	}
	
	public int height(Node<Integer> n) throws IllegalArgumentException { 
		if (isExternal(n)) { return 0; } 
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n)) h = Math.max(h, height(c)); return h + 1; 
	}

	// Recursively generates an integer binary search tree from an array of ints.
	// Input is an array of ints. Length of array a is 2^n - 1 as defined in the doc.
	// Output is the Int BST generated from array a.
	public static IntBST makeBinaryTree(int[] a){
		// Set the midpoint of the array as an int.
		// This will be the index for the root for each recursive iteration.
		// e.g. a = [10, 20, 30] -> midpoint = 1, which will refer to 20
		// We use integer division here for this purpose, since arrays index from 0.
	  	int midpoint = a.length / 2;

	  	// Recursion condition. This method only accepts arrays that are 2^n -1
		// So whenever length of the incoming array is larger than 1, we can build sub-trees on both sides.
		if(a.length > 1)
	 	{
			 // Initialize a new IntBST
			IntBST ibst = new IntBST();
			// Root is the midpoint element of the array.
			ibst.addRoot(a[midpoint]);

			// Use midpoint variable to split incoming array into left (first half) and right (second half)
			int[] larray = Arrays.copyOfRange(a, 0, midpoint);
			int[] rarray = Arrays.copyOfRange(a, midpoint + 1, a.length);
			// Recursively generate child trees, left and right with larray/rarray
			IntBST lt = makeBinaryTree(larray);
			IntBST rt = makeBinaryTree(rarray);

			// Attach child trees to the root.
			ibst.attach(ibst.root, lt, rt);
			// Return the tree.
			return ibst;
		}
	  	else
		{
			// When array length == 1, we have reached leaf generation and recursion ends.
			// New Tree
			IntBST ibst = new IntBST();
			// Add root.
			ibst.addRoot(a[0]);
			// Return the 1-depth tree.
			return ibst;
		}
	}

}
