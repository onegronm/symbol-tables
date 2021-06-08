package BinarySearchTree;

import edu.princeton.cs.algs4.Queue;
import interfaces.ST;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key , Value> {
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val){
            this.key = key;
            this.val = val;
        }
    }

    Node root;
    int total;

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
        total++;
    }

    private Node put(Node x, Key key, Value val) {
        // key in tree => reset value
        // key not in tree => add new node, reset links on the way up
        // cost: number of compare is equal to 1 + depth of node
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;

        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    @Override
    public int total() {
        return total;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }

    @Override
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        else return min(x.left);
    }

    @Override
    public Key max() {
        if(isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is empty");
        if (isEmpty()) throw new NoSuchElementException("calls floor with empty symbol table");

        return floor(root, key, null);
    }

    private Key floor(Node x, Key key, Key best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor(x.left, key, null);
        if (cmp > 0) return floor(x.right, key, x.key);
        return x.key;
    }

    @Override
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is empty");
        if (isEmpty()) throw new NoSuchElementException("calls floor with empty symbol table");
        return ceiling(root, key, null);
    }

    private Key ceiling(Node x, Key key, Key best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return ceiling(x.right, key, x.key);
        if (cmp > 0) return ceiling(x.left, key, null);
        return x.key;
    }

    @Override
    public Key select(int k) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Key key) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteMin() {
        throw new NotImplementedException();
    }

    @Override
    public void deleteMax() {
        throw new NotImplementedException();
    }

    @Override
    public int size(Key lo, Key hi) {
        throw new NotImplementedException();
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        if (x == null) throw new IllegalArgumentException("argument to rank() is null");
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        if (cmp > 0) return 1 + size(x) + rank(key, x.right);
        return size(x.left);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        throw new NotImplementedException();
    }

    private void inorder(Node x, Queue<Key> q){
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }
}