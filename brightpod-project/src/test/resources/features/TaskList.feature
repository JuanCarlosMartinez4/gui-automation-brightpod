Feature: Task List

  Background: Create Pod
    When Creates a Pod with the following values
      | Pod Name    | Pod04              |
      | Description | this a description |

  @TaskList @Del
  Scenario: Create a Task List
    When Creates a TaskList with the following values
      | Name        | Task01                |
      | Description | This is a description |
      | Is Visible  | true                  |
    Then TaskList should contains input data values

  @TaskList @Del
  Scenario: Delete a Task List
    Given Creates a TaskList with the following values
      | Name        | TaskList00            |
      | Description | This is a description |
      | Is Visible  | true                  |
    And Searches taskList by name "TaskList00"
    When Removes taskList by name "TaskList00"
    Then TaskList "TaskList00" should not exist

  @TaskList @Del
  Scenario: Edit a Task List
    Given Creates a TaskList with the following values
      | Name        | Task03                |
      | Description | This is a description |
    And TaskList should contains input data values
    And Searches taskList by name "Task03"
    When Edits a TaskList with the following values
      | Name        | Task0333            |
      | Description | Description updated |
    Then TaskList should contains input data values
