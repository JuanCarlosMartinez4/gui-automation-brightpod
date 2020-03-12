Feature: Task List

  Background: Create Pod
    When I create a Pod with the following values
      | Pod Name    | Pod04              |
      | Description | this a description |

  @TaskList @Del
  Scenario: Create a Task List
    When I create a TaskList with the following values
      | Name        | Task01                |
      | Description | This is a description |
      | Is Visible  | true                  |
    Then The TaskList should contains inserted data values

  @TaskList @Del
  Scenario: Remove a Task List
    Given I create a TaskList with the following values
      | Name        | TaskList00            |
      | Description | This is a description |
      | Is Visible  | true                  |
    And I search the taskList by name "TaskList00"
    When I remove the taskList by name "TaskList00"
    Then The TaskList "TaskList00" should not exist

  @TaskList @Del
  Scenario: Edit a Task List
    Given I create a TaskList with the following values
      | Name        | Task03                |
      | Description | This is a description |
    And The TaskList should contains inserted data values
    And I search the taskList by name "Task03"
    When I edit the TaskList with the following values
      | Name        | Task0333            |
      | Description | Description updated |
    Then The TaskList should contains inserted data values
