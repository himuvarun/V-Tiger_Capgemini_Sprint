package dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.DashboardPage;

public class DashboardIntegrationTest extends BaseClass {

    @Test(groups = "Integration")
    public void verifyDashboardFromAnotherModule() throws IOException {

        ExcelUtility excel = new ExcelUtility();
        String testId = "DB_014";
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openDashboard();
        Assert.assertTrue(dashboardPage.isDashboardPageDisplayed(),excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
    }

    @Test(groups = {"Integration", "Acceptance"})
    public void verifyDashboardEndToEnd() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_015";
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openDashboard();
        dashboardPage.waitForDashboardToLoad();
        Assert.assertTrue(dashboardPage.isDashboardPageDisplayed(),
                excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
        Assert.assertTrue(
                dashboardPage.getDashboardWidgetCount() > 0,
                excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
        Assert.assertTrue(
                dashboardPage.areChartsPresent(),
                excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
    }
}