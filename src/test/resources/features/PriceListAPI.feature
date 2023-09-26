@backend @smoke
Feature: Requesting currency rate price list

  Scenario: Requesting a basic currency price list, which is properly formatted
    Given the Price endpoint has been called through GET
    Then the response code is 200
    And the response is a valid JSON
    And the response includes the following currency rates:
      | USD |
      | GBP |
      | EUR |
