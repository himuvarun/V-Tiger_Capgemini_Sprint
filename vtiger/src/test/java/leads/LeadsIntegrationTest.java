package leads;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.HomePage;
import objectRepository.LeadsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsIntegrationTest extends BaseClass {

    @Test(groups = "Integration")
    public void verifyApplicationNavigationIntegratesWithLeadCreation() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String testId = "LD_015";
        String firstName = excel.getDataByTestId("LeadsData", testId, "FirstName");
        String lastName = excel.getDataByTestId("LeadsData", testId, "LastName");
        String company = excel.getDataByTestId("LeadsData", testId, "Company");
        String phone = excel.getDataByTestId("LeadsData", testId, "Phone");
        String email = excel.getDataByTestId("LeadsData", testId, "Email");
        String assignedTo = excel.getDataByTestId("LeadsData", testId, "AssignedTo");

        HomePage homePage = new HomePage(driver);
        homePage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.clickCreateLead();

        leadsPage.enterFirstName(firstName);
        leadsPage.enterLastName(lastName);
        leadsPage.enterCompany(company);
        leadsPage.enterPhone(phone);
        leadsPage.enterEmail(email);
        leadsPage.selectAssignedTo(assignedTo);
        leadsPage.clickSave();

        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),"Application and Lead creation integration failed");
        Reporter.log(excel.getDataByTestId("LeadsData", testId, "Description"),true);
        Reporter.log("Application navigation and Lead creation integration verified",true);
    }
}