@Tempo
Feature: Sample Tempo Navigation Example to get started with CucumberForAppian

  Prerequisites for running the test:
  1. Run the appropriate setupCustomPropertiesFor... script
  2. Replace BROWSER with either FIREFOX or CHROME
  3. Replace APPIAN_URL with Appian URL, beginning with https:// and including /suite at the end (e.g. https://forum.appian.com/suite)
  4. Replace APPIAN_VERSION with a supported Appian Version in xx.x format
  5. Replace APPIAN_LOCALE with either "en_US" or "en_GB"
  5. Replace APPIAN_USERNAME with a valid username for Appian URL above
  6. Open src/main/resources/configs/users.properties and add a line for the above username that is of the form username=password (e.g. test.user=password)

  Scenario: Login to Appian environment and navigate Tempo tabs
    Given I setup with "BROWSER" browser
    When I set appian URL to "APPIAN_URL"
    And I set appian version to "APPIAN_VERSION"
    And I set appian locale to "APPIAN_LOCALE"
    And I login with username "APPIAN_USERNAME"
    Then I click on menu "News"
    And I click on menu "Tasks"
    And I click on menu "Records"
    And I click on menu "Reports"
    And I click on menu "Actions"
    And I tear down
