Feature: Identify Hospitals with required facilities
  @smoke
  Scenario: Find hospitals in a location with rating above 3.5 and open 24x7
    Given user is on the Practo home page
    When user selects location "Bangalore"
    And user searches for hospital "Hospital"
    And user collects all hospital links
    Then user filters hospitals which are open 24x7 and rating above 3.5
    And user prints hospital name, rating and parking availability
