package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class DiagnosticPage extends BasePage {

    WebDriver driver;

    // Constructor
    public DiagnosticPage(WebDriver driver) {
        super(driver);
    }

    // Lab Tests link
    @FindBy(xpath = "//a[@aria-label='Lab Tests']")
    private WebElement diagnosticPageLink;

    // Top cities list
    @FindBy(xpath = "//*[@class='u-margint--standard o-f-color--primary']")
    private List<WebElement> topCities;

    // Click on Diagnostic (Lab Tests) page
    public void clickDiagnosticPage() {
        diagnosticPageLink.click();
    }

    // Fetch and print all city names
    public void printTopCities() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(topCities));

        System.out.println("All the top cities in Diagnostic page:");
        for (WebElement city : topCities) {
            System.out.println(city.getText());
        }
    }
}