package com.company;

public class Node {
    private Integer XValue;
    private Integer YValue;
    private Node next;

    // adding some methods to Node to return X and Y values to as well as computations of the 2 to see if there are conflicts between Queens.

    public Integer getX() {
        return XValue;
    }
    public Integer getY() {
        return YValue;
    }
    public void setX(Integer X) {this.XValue = X;}
    public void setY(Integer Y) {this.YValue = Y;}

    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }

    // Sum and Diff numbers are needed to calculate diagonal conflicts
    public Integer getSum() {return XValue+YValue;}
    public Integer getDiff() {return XValue-YValue;}

}
