Feature: Corporate Wellness Demo Form
  @smoke @dataDriven
  Scenario: Submit corporate wellness demo request form
    Given user is on the Practo home page
    When user navigates to Corporate Wellness page
    And user fills corporate wellness form details
      | name         | unknown                    |
      | organization | unknown organization       |
      | mobile       | 9876543211                 |
      | email        | unknown@abc.com            |
    And user selects organization size and interest
      | orgSize  | <500           |
      | interest | Taking a demo |
    And user submits the demo request form
    Then user should see a confirmation alert message
