Feature: Task List

  Background: Login
    When Create a Pod with the following values
      | Pod Name    | Pod04              |
      | Description | this a description |

  Scenario: Create a Task List
    When Create a TaskList with the following values
      | Name        | Task01                |
      | Description | This is a description |
      | Is Visible  | true                  |
    Then Verify TaskList values
    And Search pod by name "Pod04"
    And Remove pod

#    And Quit session

#  Scenario: Delete a Task List
#    Given Create a TaskList with the following values
#      | Name        | Task02                |
#      | Description | This is a description |
#      | Is Visible  | true                  |
#    And Search taskList by name "Task02"
#    When Remove taskList by name "Task02"
#    Then Search pod by name "Pod04"
#    And Remove pod

#    And Quit session

#  Scenario: Edit a Task List
#    Given Create a TaskList with the following values
#      | Name        | Task 03               |
#      | Description | This is a description |
#    And Verify TaskList values
#    When Edit a TaskList with the following
#      | Name        | Task 0333           |
#      | Description | Description updated |
#    Then Verify TaskList values
#
#  Scenario: Copy a Task List
#    Given "Create" a "TaskList" with the following
#      | Name        | Task 01               |
#      | Description | This is a description |
#    And The application displays "TaskListPage" page
#    And The "Description" should be "This is a description"
#    When "Copy" a "TaskList" with the following
#      | Name | Task 01 |
#    Then The application displays a "TaskListPage" page