package Campaign;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.CampaignPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignFunctionalTest extends BaseClass {

    @Test(groups = {"Functional", "EP"})
    public void verifyCampaignCreationWithValidDetails() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "CM_002";

        String campaignName =
                excel.getDataByTestId(
                        "CampaignData", testId, "CampaignName");

        String assignedTo =
                excel.getDataByTestId(
                        "CampaignData", testId, "AssignedTo");

        String status =
                excel.getDataByTestId(
                        "CampaignData", testId, "Status");

        String type =
                excel.getDataByTestId(
                        "CampaignData", testId, "Type");

        String closingDate =
                excel.getDataByTestId(
                        "CampaignData", testId, "ClosingDate");

        CampaignPage campaignPage =
                new CampaignPage(driver);

        campaignPage.clickCampaigns();
        campaignPage.clickCreateCampaign();

        campaignPage.enterCampaignName(campaignName);
        campaignPage.selectAssignedTo(assignedTo);
        campaignPage.selectCampaignStatus(status);
        campaignPage.selectCampaignType(type);
        campaignPage.enterClosingDate(closingDate);

        campaignPage.clickSave();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("DetailView"),
                "Campaign was not created successfully"
        );

        Reporter.log(
                excel.getDataByTestId(
                        "CampaignData",
                        testId,
                        "Description"
                ),
                true
        );
    }
}