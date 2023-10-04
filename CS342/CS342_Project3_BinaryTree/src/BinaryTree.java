// Binary Tree: Binary Tree Class to use for the exercise

public class BinaryTree {
    // root node
    Node root;
    // Separate ref for deepest node
    Node deepestnode;
    // Reference for Depth and trackers for pre/post/in order traversals
    private int TreeDepth;
    private int preOrderTracker = 0;
    private int postOrderTracker = 0;
    private int inOrderTracker = 0;

    // Recursive add
    private Node addRecursive(Node current, String newWord) {

        if (current == null) {
            // Empty tree. Return a new node
            return new Node(newWord);
        }

        if (newWord.compareToIgnoreCase(current.word) < 0) {
            // Not how ComparetoIgnorCase is used - this is to make sure we are case agnostic
            // new word comes before current word we are looking at - go left
            current.left = addRecursive(current.left, newWord);

        } else if (newWord.compareToIgnoreCase(current.word) > 0) {
            // New word comes after current word we are looking at - go right
            current.right = addRecursive(current.right, newWord);

        } else {
            // value already exists
            // increment count
//            System.out.println("Adding to count!");
            current.count++;
            // Some text output for debugging
//            System.out.println(current.count);
            // Returns current when duplicate
            return current;
        }
        return current;
    }

    // Public method to add while hiding the recursive add
    public void add(String word) {
        root = addRecursive(root, word);
    }

    // Public method to find a word's occurrence count
    public int containsWord(String findWord) {
        return containsWordRecursive(root, findWord);
    }

    // Recursive method to find a word and return its occurrence count
    private int containsWordRecursive(Node current, String findWord) {
        if (current == null) {
            // Base Case A: Traverse the tree until we hit a null node. Word not found
//            System.out.println("Word not found!");
            return 0;
        }
        if (findWord.compareToIgnoreCase(current.word) == 0) {
            // Base Case B: Word is found. Return its count.
//            System.out.println("Word found!");
            return current.count;

        } else if (findWord.compareToIgnoreCase(current.word) < 0) {
            // Recurse to the left if the word we are looking for is earlier in alphabet
            return containsWordRecursive(current.left, findWord);
        }

        else if (findWord.compareToIgnoreCase(current.word) > 0) {
            // Recurse to the right if the word we are looking for is later in alphabet
            return containsWordRecursive(current.right, findWord);
        }
        // it should never get here
        return -1;
    }

// Find the depth of the tree
    public int maxDepth(Node root) {
        if(root == null) {
            return 0;
            // if it is null, do nothing. tree is empty
        }
        if(root.left == null && root.right == null){
            return 1;
            // If we reach a dead end (leaf == no children) - return 1 to add to depth
        }
        else{
            int l = maxDepth(root.left);
            int r = maxDepth(root.right);
            return (1 + (Math.max(l, r)));
            // if it has both children, recurse both ways then take the max depth of children + 1 as your depth

        }
    }


    private void DeepestNode(Node root, int level) {
        if (root != null) {
            // eliminate the case for empty trees
            // Recurse through tree, and whenever you go down a level you add to level (depth you are at)
            // when level is larger than treedepth (initially 0), update treedepth & deepest node reference
            // Recurse left first
            DeepestNode(root.left, ++level);
            if (level > TreeDepth) {
                deepestnode = root;
                TreeDepth = level;
            }
            // Recurse right but don't add to level again
            DeepestNode(root.right, level);
        }
    }
    // public method to hide recursive method from user
    public String DeepestWord(Node root) {
        DeepestNode(root, 0);
        return deepestnode.word;
    }

    // Node count
    // Start with 1 (count yourself)
    // Recurse Left & Recurse right - add left & right children to yourself
    // Ultimate outcome is the number of children + self for root = nodecount
    public int nodeCount(Node root) {
        int count = 1;

        if(root == null) {return 0;}

        else {
            int l = nodeCount(root.left);
            int r = nodeCount(root.right);

            count = count + l + r;

            return count;
        }

    }

    // Count of Total words (vs unique)
    // Similar to the above node count, except you use the sum of count reference in each node vs 1 (or # of child nodes)
    public int wordCount(Node root) {
        int count;
        if (root == null) {
        return 0;
        }
        else {
            count = root.count;
            int l = wordCount(root.left);
            int r = wordCount(root.right);
            count = count + l + r;
            return count;
        }
    }

    // Similar to Deepest node - recurse through the tree till then end and keep a tracker for Frequency (maxCount)
    // Whenever you see something larger, update mostFrequentWord variable
    public String mostFrequentWord(Node root) {
        int frequency = 0;
        String mostFrequentWord = null;

        if (root == null) {
            return null;
        }
        else {
            if(root.count > frequency) {frequency = root.count; mostFrequentWord = root.word;}
            String l = mostFrequentWord(root.left);
            String r = mostFrequentWord(root.right);
            return mostFrequentWord;
        }
    }

    // Pre-Order Traversal.
    // Print Self, Recurse Left and Right

    public void preOrder(Node root, int tracker) {
        if(root == null) {
            return;}

        if (preOrderTracker < 20) {
            preOrderTracker++;
        System.out.print(root.word+" ");
        preOrder(root.left, preOrderTracker);
        preOrder(root.right, preOrderTracker);
        }
    }

    // Post Order
    // Recurse Left, Right then print self
    public void postOrder(Node root, int tracker) {
        if(root == null) {
            return;}

        if (postOrderTracker < 20) {
            postOrderTracker++;
            postOrder(root.left, postOrderTracker);
            postOrder(root.right, postOrderTracker);
            System.out.print(root.word+" ");

        }
    }

    // In Order
    // Recurse Left, Print Self then Recurse Right
    public void inOrder(Node root, int tracker) {
        if(root == null) {
            return;}

        if (inOrderTracker < 20) {
            inOrderTracker++;
            inOrder(root.left, inOrderTracker);
            System.out.print(root.word+" ");
            inOrder(root.right, inOrderTracker);


        }
    }

}




