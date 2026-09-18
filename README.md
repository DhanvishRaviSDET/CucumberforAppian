# Cucumber Appian Test Automation

## 📌 Project Overview

This project demonstrates **BDD-based test automation for an Appian application using Cucumber, Java, TestNG, Selenium WebDriver, and the Appian Automated Testing Framework**.

The project combines Appian's existing Cucumber steps with **custom Selenium-based step definitions and fixtures** to automate application-specific scenarios.

The automation covers:

* Appian application login
* Appian Site navigation
* Actions
* Expense reimbursement workflow
* Form field interaction
* Picker field selection
* Checkbox interaction
* Button validation and submission
* Success message validation
* Records navigation
* My Tasks navigation
* Custom date field interaction
* Cucumber HTML and JSON reporting

---

## 🛠️ Technology Stack

| Technology                         | Purpose                     |
| ---------------------------------- | --------------------------- |
| Java                               | Programming language        |
| Cucumber                           | BDD test automation         |
| Gherkin                            | Writing test scenarios      |
| Appian Automated Testing Framework | Appian-specific automation  |
| Selenium WebDriver                 | Custom UI automation        |
| TestNG                             | Test execution              |
| Maven                              | Dependency/build management |
| Eclipse                            | Development environment     |
| Chrome                             | Test browser                |

---

## 📂 Project Structure

```text
Cucumber-Appian-Test-Automation
│
├── src
│   └── test
│       ├── java
│       │   ├── TestRunner
│       │   │   └── RunCucumberForAppianTest.java
│       │   │
│       │   ├── steps
│       │   │   └── AppianSteps.java
│       │   │
│       │   └── fixtures
│       │       └── CustomAppianFixture.java
│       │
│       └── resources
│           └── CucumberforAppian.feature
│
├── target
│   └── cucumber reports
│
├── pom.xml
└── README.md
```

---

## 🧪 Test Scenarios

### 1. Login to Appian Community

The login scenario:

* Initializes the Chrome browser
* Configures the Appian URL
* Sets the Appian version
* Performs login using custom Selenium automation

```gherkin
Scenario: Login to Appian Community

  Given I setup with "CHROME" browser
  And I set appian URL to ""
  And I set appian version to "25.2"
  Then I am logged into Appian
```

---

### 2. Submit Expense Reimbursement

This scenario automates an Appian expense reimbursement workflow.

The test:

1. Navigates to the **Actions** page.
2. Opens **Submit Expense Reimbursement**.
3. Selects **CSR Expenses** from the Expense Category picker.
4. Selects **AED** as the reimbursement currency.
5. Selects the **On behalf of?** checkbox.
6. Enters comments.
7. Verifies that the **SAVE** button is enabled.
8. Saves the request.
9. Verifies the **Action completed** message.

```gherkin
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
```

---

### 3. Verify Expense Records

The scenario navigates to the **Records** section and opens the expense request details.

```gherkin
Scenario: Verify expense records

  When I click on site page "Records"
  And I click on document image link "Expense Requests Details"
```

---

### 4. Verify My Tasks

The scenario navigates to **My Tasks**, applies task filters, and interacts with the Start Date and End Date fields.

```gherkin
Scenario: Verify My tasks

  When I click on site page "My Tasks"
  And I click on radio option "Accepted"
  And I click on radio option "Individual"
  And I custom clicked on Start and End Date

  Then I tear down
```

---

## 🏗️ Framework Architecture

The project follows a layered approach:

```text
                    Feature File
                         │
                         ▼
                  Gherkin Scenario
                         │
                         ▼
                Cucumber Step Definitions
                         │
              ┌──────────┴──────────┐
              ▼                     ▼
     Appian Cucumber Steps     Custom Steps
              │                     │
              │                     ▼
              │              CustomAppianFixture
              │                     │
              │                     ▼
              │              Selenium WebDriver
              │
              ▼
      Appian Application
```

---

## 🔹 Appian Cucumber Integration

The project uses Appian's Cucumber framework through the following glue configuration:

```java
glue = {
    "com.appiancorp.ps.cucumber",
    "steps"
}
```

This allows the project to use:

* Built-in Appian Cucumber steps
* Custom project-specific Cucumber steps

This approach avoids creating custom Selenium code for every Appian interaction when an existing Appian Cucumber step is already available.

---

## 🔹 Custom Step Definitions

Custom application-specific functionality is implemented in:

```text
src/test/java/steps/AppianSteps.java
```

Example:

```java
@Then("I am logged into Appian")
public void iAmLoggedIntoAppian() {

    appianFixture = new CustomAppianFixture();
    appianFixture.login();
}
```

The custom step creates the fixture and invokes the login functionality.

---

## 🔹 Custom Appian Fixture

The custom fixture is implemented in:

```text
src/test/java/fixtures/CustomAppianFixture.java
```

The fixture obtains the WebDriver from the Appian Cucumber framework:

```java
this.driver = CucumberBaseFixture.getSettings().getDriver();
```

It then initializes the page elements using Selenium's `PageFactory`.

```java
PageFactory.initElements(this.driver, this);
```

This allows custom Selenium operations to work with the same driver managed by the Appian automation framework.

---

## 🔹 Custom Selenium Automation

The project uses Selenium WebDriver for application-specific elements that require custom handling.

For example, the login page contains:

```java
@FindBy(xpath = "//input[@placeholder='Username']")
private WebElement usernameInput;

@FindBy(xpath = "//input[@placeholder='Password']")
private WebElement passwordInput;

@FindBy(xpath = "//input[@id='jsLoginButton']")
private WebElement signInBtn;
```

The login operation is then performed through:

```java
public void login() {

    driver.get("");

    usernameInput.sendKeys("");
    passwordInput.sendKeys("");
    signInBtn.click();
}
```

---

## 📅 Custom Date Handling

The My Tasks scenario contains custom handling for Start Date and End Date fields.

The elements are identified using their labels and Appian DatePicker attributes:

```java
@FindBy(xpath="//label[contains(text(),'Start Date')]/parent::div/following-sibling::div/descendant::input[@data-testid='DatePickerWidget-textInput']")
private WebElement StartDateCalendar;
```

The custom methods enter the required dates:

```java
public void clickStartDate() {
    StartDateCalendar.sendKeys("04/09/2026");
}

public void clickEndDate() {
    EndDateCalendar.sendKeys("09/16/2026");
}
```

The corresponding Cucumber step is:

```java
@Given("I custom clicked on Start and End Date")
public void iCustomClickedStartEndDate() {

    appianFixture = new CustomAppianFixture();

    appianFixture.clickStartDate();
    appianFixture.clickEndDate();
}
```

---

## 🏃 Test Runner

The project uses:

```java
AbstractTestNGCucumberTests
```

with `@CucumberOptions`.

```java
@CucumberOptions(
    features = "src/test/resources",
    glue = {
        "com.appiancorp.ps.cucumber",
        "steps"
    },
    plugin = {
        "pretty"
    },
    tags = "@Site"
)
```

### Configuration

**Features**

```text
src/test/resources
```

Specifies the location of the Cucumber feature files.

**Glue**

```text
com.appiancorp.ps.cucumber
steps
```

Loads Appian's built-in Cucumber steps together with custom project steps.

**Tags**

```text
@Site
```

Allows the required Appian scenarios to be selected for execution.

---

## 📊 Cucumber Reporting

The project dynamically creates timestamped Cucumber reports.

The runner generates:

```text
target/cucumber-report-<timestamp>.html
target/cucumber-report-<timestamp>.json
```

Example:

```text
target/
├── cucumber-report-20260918_190000.html
└── cucumber-report-20260918_190000.json
```

The timestamp prevents reports from different executions from overwriting each other.

---

## 🔄 Test Execution Flow

```text
Start Test
    │
    ▼
Initialize Cucumber + TestNG
    │
    ▼
Execute @Site scenarios
    │
    ▼
Appian Cucumber framework
    │
    ├── Built-in Appian steps
    │
    └── Custom project steps
             │
             ▼
      CustomAppianFixture
             │
             ▼
       Selenium WebDriver
             │
             ▼
      Appian Application
             │
             ▼
       Test Validation
             │
             ▼
     HTML / JSON Report
```

---

## ▶️ How to Run

### Prerequisites

Install/configure:

* Java JDK
* Maven
* Eclipse IDE
* Google Chrome
* Appian environment access
* Required Appian automation dependencies

### Run from Eclipse

1. Import the Maven project into Eclipse.
2. Update Maven dependencies.
3. Verify the Appian environment configuration.
4. Open:

```text
RunCucumberForAppianTest.java
```

5. Run it as a **TestNG Test**.

### Run using Maven

From the project root:

```bash
mvn test
```

---

## 🔐 Security

Environment-specific information and credentials should not be committed to the repository.

The following values should be kept private:

* Appian URL
* Username
* Password
* Internal application information
* Company/customer-specific data

For the GitHub demonstration repository, sensitive values should be replaced with placeholders.

---

## 🎯 Key Automation Concepts Demonstrated

This project demonstrates practical experience with:

* BDD using Cucumber
* Gherkin syntax
* Appian test automation
* Selenium WebDriver
* Java
* TestNG
* Maven
* PageFactory
* Custom Cucumber step definitions
* Custom Appian fixtures
* Reusing the Appian-managed WebDriver
* Appian site navigation
* Form automation
* Picker fields
* Checkbox interaction
* Button state validation
* Success message validation
* Custom date-field automation
* HTML and JSON test reporting

---

## 👨‍💻 Author

**Dhanvish R**

QA Automation Engineer

**Automation:** Java | Cucumber | Appian | Selenium | TestNG | Maven

---

## 📌 Disclaimer

This repository is intended to demonstrate test automation framework design and BDD automation practices.

Application URLs, credentials, and other confidential environment-specific information are intentionally excluded from the repository.
