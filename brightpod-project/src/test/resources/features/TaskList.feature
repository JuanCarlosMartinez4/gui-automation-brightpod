Feature: Task List

  Background: Login
    Given Login with user credentials in "/user"
    When Create a Pod with the following
      | projectName | Pod04              |
      | description | this a description |

  Scenario: Create a Task List
    When Create a TaskList with the following
      | name        | Task01                |
      | description | This is a description |
      | isVisible   | true                  |
    Then Verify TaskList values
      | name        | Task01                |
      | description | This is a description |
    And Search a "pod" by name "Pod04"
    And Remove pod
    And Quit session

  Scenario: Delete a Task List
    Given Create a TaskList with the following
      | name        | Task02                |
      | description | This is a description |
      | isVisible   | true                  |
    And Search a "taskList" by name "Task02"
    When Remove taskList by name "Task02"
    Then Search a "pod" by name "Pod04"
    And Remove pod
    And Quit session
#  fix this and complete

#  Scenario: Edit a Task List
#    Given Create a TaskList with the following
#      | Name        | Task 01               |
#      | Description | This is a description |
#    And The application displays "TaskListPage" page
#    And The "Description" should be "This is a description"
#    When "Edit" a "TaskList" with the following
#      | Name        | Task 0222           |
#      | Description | Description updated |
#    Then The application displays a "TaskListPage" page
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