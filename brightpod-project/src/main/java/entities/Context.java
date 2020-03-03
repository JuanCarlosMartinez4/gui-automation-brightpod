package entities;

public class Context {
    private Pod pod;
    private TaskList taskList;
    private Task task;

    /**
     * Generates a new instance of context.
     */
    public Context() {
        this.pod = new Pod();
        this.taskList = new TaskList();
        this.task = new Task();
    }

    /**
     * Gets Pod instance.
     * @return Pod instance.
     */
    public Pod getPod() {
        return pod;
    }

    /**
     * Gets Task List instance.
     * @return Task List instance.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Gets Task instance.
     * @return Task instance.
     */
    public Task getTask() {
        return task;
    }
}
