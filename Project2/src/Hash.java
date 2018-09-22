/**
 * This class creates a hash table that allows for very quick searching via a hash function.
 *
 * @author Dasha Savina (dasha8)
 * @version 09.17.18
 */

public class Hash
{

    private int size;
    private int numEntries;
    private Record[] table;


    /**
     * Initalizes the hash object and takes in the size of it
     * 
     * @param initialSize
     *            the starting size for the hash
     */
    public Hash(int initialSize) {
        size = initialSize;
        numEntries = 0;
        table = new Record[size];
    }


    /**
     * Check if a record is in table without adding or deleting it
     * 
     * @param key
     *            the key to search for
     * @return boolean if in table
     */
    public boolean inTable(String key) {
        int home = h(key, size); // Home position for K
        int pos = home; // Initial position is the home slot

        // if table[home] is null, there's no entry
        if (table[home] == null) {
            return false;
        }

        // if table[home] is the entry, delete it
        if ((!table[home].isEmpty()) && (key.equals(table[pos].getKey()))) {
            return true;
        }

        pos = (home + 1) % size;
        // if table[home] isn't null/the entry, start probing
        for (int i = 1; table[pos] != null; i++) {

            if ((!table[pos].isEmpty()) && (key.equals(table[pos].getKey()))) {

                return true;
            }
            pos = (home + i * i) % size;
        }

        return false;
    }


    /**
     * inserts a new record into the hash table
     * 
     * @param record
     *            the hash record to be added
     * @return returns if successfuly inserted
     */
    public boolean hashInsert(Record record) {

        int home = h(record.getKey(), size); // home position
        int pos = home; // probe position (initialized to home)
        // set to null
        int firstTomb = -1;

        // check if the home position is a duplicate
        if ((table[home] != null) && (record.getKey().equals(table[pos]
            .getKey()))) {
            return false;
        }

        // if home position is full, start probing
        for (int i = 1; (table[pos] != null); i++) {

            if (table[pos].isEmpty()) {
                firstTomb = pos;
            }

            pos = (home + i * i) % size; // probe

            // check if new position is a duplicate
            if ((table[pos] != null) && (record.getKey().equals(table[pos]
                .getKey()))) {
                return false;
            }

        }

        // once we found our spot check if we need to double
        if ((numEntries + 1) > (size / 2)) {
            // System.out.println("size:" + size);
            doubleSize();

            // System.out.println("size:" + size);
            home = h(record.getKey(), size); // home position
            pos = home; // probe position (initialized to home)
            firstTomb = -1;

            // if home position is full, start probing
            for (int i = 1; (table[pos] != null); i++) {
                pos = (home + i * i) % size; // probe
            }
        }

        // finally, insert record
        if (firstTomb != -1) {
            table[firstTomb] = record;
        }
        else {
            table[pos] = record;
        }
        numEntries++;

        return true;
    }


    /**
     * Deletes an object from the hash
     * 
     * @param key
     *            used to delete the object
     * @return if deleted
     */
    public Record hashDelete(String key) {

        int home = h(key, size); // Home position for K
        int pos = home; // Initial position is the home slot

        // if table[home] is null, there's no entry
        if (table[home] == null) {
            return null;
        }

        // if table[home] is the entry, delete it
        if ((!table[home].isEmpty()) && (key.equals(table[pos].getKey()))) {

            //Record foundIt = new Record(key, table[home].getHandle());
            table[home].empty();
            numEntries--;
            //return foundIt;
        }

        pos = (home + 1) % size;
        // if table[home] isn't null/the entry, start probing
        for (int i = 1; table[pos] != null; i++) {

            if ((!table[pos].isEmpty()) && (key.equals(table[pos].getKey()))) {
                //Record foundIt = new Record(key, table[pos].getHandle());
                table[pos].empty();
                numEntries--;
                //return foundIt;
            }
            pos = (home + i * i) % size;
        }

        return null;

    }


    /**
     * Returns the size of the hash
     * 
     * @return the hash size
     */
    public int getSize() {
        return size;
    }


    /**
     * find a hash record based on a key
     * 
     * @param key
     *            the object to be found in the hash
     * @return the record found with the key
     */
    public Record hashSearch(String key) {
        int home = h(key, size); // Home position for K
        int pos = home; // Initial position is the home slot
        for (int i = 1; (key != table[pos].getKey() && !(table[pos]
            .isEmpty())); i++) {

            pos = (home + i * i) % size;
        } // Next on probe sequence
        if (key == table[pos].getKey()) { // Found it
            return table[pos];
        }
        else { // K not in hash table
            return null;
        }

    }


    /**
     * search again (to remove sometime)
     * 
     * @param key
     *            its the key
     * @return the record
     */
    public Record hashSearch2(String key) {

        int home = h(key, size); // Home position for K
        int pos = home; // Initial position is the home slot

        // if table[home] is null, there's no entry
        if (table[home] == null) {
            return null;
        }

        // if table[home] is the entry, delete it
        if ((!table[home].isEmpty()) && (key.equals(table[pos].getKey()))) {
            return table[pos];
        }

        pos = (home + 1) % size;
        // if table[home] isn't null/the entry, start probing
        for (int i = 1; table[pos] != null; i++) {

            if ((!table[pos].isEmpty()) && (key.equals(table[pos].getKey()))) {

                return table[pos];
            }
            pos = (home + i * i) % size;
        }

        return null;
    }


    /**
     * This function prints out all the key values and their location in the
     * has table
     */
    public void printHash() {
        for (int i = 0; i < size; i++) {
            if ((table[i] != null) && (!table[i].isEmpty())) {
                System.out.println("|" + table[i].getKey() + "| " + i);
            }
        }
        System.out.println("Total records: " + numEntries);
    }


    /**
     * returns the record stored at a particular index of the hash table.
     * this function is primarily used for testing.
     * 
     * @param index
     *            the index you are looking at
     * @return the record stored at that index
     */
    public Record getRecordAt(int index) {
        return table[index];
    }


    /**
     * Doubles the size of the hash table and re-hashes existing entries
     */
    private void doubleSize() {
        
        Record[] newTable = new Record[size * 2];

        for (int a = 0; a < size; a++) {
            Record curr = table[a];

            if ((curr == null) || (curr.isEmpty())) {
                continue;
            }

            int home = h(curr.getKey(), size * 2);
            int pos = home;

            for (int i = 1; (newTable[pos] != null); i++) {
                pos = (home + (i * i)) % (size * 2);
            }
            newTable[pos] = curr;
        }

        this.table = newTable;
        this.size = size * 2;
    }


    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    // You can make this private in your project.
    // This is public for distributing the hash function in a way
    // that will pass milestone 1 without change.
    public int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % m);
    }
}
