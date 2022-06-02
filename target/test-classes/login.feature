@login
Feature: Login test suite

  Background:
    Given the user is on the home page
    And the user clicks the login button in the home page
    And the user can see the login form

  @smoke
    @testcase-01
  Scenario Outline: Verify valid user can login
    When the user provides the username "<username>"
    And the user provides the password "<password>"
    And the user clicks the login button in the form
    Then the user is logged successfully

    Examples:
      | username      | password |
      | Maschinenmann | david123 |

  @testcase-02
  Scenario Outline: Verify alert appear if invalid credentials
    When the user provides the username "<username>"
    And the user provides the password "<password>"
    And the user clicks the login button in the form
    Then the user can see the error alert

    Examples:
      | username | password |
      | Maschi   | david123 |

    #No estoy seguro de si esta prueba está bien diseñada
  @testcase-03
  Scenario Outline: Verify specific text in alert if invalid username or password
    When the user provides the username "<username>"
    And the user provides the password "<password>"
    And the user clicks the login button in the form
    Then the user can see the specific error "<message>"

    Examples:
      | username      | password | message              |
      | Maschi        | david123 | User does not exist. |
      | Maschinenmann | david    | Wrong password.      |