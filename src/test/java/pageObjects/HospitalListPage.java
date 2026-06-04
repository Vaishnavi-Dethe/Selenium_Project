package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HospitalListPage extends BasePage {

    public HospitalListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ol/li//div/div[2]/div[1]/a")
    private List<WebElement> hospitalLinks;

    public List<WebElement> getHospitalLinks() {
        return hospitalLinks;
    }
}
