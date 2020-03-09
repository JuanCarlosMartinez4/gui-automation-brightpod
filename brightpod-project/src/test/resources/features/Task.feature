Feature: Task
  Manages tasks

#  Background: Create Task List
#    Given Create a Pod with the following values
#      | Pod Name    | Pod05              |
#      | Description | this a description |
#    When Create a TaskList with the following values
#      | Name        | Task05                |
#      | Description | This is a description |
#      | Is Visible  | true                  |
#
#  Scenario: Create a Task
#    Given Search taskList by name "Task05"
#    When Create a Task with the following values
#      | Task Name     | Task 01                  |
#      | Member        | juan martinez (Pod Lead) |
#      | Due Date      | TODAY                    |
#      | High Priority | true                     |
#    Then Task should contains
#    And Search pod by name "Pod05"
#    And Remove pod

#  Scenario: Edit a Task
#    Given "Create" a "Task" with the following
#      | Name        | Task 01               |
#      | Description | This is a description |
#    And The application displays "TaskListPage" page
#    And The "Description" should be "This is a description"
#    When "Edit" a "Task" with the following
#      | Name        | Task 01             |
#      | Description | Description updated |
#    Then The application displays "TaskListPage" page
#    And The "Description" should be "Description updated"
#
#  Scenario: Remove a Task
#    Given "Create" a "Task" with the following
#      | Name        | Task 01               |
#      | Description | This is a description |
#    And The application displays "TaskListPage" page
#    And The "Description" should be "This is a description"
#    When "Remove" a "Task" with the following
#      | Name        | Task 01             |
#    Then The application displays "TaskListPage" page
