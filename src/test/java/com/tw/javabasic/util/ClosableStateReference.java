package com.tw.javabasic.util;

public class ClosableStateReference {
    private boolean isClosed;

    public boolean isClosed() {
        return isClosed;
    }

    public void close() {
        isClosed = true;
    }
}
