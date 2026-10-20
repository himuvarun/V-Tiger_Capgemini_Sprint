package Campaign;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.CampaignPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignSmokeTest extends BaseClass {

    @Test(groups = "Smoke")
    public void verifyCampaignPageNavigation() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "CM_001";
        CampaignPage campaignPage = new CampaignPage(driver);
        System.out.println("TEST DRIVER = " + driver);
        campaignPage.clickCampaigns();
        campaignPage.clickCreateCampaign();
        Assert.assertTrue(driver.getCurrentUrl().contains("EditView"),"Campaign Create page is not displayed");
        Reporter.log(excel.getDataByTestId("CampaignData",testId,"Description"),true);
    }
}