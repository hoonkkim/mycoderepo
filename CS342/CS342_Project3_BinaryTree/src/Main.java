// Main: Driver for running the right methods with Dracula text file
// Import Textparser to parse text
import edu.bu.met.cs342a1.TextParser;

public class Main {

    public static void main(String[] args) {
	// Instantiate TextParser and run
        TextParser tp = new TextParser();
        if (!tp.openFile("pg345.txt")) {
            System.out.println("Error opening file");
            System.exit(0);
        }
        // Instantiate new Binary Tree
        BinaryTree bt = new BinaryTree();

        // Fill out binary tree with Add method with a for loop that goes through all parsed words via parser.
        for(String word = tp.getNextWord(); word != null; word = tp.getNextWord()) {
           bt.add(word);
        }

        // Answer questions in order
        // Occurrence of Transylvania
        System.out.println("transylvania: "+bt.containsWord("transylvania")+" times");
        // Occurrence of harker
        System.out.println("harker: "+bt.containsWord("harker")+" times");
        // Occurrence of renfield
        System.out.println("renfield: "+bt.containsWord("renfield")+" times");
        // Occurrence of vampire
        System.out.println("vampire: "+bt.containsWord("vampire")+" times");
        // Occurrence of expostulate
        System.out.println("expostulate: "+bt.containsWord("expostulate")+" times");
        // Occurrence of fang
        System.out.println("fang: "+bt.containsWord("fang")+" times");

        // Depth of Tree (distance from root to farthest leaf
        System.out.println("depth of the tree is: "+bt.maxDepth(bt.root));
        // Unique word count (= node count) of tree
        System.out.println("Unique Word Count of the tree is: "+bt.nodeCount(bt.root));
        // Root word (which will be the first one because we are adding to the tree in the order words appear)
        System.out.println("root of the tree is: "+bt.root.word);
        // Deepest word of the tree
        System.out.println("Deepest Word of the tree is: "+bt.DeepestWord(bt.root));
        // Total count of words (Sum product of Unique Leaves and Counter variable for each leaf(word)
        System.out.println("Total Word Count of the tree is: "+bt.wordCount(bt.root));
        // Most frequent word (traverse tree and update max counter variable and the reference to the word. return word)
        System.out.println("Most Frequent Word is: "+bt.mostFrequentWord(bt.root)+" with "+bt.containsWord(bt.mostFrequentWord(bt.root))+" occurrences");

        // Pre Order Traversal
        System.out.println("Pre Order Traversal");
        bt.preOrder(bt.root, 0);
        System.out.println("");
        // Post Order Traversal
        System.out.println("Post Order Traversal");
        bt.postOrder(bt.root, 0);
        System.out.println("");
        // In Order Traversal
        System.out.println("In Order Traversal");
        bt.inOrder(bt.root, 0);
    }

 }

