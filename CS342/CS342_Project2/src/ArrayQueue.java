
public class ArrayQueue {

    private int head;
    private int tail;
    private int count;
    private Integer []queue;

    private static final int QUEUE_SIZE = 10;

    public ArrayQueue() {
        queue = new Integer[QUEUE_SIZE];
        head = tail = count = 0;
    }

    public ArrayQueue(int size) {
        queue = new Integer[size];
        head = tail = count = 0;
    }

    public boolean add(Integer data) {
        if (isFull()) {
            return false;
        }

        queue[tail] = data;
        tail++;

        if (tail == queue.length) {
            tail = 0;
        }

        count++;
        return true;
    }

    public Integer remove() {
        if (isEmpty()) {
            return null;
        }

        Integer tmp = queue[head];
        head++;

        if (head == queue.length) {
            head = 0;
        }

        count--;
        return tmp;

    }

    public boolean isFull() {
        return (count == queue.length);
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    public String toString() {
        String rtn = "";
        rtn += "count = " + count + "\n";
        rtn += "head  = " + head + "\n";
        rtn += "tail  = " + tail + "\n";

        if (isEmpty()) {
            return "<empty>";
        }
        int tmp = head;
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                rtn += "head -> ";
            } else {
                if (i == count-1) {
                    rtn += "tail -> ";
                } else {
                    rtn += "        ";
                }
            }

            rtn += queue[tmp++] + "\n";

            if (tmp == queue.length) {
                tmp = 0;
            }

        }

        return rtn;
    }

    // My work starts here.
    // Method to walk a queue and remove anything with a modulo != 0.
    public void removeDivided(Integer a) {
        // base case. This is when we run out of elements. Don't do anything.
        if(this.isEmpty()) {
            // do nothing
            return;
        }

        // Off with the head!
        Integer x = this.remove();

        // A message to make sure we know what we're doing/
        // System.out.println("We will divide by "+a);
        // System.out.println(x+" divided by "+a+" leaves "+x%a);

        // Recurse!
        this.removeDivided(a);

        // Conditional Add Back. If modulo is NOT 0, then it's not divisable by A, therefore gets to survive.
        if(x%a != 0)
        {
            this.add(x);
        }

    }

    // we need this because the walk method above inverts the queue.
    public void reOrder()
    {
        if(this.isEmpty()) {
            // do nothing
            return;
        }
        // remove.
        Integer x = this.remove();
        // recurse.
        this.reOrder();
        // add back.
        this.add(x);
    }

    // printing because humans do not like to read infinitely long horizontal lines
    public void hwPrint()
    {
        // repeat until we run out of elements
        while(!this.isEmpty()){
            // Output string that keeps getting re-gen'd for every line of output.
            String output = new String();
            // 10 elements OR we run out of elements till print & reset
            for(int i = 0; i<10 & !this.isEmpty();i++)
            {
                // output = what we have so far + newly removed element and a whitespace
                output = output+this.remove().toString()+" ";
            }
            // Print.
            System.out.println(output);
            // back to the top
        }
    }

}
