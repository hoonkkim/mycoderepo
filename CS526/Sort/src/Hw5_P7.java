import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class Hw5_P7 {

    public static void main(String[] args) {

        // Initialize a Comparator needed in some sort methods
        CompareInts comp = new CompareInts();
        // build an array of integers which will be the length of arrays to sort.
        int[] sortArrayLengths = new int[10];
        sortArrayLengths[0] = 10000;
        sortArrayLengths[1] = 20000;
        sortArrayLengths[2] = 30000;
        sortArrayLengths[3] = 40000;
        sortArrayLengths[4] = 50000;
        sortArrayLengths[5] = 60000;
        sortArrayLengths[6] = 70000;
        sortArrayLengths[7] = 80000;
        sortArrayLengths[8] = 90000;
        sortArrayLengths[9] = 100000;
        // build arrays to record elapsed times for each sorting algo
        long[] insertionSortTimes = new long[10];
        long[] mergeSortTimes = new long[10];
        long[] quickSortTimes = new long[10];
        long[] heapSortTimes = new long[10];

        // Run loop 10 times, which will generate randomized arrays of lengths 10K ~ 100K and sort them
        for(int i = 0; i < 10; i++) {
            // Random, used for generating random integers
            Random r = new Random(System.currentTimeMillis());
            // Build a hashSet which is needed to ensure we have n distinct numbers to sort
            var sortSet = new HashSet<Integer>();
            Integer[] sortInts = new Integer[sortArrayLengths[i]];

            // New Seed for every run so arrays change every iteration.
            r.setSeed(System.currentTimeMillis());
            // Insert random numbers into array. 100K ints ranging from 0 to 100000
            while (sortSet.size() < sortArrayLengths[i]) {
                sortSet.add(r.nextInt(sortArrayLengths[i]*10) + 1);
            }
            sortSet.toArray(sortInts);

            // Build arrays for each sort algo. They are clones of the initial random integer array.
            Integer[] insertionSortInts = sortInts.clone();
            Integer[] mergeSortInts = sortInts.clone();
            Integer[] quickSortInts = sortInts.clone();
            Integer[] heapSortInts = sortInts.clone();

            // instantiate time variables.
            long startTime, endTime, elapsedTime;

            // Log times and run insertion sort
            startTime = System.currentTimeMillis();
            insertionSort(insertionSortInts);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            insertionSortTimes[i] = elapsedTime;
            // Log times and run merge sort
            startTime = System.currentTimeMillis();
            mergeSort(mergeSortInts, comp);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            mergeSortTimes[i] = elapsedTime;
            // Log times and run quick sort
            startTime = System.currentTimeMillis();
            quickSortInPlace(quickSortInts, comp, 0, quickSortInts.length - 1);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            quickSortTimes[i] = elapsedTime;

            // Log times and run heap sort
            startTime = System.currentTimeMillis();
            heapSort(heapSortInts);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            heapSortTimes[i] = elapsedTime;
        }

        // Print out runtimes for each algorithm to record and write deliverables.
        // Print out runtimes for each algorithm to record and write deliverables.
        for(int i = 0; i < 10; i++) {System.out.println(insertionSortTimes[i]);}
        for(int i = 0; i < 10; i++) {System.out.println(mergeSortTimes[i]);}
        for(int i = 0; i < 10; i++) {System.out.println(quickSortTimes[i]);}
        for(int i = 0; i < 10; i++) {System.out.println(heapSortTimes[i]);}

    }
    /* Taken from Textbook */
    public static void insertionSort(Integer[] data) {

        int n = data.length;
        for (int k = 1; k < n; k++) {
            // begin with second character
            // time to insert cur=data[k]
            // find correct index j for cur
            // thus, data[j-1] must go after cur // slide data[j-1] rightward
            // and consider previous j for cur // this is the proper place for cur
            Integer cur = data[k];
            int j = k;
            while (j > 0 && data[j - 1] > cur) {
                data[j] = data[j - 1];
                j--;
                data[j] = cur;
            }
            data[j] = cur;
        }
    }

    /* Taken from TextBook */
    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i + j] = S1[i++]; // copy ith element of S1 and increment i
            else
                S[i + j] = S2[j++]; // copy jth element of S2 and increment j
        }
    }

    /* Taken from TextBook */
    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2) return;
        // divide
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);
        K[] S2 = Arrays.copyOfRange(S, mid, n); // conquer (with recursion)
        mergeSort(S1, comp);
        mergeSort(S2, comp);
        // merge results
        merge(S1, S2, S, comp);
    }

    /* Taken from Textbook */
    private static <K> void quickSortInPlace(K[ ] S, Comparator<K> comp, Integer a, Integer b) {
        if (a >= b) return; // subarray is trivially sorted
        int left = a;
        int right = b - 1;
        K pivot = S[b];
        K temp; // temp object used for swapping
        while (left <= right) {
            // scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && comp.compare(S[left], pivot) < 0) left++;
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && comp.compare(S[right], pivot) > 0) right--;
            if (left <= right) { // indices did not strictly cross
                // so swap values and shrink range
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++;
                right--;
            }
        }
    // put pivot into its final place (currently marked by left index)
    temp = S[left]; S[left] = S[b]; S[b] = temp;
    // make recursive calls
    quickSortInPlace(S, comp, a, left-1);
    quickSortInPlace(S, comp, left + 1, b);
    }

    /* Code from https://www.geeksforgeeks.org/java-program-for-heap-sort/ */
    public static void heapSort(Integer arr[])
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    /* Code from https://www.geeksforgeeks.org/java-program-for-heap-sort/ */
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(Integer arr[], Integer n, Integer i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    static class CompareInts implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

}