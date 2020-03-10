package entities;

import brightpod.constants.PodConstant;
import brightpod.components.DateFormat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Pod {
    private String podName;
    private String startDate;
    private String dueDate;
    private String budgetedTime;
    private String client;
    private String podLead;
    private String podColor;
    private String description;

    private Set<String> modifiedPodFields = new HashSet<>();

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
    private void setPodName(final String podName) {
        this.podName = podName;
    }

    /**
     * Sets Start Date.
     * @param startDate value.
     */
    private void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets Due Date.
     * @param dueDate value.
     */
    private void setDueDate(final String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets Budgeted Time.
     * @param budgetedTime value.
     */
    private void setBudgetedTime(final String budgetedTime) {
        this.budgetedTime = budgetedTime;
    }

    /**
     * Sets Client name.
     * @param client name value.
     */
    private void setClient(final String client) {
        this.client = client;
    }

    /**
     * Sets Pod Lead name.
     * @param podLead name value.
     */
    private void setPodLead(final String podLead) {
        this.podLead = podLead;
    }

    /**
     * Sets Pod Color value.
     * @param podColor value.
     */
    private void setPodColor(final String podColor) {
        this.podColor = podColor;
    }

    /**
     * Sets pod Description value.
     * @param description value.
     */
    private void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets pod information values.
     * @param podInformation map.
     */
    public void setPodInformation(final Map<String, String> podInformation) {
        Map<String, String> currentPodInformation = new HashMap<>(podInformation);
        if (podInformation.get(PodConstant.START_DATE) != null) {
            currentPodInformation.put(PodConstant.START_DATE,
                DateFormat.formatDate(podInformation.get(PodConstant.START_DATE)));
        }
        if (podInformation.get(PodConstant.DUE_DATE) != null)
            currentPodInformation.put(PodConstant.DUE_DATE, DateFormat.formatDate(podInformation.get(PodConstant.DUE_DATE)));
        HashMap<String, Runnable> strategyMap = composeStrategyMap(currentPodInformation);
        currentPodInformation.keySet().forEach(key -> strategyMap.get(key).run());
        modifiedPodFields.addAll(currentPodInformation.keySet());
    }

    /**
     * Allows to visit all setter methods.
     * @param currentPodInformation map.
     * @return map of visited methods.
     */
    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> currentPodInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(PodConstant.POD_NAME, () -> setPodName(currentPodInformation.get(PodConstant.POD_NAME)));
        strategyMap.put(PodConstant.START_DATE, () ->  setStartDate(currentPodInformation.get(PodConstant.START_DATE)));
        strategyMap.put(PodConstant.DUE_DATE, () -> setDueDate(currentPodInformation.get(PodConstant.DUE_DATE)));
        strategyMap.put(PodConstant.BUDGET_TIME, () -> setBudgetedTime(currentPodInformation.get(PodConstant.BUDGET_TIME)));
        strategyMap.put(PodConstant.CLIENT, () -> setClient(currentPodInformation.get(PodConstant.CLIENT)));
        strategyMap.put(PodConstant.POD_LEAD, () -> setPodLead(currentPodInformation.get(PodConstant.POD_LEAD)));
        strategyMap.put(PodConstant.POD_COLOR, () -> setPodColor(currentPodInformation.get(PodConstant.POD_COLOR)));
        strategyMap.put(PodConstant.DESCRIPTION, () -> setDescription(currentPodInformation.get(PodConstant.DESCRIPTION)));
        return strategyMap;
    }

    /**
     * Gets pod information.
     * @return map of pod values.
     */
    public HashMap<String, String> getPodInformation() {
        HashMap<String, String> values = new HashMap<>();
        HashMap<String, Supplier> strategyMap = composeStrategyMapGet();
        for (String field: modifiedPodFields) {
            values.put(field, strategyMap.get(field).get().toString());
        }
        return values;
    }

    /**
     * Visits all getter methods.
     * @return map of visited methods values.
     */
    private HashMap<String, Supplier> composeStrategyMapGet() {
        HashMap<String, Supplier> strategyMap = new HashMap<>();

        strategyMap.put(PodConstant.POD_NAME, () -> getPodName());
        strategyMap.put(PodConstant.START_DATE, () ->  getStartDate());
        strategyMap.put(PodConstant.DUE_DATE, () -> getDueDate());
        strategyMap.put(PodConstant.BUDGET_TIME, () -> getBudgetedTime());
        strategyMap.put(PodConstant.CLIENT, () -> getClient());
        strategyMap.put(PodConstant.POD_LEAD, () -> getPodLead());
        strategyMap.put(PodConstant.POD_COLOR, () -> getPodColor());
        strategyMap.put(PodConstant.DESCRIPTION, () -> getDescription());
        return strategyMap;
    }
}
