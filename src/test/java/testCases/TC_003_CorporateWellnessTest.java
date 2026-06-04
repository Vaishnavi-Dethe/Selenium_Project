package testCases;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.RetryListener;
import pageObjects.CorporateWellnessPage;
import utilities.ExcelUtils;

@Listeners(RetryListener.class)
public class TC_003_CorporateWellnessTest extends BaseClass {
	

	@Feature("Corporate Wellness")
	@Story("Send Invalid data and get Alert message")
    @Test(priority = 3)
    public void verifyCorporateWellnessDemoForm() throws Exception {
		logger.info("----------Corporate Wellness Test starts ----------");
        // ✅ Excel details
        String xlPath = ".\\testdata\\testData.xlsx";
        String sheetName = "Sheet1";

        // ✅ Read Corporate Wellness data from Excel
        String name = ExcelUtils.getCellData(xlPath, sheetName, 5, 1);
        String organization = ExcelUtils.getCellData(xlPath, sheetName, 6, 1);
        String mobile = ExcelUtils.getCellData(xlPath, sheetName, 7, 1);
        String email = ExcelUtils.getCellData(xlPath, sheetName, 8, 1);
        String orgSize = ExcelUtils.getCellData(xlPath, sheetName, 9, 1);
        String interest = ExcelUtils.getCellData(xlPath, sheetName, 10, 1);

        CorporateWellnessPage wellnessPage = new CorporateWellnessPage(driver);
        
        logger.info("Giving inputs for each field");
        wellnessPage.openHealthAndWellnessPlans();

        wellnessPage.fillForm(
                name,
                organization,
                mobile,
                email
        );
        
        logger.info("Selecting the dropdown options");
        wellnessPage.selectOrganizationSize(orgSize);
        wellnessPage.selectInterestedIn(interest);
        wellnessPage.submitForm();

        // CAPTCHA manual pause
        Scanner sc = new Scanner(System.in);
        System.out.println("After validating CAPTCHA... Press ENTER");
        sc.nextLine();
        logger.info("CAPTCHA is validated");

        System.out.println("Alert message : " +
                wellnessPage.getAlertMessage());
        logger.info("Printed the alert message in console");

        driver.navigate().to("https://www.practo.com/plus/corporate");
        logger.info("Redirected to the previous page");
        sc.close();
    }
}
