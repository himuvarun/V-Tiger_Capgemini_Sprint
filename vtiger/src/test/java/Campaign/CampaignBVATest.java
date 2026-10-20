package Campaign;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.CampaignPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignBVATest extends BaseClass {

    @Test(groups = {"BVA", "Functional"})
    public void verifyMinimumCampaignName() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "CM_007";
        CampaignPage campaignPage = new CampaignPage(driver);
        campaignPage.clickCampaigns();
        campaignPage.clickCreateCampaign();
        campaignPage.enterCampaignName(excel.getDataByTestId("CampaignData", testId, "CampaignName"));
        campaignPage.selectAssignedTo(excel.getDataByTestId("CampaignData", testId, "AssignedTo"));
        campaignPage.selectCampaignStatus(excel.getDataByTestId("CampaignData", testId, "Status"));
        campaignPage.selectCampaignType(excel.getDataByTestId("CampaignData", testId, "Type"));
        campaignPage.enterClosingDate(excel.getDataByTestId("CampaignData", testId, "ClosingDate"));
        campaignPage.clickSave();

        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),"Minimum Campaign Name was not accepted");
        Reporter.log(excel.getDataByTestId("CampaignData", testId, "Description"),true);
    }
}