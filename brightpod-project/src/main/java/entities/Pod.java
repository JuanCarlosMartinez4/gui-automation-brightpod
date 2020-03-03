package entities;

import java.util.HashMap;

public class Pod {
    private String podName;
    private String startDate;
    private String dueDate;
    private String budgetedTime;
    private String client;
    private String podLead;
    private String podColor;
    private String description;

    /**
     * Gets pod name.
     * @return pod name.
     */
    public String getPodName() {
        return podName;
    }

    /**
     * Gets Start Date.
     * @return Start Date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets Due Date.
     * @return Due Date.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Gets Budgeted Time.
     * @return Budgeted Time.
     */
    public String getBudgetedTime() {
        return budgetedTime;
    }

    /**
     * Gets Client name.
     * @return Client name.
     */
    public String getClient() {
        return client;
    }

    /**
     * Gets Pod Lead Name.
     * @return Pod Lead Name.
     */
    public String getPodLead() {
        return podLead;
    }

    /**
     * Gets Pod color.
     * @return Pod color.
     */
    public String getPodColor() {
        return podColor;
    }

    /**
     * Gets pod Description.
     * @return pod Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets Pot Name.
     * @param podName value.
     */
    public void setPodName(String podName) {
        this.podName = podName;
    }

    /**
     * Sets Start Date.
     * @param startDate value.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets Due Date.
     * @param dueDate value.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets Budgeted Time.
     * @param budgetedTime value.
     */
    public void setBudgetedTime(String budgetedTime) {
        this.budgetedTime = budgetedTime;
    }

    /**
     * Sets Client name.
     * @param client name value.
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Sets Pod Lead name.
     * @param podLead name value.
     */
    public void setPodLead(String podLead) {
        this.podLead = podLead;
    }

    /**
     * Sets Pod Color value.
     * @param podColor value.
     */
    public void setPodColor(String podColor) {
        this.podColor = podColor;
    }

    /**
     * Sets pod Description value.
     * @param description value.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPodInformation(final HashMap<String, String> podInformation) {

    }
}
