package com.tw.javabasic;

public class TodoTaskNameComparator implements TodoTaskFilterableField<String> {
    @Override
    public boolean compare(String field, TodoTask task) {
        return field.equals(task.getName());
    }
}
