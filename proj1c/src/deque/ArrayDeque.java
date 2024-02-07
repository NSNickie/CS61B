package deque;

import java.util.*;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private final int REFACTOR;
    private Object[] items;

    public ArrayDeque() {
        size = 0;
        items = new Object[8];
        REFACTOR = 2;
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
        if (size < items.length) {
            items[0] = x;
            for (int i = 1; i < items.length; i++) {
                items[i] = items[i - 1];
            }
        } else {
            items = Arrays.copyOf(items, size * REFACTOR);
            items[0] = x;
            for (int i = 1; i < size + 1; i++) {
                items[i] = items[i - 1];
            }
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size >= items.length) {
            items = Arrays.copyOf(items, size * REFACTOR);
        }
        items[size] = x;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            list.add((T) items[i]);
        }
        return list;
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
    public T removeFirst() {
        for (int i = 0; i < size() - 1; i++) {
            items[i] = items[i + 1];
        }
        items[size - 1] = null;
        size--;
        return null;
    }

    @Override
    public T removeLast() {
        items[size - 1] = null;
        size--;
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > items.length) {
            return null;
        }
        return (T) items[index - 1];
    }

    public String toString() {
        return toList().toString();
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public boolean equals() {
        return false;
    }

    public boolean contains(Object t) {
        for (Object x : items) {
            if (x == t) {
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArrayDeque otherObj) {
            if (this.size() != otherObj.size()) {
                return false;
            }
            for (Object x : this) {
                if (!(otherObj).contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
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
        return new ArrayDequeIterator();
    }
}

