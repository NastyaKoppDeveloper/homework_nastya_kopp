public class Subtask extends Task {
    private Epic epic;

    public Subtask(String name, String description, Epic epic) {
        super(name, description);
        this.epic = epic;
        epic.addSubtask(this);
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public Epic getEpic() {
        return this.epic;
    }

    @Override
    public String toString() {
        return "Subtask {" +
                "name = " + getName() +
                ", description = " + getDescription() +
                ", status = " + getStatus() +
                ", epic = " + epic.getName() +
                ", id = " + getId() +
                "}\n";
    }
}
