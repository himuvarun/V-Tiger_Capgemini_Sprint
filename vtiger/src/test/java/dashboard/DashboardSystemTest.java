package dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objectRepository.DashboardPage;

public class DashboardSystemTest extends BaseClass {

    @Test(groups = "System")
    public void verifyDashboardScrollToBottom() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        String testId = "DB_011";
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openDashboard();
        dashboardPage.scrollToBottom();
        Assert.assertTrue(
                dashboardPage.getCurrentScrollPosition() > 0,
                excel.getDataByTestId("DashboardData",testId,"ExpectedMessage"));
    }
}