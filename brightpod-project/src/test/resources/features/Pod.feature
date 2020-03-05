Feature: Pod

  Scenario: Create a Pod
    Given The application displays "/projects" page
    When Create a Pod with the following values
      | Pod Name     | Pod01                                  |
      | Start Date   | TODAY                                  |
      | Due Date     | 3 DAYS-0 MONTH-0 YEAR-AFTER-FROM TODAY |
      | Budget Time  | 0.30                                   |
      | Client       | fundacion                              |
      | Project Lead | juan martinez                          |
#      | Color        | #F83A22            |
      | Description  | this a description                     |
    Then Pod should contains
    And Search pod by name "Pod01"
    And Remove pod


#      | Start Date   | Mar 05, 2020       | Today
#      | Due Date     | Mar 10, 2020       | 3 days from now
#    And Quit session

#  Scenario: Archive a Pod
#    Given Create a Pod with the following values
#      | Pod Name    | Pod02              |
#      | Description | this a description |
#    And Search pod by name "Pod02"
#    When Remove pod
#
##    Then Quit session
#
#  Scenario: Edit a Pod
#    Given Create a Pod with the following values
#      | Pod Name    | Pod03              |
#      | Description | this a description |
#    And Search pod by name "Pod03"
#    When Edit a Pod with the following
#      | Pod Name    | PodUpdated          |
#      | Description | description updated |
##    Then Pod should contains
##      | StartDate   | Mar 05, 2020   |
##      | dueDate     |  Mar 10, 2020             |
##      | budgetTime  | 0.00          |
##      | client      | None          |
##      | projectLead | juan martinez |
#    And Search pod by name "PodUpdated"
#    And Remove pod

#    And Quit session