import java.util.*;

public class Hw4_p6 {

    public static void main(String[] args) {

        // Instantiate Random to use to generate insert and search keys
        Random r = new Random(System.currentTimeMillis());

        // Instantiate both search and insert arrays. Each 100,000 in size
        Integer[] insertKeys = new Integer[100000];
        Integer[] searchKeys = new Integer[100000];

        // Instantiate HashMap, ArrayList, and LinkedList objects to insert and search.
        // create a HashMap instance myMap
        HashMap myMap = new HashMap();
        // create an ArrayList instance myArrayList
        ArrayList myArrayList = new ArrayList();
        // create a LinkedList instanc myLinkedList
        LinkedList myLinkedList = new LinkedList();
        // Create Helper Set Objects to ensure all keys are unique
        var insertSet = new HashSet<Integer>();
        var searchSet = new HashSet<Integer>();

        // Instantiate time arrays to measure how long each batch of inserts/searches took to average later
        long[] myMapInsertTime = new long[10];
        long[] myArrayListInsertTime = new long[10];
        long[] myLinkedListInsertTime = new long[10];

        long[] myHashMapSearchTime = new long[10];
        long[] myArrayListSearchTime = new long[10];
        long[] myLinkedListSearchTime = new long[10];


    // Repeat the following 10 times and calculate average total insertion time and average total search time for each data structure
    // 10x loop for insert & search
    for (int repeats = 0; repeats < 10; repeats++)
    {
        // begin with empty myHap, myArrayList, and myLinkedList each time
        // Clear map, arraylist and linked list to start off the process
        myMap.clear();
        myArrayList.clear();
        myLinkedList.clear();
        insertSet.clear();
        searchSet.clear();

        // Set the random number seed for every iteration.
        r.setSeed(System.currentTimeMillis());
        // Insert random numbers into array. 100K ints ranging from 0 to 100000
        while(insertSet.size() < 100000)
        {insertSet.add(r.nextInt(1000000)+1);}
        insertSet.toArray(insertKeys);



        // Insert keys one at a time but measure only the total time (not individual insert  // time)
        // insert all keys in insertKeys [ ] into myMap and measure the total insert time
        // Use put method for HashMap, e.g., myMap.put(insertKeys[i], i)
        // To ensure the keys are unique (and there are 100K of them) we use a set which we then convert to an array.
        // Sets by definition can only contain unique values, so running a while loop until the set reaches size 100K
        // guarantees we have the right number of keys
        long startTime, endTime, elapsedTime;
        startTime = System.currentTimeMillis();
        for (int j = 0; j < insertKeys.length; j++)
        {

            myMap.put(insertKeys[j], j);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        myMapInsertTime[repeats] = elapsedTime;

        // insert all keys in insertKeys [ ] into  myArrayList and measure the total insert time
        // Use add method for ArrayList and LinkedList
        startTime = System.currentTimeMillis();
        for (int j = 0; j < insertKeys.length; j++)
        {
            myArrayList.add(insertKeys[j]);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        myArrayListInsertTime[repeats] = elapsedTime;

        // insert all keys in insertKeys [ ] into myLinkedList and measure the total insert time
        // Use add method for ArrayList and LinkedList
        startTime = System.currentTimeMillis();
        for (int j = 0; j < insertKeys.length; j++)
        {
            myLinkedList.add(insertKeys[j]);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        myLinkedListInsertTime[repeats] = elapsedTime;


        // generate 100,000 distinct random integers in the range [1, 2,000,000] and store them in the array searchKeys[ ].
        // To ensure the keys are unique (and there are 100K of them) we use a set which we then convert to an array.
        // Sets by definition can only contain unique values, so running a while loop until the set reaches size 100K
        // guarantees we have the right number of keys
        // Also, reset the seed to prevent some fluke from happening.
        r.setSeed(System.currentTimeMillis());
        while(searchSet.size() < 100000)
        {searchSet.add(r.nextInt(2000000)+1);}
        searchSet.toArray(searchKeys);


        // search myMap for all keys in searchKeys[ ] and measure the total search time search myArrayList for all keys in searchKeys[ ] and measure the total search time  search myLinkedList for all keys in searchKeys[ ] and measure the total search time
        // Use containsKey method for HashMap
        // Search keys one at a time but measure only total time (not individual search // time)
        startTime = System.currentTimeMillis();
        for(int i = 0; i<100000; i++)
        {
            myMap.containsKey(searchKeys[i]);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        myHashMapSearchTime[repeats] = elapsedTime;

        // Use contains method for ArrayList and Linked List
        // Search keys one at a time but measure only total time (not individual search // time)
        startTime = System.currentTimeMillis();
        for(int i = 0; i<100000; i++)
        {
            myArrayList.contains(searchKeys[i]);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        myArrayListSearchTime[repeats] = elapsedTime;

        // Use contains method for ArrayList and Linked List
        // Search keys one at a time but measure only total time (not individual search // time)
        startTime = System.currentTimeMillis();
        for(int i = 0; i<100000; i++)
        {
            myLinkedList.contains(searchKeys[i]);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        myLinkedListSearchTime[repeats] = elapsedTime;

    }

    // Declare helper variables to take the average of time values in the time arrays filled in the loop
    double myMapInsertTimeAvg =  0.0d;
    double myArrayListInsertTimeAvg = 0.0d;
    double myLinkedListInsertTimeAvg = 0.0d;

    double myMapSearchTimeAvg =  0.0d;
    double myArrayListSearchTimeAvg = 0.0d;
    double myLinkedListSearchTimeAvg = 0.0d;


    // Loop through each time array and add the values to the time average variables.
    // Division is done during the add so there is no need for an average function
        for(int i=0; i<10;i++) {
        myMapInsertTimeAvg += myMapInsertTime[i]/10.0d;
        myArrayListInsertTimeAvg += myArrayListInsertTime[i]/10.0d;
        myLinkedListInsertTimeAvg += myLinkedListInsertTime[i]/10.0d;
        myMapSearchTimeAvg += myHashMapSearchTime[i]/10.0d;
        myArrayListSearchTimeAvg += myArrayListSearchTime[i]/10.0d;
        myLinkedListSearchTimeAvg += myLinkedListSearchTime[i]/10.0d;
    }


    // Print findings on console per instructions
    System.out.println("Number of Keys = "+insertKeys.length);
    System.out.println();
    System.out.println("HashMap average total insert time = "+myMapInsertTimeAvg);
    System.out.println("ArrayList average total insert time = "+myArrayListInsertTimeAvg);
    System.out.println("LinkedList average total insert time "+myLinkedListInsertTimeAvg);
    System.out.println();
    System.out.println("HashMap average total search time = "+myMapSearchTimeAvg);
    System.out.println("ArrayList average total search time = "+myArrayListSearchTimeAvg);
    System.out.println("LinkedList average total search time "+myLinkedListSearchTimeAvg);


    /*
    Discussion on #6 of HW4: Performance of HashMap, ArrayList and LinkedList for large scale Insert/Search.
    There are a few takeaways on the performance of different data structures when inserting & searching for a large number of iterations.
    1.	ArrayList & LinkedList are much quicker than HashMap for value insertion. ArrayList & LinkedList performed similarly when inserting. This makes sense because unlike HashMaps which may experience collisions while inserting, ArrayLists and LinkedLists do not because add is performed sequentially.
    2.	When searching, the opposite occurs and HashMap is much, much quicker thanArrayList or LinkedList. With HashMaps, searching for a keys has an average big-O of O(1) so search times are trivial. On the other hand, with ArrayLists and LinkedLists, each value search must travel through a decent share of the ArrayList/LinkedList (2/n on average), resulting in a big-o of O(n).
    a.	ArrayList was faster than LinkedList for both tasks (trivial difference in inserts and a large difference in search). However, for the search task, both data structures were much slower than hashmap.
    3.	The takeaway here seems to be the following:
    a.	When the use case involves high volume of inserts but not that many searches, use ArrayLists or LinkedLists.
    b.	When the use-case is search heavy, use Hashmaps because search performance differs greatly between Hashmaps and ArrayList/LinkedList.


     */

    }
}
