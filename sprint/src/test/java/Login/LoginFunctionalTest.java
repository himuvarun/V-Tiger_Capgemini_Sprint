package Login;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginFunctionalTest extends BaseClass {

    @Test(groups = "Functional")
    public void verifyUsernameAcceptsValidInput() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-FUN-001", "Username");
        LoginPage page = new LoginPage(driver);
        page.enterUsername(username);
        Assert.assertEquals(driver.findElement(org.openqa.selenium.By.id("user-name")).getAttribute("value"),username,"Username was not accepted");
        Reporter.log("Username accepted", true);
    }

    @Test(groups = "Functional")
    public void verifyPasswordIsMasked() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String password = excel.getDataByTestId("Login", "LOGIN-FUN-001", "Password");
        LoginPage page = new LoginPage(driver);
        page.enterPassword(password);
        Assert.assertTrue(page.isPasswordMasked(),"Password is not masked");
        Reporter.log("Password masking verified", true);
    }

    
}
