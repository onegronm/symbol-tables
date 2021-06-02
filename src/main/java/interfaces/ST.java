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
     * total number of words
     * @return
     */
    int total();

    /**
     * All the keys in the table
     * @return
     */
    Iterable<Key> keys();

    /**
     * Smallest key
     * @return
     */
    Key min();

    /**
     * Largest key
     * @return
     */
    Key max();

    /**
     * Largest key less than or equal to key
     * @param key
     * @return
     */
    Key floor(Key key);

    /**
     * Smallest key greater than or equal to key
     * @param key
     * @return
     */
    Key ceiling(Key key);

    /**
     * Key of rank k
     * @param k
     * @return
     */
    Key select(int k);

    /**
     * Delete smallest key
     */
    void deleteMin();

    /**
     * Delete largest key
     */
    void deleteMax();

    /**
     * Number of keys in [lo...hi]
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi);

    /**
     * Number of keys strictly less than key
     * @param key
     * @return
     */
    int rank(Key key);

    /**
     * Keys in [lo...hi], in sorted order
     * @param lo
     * @param hi
     * @return
     */
    Iterable<Key> keys(Key lo, Key hi);
}