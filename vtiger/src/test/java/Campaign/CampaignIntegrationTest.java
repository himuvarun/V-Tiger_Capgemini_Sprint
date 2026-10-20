package Campaign;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.CampaignPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignIntegrationTest extends BaseClass {

    @Test(groups = "Integration")
    public void verifyCampaignNavigationAndCreationIntegration()
            throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "CM_013";

        CampaignPage campaignPage =
                new CampaignPage(driver);

        campaignPage.clickCampaigns();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("Campaign"),
                "Campaign module navigation failed"
        );

        campaignPage.clickCreateCampaign();

        campaignPage.enterCampaignName(
                excel.getDataByTestId(
                        "CampaignData", testId, "CampaignName"));

        campaignPage.selectAssignedTo(
                excel.getDataByTestId(
                        "CampaignData", testId, "AssignedTo"));

        campaignPage.selectCampaignStatus(
                excel.getDataByTestId(
                        "CampaignData", testId, "Status"));

        campaignPage.selectCampaignType(
                excel.getDataByTestId(
                        "CampaignData", testId, "Type"));

        campaignPage.enterClosingDate(
                excel.getDataByTestId(
                        "CampaignData", testId, "ClosingDate"));

        campaignPage.clickSave();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("DetailView"),
                "Campaign creation integration failed"
        );

        Reporter.log(
                excel.getDataByTestId(
                        "CampaignData", testId, "Description"),
                true
        );

        Reporter.log(
                "Campaign navigation and creation integration verified",
                true
        );
    }
}