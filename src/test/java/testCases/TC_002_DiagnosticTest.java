package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.RetryListener;
import pageObjects.DiagnosticPage;


public class TC_002_DiagnosticTest extends BaseClass {
	

	@Feature("Diagnostic Page")
	@Story("Search Top Cities")
    @Test(priority = 2)
    public void verifyTopCitiesInDiagnosticPage() {
    	
		logger.info("----------Diagnostic Test starts ----------");
    	
        DiagnosticPage diagnosticPage = new DiagnosticPage(driver);
        
        logger.info("Clicking diagnostic page");
        diagnosticPage.clickDiagnosticPage();
        logger.info("Printing Top Cities in Diagnostic Page");
        diagnosticPage.printTopCities();
    	
    	
    }
}