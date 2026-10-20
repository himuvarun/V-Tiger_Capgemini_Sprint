package dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.DashboardPage;

public class DashboardNegativeTest extends BaseClass {

    @Test(groups = "Negative")
    public void verifyDashboardAfterRefresh() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_007";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();
        dashboardPage.refreshDashboard();

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