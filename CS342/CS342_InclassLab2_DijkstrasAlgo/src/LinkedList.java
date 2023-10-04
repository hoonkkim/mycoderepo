public class LinkedList {
    Vertex head;
    int count;

    public void addToHead(Integer data, Integer distance) {
        Vertex v = new Vertex();
        v.setData(data);
        v.setDistance(distance);
        v.setNext(head);

        head = v;
        count++;
    }

    public void addToTail(Integer data, Integer distance) {
        if (count == 0) {
            addToHead(data, distance);
        } else {
            Vertex newNode = new Vertex();
            newNode.setData(data);
            newNode.setDistance(distance);
            Vertex tmp = head;
            while(tmp != null) {
                if (tmp.getNext() == null) {
                    // tmp is the last Node in the chain
                    tmp.setNext(newNode);
                    count++;
                    return;
                }
                tmp = tmp.getNext();
            }
        }
    }

    public Vertex findClosest()
    {
        Vertex pointer = new Vertex();
        pointer = this.head;

        int mindistance = 999999;
        Vertex closest = new Vertex();
        while(pointer.getNext() != null) // until we fall off the edgelist
        {
//            int overlaps = 0;
//            for(int i =0; i < allowedlist.length; i++)
//            {
//                if(pointer.getData() == allowedlist[i]) {overlaps++;}
//            }

            if(pointer.getDistance() < mindistance) // if this edge is closer than established minimum
            {
                     mindistance = pointer.getDistance();
                    closest.setData(pointer.getData());
                    closest.setDistance(pointer.getDistance());
            }

            pointer = pointer.getNext(); // go to next vertex/edge
        }

        System.out.println("closest vertex is:"+closest.getData());
        System.out.println("closest distance is:"+closest.getDistance());
        return closest;
    }
}
