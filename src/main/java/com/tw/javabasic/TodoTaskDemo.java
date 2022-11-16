package com.tw.javabasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class TodoTaskDemo {
    public static void main(String[] args) {
        final var task = new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None);

        final var taskName = task.getSubTask()
                .flatMap(TodoTask::getSubTask)
                .flatMap(TodoTask::getSubTask)
                .map(TodoTask::getName)
                .orElse("");

        System.out.println(taskName);
    }
}
