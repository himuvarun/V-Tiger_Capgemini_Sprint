package leads;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.HomePage;
import objectRepository.LeadsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsSmokeTest extends BaseClass {

    @Test(groups = "Smoke")
    public void verifyLeadsCreatePageNavigation() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String description = excel.getDataByTestId("LeadsData", "LD_001", "Description");

        HomePage homePage = new HomePage(driver);
        homePage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.clickCreateLead();

        Assert.assertTrue(driver.getCurrentUrl().contains("EditView"),"Create Lead page is not displayed");

        Reporter.log(description, true);
        Reporter.log("Leads Create page navigation verified", true);
    }
}