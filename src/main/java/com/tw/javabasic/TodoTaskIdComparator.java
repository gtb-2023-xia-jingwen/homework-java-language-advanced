package com.tw.javabasic;

public class TodoTaskIdComparator implements TodoTaskFilterableField<Integer> {
    @Override
    public boolean compare(Integer field, TodoTask task) {
        return field == task.getID();
    }
}
