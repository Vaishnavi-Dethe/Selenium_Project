package stepdefinitions;

import org.openqa.selenium.WebDriver;

import hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DiagnosticPage;

public class DiagnosticSteps {

    WebDriver driver;
    DiagnosticPage diagnosticPage;

    @When("user navigates to Diagnostic page")
    public void user_navigates_to_diagnostic_page() {
        driver = Hook.getDriver();
        diagnosticPage = new DiagnosticPage(driver);
        diagnosticPage.clickDiagnosticPage();
    }

    @Then("user should see and print all top cities")
    public void user_should_see_and_print_all_top_cities() {
        diagnosticPage.printTopCities();
    }
}