Feature: Freelancer

  Scenario Outline: Register as an freelancer.
    Given I want to register as an freelancer
    And I entered a valid email<email>
    Then I should be able to see my new account.

    Examples:
      | email                  |
      | bill1390@gmail.com     |

  Scenario Outline: Can't register as an freelancer.
    Given I want to register as an freelancer
    And I entered an invalid email<email>
    Then I should see an error message<error>.

    Examples:
      | email                  | error                    |
      | abcdf                  |"Ingrese un email valido" |