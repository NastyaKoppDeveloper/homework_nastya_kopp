public class Main {

    public static void main(String[] args) {

        Task task1 = new Task("task1_name","task1_description");
        Task task2 = new Task("task2_name","task2_description");
        TaskManager taskManager = new TaskManager();
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Epic epic1 = new Epic("epic1_name","epic1_description");
        Epic epic2 = new Epic("epic2_name","epic2_description");
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

        Subtask subtask1 = new Subtask("subtask1_name","subtask1_description", epic1);
        Subtask subtask2 = new Subtask("subtask2_name","subtask2_description", epic1);
        Subtask subtask3 = new Subtask("subtask3_name","subtask3_description", epic2);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        System.out.println("Созданы эпики:\n" + taskManager.getEpics());
        System.out.println("Созданы задачи:\n" + taskManager.getTasks());
        System.out.println("Созданы подзадачи:\n" + taskManager.getSubtasks());

        task1.setStatus(TaskStatus.DONE);
        task1.setDescription("task1_new text for description");
        taskManager.updateTask(task1);
        System.out.println("Атрибуты задачи task1 после обновления атрибутов = " + task1);

        System.out.println("Атрибуты эпика2 ДО обновления статуса подзадачи: " + epic2);
        subtask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask3);
        System.out.println("Атрибуты эпика2 ПОСЛЕ обновления статуса подзадачи: " + epic2);

        System.out.println("Атрибуты эпика1 ДО удаления единственной подзадачи:\n" + epic1);
        System.out.println("Атрибуты эпика1 ПОСЛЕ удаления единственной подзадачи:\n" + epic1);

        taskManager.deleteTaskById(task2.getId());
        taskManager.deleteEpicById(epic2.getId());

    }
}
