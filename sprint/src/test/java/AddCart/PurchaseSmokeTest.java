package AddCart;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.CheckoutPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PurchaseSmokeTest extends BaseClass {

    @Test(groups = "Smoke")
    public void verifyCartOpensSuccessfully() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");
        String expectedTitle = excel.getDataByTestId("Checkout", "CHECKOUT-SMO-001", "ExpectedCartTitle");

        new LoginPage(driver).login(username, password);
        ProductPage product = new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();
        Assert.assertEquals(new CartPage(driver).getPageTitle(),expectedTitle,"Cart page did not open");
        Reporter.log("Cart smoke flow passed",true);
    }

    @Test(groups = "Smoke")
    public void verifyCheckoutPageOpensWithProduct() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String productName = excel.getDataByTestId("Product", "PRODUCT-FUN-001", "ProductName");

        new LoginPage(driver).login(username, password);
        ProductPage product = new ProductPage(driver);
        product.addProduct(productName);
        product.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),"Checkout page did not open");
        Assert.assertTrue(new CheckoutPage(driver).isPageDisplayed(),"Checkout information page is not displayed");
        Reporter.log("Checkout smoke flow passed",true);
    }
}