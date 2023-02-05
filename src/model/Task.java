package model;

import service.InMemoryTaskManager;

public class Task {

    private String title;
    private String description;
    private Status status;
    private final int idTask;

    public Task(String title, String description,Status status, int idTask) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.idTask = setId(idTask);
    }

    public int getIdTask() {
        return idTask;
    }

    public static int setId(int id) {
        if (id>=0) {
            return id;
        }
        return InMemoryTaskManager.getId();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}

