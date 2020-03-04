package entities;

import java.util.HashMap;
import java.util.Map;

public class TaskList {
    private String name;
    private String description;
    private boolean isVisibleToClients;

    private Map<String, String> taskListValues;

    /**
     * Gets Task List name.
     * @return Task List name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Task List description.
     * @return Task List description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets if is visible to client.
     * @return boolean value.
     */
    public boolean isVisibleToClients() {
        return isVisibleToClients;
    }

    /**
     * Sets Task List name.
     * @param name value.
     */
    private void setName(final String name) {
        if (name == null) return;
        this.name = name;
    }

    /**
     * Sets Task List description.
     * @param description value.
     */
    private void setDescription(final String description) {
        if (name == null) return;
        this.description = description;
    }

    /**
     * Sets visible to client.
     * @param visibleToClients value.
     */
    private void setVisibleToClients(final boolean visibleToClients) {
        if (name == null) return;
        isVisibleToClients = visibleToClients;
    }

    public void setTaskListInformation(final Map<String, String> taskListInformation) {
        setName(taskListInformation.get("name"));
        setDescription(taskListInformation.get("description"));
        setVisibleToClients(Boolean.parseBoolean(taskListInformation.get("isVisible")));
    }

    public Map<String, String> getTaskListInformation() {
        taskListValues = new HashMap<>();
        taskListValues.put("name", getName());
        taskListValues.put("description", getDescription());
        taskListValues.put("isVisible", Boolean.toString(isVisibleToClients()));
        return taskListValues;
    }
}
