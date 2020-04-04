Feature: Pod

  @Pod @Del
  Scenario: Create a Pod
    When I create a Pod with the following values
      | Pod Name     | Pod01                                  |
      | Start Date   | TODAY                                  |
      | Due Date     | 3 DAYS-0 MONTH-0 YEAR-AFTER-FROM TODAY |
      | Budget Time  | 0.30                                   |
      | Client       | Jala-Fundation                         |
      | Project Lead | juan martinez                          |
      | Description  | this a description                     |
    Then The Pod should contains inserted data values

  @Pod
  Scenario: Remove a Pod
    Given I create a Pod with the following values
      | Pod Name    | Pod02              |
      | Description | this a description |
    And I search the pod by name "Pod02"
    When I remove pod
    Then The Pod "Pod02" should not exist

  @Pod @Del
  Scenario: Edit a Pod
    Given I create a Pod with the following values
      | Pod Name    | Pod03              |
      | Description | this a description |
    And I search the pod by name "Pod03"
    When I edit the Pod with the following values
      | Pod Name    | PodUpdated          |
      | Description | description updated |
    Then The Pod should contains inserted data values
