package TestRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

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
public class RunCucumberForAppianTest extends AbstractTestNGCucumberTests {
     
	  static {
	        String timestamp =
	                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

	        System.setProperty(
	                "cucumber.plugin",
	                "pretty,"
	                + "html:target/cucumber-report-" + timestamp + ".html,"
	                + "json:target/cucumber-report-" + timestamp + ".json"
        );

    }
}