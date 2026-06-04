package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.RetryListener;
import pageObjects.HomePage;
import pageObjects.HospitalDetailPage;
import pageObjects.HospitalListPage;
import utilities.ExcelUtils;





@Listeners(RetryListener.class)
public class TC_001_Identify_Hospitals extends BaseClass {

    // Shared list to hold URLs between methods
    private List<String> hospitalUrls = new ArrayList<>();
    private final String xlPath = ".\\testdata\\testData.xlsx";
    private final String sheetName = "Sheet1";

    
    @Feature("Hospital Search")
    @Story("Search hospital by filtering")
    @Test(priority = 1)
    public void searchForHospitals() throws Exception {
        logger.info("---------- Step 1: Searching for Hospitals ----------");
        
        // Read data from Excel
        String location = ExcelUtils.getCellData(xlPath, sheetName, 1, 1);
        String hospitalSearch = ExcelUtils.getCellData(xlPath, sheetName, 2, 1);

        HomePage home = new HomePage(driver);
        home.selectLocation(location);
        logger.info("Location selected: " + location);
        
        home.selectHospital(hospitalSearch);
        logger.info("Hospital search initiated for: " + hospitalSearch);
    }

    
    
    
    @Feature("Hospital Search")
    @Story("Collect Hospital URLs")
    @Test(priority = 2, dependsOnMethods = {"searchForHospitals"})
    public void collectHospitalLinks() {
        logger.info("---------- Step 2: Collecting Hospital Links ----------");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HospitalListPage listPage = new HospitalListPage(driver);
        List<WebElement> hospitalLinks = listPage.getHospitalLinks();

        for (WebElement link : hospitalLinks) {
            js.executeScript("arguments[0].scrollIntoView(true);", link);
            hospitalUrls.add(link.getAttribute("href"));
        }
        
        logger.info("Found " + hospitalUrls.size() + " hospital links.");
    }

    
    
    
    @Feature("Hospital Search")
    @Story("Validate Hospital Details")
    @Test(priority = 3, dependsOnMethods = {"collectHospitalLinks"})
    public void validateHospitalDetails() {
        logger.info("---------- Step 3: Validating Hospital Details ----------");

        for (String url : hospitalUrls) {
            driver.navigate().to(url);

            HospitalDetailPage detail = new HospitalDetailPage(driver);

            double rating = detail.getRating();
            boolean open247 = detail.isOpen247();

            if (open247 && rating > 3.5) {
                detail.clickReadMore();
                boolean parking = detail.hasParking();

                System.out.println(
                        "Hospital: " + detail.getHospitalName()
                                + " | Rating: " + rating
                                + " | Open 24x7 | Parking: "
                                + (parking ? "Available" : "Not Mentioned")
                );
            }
            
            logger.info("Processed: " + url);
            driver.navigate().back();
        }
        logger.info("---------- Identifying Hospital Test completed ----------");
    }
}