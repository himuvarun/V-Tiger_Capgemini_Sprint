package Campaign;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.CampaignPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignNegativeTest extends BaseClass {

    @Test(groups = {"Negative", "EP"})
    public void verifyCampaignWithoutName() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String testId = "CM_003";
        String expectedMessage = excel.getDataByTestId("CampaignData",testId,"ExpectedMessage");
        CampaignPage campaignPage = new CampaignPage(driver);
        campaignPage.clickCampaigns();
        campaignPage.clickCreateCampaign();
        campaignPage.selectAssignedTo(excel.getDataByTestId("CampaignData", testId, "AssignedTo"));
        campaignPage.selectCampaignStatus(excel.getDataByTestId("CampaignData", testId, "Status"));
        campaignPage.selectCampaignType(excel.getDataByTestId("CampaignData", testId, "Type"));
        campaignPage.enterClosingDate(excel.getDataByTestId("CampaignData", testId, "ClosingDate"));
        campaignPage.clickSave();

        String actualMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualMessage,expectedMessage,"Validation message is incorrect");
        Reporter.log("Expected: " + expectedMessage,true);
        Reporter.log("Actual: " + actualMessage,true);

        driver.switchTo().alert().accept();
    }
}