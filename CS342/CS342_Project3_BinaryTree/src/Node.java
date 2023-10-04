// Simple/straightforward binary tree node class
// Left & Right child references
// String for value (vs numeric)
// Int Count for occurrences that will be incremented for every duplicate entry

public class Node {
    String word;
    int count;
    Node left;
    Node right;

    Node(String word) {
        this.word = word;
        count = 1;
        right = null;
        left = null;
    }


}
