package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private List<Subtask> subtasks = new ArrayList<>();

    public Epic(String title, String description, Status status, int idTask) {
        super(title, description, status, idTask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}

