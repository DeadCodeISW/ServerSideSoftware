Feature: Offer

Scenario Outline: As a employer i want to post a new job offer.
Given I want to post a job offer
And The job offer have a stable minimum <salary> and <id>
Then I should be able to see my newly offer

Examples:
| id | salary |
| 1  | 940    |

Scenario Outline: As a employer i want to post a new job offer with no salary.
Given I want to post a job offer
And The job offer don't have a low minimum <salary>
Then I should be able to see <error>
Examples:
| salary | error                           |
| 0      | "El salario debe ser mayor a 0" |