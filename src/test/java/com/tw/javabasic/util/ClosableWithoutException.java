package com.tw.javabasic.util;

import java.util.List;

public class ClosableWithoutException implements AutoCloseable {
    private final List<String> logger;

    public ClosableWithoutException(List<String> logger) {
        this.logger = logger;
    }

    @Override
    public void close() {
        logger.add("ClosableWithoutException.close");
    }
}
