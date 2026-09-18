package steps;

import org.openqa.selenium.WebDriver;

import fixtures.CustomAppianFixture;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AppianSteps {

	//private WebDriver driver;
    private CustomAppianFixture appianFixture;

    @Then("I am logged into Appian")
    public void iAmLoggedIntoAppian() {

       
    	appianFixture = new CustomAppianFixture();
        appianFixture.login();
    }
    
   @Given("I custom clicked on Start and End Date") 
    public void iCustomClickedStartEndDate() {
    	appianFixture = new CustomAppianFixture();
    	appianFixture.clickStartDate();
    	appianFixture.clickEndDate();
    }
    
   
}
