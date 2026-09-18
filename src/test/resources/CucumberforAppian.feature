@Site

Feature:  Cucumber Appian test script

  Scenario: Login to Appian Community

    Given I setup with "CHROME" browser
    And I set appian URL to ""
    And I set appian version to "25.2"
    Then I am logged into Appian


  Scenario: Submit expense reimbursement

   
    When I click on site page "Actions"
    And I click on link "Submit Expense Reimbursement"
    And I populate picker field "Expense Category" with partially matching suggestions for "CSR Expenses"
    And I populate picker field "Reimbursement Currency" with partially matching suggestions for "AED"
    And I click on checkbox option "On behalf of?"
    And I populate field with placeholder "Enter your comments" with "Test"
    Then I verify button "SAVE" is enabled
    When I click on button "SAVE"
    Then I verify text "Action completed" is present


  Scenario: Verify expense records

    
    When I click on site page "Records"
    And I click on document image link "Expense Requests Details"


  Scenario: Verify My tasks

    
    When I click on site page "My Tasks"
    And I click on radio option "Accepted"
    And I click on radio option "Individual"
    And I custom clicked on Start and End Date
    Then I tear down
