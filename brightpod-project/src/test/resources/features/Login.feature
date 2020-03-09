Feature: Login
  Background: Logout on Login page
    Given Selects on Logout

  Scenario: Login
    When Login with user credentials in "/user"
    Then Verify user authenticated
    And Selects on Logout

  Scenario: Logout
    Given Login with user credentials in "/user"
    When Selects on Logout
