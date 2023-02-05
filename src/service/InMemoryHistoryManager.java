package service;

import model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InMemoryHistoryManager implements HistoryManager {

    static private final int historySize = 10;

    static private Queue<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (history.size() == historySize) {
            history.remove();
        }
        history.add(task);
    }

    @Override
    public Queue<Task> getHistory() {
        return history;
    }
}

