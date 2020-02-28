Feature: Task
  Manages tasks

  Background: Create Task List
    Given It login with user credentials
    And The application displays "PodPage" page
    And "Create" a "Pod" with the following
      | PodName     | Pod 01                |
      | Description | This is a description |
    And The application displays "PodPage" page
    And The "Description" should be "This is a description"
    When "Create" a "TaskList" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    Then The application displays "TaskListPage" page
    And The "Description" should be "This is a description"

  Scenario: Create a Task
    When "Create" a "Task" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    Then The application displays "TaskListPage" page
    And The "Description" should be "This is a description"

  Scenario: Edit a Task
    Given "Create" a "Task" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    And The application displays "TaskListPage" page
    And The "Description" should be "This is a description"
    When "Edit" a "Task" with the following
      | Name        | Task 01             |
      | Description | Description updated |
    Then The application displays "TaskListPage" page
    And The "Description" should be "Description updated"

  Scenario: Remove a Task
    Given "Create" a "Task" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    And The application displays "TaskListPage" page
    And The "Description" should be "This is a description"
    When "Remove" a "Task" with the following
      | Name        | Task 01             |
    Then The application displays "TaskListPage" page
