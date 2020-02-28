Feature: Task List

  Background: Login
    Given It login with user credentials
    And The application displays "PodPage" page
    When "Create" a "Pod" with the following
      | PodName      | Pod 01                |
      | BudgetedTime | 0.30                  |
      | Client       | fundacion             |
      | PodLead      | juan martinez         |
      | Description  | This is a description |
    Then The application displays "PodPage" page
    And The "Client" should be "fundacion"
    And The "PodLead" should be "juan martinez"
    And The "Description" should be "This is a description"

  Scenario: Create a Task List
    When "Create" a "TaskList" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    Then The application displays "TaskListPage" page
    And The "Description" should be "This is a description"

  Scenario: Delete a Task List
    Given "Create" a "TaskList" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    And The application displays "TaskListPage" page
    And The "Description" should be "This is a description"
    When "Remove" a "TaskList" with the following
      | Name | Task 01 |
    Then The application displays a "TaskListPage" page

  Scenario: Edit a Task List
    Given "Create" a "TaskList" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    And The application displays "TaskListPage" page
    And The "Description" should be "This is a description"
    When "Edit" a "TaskList" with the following
      | Name        | Task 0222           |
      | Description | Description updated |
    Then The application displays a "TaskListPage" page

  Scenario: Copy a Task List
    Given "Create" a "TaskList" with the following
      | Name        | Task 01               |
      | Description | This is a description |
    And The application displays "TaskListPage" page
    And The "Description" should be "This is a description"
    When "Copy" a "TaskList" with the following
      | Name        | Task 01           |
    Then The application displays a "TaskListPage" page