package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import utilities.ScreenShotUtil;

public class CorporateWellnessPage extends BasePage{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;
	
    public CorporateWellnessPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='nav-interact' and text()='For Corporates']")
    private WebElement forCorporates;

    @FindBy(xpath = "//a[@event='Nav Provider Marketing:Interacted:Plus Corporate' and text()='Health & Wellness Plans']")
    private WebElement healthAndWellness;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "organizationName")
    private WebElement organizationName;

    @FindBy(id = "contactNumber")
    private WebElement contactNumber;

    @FindBy(id = "officialEmailId")
    private WebElement officialEmailId;

    @FindBy(id = "organizationSize")
    private WebElement organizationSize;

    @FindBy(id = "interestedIn")
    private WebElement interestedIn;

    @FindBy(xpath = "//button[@type='submit' and text()='Schedule a demo']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='text-center']/div[2]")
    private WebElement alertMessage;

    // ================== Actions ==================

    public void openHealthAndWellnessPlans() {
        forCorporates.click();
        wait.until(ExpectedConditions.elementToBeClickable(healthAndWellness)).click();
    }

    public void fillForm(String username, String orgName, String phone, String email) {
        name.sendKeys(username);
        organizationName.sendKeys(orgName);
        contactNumber.sendKeys(phone);
        officialEmailId.sendKeys(email);
    }

    public void selectOrganizationSize(String size) {
        new Select(organizationSize).selectByVisibleText(size);
    }

    public void selectInterestedIn(String option) {
        new Select(interestedIn).selectByVisibleText(option);
    }

    public void submitForm() {
        submitButton.click();
    }

    public String getAlertMessage() {
        return alertMessage.getText();
    }
}