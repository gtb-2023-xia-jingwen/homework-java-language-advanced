package com.tw.javabasic;

public class TodoTaskTagsComparator implements TodoTaskFilterableField<TodoTaskTag> {
    @Override
    public boolean compare(TodoTaskTag field, TodoTask task) {
        return field == task.getTag();
    }
}
