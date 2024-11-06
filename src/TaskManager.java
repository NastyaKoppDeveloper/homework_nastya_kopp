import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
    static int counter = 1; // поле-счетчик для создания идентификатора задачи любого типа

    private final HashMap<Integer, Task> tasks = new HashMap<>();

    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int createId() {
        return (counter++);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(this.epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(this.subtasks.values());
    }

    public void deleteAllTasks() {
        this.tasks.clear();
    }

    public void deleteAllEpics() {
        this.epics.clear();
        this.subtasks.clear();
    }

    public void deleteAllSubtasks() {
        this.subtasks.clear();
        for (Epic epic : epics.values()) {
            recalculateEpicStatus(epic);
        }
    }

    public Task getTaskById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Передан некорректный идентификатор задачи. Ожидается положительное целое число.");
        }
        return tasks.get(id);

    }

    public Epic getEpicById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Передан некорректный идентификатор эпика. Ожидается положительное целое число.");
        }
        return epics.get(id);

    }

    public Subtask getSubtaskById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Передан некорректный идентификатор подзадачи. Ожидается положительное целое число.");
        }
        return subtasks.get(id);

    }

    public void createTask(Task task) {
        int id = createId();
        task.setId(id);
        tasks.put(id, task);
    }

    public void createEpic(Epic epic) {
        int id = createId();
        epic.setId(id);
        epics.put(id, epic);
    }

    public void createSubtask(Subtask subtask) {
        int id = createId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        recalculateEpicStatus(subtask.getEpic());
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        recalculateEpicStatus(subtask.getEpic());
    }

    public void deleteTaskById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Передан некорректный идентификатор удаляемой задачи: он не является положительным целым числом.");
        }
        this.tasks.remove(id);
    }

    public void deleteEpicById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Передан некорректный идентификатор удаляемого эпика: он не является положительным целым числом.");
        }
        epics.get(id).getSubtasks().clear();
        epics.remove(id);
    }

    public void deleteSubtaskById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Передан некорректный идентификатор удаляемой подзадачи: он не является положительным целым числом.");
        }

        this.getSubtaskById(id).getEpic().getSubtasks().remove(getSubtaskById(id));
        recalculateEpicStatus(this.subtasks.get(id).getEpic());
        this.subtasks.remove(id);
    }

    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        ArrayList<Subtask> subtasks = new ArrayList<>();

        for (Epic currentEpic : epics.values()) {
            if(currentEpic.equals(epic)) {
                subtasks = currentEpic.getSubtasks();
                break;
            }
        }

        return subtasks;
    }

    public void recalculateEpicStatus(Epic epic) {

        if (this.getSubtasksByEpic(epic).equals(Collections.emptyList())) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        int subtasksCount = epic.getSubtasks().size();
        int subtasksInStatusNewCount = 0;
        int subtasksInStatusDoneCount = 0;

        for (Subtask s : this.getSubtasksByEpic(epic)) {
            if (s.getStatus().equals(TaskStatus.NEW)) {
                subtasksInStatusNewCount++;
            } else if (s.getStatus().equals(TaskStatus.DONE)) {
                subtasksInStatusDoneCount++;
            }
        }

        if (subtasksInStatusNewCount == subtasksCount) {
            epic.setStatus(TaskStatus.NEW);
        } else if (subtasksInStatusDoneCount == subtasksCount) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }

    }

}
