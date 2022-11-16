package com.tw.javabasic;

public interface TodoTaskFilterableField<T> {
    boolean compare(T field, TodoTask task);
}
