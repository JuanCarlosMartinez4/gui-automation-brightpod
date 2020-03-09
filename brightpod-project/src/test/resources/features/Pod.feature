Feature: Pod


  Scenario: Create a Pod
    Given The application displays "/projects" page
    When Create a Pod with the following values
      | Pod Name     | Pod01                                  |
      | Start Date   | TODAY                                  |
      | Due Date     | 3 DAYS-0 MONTH-0 YEAR-AFTER-FROM TODAY |
      | Budget Time  | 0.30                                   |
      | Client       | Jala-Fundation                         |
      | Project Lead | juan martinez                          |
      | Description  | this a description                     |
    Then Pod should contains
    And Search pod by name "Pod01"
    And Remove pod


  Scenario: Archive a Pod
    Given Create a Pod with the following values
      | Pod Name    | Pod02              |
      | Description | this a description |
    And Search pod by name "Pod02"
    When Remove pod
    Then The "Pod02" Pod should not exist


  Scenario: Edit a Pod
    Given Create a Pod with the following values
      | Pod Name    | Pod03              |
      | Description | this a description |
    And Search pod by name "Pod03"
    When Edit a Pod with the following
      | Pod Name    | PodUpdated          |
      | Description | description updated |
    Then Pod should contains
    And Search pod by name "PodUpdated"
    And Remove pod
