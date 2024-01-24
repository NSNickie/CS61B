import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        for(int i=0;i<size()-1;i++){
            items[i]=items[i+1];
        }
        items[size-1]=null;
        size--;
        return null;
    }

    @Override
    public T removeLast() {
        items[size-1]=null;
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

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
