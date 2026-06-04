package base;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;


public class BaseClass {

	protected WebDriver driver;
	public Logger logger;  // to store the log messages


	@BeforeSuite(alwaysRun = true)
    public void copyEnvFile() throws Exception {

        Path resultsDir = Paths.get("target/allure-results");
        Files.createDirectories(resultsDir);

        Files.copy(
            Paths.get("src/test/resources/environment.properties"),
            resultsDir.resolve("environment.properties"),
            StandardCopyOption.REPLACE_EXISTING
        );
    }





	@BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browser) {
		logger = LogManager.getLogger(this.getClass());
        // Browser selection
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("edge")) {

        	EdgeOptions options = new EdgeOptions();
        	options.addArguments("--start-maximized");
        	driver = new EdgeDriver(options);

        }
        else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        // Browser configuration
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("implicitWait"))
                )
        );

        // Launch application
        driver.get(ConfigReader.get("baseUrl"));
    }

	@AfterClass
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

}








