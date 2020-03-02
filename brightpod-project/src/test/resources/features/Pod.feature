Feature: Pod

  Background: Login
    When It login with user credentials
    Then The application displays "/projects" page

  Scenario: Create a Pod
    When "Create" a "Pod" with the following
      | projectName | Pod01              |
      | startDate   | 29                 |
      | dueDate     | 1                  |
      | budgetTime  | 0.30               |
      | client      | fundacion          |
      | projectLead | juan martinez      |
      | color       | #F83A22            |
      | description | this a description |
    Then The application displays "PodPage" page
    And Verify Pod
      | projectName | Pod01              |
      | startDate   | Feb 29, 2020       |
      | dueDate     | Mar 01, 2020       |
      | budgetTime  | 0.30               |
      | client      | fundacion          |
      | projectLead | juan martinez      |
      | description | this a description |
    And Search a "pod" by name "Pod01"
    And Remove pod
#
#  Scenario: Archive a Pod
#    Given Create a "Pod" with the following
#      | projectName | Pod02              |
#      | description | this a description |
#    And Search a "pod" by name "Pod02"
#    When Remove pod
#    Then The application displays "/user"

#  Scenario: Edit a Pod
#    Given Create a "Pod" with the following
#      | projectName | Pod03              |
#      | description | this a description |
#    And Search a "pod" by name "Pod03"
#    When Edit a "Pod" with the following
#      | projectName | PodUpdated          |
#      | description | description updated |
#    Then The application displays "/projects"
#    And Verify Pod
#      | projectName | PodUpdated          |
#      | startDate   | Mar 02, 2020        |
#      | dueDate     |                     |
#      | budgetTime  | 0.00                |
#      | client      | None                |
#      | projectLead | You                 |
#      | description | description updated |
#    And Search a "pod" by name "PodUpdated"
#    And Remove pod
#    Then The application displays "/user"