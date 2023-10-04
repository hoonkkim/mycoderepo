import java.io.*;
import java.util.*;

public class ProcessScheduling {
    // The program will
    // 1) import a file with process information
    // 2) instantiate process objects
    // 3) run them in the right order with a Priority Queue
    // 4) log their wait times and report them
    // 5) return all of the above as console outputs and a text tile.
    public static void main(String[] args) throws IOException {

        // Import Text File
        // Declare Read File
        File file = new File("process_scheduling_input.txt");

        // Instantiate BufferedReader to read file
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));

        // Declare Write File
        FileWriter writefile = new FileWriter("process_scheduling_output.txt");
        // Instantiate BufferedWriter to write the console outputs to file
        BufferedWriter wr = new BufferedWriter(writefile);

        // String to first store the raw read data from bufferedreader
        String importString;
        // Build a new arraylist of ints to take in process information
        ArrayList<int[]> importArrayList = new ArrayList<>();


        // Read until the file runs out
        while ((importString = br.readLine()) != null) {
            // First store as an array of strings with space delimiter
            String[] tempInputArray = importString.split(" ");
            int[] tempIntInputArray = new int[tempInputArray.length];
            for(int i = 0; i < tempInputArray.length; i++)
            {
                // Parse the results in the string array into ints then insert into int array
                tempIntInputArray[i] = Integer.parseInt(tempInputArray[i]);
            }
            // Add the temp int array to the arraylist created above
            importArrayList.add(tempIntInputArray);
        }

        // Instantiate the external db for processes
        ArrayList<Process> D = new ArrayList<>();
        // Instantiate the Priority Queue Q for processes with a custom comparator using priority
        PriorityQueue<Process> Q = new PriorityQueue<>(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.getPr() - o2.getPr();
            }
        });

        // Lambda to add values to each process object and add to D
        importArrayList.forEach(importedRow -> {
            Process p = new Process(importedRow[0], importedRow[1], importedRow[2], importedRow[3]);
            D.add(p);
        });

        // Sort the processes by arrival time.
        Collections.sort(D, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.getArrivalTime() - o2.getArrivalTime();
            }
        });

        // Print out all process information. Also write them to file with the same loop.
        for(int i = 0; i<D.size(); i++)
        {
            Process DummyProcess = D.get(i);
            String tempString = "ID = "+DummyProcess.getID()+" Priority = "+DummyProcess.getPr()+" Duration = "+DummyProcess.getDuration()+" ArrivalTime = "+DummyProcess.getArrivalTime();
            System.out.println(tempString);
            wr.write(tempString);
            wr.newLine();
        }

        // Initialize Current Time and max Wait Times
        int currentTime = 0;
        int maxWaitTime = 30;


        // Print & Write current max wait time
        wr.newLine();
        String tempString = "Maximum Wait Time: "+maxWaitTime;
        System.out.println(tempString);
        wr.write(tempString);
        wr.newLine();

        // Build a wait times arraylist to complie each process's wait times which will get reported at the end.
        ArrayList waitTimesArrayList = new ArrayList<>();

        // Instantiate remaining time and flag for whether a process is running
        int currentProcessRemainingTime = 0;
        boolean currentlyExecutingProcess = false;

        // Instantiate a dummy process object that will point to the process most recently removed from the Queue (=executed)
        Process runningProcess = null;

        // First part of the process, which runs while D is not empty
        // Here for each iteration we
        // 1) move a process from D to Q if needed
        // 2) if needed, run a new process from Q
        // 3) decrement remaining time
        // 4) update priorities for processes waiting longer than max wait time
        // 5) increment current time

        while(D.size() > 0)
        {
            // Move process from D to Q. Kind of.
            while (D.size() > 0 && D.get(0).getArrivalTime() <= currentTime) {
                Q.add(D.get(0));
                D.remove(0);
                }

            // If Q is not empty and no processes are running. Run process with smallest priority
            if(Q.size() > 0 & currentlyExecutingProcess == false)
            {
                // Take the first process out of Q
                runningProcess = Q.poll();
                System.out.println();
                wr.newLine();

                // Print/write stuff
                tempString = runningProcess.execute();
                wr.write(tempString);
                wr.newLine();

                // Log the time that this process had to wait.
                runningProcess.setWaitTime(currentTime-runningProcess.getArrivalTime());
                tempString = "Current Time is "+currentTime+" and Wait Time was: "+(currentTime-runningProcess.getArrivalTime());
                System.out.println(tempString);

                // Print/Write stuff
                wr.write(tempString);
                wr.newLine();

                // Add the time this process had to wait for to the wait time AL
                waitTimesArrayList.add(runningProcess.getWaitTime());

                // Flip the process-running flag
                currentlyExecutingProcess = true;
                // Add to the process is running timer
                currentProcessRemainingTime = runningProcess.getDuration();
            }

            if(currentlyExecutingProcess == true)
            {
                // For every iteration, time remaining goes down by 1
                currentProcessRemainingTime--;
            }

            if(currentlyExecutingProcess == true && currentProcessRemainingTime == 0)
            {
                // If we are running a process but the remaining time is 0, the process is complete.
                currentlyExecutingProcess = false;

                // Declare process completion and write/print it
                tempString = "Process "+runningProcess.getID()+" finished at time "+(currentTime+1);
                System.out.println(tempString);
                wr.write(tempString);
                wr.newLine();

                // Now that a process is complete, time to check and decrement priorities as needed
                if(Q.size() > 0) {
                    // We need a temporary ArrayList to store all the processes that we will remove & edit from Q
                    ArrayList<Process> tempAL = new ArrayList<Process>();
                    while(!Q.isEmpty())
                    {
                        // Remove a process
                        Process x = Q.poll();
                        // Check if it's waited for too long
                        if(currentTime - x.getArrivalTime() > maxWaitTime)
                        {
                            System.out.println();
                            wr.newLine();

                            // If yes, update priority (decrement by 1)
                            tempString = "Updating Priority";
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();

                            // Print/write pre/post priorities and the changes made
                            tempString = "Current Priority for Process ID="+x.getID()+" is "+x.pr;
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();

                            tempString = "The process arrived at "+x.getArrivalTime()+" and waited for "+(currentTime-x.getArrivalTime()+1);
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();

                            // Actually decrement the process's priority
                            x.pr -= 1;

                            tempString = "New Priority for Process ID="+x.getID()+" is "+x.pr;
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();
                        }
                        tempAL.add(x);
                    }

                    // Add back all elements in tempAL back into Q. Don't worry about sorting because it does it automatically
                    for(int j = 0; j < tempAL.size(); j++)
                    {
                        Q.add(tempAL.get(j));
                    }
                }
            }
            // Increment the clock
            currentTime += 1;

        }

        // When we move on to phase 2 where we only work with Q, declare it.
        tempString = "D is now empty at "+currentTime;
        System.out.println(tempString);
        wr.write(tempString);
        wr.newLine();

        // Similar to above, except the part to move process from D to Q is gone.
        // Also the condition of the while loop is whether we have processes running or Q is not empty.
        while(!Q.isEmpty() || currentlyExecutingProcess == true)
        {
            // Similar to code above, when Q is empty, run a new process
            if(!Q.isEmpty() && currentlyExecutingProcess == false)
            {
                runningProcess = Q.poll();
                System.out.println();
                wr.newLine();

                tempString = runningProcess.execute();
                wr.write(tempString);
                wr.newLine();

                runningProcess.setWaitTime(currentTime-runningProcess.getArrivalTime());
                tempString = "Current Time is "+currentTime+" and Wait Time was: "+(currentTime-runningProcess.getArrivalTime());
                System.out.println(tempString);

                wr.write(tempString);
                wr.newLine();

                waitTimesArrayList.add(runningProcess.getWaitTime());
                currentlyExecutingProcess = true;
                currentProcessRemainingTime = runningProcess.getDuration();
            }

            // While process is running, decrement remaining time
            if(currentlyExecutingProcess == true)
            {
                currentProcessRemainingTime--;
            }

            if(currentlyExecutingProcess == true && currentProcessRemainingTime == 0)
            {
                // When process is done running, flip the running flag and make necessary changes/outputs
                currentlyExecutingProcess = false;
                tempString = "Process "+runningProcess.getID()+" finished at time "+(currentTime+1);
                System.out.println(tempString);
                wr.write(tempString);
                wr.newLine();

                // Same checks for processes waiting too long
                if(Q.size() > 0) {
                    ArrayList<Process> tempAL = new ArrayList<Process>();
                    while(!Q.isEmpty())
                    {
                        Process x = Q.poll();
                        if(currentTime - x.getArrivalTime() > maxWaitTime)
                        {
                            System.out.println();
                            wr.newLine();

                            tempString = "Updating Priority";
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();

                            tempString = "Current Priority for Process ID="+x.getID()+" is "+x.pr;
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();

                            tempString = "The process arrived at "+x.getArrivalTime()+" and waited for "+(currentTime-x.getArrivalTime()+1);
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();

                            x.pr -= 1;

                            tempString = "New Priority for Process ID="+x.getID()+" is "+x.pr;
                            System.out.println(tempString);
                            wr.write(tempString);
                            wr.newLine();;
                        }
                        tempAL.add(x);
                    }

                    for(int j = 0; j < tempAL.size(); j++)
                    {
                        Q.add(tempAL.get(j));
                    }
                }
            }
            currentTime += 1;
        }

        System.out.println();
        wr.newLine();

        // When everything is complete and we've exited the second loop, say so.
        tempString = "All Processes Complete at time "+currentTime;
        System.out.println(tempString);
        wr.write(tempString);
        wr.newLine();

        // Now report on total/avg wait times take above.
        int totalWaitTime = 0;
        double avgWaitTime;
        for(int i = 0; i < waitTimesArrayList.size(); i++)
        {
            totalWaitTime += (int) waitTimesArrayList.get(i);
        }
        // Need to case size of the arraylist(= number of processes) to a double because int division will drop decimals
        avgWaitTime = (totalWaitTime/(double)waitTimesArrayList.size());

        // Report the times
        tempString = "Total Wait Time is "+totalWaitTime;
        System.out.println(tempString);
        wr.write(tempString);
        wr.newLine();

        tempString = "Avg Wait Time is "+avgWaitTime;
        System.out.println(tempString);
        wr.write(tempString);
        wr.newLine();

        // Close the reader and writer
        br.close();
        wr.close();
    }

}


// Process class. Contains all the necessary data regarding process priority, arrival time, etc.
class Process {
    int pr; // Priority
    int id; // ID
    int arrivalTime; //  Arrival Time
    int duration; // duration
    int waitTime = 0; // waitTime

    // Constructor to build the class from the data file provided
    public Process(int idInput, int priorityInput, int durationInput, int arrivalTimeInput) {
        pr = priorityInput;
        id = idInput;
        arrivalTime = arrivalTimeInput;
        duration = durationInput;
    }

    // Getter methods. We don't proactively update variable values so setters aren't needed
    // Gets PR of a process. Takes no input. Returns an int.
    public int getPr() {return pr;}
    // Gets ID of a process. Takes no input. Returns an int.
    public int getID() {return id;}
    // Gets ArrivalTime of a process. Takes no input. Returns an int.
    public int getArrivalTime() {return arrivalTime;}
    // Gets Duration of a process. Takes no input. Returns an int.
    public int getDuration() {return duration;}
    // Gets Wait Time of a process. Takes no input. Returns an int.
    public int getWaitTime() {return waitTime;}

    // We do need to change wait times so that gets a setts
    // Sets PR of a process. Takes an int as input. Returns nothing.
    public void setWaitTime(int waitTimeInput) {waitTime = waitTimeInput;}
    // Execute method to display details of the process when its "run."
    // Takes no input and returns a string to be written to file.
    public String execute() {
        String tempString = "Process "+id+" with priority "+pr+" is removed from Queue and executing. Arrival: "+arrivalTime+" Duration: "+duration;
        System.out.println(tempString);
        return tempString;

    }
}