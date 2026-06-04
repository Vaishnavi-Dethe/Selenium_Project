package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import utilities.ScreenShotUtil;

import io.qameta.allure.Step;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    private static final String LOCATION = "Bangalore";
    private static final String HOSPITAL = "Hospital";
    
    @FindBy(xpath = "//input[@placeholder='Search location']")
    private WebElement searchLocation;

    @FindBy(xpath = "//div[@class='c-omni-suggestion-group']//div[normalize-space()='"+LOCATION+"']")
    private WebElement bangaloreOption;

    @FindBy(xpath = "//input[@data-qa-id='omni-searchbox-keyword']")
    private WebElement searchHospital;

    @FindBy(xpath = "//div[@class='c-omni-suggestion-group']//div[normalize-space()='"+HOSPITAL+"']")
    private WebElement hospitalOption;
    
    
    @Step("Selecting location: {0}")
    public void selectLocation(String location) {
        searchLocation.clear();
        searchLocation.sendKeys(location);
        wait.until(ExpectedConditions.elementToBeClickable(bangaloreOption)).click();
    }
    
    @Step("Searching hospital: {0}")
    public void selectHospital(String hospital) {
        searchHospital.sendKeys(hospital);
        wait.until(ExpectedConditions.elementToBeClickable(hospitalOption)).click();
    }
}