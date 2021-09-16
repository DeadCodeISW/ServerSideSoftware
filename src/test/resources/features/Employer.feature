Feature: Employer

  Scenario Outline: Register as an employer.
    Given I want to register as an employer
    And I entered a valid email<email>
    Then I should be able to see my new account.

    Examples:
      | email                  |
      | fano1390@gmail.com     |

  Scenario Outline: Can't register as an employer.
    Given I want to register as an employer
    And I entered an invalid email<email>
    Then I should see an error message<error>.

    Examples:
      | email                  | error                    |
      | abcdf                  |"Ingrese un email valido" |
