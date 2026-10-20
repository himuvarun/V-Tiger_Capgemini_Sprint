package dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.DashboardPage;

public class DashboardSmokeTest extends BaseClass {

    @Test(groups = "Smoke")
    public void verifyDashboardNavigation() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_001";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();
        dashboardPage.waitForDashboardToLoad();

        Assert.assertTrue(
                dashboardPage.isDashboardPageDisplayed(),
                excel.getDataByTestId(
                        "DashboardData",
                        testId,
                        "ExpectedMessage"
                )
        );
    }

    @Test(groups = "Smoke")
    public void verifyDashboardPageDisplayed() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_002";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();

        Assert.assertTrue(
                dashboardPage.isDashboardPageDisplayed(),
                excel.getDataByTestId(
                        "DashboardData",
                        testId,
                        "ExpectedMessage"
                )
        );
    }
}