package entities;

import java.util.HashMap;

public class Task {
    private String taskName;
    private String member;
    private String dueDate;
    private boolean HighPriority;

    /**
     * Gets Task name.
     * @return Task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets Member name.
     * @return Member name.
     */
    public String getMember() {
        return member;
    }

    /**
     * Gets Due Date value.
     * @return Due Date value.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Gets if Task is High Priority.
     * @return boolean value.
     */
    public boolean isHighPriority() {
        return HighPriority;
    }

    /**
     * Sets Task name.
     * @param taskName value.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets Member name.
     * @param member value.
     */
    public void setMember(String member) {
        this.member = member;
    }

    /**
     * Sets Due Date value.
     * @param dueDate value.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets High Priority value.
     * @param highPriority value.
     */
    public void setHighPriority(boolean highPriority) {
        HighPriority = highPriority;
    }

    public void setTaskInformation(final HashMap<String, String> taskInformation) {

    }
}
