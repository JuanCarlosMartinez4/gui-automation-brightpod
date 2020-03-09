Feature: Task List

  Background: Create Pod
    When Create a Pod with the following values
      | Pod Name    | Pod04              |
      | Description | this a description |


  Scenario: Create a Task List
    When Create a TaskList with the following values
      | Name        | Task01                |
      | Description | This is a description |
      | Is Visible  | true                  |
    Then TaskList should contains
    And Search pod by name "Pod04"
    And Remove pod


  Scenario: Delete a Task List
    Given Create a TaskList with the following values
      | Name        | Task02                |
      | Description | This is a description |
      | Is Visible  | false                 |
    And Search taskList by name "Task02"
    When Remove taskList by name "Task02"
    Then TaskList "Task02" should not exist
    And Search pod by name "Pod04"
    And Remove pod


  Scenario: Edit a Task List
    Given Create a TaskList with the following values
      | Name        | Task03                |
      | Description | This is a description |
    And TaskList should contains
    And Search taskList by name "Task03"
    When Edit a TaskList with the following values
      | Name        | Task0333            |
      | Description | Description updated |
    Then TaskList should contains
    And Search pod by name "Pod04"
    And Remove pod
