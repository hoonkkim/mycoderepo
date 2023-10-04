public class CacheSlot {
    short slotnumber;
    short tag = 0;
    short valid = 0;
    short dirty = 0;

    short[] slotdata = new short[16];

    public void InitializeSlot(short n)
    {
        this.slotnumber = n;
        for(int i = 0; i < 16; i++) {
        slotdata[i] = 0;
        }
    }

    public void DisplaySlot()
    {
        System.out.printf("%-5s %-5s %-5s %-5s", Integer.toHexString(this.slotnumber), valid, tag, dirty);
        for(int i = 0; i < 16; i++) {
            String formatted = String.format("%02x", (slotdata[i]));
            System.out.print(" "+formatted+" ");
        }
        System.out.println();

    }


    public void ReadtoSlot(short address, short[] MainMemory) {
    short offset = (short) (address &  0b1111);
    short newtag = (short) ((address >>> 8) & 0b1111);
    short slotstart = (short) (address & 0b111111110000);

        System.out.println("Beginning of slot to be read is: "+slotstart);
        // Obligatory Miss
        if (this.valid == 0)
        {
            System.out.println("Cache Miss! - Obligatory!");

            for(int i = 0; i < 16; i++) {
                slotdata[i] = MainMemory[slotstart+i];
            }
            System.out.println("Data requested is: "+Integer.toHexString(slotdata[offset]));
            // new data: dirty = false
            this.dirty = 0;

        }
        // Cache hit
        if (this.valid == 1 & this.tag == newtag)
        {
            System.out.println("Cache Hit!");
            System.out.println("Data requested is: "+Integer.toHexString(slotdata[offset]));
            // Don't touch dirty. Cache is unchanged
        }
        // Clean Conflict Miss
        if (this.valid == 1 & this.tag != newtag & this.dirty == 0)
        {
            // Just read everything into cache
            System.out.println("Cache Miss!");
            for(int i = 0; i < 16; i++) {
                slotdata[i] = MainMemory[slotstart+i];
            }
            System.out.println("Data requested is: "+Integer.toHexString(slotdata[offset]));
            // new data: dirty = false
            this.dirty = 0;
        }
        // Dirty Conflict Miss
        if (this.valid == 1 & this.tag != newtag & this.dirty == 1)
        {
            System.out.println("Cache Miss!");
            // Write the dirty content to MM
            for(int i = 0; i < 16; i++) {
                MainMemory[(this.tag << 8) + (this.slotnumber << 4) + i] = slotdata[i];
            }

            // Now read in new stuff from MM
            for(int i = 0; i < 16; i++) {
                slotdata[i] = MainMemory[slotstart+i];
            }
            System.out.println("Data requested is: "+Integer.toHexString(slotdata[offset]));

            // new data: dirty = false
            this.dirty = 0;
        }

        this.tag = newtag;
        this.valid = 1;

    }

    public void WritetoSlot (short address, short value, short[] MainMemory)
    {
        short offset = (short) (address &  0b1111);
        short newtag = (short) ((address >>> 8) & 0b1111);
        short slotstart = (short) (address & 0b111111110000);


        System.out.println("Beginning of slot to be written is: "+slotstart);
        // Obligatory Miss
        if (this.valid == 0)
        {
            System.out.println("Cache Miss! - Obligatory!");
            for(int i = 0; i < 16; i++) {
                slotdata[i] = MainMemory[slotstart+i];
            }

            slotdata[offset] = value;
            System.out.println("New Data written is: "+Integer.toHexString(slotdata[offset]));
        }

        // Cache hit
        if (this.valid == 1 & this.tag == newtag)
        {
            System.out.println("Cache Hit!");
            slotdata[offset] = value;
            System.out.println("New Data written is: "+Integer.toHexString(slotdata[offset]));
        }
        // Clean Conflict Miss. Valid, different tag but clean.
        if (this.valid == 1 & this.tag != newtag & this.dirty == 0)
        {
            // Just read everything into cache
            System.out.println("Cache Miss!");
            for(int i = 0; i < 16; i++) {
                slotdata[i] = MainMemory[slotstart+i];
            }
            slotdata[offset] = value;
            System.out.println("New Data written is: "+Integer.toHexString(slotdata[offset]));
        }
        // Dirty Conflict Miss. Valid, dirty & different tag
        if (this.valid == 1 & this.tag != newtag & this.dirty == 1)
        {
            System.out.println("Cache Miss!");
            // Write the dirty content to MM
            for(int i = 0; i < 16; i++) {
                MainMemory[(this.tag << 8) + (this.slotnumber << 4) + i] = slotdata[i];
            }

            // Now read in new stuff from MM
            for(int i = 0; i < 16; i++) {
                slotdata[i] = MainMemory[slotstart+i];
            }
            slotdata[offset] = value;
            System.out.println("New Data written is: "+Integer.toHexString(slotdata[offset]));

            // new data: dirty = false
        }

        this.tag = newtag;
        this.valid = 1;
        this.dirty = 1;
    }


}
