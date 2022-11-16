package com.tw.javabasic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class Stream<T> {
    public static <T> Stream<T> generate(Supplier<T> supplier) {
        final var iterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return supplier.get();
            }
        };
        return new Stream<>(iterator);
    }

    public Iterator<T> getIterator() {
        return iterator;
    }

    private Iterator<T> iterator;

    public Stream(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    public Stream<T> filter(Predicate<T> predicate) {
        final var newIterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                final var element = iterator.next();
                if (!predicate.test(element)) {
                    return null;
                }
                return element;
            }
        };
        return new Stream<T>(newIterator);
    }

    public List<T> collect() {
        final var results = new ArrayList<T>();
        while (iterator.hasNext()){
            final var element = iterator.next();
            if(element != null)
                results.add(element);
        }

        return results;
    }

    public <C> C collect(Supplier<C> supplier, BiConsumer<C, T> accumulator){
        final var container = supplier.get();
        while (iterator.hasNext()) {
            final var element = iterator.next();
            if(element != null)
                accumulator.accept(container, element);
        }
        return container;
    }

    public <R> Stream<R> map(Function<T, R> mapper) {
        final var newIterator = new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                final var element = iterator.next();
                return element == null ? null : mapper.apply(element);
            }
        };
        return new Stream<>(newIterator);
    }

    public <R> R reduce(R identity, BiFunction<R,T,R> accumulator) {
        var result = identity;
        while (iterator.hasNext()){
            final var element = iterator.next();
            result = accumulator.apply(result, element);
        }

        return result;
    }
}
