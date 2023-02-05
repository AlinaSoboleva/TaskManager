package model;

public class Subtask extends Task{

    private Epic epic;

    public Subtask(String title, String description, Status status, int idTask) {
        super(title, description, status, idTask);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}

