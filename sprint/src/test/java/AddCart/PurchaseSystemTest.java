package AddCart;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.CheckoutCompletePage;
import objRepo.CheckoutOverviewPage;
import objRepo.CheckoutPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PurchaseSystemTest extends BaseClass {

    private ExcelUtility excel = new ExcelUtility();
    private void login() throws Exception {
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        new LoginPage(driver).login(username, password);
    }

    @Test(groups = "System")
    public void verifyCompletePurchaseFlow() throws Exception {

        String productName = excel.getDataByTestId("Product", "PRODUCT-SYS-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "PostalCode");
        String expectedMessage = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "ExpectedMessage");
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productName);
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(),1,"Product was not added to Cart");
        cartPage.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerDetails(firstName,lastName,postalCode);
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage =new CheckoutOverviewPage(driver);
        Assert.assertTrue(overviewPage.containsProduct(productName),"Product is missing from Checkout Overview");
        overviewPage.finishOrder();
        String actualMessage =new CheckoutCompletePage(driver).getConfirmationMessage();
        Assert.assertEquals(actualMessage,expectedMessage,"End-to-end purchase failed");
        Reporter.log("Complete Login to Purchase system flow passed",true);
    }

    @Test(groups = "System")
    public void verifyPurchaseWithMultipleProducts() throws Exception {

        String productOne = excel.getDataByTestId("Product", "PRODUCT-SYS-001", "ProductName");
        String productTwo = excel.getDataByTestId("Product", "PRODUCT-INT-001", "ProductName");
        String firstName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "FirstName");
        String lastName = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "LastName");
        String postalCode = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "PostalCode");
        String expectedMessage = excel.getDataByTestId("Checkout", "CHECKOUT-SYS-001", "ExpectedMessage");
        login();
        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct(productOne);
        productPage.addProduct(productTwo);

        productPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(),2,"Multiple products were not added to Cart");
        Assert.assertTrue(cartPage.containsProduct(productOne),"First product is missing from Cart");
        Assert.assertTrue(cartPage.containsProduct(productTwo),"Second product is missing from Cart");
        cartPage.clickCheckout();
        
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerDetails(firstName,lastName,postalCode);
        checkoutPage.clickContinue();
        
        CheckoutOverviewPage overviewPage =new CheckoutOverviewPage(driver);
        Assert.assertTrue(overviewPage.containsProduct(productOne),"First product is missing from Checkout Overview");
        Assert.assertTrue(overviewPage.containsProduct(productTwo),"Second product is missing from Checkout Overview");
        overviewPage.finishOrder();
        
        String actualMessage =new CheckoutCompletePage(driver).getConfirmationMessage();
        Assert.assertEquals(actualMessage,expectedMessage,"Multiple product purchase failed");
        Reporter.log("Multiple product purchase system flow passed",true);
    }
}