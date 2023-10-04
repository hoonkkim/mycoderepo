import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // get user input with Scanner
        Scanner keyboard = new Scanner(System.in);
        // ** Human Friendly Instructions. No NLP/String Catch since this shouldn't be too hard...
        System.out.println("Please enter a number you want to find Prime Numbers up to.");
        int userinput = keyboard.nextInt();

        // even though we get a number as an input, both input and output queues start at size 10 per Spec.
        ArrayQueue intArray = new ArrayQueue(10);
        ArrayQueue primeArray = new ArrayQueue(10);

        // Build input array with a loop. I guess I could do recursion, but why?
        for(int i = 2; i <= userinput; i++) {

            // Provision for expanding the array if it becomes full during build.
            // Note how the expand is happening BEFORE new element add.
            if(intArray.isFull())
            {
                // New Queue.
                ArrayQueue newArrayQueue = new ArrayQueue(userinput-1);

                while(!intArray.isEmpty()) {
                // By using remove & add methods we can get the heads/tails set up properly (vs a x[] = y[] type of move.)
                    newArrayQueue.add(intArray.remove());
                }
                // rename newqueue to point at oldqueue.
                intArray = newArrayQueue;
            }
            // more elements
            intArray.add(i);
        }

        // Debugger tostring() method to see what I actually added
        //  System.out.println(intArray.toString());
        int remover = 0;
        // remover = 0 so we get into the loop the first time. We can also use a do while too.
        // Implementing "divide and eject until SQRT(x)"
        while(remover < Math.sqrt(userinput)) {
            // head = remover, which is the divisor used to test elements
            remover = intArray.remove();
            // we have a custom method for that
            intArray.removeDivided(remover);
            // if full, make new array, port and rename. same as above
            if(primeArray.isFull())
            {
                 ArrayQueue newArrayQueue = new ArrayQueue(userinput-1);

                 while(!primeArray.isEmpty()) {
                     newArrayQueue.add(primeArray.remove());
                 }

                primeArray = newArrayQueue;
            }

            // Add next prime number to the PrimeArray.
            primeArray.add(remover);

            // Recursion apparently messes up my queue because the deepest recursion (last) gets added back first.
            // We have a method for that!
            intArray.reOrder();

            // Debugger toString();
            // System.out.println(intArray.toString());
        }

        // because we only removed up to p < SQRT(n), we need to port the remaining elements in intArray to PrimeArray.
        while(!intArray.isEmpty())
        {
            // Integer for Removal
            Integer move = intArray.remove();

            // Size-up loop again.
            if(primeArray.isFull())
            {
                ArrayQueue newArrayQueue = new ArrayQueue(userinput-1);

                while(!primeArray.isEmpty()) {
                    newArrayQueue.add(primeArray.remove());
                }

                primeArray = newArrayQueue;
            }

            // Migration.
            primeArray.add(move);
        }

        // We have a printer method!
        System.out.println("These are the "+primeArray.size()+" Prime Numbers up to "+userinput+" you requested");
        primeArray.hwPrint();

    }
}
