Feature: Login
#  It manages login requests

  Scenario: Login
    When It login with user credentials
    Then The application displays "PodPage" page


  Scenario: Logout
    Given It login with user credentials
    And The application displays "PodPage" page
    When Selects on Logout
    Then The application displays "LogoutPage" page