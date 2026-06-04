package stepdefinitions;

import io.cucumber.java.en.*;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hooks.Hook;
import pageObjects.*;

public class IdentifyHospitalsSteps {

    WebDriver driver;

    HomePage home;
    HospitalListPage listPage;
    HospitalDetailPage detail;

    List<String> hospitalUrls = new ArrayList<>();

    @Given("user is on the Practo home page")
    public void user_is_on_the_practo_home_page() {
        driver = Hook.getDriver();
        home = new HomePage(driver);
    }

    @When("user selects location {string}")
    public void user_selects_location(String location) {
        home.selectLocation(location);
    }

    @When("user searches for hospital {string}")
    public void user_searches_for_hospital(String hospital) {
        home.selectHospital(hospital);
    }

    @When("user collects all hospital links")
    public void user_collects_all_hospital_links() {

        listPage = new HospitalListPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> hospitalLinks = listPage.getHospitalLinks();

        for (WebElement link : hospitalLinks) {
            js.executeScript("arguments[0].scrollIntoView(true);", link);
            hospitalUrls.add(link.getAttribute("href"));
        }
    }

    @Then("user filters hospitals which are open 24x7 and rating above 3.5")
    public void user_filters_hospitals_open_24x7_and_rating_above_3_5() {

        for (String url : hospitalUrls) {

            driver.navigate().to(url);
            detail = new HospitalDetailPage(driver);

            double rating = detail.getRating();
            boolean open247 = detail.isOpen247();

            if (open247 && rating > 3.5) {
                detail.clickReadMore();
                boolean parking = detail.hasParking();

                System.out.println(
                        "Hospital: " + detail.getHospitalName()
                                + " | Rating: " + rating
                                + " | Open 24x7"
                                + " | Parking: " + (parking ? "Available" : "Not Mentioned")
                );
            }

            driver.navigate().back();
        }
    }

    @And("user prints hospital name, rating and parking availability")
    public void user_prints_hospital_details() {
        // Output already printed in previous step
    }
}