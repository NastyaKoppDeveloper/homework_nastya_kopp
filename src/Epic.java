import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description);
        this.subtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    @Override
    public String toString() {
        return "Epic{" + "name = " + getName() +
                ", description = " + getDescription() +
                ", status = " + getStatus() +
                ", subtasks = " + subtasks +
                ", id = " + getId() + "}\n";
    }
}
