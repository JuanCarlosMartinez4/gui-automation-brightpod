Feature: Login
#  It manages login requests

  Scenario: Login
    When It login with user credentials
    Then Verify user authenticated
    And Selects on Logout


#  Scenario: Logout
#    Given It login with user credentials
#    And The application displays "/projects" page
#    When Selects on Logout
#    Then The application displays "/user" page