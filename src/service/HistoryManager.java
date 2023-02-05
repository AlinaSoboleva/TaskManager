package service;

import model.Task;

import java.util.List;
import java.util.Queue;

public interface HistoryManager {

    void add(Task task);

    Queue<Task> getHistory();
}
