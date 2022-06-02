@login
Feature: Login test suite

  Background:
    Given the user is on the home page

  @smoke
  @testcase-01
  Scenario Outline: Verify valid user can login
    And the user clicks the login button in the home page
    And the user can see the login form
    When the user provides the username "<username>"
    And the user provides the password "<password>"
    And the user clicks the login button in the form
    Then the user is logged successfully

    Examples:
      | username      | password |
      | Maschinenmann | david123 |