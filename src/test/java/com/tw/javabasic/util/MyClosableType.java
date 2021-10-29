package com.tw.javabasic.util;

public class MyClosableType implements AutoCloseable {
    private final ClosableStateReference reference;

    public MyClosableType(ClosableStateReference reference) {
        this.reference = reference;
    }

    public boolean isClosed() {
        return reference.isClosed();
    }

    @Override
    public void close() {
        reference.close();
    }
}
