Feature: Login

  @login
  Scenario: Login
    When Login with user credentials in "/user"
    Then Verify user authenticated
    And Selects on Logout

  @login
  Scenario: Logout
    Given Login with user credentials in "/user"
    When Selects on Logout
