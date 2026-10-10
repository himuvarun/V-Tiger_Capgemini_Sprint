package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginSystemTest extends BaseClass {

    @Test(groups = "System")
    public void verifyCompleteLoginToProductsFlow() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login","LOGIN-SMOKE-001","Username");
        String password = excel.getDataByTestId("Login","LOGIN-SMOKE-001","Password");
        String expectedTitle = excel.getDataByTestId("Login","LOGIN-SMOKE-001","ExpectedTitle");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getPageTitle(),expectedTitle,"Login to Products system flow failed");
        Assert.assertTrue(productPage.getProductCount() > 0,"Products are not displayed after login");
        Reporter.log("Complete Login to Products system flow passed",true);
    }
}