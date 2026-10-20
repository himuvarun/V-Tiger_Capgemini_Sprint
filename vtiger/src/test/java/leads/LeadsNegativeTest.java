package leads;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.HomePage;
import objectRepository.LeadsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsNegativeTest extends BaseClass {

    @Test(groups = {"Functional", "Negative", "EP"})
    public void verifyLeadWithoutLastName() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String testId = "LD_004";

        String firstName = excel.getDataByTestId("LeadsData", testId, "FirstName");
        String company = excel.getDataByTestId("LeadsData", testId, "Company");
        String phone = excel.getDataByTestId("LeadsData", testId, "Phone");
        String email = excel.getDataByTestId("LeadsData", testId, "Email");
        String assignedTo = excel.getDataByTestId("LeadsData", testId, "AssignedTo");
        String expectedMessage = excel.getDataByTestId("LeadsData", testId, "ExpectedMessage");

        HomePage homePage = new HomePage(driver);
        homePage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.clickCreateLead();

        leadsPage.enterFirstName(firstName);
        leadsPage.enterCompany(company);
        leadsPage.enterPhone(phone);
        leadsPage.enterEmail(email);
        leadsPage.selectAssignedTo(assignedTo);

        leadsPage.clickSave();

        String actualMessage = driver.switchTo().alert().getText();

        Assert.assertEquals(actualMessage,expectedMessage,"Validation message is incorrect");
        Reporter.log("Expected: " + expectedMessage,true);
        Reporter.log("Actual: " + actualMessage,true);

        driver.switchTo().alert().accept();
        Reporter.log(excel.getDataByTestId("LeadsData",testId,"Description"),true);
    }

    @Test(groups = {"Functional", "Negative", "EP"})
    public void verifyLeadWithoutAssignedTo() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "LD_008";
        String firstName = excel.getDataByTestId("LeadsData", testId, "FirstName");
        String lastName = excel.getDataByTestId("LeadsData", testId, "LastName");
        String company = excel.getDataByTestId("LeadsData", testId, "Company");
        String phone = excel.getDataByTestId("LeadsData", testId, "Phone");
        String email = excel.getDataByTestId("LeadsData", testId, "Email");
        String expectedMessage = excel.getDataByTestId("LeadsData", testId, "ExpectedMessage");

        HomePage homePage = new HomePage(driver);
        homePage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.clickCreateLead();

        leadsPage.enterFirstName(firstName);
        leadsPage.enterLastName(lastName);
        leadsPage.enterCompany(company);
        leadsPage.enterPhone(phone);
        leadsPage.enterEmail(email);
        leadsPage.clearAssignedTo();
        leadsPage.clickSave();

        String actualMessage = driver.switchTo().alert().getText();

        Assert.assertEquals(actualMessage,expectedMessage,"Validation message is incorrect");
        Reporter.log("Expected: " + expectedMessage,true);
        Reporter.log("Actual: " + actualMessage,true);

        driver.switchTo().alert().accept();
        Reporter.log(excel.getDataByTestId("LeadsData",testId,"Description"),true);
    }
}