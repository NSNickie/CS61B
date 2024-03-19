import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    public class BSTNode {
        public K key;
        public V value;
        BSTNode left;
        BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new BSTNode(key, value);
            this.size++;
            return;
        }
        putRecursion(root, key, value);
    }

    private BSTNode putRecursion(BSTNode node, K key, V value) {
        if (node == null) {
            this.size++;
            return new BSTNode(key, value);
        }
        if (node.key.compareTo(key) == 0) {
            node.value = value;
        } else if (node.key.compareTo(key) < 0) {
            node.left = putRecursion(node.left, key, value);
        } else {
            node.right = putRecursion(node.right, key, value);
        }
        return node;
    }

    @Override
    public V get(K key) {
        BSTNode node = getRecursion(this.root, key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    private BSTNode getRecursion(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        }
        if (node.key.compareTo(key) < 0) {
            return getRecursion(node.left, key);
        } else {
            return getRecursion((node.right), key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode node = containsRecursion(this.root, key);
        return node != null;
    }

    public BSTNode containsRecursion(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        }
        if (node.key.compareTo(key) < 0) {
            return containsRecursion(node.left, key);
        } else {
            return containsRecursion(node.right, key);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        clearRecursion(this.root);
        this.root = null;
    }

    public BSTNode clearRecursion(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            node.left = clearRecursion(node.left);
        }
        if (node.right != null) {
            node.right = clearRecursion(node.right);
        }
        this.size--;
        node = null;
        return node;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator<K>();
    }

    private class MyIterator<T> implements Iterator<T> {
        public BSTNode root;
        public BSTNode[] array;
        public int pointer;

        public MyIterator() {
            this.root = BSTMap.this.root;
            midOrderRecursion(this.root);
            this.pointer = 0;
        }

        private void midOrderRecursion(BSTNode node) {
            if (node == null) {
                return;
            }
            this.array[this.pointer] = node;
            this.pointer++;
            midOrderRecursion(node.left);
            midOrderRecursion(node.right);
        }

        public boolean hasNext() {
            int next = this.pointer + 1;
            return this.array[next] != null;
        }

        public T next() {
            if (!hasNext()) {
                return null;
            }
            return (T) this.array[++this.pointer];
        }
    }

}
