Feature: Diagnostic Page Top Cities
  @regression
  Scenario: Verify top cities displayed in Diagnostic page
    Given user is on the Practo home page
    When user navigates to Diagnostic page
    Then user should see and print all top cities
