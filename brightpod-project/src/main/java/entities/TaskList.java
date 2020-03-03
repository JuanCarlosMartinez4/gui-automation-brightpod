package entities;

import java.util.HashMap;

public class TaskList {
    private String name;
    private String description;
    private boolean isVisibleToClients;

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
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets Task List description.
     * @param description value.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets visible to client.
     * @param visibleToClients value.
     */
    public void setVisibleToClients(boolean visibleToClients) {
        isVisibleToClients = visibleToClients;
    }

    public void setTaskListInformation(final HashMap<String, String> taskListInformation) {

    }
}
