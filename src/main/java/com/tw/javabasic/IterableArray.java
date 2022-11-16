package com.tw.javabasic;

import java.util.Iterator;

public class IterableArray<T> implements Iterable<T>{
    private final T[] elements;

    public IterableArray(T[] elements) {
        this.elements = elements;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < elements.length;
            }

            @Override
            public T next() {
                return hasNext() ? elements[currentIndex++] : null;
            }
        };
    }
}
