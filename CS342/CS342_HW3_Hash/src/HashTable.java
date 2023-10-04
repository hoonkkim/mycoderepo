public class HashTable implements HashTableInterface {

    private HashObject table[];
    private static final int TABLE_SIZE = 31;
    int count;

    public HashTable() {
        table = new HashObject[TABLE_SIZE];
        count = 0;
    }

    private int hash(int k) {return k % TABLE_SIZE;}

    private boolean tableFull() {
        return count == table.length;
    }

    private int probe(int loc) {
        return (loc + 1) % TABLE_SIZE;
    }


    public void put(int k, String v) {
        // Let's start with the can't do.
        if (tableFull()) {
            System.out.println("The table is full. Sorry!");
        }
        // identical key exists
        if(contains(k))
        {int index = keySearch(k);
        // data is identical -> do nothing
         if(table[index].getData().equals(v)) {
             System.out.println("Duplicate key with identical data. Doing Nothing");
             return;}
        // Duplicate Key + Different Data -> Overwrite
         if(!v.equals(table[index].getData())) {
             System.out.println("Duplicate key with different data. Overwriting");
             table[index].setData(v);}
        }

        else {
            // Adding as the key does not exist
            boolean done = false;
            int loc = hash(k);

            while (done == false) {
                // loc as hashvalue
                // Simplest Outcome -> hash value is empty and has never been used. Add it.
                if (table[loc] == null || (table[loc].isUsed())) {
                    HashObject x = new HashObject();
                    x.setData(v);
                    x.setKey(k);
                    table[loc] = x;
                    count++;
                    done = true;
                }
                // if neither was met, then we keep looking.
                loc = probe(loc);
            }
        }

    }

    public String get(int k)
    {
        int loc = keySearch(k);
        if(loc != -1)
        {
            return table[loc].getData();
        }
        return "No data with key: "+k;
    }


    public void delete(int k)
    {
        int loc = keySearch(k);

        if (loc != -1) {
            table[loc].setUsed(true);
            count--;
        }
    }


    public boolean contains(int k)
    {
        int loc = hash(k);

        int cnt = 0;

        while ((table[loc] != null) && (cnt < TABLE_SIZE)) {

            cnt++;

            if (table[loc].isUsed()) {
                loc = probe(loc);
                continue;
            }

            if (table[loc].getKey() == k) {
                return true;
            }

            loc = probe(loc);

        }

        return false;
    }



    public void printHash() {
        String rtn = "";

        for (int i = 0; i < TABLE_SIZE; i++) {
            rtn += "table[" + i + "] = ";
            if (table[i] == null) {
                rtn += "<Never Used> \n";
            } else if (table[i].isUsed()) {
                rtn += "<Prev Used>\n";
            } else {
                rtn += table[i].getKey() + " - "+ table[i].getData() + "\n";
            }
        }

        System.out.println(rtn);
    }

    private int keySearch(int k) {

        int loc = hash(k);

        int cnt = 0;

        while ((table[loc] != null) && (cnt < TABLE_SIZE)) {

            cnt++;

            if (table[loc].isUsed()) {
                loc = probe(loc);
                continue;
            }

            if (table[loc].getKey() == k) {
                return loc;
            }

            loc = probe(loc);

        }

        return -1;

    }

    }



