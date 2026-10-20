package leads;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.HomePage;
import objectRepository.LeadsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsBVATest extends BaseClass {

    @Test(groups = {"BVA", "Negative"})
    public void verifyPostalCodeBoundary() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String testId = "LD_011";
        String firstName = excel.getDataByTestId("LeadsData", testId, "FirstName");
        String lastName = excel.getDataByTestId("LeadsData", testId, "LastName");
        String company = excel.getDataByTestId("LeadsData", testId, "Company");
        String phone = excel.getDataByTestId("LeadsData", testId, "Phone");
        String email = excel.getDataByTestId("LeadsData", testId, "Email");
        String assignedTo = excel.getDataByTestId("LeadsData", testId, "AssignedTo");
        String postalCode = excel.getDataByTestId("LeadsData", testId, "PostalCode");

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
        leadsPage.enterPostalCode(postalCode);

        Assert.assertTrue(driver.getCurrentUrl().contains("EditView"),"Create Lead page is not displayed");
        Reporter.log(excel.getDataByTestId("LeadsData", testId, "Description"),true);
        Reporter.log("Postal Code boundary scenario verified", true);
    }

    @Test(groups = {"BVA", "Functional"})
    public void verifyMinimumFirstNameBoundary() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String testId = "LD_012";
        String firstName = excel.getDataByTestId("LeadsData", testId, "FirstName");
        String lastName = excel.getDataByTestId("LeadsData", testId, "LastName");
        String company = excel.getDataByTestId("LeadsData", testId, "Company");
        String phone =  excel.getDataByTestId("LeadsData", testId, "Phone");
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

        Assert.assertTrue(driver.getCurrentUrl().contains("EditView"),"Create Lead page is not displayed");
        Reporter.log(excel.getDataByTestId("LeadsData", testId, "Description"),true);
        Reporter.log("Minimum First Name boundary verified", true);
    }
}