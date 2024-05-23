package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * A hash table-backed Map implementation.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void put(K key, V value) {

        int bucket = this.getBucketByKey(key);
        if (this.containsKey(key)) {
            for (Node node : this.buckets[bucket]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }

        }
        Node node = new Node(key, value);
        this.buckets[bucket].add(node);
        this.size++;
        resize();
    }

    @Override
    public V get(K key) {

        for (Node node : this.buckets[this.getBucketByKey(key)]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public int getBucketByKey(K key) {
        int hashcode = key.hashCode();
        return Math.floorMod(hashcode, this.capacity);
    }

    @Override
    public boolean containsKey(K key) {
        for (Node node : this.buckets[this.getBucketByKey(key)]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.capacity; i++) {
            this.buckets[i].clear();
        }
    }

    public void resize() {
        double currentFactor = (double) this.size / this.capacity;
        if (currentFactor >= this.loadFactor) {

            this.capacity = (int) Math.floor((this.capacity / this.loadFactor));
            Collection<Node>[] newBuckets = new Collection[this.capacity];

            for (int i = 0; i < this.capacity; i++) {
                newBuckets[i] = this.createBucket();
            }

            for (Collection<Node> list : this.buckets) {
                for (Node node : list) {
                    newBuckets[this.getBucketByKey(node.key)].add(node);
                }
            }
            this.buckets = newBuckets;
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /**
     * Constructors
     */
    public static final int DEFAULT_CAPACITY = 16;
    public static final double DEFAULT_LOADFACTOR = 0.75;
    public int capacity;
    public double loadFactor;

    private int size;

    public MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOADFACTOR;
        this.initializeBucket();

    }

    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.loadFactor = DEFAULT_LOADFACTOR;
        this.initializeBucket();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor      maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.initializeBucket();
    }

    public void initializeBucket() {
        this.buckets = new Collection[this.capacity];
        this.size = 0;
        for (int i = 0; i < this.capacity; i++) {
            this.buckets[i] = this.createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
