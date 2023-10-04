import java.io.*;
import java.util.*;

public class Hw6_p5 {

    public static void main(String[] args) throws IOException {

        // Import the adjacency list in the homework.
        ArrayList<Node> adjlist = importAdjList();

        // Test the allFollows method for all nodes in the graph as well as a non-existent node.
        allFollows("A", adjlist);
        allFollows("B", adjlist);
        allFollows("C", adjlist);
        allFollows("D", adjlist);
        allFollows("E", adjlist);
        allFollows("F", adjlist);
        allFollows("G", adjlist);
        allFollows("H", adjlist);
    }

    public static ArrayList<Node> importAdjList() throws IOException {
        // Import Text File
        // Declare File
        File file = new File("follows_input.txt");

        // Instantiate BufferedReader
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));

        // Instantiate empty string to accept reads from bufferedreader
        String importString;
        // Instantiate ArrayList of Strings to dump file content into
        ArrayList importArrayList = new ArrayList<String[]>();

        while ((importString = br.readLine()) != null) {
            String[] tempInputArray = importString.split(", ");
            importArrayList.add(tempInputArray);
        }

        // Ready to Make Nodes and the Adjacency List
        // Declare the Adjacency List
        ArrayList<Node> AdjacencyList = new ArrayList<Node>();
        // Loop through the temporary input array to generate nodes and then insert them into adj-list
        for (int i = 0; i < importArrayList.size(); i++) {
            // Declare temporary array to store and manipulate entries in the import AL
            String[] tempArray = (String[]) importArrayList.get(i);
            // Declare helper node. This will get stored into the Adj-List
            Node node = new Node();
            // For every item in the array of strings, the first entry is the name of the node
            // The rest are references
            node.person = tempArray[0];
            if (tempArray.length > 1) {
                node.references = new ArrayList<Node>();
            }
            AdjacencyList.add(node);
        }
        // Now that the entire AL is build, populate each node's references
        // Iterate through the adjacency list
        for (int j = 0; j < AdjacencyList.size(); j++) {
            String[] tempArray = (String[]) importArrayList.get(j);
//            System.out.println("Adding references for "+AdjacencyList.get(j).person);
            // Grab the nth node's references, which will be index 1 ~ end of the importArrayList's members
            // iterate through them
            for (int k = 1; k < tempArray.length; k++) {
                String referenceString = tempArray[k];
//                System.out.println("Looking for Node of Person "+referenceString);
                // for each letter that comes up, look up the node where the letter equals the person of the node in the AdjacencyList
                // Add that node
                for (int l = 0; l < AdjacencyList.size(); l++) {
                    if (AdjacencyList.get(l).person.equals(referenceString)) {
//                        System.out.println("node at "+l+" is added to references");
                        AdjacencyList.get(j).references.add(AdjacencyList.get(l));
                    }
                }

            }

        }

        return AdjacencyList;

    }

    public static void allFollows(String person, ArrayList<Node> adjList) {
        if(getIndex(person, adjList) == -1) {System.out.println(person+" does not exist in the Adjacency List!");}

        if (getIndex(person, adjList) != -1) {
            Node sourceNode = adjList.get(getIndex(person, adjList));
            // Divide for nodes with references and no references
            if (sourceNode.references != null) {
                // First for direct followings
                ArrayList<String> directFollowing = new ArrayList<>();
                for (int i = 0; i < sourceNode.references.size(); i++) {
                    directFollowing.add(sourceNode.references.get(i).person);
                }
                System.out.println(sourceNode.person + " directly follows " + directFollowing.toString().replace("[", "{").replace("]", "}"));


                // Some helper variables for Graph BFS
                // Queue
                LinkedList<Node> queue = new LinkedList();
                queue.add(sourceNode);
                // Visited boolean array to avoid infinite loops/duplicate traversals
                boolean[] visited = new boolean[adjList.size()];
                // label the source node as traversed
                int x = getIndex(person, adjList);
                visited[x] = true;


                // Run a breadth first search to find all direct & indirect follows
                // Arraylist to log all nodes that the source node follows indirectly
                ArrayList<String> IndirectFollows = new ArrayList<>();
                while (queue.size() != 0) {
                    // Remove first node from queue
                    Node s = queue.poll();
//                System.out.println("Polled "+s.person);
//                System.out.println(queue.size());
                    if (s.references != null) {
                        // iterate through the references that the node we are exploring owns
                        Iterator<Node> i = s.references.iterator();
                        while (i.hasNext()) {
                            Node n = i.next();
//                        System.out.println(s.person+" follows: "+n.person);
                            // Only add n to the queue if we haven't traversed it yet
                            if (visited[getIndex(n.person, adjList)] == false) {
                                // Set the visited flag to true since we are looking at this node.
                                visited[getIndex(n.person, adjList)] = true;
                                queue.add(n);
                                if (!s.person.equals(sourceNode.person)) {
                                    // if this node is not a direct follow (=node s is the original source node)
                                    // Add node's person string to indirect follows arraylist.
                                    IndirectFollows.add(n.person);
                                }
                            }
                        }
                    }
                }
                // Print out results
                if(IndirectFollows.size() > 0) {System.out.println(sourceNode.person + " indirectly follows "
                        + IndirectFollows.toString().replace("[", "{").replace("]", "}"));}
                // Provision for nodes with no followers at all. Blanks look bad so replace with English.
                if(IndirectFollows.size() == 0) System.out.println(sourceNode.person + " indirectly follows no one");}

            // No direct references = no references. Print hardcoded messages.
            else {
                System.out.println(sourceNode.person + " directly follows no one");
                System.out.println(sourceNode.person + " indirectly follows no one");
            }
        }
    }

    // Helper method to locate the indices of nodes in the adjacency list with the node's person String
    public static int getIndex(String person, ArrayList<Node> adjList)
    {
    int x = -1;
    for (int l = 0; l < adjList.size(); l++) {
        if (adjList.get(l).person.equals(person)) {
            x = l;
        }
    }
        return x;
    }


    // Custom node class. not much to see here. Node has a person (it's "ID") and an arraylist of nodes it follows.
    public static class Node {
        String person;
        ArrayList<Node> references;
    }

}


