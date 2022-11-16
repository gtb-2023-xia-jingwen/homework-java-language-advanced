package com.tw.javabasic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class TodoTaskRepository {
    private final List<TodoTask> tasks;

    public TodoTaskRepository(List<TodoTask> tasks) {
        this.tasks = tasks;
    }

    public <T> List<TodoTask> find(T field, TodoTaskFilterableField<T> filter) {
        var tasksFound = new ArrayList<TodoTask>();
        for (final TodoTask task : tasks) {
            if(filter.compare(field, task)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public List<TodoTask> findByName(String name) {
        var tasksFound = new ArrayList<TodoTask>();
        for (final TodoTask task : tasks) {
            if(name.equals(task.getName())) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public List<TodoTask> findById(int givenId) {
        var tasksFound = new ArrayList<TodoTask>();
        for (final TodoTask task : tasks) {
            if(givenId == task.getID()) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public List<TodoTask> findByStatus(TodoTaskStatus givenStatus) {
        var tasksFound = new ArrayList<TodoTask>();
        for (final TodoTask task : tasks) {
            if(givenStatus == task.getStatus()) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public List<TodoTask> findByTag(TodoTaskTag givenTag) {
        var tasksFound = new ArrayList<TodoTask>();
        for (final TodoTask task : tasks) {
            if(givenTag == task.getTag()) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public <T> List<TodoTask> findWithJavaFunctionAPI(T field, BiPredicate<T, TodoTask> predicate) {
        var tasksFound = new ArrayList<TodoTask>();
        for (final TodoTask task : tasks) {
            if(predicate.test(field, task)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }
}
