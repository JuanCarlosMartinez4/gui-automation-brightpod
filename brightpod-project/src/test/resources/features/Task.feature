Feature: Task
  Manages tasks

  Background: Create Task List
    Given I create a Pod with the following values
      | Pod Name    | Pod05              |
      | Description | this a description |
    When I create a TaskList with the following values
      | Name        | Task05                |
      | Description | This is a description |
      | Is Visible  | true                  |

  @Task @Del
  Scenario: Create a Task
    Given I search the taskList by name "Task05"
    When Creates a Task with the following values
      | Task Name     | Task011                  |
      | Member        | juan martinez (Pod Lead) |
      | Due Date      | TODAY                    |
    And Searches task by name "Task011"
    Then Task should contains input data values
