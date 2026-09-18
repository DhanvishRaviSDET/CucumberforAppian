package fixtures;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.appiancorp.ps.cucumber.fixtures.CucumberBaseFixture;
import com.appiancorp.ps.automatedtest.fixture.TempoFixture;

public class CustomAppianFixture {

    private WebDriver driver;
    private TempoFixture tempoFixture;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='jsLoginButton']")
    private WebElement signInBtn;
    
    @FindBy(xpath="//label[contains(text(),'Start Date')]/parent::div/following-sibling::div/descendant::input[@data-testid='DatePickerWidget-textInput']")
    private WebElement StartDateCalendar;
    
    @FindBy(xpath="//label[contains(text(),'End Date')]/parent::div/following-sibling::div/descendant::input[@data-testid='DatePickerWidget-textInput']")
    private WebElement EndDateCalendar;

    public CustomAppianFixture() {

        this.driver = CucumberBaseFixture.getSettings().getDriver();

        PageFactory.initElements(this.driver, this);
        // Create TempoFixture
        this.tempoFixture = new TempoFixture();

        // VERY IMPORTANT:
        // Give TempoFixture the same Settings/driver
        this.tempoFixture.setSettings(CucumberBaseFixture.getSettings());
    }

    public void login() {
    	driver.get("");

        usernameInput.sendKeys("");
        passwordInput.sendKeys("");
        signInBtn.click();
    }

    public WebDriver getDriver() {
        return driver;
    }
    
   public void clickStartDate()
   {
	   StartDateCalendar.sendKeys("04/09/2026");
   }
   public void clickEndDate()
   {
	   EndDateCalendar.sendKeys("09/16/2026");
   }
}
