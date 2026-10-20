package dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.DashboardPage;

public class DashboardBVATest extends BaseClass {

    @Test(groups = "BVA")
    public void verifyMinimumDashboardWidget() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_009";
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openDashboard();
        int widgetCount = dashboardPage.getDashboardWidgetCount();
        Assert.assertTrue(widgetCount >= 1,excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
    }
}