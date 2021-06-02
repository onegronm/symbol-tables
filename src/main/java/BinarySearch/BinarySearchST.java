package BinarySearch;

import interfaces.ST;

import java.util.Arrays;

/**
 * Symbol table implementation with binary search in an ordered array.
 * Uses repeated doubling to resize the array
 * Takes advantage that keys are comparable to give us an efficient search
 * Search: log(N)
 * Insert: N
 * Problem: to insert, need to shift all greater keys over
 * Fine for relatively static symbol tables
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value>
    implements ST<Key, Value> {

    private static final int INIT_SIZE = 2;
    /**
     * Array of keys is sorted
     */
    private Key[] keys;
    private Value[] vals;
    private int size;
    private int total;

    public BinarySearchST() {
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

        total++;

        if (size >= vals.length) resize(2 * size);

        int index = rank(key);

        // key is already in table
        if (index < size && keys[index].compareTo(key) == 0) {
            vals[index] = val;
            return;
        }

        // shift larger keys over
        for (int i = size; i > index; i--) {
            keys[i] = keys[i-1];
            vals[i] = vals[i-1];
        }

        keys[index] = key;
        vals[index] = val;
        size++;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) return vals[index];

        return null;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        total--;
        int index = rank(key);

        // key not in table
        if (index == size || keys[index].compareTo(key) != 0)
            return;

        // shift values back
        for (int i = index; i < size - 1; i++) {
            keys[i] = keys[i+1];
            vals[i] = vals[i+1];
        }

        // resize to half if 1/4 full
        if (size > 0 && size == keys.length / 4) resize(keys.length / 2);

        keys[index] = null;
        vals[index] = null;
        size--;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains is null");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public Iterable<Key> keys() {
        return Arrays.asList(keys.clone());
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    /**
     * Resize parallel arrays to given capacity
     * @param n
     */
    private void resize(int n) {
        Key[] newArrKeys = (Key[]) new Comparable[n];

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
    public int rank(Key key) {
        int lo = 0;
        int hi = size - 1;
        try {
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
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
