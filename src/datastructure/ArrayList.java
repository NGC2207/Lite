package datastructure;

import java.util.Arrays;

//public class ArrayList<E> implements List<E> {
//    private static final int DEFAULT_CAPACITY = 10;
//    private static final Object[] EMPTY = {};
//    private Object[] elementData;
//    private int size = 0;
//
//    public ArrayList() {
//        elementData = EMPTY;
//    }
//
//    @Override
//    public void clear() {
//        final Object[] es = elementData;
//        for (int to = size, i = size = 0; i < to; i++) {
//            es[i] = null;
//        }
//    }
//
//    @Override
//    public void createList(E[] elements) {
//        isNull(elements);
//        clear();
//        int numNew = elements.length;
//        if (numNew == 0) {
//            return;
//        }
//        Object[] elementData;
//        final int s;
//        if (numNew > (elementData = this.elementData).length - (s = size)) {
//            elementData = grow(s + numNew);
//        }
//        System.arraycopy(elements, 0, elementData, s, numNew);
//        size = s + numNew;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public E get(int index) {
//        rangeCheckWithoutSize(index);
//        return getElem(index);
//    }
//
//    @SuppressWarnings("unchecked")
//    private E getElem(int index) {
//        return (E) elementData[index];
//    }
//
//    @Override
//    public void add(int index, E element) {
//        rangeCheckWithSize(index);
//        final int s;
//        Object[] elementData;
//        if ((s = size) == (elementData = this.elementData).length) {
//            elementData = grow();
//        }
//        System.arraycopy(elementData, index, elementData, index + 1, s -
//                index);
//        elementData[index] = element;
//        size = s + 1;
//    }
//
//    @Override
//    public E remove(int index) {
//        rangeCheckWithoutSize(index);
//        final Object[] es = elementData;
//        @SuppressWarnings("unchecked") final E oldValue = (E) es[index];
//        final int newSize;
//        if ((newSize = size - 1) > index) {
//            System.arraycopy(es, index + 1, es, index, newSize - index);
//        }
//        es[newSize] = null;
//        size = newSize;
//        return oldValue;
//    }
//
//    @Override
//    public void reserve() {
//        Object[] elementData;
//        int i = 0;
//        int j = size - 1;
//        elementData = this.elementData;
//        while (i < j) {
//            Object temp = elementData[i];
//            elementData[i] = elementData[j];
//            elementData[j] = temp;
//            i++;
//            j--;
//        }
//    }
//
//    private void rangeCheckWithSize(int index) {
//        if (index > size || index < 0) {
//            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
//        }
//    }
//
//    private void rangeCheckWithoutSize(int index) {
//        if (index >= size || index < 0) {
//            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
//        }
//    }
//
//    private Object[] grow() {
//        return grow(size + 1);
//    }
//
//    private Object[] grow(int minCapacity) {
//        int oldCapacity = elementData.length;
//        if (oldCapacity > 0 || elementData != EMPTY) {
//            int newCapacity = oldCapacity + (oldCapacity >> 1);
//            if (newCapacity - minCapacity < 0) {
//                newCapacity = minCapacity;
//            }
//            return elementData = Arrays.copyOf(elementData, newCapacity);
//        } else {
//            return elementData = new Object[Math.max(DEFAULT_CAPACITY,
//                    minCapacity)];
//        }
//    }
//
//    private void isNull(E[] elementData) {
//        if (elementData == null) {
//            throw new IllegalArgumentException("error in Structure.ArrayList, elements is null ");
//        }
//    }
//}
