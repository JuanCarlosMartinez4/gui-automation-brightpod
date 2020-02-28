Feature Pod
  It manages pods

  Background: Login
    When It login with user credentials
    Then The application displays "PodPage" page


  Scenario: Create a Pod
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


  Scenario: Archive a Pod
    Given "Create" a "Pod" with the following
      | PodName      | Pod 02                |
      | BudgetedTime | 0.30                  |
      | Client       | fundacion             |
      | PodLead      | juan martinez         |
      | Description  | This is a description |
    When It search a "pod" by name "Pod 02"
    And "Remove" a "Pod" with the following
      | PodName | Pod 02 |
    Then The application displays "PodPage"