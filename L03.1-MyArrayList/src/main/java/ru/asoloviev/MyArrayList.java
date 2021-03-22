package ru.asoloviev;

import java.util.*;

public class MyArrayList<T> implements List<T>, Cloneable {

    private Object[] data;
    private int size;

    public MyArrayList() {
        this.data = new Object[0];
    }

    public MyArrayList(int size) {
        if (size > 0) {
            this.data = new Object[size];
        } else if (size == 0) {
            this.data = new Object[0];
        } else {
            throw new IllegalArgumentException("Wrong initial size = " + size);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(object)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        int cursor;
        int lastReturnedElement = -1;

        MyIterator() {}

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elements = MyArrayList.this.data;
            cursor = i + 1;
            lastReturnedElement = i;
            return (T) elements[i];
        }
    }



    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size == data.length) {
            data = grow();
        }
        data[size] = t;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                index = i;
                break;
            }
        }
        if (index > 0) {
            int newSize;
            if ((newSize = size - 1) > index) {
                System.arraycopy(data, index + 1, data, index, newSize - index);
            }
            data[newSize] = null;
            size = newSize;
            return true;
        } else {
            return false;
        }
    }



    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] appendArray = c.toArray();
        if (appendArray.length == 0) {
            return false;
        }
        while (appendArray.length > (data.length - size)) {
            data = grow();
        }
        System.arraycopy(appendArray, 0, data, size, appendArray.length);
        size = size + appendArray.length;
        return true;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i =  0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
        data = new Object[size];
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Wrong index value = " + index);
        }
        data[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private Object[] grow() {
        return Arrays.copyOf(data, (size + 1) * 2);
    }

    @Override
    protected Object clone() {
        try {
            MyArrayList<?> list = (MyArrayList<?>) super.clone();
            list.data = Arrays.copyOf(data, size);
            return list;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) data, 0, size, c);
    }


}
