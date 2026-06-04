package runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
// You can remove this import
import io.cucumber.testng.CucumberOptions;
import utilities.AllureReportManager;


@CucumberOptions(
	features = {
        "src/test/resources/features/CorporateWellness.feature",
        "src/test/resources/features/DiagnosticTopCities.feature",
        "src/test/resources/features/IdentifyHospitals.feature"
    },
    glue = {"stepdefinitions","hooks"}, // Ensure this matches your package name exactly (lowercase)
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)


@Listeners(utilities.ExtentReportManager.class)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
       AllureReportManager.cleanAllureResults();
    }
    
    @AfterSuite
    public void afterSuite() {
       AllureReportManager.openAllureReport();
    }
}