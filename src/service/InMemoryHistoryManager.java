package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    static private final int historySize = 10;

    static private List<Task> history = new ArrayList<>(historySize);

    @Override
    public void add(Task task) {
        if (history.size() == historySize) {
            history.remove(0);
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}

