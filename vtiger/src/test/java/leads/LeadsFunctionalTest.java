package leads;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.HomePage;
import objectRepository.LeadsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsFunctionalTest extends BaseClass {

    @Test(groups = "Functional")
    public void verifyLeadCreationWithValidDetails() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "LD_002";
        String firstName = excel.getDataByTestId("LeadsData", testId, "FirstName");
        String lastName = excel.getDataByTestId("LeadsData", testId, "LastName");
        String company = excel.getDataByTestId("LeadsData", testId, "Company");
        String phone = excel.getDataByTestId("LeadsData", testId, "Phone");
        String email = excel.getDataByTestId("LeadsData", testId, "Email");
        String assignedTo = excel.getDataByTestId("LeadsData", testId, "AssignedTo");
        String city = excel.getDataByTestId("LeadsData", testId, "City");
        String state = excel.getDataByTestId("LeadsData", testId, "State");
        String postalCode = excel.getDataByTestId("LeadsData", testId, "PostalCode");
        String country = excel.getDataByTestId("LeadsData", testId, "Country");
        String description = excel.getDataByTestId("LeadsData", testId, "Description");

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
        leadsPage.enterCity(city);
        leadsPage.enterState(state);
        leadsPage.enterPostalCode(postalCode);
        leadsPage.enterCountry(country);

        leadsPage.clickSave();

        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),"Lead was not created successfully");
        Reporter.log(description, true);
        Reporter.log("Lead created successfully with valid details", true);
    }
}