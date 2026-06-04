package hooks;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
// ✅ ADDED IMPORT: Connects your hooks to your screenshot utility folder
import utilities.ScreenShotUtil;

public class Hook {

    protected static WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.practo.com");
    }

    @After
    public void tearDown(Scenario scenario) {

        // Check if the scenario failed and driver is alive
        if (scenario.isFailed() && driver != null) {
            
            // ✅ ADDED CLEANER: Replaces spaces and special symbols so Windows can create a clean file name
            String cleanScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9_]", "_");

            // ✅ ADDED ACTION: This forces the script to drop a .png file inside your local /screenshots/ folder
            ScreenShotUtil.screenShotTC(driver, cleanScenarioName);

            // This keeps your Allure report attachment working beautifully
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Failed Scenario Screenshot",
                    new ByteArrayInputStream(screenshot)
            );
        }

        // Safely close down the browser instance
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}