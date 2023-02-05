import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import service.InMemoryTaskManager;
import service.Managers;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        Epic epic1 = new Epic("Эпик", "Описание эпика", Status.NEW, InMemoryTaskManager.getId());
        Epic epic2 = new Epic("Эпик 2", "Описание эпика", Status.NEW, InMemoryTaskManager.getId());
        Epic oldEpic = new Epic("Эпик для замены", "Описание эпика", Status.NEW, InMemoryTaskManager.getId());
        Epic newEpic = new Epic("Поменяли эпик", "Описание эпика", Status.NEW, oldEpic.getIdTask());

        Task task1 = new Task("Простая задача", "Описание простой задачи", Status.NEW, InMemoryTaskManager.getId());
        Task task2 = new Task("Простая задача 2", "Описание простой задачи", Status.NEW, InMemoryTaskManager.getId());
        Task oldTask = new Task("Простая задача для замены", "Описание простой задачи", Status.NEW, InMemoryTaskManager.getId());
        Task newTask = new Task("Поменяли простую задачу", "Описание простой задачи", Status.NEW, oldTask.getIdTask());

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание", Status.DONE, InMemoryTaskManager.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", Status.DONE, InMemoryTaskManager.getId());
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание", Status.NEW, InMemoryTaskManager.getId());
        Subtask subtask4 = new Subtask("Подзадача 4", "Описание", Status.NEW, InMemoryTaskManager.getId());
        Subtask oldSubtask = new Subtask("Подзадача для замены", "Описание", Status.IN_PROGRESS, InMemoryTaskManager.getId());
        Subtask newSubtask = new Subtask("Поменяли подзадачу", "Описание", Status.NEW, oldSubtask.getIdTask());

        TaskManager taskManager = Managers.getDefault();
        InMemoryTaskManager inMemoryTaskManager = (InMemoryTaskManager) taskManager;

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(oldTask);

        taskManager.createEpicTask(epic1);
        taskManager.createSubtask(epic1,subtask1);
        taskManager.createSubtask(epic1,subtask2);

        taskManager.createEpicTask(epic2);
        taskManager.createSubtask(epic2,subtask3);
        taskManager.createSubtask(epic2,subtask4);
        taskManager.createSubtask(epic2,oldSubtask);

        taskManager.createEpicTask(oldEpic);

        System.out.println("Эпики с подзадачами: ");
        System.out.println(taskManager.getSubtasksByEpic(epic1));
        System.out.println(taskManager.getSubtasksByEpic(epic2));
        System.out.println("--------------------------");

        System.out.println("Поменяем эпик:");
        System.out.println("До:");
        System.out.println(taskManager.getAllEpicTasks());
        taskManager.updateEpicTask(newEpic);
        System.out.println("После:");
        System.out.println(taskManager.getAllEpicTasks());
        System.out.println("Эпик с подзадачами после изменения:");
        System.out.println(taskManager.getSubtasksByEpic(taskManager.getEpicTaskById(epic2.getIdTask())));
        System.out.println("---------------------------");

        System.out.println("Поменяли подзадачу:");
        System.out.println("До:");
        System.out.println(taskManager.getSubtasksByEpic(taskManager.getEpicTaskById(epic2.getIdTask())));
        taskManager.updateSubtask(newSubtask);
        System.out.println("После:");
        System.out.println(taskManager.getSubtasksByEpic(taskManager.getEpicTaskById(epic2.getIdTask())));
        System.out.println("----------------------------");

        System.out.println("Посмотрим историю задач:");
        System.out.println(inMemoryTaskManager.getHistoryManager().getHistory());
        System.out.println("----------------------------");
        System.out.println("Посмотрим 10 задач и посмотрим как изменилась история:");
        taskManager.getTaskById(task1.getIdTask());
        taskManager.getSubtaskById(subtask3.getIdTask());
        taskManager.getEpicTaskById(oldEpic.getIdTask());
        taskManager.getSubtaskById(subtask2.getIdTask());
        taskManager.getSubtaskById(subtask1.getIdTask());
        taskManager.getEpicTaskById(epic2.getIdTask());
        taskManager.getTaskById(task2.getIdTask());
        taskManager.getTaskById(task1.getIdTask());
        taskManager.getSubtaskById(subtask3.getIdTask());
        taskManager.getSubtaskById(subtask4.getIdTask());
        System.out.println(inMemoryTaskManager.getHistoryManager().getHistory());
        System.out.println("---------------------------");

        System.out.println("Удалим все подзадачи:");
        taskManager.deleteAllSubtasks();
        System.out.println(taskManager.getAllSubtasks());
        System.out.println("Изменения в эпиках после удаления:");
        System.out.println(taskManager.getAllEpicTasks());
    }
}
