Feature: Pod

  Scenario: Create a Pod
    Given The application displays "/projects" page
    When Create a Pod with the following
      | podName     | Pod01              |
      | startDate   | 29                 |
      | dueDate     | 1                  |
      | budgetTime  | 0.30               |
      | client      | fundacion          |
      | projectLead | juan martinez      |
      | color       | #F83A22            |
      | description | this a description |
    Then Pod should contains
      | startDate   | Feb 29, 2020 |
      | dueDate     | Mar 01, 2020 |
    And Search pod by name "Pod01"
    And Remove pod
    And Quit session

  Scenario: Archive a Pod
    Given Create a Pod with the following
      | podName     | Pod02              |
      | description | this a description |
    And Search pod by name "Pod02"
    When Remove pod
    Then Quit session

  Scenario: Edit a Pod
    Given Create a Pod with the following
      | podName     | Pod03              |
      | description | this a description |
    And Search pod by name "Pod03"
    When Edit a Pod with the following
      | podName     | PodUpdated          |
      | description | description updated |
    Then Pod should contains
      | podName     | PodUpdated          |
      | startDate   | Mar 03, 2020        |
#      | dueDate     |                     |
      | budgetTime  | 0.00                |
      | client      | None                |
      | projectLead | You                 |
      | description | description updated |
    And Search pod by name "PodUpdated"
    And Remove pod
    And Quit session