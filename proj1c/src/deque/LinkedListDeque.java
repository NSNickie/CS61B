package deque;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {

    private static int size;
    private final Node sentinel = new Node();
    private Node lastNode;

    public LinkedListDeque() {
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        lastNode = sentinel;
    }

    public String toString() {
        return toList().toString();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int pos;

        public LinkedListIterator() {
            pos = 1;
        }

        @Override
        public boolean hasNext() {
            return pos <= size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public class Node {
        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public Node() {
            this.prev = null;
            this.item = null;
            this.next = null;
        }

        public Node prev;
        public T item;
        public Node next;

    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T max(Comparator<T> c) {
        return null;
    }

    @Override
    public void addFirst(T x) {
        if (size() != 0) {
            Node originNode = sentinel.next;
            Node node = new Node();
            this.sentinel.next = node;
            node.prev = this.sentinel;
            node.item = x;
            node.next = originNode;
            originNode.prev = node;

        } else {
            Node node = new Node();
            this.sentinel.next = node;
            this.sentinel.prev = node;
            node.prev = this.sentinel;
            node.item = x;
            node.next = this.sentinel;
            lastNode = node;

        }

        size++;

    }

    @Override
    public void addLast(T x) {
        if (size() != 0) {
            Node node = new Node();
            node.prev = lastNode;
            node.item = x;
            node.next = sentinel;
            lastNode.next = node;
            lastNode = node;
            sentinel.prev = node;

        } else {
            Node node = new Node();
            this.sentinel.next = node;
            this.sentinel.prev = node;
            node.prev = this.sentinel;
            node.item = x;
            node.next = this.sentinel;
            lastNode = node;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        ArrayList<T> list = new ArrayList<T>();
        int originSize = size;
        Node pointer = sentinel;

        while (originSize > 0) {
            list.add(pointer.next.item);
            pointer = pointer.next;
            originSize--;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return null;

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        lastNode.prev.next = sentinel;
        sentinel.prev = lastNode.prev;
        lastNode = lastNode.prev;
        size--;

        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        int localSize = size;
        Node node = sentinel;
        while (localSize != 0 && index > 0) {
            node = node.next;
            index--;
            localSize--;
        }
        if (localSize == 0) {
            if (index == 0) {
                return node.item;
            }
            return null;
        } else {
            return node.item;
        }

    }

    @Override
    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        }
        return loop(index, sentinel);
    }

    @Override
    public boolean equals() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LinkedListDeque otherObj) {
            if(this.size()!=otherObj.size()){
                return false;
            }
            for(Object x:this){
                if(!(otherObj).contains(x)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean contains(Object t){
        int localSize=size();
        Node node=sentinel;
        while(localSize!=0){
            if(node.next.item==t){
                return true;
            }else{
                node=node.next;
                localSize--;
            }
        }
        return false;
    }



    public T loop(int index, Node node) {
        if (node == null) {
            return null;
        }
        if (index == 0) {
            return node.item;
        } else {
            loop(index--, node.next);
        }
        return null;
    }


}
