package entities;

import java.util.HashMap;
import java.util.Map;

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
    private String getPodName() {
        return podName;
    }

    /**
     * Gets Start Date.
     * @return Start Date.
     */
    private String getStartDate() {
        return startDate;
    }

    /**
     * Gets Due Date.
     * @return Due Date.
     */
    private String getDueDate() {
        return dueDate;
    }

    /**
     * Gets Budgeted Time.
     * @return Budgeted Time.
     */
    private String getBudgetedTime() {
        return budgetedTime;
    }

    /**
     * Gets Client name.
     * @return Client name.
     */
    private String getClient() {
        return client;
    }

    /**
     * Gets Pod Lead Name.
     * @return Pod Lead Name.
     */
    private String getPodLead() {
        return podLead;
    }

    /**
     * Gets Pod color.
     * @return Pod color.
     */
    private String getPodColor() {
        return podColor;
    }

    /**
     * Gets pod Description.
     * @return pod Description.
     */
    private String getDescription() {
        return description;
    }

    /**
     * Sets Pot Name.
     * @param podName value.
     */
    private void setPodName(final String podName) {
        if (podName == null) return;
        this.podName = podName;
    }

    /**
     * Sets Start Date.
     * @param startDate value.
     */
    private void setStartDate(final String startDate) {
        if (startDate == null) return;
        this.startDate = startDate;
    }

    /**
     * Sets Due Date.
     * @param dueDate value.
     */
    private void setDueDate(final String dueDate) {
        if (dueDate == null) return;
        this.dueDate = dueDate;
    }

    /**
     * Sets Budgeted Time.
     * @param budgetedTime value.
     */
    private void setBudgetedTime(final String budgetedTime) {
        if (budgetedTime == null) return;
        this.budgetedTime = budgetedTime;
    }

    /**
     * Sets Client name.
     * @param client name value.
     */
    private void setClient(final String client) {
        if (client == null) return;
        this.client = client;
    }

    /**
     * Sets Pod Lead name.
     * @param podLead name value.
     */
    private void setPodLead(final String podLead) {
        if (podLead == null) return;
        this.podLead = podLead;
    }

    /**
     * Sets Pod Color value.
     * @param podColor value.
     */
    private void setPodColor(final String podColor) {
        if (podColor == null) return;
        this.podColor = podColor;
    }

    /**
     * Sets pod Description value.
     * @param description value.
     */
    private void setDescription(final String description) {
        if (description == null) return;
        this.description = description;
    }

    private Map<String, String> podInformation;

    public void setPodInformation(final Map<String, String> podInformation) {
        setPodName(podInformation.get("podName"));
        setStartDate(podInformation.get("startDate"));
        setDueDate(podInformation.get("dueDate"));
        setBudgetedTime(podInformation.get("budgetTime"));
        setClient(podInformation.get("client"));
        setPodLead(podInformation.get("projectLead"));
        setPodColor(podInformation.get("color"));
        setDescription(podInformation.get("description"));
    }

    public Map<String, String> getPodInformation() {
        podInformation = new HashMap<>();
        podInformation.put("podName", getPodName());
        podInformation.put("startDate", getStartDate());
        podInformation.put("dueDate", getDueDate());
        podInformation.put("budgetTime", getBudgetedTime());
        podInformation.put("client", getClient());
        podInformation.put("projectLead", getPodLead());
        podInformation.put("description", getDescription());
        return podInformation;
    }
}
