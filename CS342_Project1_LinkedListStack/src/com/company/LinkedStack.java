package com.company;

public class LinkedStack {

    private Node top;
    private int count;

    public LinkedStack() {
        clearStack();
    }

    private void clearStack() {
        top = null;
        count = 0;
    }


    // Push has 2 variables now. an X value and a Y value.
    public Node push(Integer X, Integer Y) {
        Node n = new Node();
        n.setX(X);
        n.setY(Y);
        n.setNext(top);
        top = n;
        count++;
        return top;
    }

    // pop returns a Node as well
    public Node pop() {
        if (isEmpty()) {
            return null;
        }

        top = top.getNext();
        count--;
        return top;
    }

    public void clear() {
        clearStack();
    }

    public int size() {
        return count;
    }

    // peek now returns a Node Object instead of a value as we need both X and Y to calculate conflicts
    public Node peek() {
        if (isEmpty()) {
            return null;
        } else {
            return top;
        }
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    // recursive method which pops items off the stack, stores the value until index == 1, where it stops.
    public Node itemAt(int index) {
    if(index == 1) // the index is depth = distance from top. if index == 1, then we want the topmost item
    {return this.peek();}

    Node x = this.peek(); // maintain top that gets popped to add back
    this.pop(); // pop top

    Node y = itemAt(index - 1); // the recursion is the output.

    this.push(x.getX(), x.getY()); // add everything back in so the stack remains unchanged

    return y;
    }

}