package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {

    List<Subtask> getSubtasksByEpic(Epic epic);

    void deleteSubtask(int id);

    void deleteEpicTask(int id);

    void deleteTask(int id);

    void updateSubtask(Subtask newSubtask);

    void updateEpicTask(Epic newEpicTask);

    void updateTask(Task newTask);

    List<Task> getAllSubtasks();

    List<Task> getAllEpicTasks();

    List<Task> getAllTasks();

    Task getSubtaskById(int id);

    Epic getEpicTaskById(int id);

    Task getTaskById(int id);

    void deleteAllSubtasks();

    void deleteAllEpicTasks();

    void deleteAllTasks();

    void createSubtask(Epic epic, Subtask subtask);

    void createEpicTask(Epic epic);

    void createTask(Task task);
}
