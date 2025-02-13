package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T>{
    private Object [] array;
    private int capacity = 10; // ёмкость
    private int size = 0; // действительный размер
    private static final Object[] empty = {};

    // Empty constructor
    public MyArrayList() {
        array = new Object[capacity];
    }

    // Constructor with size
    public MyArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public void trimToSize(){
        if (size < array.length)
            array = size == 0 ?  empty : Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size)
            return (E[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public void add(T el){
        checkCapacity();
        array[size++] = el;
    }

    private void checkCapacity(){
        if (size == array.length)
            grow();
    }

    private void grow(){
        int newCapacity = (int) Math.round(array.length * 1.5);
        array = Arrays.copyOf(array, newCapacity);
    }

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    public T getEl(int index){
        return (T) array[index];
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Iterable collection: [");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            sb.append((i == size-1) ? "]" : ", ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                return getEl(i++);
            }
        };
    }
}