package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private static int id;

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epicTasks = new HashMap<>();

    private HistoryManager historyManager = Managers.getDefaultHistory();

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public static void setStatus(Epic epic) {
        int countDone = 0;
        epic.setStatus(Status.NEW);
        for (Subtask subtask : epic.getSubtasks()) {
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                epic.setStatus(Status.IN_PROGRESS);
            } else if (subtask.getStatus() == Status.DONE) {
                countDone++;
                if (countDone == epic.getSubtasks().size()) {
                    epic.setStatus(Status.DONE);
                }
            }
        }
    }

    @Override
    public List<Subtask> getSubtasksByEpic(Epic epic) {
        List<Subtask> subtasksByEpic = new ArrayList<>();
        if (!epic.getSubtasks().isEmpty()) {
            System.out.println("В эпике " + epic.getTitle() + " , статус: " + epic.getStatus() + " подзадачи:");
            for (Subtask subtask : epic.getSubtasks()) {
                subtasksByEpic.add(subtask);
            }
        }
        return subtasksByEpic;
    }

    @Override
    public void deleteSubtask(int id) {
        for (Epic epic : epicTasks.values()) {
            epic.getSubtasks().remove(id);
            InMemoryTaskManager.setStatus(epic);
        }
    }

    @Override
    public void deleteEpicTask(int id) {
        epicTasks.remove(id);
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void updateSubtask(Subtask newSubtask) {
        for (Epic epic : epicTasks.values()) {
            for (Subtask subtask : epic.getSubtasks()) {
                if (subtask.getIdTask() == newSubtask.getIdTask()) {
                    subtask.setTitle(newSubtask.getTitle());
                    subtask.setDescription(newSubtask.getDescription());
                    subtask.setStatus(newSubtask.getStatus());
                    InMemoryTaskManager.setStatus(epic);
                }
            }
        }
    }

    @Override
    public void updateEpicTask(Epic newEpicTask) {
        for (Epic epic : epicTasks.values()) {
            if (epic.getIdTask() == newEpicTask.getIdTask()) {
                newEpicTask.setSubtasks(epic.getSubtasks());
                epicTasks.replace(epic.getIdTask(), newEpicTask);
            }
        }
        InMemoryTaskManager.setStatus(newEpicTask);
    }

    @Override
    public void updateTask(Task newTask) {
        for (Task task : tasks.values()) {
            if (task.getIdTask() == newTask.getIdTask()) {
                tasks.replace(task.getIdTask(), newTask);
            }
        }
    }

    @Override
    public List<Task> getAllSubtasks() {
        List<Task> allSubtasks = new ArrayList<>();
        for (Epic epic : epicTasks.values()) {
            for (Subtask subtask : epic.getSubtasks()) {
                allSubtasks.add(subtask);
            }
        }
        return allSubtasks;
    }

    @Override
    public List<Task> getAllEpicTasks() {
        List<Task> allEpicTasks = new ArrayList<>();
        for (Epic epic : epicTasks.values()) {
            allEpicTasks.add(epic);
        }
        return allEpicTasks;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            allTasks.add(task);
        }
        return allTasks;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = null;
        for (Epic epic : epicTasks.values()) {
            for (int i = 0; i < epic.getSubtasks().size(); i++) {
                if (epic.getSubtasks().get(i).getIdTask() == id) {
                    historyManager.add(epic.getSubtasks().get(i));
                    return epic.getSubtasks().get(i);
                }
            }
        }
        return subtask;
    }

    @Override
    public Epic getEpicTaskById(int id) {
        historyManager.add(epicTasks.get(id));
        return epicTasks.get(id);
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public void deleteAllSubtasks() {
        for (Epic epic : epicTasks.values()) {
            epic.getSubtasks().clear();
            InMemoryTaskManager.setStatus(epic);
        }
    }

    @Override
    public void deleteAllEpicTasks() {
        epicTasks.clear();
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void createSubtask(Epic epic, Subtask subtask) {
        epic.getSubtasks().add(subtask);
        InMemoryTaskManager.setStatus(epic);
        subtask.setEpic(epic);
    }

    @Override
    public void createEpicTask(Epic epic) {
        InMemoryTaskManager.setStatus(epic);
        epicTasks.put(epic.getIdTask(), epic);
    }

    @Override
    public void createTask(Task task) {
        tasks.put(task.getIdTask(), task);
    }

    public static int getId() {
        return ++id;
    }
}
