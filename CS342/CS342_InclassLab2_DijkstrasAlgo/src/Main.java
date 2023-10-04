public class Main {

    public static void main(String[] args) {
        LinkedList[] Edgelist = new LinkedList[7];
        // allocate array of linkedlists
        for(int i = 0; i < 7; i++)
        {
            Edgelist[i] = new LinkedList();
        }

        // populate linkedlist with edges
        Edgelist[0].addToTail(1, 5);
        Edgelist[0].addToTail(2, 2);
        Edgelist[0].addToTail(5, 5); // 0 has 3 edges
        Edgelist[1].addToTail(4, 2);
        Edgelist[1].addToTail(5, 4); // 1 has 2 edges
        Edgelist[2].addToTail(4, 3); // 2 has 1 edge
        Edgelist[3].addToTail(2, 3);
        Edgelist[3].addToTail(6, 4); // 3 has 2 edges
        Edgelist[4].addToTail(6, 2); // 4 has 1 edge
        Edgelist[5].addToTail(6, 3); // 5 has 1 edge
        Edgelist[6].addToTail(6, 2); // 6 has no edges

        // step 1
        int[][] MinDists = new int[7][7]; // MinDist[0] = distances from Node 0

        int i = 0;
        while(i < 7)
        {
            int j = 0;
            String Output = "";
            while(j < 7)
            {
                MinDists[i][j] = 99999999;
                MinDists[i][i] = 0;
                Output += MinDists[i][j]+" ";
                ++j;
            }
            System.out.println(Output);
            ++i;
        }

        // step 2

        boolean[] AllowedVertices = new boolean[7];
        AllowedVertices[0] = true;

        for(int boool = 1; boool < 7; i++)
        {AllowedVertices[boool] = false;}

        // step 3
        for(int allowedsize = 1; allowedsize <= 7; allowedsize++)
        {
            // traverse each EdgeList
            // check if EdgeList.data == AllowedVertices.data


        }



    }
}
