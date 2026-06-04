package stepdefinitions;

import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import hooks.Hook;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import pageObjects.CorporateWellnessPage;

public class CorporateWellnessSteps {

    WebDriver driver;
    CorporateWellnessPage wellnessPage;
    Scanner sc;

    @When("user navigates to Corporate Wellness page")
    public void user_navigates_to_corporate_wellness_page() {
        driver = Hook.getDriver();
        wellnessPage = new CorporateWellnessPage(driver);
        wellnessPage.openHealthAndWellnessPlans();
    }

    @And("user fills corporate wellness form details")
    public void user_fills_corporate_wellness_form_details(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);

        wellnessPage.fillForm(
                data.get("name"),
                data.get("organization"),
                data.get("mobile"),
                data.get("email")
        );
    }

    @And("user selects organization size and interest")
    public void user_selects_organization_size_and_interest(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);

        wellnessPage.selectOrganizationSize(data.get("orgSize"));
        wellnessPage.selectInterestedIn(data.get("interest"));
    }

    @And("user submits the demo request form")
    public void user_submits_the_demo_request_form() {

        wellnessPage.submitForm();

        // Manual CAPTCHA handling
        sc = new Scanner(System.in);
        System.out.println("After validating CAPTCHA... Press ENTER");
        sc.nextLine();
    }

    @Then("user should see a confirmation alert message")
    public void user_should_see_a_confirmation_alert_message() {

        System.out.println(
                "Alert message : " + wellnessPage.getAlertMessage()
        );

        driver.navigate().to("https://www.practo.com/plus/corporate");
        sc.close();
    }
}