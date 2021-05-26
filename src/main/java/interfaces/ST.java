package interfaces;

/**
 * Assumes that the keys are comparable
 */
public interface ST<Key extends Comparable<Key>, Value> {
    /**
     * put key-value pair into the table (remove key from table if value is null)
     * @param key
     * @param val
     */
    void put(Key key, Value val);

    /**
     * value paired with key (null if key is absent)
     * @param key
     * @return
     */
    Value get(Key key);

    /**
     * remove key (and its value) from table
     * @param key
     */
    void delete(Key key);

    /**
     * is there a value paired with key?
     * @param key
     * @return
     */
    boolean contains(Key key);

    /**
     * Is the table empty?
     * @return
     */
    boolean isEmpty();

    /**
     * number of key-value pairs in table
     * @return
     */
    int size();

    /**
     * All the keys in the table
     * @return
     */
    Iterable<Key> keys();
}