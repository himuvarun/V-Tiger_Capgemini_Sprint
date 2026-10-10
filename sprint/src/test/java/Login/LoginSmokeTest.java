package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseClass {

	@Test(groups = {"Smoke", "Functional"})
	public void verifyValidLogin() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String expectedTitle = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "ExpectedTitle");

        new LoginPage(driver).login(username, password);
        Assert.assertEquals(new ProductPage(driver).getPageTitle(),expectedTitle,"Valid login failed");
        Reporter.log("Valid login completed successfully", true);
    }
}
