package com.tw.javabasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoTask {
    private final int ID;
    private final String Name;
    private final TodoTaskStatus Status;
    private final TodoTaskTag Tag;
    private List<String> Notes = new ArrayList<>();

    private TodoTask subTask;
    public Optional<TodoTask> getSubTask() {
        return Optional.ofNullable(subTask);
    }

    public TodoTask(int ID, String name, TodoTaskStatus status) {
        this(ID, name,status, TodoTaskTag.None);
    }

    public TodoTask(int ID, String name, TodoTaskStatus status, TodoTaskTag tag) {
        this.ID = ID;
        Name = name;
        Status = status;
        Tag = tag;
    }

    public TodoTask(int ID, String name, TodoTaskStatus status, TodoTaskTag tag, TodoTask subTask) {
        this(ID, name,status, tag);
        this.subTask = subTask;
    }

    public TodoTaskTag getTag() {
        return Tag;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public TodoTaskStatus getStatus() {
        return Status;
    }
    public void setNotes(List<String> notes) {
        Notes = notes;
    }
    public List<String> getNotes() {
        return Notes;
    }

    @Override
    public String toString() {
        return "TodoTask { " +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Status=" + Status +
                " }";
    }
}
