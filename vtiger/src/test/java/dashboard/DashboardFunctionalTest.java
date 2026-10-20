package dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.DashboardPage;

public class DashboardFunctionalTest extends BaseClass {

    @Test(groups = "Functional")
    public void verifyDashboardTitle() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_003";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();

        String title = dashboardPage.getDashboardPageTitle();

        Assert.assertFalse(
                title.isEmpty(),
                excel.getDataByTestId(
                        "DashboardData",
                        testId,
                        "ExpectedMessage"
                )
        );
    }

    @Test(groups = "Functional")
    public void verifyDashboardURL() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_004";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();

        Assert.assertTrue(
                dashboardPage.getCurrentUrl().contains("module=Dashboard"),
                excel.getDataByTestId(
                        "DashboardData",
                        testId,
                        "ExpectedMessage"
                )
        );
    }

    @Test(groups = "Functional")
    public void verifyDashboardWidgets() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_005";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();

        int widgetCount = dashboardPage.getDashboardWidgetCount();

        Assert.assertTrue(
                widgetCount > 0,
                excel.getDataByTestId(
                        "DashboardData",
                        testId,
                        "ExpectedMessage"
                )
        );
    }

    @Test(groups = "Functional")
    public void verifyDashboardCharts() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_006";

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.openDashboard();

        Assert.assertTrue(dashboardPage.areChartsPresent(),excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
    }
}