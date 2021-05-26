package BinarySearch;

import interfaces.ST;

/**
 * Binary search with an ordered array of key-value pairs. Uses repeated doubling to resize the array
 * Takes advantage that keys are comparable to give us an efficient search
 * Search: log(N)
 * Insert: N
 * Problem: to insert, need to shift all greater keys over
 * Fine for relatively static symbol tables
 * @param <Key>
 * @param <Value>
 */
public class ArrayST<Key extends Comparable<Key>, Value>
    implements ST<Key, Value> {

    private static final int INIT_SIZE = 2;
    /**
     * Array of keys is sorted
     */
    private Key[] keys;
    private Value[] vals;
    private int size;

    public ArrayST() {
        keys = (Key[]) new Comparable[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
    }

    /**
     * Use binary search to find place to insert; shift all larger keys over
     * takes linear time in worst case
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        delete(key); // handle duplicates

        if (size >= vals.length) resize(2 * size);

        int index = rank(key);

        // shift larger keys over
        for (int i = keys.length - 1; i >= index; i--) {
            keys[i] = keys[i-1];
            vals[i] = vals[i=1];
        }

        keys[index] = key;
        vals[index] = val;
        size++;
    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    /**
     * Resize parallel arrays to given capacity
     * @param n
     */
    private void resize(int n) {
        Key[] newArrKeys = (Key[]) new Object[n];

        for(int i = 0; i < keys.length; i++) {
            newArrKeys[i] = keys[i];
        }

        Value[] newArrVals = (Value[]) new Object[n];

        for(int i = 0; i < vals.length; i++) {
            newArrVals[i] = vals[i];
        }

        keys = newArrKeys;
        vals = newArrVals;
    }

    /**
     * Uses a binary search to return the number of keys in this symbol table strictly less than key
     * @param key
     * @return
     */
    private int rank(Key key) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0){
                lo = mid + 1;
            }
            else if (cmp < 0) {
                hi = mid - 1;
            }
            else return mid;
        }
        return lo;
    }

}
