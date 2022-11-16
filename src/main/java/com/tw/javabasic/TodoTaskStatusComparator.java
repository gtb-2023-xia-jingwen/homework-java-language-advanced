package com.tw.javabasic;

public class TodoTaskStatusComparator implements TodoTaskFilterableField<TodoTaskStatus> {
    @Override
    public boolean compare(TodoTaskStatus field, TodoTask task) {
        return field == task.getStatus();
    }
}
