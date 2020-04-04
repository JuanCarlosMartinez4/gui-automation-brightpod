Feature: Login

  @Login
  Scenario: Login
    When I login with user credentials in "/user"
    Then I verify authenticated user
    And I selects on Logout

  @Login
  Scenario: Logout
    Given I login with user credentials in "/user"
    When I selects on Logout
