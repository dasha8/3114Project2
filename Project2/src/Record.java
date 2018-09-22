
/**
 * A Record is simply a container class for data that might be stored in the
 * hash table.
 * 
 * @author Dasha Savina (dasha8)
 * @version 09.18.18
 */
public class Record {

    private String key;
    private boolean isEmpty;


    /**
     * Constructor class takes a key value.
     * 
     * @param key
     *            Key value to be used to search hash table.
     */
    public Record(String key) {
        this.key = key;
        isEmpty = false;
    }


    /**
     * Gets key value
     * 
     * @return key value
     */
    public String getKey() {
        return key;
    }


    /**
     * Empties a record, to behave as a tombstone.
     */
    public void empty() {
        key = "";
        isEmpty = true;
    }


    /**
     * Checks if a record is empty AKA is a tombstone
     * 
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return isEmpty;
    }

}
