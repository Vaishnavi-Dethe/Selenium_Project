package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

import java.util.List;

public class HospitalDetailPage extends BasePage {

    public HospitalDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement hospitalName;

    @FindBy(xpath = "//span[@class='common__star-rating__value']")
    private List<WebElement> ratings;

    @FindBy(xpath = "//p[text()='Open 24 x 7']")
    private List<WebElement> open247;

    @FindBy(xpath = "//span[text()='Read more info']")
    private List<WebElement> readMore;

    @FindBy(xpath = "//span[text()='Parking']")
    private List<WebElement> parking;

    public String getHospitalName() {
        return hospitalName.getText();
    }

    public double getRating() {
        if (ratings.isEmpty()) return 0.0;
        return Double.parseDouble(ratings.get(0).getText());
    }

    public boolean isOpen247() {
        return !open247.isEmpty();
    }

    public void clickReadMore() {
        if (!readMore.isEmpty()) {
            readMore.get(0).click();
        }
    }

    public boolean hasParking() {
        return !parking.isEmpty();
    }
}