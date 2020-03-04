Feature: Task List

  Background: Login
    When Create a Pod with the following
      | podName     | Pod04              |
      | description | this a description |

#  Scenario: Create a Task List
#    When Create a TaskList with the following
#      | name        | Task01                |
#      | description | This is a description |
#      | isVisible   | true                  |
#    Then Verify TaskList values
#    And Search pod by name "Pod04"
#    And Remove pod

#    And Quit session

#  Scenario: Delete a Task List
#    Given Create a TaskList with the following
#      | name        | Task02                |
#      | description | This is a description |
#      | isVisible   | true                  |
#    And Search taskList by name "Task02"
#    When Remove taskList by name "Task02"
#    Then Search pod by name "Pod04"
#    And Remove pod

#    And Quit session

  Scenario: Edit a Task List
    Given Create a TaskList with the following
      | name        | Task 03               |
      | description | This is a description |
    And Verify TaskList values
    When Edit a TaskList with the following
      | name        | Task 0333           |
      | description | Description updated |
    Then Verify TaskList values
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