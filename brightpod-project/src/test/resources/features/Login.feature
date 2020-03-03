Feature: Login
#  It manages login requests

  Scenario: Login
    When Login with user credentials in "/user"
    Then Verify user authenticated
    And Selects on Logout
    And Quit session

  Scenario: Logout
    Given Login with user credentials in "/user"
    When Selects on Logout
    Then Quit session