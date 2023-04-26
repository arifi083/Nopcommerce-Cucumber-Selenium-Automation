package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={".//Features//Login.feature",".//Features//Customers.feature"},
        glue="stepDefinitions",
        dryRun=false,
        monochrome=true,
        plugin = {"pretty","html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"}
       )
public class TestRun {
}
