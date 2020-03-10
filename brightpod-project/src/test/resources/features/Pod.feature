Feature: Pod

  @Pod @Del
  Scenario: Create a Pod
    When Creates a Pod with the following values
      | Pod Name     | Pod01                                  |
      | Start Date   | TODAY                                  |
      | Due Date     | 3 DAYS-0 MONTH-0 YEAR-AFTER-FROM TODAY |
      | Budget Time  | 0.30                                   |
      | Client       | Jala-Fundation                         |
      | Project Lead | juan martinez                          |
      | Description  | this a description                     |
    Then Pod should contains input data values

  @Pod
  Scenario: Archive a Pod
    Given Creates a Pod with the following values
      | Pod Name    | Pod02              |
      | Description | this a description |
    And Searches pod by name "Pod02"
    When Removes pod
    Then The "Pod02" Pod should not exist

  @Pod @Del
  Scenario: Edit a Pod
    Given Creates a Pod with the following values
      | Pod Name    | Pod03              |
      | Description | this a description |
    And Searches pod by name "Pod03"
    When Edits a Pod with the following
      | Pod Name    | PodUpdated          |
      | Description | description updated |
    Then Pod should contains input data values
